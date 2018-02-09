package com.vispractice.fmc.web.controller.doc;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.constrant.SysDocConstant;
import com.vispractice.fmc.business.base.domain.SbWFApprovalForm;
import com.vispractice.fmc.business.entity.sys.news.view.DocumentSubmmitedV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfProcess;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfWorkitem;
import com.vispractice.fmc.business.service.sys.facade.IProcessFacade;
import com.vispractice.fmc.business.service.sys.news.ISysNewsMainService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfProcessService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfWorkitemService;
import com.vispractice.fmc.business.services.setting.ILoadBillFormService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

@Slf4j
@Controller
@RequestMapping("/docManage/billProcess")
public class BillProcessController<T> extends AbstractController<T> {
	@Autowired
	private ISysNewsMainService sysNewsMainService;
	
	@Autowired
	private ISysWfWorkitemService sysWfWorkitemService;

	@Autowired
	private IProcessFacade processFacade;
	
	@Autowired
	private ISysWfProcessService sysWfProcessService;
	
	@Autowired
	private ILoadBillFormService loadBillFormService;

	@RequestMapping("/index")
	public String indexProcess() {
		return "docManage/billProcess/sys_news_main";
	}

	/**
	 * 查询流程调整单据
	 */
	@RequestMapping(value = "/findAll",method = RequestMethod.POST)
	@ResponseBody
	public Page<DocumentSubmmitedV> findAll(@RequestParam String context,@RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			DocumentSubmmitedV documentSubmmitedV = mapper.readValue(context,DocumentSubmmitedV.class);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			documentSubmmitedV.setDocStatus(SysDocConstant.DOC_STATUS_EXAMINE + "," + SysDocConstant.DOC_STATUS_REFUSE);
			Page<DocumentSubmmitedV> page = sysNewsMainService.searchDocumentSubmmitedV(documentSubmmitedV,pageVo.getPageable());
			
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 获取单据审批
	 */
	@RequestMapping(value = "/billApprove/findForm",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult billApproveForm(@RequestParam String wfInstanceId,@RequestParam String wfNodeId,@RequestParam String identify) {
		WebMessageResult result = new WebMessageResult();
		try {
			SbWFApprovalForm approveForm = loadBillFormService.initApproveForm(wfInstanceId,wfNodeId,this.getCurrentUser(),identify);
			
			result.setData(approveForm);
		} catch (Exception e) {
			log.error("获取审批页面发生错误.");
			result.setErrorMessage("获取审批页面发生错误.");
		}
		
		return result;
	}

	/***
	 * 单据审批提交
	 */
	@RequestMapping(value = "/billApprove/saveOrUpdateForm",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult billApprove(String context) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
		
		try {
			//解析处理单据信息
			SbWFApprovalForm sbWFApprovalForm = objectMapper.readValue(context,SbWFApprovalForm.class);
			
			//设置当前处理人
			SysOrgPerson sysOrgPerson = this.getCurrentUser();
			if (StringUtils.isEmpty(sbWFApprovalForm.getWfAuditedUserNo())) {
				sbWFApprovalForm.setWfAuditedUserId(sysOrgPerson.getFdId());
				sbWFApprovalForm.setWfAuditedUserNo(sysOrgPerson.getFdLoginName());
			}
			
			//设置取消沟通人员
			if (sbWFApprovalForm.getCelCommunicaterList() != null && sbWFApprovalForm.getCelCommunicaterList().size()>0) {
				StringBuffer handlerIds = new StringBuffer();
				StringBuffer handlerNames = new StringBuffer();
				for (Map<String, String> celCommunicater : sbWFApprovalForm.getCelCommunicaterList()) {
					handlerIds.append(celCommunicater.get("fdId"));
					handlerIds.append(";");
					handlerNames.append(celCommunicater.get("fdName"));
					handlerNames.append(";");
				}
				sbWFApprovalForm.setCommicateHandlerIds(handlerIds.substring(0,handlerIds.length()-1));
				sbWFApprovalForm.setCommicateHandlerNames(handlerNames.substring(0,handlerNames.length()-1));
			}
			
			//根据审批类型进行审批
			String approvalType = sysWfProcessService.getApprovalType(sbWFApprovalForm.getWfResult());
			if (OAConstant.APPROVAL_TYPE_INSTANCE.equals(approvalType)) {
				processFacade.sbWFInstanceApprovalSrv(sbWFApprovalForm);
			} else {
				processFacade.sbWFNodeApprovalSrv(sbWFApprovalForm);
			}
			result.setMessage("流程处理成功.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			result.setErrorMessage(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 获取子流程信息
	 */
	@RequestMapping(value = "/getSubProcess",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult getSubProcess(@RequestParam String wfInstanceId) {
		WebMessageResult result = new WebMessageResult();
		
		List<SysWfProcess> sysWfProcesses = sysWfProcessService.findSubProcessByParentId(wfInstanceId);
		
		try {
			result.setCode(WebMessageResult.SUCCESS);
			result.setData(sysWfProcesses);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			result.setCode(WebMessageResult.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 多级沟通,获取沟通人员信息
	 */
	@RequestMapping(value="/getCommunicateScope",method = RequestMethod.GET)
	@ResponseBody
	public WebMessageResult getCommunicateScope(@RequestParam String workitemId){
		WebMessageResult result = new WebMessageResult();
		
		try {
			SysWfWorkitem sysWfWorkitem = sysWfWorkitemService.findByFdId(workitemId);
			if (null==sysWfWorkitem) {
				result.setErrorMessage("当前工作事项不存在,获取沟通范围失败.");
				return result;
			}else if (null==sysWfWorkitem.getFdRelationWorkitemId()&&null==sysWfWorkitem.getFdSourceWorkitemId()) {
				result.setData(null);
				return result;
			}
			
			List<SysOrgElement> communicateScope = sysWfWorkitemService.getCommunicateScope(sysWfWorkitem.getFdNodeId());
			result.setData(communicateScope);
		} catch (Exception e) {
			result.setErrorMessage(e.getMessage());
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
}
package com.vispractice.fmc.web.controller.aboutMyself.myBills;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.base.utils.ResourceUtil;
import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.constrant.SysOrgConstant;
import com.vispractice.fmc.business.base.domain.SbWFApprovalForm;
import com.vispractice.fmc.business.base.domain.SysWfBusinessForm;
import com.vispractice.fmc.business.base.utils.ProcessLogicHelper;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.news.view.DocumentSubmmitedV;
import com.vispractice.fmc.business.entity.sys.news.view.SysNewsBasicV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.AbstractProcessDetail;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.INodeDetail;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.IProcessDetail;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfTokenRepository;
import com.vispractice.fmc.business.entity.sys.workflow.support.detail.OAAbstractManualNodeDetail;
import com.vispractice.fmc.business.entity.sys.workflow.support.detail.OAProcessDetail;
import com.vispractice.fmc.business.service.sys.facade.IProcessFacade;
import com.vispractice.fmc.business.service.sys.news.ISysNewsMainService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.services.setting.ILoadBillFormService;
import com.vispractice.fmc.sys.workflow.engine.formula.service.IFormulaPluginService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

@Controller
@RequestMapping("/aboutMyself/myBills")
@Slf4j
public class SysNewsMainController extends AbstractController<SysNewsBasicV> {
	@Autowired
	private ISysOrgElementService elementService;
	
	@Autowired
	private ISysNewsMainService sysNewsMainService;
	
	@Autowired
	private ILoadBillFormService loadBillFormService;

	@Autowired
	private IProcessFacade processFacade;

	@Autowired
	private SysWfTokenRepository sysWfTokenService;
	
	@Autowired
	private ISysOrgElementService sysOrgElementService;
	
	private IFormulaPluginService formulaPluginService;
	
	@Autowired
	private ResourceUtil resourceUtil;
	
	/**
	 * 我提交的单据
	 */
	@RequestMapping("/index")
	public String toIndex() {
		return "aboutMyself/myBills/sys_news_main";
	}
	
	/**
	 * 初始化单据信息
	 * @param fdTemplateId
	 * @return
	 */
	@RequestMapping(value = "/initDetail",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult initDetail(@RequestParam String fdTemplateId) {
		WebMessageResult result = new WebMessageResult();
		try {
			SysWfBusinessForm sysWfBusinessForm = loadBillFormService.initForm(fdTemplateId,this.getCurrentUser());
			
			result.setCode(WebMessageResult.SUCCESS);
			result.setData(sysWfBusinessForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			result.setCode(WebMessageResult.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
		
	}

	/**
	 * 流程中心详细信息
	 */
	@RequestMapping(value = "/getDetail",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult getDetail(@RequestParam String wfInstanceId) {
		WebMessageResult result = new WebMessageResult();
		
		try {
			SysWfBusinessForm sysWfBusinessForm = loadBillFormService.loadForm(wfInstanceId,this.getCurrentUser());
			log.info(">>>>>>>>>>>>>>>>>" + sysWfBusinessForm.getFdFlowContent() + ">>>>>>>>>>>>>>>>>>>>");
			String fdFlowContent = this.setFlowContent(sysWfBusinessForm.getFdFlowContent(),sysWfBusinessForm.getBasic().getDocCreatorId());
			sysWfBusinessForm.setFdFlowContent(fdFlowContent);
			log.info(">>>>>>>>>>>>>>>>>" + sysWfBusinessForm.getFdFlowContent() + ">>>>>>>>>>>>>>>>>>>>");
			
			result.setCode(WebMessageResult.SUCCESS);
			result.setData(sysWfBusinessForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			result.setCode(WebMessageResult.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private String setFlowContent(String flowContent,String draftId) throws Exception {
		SysOrgElement baseElement = sysOrgElementService.findByFdId(draftId);
		
		IProcessDetail processDetail = AbstractProcessDetail.parse(flowContent,OAProcessDetail.class);
		List<INodeDetail> nodes = processDetail.getNodes();
		for (INodeDetail node : nodes) {
			if ((node instanceof OAAbstractManualNodeDetail)) {
				if (StringUtil.isNotNull(node.getHandlerIds())) {
					String[] handlerIds = node.getHandlerIds().split(";");
					StringBuffer handlerDescs = new StringBuffer();
					
					for (String handerId : handlerIds) {
						SysOrgElement sysOrgElement = sysOrgElementService.findByFdId(handerId);
						if (sysOrgElement == null) {
						} else {
							if (sysOrgElement.getFdOrgType() == Long.valueOf(SysOrgConstant.ORG_TYPE_PERSON)) {
								if (sysOrgElement.getFdIsAvailable() == Long.parseLong(CommonConstant.NOT_AVAILABLE_FLAG)) {
									handlerDescs.append(sysOrgElement.getFdName() + "(已禁用);");
								} else {
									handlerDescs.append(sysOrgElement.getFdName() + ";");
								}
							} else if (sysOrgElement.getFdOrgType() == Long.valueOf(SysOrgConstant.ORG_TYPE_POST)) {
								List<SysOrgElement> sysOrgElements = sysOrgElementService.findPostPerson(handerId);
								StringBuffer handlerDesc = new StringBuffer();
								for (SysOrgElement soe : sysOrgElements) {
									if (soe.getFdIsAvailable() == Long.parseLong((CommonConstant.NOT_AVAILABLE_FLAG))) {
										handlerDesc.append(soe.getFdName() + "(已禁用)/");
									} else {
										handlerDesc.append(soe.getFdName() + "/");
									}
								}
								
								if (StringUtils.isNotEmpty(handlerDesc.toString())) {
									handlerDescs.append(handlerDesc.toString().substring(0,handlerDesc.toString().length() - 1));
									handlerDescs.append(";");
								}
							} else if (sysOrgElement.getFdOrgType() == Long.valueOf(SysOrgConstant.ORG_TYPE_ROLE)) {
								SysOrgElement sysRoleElement = sysOrgElementService.findByFdId(handerId);
								List<SysOrgElement> sysOrgElements = null;
								try {
									sysOrgElements = formulaPluginService.parseSysOrgRole(sysRoleElement,baseElement);
								} catch (Exception e) {
									sysOrgElements = new ArrayList<SysOrgElement>();
								}
								
								StringBuffer handlerDesc = new StringBuffer();
								for (SysOrgElement soe : sysOrgElements) {
									if (soe.getFdIsAvailable() == Long.parseLong(CommonConstant.NOT_AVAILABLE_FLAG)) {
										handlerDesc.append(soe.getFdName() + "(已禁用)/");
									} else {
										handlerDesc.append(soe.getFdName() + "/");
									}
								}
								
								if (StringUtils.isNotEmpty(handlerDesc.toString())) {
									handlerDescs.append(handlerDesc.toString().substring(0,handlerDesc.toString().length() - 1));
									handlerDescs.append(";");
								}
							}
						}
						
						if (StringUtils.isNotEmpty(handlerDescs.toString())) {
							node.setHandlerDescs(handlerDescs.toString().substring(0,handlerDescs.toString().length() - 1));
						}
					}
				}
			}
		}
		
		return processDetail.toString();
	}
	
	/**
	 * 起草人提交单据
	 */
	@RequestMapping(value = "/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult submitDocument(@RequestParam String context) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		WebMessageResult result = new WebMessageResult();
		try {
			SbWFApprovalForm sbWFApprovalForm = mapper.readValue(context,SbWFApprovalForm.class);;
			SysNewsMain sysNewsMain = sbWFApprovalForm.getSysNewsMain();
			//信息初始化
			if (null == sysNewsMain.getDocCreatorId()) {
				sysNewsMain.setDocCreatorId(this.getCurrentUser().getFdId());				
				sysNewsMain.setDocAuthorId(this.getCurrentUser().getFdId());
				sysNewsMain.setDocCreateTime(new Timestamp(System.currentTimeMillis()));	
			}
			sysNewsMain.setDocAlterTime(new Timestamp(System.currentTimeMillis()));
			
			//设置提交身份
			if (StringUtils.isNotBlank(sbWFApprovalForm.getPostCode())) {
				Map<String,Object> processVal = new HashMap<String,Object>();
				processVal.put("postCode",sbWFApprovalForm.getPostCode());
				sysNewsMain.setProcessVarXml(ProcessLogicHelper.objectXmlEncoder(processVal));
			}
			
			//启动流程服务
			processFacade.sbWFStartProcessSrv(sbWFApprovalForm);
			result.setMessage("流程启动成功");
		} catch (Exception e) {
			log.error("提交失败:"+ e.getMessage());
			e.printStackTrace();
			result.setErrorMessage("提交失败:" + e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 查询我的申请单据
	 */
	@RequestMapping(value = "/findAll",method = RequestMethod.POST)
	@ResponseBody
	public Page<DocumentSubmmitedV> findAll(@RequestParam String context,@RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			DocumentSubmmitedV docSubmit = mapper.readValue(context,DocumentSubmmitedV.class);
			docSubmit.setCreatorUserNo(this.getCurrentUser().getFdLoginName());
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			Page<DocumentSubmmitedV> page = sysNewsMainService.searchDocumentSubmmitedV(docSubmit,pageVo.getPageable());
			
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 删除流程实例
	 * @Title: deleteInstance 
	 * @param fdIdStr
	 * @return
	 */
	@RequestMapping(value = "/deleteInstance")
	@ResponseBody
	public WebMessageResult deleteInstance(String fdIdStr) {
		WebMessageResult result = new WebMessageResult();
		try {
			sysNewsMainService.deleteProcessInstance(fdIdStr);
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 获取驳回节点信息
	 */
	@RequestMapping(value = "/findRejectNodes",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult findRejectNodes(@RequestParam String wfInstanceId,@RequestParam String nodeId) {
		WebMessageResult result = new WebMessageResult();
		
		try {
			result.setCode(WebMessageResult.SUCCESS);
			Map<String,Map<String,String>> nodes = processFacade.sbWFInquiryRejectNodeSrv(wfInstanceId,nodeId);
			List<Map<String,String>> data = new ArrayList<Map<String,String>>();
			Set<String> keys = nodes.keySet();
			Map<String,String> node = null;
			Map<String,String> item = null;
			 
			 for (String key : keys) {
				 node = nodes.get(key);
				 item = new HashMap<String,String>();
				 item.put("id",node.get("nodeId"));
				 item.put("name",node.get("nodeName"));
				 data.add(item);
			 }
			
			result.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			result.setCode(WebMessageResult.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
}

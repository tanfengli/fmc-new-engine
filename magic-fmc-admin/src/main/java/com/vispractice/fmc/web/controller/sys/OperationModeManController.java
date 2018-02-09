package com.vispractice.fmc.web.controller.sys;
   
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.base.utils.AutoArrayList;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfOperMain;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfOperations;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfOperMainService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfOperationsService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;
 
/**
 * 
 * 编  号：<br/>
 * 名  称：OperationModeManController<br/>
 * 描  述：<br/>操作方式管理
 * 完成日期：2016年12月16日 下午4:25:15<br/>
 * 编码作者：zhaoxiu<br/>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/sys/operationMode")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationModeManController extends AbstractController{
	@Autowired
	private ISysWfOperMainService iSysWfOperMainService;
	
	@Autowired
	private ISysWfOperationsService operationsService;
	
	/**
	 * 数据源页面入口
	 * */
	@RequestMapping("/list")
	public String operationIndex(Model model) { 
		return "sys/operationModeManagement/operation_list";
	} 
	
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysWfOperMain> findAll(@RequestParam String context,
			@RequestParam String pageVO){
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysWfOperMain operMain = mapper.readValue(context, SysWfOperMain.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			Page<SysWfOperMain> page = iSysWfOperMainService.findBySearch(operMain,pageVo.getPageable());
			
			List<SysWfOperMain> list = page.getContent();
			for (SysWfOperMain som : list) {
				List<SysWfOperations> operationsList = operationsService.findByFdOperatorId(som.getFdId());
				List<SysWfOperations>[] operations = getSplitOperationsFromMain(operationsList); 
				som.setOperationsList(operations[0]);
				som.setOperationsListTwo(operations[1]); 
			}
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked" })
	private List<SysWfOperations>[] getSplitOperationsFromMain(
			List sysFlowOperationsFormList) {
		List[] rtnArray = new List[2];
		// draft
		rtnArray[0] = new AutoArrayList(SysWfOperations.class);
		// processor
		rtnArray[1] = new AutoArrayList(SysWfOperations.class);
		for (int i = 0; i < sysFlowOperationsFormList.size(); i++) {
			SysWfOperations sysFlowOperationsForm = (SysWfOperations) sysFlowOperationsFormList
					.get(i);
			if (null!=sysFlowOperationsForm.getFdOperType()) {
				if (sysFlowOperationsForm.getFdOperType().toString().startsWith("1")) {
					rtnArray[1].add(sysFlowOperationsForm);
				} else if (sysFlowOperationsForm.getFdOperType().toString().startsWith("2")) {
					rtnArray[0].add(sysFlowOperationsForm);
				}
			}
		}
		Collections.sort(rtnArray[0]);
		Collections.sort(rtnArray[1]);
		return rtnArray;
	}
	
	/**
	 * 新增或修改
	 * */ 
	@RequestMapping(value = "/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult addSysWfUsage(String context) {//SysWfOperMain operMain
		WebMessageResult result = new WebMessageResult();
		try { 
			ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
			SysWfOperMain operMain=mapper.readValue(context,SysWfOperMain.class);
			
			//验证节点类型只能存在一个‘是’默认
			if(null!=operMain){
				if(null!=operMain.getFdNodeType()&&null!=operMain.getFdIsDefault()&&"1".equals(operMain.getFdIsDefault())){
					String fdId = operMain.getFdId();
					if(null==fdId||"".equals(fdId)){
						fdId="0";
					}
					int opeMain = iSysWfOperMainService.findByFdNodeTypeAndFdIsDefault(operMain.getFdNodeType(), operMain.getFdIsDefault(),fdId);
			    	if(opeMain>=1){
			    		result.setErrorMessage(resourceUtil.getLocaleMessage("defValueExist.operType"));
						return result; 
			    	}
				}
			}
			
			//主表
			SysWfOperMain swom =  iSysWfOperMainService.save(operMain); 
			
			List<SysWfOperations> operations = setOrderValue(operMain.getOperationsList());
			List<SysWfOperations> operationsTwo = setOrderValue(operMain.getOperationsListTwo());  
			
			//判断新增修改
			if(null!=swom.getFdId()){
				//删除关联的从表信息，重新添加
				operationsService.deleteByFdOperatorId(swom.getFdId());
			}
			
			for (SysWfOperations sysWfOperations : operations) {
				sysWfOperations.setFdOperatorId(swom.getFdId());
				//从表
				operationsService.save(sysWfOperations);
			}
			for (SysWfOperations sysWfOperations : operationsTwo) {
				sysWfOperations.setFdOperatorId(swom.getFdId());
				//从表
				operationsService.save(sysWfOperations);
			}
			
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private List<SysWfOperations> setOrderValue(List<SysWfOperations> operationsDraftList) {
		if (operationsDraftList != null) {
			List<SysWfOperations> rtnList = new ArrayList();
			for (int i = 0; i < operationsDraftList.size(); i++) {
				SysWfOperations sysFlowOperationsForm = (SysWfOperations) operationsDraftList
						.get(i);
				sysFlowOperationsForm.setFdOrder(new Long(i));
				rtnList.add(sysFlowOperationsForm);
			}
			return rtnList;
		}
		return null;
	}
	
	@RequestMapping("/deleteOne")
	@ResponseBody
	public WebMessageResult deleteOne(String fdId) throws Exception { 
		WebMessageResult result = new WebMessageResult();
		try {
			iSysWfOperMainService.deleteByFdId(fdId);
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		return result;
	}
	
	
	/**
	 * 查询所有的基本操作列表
	 */
	@RequestMapping("/findList")
	@ResponseBody
	public List<SysWfOperMain> findList() {
		
		List<SysWfOperMain> list = iSysWfOperMainService.findList();
		
		for (SysWfOperMain som : list) {
			List<SysWfOperations> operationsList = operationsService.findByFdOperatorId(som.getFdId());
			List<SysWfOperations>[] operations = getSplitOperationsFromMain(operationsList); 
			som.setOperationsList(operations[0]);
			som.setOperationsListTwo(operations[1]); 
		}
		return list;
	 
	}
}

package com.vispractice.fmc.web.controller.flowchart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfCommonTemplateService;
import com.vispractice.fmc.business.service.sys.workflow.impl.SysWfTemplateServiceImpl;
import com.vispractice.fmc.web.result.WebMessageResult;

@Slf4j
@Controller
@RequestMapping("/flowchart/setting")
public class FlowChartController {
	
	@Autowired
	private SysWfTemplateServiceImpl sysWfTemplateService;
	
	
	@Autowired
	private ISysWfCommonTemplateService commonTemplateService;
	
	@RequestMapping("/flowDesigner")
	public String flowDesigner() {
		return "flowchart/setting/flowDesigner";
	}
	
	@RequestMapping("/getFlowContent")
	@ResponseBody
	public String getFlowContent(String fdId) {
		return sysWfTemplateService.getFlowContent(fdId);
	}
	
	/**
	 * 根据模板获取流程图
	 */
	@RequestMapping("/getContentByTemplateId")
	@ResponseBody
	public WebMessageResult getContentByTemplateId(String templateId) {
		WebMessageResult result = new WebMessageResult();
		try {
			result.setData(sysWfTemplateService.getContentByTemplateId(templateId));
			result.setCode(WebMessageResult.SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setCode(WebMessageResult.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 根据模板获取流程图和表格
	 */
	@RequestMapping("/getContentAndNodeByTemplateId")
	@ResponseBody
	public WebMessageResult getContentAndNodeByTemplateId(String templateId) {
		WebMessageResult result = new WebMessageResult();
		try {
			result.setCode(WebMessageResult.SUCCESS);
			result.setData(sysWfTemplateService.getContentAndNodeByTemplateId(templateId));
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setCode(WebMessageResult.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 根据实例获取流程图
	 */
	@RequestMapping("/getContentAndTranByInstanceId")
	@ResponseBody
	public WebMessageResult getContentByInstanceId(String instanceId) {
		WebMessageResult result = new WebMessageResult();
		try {
			result.setCode(WebMessageResult.SUCCESS);
			result.setData(sysWfTemplateService.getContentByInstanceId(instanceId));
			
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setCode(WebMessageResult.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 获取通用流程图
	 * @Title: getCommonContent 
	 * @param fdId
	 * @return
	 */
	@RequestMapping("/getCommonContentAndTran")
	@ResponseBody
	public WebMessageResult getCommonContent(String fdId) {
		WebMessageResult result = new WebMessageResult();
		try {
			result.setCode(WebMessageResult.SUCCESS);
			result.setData(commonTemplateService.getContent(fdId));
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setCode(WebMessageResult.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	 
	/**
	 * 实现流程:TODO(审批节点初始)<br/>
	 * 1.XXX<br/>
	 * @Title: approvalIndex 
	 * @return
	 */
	@RequestMapping("/approvalIndex")
	public String approvalIndex() {
		return "flowchart/approvalNode/approval_list";
	}
	
	/**
	 * 实现流程:TODO(公式定义器初始)<br/>
	 * 1.XXX<br/>
	 * @Title: formulaIndex 
	 * @return
	 */
	@RequestMapping("/formulaIndex")
	public String formulaIndex() {
		return "flowchart/formulaDefinition/formulaDefinition_list";
	}
	
	/**
	 * 实现流程:TODO(条件分支节点初始)<br/>
	 * 1.XXX<br/>
	 * @Title: conditionalIndex 
	 * @return
	 */
	@RequestMapping("/conditionalIndex")
	public String conditionalIndex() {
		return "flowchart/conditionalBranchNode/conditional_list";
	}
	 
}

package com.vispractice.fmc.business.service.sys.workflow;

import java.util.Map;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfTemplate;

public interface ISysWfTemplateService {
	/**
	 * 根据标识获取模板内容
	 * @param fdId
	 * @return
	 */
	public String getFlowContent(String fdId);

	/**
	 * 根据流程模板模型标识获取模板
	 * @param modelId
	 * @return
	 * @throws Exception 
	 */
	public SysWfTemplate findTemplateByModelIdOrNewOne(String modelId) throws Exception;
	
	public SysWfTemplate findTemplateByFdId(String fdId);

	/**
	 * 
	 * 根据模板ID获取流程图
	 * @Title: getContentByTemplateId 
	 * @param templateId
	 * @return 流程图XML
	 */
	public String getContentByTemplateId(String templateId);

	/**
	 * 
	 * 根据流程实例ID获取流程图和节点列表
	 * @Title: getContentByInstanceId 
	 * @param instanceId
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getContentByInstanceId(String instanceId)
			throws Exception;

	/**
	 * 
	 * 根据模板ID获取流程图和节点列表
	 * @Title: getContentAndNodeByTemplateId 
	 * @param templateId
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public Map getContentAndNodeByTemplateId(String templateId) throws Exception;
}

package com.vispractice.fmc.business.entity.sys.workflow.beans.parameter;

import lombok.Data;

import com.vispractice.fmc.business.base.utils.BeanReaderWriter;

@Data
public class ProcessParameter {
	public static String PROCESS_PARAMETER_NODE = "<process-param/>";

	private String docSubject;

	private String fdModelName;

	private String fdModelId;

	private String fdKey;
	
	/*
	 * 驳回时返回本节点的ID
	 */
	private String toRefuseThisNodeId;

	/*
	 * 驳回时返回本节点的处理人ID
	 */
	private String toRefuseThisHandlerId;

	private String toRefuseThisHandlerName;
	
//	private String toRefuseFlag;
	
	private String processType;
	
	/*
	 * 返回节点原先处理人配置，用于比对是否被修改
	 */
	private String toRefuseThisHandlerConfig;

	// 工作流类型OA或HR
	private String fdWfType;

	// 起草人身份ID,如果个人ID或岗位ID，用于通用岗位转换
	private String fdDrafterId;

	// 起草节点ModelId，用于起草人撤回时查找历史节点ID
	private String fdDrafteNodeFdId;

	private String fdTemplateId;
	
	private static BeanReaderWriter beanReaderWriter = new BeanReaderWriter(
			ProcessParameter.class);

	public String toString() {
		return beanReaderWriter.write(this);
	}

	public synchronized static ProcessParameter parse(String xml) throws Exception {
		return beanReaderWriter.parse(xml, PROCESS_PARAMETER_NODE);
	}

}

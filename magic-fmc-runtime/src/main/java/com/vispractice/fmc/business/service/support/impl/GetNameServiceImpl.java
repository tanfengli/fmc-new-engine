package com.vispractice.fmc.business.service.support.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.utils.ProcessLogicHelper;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfProcess;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfWorkitem;
import com.vispractice.fmc.business.entity.sys.workflow.beans.parameter.NodeParameter;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfNodeRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfProcessRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfWorkitemRepository;
import com.vispractice.fmc.business.service.support.GetNameService;

/**
 * 名  称：ShowNameServiceImpl
 * 描  述：获取页面当前节点和当前处理人
 * 完成日期：2017年6月21日 上午11:26:54
 * 编码作者："LaiJiashen"
 */
@Service
public class GetNameServiceImpl implements GetNameService {
	/**
	 * 日志信息服务
	 */
	private static final Logger logger = LoggerFactory.getLogger(GetNameServiceImpl.class); 
	
	/**
	 * 流程节点持久化服务
	 */
	@Autowired
	private SysWfNodeRepository wfNodeRepo;
	
	/**
	 * 流程工作项持久化服务
	 */
	@Autowired
	private SysWfWorkitemRepository wfWorkitemRepo;
	
	/**
	 * 组织架构持久化服务
	 */
	@Autowired
	private SysOrgElementRepository orgElementRepo;
	
	/**
	 * 流程实例持久化服务
	 */
	@Autowired
	private SysWfProcessRepository wfProcessRepo;
	
	/**
	 * 获取当前节点名称
	 */
	@Override
	public String getCurrentNodesName(String processId) throws Exception {
		StringBuffer sb = new StringBuffer();
		int n = 0;
		List<SysWfNode> nodes = wfNodeRepo.findByFdProcessId(processId);
		
		for (int i = 0; i < nodes.size(); i++) {
			SysWfNode node = nodes.get(i);
			if (n > 0) {
				sb.append(",");
			}
			sb.append(node.getFdFactNodeName());
			n++;
		}
		
		if (0 == n) {
			// 结束节点的名称描述直接修改为结束
			sb.append("结束");
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取当前处理人名称
	 */
	@Override
	public String getHandlersName(String processId) throws Exception {
		StringBuffer sb = new StringBuffer();
		int n = 0;
		List<SysWfNode> nodes = wfNodeRepo.findByFdProcessId(processId);
		
		try {
			for (int i = 0; i < nodes.size(); i++) {
				SysWfNode node = nodes.get(i);
				List<SysWfWorkitem> sysWfWorkItems = wfWorkitemRepo.findByFdNodeId(node.getFdId());
				if (OAConstant.DRAFT_NODE_PARENT.equals(node.getFdNodeType())) {
					//取到当前处理人
					SysOrgElement sysOrgElement = orgElementRepo.findOne(sysWfWorkItems.get(0).getFdExpectedId());
					
					if (sysOrgElement != null) {
						sb.append(sysOrgElement.getFdName());
					} else {
						sb.append("null");
					}
					
					return sb.toString();
				}
				
				if (OAConstant.AUDIT_NODE_PARENT.equals(node.getFdNodeType())
					|| OAConstant.SIGN_NODE_PARENT.equals(node.getFdNodeType())) {
					if (n > 0) {
						sb.append(";");
					}
					
					n++;
					SysWfProcess sysWfProcess = wfProcessRepo.findOne(processId);
							
					if (isSerialNode(sysWfProcess,node)) {
						sb.append(getHandlersName(sysWfProcess,node,sysWfWorkItems));
						continue;
					}
					
					for (int j = 0; j < sysWfWorkItems.size(); j++) {
						if (j > 0) {
							sb.append(",");
						}
						
						//取到当前处理人
						SysOrgElement elem = orgElementRepo.findOne(sysWfWorkItems.get(j).getFdExpectedId());
						sb.append(elem.getFdName());
					}
				}
			}
		} catch (Exception e) {
			// 增加出错流程相关信息
			String info = "流程列表视图获取<处理人>出错：[fdProcessId=%s]";
			logger.info(String.format(info,processId,e));
			e.printStackTrace();
		}
		
		if (sb.length() == 0) {
			sb.append("无");
		}
		
		return sb.toString();
	}

	@SuppressWarnings({"unchecked","rawtypes"})
	private String getHandlersName(SysWfProcess wfProcess,SysWfNode node,List<SysWfWorkitem> sysWorkItems) throws Exception {
		Map nodeAttr = ProcessLogicHelper.getNodeAttrByNodeId(wfProcess.getFdDetail(),node.getFdFactNodeId());
		String[] handlerId = nodeAttr.get("handlerIds").toString().split(";");
		String[] handlerName = nodeAttr.get("handlerNames").toString().split(";"); 
		// 假如修改为节点初始化就确定审批人,那就不用这样获取公式定义的审批人
		if (OAConstant.HANDLER_SELECT_TYPE_FORMULA.equals(nodeAttr.get("handlerSelectType"))) {
			
			
		}
		
		NodeParameter nodeParemter = NodeParameter.parse(node.getFdParameter());
		// 新增按创建时间排序，以防止依赖数据库自然排序方式的实效

		Collections.sort(sysWorkItems,new BeanComparator("fdStartDate"));
		int currIndex = sysWorkItems.size() - 1;
		
		/***修复原先的一个缺陷****/
		if(currIndex < 0){
			return null;
		}
		
		if(sysWorkItems.get(currIndex).getFdExpectedId() == null){
			return null;
		}
		
		SysOrgElement curr = orgElementRepo.findOne(sysWorkItems.get(currIndex).getFdExpectedId());

		// 如果是发起了沟通操作,当前处理人显示为 第一个被沟通处理人名称 + “等”
		if (sysWorkItems.size() >= 2
			&& OAConstant.HANDLER_OPERATION_TYPE_COMMUNICATE == sysWorkItems.get(0).getFdActionId()) {

			curr = orgElementRepo.findOne(sysWorkItems.get(1).getFdExpectedId());
			String msg = curr.getFdName();
			if (sysWorkItems.size() > 2) {
				msg += " " + "等";
			}
			
			return msg;
		}
		
		if (curr == null) {
			return null;
		}
		
		String currId = curr.getFdId();
		int index = getHandlerIndex(handlerId,currId);
		if (index == -1 || index != nodeParemter.getCurrHandlerOffset()) {
			index = nodeParemter.getCurrHandlerOffset();
			// 转办、授权情况下，在流程图中无法找到相关处理人
			if (index < handlerName.length) { 
				handlerName[index] = curr.getFdName();
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = index; i < handlerId.length; i++) {
			if (i > index) {
				sb.append(",");
			}
			if (i == index) {
				sb.append(handlerName[i]);
			} else {
				sb.append(handlerName[i]);
			}
		}
		
		return sb.toString();
	}

	private int getHandlerIndex(String[] ids,String id) {
		for (int i = 0; i < ids.length; i++) {
			if (ids[i].equals(id)) {
				return i;
			}
		}
		
		return -1;
	}

	@SuppressWarnings("rawtypes")
	private boolean isSerialNode(SysWfProcess wfProcess,SysWfNode node) {
		Map nodeAttr = ProcessLogicHelper.getNodeAttrByNodeId(wfProcess.getFdDetail(),node.getFdFactNodeId());
		
		return nodeAttr.get("processType").equals(String.valueOf(OAConstant.PROCESSTYPE_SERIAL));
	}
}

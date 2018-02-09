package com.vispractice.fmc.business.service.sys.workflow.impl;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.base.utils.ProcessLogicHelper;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfProcess;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfToken;
import com.vispractice.fmc.business.entity.sys.workflow.beans.context.ProcessContext;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.NodeDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.ILineDetail;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.INodeDetail;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.IProcessDetail;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfTokenRepository;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfTokenService;

@Slf4j
@Service
@Transactional
public class SysWfTokenServiceImpl implements ISysWfTokenService {
	
	private final String ACTIVE = "active";
	private final String INACTIVE = "inactive";
	private final String ENDED = "ended";
	@SuppressWarnings("unused")
	private final String CREATED = "created";
	@SuppressWarnings("unused")
	private final String ASYNC = "async";
	private final String TRANSFER = "transfer";
	
	@Autowired
	private SysWfTokenRepository sysWfTokenRepository;
	
	public void saveToken(SysWfToken token) {
		sysWfTokenRepository.save(token);
	}
	
	public SysWfToken getCurrToken(String pId, String nId) {
		if (nId != null) {
			return sysWfTokenRepository.findWfTokenByNodeIdAndInstanceId(nId, pId);
		}
		return null;
	}

	/**
	 * 初始化流程令牌信息
	 * @throws WorkflowException
	 */
	public void initRootToken(ProcessContext context,String nodeFdId) {
		SysWfProcess sysWfProcess = context.getSysWfProcess();
		SysWfToken sysWfToken = new SysWfToken();
		sysWfToken.setFdKey(sysWfProcess.getFdKey());
		sysWfToken.setFdStatus("active");
		sysWfToken.setFdProcessId(sysWfProcess.getFdId());
		sysWfToken.setFdStatus(SysWfToken.ACTIVE);
		Map<String,String> initialNode = ProcessLogicHelper.getInitialNode(sysWfProcess.getFdDescriptor());
		sysWfToken.setFdNodeInstanceId(nodeFdId);
		sysWfToken.setFdFromNodeId(initialNode.get("fdFromNodeId"));
		sysWfToken.setFdKey(sysWfProcess.getFdKey());
		sysWfToken.setFdNodeFactId(initialNode.get("fdNodeFacatId"));
		try {
			sysWfTokenRepository.save(sysWfToken);
		} catch (Exception e) {
			throw new WorkflowException("生成令牌错误." + e.getMessage());
		}
	}
	
	public void transferTo(SysWfToken token, NodeDescriptor nextNodeDesc,IProcessDetail detail ) {
		if (token == null) {
			if (log.isDebugEnabled()) {
				log.debug("token is null");
			}
			return;
		}
		//如果token是失效的需产生子token
		if (token.getFdStatus().equals(INACTIVE)) {
			SysWfToken subToken = new SysWfToken();
//			saveToken(token);
			subToken.setFdKey(nextNodeDesc.getId());
				
			String name = null;
			INodeDetail nodeDetail =detail.getNodeById(nextNodeDesc.getId());
			for (ILineDetail line : nodeDetail.loadInLines()) {
				if (line.getStartNodeId().equals(token.getFdNodeFactId())) {
					name = line.getName();
				}
			}
			if (name == null) {
				name = nextNodeDesc.getId() + "." + nextNodeDesc.getName();
			}
			subToken.setFdName(name);
			subToken.setFdFromNodeId(token.getFdNodeFactId());
			subToken.setFdTargetNodeId(nextNodeDesc.getId());
			subToken.setFdStatus(SysWfToken.TRANSFER);
			subToken.setFdProcessId(token.getFdProcessId());
			subToken.setFdParentId(token.getFdId());
			
			saveToken(subToken);
//			subToken
//			BaseTreeConstant.HIERARCHY_ID_SPLIT
//			SysWfToken childToken = createToken(context, context
//					.getSysWfProcess(), token, nextNodeDesc);
//			// 审批记录
//			SysWfAuditNote note = new SysWfAuditNote();
//			note.setFdCreateDate(new Date());
//			note.setFdOrder(new Long(new Date().getTime()));
//			context.getSysWfProcess().getFdWfAuditNotes().add(note);
//			note.setFdWfProcess(context.getSysWfProcess());
//			note.setFdFactNodeName(childToken.getFdName());
//			note.setFdTokenId(childToken.getFdId());
//			note.setFdParentTokenId(childToken.getFdParentId());
//			note.setFdActionId(Long
//					.valueOf(OAConstant.SYSTEM_OPERATION_TYPE_SPLIT));
//			note
//					.setFdActionName("{0}-{1}%%sysWfProcess.processor.identity.system;"
//							+ "sysWfOperations.fdOperType.system.branchstart");
//			note.setFdAuditNote(ResourceUtil.getString(
//					"sysWfNode.opInfo.split.auditInfo", "sys-workflow", null,
//					childToken.getFdName()));
//			note
//					.setFdActionInfo("sysWfOperations.fdOperType.system.branchsplit");
//
//			childToken.transferTo(nextNodeDesc);
		} else {
			if (token.getFdNodeFactId() == null && token.getFdParentId()!=null) {
				SysWfToken parentToken = this.getParentToken(token);						
				token.setFdFromNodeId(parentToken.getFdNodeFactId());
			} else {
				token.setFdFromNodeId(token.getFdNodeFactId());
			}
			token.setFdTargetNodeId(nextNodeDesc.getId());
			token.setFdNodeFactId(null);
			token.setFdNodeInstanceId(null);
//			token.setNode(null);
			token.setFdStatus(TRANSFER);
			
//			saveToken(token);
		}
		if (log.isDebugEnabled()) {
//			log.debug("before transfer token size "
//					+ context.getSysWfProcess().getFdTokens().size()
//					+ " and transfer to " + nextNodeDesc.getId());
		}
	}
	
	public void moveToParent(SysWfToken token, NodeDescriptor nodeDesc, SysWfNode node) {
		if (node == null)
			return;
		if (token == null ||  token.getFdParentId() == null || token.getFdStatus() != ENDED) {
			log.debug("can not move to parent, token is null.");
		}else {
			SysWfToken parentToken = getParentToken(token);
			
			if(isAllChildrenEnded(token)) {
				
				token = parentToken;
				token.setFdStatus(ACTIVE);
			
				clearChildren(token);
				
				takeNode(token, node);
				token.setFdFromNodeId(node.getFdFactNodeId());
				token.setFdTargetNodeId(node.getFdTargetId());
				saveToken(token);
				if (log.isDebugEnabled()) {
					log.debug("move to parent");
				}
			}

		}
	}
	
	public void takeNode(SysWfToken token, NodeDescriptor nodeDescriptor) {
		token.setFdNodeFactId(nodeDescriptor.getId());
//		token.setNodeDesc(nodeDescriptor);
		token.setFdTargetNodeId(null);
	}
	
	public void takeNode(SysWfToken token, SysWfNode node) {
//		token.setNode(node);
		token.setFdNodeInstanceId(node.getFdId());
		token.setFdStatus(ACTIVE);
	}
	
	@SuppressWarnings("rawtypes")
	public SysWfToken takeNode(NodeDescriptor nodeDesc,SysWfNode node,Map parameter) {
		SysWfToken token = findNeedTakeToken(node, (String) parameter.get("fromNodeId"));
		if (log.isDebugEnabled()) {
			log.debug("need take " + nodeDesc.getId() + ", nodeId = "
					+ node.getFdFactNodeId() + ", fromNodeId = "
					+ (String) parameter.get("fromNodeId"));
		}
		if (token != null && StringUtil.isNotNull(token.getFdId())) {
			if (log.isDebugEnabled()) {
				log.debug("take node " + nodeDesc.getId());
			}
			takeNode(token, nodeDesc);
			takeNode(token, node);
//			currentToken = token;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("no take token, token is null.");
			}
		}
		return token;
	}
	
	private SysWfToken findNeedTakeToken(SysWfNode node, String fromNodeId) {
		
		List<SysWfToken> tokenList = sysWfTokenRepository.findTokenListByFdProcessIdAndFdFromNodeId(node.getFdProcessId(), fromNodeId);
		
		if (log.isDebugEnabled()) {
			log.debug("current token size " + tokenList.size());
		}
		for (SysWfToken token : tokenList) {
			if (log.isDebugEnabled()) {
				log.debug(String.format("token name %s, target %s, from %s",
						token.getFdName(), token.getFdTargetNodeId(), token
								.getFdFromNodeId()));
			}
			if (node.getFdFactNodeId().equals(token.getFdTargetNodeId())) {
				if (fromNodeId != null) {
					if (fromNodeId.equals(token.getFdFromNodeId())) {
						return token;
					}
				} else {
					return token;
				}
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("no find need take token !");
		}
		return null;
		
	}
	
	private SysWfToken getParentToken(SysWfToken token) {
		SysWfToken parentToken = sysWfTokenRepository.findOne(token.getFdParentId());
		return parentToken;
	}
	
	/**
	 * 检查是否所有子节点都已结束
	 * @param token
	 * @return
	 */
	private boolean isAllChildrenEnded(SysWfToken token) {
		List<SysWfToken> tokenList = sysWfTokenRepository.findTokenListByFdParentId(token.getFdParentId());
		
		for (SysWfToken child : tokenList) {
			if (child.getFdStatus() != ENDED&&child.getFdId().equals(token.getFdId())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 清除
	 * @param token
	 * @return
	 */
	private void clearChildren(SysWfToken token) {
		
		sysWfTokenRepository.deleteAllByFdParentId(token.getFdId());
	}
		
	public SysWfToken findTokenById(String tokenId){
		
		return sysWfTokenRepository.findOne(tokenId);
	}

	@Override
	public List<SysWfToken> findTokenByParentId(String parentId) {
		return sysWfTokenRepository.findTokenListByFdParentId(parentId);
	}

	@Override
	public SysWfToken getJoinToken(String pId, String nId, String fId) {
	
		return sysWfTokenRepository.findWfTokenByNodeIdAndInstanceIdAndFromNodeId(nId, pId, fId);
	}

	@Override
	public List<SysWfToken> findTokenByFdProcessId(String fdProcessId) {
		return sysWfTokenRepository.findTokenListByFdProcessId(fdProcessId);
	}
}

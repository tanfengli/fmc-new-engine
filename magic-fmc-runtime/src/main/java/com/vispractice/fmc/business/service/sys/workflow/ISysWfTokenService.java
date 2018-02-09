package com.vispractice.fmc.business.service.sys.workflow;

import java.util.List;
import java.util.Map;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfToken;
import com.vispractice.fmc.business.entity.sys.workflow.beans.context.ProcessContext;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.NodeDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.IProcessDetail;

public interface ISysWfTokenService {
	public SysWfToken getCurrToken(String pId,String nId);
	
	public void initRootToken(ProcessContext context,String nodeFdId);
	
	public void transferTo(SysWfToken token,NodeDescriptor nextNodeDesc,IProcessDetail detail);
	
	public void moveToParent(SysWfToken token,NodeDescriptor nodeDesc,SysWfNode node);
	
	@SuppressWarnings("rawtypes")
	public SysWfToken takeNode(NodeDescriptor nodeDesc,SysWfNode node,Map parameter);	
	
	public void saveToken(SysWfToken token);
	
	public SysWfToken findTokenById(String tokenId);
	
	public List<SysWfToken> findTokenByParentId(String parentId);
	
	public SysWfToken getJoinToken(String pId,String nId,String fId);
	
	public List<SysWfToken> findTokenByFdProcessId(String fdProcessId);
}
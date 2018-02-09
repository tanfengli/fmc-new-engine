package com.vispractice.fmc.business.service.sys.workflow.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.base.utils.ProcessLogicHelper;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsMainRepository;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfCommonTemplate;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfEvent;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfHistoryNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfProcess;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfTemplate;
import com.vispractice.fmc.business.entity.sys.workflow.beans.context.ProcessContext;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfCommonTemplateRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfEventRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfHistoryNodeRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfNodeRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfTemplateRepository;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.service.sys.workflow.IClusterService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfEventService;

/**
 * @ClassName SysWfEventServiceImpl
 * @author sky
 * @Date May 11, 2017
 * @version 1.0.0
 */
@Service
@Transactional
@Slf4j
public class SysWfEventServiceImpl implements ISysWfEventService {
	
	/**
	 * 集群配置
	 */
	@Value("${cluster.enable}")
	private boolean clusterEnable;

	/**
	 * 保持引擎事件数据库层服务
	 */
	@Autowired
	private SysWfEventRepository sysWfEventRepository;
	
	/**
	 * 集群服务
	 */
	@Autowired(required=false)
	private IClusterService clusterService;
	
	/**
	 * 组织结构服务
	 */
	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;
	
	/**
	 * 流程实例基本信息服务
	 */
	@Autowired
	private SysNewsMainRepository sysNewsMainRepository;

	/**
	 * 流程节点数据库层服务
	 */
	@Autowired
	private SysWfNodeRepository sysWfNodeRepository;

	/**
	 * 历史节点数据库层服务
	 */
	@Autowired
	private SysWfHistoryNodeRepository historyNodeRepo;

	/**
	 * 保持流程模板服务
	 */
	@Autowired
	private SysWfTemplateRepository sysWfTemplateRepository;

	/**
	 * 保持新闻模板服务
	 */
	@Autowired
	private SysWfCommonTemplateRepository sysWfCommonTemplateRepository;

	/**
	 * 组织架构服务
	 */
	@Autowired
	private ISysOrgElementService sysOrgElementService;
	
	
	/**
	 * 添加引擎事件
	 */
	@Override
	public void addEvent(ProcessContext context,String fdNodeId,String fdUserId,Long fdPriority) throws WorkflowException {
		final SysWfEvent sysWfEvent = new SysWfEvent();
		
		sysWfEvent.setFdProcessId(context.getSysWfProcess().getFdId());
		sysWfEvent.setFdUserId(fdUserId);
		sysWfEvent.setFdNodeId(fdNodeId);
		sysWfEvent.setFdAction(SysWfEvent.ACTION_DOACTION);
		try {
			sysWfEvent.setFdParameter(getEventParam(context,fdNodeId,fdUserId));
			log.debug("sys_wf_event-fd_parameter:" + sysWfEvent.getFdParameter());
		} catch (Exception e) {
			e.printStackTrace();
			throw new WorkflowException("流程引擎事件fdParameter字段生成失败.",e);
		}
		sysWfEvent.setFdPriority(fdPriority);
		sysWfEvent.setDocCreateTime(new Date());

		try {
			sysWfEventRepository.save(sysWfEvent);
			if (clusterEnable) {
				TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
					@Override
					@Async
					public void afterCommit() {
						log.info("send cluster message after transaction commit...");
						try {
							clusterService.submit(sysWfEvent);
						} catch (Exception e) {
							log.error("调用流程引擎消息失败." + e.getMessage());
						}
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new WorkflowException("生成流程引擎处理事件失败.",e);
		}
	}

	@Override
	public void delete(SysWfEvent sysWfEvent) {
		sysWfEventRepository.delete(sysWfEvent);
	}

	/**
	 * 获取流程时间参数
	 * @param processId 流程实例序号
	 * @return 时间参数字符串
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private String getEventParam(ProcessContext context,String nodeId,String userId) throws WorkflowException {
		String eventParam;
		SysWfProcess sysWfProcess = context.getSysWfProcess();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		//流程设置
		paramMap.put("fdFlowContent",XMLEscape(sysWfProcess.getFdDetail())); 
		paramMap.put("fdHandleNodeId",context.getFdHandleNodeId());
		paramMap.put("fdModelName",sysWfProcess.getFdModelName());
		paramMap.put("fdModelId",sysWfProcess.getFdModelId());

		//是否运行流程标记
		paramMap.put("canStartProcess",context.getCanStartProcess());
		//是否允许设置审批意见类型
		paramMap.put("fdIsAllowSetupApprovalType","false");
		paramMap.put("executeDoDraftWorkFlow","false");
		//是否是流程的有效操作人
		paramMap.put("fdIsAvailableUser","true");
		paramMap.put("systemAutoRun",false);

		paramMap.put("fdVersion","");
		paramMap.put("fdAuditNodeFormList",new ArrayList());
		paramMap.put("fdKey","");
		paramMap.put("fdCurrentUserIds","");
		paramMap.put("fdHandleWorkItemId",context.getFdHandleWorkItemId());
		paramMap.put("page",0);

		paramMap.put("fdWorkItemParameter",context.getFdWorkItemParameter());
		paramMap.put("fdProcessorInfoXML",context.getFdProcessorInfoXML());

		//当前流程需要处理的节点列表
		List<SysWfNode> nodeList = sysWfNodeRepository.findByFdProcessId(sysWfProcess.getFdId());

		Map<String,Object> parameter = new HashMap<String,Object>();
		//起草人XML
		String drafterInfo = getDraftInfo(sysWfProcess,userId,nodeList,parameter);
		//特权人XML
		String authorInfo = getAuthorityInfo(sysWfProcess,nodeList,parameter);

		paramMap.put("fdAuthorityInfoXML",XMLEscape(authorInfo));
		paramMap.put("fdNodeInfoXML","");
		paramMap.put("fdHandlerInfo","");
		paramMap.put("fdDrafterInfoXML",XMLEscape(drafterInfo));
		paramMap.put("fdTranProcessXML",getProcessStatus(sysWfProcess,nodeList));
		paramMap.put("fdRelationWorkitemXML","");

		//通知方式
		paramMap.put("fdSystemNotifyType","false");

		String[] templateMes = loadTemplateModelInfo(sysWfProcess);
		paramMap.put("fdTemplateModelName",templateMes[0]);
		paramMap.put("fdTemplateKey",templateMes[1]);

		//页面加载前设了值,表单提交后为空
		SysOrgElement sysOrgElement = sysOrgElementRepository.getOne(sysWfProcess.getFdCreatorId());
		paramMap.put("draftIdentityID",sysWfProcess.getFdCreatorId());
		paramMap.put("draftIdentityName",sysOrgElement.getFdName());

		String[] currentUserRoles = getCurrentUserRoles(userId);
		//当前登录用户及其岗位 206301;6011,姚雪;行政专员
		paramMap.put("fdHandlerRoleInfoIds",currentUserRoles[0]);
		paramMap.put("fdHandlerRoleInfoNames",currentUserRoles[1]);

		//特权人 特权人XML设置时
		paramMap.put("privilegerFlag", "1");
		//处理人XML设置时
		paramMap.put("canModifyMainDoc",false);
		//工作项相关
		paramMap.put("fdNodeAdditionalInfo",new HashMap());

		try {
			eventParam = ProcessLogicHelper.objectXmlEncoder(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WorkflowException("获取流程事件参数失败" + e.getMessage());
		}
		
		return eventParam;
	}
	
	/**
	 * 取得当前用户的信息（包括岗位）
	 * @return String[2] String[0]为Id字符串,String[1]为Name字符串
	 */
	@SuppressWarnings({"rawtypes"})
	private String[] getCurrentUserRoles(String fdUserId) {
		SysOrgElement currentUser = sysOrgElementRepository.findOne(fdUserId);
		String[] rtnValue = new String[2];
		rtnValue[0] = currentUser.getFdId();
		rtnValue[1] = currentUser.getFdName();

		//获取岗位
		List currentUserPost = sysOrgElementService.findPosts(fdUserId);
		if (currentUserPost != null && currentUserPost.size() > 0) {
			rtnValue[0] += ";";
			rtnValue[1] += ";";
			for (int i = 0; i < currentUserPost.size(); i++) {
				SysOrgElement postElement = (SysOrgElement) currentUserPost.get(i);
				if (i == currentUserPost.size() - 1) {
					rtnValue[0] += postElement.getFdId();
					rtnValue[1] += postElement.getFdName();
				} else {
					rtnValue[0] += postElement.getFdId() + ";";
					rtnValue[1] += postElement.getFdName() + ";";
				}
			}
		}
		
		// 去除结尾的
		if (rtnValue[0].endsWith(";")) {
			rtnValue[0].substring(0, rtnValue[0].length() - 1);
			rtnValue[1].substring(0, rtnValue[1].length() - 1);
		}

		return rtnValue;
	}

	/**
	 * 获取模板model信息
	 * @Title:loadTemplateModelInfo
	 * @param sysWfProcess
	 * @return String[2] String[0]为ModelName，String[1]为Key
	 */
	private String[] loadTemplateModelInfo(SysWfProcess sysWfProcess) {
		String[] templateMes = new String[2];
		String templateModelName = null;
		String templateKey = null;
		SysWfTemplate template = sysWfTemplateRepository.findOne(sysWfProcess.getFdTemplateId());
		
		// 通用模板
		if (null == template) {
			SysWfCommonTemplate commonTemplate = sysWfCommonTemplateRepository.findOne(sysWfProcess.getFdTemplateId());
			if (null==commonTemplate) {
				log.warn("生成event事件中未能获取模板model信息，原模板可能已被删除");
				return templateMes;
			}
			templateModelName = commonTemplate.getFdModelName();
			templateKey = commonTemplate.getFdKey();
		} else {
			templateModelName = template.getFdModelName();
			templateKey = template.getFdKey();
		}
		
		templateMes[0] = templateModelName;
		templateMes[1] = templateKey;

		return templateMes;
	}

	/**
	 * 实现流程: 获取/拼接FdTranProcessXML
	 * @Title: getProcessStatus
	 * @param sysWfProcess
	 * @param nodeList
	 * @return 拼接后的FdTranProcessXML
	 */
	public String getProcessStatus(SysWfProcess sysWfProcess,List<SysWfNode> nodeList) {
		StringBuffer sb = new StringBuffer();
		sb.append(" <process-status> \n");
		sb.append("<historyNodes> \n");
		// 获取历史节点
		List<SysWfHistoryNode> historyNodeList = historyNodeRepo.findByFdProcessId(sysWfProcess.getFdId());

		for (int i = 0; i < historyNodeList.size(); i++) {
			SysWfHistoryNode hNode = historyNodeList.get(i);
			if (isThisNodeFinish(hNode, nodeList)) {
				sb.append("<node id=\"" + hNode.getFdFactNodeId() + "\" modelId=\"" + hNode.getFdId()
					+ "\" routeType=\"" + StringUtil.getString(hNode.getFdRouteType()) + "\" targetId=\""
					+ StringUtil.getString(hNode.getFdTargetId()) + "\" targetName=\"" + StringUtil.getString(hNode.getFdTargetName())
					+ "\"" + (StringUtil.isEmpty(hNode.getFdTargetModelId()) ? ""
						: " targetModelId=\"" + hNode.getFdTargetModelId() + "\"")
					+ "/> \n");
			}
		}
		sb.append("</historyNodes> \n");
		sb.append("<runningNodes> \n");
		for (int i = 0;i < nodeList.size();i++) {
			SysWfNode node = nodeList.get(i);
			sb.append("<node id=\"" + node.getFdFactNodeId() + "\" /> \n");
		}
		sb.append("</runningNodes> \n");
		sb.append("</process-status> \n");
		
		return sb.toString();
	}

	private boolean isThisNodeFinish(SysWfHistoryNode hNode,List<SysWfNode> nodeList) {
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).getFdId().equals(hNode.getFdNodeId())) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 转换字符串中HTML/XML敏感的字符。
	 * @param src 源字符串
	 * @return 转换后的字符串
	 */
	public static String XMLEscape(String src) {
		if (src == null) {
			return null;
		}
		
		String rtnVal = src.replaceAll("&","&amp;");
		rtnVal = rtnVal.replaceAll("\"","&quot;");
		rtnVal = rtnVal.replaceAll("<","&lt;");
		rtnVal = rtnVal.replaceAll(">","&gt;");
		rtnVal = rtnVal.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]","");
		
		return rtnVal;
	}

	public String getNodeFormInfo(SysWfProcess sysWfProcess,SysWfNode node,Map<String,Object> parameter) throws WorkflowException {
		if (("" + OAConstant.HANDLER_IDENTITY_HANDLER).equals(parameter.get("_HANDLER_IDENTITY"))) {
			parameter.put("_isCanModifyMainDoc",this.isCanModifyMainDoc(sysWfProcess,node));
			return getNodeFormInfo(sysWfProcess,node,parameter);
		}

		if (("" + OAConstant.HANDLER_IDENTITY_DRAFT).equals(parameter.get("_HANDLER_IDENTITY"))) {
			return getDrafterFormInfo(sysWfProcess,node);
		}
		
		if (("" + OAConstant.HANDLER_IDENTITY_PRIVILEGER).equals(parameter.get("_HANDLER_IDENTITY"))) {
			// 先判断当前用户是否为特权人
			@SuppressWarnings("unused")
			Map<String,String> nodeDetail = ProcessLogicHelper.getProcessAttr(sysWfProcess.getFdDetail());
			// if (isPrivileger(nodeDetail.get("privilegerIds"))) {
			parameter.put("_PRIVILEGER","1");
			return getPrivilegerFormInfo(sysWfProcess,node);
			// }
		}
		
		return "";
	}

	private Object isCanModifyMainDoc(SysWfProcess sysWfProcess,SysWfNode node) {
		return null;
	}

	/**
	 * 实现流程: 起草人XML拼接  <br/>
	 * 1.设置XML根节点属性   <br/>
	 * 2.设置根节点下元素的值  <br/>
	 * 3.设置其他信息 （通知方式,是否通知我）<br/>
	 * @Title: getDraftInfo
	 * @param sysWfProcess
	 * @param userId
	 * @param nodeList
	 * @return 拼接后的fdDrafterInfoXML
	 * @throws Exception
	 */
	private String getDraftInfo(SysWfProcess sysWfProcess,String userId,List<SysWfNode> nodeList,Map<String,Object> parameter) throws WorkflowException {
		StringBuffer drafterInfo = new StringBuffer();
		SysNewsMain sysNewsMain = sysNewsMainRepository.findOne(sysWfProcess.getFdId());

		// 1.设置XML根节点属性
		String fdStauts = sysWfProcess.getFdStatus();
		// 流程出错也开放起草人操作
		if (userId.equals(sysNewsMain.getDocCreatorId())
				&& (SysWfProcess.ACTIVATED.equals(fdStauts) 
				|| SysWfProcess.ERROR.equals(fdStauts))) { 
			parameter.put("_HANDLER_IDENTITY","" + OAConstant.HANDLER_IDENTITY_DRAFT);
			// 应在国际化配置文件中取值
			drafterInfo.append("	<drafter isArray=\"false\" CHILDRENISARRAY=\"true\" identifyKey=\""
					+ OAConstant.IDENTITY_DRAFTER + "\" identifyText=\"" + "起草人"
					+ "\">\n");
			// 2.设置根节点下元素的值
			for (Iterator<SysWfNode> nodeIter = nodeList.iterator();nodeIter.hasNext();) {
				SysWfNode node = nodeIter.next();
				drafterInfo.append(getNodeFormInfo(sysWfProcess,node,parameter));
			}
			drafterInfo.append("		</drafter>\n");
		}
		
		return drafterInfo.toString();
	}

	/**
	 * 实现流程:设置 起草人XML根节点下元素的值<br/>
	 * @Title: getDrafterFormInfo
	 * @param sysWfProcess
	 * @param node
	 * @return
	 * @throws Exception
	 */
	private String getDrafterFormInfo(SysWfProcess sysWfProcess,SysWfNode node) throws WorkflowException {
		StringBuffer sb = new StringBuffer();
		sb.append("		<info \n");
		sb.append("			nodeId=\"" + node.getFdFactNodeId() + "\"\n");
		sb.append("			nodeModelId=\"" + node.getFdId() + "\"\n");
		sb.append("			oprNames=\"" + XMLEscape(getDrafterOprNames(sysWfProcess, node)) + "\"\n");
		sb.append("			processText=\"" + XMLEscape(node.getFdFactNodeName()) + "\"\n");
		setOtherHandlerInfo(sysWfProcess, node, sb);
		sb.append("		 />\n");
		
		return sb.toString();
	}

	/**
	 * 实现流程: 获取起草人的操作方式<br/>
	 * 1.从流程图XML中获取操作id进而获取操作方式<br/>
	 * 2.直接从流程图XML中获取自定义操作方式<br/>
	 * @Title: getDrafterOprNames
	 * @param sysWfProcess
	 * @param node
	 * @return 起草人操作名称 String
	 */
	private String getDrafterOprNames(SysWfProcess sysWfProcess,SysWfNode node) {
		return null;
	}

	/**
	 * 实现流程: 特权人XML拼接<br/>
	 * @Title: getAuthorityInfo
	 * @param sysWfProcess
	 * @param nodeList
	 * @param parameter
	 * @return 特权人信息XML
	 * @throws Exception
	 */
	private String getAuthorityInfo(SysWfProcess sysWfProcess,List<SysWfNode> nodeList,Map<String,Object> parameter) throws WorkflowException {
		StringBuffer authorityInfo = new StringBuffer();
		// 应从国际化文件中取
		authorityInfo.append("	<authority isArray=\"false\" CHILDRENISARRAY=\"true\" identifyKey=\""
			+ OAConstant.IDENTITY_AUTHORITY + "\" identifyText=\"" + "特权人"
			+ "\">\n");
		// 特权人信息
		parameter.put("_HANDLER_IDENTITY", "" + OAConstant.HANDLER_IDENTITY_PRIVILEGER);
		
		// 当前节点为是否为空
		if (!nodeList.isEmpty()) {
			// 多节点时
			if (nodeList.size() > 1) {
				/* IWorkFlowNodeService nodeService = getNodeService(context,
				 null);
				 authorityInfo.append(nodeService.getNodeFormInfo(context,
				 null,
				 requestContext,parameter));*/
			}
			for (SysWfNode node : nodeList) {
				authorityInfo.append(getNodeFormInfo(sysWfProcess, node, parameter));
			}
		} else {
			/* IWorkFlowNodeService nodeService = getNodeService(context, null);
			 authorityInfo.append(nodeService.getNodeFormInfo(context, null,
			 requestContext, parameter));*/
		}
		authorityInfo.append("		</authority>\n");
		
		return authorityInfo.toString();
	}

	/**
	 * 实现流程:设置 特权人XML根节点下元素的值<br/>
	 * @Title: getPrivilegerFormInfo
	 * @param sysWfProcess
	 * @param node
	 * @return
	 * @throws Exception
	 */
	protected String getPrivilegerFormInfo(SysWfProcess sysWfProcess,SysWfNode node) 
		throws WorkflowException {
		StringBuffer sb = new StringBuffer();
		sb.append("		<info \n");
		sb.append("			nodeId=\"" + node.getFdFactNodeId() + "\"\n");
		sb.append("			nodeModelId=\"" + node.getFdId() + "\"\n");
		sb.append("			oprNames=\"" + XMLEscape(getPrivilegerOprNames(sysWfProcess, node)) + "\"\n");
		sb.append("			processText=\"" + XMLEscape(node.getFdFactNodeName()) + "\"\n");
		setOtherHandlerInfo(sysWfProcess, node, sb);
		sb.append("		 />\n");
		
		return sb.toString();
	}
	
	/**
	 * 获取特权人操作类型
	 * @param sysWfProcess
	 * @param node
	 * @return
	 */
	private String getPrivilegerOprNames(SysWfProcess sysWfProcess,SysWfNode node) {
		StringBuffer sb = new StringBuffer();
		/*sb.append(OAConstant.ADMIN_OPERATION_TYPE_PASS
		 + ":"
		 + ResourceUtil.getString(
		 "sysWfOperations.fdOperType.processor.finishpass",
		 "sys-workflow", requestContext.getLocale()) + ";");
		 sb.append(OAConstant.ADMIN_OPERATION_TYPE_JUMP
		 + ":"
		 + ResourceUtil.getString(
		 "sysWfOperations.fdOperType.processor.jump",
		 "sys-workflow", requestContext.getLocale()) + ";");
		 sb.append(OAConstant.ADMIN_OPERATION_TYPE_ABANDON
		 + ":"
		 + ResourceUtil.getString(
		 "sysWfOperations.fdOperType.processor.dirctabandon",
		 "sys-workflow", requestContext.getLocale()) + ";");
		 sb.append(OAConstant.ADMIN_OPERATION_TYPE_CHGCURHANDLER
		 + ":"
		 + ResourceUtil.getString(
		 "sysWfOperations.fdOperType.processor.modifyhandler",
		 "sys-workflow", requestContext.getLocale()) + ";");
		 sb.append(OAConstant.ADMIN_OPERATION_TYPE_MODIFYPROCESS
		 + ":"
		 + ResourceUtil.getString(
		 "sysWfOperations.fdOperType.processor.modifyflow",
		 "sys-workflow", requestContext.getLocale()));
		 if (!context.getSysWfProcess().getFdChildren().isEmpty()) {
		 sb.append(";");
		 sb.append(OAConstant.ADMIN_OPERATION_TYPE_RECOVER
		 + ":"
		 + ResourceUtil.getString(
		 "sysWfOperations.fdOperType.processor.recover",
		 "sys-workflow", requestContext.getLocale()));
		 }
		 if (context.getSysWfProcess().getFdRootToken() != null
		 && context.getSysWfProcess().getFdRootToken().isInactive()) {
		 sb.append(";");
		 sb.append(OAConstant.ADMIN_OPERATION_TYPE_ENDBRANCH
		 + ":"
		 + ResourceUtil.getString(
		 "sysWfOperations.fdOperType.processor.branchjoin",
		 "sys-workflow", requestContext.getLocale()));
		 }*/
		
		return sb.toString();
	}

	/**
	 * 实现流程:设置起草人,特权人XML其他信息 （通知方式,是否通知我）
	 * @Title:setOtherHandlerInfo
	 * @param sysWfProcess
	 * @param node
	 * @param sb XML字符串
	 * @throws Exception
	 */
	protected void setOtherHandlerInfo(SysWfProcess sysWfProcess,SysWfNode node,StringBuffer sb) {
		/* INodeDetail nodeDe = context.getProcessDetail().getNodeById(
		 node.getFdFactNodeId());
		 if (!(nodeDe instanceof OAAbstractManualNodeDetail)) {
		 return;
		 }
		 OAAbstractManualNodeDetail nodeDetail = (OAAbstractManualNodeDetail)
		 nodeDe;
		 OAProcessDetail detail = (OAProcessDetail) nodeDetail.loadDetail();
		 sb.append(" notifyType=\""
		 + StringUtil.getString(detail.getNotifyType()) + "\"\n");
		 sb.append(" notifyMe=\"" +
		 String.valueOf(detail.isNotifyOnFinish())
		 + "\"\n");*/
	}

	@Override
	public SysWfEvent getEventById(String fdId) {
		return sysWfEventRepository.findOne(fdId);
	}

	@Override
	public void addEvent(SysWfEvent sysWfEvent) throws WorkflowException {
		sysWfEventRepository.save(sysWfEvent);
		
	}
}

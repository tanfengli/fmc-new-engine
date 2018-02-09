package com.vispractice.fmc.business.services.setting.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.base.utils.ResourceUtil;
import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.domain.SbWFApprovalForm;
import com.vispractice.fmc.business.base.domain.SysWfBusinessForm;
import com.vispractice.fmc.business.base.utils.XMLReaderOrCreate;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsBasicVRepository;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsMainRepository;
import com.vispractice.fmc.business.entity.sys.news.view.SysNewsTemplateV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgPersonRepository;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfExpecterLog;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfHistoryNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfProcess;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfWorkitem;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfExpecterLogRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfHistoryNodeRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfLogVRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfNodeRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfTokenRepository;
import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfLogV;
import com.vispractice.fmc.business.service.sys.news.ISysNewsMainService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgPostPersonService;
import com.vispractice.fmc.business.service.sys.read.ISysReadLogService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfNodeService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfProcessService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfWorkitemService;
import com.vispractice.fmc.business.service.sys.workflow.impl.SysWfProcessServiceImpl;
import com.vispractice.fmc.business.services.setting.ILoadBillFormService;
import com.vispractice.fmc.business.services.setting.ISysNewsTemplateVService;

/**
 * 编  号：
 * 名  称：LoadBillFormServiceImpl
 * 描  述：LoadBillFormServiceImpl.java
 * 完成日期：2017年8月22日 上午11:18:46
 * 编码作者：ZhouYanyao
 */
@Service
@Transactional(readOnly = true)
public class LoadBillFormServiceImpl implements ILoadBillFormService {
	@Autowired
	private ISysWfProcessService sysWfProcessService;
	
	@Autowired
	private SysWfProcessServiceImpl wfProcessSrvImpl;

	@Autowired
	private SysNewsBasicVRepository sysNewsBasicVRepository;
	
	@Autowired
	private SysWfLogVRepository sysWfLogVRepository;
	
	@Autowired
	private SysWfHistoryNodeRepository sysWfHistoryNodeRepository;
	
	@Autowired
	private SysWfNodeRepository sysWfNodeRepository;
	
	@Autowired
	private ISysWfNodeService sysWfNodeService;
	
	@Autowired
	private SysOrgPersonRepository orgPersonRepo;

	@Autowired
	private ISysWfWorkitemService sysWfWorkitemService;
	
	@Autowired
	private SysOrgElementRepository orgElementRepo;
	
	@Autowired
	private ISysOrgPostPersonService orgPostPersonService;
	
	@Autowired
	private SysWfTokenRepository sysWfTokenService;
	
	@Autowired
	private SysNewsMainRepository sysNewsMainRepository;

	@Autowired
	private ISysNewsMainService sysNewsMainService;
	
	@Autowired
	private ISysReadLogService sysReadLogService;
	
	@Autowired
	private ISysNewsTemplateVService sysNewsTemplateVService;

	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;
	
	@Autowired
	private ISysOrgPostPersonService sysOrgPostPersonService;
	
	@Autowired
	private ISysOrgElementService sysOrgElementService;
	
	@Autowired
	private SysWfExpecterLogRepository sysWfExpecterLogRepository;
	
	@Autowired
	private ResourceUtil resourceUtil;
	
	/**
	 * 加载流程中单据的信息
	 */
	@Override
	public SysWfBusinessForm initForm(String fdTemplateId,SysOrgPerson sysOrgPerson) throws Exception {
		SysWfBusinessForm sysWfBusinessForm = new SysWfBusinessForm();
		
		//获取单据基本信息
		SysNewsMain sysNewsMain = new SysNewsMain();
		SysNewsTemplateV sysNewsTemplateV = sysNewsTemplateVService.findOne(fdTemplateId);
		sysNewsMain.setBusiSysId(sysNewsTemplateV.getBusiSysId());
		sysNewsMain.setBusiName(sysNewsTemplateV.getBusyName());
		sysNewsMain.setFdNewsSource(sysNewsTemplateV.getBusyCode());
		sysNewsMain.setFdTemplateId(sysNewsTemplateV.getFdId());
		sysNewsMain.setTemplateName(sysNewsTemplateV.getFdName());
		sysWfBusinessForm.setBasic(sysNewsMain);
		
		//获取当前所有节点处理人
		sysWfBusinessForm.setCurrentHandlerIds("");
		sysWfBusinessForm.setCurrentHandlerNames("");
		sysWfBusinessForm.setNodeList(new ArrayList<SysWfNode>());
		
		//获取审批日志信息
		sysWfBusinessForm.setHistoryHandlerNames("");
		sysWfBusinessForm.setWfLog(new ArrayList<SysWfLogV>());

		//获取审批历史节点信息
		sysWfBusinessForm.setHistoryList(new ArrayList<SysWfHistoryNode>());
		
		//设置流程模板内容
		sysWfBusinessForm.setFdFlowContent(sysNewsTemplateV.getFdFlowContent());
		
		//获取已阅人员
		sysWfBusinessForm.setReaderNames("");
		sysWfBusinessForm.setReadTimes(Long.parseLong("0"));

		return sysWfBusinessForm;
	}
	
	/**
	 * 加载流程中单据的信息
	 */
	@Override
	public SysWfBusinessForm loadForm(String wfInstanceId,SysOrgPerson sysOrgPerson) throws Exception {
		SysWfBusinessForm sysWfBusinessForm = new SysWfBusinessForm();
		//获取单据基本信息
		SysNewsMain sysNewsMain = sysNewsMainService.getByFdId(wfInstanceId);
		sysWfBusinessForm.setBasic(sysNewsMain);
		
		//获取当前所有节点处理人
		List<SysWfNode> sysWfNodes = sysWfNodeService.findByFdProcessId(wfInstanceId);
		String currentHandlerIds = "";
		String currentHandlerNames = "";
		for (SysWfNode sysWfNode : sysWfNodes) {
			currentHandlerIds = currentHandlerIds + ";" + sysWfNode.getFdHandlerIds();
			currentHandlerNames = currentHandlerNames + ";" + sysWfNode.getFdHandlerNames();
		}
		
		if (currentHandlerIds.length() > 0 && currentHandlerNames.length() > 0) {
			sysWfBusinessForm.setCurrentHandlerIds(currentHandlerIds.substring(1));
			sysWfBusinessForm.setCurrentHandlerNames(currentHandlerNames.substring(1));
		} else {
			sysWfBusinessForm.setCurrentHandlerIds("");
			sysWfBusinessForm.setCurrentHandlerNames("");
		}
		sysWfBusinessForm.setNodeList(sysWfNodes);
		
		//获取审批日志信息
		String historyHandlerNames = "";
		List<SysWfLogV> sysWfLogVs = sysWfLogVRepository.findByFdId(wfInstanceId);
		for (SysWfLogV sysWfLogV : sysWfLogVs) {
			sysWfLogV.setFdActionName(resourceUtil.getActionName(sysWfLogV.getFdActionName()));
			sysWfLogV.setFdActionInfo(resourceUtil.getActionInfo(sysWfLogV.getFdActionInfo()));
			sysWfLogV.setIsHideFlag("2");
			if (StringUtils.isNotEmpty(sysWfLogV.getFdIdentityFlag())) {
				sysWfLogV.setIsHideFlag("1");
				String[] flag = sysWfLogV.getFdIdentityFlag().split("x");
				// 当前审批意见为当前处理人的审批意见时 直接返回改处理人有权限查看
				if (sysOrgPerson.getFdId().equals(flag[0])) {
					sysWfLogV.setIsHideFlag("2");;
				}
				// 取得本流程相关处理人信息列表
				List<SysWfExpecterLog> sysWfExpecterLogList = sysWfExpecterLogRepository.findByFdWfProcessIdAndFdNodeId(wfInstanceId, sysWfLogV.getFdFactNodeId());
				for (SysWfExpecterLog excepterLog : sysWfExpecterLogList) {
					if (sysWfLogV.getFdIdentityFlag().equals(excepterLog.getFdIdentityFlag())
							&& sysOrgPerson.getFdId().equals(excepterLog.getFdExpecterId())) {
						sysWfLogV.setIsHideFlag("2");;
						break;
					}
				}
			}
			if (StringUtils.isNotEmpty(sysWfLogV.getFdName())&&!historyHandlerNames.contains(sysWfLogV.getFdName())) {
				historyHandlerNames = historyHandlerNames + ";" + sysWfLogV.getFdName();
			}
		}
		
		if (historyHandlerNames.length() > 0) {
			sysWfBusinessForm.setHistoryHandlerNames(historyHandlerNames.substring(1));
		}
		sysWfBusinessForm.setWfLog(sysWfLogVs);

		//获取审批历史节点信息
		List<SysWfHistoryNode> sysWfHistoryNodes = new ArrayList<SysWfHistoryNode>();
		List<SysWfHistoryNode> historyNodes = sysWfHistoryNodeRepository.findByFdProcessId(wfInstanceId);
		for (SysWfHistoryNode historyNode : historyNodes) {
			boolean flag = true;
			for (SysWfNode wfNode : sysWfNodes) {
				if (StringUtil.isNotNull(historyNode.getFdNodeId()) 
						&& historyNode.getFdNodeId().equals(wfNode.getFdId())) {
					flag = false;
				}
			}
			
			if (flag) {
				sysWfHistoryNodes.add(historyNode);
			}
		}
		sysWfBusinessForm.setHistoryList(sysWfHistoryNodes);
		
		//设置流程图内容
		SysWfProcess sysWfProcess = sysWfProcessService.findByFdModelId(wfInstanceId);
		sysWfBusinessForm.setFdFlowContent(sysWfProcess.getFdDetail());
		
		//获取已阅人员
		String readerNames = sysReadLogService.getReaderNames(wfInstanceId);
		sysWfBusinessForm.setReaderNames(readerNames);
		Long readTimes = sysReadLogService.countReadTimes(wfInstanceId);
		sysWfBusinessForm.setReadTimes(readTimes);

		return sysWfBusinessForm;
	}
	
	/**
	 * 加载审批单据信息
	 */
	@Override
	public SbWFApprovalForm initApproveForm(String wfInstanceId,String wfNodeId,SysOrgPerson sysOrgPerson,String identify) throws Exception {
		SbWFApprovalForm sbWFApprovalForm = new SbWFApprovalForm();
		//1.获取流程实例
		sbWFApprovalForm.setWfInstanceId(wfInstanceId);
		
		//2.获取当前节点
		SysWfNode sysWfNode = sysWfNodeService.getNodeByInstanceIdAndNodeId(wfInstanceId,wfNodeId);
		if (sysWfNode != null) {
			sbWFApprovalForm.setFdHandleNodeId(sysWfNode.getFdFactNodeId());
		} else  {
			sysWfNode = new SysWfNode();
		}
		
		//3.根据当前工作事项获取当前节点操作
		List<Map<String,Object>> oprNamesList = new ArrayList<Map<String,Object>>();
		List<SysWfWorkitem> sysWfWorkitems = sysWfWorkitemService.findByNodeIdAndUserId(sysWfNode.getFdId(),sysOrgPerson.getFdId());
		String oprNames = "";
		if (OAConstant.IDENTITY_AUTHORITY.equals(identify)) {
			resourceUtil.addOprName(oprNamesList,
					String.valueOf(OAConstant.ADMIN_OPERATION_TYPE_ABANDON),
					"sysWfOperations.fdOperType.processor.dirctabandon");
			
			resourceUtil.addOprName(oprNamesList,
					String.valueOf(OAConstant.ADMIN_OPERATION_TYPE_PASS),
					"sysWfOperations.fdOperType.processor.finishpass");
			
			resourceUtil.addOprName(oprNamesList,
					String.valueOf(OAConstant.ADMIN_OPERATION_TYPE_JUMP),
					"sysWfOperations.fdOperType.processor.jump");
			
			resourceUtil.addOprName(oprNamesList,
					String.valueOf(OAConstant.ADMIN_OPERATION_TYPE_CHGCURHANDLER),
					"sysWfOperations.fdOperType.processor.modifyhandler");
			
			resourceUtil.addOprName(oprNamesList,
					String.valueOf(OAConstant.ADMIN_OPERATION_TYPE_MODIFYPROCESS),
					"sysWfOperations.fdOperType.processor.modifyflow");
			
			resourceUtil.addOprName(oprNamesList,
					String.valueOf(OAConstant.ADMIN_OPERATION_TYPE_RECOVER),
					"sysWfOperations.fdOperType.processor.recover");
		} else {
			if (sysWfWorkitems != null && sysWfWorkitems.size() > 0) {
				sbWFApprovalForm.setCurrWorkItems(sysWfWorkitems);
				sbWFApprovalForm.setFdHandleWorkItemId(sysWfWorkitems.get(0).getFdId());
				sbWFApprovalForm.setFdRelationWorkitemId(sysWfWorkitems.get(0).getFdRelationWorkitemId());
				XMLReaderOrCreate xmlread = new XMLReaderOrCreate();
				Map<String,String> map = xmlread.getRootElementAttr(sysWfWorkitems.get(0).getFdParameter());
				oprNames = map.get("oprNames");
				
				if (null != oprNames) {
					String oprNameArr[] = oprNames.split(";");
					for (String oprName : oprNameArr) {
						Map<String,Object> operNameMap = new HashMap<String,Object>();
						String[] oprItem = oprName.split(":");
						operNameMap.put("oprId",oprItem[0]);
						String oprItemValueArr[] = oprItem[1].split("%");
						StringBuffer oprItemValue = new StringBuffer();
						for (String string : oprItemValueArr) {
							if (StringUtils.isNotEmpty(string)) {
								oprItemValue.append(resourceUtil.getLocaleMessage(string));
							}
						}
						operNameMap.put("oprName",oprItemValue.toString());
					
						oprNamesList.add(operNameMap);
					}
				}
				
				//设置被取消沟通人员列表
				List<Map<String,String>> communicaterList = new ArrayList<Map<String,String>>();
				if (StringUtils.isNotBlank(oprNames)) {
					if (oprNames.contains(String.valueOf(OAConstant.HANDLER_OPERATION_TYPE_CELCOMMUNICATE))) {
						List<SysWfWorkitem> relationWorkitems = sysWfWorkitemService.findByFdRelationWorkitemId(sysWfWorkitems.get(0).getFdId());
						for (SysWfWorkitem relationWorkitem : relationWorkitems) {
							Map<String,String> communicaterMap = new HashMap<String,String>();
							communicaterMap.put("fdId",relationWorkitem.getFdExpectedId());
							communicaterMap.put("fdName",sysOrgElementRepository.findOne(relationWorkitem.getFdExpectedId()).getFdName());
							communicaterMap.put("workitemId",relationWorkitem.getFdId());
							communicaterList.add(communicaterMap);
						}
					}
				}
				sbWFApprovalForm.setCommunicaterList(communicaterList);
			}
		}
		sbWFApprovalForm.setOprNames(oprNamesList);
		
		//设置默认操作类型
		if (oprNamesList != null && oprNamesList.size() > 0) {
			sbWFApprovalForm.setWfResult(String.valueOf(oprNamesList.get(0).get("id")));
		} else {
			sbWFApprovalForm.setWfResult("101");
		}
		
		//4.设置当前人员岗位信息
		if (sysOrgPerson != null) {
			List<SysOrgElement> currentPosts = new ArrayList<SysOrgElement>();
			List<SysOrgElement> sysOrgPosts = sysOrgPostPersonService.getPostByPersonId(sysOrgPerson.getFdId());
			if (sysOrgPosts != null && sysOrgPosts.size() > 0) {
				currentPosts.addAll(sysOrgPosts);
			}
			SysOrgElement sysOrgPost = sysOrgElementService.findByFdId(sysOrgPerson.getFdId());
			sbWFApprovalForm.setPostCode(sysOrgPost.getFdId());
			if (sysOrgPost != null) {
				currentPosts.add(sysOrgPost);
			}
			sbWFApprovalForm.setCurrentPosts(currentPosts);
		}
		
		return sbWFApprovalForm;
	}
}

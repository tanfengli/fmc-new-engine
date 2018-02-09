package com.vispractice.fmc.business.service.sys.news.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.km.review.KmReviewSn;
import com.vispractice.fmc.business.entity.sys.config.SysBusiSys;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.news.SysNewsTemplate;
import com.vispractice.fmc.business.entity.sys.news.repository.DocumentSubmmitedVRepository;
import com.vispractice.fmc.business.entity.sys.news.repository.ProcessHistoryVRepository;
import com.vispractice.fmc.business.entity.sys.news.repository.ProcessLogVRepository;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsBasicVRepository;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsMainRepository;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsTemplateRepository;
import com.vispractice.fmc.business.entity.sys.news.view.DocumentSubmmitedV;
import com.vispractice.fmc.business.entity.sys.news.view.ProcessHistoryV;
import com.vispractice.fmc.business.entity.sys.news.view.ProcessLogV;
import com.vispractice.fmc.business.entity.sys.notify.repository.SysNotifyTodoDoneInfoRepository;
import com.vispractice.fmc.business.entity.sys.notify.repository.SysNotifyTodoRepository;
import com.vispractice.fmc.business.entity.sys.notify.repository.SysNotifyTodotargetRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfAuditNoteRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfHistoryNodeRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfLogVRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfNodeRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfProcessRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfTokenRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfWorkitemRepository;
import com.vispractice.fmc.business.service.km.review.IKmReviewSnService;
import com.vispractice.fmc.business.service.sys.config.ISysBusiSysService;
import com.vispractice.fmc.business.service.sys.news.ISysNewsMainService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfProcessService;

@Transactional
@Service
public class SysNewsMainServiceImpl implements ISysNewsMainService {
	@Autowired
	private SysNewsMainRepository sysNewsMainRepository;

	@Autowired
	private DocumentSubmmitedVRepository documentSubmmitedVRepository;

	@Autowired
	private ProcessHistoryVRepository processHistoryVRepository;
	
	@Autowired
	private ProcessLogVRepository processLogVRepository;

	@Autowired
	private SysNewsBasicVRepository sysNewsBasicVRepository;

	@Autowired
	private SysNewsTemplateRepository sysNewsTemplateRepository;

	@Autowired
	private SysWfNodeRepository sysWfNodeRepository;

	@Autowired
	private SysWfWorkitemRepository sysWfWorkitemRepository;

	@Autowired
	private SysWfLogVRepository wfLogVRepository;

	@Autowired
	private SysWfHistoryNodeRepository historyNodeRepo;
	
	@Autowired
	private SysWfProcessRepository sysWfProcessRepository;
	
	@Autowired
	private SysWfAuditNoteRepository sysWfAuditNoteRepository;
	
	@Autowired
	private SysNotifyTodoRepository sysNotifyTodoRepository;
	
	@Autowired
	private SysNotifyTodotargetRepository sysNotifyTodotargetRepository;
	
	@Autowired
	private SysNotifyTodoDoneInfoRepository sysNotifyTodoDoneInfoRepository;
	
	@Autowired
	private SysWfTokenRepository sysWfTokenRepository;

	@Autowired
	private ISysWfProcessService sysWfProcessService;
	
	@Autowired
	private IKmReviewSnService kmReviewSnService;
	
	@Autowired
	private ISysBusiSysService busiSysService;
	
	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;

	/**
	 * 查看我的申请接口服务
	 */
	@Override
	public Page<DocumentSubmmitedV> searchDocumentSubmmitedV(DocumentSubmmitedV documentSubmmitedV,Pageable pageable) {
		Specification<DocumentSubmmitedV> spec = buildQuery(documentSubmmitedV,pageable);
		Page<DocumentSubmmitedV> documentSubmmitedVs = documentSubmmitedVRepository.findAll(spec,pageable);
		
		return documentSubmmitedVs;
		
	}
	
	/**
	 * 单据查询条件
	 */
	public Specification<DocumentSubmmitedV> buildQuery(final DocumentSubmmitedV docSubmit,Pageable pageable) {
		Specification<DocumentSubmmitedV> spec = new Specification<DocumentSubmmitedV>() {
			@Override
			public Predicate toPredicate(Root<DocumentSubmmitedV> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();

				// 用户
				if (StringUtils.isNotBlank(docSubmit.getCreatorUserNo())) {
					expressions.add(cb.equal(root.<String>get("creatorUserNo"),docSubmit.getCreatorUserNo()));
				}
				
				// 创建人：名称/编号
				if (StringUtils.isNotBlank(docSubmit.getCreatorUserName())) {
					Predicate p1 = cb.equal(root.<String>get("creatorUserNo"),docSubmit.getCreatorUserNo());
					Predicate p2 = cb.equal(root.<String>get("creatorUserName"),docSubmit.getCreatorUserName());
					Predicate p3 = cb.or(p1,p2);
					expressions.add(p3);
				}

				// 单据主题
				if (StringUtils.isNotBlank(docSubmit.getDocSubject())) {
					expressions.add(cb.like(root.<String>get("docSubject"),"%" + docSubmit.getDocSubject() + "%"));
				}

				// 节点名称
				if (StringUtils.isNotBlank(docSubmit.getNodeNames())) {
					expressions.add(cb.like(root.<String>get("nodeNames"),"%" + docSubmit.getNodeNames() + "%"));
				}
				
				// 处理人名字
				if (StringUtils.isNotBlank(docSubmit.getDealName())) {
					expressions.add(cb.like(root.<String>get("dealName"),"%" + docSubmit.getDealName() + "%"));
				}

				// 单据编号
				if (StringUtils.isNotBlank(docSubmit.getApplyCode())) {
					expressions.add(cb.like(root.<String>get("applyCode"),"%" + docSubmit.getApplyCode() + "%"));
				}
				
				// 开始时间
				if (null != docSubmit.getDocCreateTime()) {
					expressions.add(cb.greaterThanOrEqualTo(root.<Date>get("docCreateTime"),docSubmit.getDocCreateTime()));
				}

				// 申请原因
				if (StringUtils.isNotBlank(docSubmit.getFdDescription())) {
					expressions.add(cb.like(root.<String>get("fdDescription"),"%" + docSubmit.getFdDescription() + "%"));
				}

				// 申请内容
				if (StringUtils.isNotBlank(docSubmit.getDocContent())) {
					expressions.add(cb.like(root.<String>get("docContent"),"%" + docSubmit.getDocContent() + "%"));
				}
				
				// 模板id
				if (null != docSubmit.getFdTemplateId()) {
					expressions.add(cb.equal(root.<String>get("fdTemplateId"),docSubmit.getFdTemplateId()));
				}
				
				// 紧急程度
				if (null != docSubmit.getFdImportance()) {
					expressions.add(cb.equal(root.<Long>get("fdImportance"),docSubmit.getFdImportance()));
				}
				
				// 单据状态
				if (StringUtils.isNotBlank(docSubmit.getDocStatus())) {
					expressions.add(root.<String>get("docStatus").in(Arrays.asList(docSubmit.getDocStatus().split(","))));
				}

				return predicate;
			}
		};
		
		return spec;
	}
	
	/**
	 * 单据新增/提交
	 */
	@Override
	public void submitDocument(SysNewsMain sysNewsMain) throws WorkflowException {
		SysNewsTemplate sysNewsTemplate = sysNewsTemplateRepository.findOne(sysNewsMain.getFdTemplateId());

		if (null == sysNewsMain.getFdId()) {
			String applyCode = sysNewsMain.getApplyCode();
			
			//单据号生成
			if (applyCode == null) {
				KmReviewSn snContext = new KmReviewSn();
				snContext.setFdModelName("com.ruiyi.kmss.sys.news.model.SysNewsMain");
				snContext.setFdTemplateId(sysNewsTemplate.getFdId());
				snContext.setFdPrefix(sysNewsTemplate.getFdNumberPrefix());
				try {
					synchronized (this) {
						applyCode = kmReviewSnService.getSerialNumber(snContext);
						sysNewsMain.setApplyCode(applyCode);
					}
				} catch (Exception e) {
					throw new WorkflowException("生成单据号出错.",e);
				}
			}
		}
		
		try {
			if (null == sysNewsMain.getBusiSysId()) {
				SysBusiSys busiSys = busiSysService.getByFdCode(sysNewsMain.getFdNewsSource());
				sysNewsMain.setBusiSysId(busiSys.getFdId());
			}
			
			sysNewsMain.setFdLastModifiedTime(new Date());
			sysNewsMainRepository.save(sysNewsMain);
		} catch (Exception e) {
			throw new WorkflowException("生成流程参数错误." + e.getMessage());
		}
	}

	@Override
	public SysNewsMain findByBusiSysIdAndApplCode(String BusiSysId,String applyCode) {
		return sysNewsMainRepository.findByBusiSysIdAndApplyCode(BusiSysId,applyCode);
	}

	@Override
	public SysNewsMain getByFdId(String fdId) {
		SysNewsMain sysNewsMain = sysNewsMainRepository.findOne(fdId);
		SysBusiSys sysBusiSys = busiSysService.get(sysNewsMain.getBusiSysId());
		SysNewsTemplate sysNewsTemplate = sysNewsTemplateRepository.findOne(sysNewsMain.getFdTemplateId());
		sysNewsMain.setBusiName(sysBusiSys.getFdName());
		sysNewsMain.setTemplateName(sysNewsTemplate.getFdName());
		sysNewsMain.setDocCreateName(sysOrgElementRepository.findOne(sysNewsMain.getDocCreatorId()).getFdName());
		
		return sysNewsMain;
	}

	/**
	 * 根据流程实例标识查找审批历史记录
	 */
	@Override
	public List<ProcessHistoryV> findProcessHistory(String wfInstanceId) {
		List<ProcessHistoryV> processHistoryVs = processHistoryVRepository.findByWfInstanceId(wfInstanceId);
		
		return processHistoryVs;
	}

	/**
	 * 根据流程实例标识查找审批日志记录
	 */
	@Override
	public List<ProcessLogV> findProcessLog(String wfInstanceId) {
		List<ProcessLogV> processLogVs = processLogVRepository.findByWfInstanceId(wfInstanceId);
		
		return processLogVs;
	}
	
	/**
	 * 根据人员和流程实例获取单据信息
	 */
	@Override
	public SysNewsMain findByUserNoAndWfinstanceId(String userNo,String wfInstanceId) {
		SysNewsMain sysNewsMain = sysNewsMainRepository.findByUserNoAndWfInstanceId(userNo,wfInstanceId);
		
		return sysNewsMain;
	}
	
	/**
	 * 删除流程实例及其相关信息
	 */
	@Override
	public void deleteProcessInstance(String fdIdStr){
		List<String> fdIdList = StringUtil.getStrList(fdIdStr);
		
		// 1.删除单据信息
		sysNewsMainRepository.deleteAllByfdId(fdIdList);
		// 2.删除流程实例
		sysWfProcessRepository.deleteAllByFdModelId(fdIdList);
		// 3.删除令牌
		sysWfTokenRepository.deleteAllByFdProcessId(fdIdList);
		// 4.删除流转记录
		sysWfAuditNoteRepository.deleteAllByFdProcessId(fdIdList);
		// 5.删除通知/待办信息
		List<String> todoIdList = sysNotifyTodoRepository.findFdIdByProcessId(fdIdList);
		if (todoIdList.size()>0) {
			sysNotifyTodotargetRepository.deleteAllByFdTodoidIn(todoIdList);
			sysNotifyTodoDoneInfoRepository.deleteAllByFdTodoid(todoIdList);
			sysNotifyTodoRepository.deleteAllByFdKey(fdIdList);
		}
		// 6.删除流程工作事项
		List<String> fdNodeIdList = sysWfNodeRepository.findFdIdListByProcessId(fdIdList);
		if (fdNodeIdList.size()>0) {
			sysWfWorkitemRepository.deleteAllByFdNodeId(fdNodeIdList);
		}
		// 7.删除流程流转节点
		sysWfNodeRepository.deleteAllByFdProcessId(fdIdList);
		
	}

	@Override
	public void save(SysNewsMain sysNewsMain) {
		sysNewsMainRepository.save(sysNewsMain);
	}
}

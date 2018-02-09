package com.vispractice.fmc.business.service.aboutmyself.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

import com.vispractice.fmc.business.base.utils.XMLReaderOrCreate;
import com.vispractice.fmc.business.entity.aboutmyself.VCmsTask;
import com.vispractice.fmc.business.entity.aboutmyself.repository.VCmsTaskRepository;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.service.aboutmyself.IVCmsTaskService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfNodeService;

/**
 * 待办信息服务
 * @author ZhouYanyao
 */
@Service
@Transactional
public class VCmsTaskServiceImpl implements IVCmsTaskService {
	@Autowired
	private VCmsTaskRepository cmsTaskRepository;
	
	@Autowired
	private ISysWfNodeService sysWfNodeService;
	
	/**
	 * 实体对象管理
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * 分页查询待办记录
	 * @throws
	 */
	@Override
	public Page<VCmsTask> searchVCmsTask(final VCmsTask vCmsTask,Pageable pageable) {
		Specification<VCmsTask> spec = buildQuery(vCmsTask,pageable);
		Page<VCmsTask> vCmsTasks = cmsTaskRepository.findAll(spec,pageable);
		em.clear();
		this.buildOperator(vCmsTasks);
		
		return vCmsTasks;
	}
	
	/**
	 * 解析操作类型
	 * @param vcmsTasks
	 * @return
	 */
	private void buildOperator(Page<VCmsTask> vCmsTasks) {
		XMLReaderOrCreate reader = new XMLReaderOrCreate();
		if (vCmsTasks != null && vCmsTasks.getContent() != null && vCmsTasks.getContent().size() > 0) {
			for (VCmsTask vCmsTask : vCmsTasks.getContent()) {
				SysWfNode sysWfNode = sysWfNodeService.findByFdId(vCmsTask.getFdId());
				if (sysWfNode != null && StringUtils.isNotEmpty(sysWfNode.getFdParameter())) {
					Map<String,String> rootAttr = reader.getRootElementAttr(sysWfNode.getFdParameter());
					vCmsTask.setOprNames(rootAttr.get("oprNames"));
				}
			}
		}
	}

	/**
	 * 构造查询条件
	 * @param vCmsTask
	 * @param pageable
	 * @return
	 */
	private Specification<VCmsTask> buildQuery(final VCmsTask vCmsTask,Pageable pageable) {
		Specification<VCmsTask> spec = new Specification<VCmsTask>() {
			@Override
			public Predicate toPredicate(Root<VCmsTask> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				{
					Predicate predicate = cb.conjunction();
					List<Expression<Boolean>> expressions = predicate.getExpressions();
					// 主题
					if (StringUtils.isNotBlank(vCmsTask.getAppTitle())) {
						expressions.add(cb.like(root.<String> get("appTitle"),"%" + vCmsTask.getAppTitle() + "%"));
					}
					
					// 单据编号
					if (StringUtils.isNotBlank(vCmsTask.getApplyCode())) {
						expressions.add(cb.like(root.<String> get("applyCode"),"%" + vCmsTask.getApplyCode() + "%"));
					}
					
					// 当前处理人
					if (StringUtils.isNotBlank(vCmsTask.getTaskUserName())) {
						expressions.add(cb.like(root.<String> get("taskUserName"),"%" + vCmsTask.getTaskUserName() + "%"));
					}
					
					// 当前节点
					if (StringUtils.isNotBlank(vCmsTask.getNodeName())) {
						expressions.add(cb.like(root.<String> get("nodeName"),"%" + vCmsTask.getNodeName() + "%"));
					}

					// 用户编号
					if (StringUtils.isNotBlank(vCmsTask.getTaskUserNo())) {
						expressions.add(cb.equal(root.<String> get("taskUserNo"),vCmsTask.getTaskUserNo()));
					}
					
					// 开始时间
					if (null != vCmsTask.getAppDate()) {
						expressions.add(cb.greaterThanOrEqualTo(root.<Date> get("appDate"),vCmsTask.getAppDate()));
					}
					
					// 结束时间
					if (null != vCmsTask.getPublishDate()) {
						expressions.add(cb.lessThanOrEqualTo(root.<Date> get("appDate"),vCmsTask.getPublishDate()));
					}
					
					// 申请原因
					if (StringUtils.isNotBlank(vCmsTask.getFdDescription())) {
						expressions.add(cb.like(root.<String> get("fdDescription"),"%" + vCmsTask.getFdDescription() + "%"));
					}
					
					// 申请内容
					if (StringUtils.isNotBlank(vCmsTask.getDocContent())) {
						expressions.add(cb.like(root.<String> get("docContent"),"%" + vCmsTask.getDocContent() + "%"));
					}
					
					// 紧急程度
					if (StringUtils.isNotBlank(vCmsTask.getUrgentLevel())) {
						expressions.add(cb.equal(root.<Long> get("urgentLevel"),vCmsTask.getUrgentLevel()));
					}
					
					return predicate;
				}
			}
		};
		
		return spec;
	}
}

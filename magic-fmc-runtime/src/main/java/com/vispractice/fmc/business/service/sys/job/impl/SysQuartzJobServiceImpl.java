package com.vispractice.fmc.business.service.sys.job.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.base.utils.function.PropertiesFuncProvider;
import com.vispractice.fmc.business.entity.sys.job.SysQuartzJob;
import com.vispractice.fmc.business.entity.sys.job.repository.SysQuartzJobRepository;
import com.vispractice.fmc.business.service.sys.job.ISysQuartzJobService;
import com.vispractice.fmc.business.service.sys.workflow.ISysQuartzJobTaskService;

@Service
@Transactional
public class SysQuartzJobServiceImpl implements ISysQuartzJobService {

	@Autowired
	private SysQuartzJobRepository quartzRepository;
	
	@Autowired(required=false)
	private ISysQuartzJobTaskService sysQuartzJobTaskService;
	
	@Override
	public Page<SysQuartzJob> findBySearch(final SysQuartzJob quartzJob, Pageable pageable) {
		
		Specification<SysQuartzJob> spec = new Specification<SysQuartzJob>() {

			@Override
			public Predicate toPredicate(Root<SysQuartzJob> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(null != quartzJob){ 
					if (StringUtils.isNotBlank(quartzJob.getFdSubject())){
						
						Path<String> fdSubject = root.get("fdSubject");

						String filterName = "%"+quartzJob.getFdSubject()+"%";

						list.add(cb.like(fdSubject, filterName));
					}
				}
				Predicate[] p = new Predicate[list.size()];
				
				query.orderBy(cb.desc(root.get("fdEnabled").as(Integer.class)));  
				
				return cb.and(list.toArray(p));
			}

		};
		return quartzRepository.findAll(spec, pageable);

	}


	@Override
	public void save(SysQuartzJob quartzJob) {
		quartzRepository.save(quartzJob);
	}


	@Override
	public List<SysQuartzJob> findAll() {
		return quartzRepository.findAll();
	}


	@Override
	public void delete(SysQuartzJob quartzJob) {
	
		quartzRepository.delete(quartzJob);
		
	}


	@Override
	public SysQuartzJob findByFdId(String fdId) {
		
		return quartzRepository.findOne(fdId);
	}
	
	
	public void initSysQuartzJob(List<Map<String,String>> list){
//		 List<Map<String,String>> list = PropertiesFuncProvider.initSysQuartzJob();
		 quartzRepository.deleteAll();
		 for(Map<String,String> map:list){
			 SysQuartzJob job = new SysQuartzJob();
			 job.setFdJobService(map.get("serviceName"));
			 job.setFdSubject(map.get("jobName"));
			 job.setFdCronExpression(map.get("cron"));
			 job.setFdJobMethod(map.get("methodName"));
			 job.setFdIsSysJob(true);
			 job.setFdEnabled(true);
			 job.setFdRunType(4);
			 job.setFdRequired(false);
			 quartzRepository.save(job);
		 }
		 
		 publishJob(new SysQuartzJob(), "init");
	}


	@Override
	public void executeJob(SysQuartzJob quartzJob,String executeType) {
		publishJob(quartzJob, executeType);
	}
	
	
	private void publishJob(final SysQuartzJob job,final String executeType){
		try {
			TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
				@Override
				@Async
				public void afterCommit() {
					try {
						sysQuartzJobTaskService.submit(job,executeType);
					} catch (Exception e) {
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("生成定时任务失败.",e);
		}
	}
}
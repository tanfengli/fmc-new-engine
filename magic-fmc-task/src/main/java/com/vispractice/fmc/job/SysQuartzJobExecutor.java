package com.vispractice.fmc.job;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import com.vispractice.fmc.base.CronExpression;
import com.vispractice.fmc.base.SchedulerTaskBase;
import com.vispractice.fmc.business.entity.sys.job.SysQuartzJob;
import com.vispractice.fmc.business.service.sys.job.ISysQuartzJobService;
import com.vispractice.fmc.business.service.sys.job.impl.SysQuartzJobServiceImpl;

@Slf4j
public class SysQuartzJobExecutor  extends SchedulerTaskBase implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	
//	@Autowired
	private ISysQuartzJobService sysQuartzJobService;
	
	private SysQuartzJob sysQuartzJob;
	
	public SysQuartzJob getSysQuartzJob() {
		return sysQuartzJob;
	}

	public void setSysQuartzJob(SysQuartzJob sysQuartzJob) {
		this.sysQuartzJob = sysQuartzJob;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
	}

	public void init(SysQuartzJob sysQuartzJob){
		sysQuartzJobService = applicationContext.getBean(SysQuartzJobServiceImpl.class);
		this.sysQuartzJob=sysQuartzJob;
		setCron(sysQuartzJob.getFdCronExpression());
	}
	/**
	 * 执行单个任务
	 * 
	 * @param jobModel
	 * @param now
	 * @throws Exception
	 */
	public void execute() throws Exception {
		// 查找bean
		if (!applicationContext.containsBean(sysQuartzJob.getFdJobService())) {
			log.error("无法找到bean：" + sysQuartzJob.getFdJobService() + "，定时任务（"
					+ sysQuartzJob.getFdSubject() + "）将被禁止或删除！");
			updateNextTime(sysQuartzJob, false);
			return;
		}
		Object service = applicationContext.getBean(sysQuartzJob.getFdJobService());
		Method method = null;
		try {
			updateNextTime(sysQuartzJob, true);
			method = service.getClass().getMethod(sysQuartzJob.getFdJobMethod());
			method.invoke(service);
		
		} catch (Exception e) {
			log.error("无法找到方法：" + sysQuartzJob.getFdJobService() + "."
					+ sysQuartzJob.getFdJobMethod() + "()或"
					+ sysQuartzJob.getFdJobService() + "."
					+ sysQuartzJob.getFdJobMethod()
					+ "定时任务（"
					+ sysQuartzJob.getFdSubject() + "）将被禁止或删除！");
			updateNextTime(sysQuartzJob, false);
		}
	}
	
	/**
	 * 更新下次运行时间，在运行完毕后触发
	 * 
	 * @param jobModel
	 * @param canNotRun
	 * @param now
	 * @throws Exception
	 */
	private void updateNextTime(SysQuartzJob jobModel, boolean canNotRun) throws Exception {
//		if (canNotRun) {
////			disableJob(jobModel);
//			return;
//		}
		Date nextTime = getNextTime(jobModel, new Date());
		if (nextTime == null) {
			if (log.isDebugEnabled()) {
				log.debug("定时任务：" + jobModel.getFdSubject() + "("
						+ jobModel.getFdId() + ")已经没有下次运行时间了，因此禁用该任务。");
			}
//			disableJob(jobModel);
		} else {
			if (log.isDebugEnabled()) {
				log.debug("定时任务：" + jobModel.getFdSubject() + "("
						+ jobModel.getFdId() + ")的下一次任务时间将安排在：" + nextTime);
			}
			jobModel.setFdRunTime(new Timestamp(nextTime.getTime()));
			sysQuartzJobService.save(jobModel);
		}
	}
	
	/**
	 * 禁用某个任务
	 * 
	 * @param jobModel
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void disableJob(SysQuartzJob jobModel) throws Exception {
		if (jobModel.isFdIsSysJob()) {
			jobModel.setFdEnabled(Boolean.FALSE);
			sysQuartzJobService.save(jobModel);
		} else {
			sysQuartzJobService.delete(jobModel);
		}
	}
	/**
	 * 构造任务运行服务
	 * 
	 * @param jobModel
	 * @return
	 */
//	private Runnable buildRunner(SysQuartzJob jobModel) {
//		// 查找bean
//		if (!applicationContext.containsBean(jobModel.getFdJobService())) {
//			log.error("无法找到bean：" + jobModel.getFdJobService() + "，定时任务（"
//					+ jobModel.getFdSubject() + "）将被禁止或删除！");
//			return null;
//		}
//		Object service = applicationContext.getBean(jobModel.getFdJobService());
//		Method method = null;
//		try {
//			method = service.getClass().getMethod(jobModel.getFdJobMethod());
//		} catch (Exception e) {
//			log.error("无法找到方法：" + jobModel.getFdJobService() + "."
//					+ jobModel.getFdJobMethod() + "()或"
//					+ jobModel.getFdJobService() + "."
//					+ jobModel.getFdJobMethod()
//					+ "(SysQuartzJobContext)，定时任务（"
//					+ jobModel.getFdSubject() + "）将被禁止或删除！");
//			return null;
//		}
////		return new SysQuartzJobRunner(service, method, jobModel);
//		return null;
//	}

	@Override
	public void start() {
		
	}

	@Override
	public void doRun() {
		try {
			execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void restart() {
		ThreadPoolTaskScheduler threadPoolTaskScheduler = SchedulerTaskBase.getThreadPoolTaskScheduler();
		this.stop();
		this.scheduledFuture = threadPoolTaskScheduler.schedule(this,this.getTrigger());
	}
	
	
	/**
	 * 获取下一次运行时间
	 * 
	 * @param job
	 * @param now
	 * @return
	 */
	private Date getNextTime(SysQuartzJob job, Date now) {
		try {
			CronExpression expression = new CronExpression(job.getFdCronExpression());
			return expression.getNextValidTimeAfter(now);
		} catch (Exception e) {
			log.warn("解释运行时间表达式发生错误！", e);
			return null;
		}

	}

}

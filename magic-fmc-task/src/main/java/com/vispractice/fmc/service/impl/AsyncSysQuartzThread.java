package com.vispractice.fmc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import com.vispractice.fmc.base.SchedulerTaskBase;
import com.vispractice.fmc.business.entity.sys.cluster.SysClusterDispatcher;
import com.vispractice.fmc.business.entity.sys.cluster.SysClusterServer;
import com.vispractice.fmc.business.entity.sys.job.SysQuartzJob;
import com.vispractice.fmc.business.service.sys.cluster.impl.SysClusterDispatcherServiceImpl;
import com.vispractice.fmc.business.service.sys.cluster.impl.SysClusterServerServiceImpl;
import com.vispractice.fmc.business.service.sys.job.ISysQuartzJobService;
import com.vispractice.fmc.job.SysQuartzJobExecutor;

@Component
public class AsyncSysQuartzThread implements InitializingBean , ApplicationContextAware{
    
	@Autowired
	private ISysQuartzJobService sysQuartzJobService;
	
	
//	@Autowired
//	private SysQuartzJobExecutor sysQuartzJobExecutor;
	
	@Autowired
	private SysClusterDispatcherServiceImpl dispatcherServiceImpl;
	
	@Autowired
	private SysClusterServerServiceImpl serverServiceImpl;
	
//	@SuppressWarnings("unused")
//	private final static String JOB_NAME="sysWfEventService";
	
	@SuppressWarnings("unused")
	private final static String QUARTZ_NAME="sysQuartzDispatcher";
	

	private static Map<String,SysQuartzJobExecutor> quartzJobMap = new HashMap<String, SysQuartzJobExecutor>();
	
	@Value("${cluster.hostName}")
	private String clusterHostName;
	
	private ApplicationContext applicationContext;
	
	/**
	 * 启动运行任务
	 */
	public void run() {
		//如果分发服节点数据为空 表示启动
		List<SysClusterDispatcher> dispatcherList = dispatcherServiceImpl.findAll();
		
		List<SysClusterServer> serverList = serverServiceImpl.findAll();
		
		if(serverList==null||serverList.size()==0){
			startSquartzJob();
			//初始化服务器数据
			serverServiceImpl.registerServer(clusterHostName, null);
			dispatcherServiceImpl.save(clusterHostName, QUARTZ_NAME);
			return;
		}else{
			boolean flag = true;
			for(SysClusterServer server:serverList){
				if(clusterHostName.equals(server.getFdKey())){
					flag = false;
					break;
				}
			}
			if(flag){
				serverServiceImpl.registerServer(clusterHostName, null);
			}
		}
//		
//		List<SysClusterDispatcher> dispatcherList = dispatcherServiceImpl.findByFdServerKey(clusterHostName);
		if(dispatcherList!=null&&dispatcherList.size()>0){
			for(SysClusterDispatcher dispatcher:dispatcherList){
				//只启用当前分发节点任务
				if(QUARTZ_NAME.equals(dispatcher.getFdDispatcherKey())&&dispatcher.getFdServerKey().equals(clusterHostName)){
					startSquartzJob();
				}
			}
		}else{
			startSquartzJob();
			dispatcherServiceImpl.save(clusterHostName, QUARTZ_NAME);
		}
		
	}
	/**
	 * 启动所有任务
	 */
	public void  startSquartzJob(){
		List<SysQuartzJob> list = sysQuartzJobService.findAll();
		for(SysQuartzJob job:list){
			if(job.isFdEnabled()){
				start(job);
			}
			
		}
	}
	
//	public 

	@Override
	public void afterPropertiesSet() throws Exception {
		run();
	}
	
	/**
	 * 禁用任务
	 * @param job
	 */
	public void stop(SysQuartzJob job){
		SysQuartzJobExecutor sysQuartzJobExecutor = quartzJobMap.get(job.getFdId());
		if(sysQuartzJobExecutor!=null){
			sysQuartzJobExecutor.stop();
		}
	}
	
	/**
	 * 启动任务
	 * @param job
	 */
	public void start(SysQuartzJob job){
		try {
			SysQuartzJobExecutor sysQuartzJobExecutor = quartzJobMap.get(job.getFdId());
			if (sysQuartzJobExecutor != null) {
				sysQuartzJobExecutor.setSysQuartzJob(job);
				sysQuartzJobExecutor.setCron(sysQuartzJobExecutor.getCron());
				sysQuartzJobExecutor.restart();
//				sysQuartzJobExecutor.execute();
				quartzJobMap.put(job.getFdId(), sysQuartzJobExecutor);
			} else {
			
				ThreadPoolTaskScheduler threadPoolTaskScheduler = SchedulerTaskBase
						.getThreadPoolTaskScheduler();
				sysQuartzJobExecutor = new SysQuartzJobExecutor();
				sysQuartzJobExecutor.setApplicationContext(applicationContext);
				sysQuartzJobExecutor.init(job);
				sysQuartzJobExecutor.setCron(sysQuartzJobExecutor.getCron());
				quartzJobMap.put(job.getFdId(), sysQuartzJobExecutor);
				sysQuartzJobExecutor.setScheduledFuture(threadPoolTaskScheduler.schedule(sysQuartzJobExecutor,
								sysQuartzJobExecutor.getTrigger()));
//				sysQuartzJobExecutor.execute();
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * 执行任务
	 * @param job
	 */
	public void execute(SysQuartzJob job){
		if(job.isFdEnabled()){
			start(job);
		}else{
			stop(job);
		}
	}
	/**
	 * 立即执行任务
	 * @param fdId
	 */
	public void executeNow(SysQuartzJob job){
		try {
//			SysQuartzJob job = sysQuartzJobService.findByFdId(fdId);
			SysQuartzJobExecutor sysQuartzJobExecutor = quartzJobMap.get(job.getFdId());
			if (sysQuartzJobExecutor == null) {
				sysQuartzJobExecutor = new SysQuartzJobExecutor();
				sysQuartzJobExecutor.setSysQuartzJob(job);
				sysQuartzJobExecutor.setApplicationContext(applicationContext);
				
			}
			sysQuartzJobExecutor.execute();
		} catch (Exception e) {
			
		}
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
		
	}
	/**
	 * 清除所有任务
	 */
	public void clearAll(){
		for(String fdId:quartzJobMap.keySet()){
			SysQuartzJobExecutor executor = quartzJobMap.get(fdId);
			executor.stop();
		}
	}
	
}
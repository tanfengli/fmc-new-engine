package com.vispractice.fmc.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vispractice.fmc.business.entity.sys.job.SysQuartzJob;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfEvent;
import com.vispractice.fmc.service.impl.AsyncSysQuartzThread;

@Component
public class SysQuartzJobNotifier implements ApplicationListener<SysQuartzJobEvent> {

	@Autowired
	private AsyncSysQuartzThread asyncSysQuartzThread;
	private static final String EXECUTE_INIT="init";//初始化定时任务
	private static final String EXECUTE_ONLY="execute";//立即执行任务
	private static final String EXECUTE_QUARTZ="quartz";//定时执行任务
	
	@Override
	public void onApplicationEvent(SysQuartzJobEvent event) {
		SysQuartzJob job = event.getEvent();
		String executeType = event.getExecuteType();
		if(EXECUTE_INIT.equals(executeType)){
			
			asyncSysQuartzThread.clearAll();
			asyncSysQuartzThread.startSquartzJob();
			
		}else if(EXECUTE_ONLY.equals(executeType)){
			
			asyncSysQuartzThread.executeNow(job);	
			
		}else if(EXECUTE_QUARTZ.equals(executeType)){
			
			asyncSysQuartzThread.execute(job);	
			
		}
	}
	

}

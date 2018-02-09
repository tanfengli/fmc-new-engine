package com.vispractice.fmc.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.vispractice.fmc.base.SchedulerTaskBase;

public class TestJob extends SchedulerTaskBase {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	@Override
	public void start() {
		ThreadPoolTaskScheduler threadPoolTaskScheduler = SchedulerTaskBase.getThreadPoolTaskScheduler();
		this.scheduledFuture = threadPoolTaskScheduler.schedule(this,this.getTrigger());
	}

	@Override
	public void doRun() {
		System.out.println(">>>>>>>>>>>>>>>>>当前时间：" + sdf.format(new Date()) + ">>>>>>>>>>");
	}

	@Override
	public void restart() {
		ThreadPoolTaskScheduler threadPoolTaskScheduler = SchedulerTaskBase.getThreadPoolTaskScheduler();
		this.stop();
		this.scheduledFuture = threadPoolTaskScheduler.schedule(this,this.getTrigger());
	}
}

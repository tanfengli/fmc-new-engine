package com.vispractice.fmc.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.job.SysQuartzJob;
import com.vispractice.fmc.business.service.sys.workflow.ISysQuartzJobTaskService;
import com.vispractice.fmc.job.SysQuartzJobEvent;

@Service
@Profile("localEngine")
public class SysQuartzJobTaskServerImpl implements ISysQuartzJobTaskService, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	@Override
	public void submit(SysQuartzJob job,String executeType) throws Exception {
		applicationContext.publishEvent(new SysQuartzJobEvent(job,executeType));
	}
}

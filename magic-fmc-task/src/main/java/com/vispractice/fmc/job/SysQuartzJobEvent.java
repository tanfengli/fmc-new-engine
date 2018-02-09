package com.vispractice.fmc.job;

import org.springframework.context.ApplicationEvent;

import com.vispractice.fmc.business.entity.sys.job.SysQuartzJob;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfEvent;

public class SysQuartzJobEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SysQuartzJob sysQuartzJob;
	private String executeType;
	
	public SysQuartzJob getEvent() {  
        return sysQuartzJob;  
    }

	public SysQuartzJobEvent(SysQuartzJob sysQuartzJob,String executeType) {
		super(sysQuartzJob);
		this.sysQuartzJob = sysQuartzJob;
		this.executeType=executeType;
	}

	public String getExecuteType() {
		return executeType;
	}

	public void setExecuteType(String executeType) {
		this.executeType = executeType;
	}

}

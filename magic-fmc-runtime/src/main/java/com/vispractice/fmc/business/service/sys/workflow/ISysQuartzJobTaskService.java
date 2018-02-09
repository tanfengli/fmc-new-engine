package com.vispractice.fmc.business.service.sys.workflow;

import com.vispractice.fmc.business.entity.sys.job.SysQuartzJob;

public interface ISysQuartzJobTaskService {

	public void submit(SysQuartzJob job,String executeType) throws Exception;
	
//	public void sendToServer(Map<String, String> map, List<String> addressStringList);
	
	
}

package com.vispractice.fmc.business.service.sys.workflow;

import java.util.List;
import java.util.Map;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfEvent;

public interface IClusterService {
	
	public void submit(SysWfEvent event) throws Exception;
	
	public void sendToServer(Map<String, String> map, List<String> addressStringList);
	
}

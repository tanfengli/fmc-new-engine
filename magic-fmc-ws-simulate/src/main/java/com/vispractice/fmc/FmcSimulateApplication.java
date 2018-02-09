package com.vispractice.fmc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.vispractice.fmc.webservice.simulate.SimulateSrv;
import com.vispractice.fmc.webservice.support.SimulateHelper;

@SpringBootApplication
public class FmcSimulateApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(FmcSimulateApplication.class,args);
		SimulateSrv simulateSrv = applicationContext.getBean(SimulateSrv.class);
		List<Integer> taskList = new ArrayList<Integer>();
		int maxCount = 1000;
		
//		taskList.add(SimulateHelper.QUIRY_PROCESS_TASK);
//		taskList.add(SimulateHelper.START_PROCESS_TASK);
//		taskList.add(SimulateHelper.NODE_APPROVAL_TASK0);
		taskList.add(SimulateHelper.NODE_APPROVAL_TASK1);
		
		for(int i=0; i< taskList.size(); i++){
			int type = (int) taskList.get(i);
			switch(type){
				case 10:		// 查询
					for(int j=0; j< maxCount; j++)
						simulateSrv.inquiryTaskSrv();
					break;
				case 20:		//启动
					for(int j=0; j< maxCount; j++)
						simulateSrv.startProcessSrv(j);
					break;
				case 30:	//节点审批
					simulateSrv.nodeApprovalSrv("xuegx", "薛贵喜");
					break;
				case 31:	//节点审批
					simulateSrv.nodeApprovalSrv("lijy", "李金元");
					break;
					
			}
			
		}
		
	}
}

//package com.vispractice.fmc.business.services.simulator.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import com.vispractice.fmc.business.base.simulator.SimulateForm;
//import com.vispractice.fmc.business.entity.aboutmyself.VCmsTask;
//import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
//import com.vispractice.fmc.business.service.aboutmyself.IVCmsTaskService;
//import com.vispractice.fmc.business.service.sys.news.ISysNewsMainService;
//import com.vispractice.fmc.business.services.simulator.IWorkflowSimulateService;
//import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.WfProcessTaskItem;
//import com.vispractice.fmc.web.utils.simulator.WorkflowApproveSimulateUtil;
//import com.vispractice.fmc.web.utils.simulator.WorkflowStartSimulateUtil;
//import com.vispractice.fmc.web.utils.simulator.WorkflowTaskSimulateUtil;
//
//@Slf4j
//@Service
//public class WorkflowSimulateServiceImpl implements IWorkflowSimulateService {
//
//	//流程URL地址
//	private static String http;
//	//并发线程数
//	private static Integer threadNum;
//	//是否流程结束后启动新流程
//	private static boolean flag = false;
//	//最大
//	private static Integer maxNum;
//	
//	private static Integer count = 0;
//	
//	private static Boolean isApprove= true;
//	
//	
//	private static String templateId = "8a4cf3b85e3c9f2d015e3cf907310013";
//	
//	private static SimulateForm  paramForm;
//	
//	@Autowired
//	private ISysNewsMainService sysNewsMainService;	
//	@Autowired
//	private IVCmsTaskService vCmsTaskService; 
//	
//	
//	public void start(SimulateForm form) {
//		paramForm = form;
//		http = form.getHttp();
//		flag = form.getFlag();
//		count = 0;
//		maxNum = form.getMaxNum()==null?9999999:form.getMaxNum();
//		isApprove = form.getIsApprove();
//		if(flag){
//			log.info("---workflow test thread start ----");
//	    	for(int i=0;i<form.getThreadNum();i++ ){
//	    		 run();
//	    	}
//		}
//		
//	}
//
//	
//	 public void run(){
//	    	new Thread(new Runnable() {
//				@Override
//				public void run() {
//					while(flag&&count<maxNum){
//						try {
//							addNum();
//							Map<String,String> rstMap = WorkflowStartSimulateUtil.getInstance().start(http);
//							rstMap.put("templateId", templateId);
//							if(isApprove){
//								String[] handlerArr = paramForm.getHandlerIds().split(";");
//								String[] handlerNameArr = paramForm.getHandlerNames().split(";");
//								if(handlerArr!=null&&handlerArr.length>0){
//									for(int i=0;i<handlerArr.length;i++){
//										rstMap.put("userNo", handlerArr[i]);
//										rstMap.put("userName", handlerNameArr[i]);
//										rstMap.remove("applyCode");
//										rstMap.remove("wfInstanceId");
//										List<WfProcessTaskItem> list = WorkflowTaskSimulateUtil.getInstance().start(http, rstMap);
//										if(list!=null&&list.size()>0){
//											for(WfProcessTaskItem  item:list){
//												rstMap.put("applyCode", item.getWFAPPLYCODE());
//												rstMap.put("wfInstanceId", item.getWFINSTANCEID());
//												rstMap.put("tokenId", item.getWFTOKENID());
//												WorkflowApproveSimulateUtil.getInstance().start(http, rstMap);
//											}
//											
//										}
//									}
//								}
//								
//							}
//								
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
//					
//				}
//			}).start();
//	    	
//	    }
//	 
//	 public void approveTest(Map<String,String> rstMap){
//		 boolean isEnd = false;
//		 String wfInstanceId = rstMap.get("wfInstanceId");
//		 String wfApplyCode = rstMap.get("applyCode");
//		 log.info(wfInstanceId+"---"+wfApplyCode);
//		 while(!isEnd){
//			SysNewsMain sysNewsMain = sysNewsMainService.getByFdId(wfInstanceId);
//			
//			if("30".equals(sysNewsMain.getDocStatus())){
//				isEnd = true;
//			}else{
//				VCmsTask vCmsTask = new VCmsTask();
//				vCmsTask.setApplyCode(wfApplyCode);
//				vCmsTask.setFdTemplateId(templateId);
//				// 获取当前页和页大小
//				Pageable pageable = new PageRequest(0, 10);
//
//				Page<VCmsTask> page = vCmsTaskService.searchVCmsTask(vCmsTask, pageable);
//				if(page.getContent()!=null&&page.getContent().size()>0){
//					VCmsTask task = page.getContent().get(0);
//					WorkflowApproveSimulateUtil.getInstance().start(http, task);
//				}
//			}
//			
//		  
//		 }
//		 
//	 }  
//	 
//	 
//	 
//	 private synchronized void addNum(){
//		 count++;
//	 }
//	 
//	 private void queryTask(Map<String,String> rstMap){
//		 String wfInstanceId = rstMap.get("wfInstanceId");
//		 String wfApplyCode = rstMap.get("applyCode");
//		 
//	 }
//}

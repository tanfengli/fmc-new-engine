package com.vispractice.fmc.service.impl;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.utils.ProcessLogicHelper;
import com.vispractice.fmc.business.entity.callback.SysCallBackInfo;
import com.vispractice.fmc.business.entity.sys.config.SysInterfaceParam;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.service.callback.ISysCallBackInfoService;
import com.vispractice.fmc.business.service.sys.config.ISysInterfaceParamService;
import com.vispractice.fmc.business.service.sys.config.ISysParamService;
import com.vispractice.fmc.business.service.sys.job.ISysQuartzJobService;
import com.vispractice.fmc.webservice.callback.EasWorkflowCommonCallbackWebservice;
import com.vispractice.fmc.webservice.callback.IEasWorkflowCommonCallbackWebservice;
import com.vispractice.fmc.webservice.callback.WorkFlowParms;

@Service("sysCallBackService")
@Slf4j
@Transactional(propagation = Propagation.NEVER)
public class SysCallbackServiceImpl  {
	
	
	@Autowired
	private ISysCallBackInfoService sysCallBackInfoService;
	
	@Autowired
	private ISysParamService sysParamService;
	
	@Autowired
	private ISysInterfaceParamService sysWfInterfaceParamService;
	
//	@Value("${webservice.eas.callback.url}")
	private String webserviceUrl;
	
	@Autowired
	private ISysQuartzJobService sysQuartzJobService;
	
	private static final String WEBSERVICE_ADDR="webservice.eas.callback.url";
	/**
	 * 设置扩展参数
	 * @param model
	 * @return
	 */
	public static Map<String, String> setAttrs(SysNewsMain model){
		Map<String, String> attrs = new HashMap<String,String>();
		if(StringUtil.isNotNull(model.getAttr1()))
			attrs.put("__EXT_ATTR1", model.getAttr1());
		if(StringUtil.isNotNull(model.getAttr2()))
			attrs.put("__EXT_ATTR2", model.getAttr2());
		if(StringUtil.isNotNull(model.getAttr3()))
			attrs.put("__EXT_ATTR3", model.getAttr3());
		if(StringUtil.isNotNull(model.getAttr4()))
			attrs.put("__EXT_ATTR4", model.getAttr4());
		if(StringUtil.isNotNull(model.getAttr5()))
			attrs.put("__EXT_ATTR5", model.getAttr5());
		if(StringUtil.isNotNull(model.getAttr6()))
			attrs.put("__EXT_ATTR6", model.getAttr6());
		if(StringUtil.isNotNull(model.getAttr7()))
			attrs.put("__EXT_ATTR7", model.getAttr7());
		if(StringUtil.isNotNull(model.getAttr8()))
			attrs.put("__EXT_ATTR8", model.getAttr8());
		if(StringUtil.isNotNull(model.getAttr9()))
			attrs.put("__EXT_ATTR9", model.getAttr9());
		if(StringUtil.isNotNull(model.getAttr10()))
			attrs.put("__EXT_ATTR10", model.getAttr10());
		if(StringUtil.isNotNull(model.getAttr11()))
			attrs.put("__EXT_ATTR11", model.getAttr11());
		if(StringUtil.isNotNull(model.getAttr12()))
			attrs.put("__EXT_ATTR12", model.getAttr12());
		if(StringUtil.isNotNull(model.getAttr13()))
			attrs.put("__EXT_ATTR13", model.getAttr13());
		if(StringUtil.isNotNull(model.getAttr14()))
			attrs.put("__EXT_ATTR14", model.getAttr14());
		if(StringUtil.isNotNull(model.getAttr15()))
			attrs.put("__EXT_ATTR15", model.getAttr15());
		if(StringUtil.isNotNull(model.getAttr16()))
			attrs.put("__EXT_ATTR16", model.getAttr16());
		if(StringUtil.isNotNull(model.getAttr17()))
			attrs.put("__EXT_ATTR17", model.getAttr17());
		if(StringUtil.isNotNull(model.getAttr18()))
			attrs.put("__EXT_ATTR18", model.getAttr18());
		if(StringUtil.isNotNull(model.getAttr19()))
			attrs.put("__EXT_ATTR19", model.getAttr19());
		if(StringUtil.isNotNull(model.getAttr20()))
			attrs.put("__EXT_ATTR20", model.getAttr20());
		return attrs;
	}
	
	
	@SuppressWarnings("unchecked")
	@Async("sysQuartzJob") 
	@Transactional
	public void runJob(){
		List<SysCallBackInfo> sysCallBackInfos = sysCallBackInfoService.querySysCallBackInfoList();
		
		for(SysCallBackInfo sysCallBackInfo:sysCallBackInfos){
			SysInterfaceParam param = sysWfInterfaceParamService.findByFdInterfaceIdAndFdName(sysCallBackInfo.getSystemId(),WEBSERVICE_ADDR);
			webserviceUrl = param.getFdValue();
			WorkFlowParms info = new WorkFlowParms();
			log.info("-----业务系统回调处理---"+sysCallBackInfo.getApplyCode()+"------");
			try{
				List<?> rtnVal = ProcessLogicHelper.objectXmlDecoder(sysCallBackInfo.getVarXml());
				List<?> rtnAttr = ProcessLogicHelper.objectXmlDecoder(sysCallBackInfo.getAttrXml());
//						ObjectXML.objectXMLDecoderByString(callBackInfo.getVarXml());
				Map<String,String> attrsMap = (Map<String, String>)rtnAttr.get(0);
				Map<String,String> varMap = (Map<String, String>)rtnVal.get(0);
				WorkFlowParms.Attrs attrs = new WorkFlowParms.Attrs();
				for (Map.Entry<String, String> entry : attrsMap.entrySet()) {
					WorkFlowParms.Attrs.Entry e = new WorkFlowParms.Attrs.Entry();
					e.setKey(entry.getKey());
					e.setValue(entry.getValue());
					attrs.getEntry().add(e);
				}
				WorkFlowParms.Vars vars = new WorkFlowParms.Vars();
				for (Map.Entry<String, String> entry : varMap.entrySet()) {
					WorkFlowParms.Vars.Entry e = new WorkFlowParms.Vars.Entry();
					e.setKey(entry.getKey());
					e.setValue(entry.getValue());
					vars.getEntry().add(e);
				}
				BeanUtils.copyProperties(info, sysCallBackInfo);
				info.setAttrs(attrs);
				info.setVars(vars);
//					
				WorkFlowParms.Attrs.Entry e2 = new WorkFlowParms.Attrs.Entry();
				e2.setKey("fdId");
				e2.setValue(sysCallBackInfo.getFdId());
				info.getAttrs().getEntry().add(e2);
				
				
				boolean flag = send(info);
				sysCallBackInfo.setFdUpdateTime(new Date());
				Integer sendNum = sysCallBackInfo.getSendNum();
				if(sendNum==null){
					sendNum = 1;
				}else{
					sendNum+=1;
				}
				sysCallBackInfo.setSendNum(sendNum);
				sysCallBackInfo.setIsSuccess(flag);
				save(sysCallBackInfo);
				
//					delete(callBackInfo);
			} catch (Exception e) {
				log.error(sysCallBackInfo.getApplyCode() + "再次调用回调任务错误!", e);
				e.printStackTrace();
				return;
			}
		}
		log.info("-----业务系统回调处理结束---");
	}
		
	
	public boolean send(WorkFlowParms info) throws Exception {
		int count=1;
		boolean isSeccuess = true;
		//最多回调3次，每次间隔5秒，如果3秒还没有成功，第二天再回调3次，定时任务处理
		log.info(info.getApplyCode() + "开始第"+count+"次回调！");
		while(count<=3&&!this.sendMsg(info)){
			count++;

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				log.error(info.getApplyCode()+"线程休眠错误！", e1);
				e1.printStackTrace();
				throw e1;
			}
			if(count>3){
				try {
					isSeccuess = false;
					
					log.info(info.getApplyCode() + "保存再调用任务成功！");
				} catch (Exception e) {
					log.error("保存次日重调任务出错！", e);
					throw e;
				}
			}else{
				log.info(info.getApplyCode() + "开始第"+count+"次回调！");
			}

		}
		if(isSeccuess){
			log.info(info.getApplyCode() + "回调成功！");
		}
		return isSeccuess;
	}
	
	
	@SuppressWarnings("unused")
	private boolean sendMsg(WorkFlowParms info) throws Exception {
		boolean isSeccuess=true;
		try{
//			List<String> list = new ArrayList<String>();
//			list.add("");
//			String url = "";
//			List<SysParam> params = sysParamService.findByFdCodeIn(list);
//			if(params!=null&&params.size()>0){
//				url = params.get(0).getFdValue();
//			}
			 
			URL	callbackWebserviceUrl = new URL(webserviceUrl);
//			 
			EasWorkflowCommonCallbackWebservice ss = new EasWorkflowCommonCallbackWebservice(callbackWebserviceUrl);
			IEasWorkflowCommonCallbackWebservice port = ss.getEasWorkflowCommonCallbackWebserviceImplPort(); 
			String reps = port.nodeComplementCallback(info);
			if(reps==null){
				return false;
			}
//			JSONObject json = new JSONObject(reps);
//			if("Y".equals(json.getString("success"))){
//				return true;
//			}else{
//				return false;
//			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(info.getApplyCode() + "回调失败:", e);
			isSeccuess=false;
		}
		return isSeccuess;
	}
	

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void save(SysCallBackInfo sysCallBackInfo){
		sysCallBackInfoService.save(sysCallBackInfo);	
	}
}

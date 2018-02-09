package com.vispractice.fmc.webservice.service.impl;

import java.util.Date;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import com.vispractice.fmc.base.utils.ResourceUtil;
import com.vispractice.fmc.business.entity.sys.webservice.SysWebServiceLog;
import com.vispractice.fmc.business.service.sys.config.ISysWfInterfaceService;
import com.vispractice.fmc.business.service.sys.facade.IProcessFacade;
import com.vispractice.fmc.business.service.sys.facade.IValidateFacade;
import com.vispractice.fmc.business.service.sys.webservice.IWebServiceService;
import com.vispractice.fmc.csb.msgheader.MsgHeader;
import com.vispractice.fmc.webservice.service.FmcMsgHeaderSrv;

public abstract class AbstractStubParentSrv {
	/**
	 * 消息头部验证服务
	 */
	@Autowired
	protected FmcMsgHeaderSrv msgHeaderSrv;
	
	/**
	 * 流程服务接口
	 */
	@Autowired
	protected IProcessFacade processFacade; 
	
	/**
	 * 数据验证服务
	 */
	@Autowired
	protected IValidateFacade validateFacade;
	
	/**
	 * 接口查看信息
	 */
	@Autowired
	protected ISysWfInterfaceService sysWfInterfaceService;
	
	/**
	 * 国际化服务
	 */
	@Autowired
	protected ResourceUtil resourceUtil;
	
	/**
	 * 语言
	 */
	protected Locale language = Locale.SIMPLIFIED_CHINESE;
	
	/**
	 * 接口日志服务
	 */
	@Autowired
	protected IWebServiceService webServiceService;
	
	/**
	 * 保存接口服务日志
	 * @param fdStartTime
	 * @param fdEndTime
	 * @param msgHeader
	 * @param response
	 */
	protected void saveWebServiceLog(String serviceName,String beanName,MsgHeader msgHeader,
		Date fdStartTime,Date fdEndTime,String result,String message) {
		SysWebServiceLog sysWebServiceLog = new SysWebServiceLog();
		sysWebServiceLog.setFdServiceName(serviceName);
		sysWebServiceLog.setFdServiceBean(beanName);
		sysWebServiceLog.setFdUserName(msgHeader.getUSERNAME());
		sysWebServiceLog.setFdClientIp(msgHeader.getSOURCESYSTEMID());
		sysWebServiceLog.setFdStartTime(fdStartTime);
		sysWebServiceLog.setFdEndTime(fdEndTime);
		sysWebServiceLog.setFdExecResult(result);
		sysWebServiceLog.setFdErrorMsg(message);
		sysWebServiceLog.setFdRunTime(fdEndTime.getTime()-fdStartTime.getTime());
		
		webServiceService.saveWebServiceLog(sysWebServiceLog);
	}
}

package com.vispractice.fmc.webservice.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.config.SysBusiSys;
import com.vispractice.fmc.business.entity.sys.config.SysWfInterface;
import com.vispractice.fmc.csb.msgheader.MsgHeader;
import com.vispractice.fmc.webservice.service.FmcMsgHeaderSrv;

@Service
public class StubFmcMsgHeader extends AbstractStubParentSrv implements FmcMsgHeaderSrv {
	/**
	 * 验证信息头部信息合理性
	 */
	@Override
	public SysBusiSys validateSysData(MsgHeader header,String sysWfInterfaceCode) throws WorkflowException {
		SysBusiSys sysBusiSys = null; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		
		// 业务系统校验
		if (StringUtils.isEmpty(header.getSOURCESYSTEMID())) {
			validateFacade.validateBusiSystem("");
		} else {
			sysBusiSys = validateFacade.validateBusiSystem(header.getSOURCESYSTEMID());
		}
		
		SysWfInterface sysWfInterface = sysWfInterfaceService.findByBusiCodeAndInterfaceCode(header.getSOURCESYSTEMID(),sysWfInterfaceCode);
		if (sysWfInterface == null) {
			throw new WorkflowException(resourceUtil.getLocaleMessage("ws.validate.interface.busisys",language));
		}
		
		// 单据人员校验
		if (StringUtils.isEmpty(header.getUSERID())) {
			validateFacade.validateSysOrgElement("");
		} else {
			validateFacade.validateSysOrgElement(header.getUSERID());
		}
		
		// 设置默认页面大小为
		if (null == header.getPAGESIZE() 
			|| header.getPAGESIZE().compareTo(new BigDecimal(1)) < 0
			|| header.getPAGESIZE().compareTo(new BigDecimal(100)) > 0) {
			header.setPAGESIZE(new BigDecimal(10));
		}
		
		if (null == header.getCURRENTPAGE() || header.getPAGESIZE().compareTo(new BigDecimal(1)) < 0) {
			header.setCURRENTPAGE(new BigDecimal(1));
		}
		
		// 单据日期校验
		if (null != header.getSUBMITDATE()) {
			String sumbitDate = header.getSUBMITDATE().toXMLFormat();
			try {
				sdf.parse(sumbitDate);
			} catch (ParseException e) {
				throw new WorkflowException(resourceUtil.getLocaleMessage("ws.validate.header.time",language));
			}
		}
		
		return sysBusiSys;
	}

}

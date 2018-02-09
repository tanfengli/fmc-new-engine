package com.vispractice.fmc.webservice.util;

import com.vispractice.fmc.business.base.constrant.SysDocConstant;

/**
 * 
 * 编  号：
 * 名  称：CodeToNameUtil
 * 描  述：各种编码转化为名称的方法
 * 完成日期：2017年5月16日 下午4:43:42
 * 编码作者："LaiJiashen"
 */

public class CodeToNameUtil {
	
	/**
	 * 
	 * 实现流程:流程状态编码转化为名称<br/>
	 * 1.XXX<br/>
	 * @Title: docStatusTran 
	 * @param statusCode
	 * @return
	 */
	public static String docStatusTran(String statusCode){
		
		String wfStatus = "";
		
		switch (statusCode) {
		case SysDocConstant.DOC_STATUS_DISCARD:
			wfStatus = "废弃";
			break;
		case SysDocConstant.DOC_STATUS_DRAFT:
			wfStatus = "草稿";
			break;
		case SysDocConstant.DOC_STATUS_EXAMINE:
			wfStatus = "审批中";
			break;
		case SysDocConstant.DOC_STATUS_REFUSE:
			wfStatus = "审批驳回";
			break;
		case SysDocConstant.DOC_STATUS_PUBLISH:
			wfStatus = "发布";
			break;
		case SysDocConstant.DOC_STATUS_EXPIRE:
			wfStatus = "过期";
			break;
		}
		
		return wfStatus;
		
	}

}

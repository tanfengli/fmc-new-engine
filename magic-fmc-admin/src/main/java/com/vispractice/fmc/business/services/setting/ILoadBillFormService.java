package com.vispractice.fmc.business.services.setting;

import com.vispractice.fmc.business.base.domain.SbWFApprovalForm;
import com.vispractice.fmc.business.base.domain.SysWfBusinessForm;
import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;

/**
 * 名  称：ILoadBillFormService
 * 描  述：用于加载单据详情form的服务
 * 完成日期：2017年7月7日 下午3:36:11
 * 编码作者：ZhouYanyao
 */
public interface ILoadBillFormService {
	/**
	 * 初始化业务单据信息
	 * @param wfInstanceId
	 * @param currUserId
	 * @return
	 * @throws Exception 
	 */
	public SysWfBusinessForm initForm(String fdTemplateId,SysOrgPerson currPerson) throws Exception;
	
	/**
	 * 加载业务单据信息
	 * @param wfInstanceId
	 * @param currUserId
	 * @return
	 * @throws Exception 
	 */
	public SysWfBusinessForm loadForm(String wfInstanceId,SysOrgPerson currPerson) throws Exception;

	/**
	 * 加载审批单据信息
	 * @param wfInstanceId
	 * @param wfNodeId
	 * @param currPerson
	 * @return
	 * @throws Exception
	 */
	public SbWFApprovalForm initApproveForm(String wfInstanceId,String wfNodeId,SysOrgPerson currPerson,String identify) throws Exception;
}

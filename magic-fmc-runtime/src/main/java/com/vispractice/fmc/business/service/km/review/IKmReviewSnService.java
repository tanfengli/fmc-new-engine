package com.vispractice.fmc.business.service.km.review;

import com.vispractice.fmc.business.entity.km.review.KmReviewSn;


public interface IKmReviewSnService {

	/**
	 * 
	 * 生成流水号<br/>
	 * @param snContext 流水号实体
	 * @return 单据编号
	 * @throws Exception
	 */
	public String getSerialNumber(KmReviewSn snContext) throws Exception;

}

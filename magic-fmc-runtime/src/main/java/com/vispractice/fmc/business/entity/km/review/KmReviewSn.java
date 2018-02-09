package com.vispractice.fmc.business.entity.km.review;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 编 号： 名 称：KmReviewSn 描 述：流水号实体 完成日期：2017年6月15日 下午2:07:15 编码作者："LaiJiashen"
 */
@Data
@Entity
@Table(name = "KM_REVIEW_SN")
public class KmReviewSn {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;

	/**
	 * 最大号
	 */
	@Column(name = "FD_MAX_NUMBER")
	private Long fdMaxNumber;

	/**
	 * 日期
	 */
	@Column(name = "FD_DATE")
	private String fdDate;

	/**
	 * 模块名
	 */
	@Column(name = "FD_MODEL_NAME")
	private String fdModelName;

	/**
	 * 模板ID
	 */
	@Column(name = "FD_TEMPLATE_ID")
	private String fdTemplateId;

	/**
	 * 流水号前缀
	 */
	@Column(name = "FD_PREFIX")
	private String fdPrefix;

	@Column(name = "AUTH_AREA_ID")
	private String authAreaId;

}

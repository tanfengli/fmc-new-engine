package com.vispractice.fmc.business.entity.sys.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@Table(name = "SYS_PARAM")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysParam {

	@Id
	@Column(name = "FD_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String fdId;
	
	@Column(name = "FD_CODE")
	private String fdCode;

	@Column(name = "FD_NAME")
	private String fdName;
	
	@Column(name = "FD_VALUE")
	private String fdValue;
	
	@Column(name = "FD_DESCRIPTION")
	private String fdDescription;
	
	@Column(name = "FD_PARENT_ID")
	private String fdParentId;
}

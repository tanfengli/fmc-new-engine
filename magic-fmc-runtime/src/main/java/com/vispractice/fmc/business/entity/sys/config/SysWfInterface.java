package com.vispractice.fmc.business.entity.sys.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@Table(name = "SYS_WF_INTERFACE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysWfInterface {
	@Id
	@Column(name = "FD_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String fdId;
	
	@Column(name = "FD_URL")
	private String fdUrl;
	
	@Column(name = "FD_CODE")
	private String fdCode;

	@Column(name = "FD_NAME")
	private String fdName;
	
	@Column(name = "FD_PARENT_ID")
	private String fdParentId;
	
	@Column(name="FD_IS_LEAF")
	private Long fdIsLeaf;
	
	public String getIsParent(){
		return null == this.fdIsLeaf || this.fdIsLeaf == 1 ? "false" : "true";
	};
}
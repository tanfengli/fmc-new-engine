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
@Table(name = "SYS_WF_BUSI_INTERFACE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysWfBusiInterface {
	@Id
	@Column(name = "FD_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String fdId;
	
	@Column(name = "FD_BUSI_ID")
	private String fdBusiId;

	@Column(name = "FD_INTERFACE_ID")
	private String fdInterfaceId;
	
	@Column(name = "FD_IS_BACK")
	private String fdIsBack;
	
	@Column(name = "FD_BACK_ADDRESS")
	private String fdBackAddress;
	
	@Column(name = "FD_BUSI_TYPE")
	private String fdBusiType;
}

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
@Table(name = "SYS_WF_INTERFACE_PARAM")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysInterfaceParam {
	@Id
	@Column(name = "FD_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String fdId;
	
	@Column(name = "FD_INTERFACE_ID")
	private String fdInterfaceId;

	@Column(name = "FD_NAME")
	private String fdName;
	
	@Column(name = "FD_VALUE")
	private String fdValue;
}

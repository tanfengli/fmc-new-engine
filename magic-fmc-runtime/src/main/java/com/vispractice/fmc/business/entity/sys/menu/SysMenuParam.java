package com.vispractice.fmc.business.entity.sys.menu;

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
@Table(name = "SYS_MENU_PARAM")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysMenuParam {
	@Id
	@Column(name = "FD_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String fdId;
	
	@Column(name = "FD_MENU_ID")
	private String fdMenuId;

	@Column(name = "FD_NAME")
	private String fdName;
	
	@Column(name = "FD_VALUE")
	private String fdValue;
}

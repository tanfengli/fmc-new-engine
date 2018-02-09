package com.vispractice.fmc.business.entities.setting.view;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name="ROLE_CATE_V")
public class RoleCateV {
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid",strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_NAME")
	private String fdName;
	
	@Column(name="CREATOR_NAME")
	private String creatorName;
	
	@Column(name="CREATE_TIME")
	private Date createTime;
}

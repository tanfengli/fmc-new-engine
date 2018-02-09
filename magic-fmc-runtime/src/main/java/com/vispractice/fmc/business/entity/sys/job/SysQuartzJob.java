package com.vispractice.fmc.business.entity.sys.job;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name="SYS_QUARTZ_JOB")
@NamedQuery(name="SysQuartzJob.findAll", query="SELECT s FROM SysQuartzJob s")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysQuartzJob implements Serializable{
	private static final long serialVersionUID = 1L; 
	 
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	
	private String fdModelName;
	
	private String fdModelId;
	
	private String fdKey;
	
	private String fdSubject;
	
	private String fdJobService;
	
	private String fdJobMethod;
	
	private String fdLink;
	
	private String fdParameter;
	
	private String fdCronExpression;
	
	private boolean fdEnabled;
	
	private boolean fdIsSysJob;
	
	private Integer fdRunType;
	
	private String fdRunServer;
	
	private Timestamp fdRunTime;
	
	private boolean fdRequired;
}

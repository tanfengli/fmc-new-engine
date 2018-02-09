package com.vispractice.fmc.business.entities.setting.view;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name="ROLE_ASSIGN_V")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RoleAssignV {
	
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_NAME")
	private String fdName;
	
	@Column(name="FD_DESCRIPTION")
	private String fdDescription;
	
	@Column(name="CATEGORY_ID")
	private String categoryId;
	
	@Column(name="CATEGORY_NAME")
	private String categoryName;
	
	@Column(name="CREATOR_NAME")
	private String creatorName;
	
	@Transient
	private List<String> authArray;
}

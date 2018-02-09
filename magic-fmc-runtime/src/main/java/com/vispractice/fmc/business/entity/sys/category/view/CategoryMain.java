package com.vispractice.fmc.business.entity.sys.category.view;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CATEGORYMAIN_V")
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class CategoryMain {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_NAME")
	private String fdName;

	@Column(name = "FD_PARENT_ID")
	private String fdParentId;
	
	@Column(name="FD_HIERARCHY_ID")
	private String fdHierarchyId;

	@Column(name = "creator_name")
	private String ceratorName;

	@Column(name = "CREATE_TIME", columnDefinition = "date")
	private Date createTime;

	@Column(name = "alter_name")
	private String alterName;

	@Column(name = "ALTER_TIME", columnDefinition = "date")
	private Date alterTime;

}

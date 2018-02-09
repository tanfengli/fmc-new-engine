package com.vispractice.fmc.business.entity.sys.category;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vispractice.fmc.business.entity.sys.news.SysNewsTemplate;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;

@Entity
@Data
@Table(name = "SYS_CATEGORY_MAIN")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysCategoryMain implements Comparable<SysCategoryMain>,Cloneable {
	@Id
	@Column(name = "FD_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String fdId;

	@Column(name = "FD_NAME")
	private String fdName;

	@Column(name = "FD_HIERARCHY_ID")
	private String fdHierarchyId;

	@Column(name = "FD_ORDER")
	private Long fdOrder;

	@Column(name = "FD_MODEL_NAME")
	private String fdModelName;

	@Column(name = "DOC_CREATE_TIME")
	private Date docCreateTime;

	@Column(name = "DOC_ALTER_TIME")
	private Date docAlterTime;

	@Column(name = "FD_ISINHERIT_MAINTAINER")
	private int fdIsinheritMaintainer;

	@Column(name = "FD_ISINHERIT_USER")
	private int fdIsinheritUser;

	@Column(name = "AUTH_READER_FLAG")
	private int authReaderFlag;

	@Column(name = "DOC_CREATOR_ID")
	private String docCreateId;

	@Column(name = "DOC_ALTEROR_ID")
	private String docAlterorId;

	@Column(name = "FD_PARENT_ID")
	private String fdParentId;

	@Column(name = "AUTH_AREA_ID")
	private String authAreaId;
	
	@Column(name="FD_IS_LEAF")
	private Long fdIsLeaf;

	@Transient
	private List<SysOrgElement> editorArray;

	@Transient
	private List<SysOrgElement> readerArray;

	@Transient
	private List<SysCategoryMain> categoryChildren;

	@Transient
	private List<SysNewsTemplate> associateModels;
	
	public String getIsParent(){
		return null==this.fdIsLeaf||this.fdIsLeaf==1?"false":"true";
	};
	

	@Override
	public int compareTo(SysCategoryMain o) {
		return o.getFdHierarchyId().length() - this.getFdHierarchyId().length();
	}
	
	@Override
	public Object clone(){
		try {
			return (SysCategoryMain)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

}

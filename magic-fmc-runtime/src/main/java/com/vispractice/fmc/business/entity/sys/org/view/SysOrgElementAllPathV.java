package com.vispractice.fmc.business.entity.sys.org.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "sys_Org_Element_allpath_v")
@NamedQuery(name="SysOrgElementAllPathV.findAll", query="SELECT p FROM SysOrgElementAllPathV p")
public class SysOrgElementAllPathV implements Cloneable{

	@Id
	private String fdId;
	
	@Column(name="name_zh")
	private String nameZh;
	
	@Column(name="fd_parentid")
	private String fdParentId;
	
	@Column(name="fd_org_type")
	private String fdOrgType;

	@Column(name="org_id")
	private String orgId;
	
	@Column(name="fd_is_available")
	private Long fdIsAvailable;
	
	@Transient
	private String description;
	
	@Override
	public Object clone() {    
		SysOrgElementAllPathV o = null;    
        try {    
            o = (SysOrgElementAllPathV) super.clone();    
        } catch (CloneNotSupportedException e) {    
            e.printStackTrace();    
        }    
        return o;
    }  
	
}

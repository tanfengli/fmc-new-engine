package com.vispractice.fmc.business.entity.sys.org;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.vispractice.fmc.base.utils.StringUtil;

import lombok.Data;

@Entity
@Data
@Table(name = "SYS_ORG_ROLE")
@NamedQuery(name = "SysOrgRole.findAll", query = "SELECT s FROM SysOrgRole s")
public class SysOrgRole implements Serializable,Cloneable{ 
	
	private static final long serialVersionUID = 1L; 
	
	@Id    
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_PLUGIN")
	private String fdPlugin;
	
	@Column(name="FD_PARAMETER")
	private String fdParameter;
	
	@Column(name="FD_IS_MULTIPLE")
	private String fdIsMultiple;
	
	@Column(name="FD_RTN_VALUE")
	private String fdRtnValue;
	
	@Column(name="FD_ROLE_CONF_ID")
	private String fdRoleConfId;
	
	@Transient
	private SysOrgElement element;
	
	@Override
	public Object clone() {    
		SysOrgRole o = null;    
        try {    
            o = (SysOrgRole) super.clone();    
        } catch (CloneNotSupportedException e) {    
            e.printStackTrace();    
        }    
        return o;
    }   
	

	public String getParameter(String name) {
		return StringUtil.getParameter(fdParameter, name);
	}
	
}

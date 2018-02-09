package com.vispractice.fmc.business.entities.sys.view;

import java.io.Serializable; 

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table; 

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Position_Managemet_V")
@NamedQuery(name="PositionManagemetV.findAll", query="SELECT p FROM PositionManagemetV p")
public class PositionManagemetV implements Serializable { 

	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_NAME")
	private String fdName;
	
	@Column(name="FD_PLUGIN")
	private String fdPlugin;
	
	@Column(name="FD_PARAMETER")
	private String fdParameter;
	 
	@Column(name="FD_RTN_VALUE")
	private String fdRtnValue;
	
	@Column(name="FD_IS_MULTIPLE")
	private String fdIsMultiple; //0是false，1是true
	
	@Column(name="FD_MEMO")
	private String fdMemo;
	
	@Column(name="FD_IS_AVAILABLE")
	private Long fdIsAvailable;

	public String getFdId() {
		return fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public String getFdName() {
		return fdName;
	}

	public void setFdName(String fdName) {
		this.fdName = fdName;
	}

	public String getFdPlugin() {
		return fdPlugin;
	}

	public void setFdPlugin(String fdPlugin) {
		this.fdPlugin = fdPlugin;
	}

	public String getFdParameter() {
		return fdParameter;
	}

	public void setFdParameter(String fdParameter) {
		this.fdParameter = fdParameter;
	}

	public String getFdRtnValue() {
		return fdRtnValue;
	}

	public void setFdRtnValue(String fdRtnValue) {
		this.fdRtnValue = fdRtnValue;
	} 
	 
	public String getFdIsMultiple() {
		return fdIsMultiple;
	}

	public void setFdIsMultiple(String fdIsMultiple) {
		this.fdIsMultiple = fdIsMultiple;
	}

	public String getFdMemo() {
		return fdMemo;
	}

	public void setFdMemo(String fdMemo) {
		this.fdMemo = fdMemo;
	}

	public Long getFdIsAvailable() {
		return fdIsAvailable;
	}

	public void setFdIsAvailable(Long fdIsAvailable) {
		this.fdIsAvailable = fdIsAvailable;
	}
}

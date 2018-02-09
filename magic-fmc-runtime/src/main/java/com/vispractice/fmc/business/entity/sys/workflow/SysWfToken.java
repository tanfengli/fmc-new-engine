package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.vispractice.fmc.business.base.constrant.BaseTreeConstant;
import com.vispractice.fmc.business.base.utils.IDGenerator;

/**
 * 
 * @ClassName SysWfToken 流程令牌实例对象
 * @author sky
 * @Date May 16, 2017
 * @version 1.0.0
 */
@Entity
@Table(name = "SYS_WF_TOKEN")
@NamedQuery(name = "SysWfToken.findAll", query = "SELECT s FROM SysWfToken s")
public class SysWfToken implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String ACTIVE = "active", // 
			INACTIVE = "inactive", //
			ENDED = "ended", //
			CREATED = "created", //
			ASYNC = "async", //
			TRANSFER = "transfer"; //
	
	@Id
	@GeneratedValue(generator = "assigned")
	@GenericGenerator(name = "assigned", strategy = "assigned")
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_FROM_NODE_ID")
	private String fdFromNodeId;

	@Column(name = "FD_HIERARCHY_ID")
	private String fdHierarchyId = BaseTreeConstant.HIERARCHY_ID_SPLIT + getFdId()
			+ BaseTreeConstant.HIERARCHY_ID_SPLIT;

	@Column(name = "FD_KEY")
	private String fdKey;

	@Column(name = "FD_NAME")
	private String fdName;

	@Column(name = "FD_NODE_FACT_ID")
	private String fdNodeFactId;

	@Column(name = "FD_NODE_INSTANCE_ID")
	private String fdNodeInstanceId;

	@Column(name = "FD_PARENT_ID")
	private String fdParentId;

	@Column(name = "FD_PROCESS_ID")
	private String fdProcessId;

	@Column(name = "FD_STATUS")
	private String fdStatus;

	@Column(name = "FD_TARGET_NODE_ID")
	private String fdTargetNodeId;

	public SysWfToken() {
	}

	public String getFdId() {
		if (fdId == null) {
			fdId = IDGenerator.generateID();
		}
		return this.fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public String getFdFromNodeId() {
		return this.fdFromNodeId;
	}

	public void setFdFromNodeId(String fdFromNodeId) {
		this.fdFromNodeId = fdFromNodeId;
	}

	public String getFdHierarchyId() {
		return this.fdHierarchyId;
	}

	public void setFdHierarchyId(String fdHierarchyId) {
		this.fdHierarchyId = fdHierarchyId;
	}

	public String getFdKey() {
		return this.fdKey;
	}

	public void setFdKey(String fdKey) {
		this.fdKey = fdKey;
	}

	public String getFdName() {
		return this.fdName;
	}

	public void setFdName(String fdName) {
		this.fdName = fdName;
	}

	public String getFdNodeFactId() {
		return this.fdNodeFactId;
	}

	public void setFdNodeFactId(String fdNodeFactId) {
		this.fdNodeFactId = fdNodeFactId;
	}

	public String getFdNodeInstanceId() {
		return this.fdNodeInstanceId;
	}

	public void setFdNodeInstanceId(String fdNodeInstanceId) {
		this.fdNodeInstanceId = fdNodeInstanceId;
	}

	public String getFdParentId() {
		return this.fdParentId;
	}

	public void setFdParentId(String fdParentId) {
		this.fdParentId = fdParentId;
	}

	public String getFdProcessId() {
		return this.fdProcessId;
	}

	public void setFdProcessId(String fdProcessId) {
		this.fdProcessId = fdProcessId;
	}

	public String getFdStatus() {
		return this.fdStatus;
	}

	public void setFdStatus(String fdStatus) {
		this.fdStatus = fdStatus;
	}

	public String getFdTargetNodeId() {
		return this.fdTargetNodeId;
	}

	public void setFdTargetNodeId(String fdTargetNodeId) {
		this.fdTargetNodeId = fdTargetNodeId;
	}

}
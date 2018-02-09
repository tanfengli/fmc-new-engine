package com.vispractice.fmc.business.entity.sys.cluster;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="SYS_CLUSTER_DISPATCHER")
@NamedQuery(name="SysClusterDispatcher.findAll", query="SELECT s FROM SysClusterDispatcher s")
public class SysClusterDispatcher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="FD_ID")
	private String fdId;

	@Column(name="FD_DISPATCHER_KEY")
	private String fdDispatcherKey;

	@Column(name="FD_SERVER_KEY")
	private String fdServerKey;

	public String getFdId() {
		return fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public String getFdDispatcherKey() {
		return fdDispatcherKey;
	}

	public void setFdDispatcherKey(String fdDispatcherKey) {
		this.fdDispatcherKey = fdDispatcherKey;
	}

	public String getFdServerKey() {
		return fdServerKey;
	}

	public void setFdServerKey(String fdServerKey) {
		this.fdServerKey = fdServerKey;
	}
}
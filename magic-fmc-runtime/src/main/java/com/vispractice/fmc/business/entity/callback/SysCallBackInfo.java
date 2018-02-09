package com.vispractice.fmc.business.entity.callback;

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
@Table(name = "sys_call_back_info")
@NamedQuery(name="SysCallBackInfo.findAll", query="SELECT s FROM SysCallBackInfo s")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysCallBackInfo  {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;
	
	@Column(name = "APPLYCODE")
	protected String applyCode;
	@Column(name = "ATTRXML")
    protected String attrXml;
	@Column(name = "BIZCODE")
    protected String bizCode;
	@Column(name = "BIZNAME")
    protected String bizName;
	@Column(name = "SERVICENAME")
    protected String callBackServiceName;
	@Column(name = "INSTANCEID")
    protected String instanceId;
	@Column(name = "NODEID")
    protected String nodeId;
	@Column(name = "NODENAME")
    protected String nodeName;
	@Column(name = "OPRCODE")
    protected String oprCode;
	@Column(name = "OPRNAME")
    protected String oprName;
	@Column(name = "STATUSCODE")
    protected String statusCode;
	@Column(name = "STATUSNAME")
    protected String statusName;
	@Column(name = "subject")
    protected String subject;
	@Column(name = "SYSTEMCODE")
    protected String systemCode;
	@Column(name = "SYSTEMID")
    protected String systemId;
	@Column(name = "SYSTEMNAME")
    protected String systemName;
	@Column(name = "TARGETBIZCODE")
    protected String targetBizCode;
	@Column(name = "TARGETBIZNAME")
    protected String targetBizName;
	@Column(name = "TARGETNODEID")
    protected String targetNodeId;
	@Column(name = "TARGETNODENAME")
    protected String targetNodeName;
	@Column(name = "TARGETAPPROVERNOS")
    protected String targetApproverNos;
	@Column(name = "TARGETAPPROVERNAMES")
    protected String targetApproverNames;
	@Column(name = "TARGETAPPROVERPOSTNOS")
    protected String targetApproverPostNos;
	@Column(name = "VARXML")
    protected String varXml;
	@Column(name = "FDCREATETIME")
    protected java.util.Date fdCreateTime; 
	@Column(name = "FDUPDATETIME")
    protected java.util.Date fdUpdateTime; 
	@Column(name = "SENDNUM")
    protected Integer sendNum;
	@Column(name = "ISSUCCESS")
    protected Boolean isSuccess; 
    
	
}

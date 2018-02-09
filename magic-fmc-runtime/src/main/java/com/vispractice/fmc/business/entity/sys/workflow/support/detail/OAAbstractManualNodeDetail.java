package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import java.util.ArrayList;
import java.util.List;

import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.AbstractNodeDetail;

public abstract class OAAbstractManualNodeDetail extends AbstractNodeDetail implements
		OAConstant {
	/**
	 * 通过方式 0全部，1按比例
	 */
	protected int passType;
	
	/**
	 * 通过方式为按比例时比例值
	 */
	protected double passPercent;
	
	protected int optHandlerCalType;

	protected boolean useOptHandlerOnly;

	protected int processType;

	protected boolean ignoreOnHandlerEmpty;

	protected boolean ignoreOnHandlerSame;//身份重复不跳过
	
	protected boolean ignoreOnSerialHandlerSame;//连续身份重复不跳过

	protected boolean canModifyMainDoc;

	protected boolean canModifyFlow;

	protected String canModifyHandlerNodeIds;

	protected String mustModifyHandlerNodeIds;

	protected int dayOfNotify;

	protected int dayOfPass;

	/**
	 * 事务启动多少天后未处理通知起草人
	 */
	protected int tranNotifyDraft;
	/**
	 * 事务启动多少天后未处理通知特权人
	 */
	protected int tranNotifyPrivate;

	protected Operations operations;

	protected List<OAVariant> variants = new ArrayList<OAVariant>();
	
	protected Variables variables;

	/**
	 * 是否已经初始化，运算过当前节点审批人
	 */
	protected boolean hasInitSysOrgElment = false;

	/**
	 * @return 是否已经初始化，运算过当前节点审批人
	 */
	public boolean isHasInitSysOrgElment() {
		return hasInitSysOrgElment;
	}

	/**
	 * @param 是否已经初始化
	 *            ，运算过当前节点审批人
	 */
	public void setHasInitSysOrgElment(boolean hasInitSysOrgElment) {
		this.hasInitSysOrgElment = hasInitSysOrgElment;
	}

	public boolean isCanModifyFlow() {
		return canModifyFlow;
	}

	public void setCanModifyFlow(boolean canModifyFlow) {
		this.canModifyFlow = canModifyFlow;
	}

	public String getCanModifyHandlerNodeIds() {
		return canModifyHandlerNodeIds;
	}

	public void setCanModifyHandlerNodeIds(String canModifyHandlerNodeIds) {
		this.canModifyHandlerNodeIds = canModifyHandlerNodeIds;
	}

	public boolean isCanModifyMainDoc() {
		return canModifyMainDoc;
	}

	public void setCanModifyMainDoc(boolean canModifyMainDoc) {
		this.canModifyMainDoc = canModifyMainDoc;
	}

	public int getDayOfNotify() {
		return dayOfNotify;
	}

	public void setDayOfNotify(int dayOfNotify) {
		this.dayOfNotify = dayOfNotify;
	}

	public int getDayOfPass() {
		return dayOfPass;
	}

	public void setDayOfPass(int dayOfPass) {
		this.dayOfPass = dayOfPass;
	}

	public boolean isIgnoreOnHandlerEmpty() {
		return ignoreOnHandlerEmpty;
	}

	public void setIgnoreOnHandlerEmpty(boolean ignoreOnHandlerEmpty) {
		this.ignoreOnHandlerEmpty = ignoreOnHandlerEmpty;
	}

	public boolean isIgnoreOnHandlerSame() {
		return ignoreOnHandlerSame;
	}

	public void setIgnoreOnHandlerSame(boolean ignoreOnHandlerSame) {
		this.ignoreOnHandlerSame = ignoreOnHandlerSame;
	}

	public String getMustModifyHandlerNodeIds() {
		return mustModifyHandlerNodeIds;
	}

	public void setMustModifyHandlerNodeIds(String mustModifyHandlerNodeIds) {
		this.mustModifyHandlerNodeIds = mustModifyHandlerNodeIds;
	}

	public Operations getOperations() {
		return operations;
	}

	public void setOperations(Operations operations) {
		this.operations = operations;
	}

	public int getOptHandlerCalType() {
		return optHandlerCalType;
	}

	public void setOptHandlerCalType(int optHandlerCalType) {
		this.optHandlerCalType = optHandlerCalType;
	}

	public String getOptHandlerIds() {
		return optHandlerIds;
	}

	public void setOptHandlerIds(String optHandlerIds) {
		this.optHandlerIds = optHandlerIds;
	}

	public String getOptHandlerNames() {
		return optHandlerNames;
	}

	public void setOptHandlerNames(String optHandlerNames) {
		this.optHandlerNames = optHandlerNames;
	}

	public int getProcessType() {
		return processType;
	}

	public void setProcessType(int processType) {
		this.processType = processType;
	}

	public boolean isUseOptHandlerOnly() {
		return useOptHandlerOnly;
	}

	public void setUseOptHandlerOnly(boolean useOptHandlerOnly) {
		this.useOptHandlerOnly = useOptHandlerOnly;
	}

	public List<OAVariant> getVariants() {
		return variants;
	}

	public void setVariants(List<OAVariant> variants) {
		this.variants = variants;
	}

	public void addVariant(OAVariant variant) {
		variants.add(variant);
	}

	/*
	 * 审批类型
	 */
	protected String approvalType;

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	public String getApprovalType() {
		return approvalType;
	}

	/**
	 * 默认处理人选择类型
	 */
	protected String handlerSelectType;

	public void setHandlerSelectType(String handlerSelectType) {
		this.handlerSelectType = handlerSelectType;
	}

	public String getHandlerSelectType() {
		return handlerSelectType;
	}

	/**
	 * 备选处理人选择类型
	 */
	protected String optHandlerSelectType;

	/**
	 * @return 备选处理人选择类型
	 */
	public String getOptHandlerSelectType() {
		return optHandlerSelectType;
	}

	/**
	 * @param optHandlerSelectType
	 *            备选处理人选择类型
	 */
	public void setOptHandlerSelectType(String optHandlerSelectType) {
		this.optHandlerSelectType = optHandlerSelectType;
	}

	// 记录原始节点处理人配置
	protected String srcHandlerIds;

	protected String srcHandlerNames;

	protected String srcHandlerSelectType;

	public String getSrcHandlerIds() {
		return srcHandlerIds;
	}

	public void setSrcHandlerIds(String srcHandlerIds) {
		this.srcHandlerIds = srcHandlerIds;
	}

	public String getSrcHandlerNames() {
		return srcHandlerNames;
	}

	public void setSrcHandlerNames(String srcHandlerNames) {
		this.srcHandlerNames = srcHandlerNames;
	}

	public String getSrcHandlerSelectType() {
		return srcHandlerSelectType;
	}

	public void setSrcHandlerSelectType(String srcHandlerSelectType) {
		this.srcHandlerSelectType = srcHandlerSelectType;
	}

	public int getTranNotifyDraft() {
		return tranNotifyDraft;
	}

	public void setTranNotifyDraft(int tranNotifyDraft) {
		this.tranNotifyDraft = tranNotifyDraft;
	}

	public int getTranNotifyPrivate() {
		return tranNotifyPrivate;
	}

	public void setTranNotifyPrivate(int tranNotifyPrivate) {
		this.tranNotifyPrivate = tranNotifyPrivate;
	}

	public int getPassType() {
		return passType;
	}

	public void setPassType(int passType) {
		this.passType = passType;
	}

	public double getPassPercent() {
		return passPercent;
	}

	public void setPassPercent(double passPercent) {
		this.passPercent = passPercent;
	}

	public boolean isIgnoreOnSerialHandlerSame() {
		return ignoreOnSerialHandlerSame;
	}

	public void setIgnoreOnSerialHandlerSame(boolean ignoreOnSerialHandlerSame) {
		this.ignoreOnSerialHandlerSame = ignoreOnSerialHandlerSame;
	}

	public Variables getVariables() {
		return variables;
	}

	public void setVariables(Variables variables) {
		this.variables = variables;
	}
}

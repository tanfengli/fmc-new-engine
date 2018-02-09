package com.vispractice.fmc.business.base.constrant;

public interface OAConstant {
	/**
	 * 审批人区分
	 */
	public static final int HANDLE_ROLE = 100;
	
	public static final int DRAFT_ROLE = 200;
	
	public static final int PRIVILEGED_ROLE = 300; 
	
	/**
	 * 流程审批通过方式：按比例
	 */
	public static final int WF_PASS_PERCENT = 1;
	
	/**
	 * 增加一个流程通知类的起始字符9
	 */
	public static final String WF_NOTIFY_PRE = "9";
	
	/**
	 * 备选处理人计算方式：按创建人
	 */
	public static final int OPTHANDLERCALTYPE_CREATOR = 1;

	/**
	 * 备选处理人计算方式：按处理人
	 */
	public static final int OPTHANDLERCALTYPE_HANDLER = 2;

	/**
	 * 流转方式：串行
	 */
	public static final int PROCESSTYPE_SERIAL = 0;

	/**
	 * 流转方式：并行
	 */
	public static final int PROCESSTYPE_SINGLE = 1;

	/**
	 * 流转方式：会审/会签
	 */
	public static final int PROCESSTYPE_ALL = 2;

	/**
	 * 处理人操作：通过
	 */
	public static final int HANDLER_OPERATION_TYPE_PASS = 101;

	/**
	 * 处理人操作：驳回
	 */
	public static final int HANDLER_OPERATION_TYPE_REFUSE = 102;

	/**
	 * 处理人操作：转办
	 */
	public static final int HANDLER_OPERATION_TYPE_COMMISSION = 103;

	/**
	 * 处理人操作：沟通
	 */
	public static final int HANDLER_OPERATION_TYPE_COMMUNICATE = 104;

	/**
	 * 处理人操作：废弃
	 */
	public static final int HANDLER_OPERATION_TYPE_ABANDON = 105;

	/**
	 * 处理人操作：签字
	 */
	public static final int HANDLER_OPERATION_TYPE_SIGN = 106;

	/**
	 * 处理人操作：回复沟通
	 */
	public static final int HANDLER_OPERATION_TYPE_RTNCOMMUNICATE = 107;

	/**
	 * 处理人操作：取消沟通
	 */
	public static final int HANDLER_OPERATION_TYPE_CELCOMMUNICATE = 108;
	
	/**
	 * 处理人操作：取消沟通
	 */
	public static final int HANDLER_OPERATION_TYPE_ADDSIGN= 109;

	/**
	 * 起草人操作：提交文档
	 */
	public static final int CREATOR_OPERATION_TYPE_SUBMIT = 201;

	/**
	 * 起草人操作：催办
	 */
	public static final int CREATOR_OPERATION_TYPE_PRESS = 202;

	/**
	 * 起草人操作：撤回
	 */
	public static final int CREATOR_OPERATION_TYPE_RETURN = 203;

	/**
	 * 起草人操作：废弃
	 */
	public static final int CREATOR_OPERATION_TYPE_ABANDON = 204;

	/**
	 * 特权人操作：终审通过
	 */
	public static final int ADMIN_OPERATION_TYPE_PASS = 301;

	/**
	 * 特权人操作：前后跳转
	 */
	public static final int ADMIN_OPERATION_TYPE_JUMP = 302;

	/**
	 * 特权人操作：直接废弃
	 */
	public static final int ADMIN_OPERATION_TYPE_ABANDON = 303;

	/**
	 * 特权人操作：修改当前处理人
	 */
	public static final int ADMIN_OPERATION_TYPE_CHGCURHANDLER = 304;

	/**
	 * 特权人操作：修改流程
	 */
	public static final int ADMIN_OPERATION_TYPE_MODIFYPROCESS = 305;

	/**
	 * 特权人操作：回收子流程
	 */
	public static final int ADMIN_OPERATION_TYPE_RECOVER = 306;

	/**
	 * 特权人操作：回收子流程
	 */
	public static final int ADMIN_OPERATION_TYPE_ENDBRANCH = 307;

	/**
	 * 系统操作：过期跳过
	 */
	public static final int SYSTEM_OPERATION_TYPE_EXPIREDJUMP = 401;

	/**
	 * 系统操作：过期废弃
	 */
	public static final int SYSTEM_OPERATION_TYPE_EXPIREDABANDON = 402;

	/**
	 * 系统操作：系统废弃
	 */
	public static final int SYSTEM_OPERATION_TYPE_ABANDON = 403;

	/**
	 * 系统操作：系统子流程驳回跳转废弃，重新流转
	 */
	public static final int SYSTEM_OPERATION_TYPE_RESTART_ABANDON = 404;

	/**
	 * 系统操作：系统主流程废弃，子流程连带废弃
	 */
	public static final int SYSTEM_OPERATION_TYPE_PARENT_ABANDON = 405;

	/**
	 * 系统操作：系统主流程通过，子流程连带废弃
	 */
	public static final int SYSTEM_OPERATION_TYPE_PARENTPASS_ABANDON = 406;

	/**
	 * 系统操作：系统特权人回收操作响应，废弃流程
	 */
	public static final int SYSTEM_OPERATION_TYPE_ADMINRECOVER_ABANDON = 407;
	
	/**
	 * 系统操作：流程跳转
	 */
	public static final int SYSTEM_OPERATION_TYPE_EXCEPTIONJUMP = 403;
	/**
	 * 系统操作：条件分支
	 */
	public static final int SYSTEM_OPERATION_TYPE_AUTOBRANCH = 501;

	/**
	 * 系统操作：自动运行
	 */
	public static final int SYSTEM_OPERATION_TYPE_AUTORUN = 502;

	/**
	 * 系统操作：创建分支
	 */
	public static final int SYSTEM_OPERATION_TYPE_SPLIT = 503;

	/**
	 * 开始节点服务父节点ID
	 */
	public static final String START_NODE_PARENT = "oaInitNode";

	/**
	 * 开始节点服务父节点ID
	 */
	public static final String DRAFT_NODE_PARENT = "oaDraftNode";

	/**
	 * 审批节点服务父节点ID
	 */
	public static final String AUDIT_NODE_PARENT = "oaAuditNode";

	/**
	 * 签字节点服务父节点ID
	 */
	public static final String SIGN_NODE_PARENT = "oaSignNode";

	/**
	 * 抄送节点服务父节点ID
	 */
	public static final String SEND_NODE_PARENT = "oaSendNode";

	/**
	 * 结束节点服务父节点ID
	 */
	public static final String END_NODE_PARENT = "oaFinalNode";

	/**
	 * 合并节点服务父节点ID
	 */
	public static final String JOIN_NODE_PARENT = "oaJoinNode";

	/**
	 * 分支节点服务父节点ID
	 */
	public static final String SPLIT_NODE_PARENT = "oaSplitNode";

	/**
	 * 人工决策分支节点服务父节点ID
	 */
	public static final String MANUAL_BRANCH_NODE_PARENT = "oaManualBranchNode";

	/**
	 * 条件分支节点服务父节点ID
	 */
	public static final String AUTO_BRANCH_NODE_PARENT = "oaAutoBranchNode";

	/**
	 * 自动运行节点服务父节点ID
	 */
	public static final String AUTO_RUN_NODE_PARENT = "oaAutoRunNode";

	/**
	 * 机器人节点服务父节点ID
	 */
	public static final String ROBOT_NODE_PARENT = "oaRobotNode";

	/**
	 * OA启动子流程节点ID
	 */
	public static final String START_SUBPROCESS_NODE_PARENT = "oaStartSubProcessNode";

	/**
	 * OA回收子流程节点ID
	 */
	public static final String RECOVER_SUBPROCESS_NODE_PARENT = "oaRecoverSubProcessNode";

	/**
	 * 处理人身份标识，键值：处理人
	 */
	public final static String IDENTITY_PROCESSOR = "processor";
	
	/**
	 * 处理人身份标识，键值：起草人
	 */
	public final static String IDENTITY_DRAFTER = "drafter";
	
	/**
	 * 处理人身份标识，键值：特权人
	 */
	public final static String IDENTITY_AUTHORITY = "authority";

	/**
	 * 处理人身份标识，起草人
	 * 
	 */
	public static final int HANDLER_IDENTITY_DRAFT = 1;

	/**
	 * 处理人身份标识，处理人
	 */
	public static final int HANDLER_IDENTITY_HANDLER = 2;

	/**
	 * 处理人身份标识，特权人
	 */
	public static final int HANDLER_IDENTITY_PRIVILEGER = 3;

	/**
	 * 处理人身份标识，系统
	 */
	public static final int HANDLER_IDENTITY_SYSTEM = 4;

	/**
	 * 流程特权人角色名
	 */
	public static final String ROLE_SYS_WORKFLOW_AUTHORITY_ADMIN = "ROLE_SYS_WORKFLOW_AUTHORITY_ADMIN";
	
	/**
	 * 流程授权管理员角色名
	 */
	public static final String ROLE_SYS_WORKFLOW_AUTHORITY_MANAGER = "ROLE_SYS_WORKFLOW_AUTHORITY_MANAGER";
	
	/**
	 * 流程授权权限
	 */
	public static final String ROLE_SYS_WORKFLOW_AUTHORIZE_ASSIGN = "ROLE_SYS_WORKFLOW_AUTHORIZE_ASSIGN";

	public static final String HANDLER_SELECT_TYPE_FORMULA = "formula";

	public static final String HANDLER_SELECT_TYPE_ORG = "org";
	
	/**
	 * 节点审批类型
	 */
	public static final String APPROVAL_TYPE_NODE = "NODE";
	
	public static final String APPROVAL_TYPE_INSTANCE = "INSTANCE";
	
}

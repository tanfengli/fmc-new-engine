alter table SYS_WF_USAGE add FD_USAGE_TYPE VARCHAR2(36 CHAR);
comment on column SYS_WF_USAGE.FD_USAGE_TYPE is '分类';


alter table sys_org_element modify fd_calendar_id varchar2(32); 
--2017/12/19添加角色线成员是否有效字段
alter table sys_org_role_line add fd_is_available number;

--增加树叶子节点
alter table SYS_ORG_ELEMENT add fd_is_leaf number;
create index IDX_SYS_ORG_ELEMENT_NO on SYS_ORG_ELEMENT (FD_NO, FD_ORG_TYPE)
--增加群组分类树叶子节点
alter table sys_org_group_cate add fd_is_leaf number;
--增加模板分类树叶子节点
alter table sys_category_main add fd_is_leaf number;
--增加角色线成员叶子节点
alter table sys_org_role_line add fd_is_leaf number;
alter table SYS_WF_USAGE add FD_ACTIVE_FLAG NUMBER(1);
comment on column SYS_WF_USAGE.FD_ACTIVE_FLAG is '是否有效';
   
--2017-08-03 流程日志记录
create table SYS_WF_EXCEPTION_LOG
(
  FD_ID             VARCHAR2(32) not null,
  FD_REASON         VARCHAR2(256),
  FD_PROCESS_ID     VARCHAR2(32),
  FD_FACT_NODE_ID   VARCHAR2(256),
  FD_FACT_NODE_NAME VARCHAR2(256),
  FD_HANDLER_ID     VARCHAR2(256),
  FD_HANDLER_NAME   VARCHAR2(256),
  FD_CREATE_TIME    TIMESTAMP(6),
  FD_EXCEPTION      CLOB,
  FD_MESSAGE        VARCHAR2(1024)
);

alter table SYS_WF_EXCEPTION_LOG add constraint PK_EXCEPTION_ID primary key (FD_ID);
--alter table SYS_WF_EXCEPTION_LOG add constraint IDX_SYSWFEXCEPTION_PROCESSID foreign key (FD_PROCESS_ID) references SYS_NEWS_MAIN (FD_ID);

comment on column sys_wf_exception_log.fd_id is '主键';
comment on column sys_wf_exception_log.fd_process_id is '流程实例ID';
comment on column sys_wf_exception_log.fd_reason is '异常原因';
comment on column sys_wf_exception_log.fd_fact_node_id is '当前处理节点ID';
comment on column sys_wf_exception_log.fd_fact_node_name is '当前处理节点名称';
comment on column sys_wf_exception_log.fd_handler_id is '当前处理人ID';
comment on column sys_wf_exception_log.fd_handler_name is '当前处理人名称';
comment on column sys_wf_exception_log.fd_create_time is '创建时间';
comment on column sys_wf_exception_log.fd_exception is '异常详情';
comment on column sys_wf_exception_log.fd_message is '异常信息';  

-- 2017-09-28 流程系统菜单
create table SYS_MENU
(
  FD_ID             VARCHAR2(36 CHAR) not null,
  FD_NAME           VARCHAR2(200 CHAR) not null,
  LINK_URL          VARCHAR2(1000 CHAR),
  ICON              VARCHAR2(1000 CHAR),
  FD_PARENT_ID      VARCHAR2(36 CHAR),
  FD_HIERARCHY_ID   VARCHAR2(1000 CHAR) not null,
  FD_IS_LEAF        NUMBER,
  FD_IS_AVAILABLE   NUMBER,
  DOC_CREATE_TIME   TIMESTAMP(6) not null,
  DOC_ALTER_TIME    TIMESTAMP(6),
  DOC_CREATOR_ID    VARCHAR2(36 CHAR) not null,
  DOC_ALTEROR_ID    VARCHAR2(36 CHAR),
  FD_DESCRIPTION    VARCHAR2(4000 CHAR),
  FD_ORDER          NUMBER,
  FD_NO             VARCHAR2(200 CHAR),
  FD_TYPE           NUMBER,
  BUSI_SYS_ID       VARCHAR2(36 CHAR) not null,
  FD_IS_OPEN        NUMBER,
  FD_AUTHORIZE_TYPE NUMBER default 0
);
  -- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_MENU
  add constraint PK_SYS_MENU primary key (FD_ID);
  
alter table sys_menu add fd_is_open number;
alter table sys_menu add FD_AUTHORIZE_TYPE NUMBER default 0;

comment on column SYS_MENU.fd_is_open is '是否展开';
-- Add comments to the columns 
comment on column SYS_MENU.FD_ID is '主键';
comment on column SYS_MENU.FD_NAME is '菜单名称';
comment on column SYS_MENU.LINK_URL is '菜单链接地址';
comment on column SYS_MENU.ICON is '菜单图标';
comment on column SYS_MENU.FD_PARENT_ID is '父级ID';
comment on column SYS_MENU.FD_HIERARCHY_ID is '层级ID';
comment on column SYS_MENU.FD_IS_LEAF is '是否子节点';
comment on column SYS_MENU.FD_IS_AVAILABLE is '是否有效';
comment on column SYS_MENU.DOC_CREATE_TIME is '创建时间';
comment on column SYS_MENU.DOC_ALTER_TIME is '修改时间';
comment on column SYS_MENU.DOC_CREATOR_ID is '创建人ID';
comment on column SYS_MENU.DOC_ALTEROR_ID is '修改人ID';
comment on column SYS_MENU.FD_DESCRIPTION is '菜单描述';
comment on column SYS_MENU.FD_ORDER is '菜单顺序';
comment on column SYS_MENU.FD_NO is '菜单编码';
comment on column SYS_MENU.FD_TYPE is '菜单类型（1为子系统，2为模块，3为菜单）';
comment on column SYS_MENU.BUSI_SYS_ID is '对应业务系统ID';
comment on column SYS_MENU.FD_AUTHORIZE_TYPE is '授权方式（0为菜单授权，1为无需授权）';
-- 菜单数据初始化
--delete from sys_menu;

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ec7fb064946b59d47d3b74551bb92c', '我的工作台', '', '', '', 'x15ec7fb064946b59d47d3b74551bb92cx', 0, 1, '01-10月-17 10.14.30.000000 上午', '18-10月-17 09.18.25.097000 上午', '50000002', '50000002', 100, '', 'aboutMyself', 3, '8a4cf3b85f0f5514015f22d939c40008', 1);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ecd2eb615094d1553382d489cb859f', '已办', '/aboutMyself/myApprovalBills/index?status=done', '', '15eccea5ef74d97892ff44b49b3b278e', 'x15ec7fb064946b59d47d3b74551bb92cx15eccea5ef74d97892ff44b49b3b278ex15ecd2eb615094d1553382d489cb859fx', 1, 1, '01-10月-17 10.29.04.000000 上午', '19-10月-17 11.18.15.063000 上午', '50000002', '50000002', 200, 'aaa', 'dealed', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ecd2ef591ceba4b0530c04dd898060', '待阅', '/aboutMyself/myPendingRead/index', '', '15eccea5ef74d97892ff44b49b3b278e', 'x15ec7fb064946b59d47d3b74551bb92cx15eccea5ef74d97892ff44b49b3b278ex15ecd2ef591ceba4b0530c04dd898060x', 1, 1, '02-10月-17 06.29.20.000000 下午', '19-10月-17 11.18.23.288000 上午', '50000002', '50000002', 300, '', 'pendingRead', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15eccea5ef74d97892ff44b49b3b278e', '流程处理', '', '', '15ec7fb064946b59d47d3b74551bb92c', 'x15ec7fb064946b59d47d3b74551bb92cx15eccea5ef74d97892ff44b49b3b278ex', 0, 1, '01-10月-17 05.14.25.000000 下午', '18-10月-17 09.45.18.409000 上午', '50000002', '50000002', 200, '', 'wfProcess', 3, '8a4cf3b85f0f5514015f22d939c40008', 1);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15eccea7e8b98e152f6a76c469fa4820', '待办', '/aboutMyself/myApprovalBills/index?status=todo', '', '15eccea5ef74d97892ff44b49b3b278e', 'x15ec7fb064946b59d47d3b74551bb92cx15eccea5ef74d97892ff44b49b3b278ex15eccea7e8b98e152f6a76c469fa4820x', 1, 1, '02-10月-17 05.14.33.000000 下午', '19-10月-17 11.18.09.169000 上午', '50000002', '50000002', 100, '', 'pendingDeal', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ecce1815e0ba113c35fa84922ac5a6', '流程起草', '/docManage/billDraft/index', '', '15ec7fb064946b59d47d3b74551bb92c', 'x15ec7fb064946b59d47d3b74551bb92cx15ecce1815e0ba113c35fa84922ac5a6x', 1, 1, '30-9月 -17 09.04.44.000000 上午', '19-10月-17 11.11.50.665000 上午', '50000002', '50000002', 100, '', 'processDraft', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1c00b8155803bc5ad444c4484516', '系统管理', '', '', '', 'x15ed1c00b8155803bc5ad444c4484516x', 0, 1, '30-9月 -17 03.46.17.601000 下午', '', '50000002', '', 500, '', 'systemAdmin', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1c040b89ef174c5ddda41bc85ffc', '组织架构', '/sys/orgElement/list', '', '15ed1c00b8155803bc5ad444c4484516', 'x15ed1c00b8155803bc5ad444c4484516x15ed1c040b89ef174c5ddda41bc85ffcx', 1, 1, '30-9月 -17 03.46.31.224000 下午', '', '50000002', '', 200, '', 'orgElement', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1c0786d5bba56af642447d991c63', '菜单设置', '/sys/menu/index', '', '15ed1c00b8155803bc5ad444c4484516', 'x15ed1c00b8155803bc5ad444c4484516x15ed1c0786d5bba56af642447d991c63x', 1, 1, '30-9月 -17 03.46.45.485000 下午', '', '50000002', '', 300, '', 'menu', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1c0dc40830056bd7f03452e9f8d9', '错误日志', '/sys/sysLogError/list', '', '15ed1c00b8155803bc5ad444c4484516', 'x15ed1c00b8155803bc5ad444c4484516x15ed1c0dc40830056bd7f03452e9f8d9x', 1, 1, '30-9月 -17 03.47.11.040000 下午', '', '50000002', '', 400, '', 'sysLogError', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1c114991f6c429aa71e494fa3ea0', '系统参数管理', '', '', '15ed1c00b8155803bc5ad444c4484516', 'x15ed1c00b8155803bc5ad444c4484516x15ed1c114991f6c429aa71e494fa3ea0x', 0, 1, '30-9月 -17 03.47.25.465000 下午', '', '50000002', '', 500, '', 'sysParamAdmin', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1c12a8bd9a7989a0862402cbf772', '自定义参数', '', '', '15ed1c114991f6c429aa71e494fa3ea0', 'x15ed1c00b8155803bc5ad444c4484516x15ed1c114991f6c429aa71e494fa3ea0x15ed1c12a8bd9a7989a0862402cbf772x', 1, 1, '30-9月 -17 03.47.31.084000 下午', '', '50000002', '', 200, '', 'paramDefine', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);


insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1c114991f6c429aa71e494fa3eb0', '预警管理', '', '', '15ed1c00b8155803bc5ad444c4484516', 'x15ed1c00b8155803bc5ad444c4484516x15ed1c114991f6c429aa71e494fa3eb0x', 0, 1, '30-9月 -17 03.47.25.465000 下午', '', '50000002', '', 500, '', 'warningAdmin', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1c12a8bd9a7989a0862402cbf782', '工作日历设置', '', '', '15ed1c114991f6c429aa71e494fa3eb0', 'x15ed1c00b8155803bc5ad444c4484516x15ed1c114991f6c429aa71e494fa3eb0x15ed1c12a8bd9a7989a0862402cbf782x', 1, 1, '30-9月 -17 03.47.31.084000 下午', '', '50000002', '', 200, '', 'workCalendarSet', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1c14a357b079661529d42a4af0b6', '时间设置', '', '', '15ed1c114991f6c429aa71e494fa3eb0', 'x15ed1c00b8155803bc5ad444c4484516x15ed1c114991f6c429aa71e494fa3eb0x15ed1c14a357b079661529d42a4af0b6x', 1, 1, '30-9月 -17 03.47.39.189000 下午', '', '50000002', '', 300, '', 'timeSetting', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1c15e3a1948c5205c534cdeb2344', '预警设置', '', '', '15ed1c114991f6c429aa71e494fa3eb0', 'x15ed1c00b8155803bc5ad444c4484516x15ed1c114991f6c429aa71e494fa3eb0x15ed1c15e3a1948c5205c534cdeb2344x', 1, 1, '30-9月 -17 03.47.44.314000 下午', '', '50000002', '', 400, '', 'warningSet', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1c184d9a76592f98f8f4fca86bea', '通知配置', '/sys/notifySet/list', '', '15ed1c114991f6c429aa71e494fa3eb0', 'x15ed1c00b8155803bc5ad444c4484516x15ed1c114991f6c429aa71e494fa3eb0x15ed1c184d9a76592f98f8f4fca86beax', 1, 1, '01-10月-17 03.47.54.000000 下午', '', '50000002', '', 250, '', 'notifySet', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15f245eff99b7dced4ee4b5491d8e4d3', '员工权限查询', '/authority/userAuth/index', '', '15ed1bef5ee9180676d892f48f6b4e47', 'x15ed1bbe34621e926480a8e499699e53x15ed1bef5ee9180676d892f48f6b4e47x15f245eff99b7dced4ee4b5491d8e4d3x', 1, 1, '17-10月-17 12.48.46.000000 上午', '', '50000002', '', 100, '', 'userAuthSearch', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1b88b07d263b2d80aca470eaf18a', '群集信息', '/sys/sysClusterServer/list', '', '15ed067de49452ca7971a7042748a472', 'x15ed067de49452ca7971a7042748a472x15ed1b88b07d263b2d80aca470eaf18ax', 1, 1, '01-10月-17 07.38.05.000000 上午', '19-10月-17 11.19.20.992000 上午', '50000002', '50000002', 400, '', 'clusterServer', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1b726a5287958b08b614da4a092a', '流程调整', '/docManage/billProcess/index', '', '15ed067de49452ca7971a7042748a472', 'x15ed067de49452ca7971a7042748a472x15ed1b726a5287958b08b614da4a092ax', 1, 1, '01-10月-17 07.36.34.000000 上午', '19-10月-17 11.18.56.756000 上午', '50000002', '50000002', 200, '', 'processAjust', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1b85d2e6ce066a0b1f8484194dd9', '流程查询', '/docManage/billSearch/index', '', '15ed067de49452ca7971a7042748a472', 'x15ed067de49452ca7971a7042748a472x15ed1b85d2e6ce066a0b1f8484194dd9x', 1, 1, '01-10月-17 07.37.54.000000 上午', '19-10月-17 11.19.05.936000 上午', '50000002', '50000002', 300, '', 'processSearch', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1b8bfc4aafe3219f6384cef81a55', '系统任务', '/sys/quartzJob/list', '', '15ed067de49452ca7971a7042748a472', 'x15ed067de49452ca7971a7042748a472x15ed1b8bfc4aafe3219f6384cef81a55x', 1, 1, '01-10月-17 07.38.19.000000 上午', '19-10月-17 11.19.30.007000 上午', '50000002', '50000002', 500, '', 'quartzJob', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1b8f3a30446b71f57044fcabb0b1', '流程报表', '', '', '15ed067de49452ca7971a7042748a472', 'x15ed067de49452ca7971a7042748a472x15ed1b8f3a30446b71f57044fcabb0b1x', 0, 1, '01-10月-17 07.38.32.000000 上午', '18-10月-17 09.45.54.670000 上午', '50000002', '50000002', 600, '', 'wfForm', 3, '8a4cf3b85f0f5514015f22d939c40008', 1);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1b91c7322872fb273584bdb89720', '异常统计', '/sys/wfExceptionLog/index', '', '15ed1b8f3a30446b71f57044fcabb0b1', 'x15ed067de49452ca7971a7042748a472x15ed1b8f3a30446b71f57044fcabb0b1x15ed1b91c7322872fb273584bdb89720x', 1, 1, '01-10月-17 07.38.43.000000 上午', '19-10月-17 11.19.42.689000 上午', '50000002', '50000002', 200, '', 'wfExceptionLog', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1b9628b75245fdcbada4ddc928a0', '模板管理', '', '', '', 'x15ed1b9628b75245fdcbada4ddc928a0x', 0, 1, '01-10月-17 07.39.01.000000 上午', '19-10月-17 11.20.04.232000 上午', '50000002', '50000002', 300, '', 'templateAdmin', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1b984dfd71807d2eb9040c9882cf', '模板分类', '/setting/category/index', '', '15ed1b9628b75245fdcbada4ddc928a0', 'x15ed1b9628b75245fdcbada4ddc928a0x15ed1b984dfd71807d2eb9040c9882cfx', 1, 1, '01-10月-17 07.39.09.000000 上午', '19-10月-17 11.20.19.206000 上午', '50000002', '50000002', 200, '', 'templareCategory', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1b9c631d85f1e8c0d9c49d0ba54c', '模板设置', '/setting/sysNewsTemplate/index', '', '15ed1b9628b75245fdcbada4ddc928a0', 'x15ed1b9628b75245fdcbada4ddc928a0x15ed1b9c631d85f1e8c0d9c49d0ba54cx', 1, 1, '01-10月-17 07.39.26.000000 上午', '19-10月-17 11.20.29.576000 上午', '50000002', '50000002', 300, '', 'sysNewsTemplate', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1b9fccc54f23b62fda648fca3ce5', '通用模板', '/sys/commonTemplate/list', '', '15ed1b9628b75245fdcbada4ddc928a0', 'x15ed1b9628b75245fdcbada4ddc928a0x15ed1b9fccc54f23b62fda648fca3ce5x', 1, 1, '01-10月-17 07.39.40.000000 上午', '19-10月-17 11.20.37.267000 上午', '50000002', '50000002', 400, '', 'commonTemplate', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1ba2aa70700bf8997ef45ad94890', '群组分类管理', '/setting/groupCate/index', '', '15ed1b9628b75245fdcbada4ddc928a0', 'x15ed1b9628b75245fdcbada4ddc928a0x15ed1ba2aa70700bf8997ef45ad94890x', 1, 1, '01-10月-17 07.39.52.000000 上午', '19-10月-17 11.20.47.489000 上午', '50000002', '50000002', 500, '', 'groupCate', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1ba6646a767ec0ce9d64be9bcde9', '群组管理', '/setting/groupAdmin/index', '', '15ed1b9628b75245fdcbada4ddc928a0', 'x15ed1b9628b75245fdcbada4ddc928a0x15ed1ba6646a767ec0ce9d64be9bcde9x', 1, 1, '01-10月-17 07.40.07.000000 上午', '19-10月-17 11.21.01.819000 上午', '50000002', '50000002', 600, '', 'groupAdmin', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1babb5b070969ceabf24c9f9a429', '角色线配置', '/roleLineManage/roleLineSetting/index', '', '15ed1b9628b75245fdcbada4ddc928a0', 'x15ed1b9628b75245fdcbada4ddc928a0x15ed1babb5b070969ceabf24c9f9a429x', 1, 1, '01-10月-17 07.40.29.000000 上午', '19-10月-17 11.20.55.364000 上午', '50000002', '50000002', 700, '', 'roleLineSetting', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1baf6ffc22635fb5e3041bca814b', '角色线成员', '/roleLineManage/roleLineMember/index', '', '15ed1b9628b75245fdcbada4ddc928a0', 'x15ed1b9628b75245fdcbada4ddc928a0x15ed1baf6ffc22635fb5e3041bca814bx', 1, 1, '30-9月 -17 03.40.44.671000 下午', '', '50000002', '', 800, '', 'roleLineMember', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1bb242a40584e50c00649d0a99fa', '模拟器', '/simulator/index', '', '15ed1b9628b75245fdcbada4ddc928a0', 'x15ed1b9628b75245fdcbada4ddc928a0x15ed1bb242a40584e50c00649d0a99fax', 1, 1, '30-9月 -17 03.40.56.234000 下午', '', '50000002', '', 900, '', 'simulator', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1bb64cda96e3f23023c4a3fbc450', '通用岗位', '/sys/generalPostion/list', '', '15ed1b9628b75245fdcbada4ddc928a0', 'x15ed1b9628b75245fdcbada4ddc928a0x15ed1bb64cda96e3f23023c4a3fbc450x', 1, 1, '30-9月 -17 03.41.12.781000 下午', '', '50000002', '', 1000, '', 'generalPostion', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1bb8c216acb6929e73c48859858d', '仿真测试', '', '', '15ed1b9628b75245fdcbada4ddc928a0', 'x15ed1b9628b75245fdcbada4ddc928a0x15ed1bb8c216acb6929e73c48859858dx', 1, 1, '30-9月 -17 03.41.22.849000 下午', '', '50000002', '', 1100, '', 'simulationTest', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1bbe34621e926480a8e499699e53', '参数管理', '', '', '', 'x15ed1bbe34621e926480a8e499699e53x', 0, 1, '30-9月 -17 03.41.45.158000 下午', '', '50000002', '', 400, '', 'paramAdmin', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1bc142c127ccf1a8c6a45f7837cb', '基础参数', '', '', '15ed1bbe34621e926480a8e499699e53', 'x15ed1bbe34621e926480a8e499699e53x15ed1bc142c127ccf1a8c6a45f7837cbx', 0, 1, '30-9月 -17 03.41.57.676000 下午', '', '50000002', '', 200, '', 'basicParam', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1bc485e323ba850cfad424db1fa7', '数据源', '/setting/compDbcp/index', '', '15ed1bc142c127ccf1a8c6a45f7837cb', 'x15ed1bbe34621e926480a8e499699e53x15ed1bc142c127ccf1a8c6a45f7837cbx15ed1bc485e323ba850cfad424db1fa7x', 1, 1, '30-9月 -17 03.42.11.038000 下午', '', '50000002', '', 200, '', 'dbSource', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1bc7c5e104137af4ddd40e6a0fd3', '基础设置', '/sys/basicSet/list', '', '15ed1bc142c127ccf1a8c6a45f7837cb', 'x15ed1bbe34621e926480a8e499699e53x15ed1bc142c127ccf1a8c6a45f7837cbx15ed1bc7c5e104137af4ddd40e6a0fd3x', 1, 1, '30-9月 -17 03.42.24.350000 下午', '', '50000002', '', 300, '', 'basicSet', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1bcc1c2090e7e7a2b6240ee942d9', '操作方式管理', '/sys/operationMode/list', '', '15ed1bc142c127ccf1a8c6a45f7837cb', 'x15ed1bbe34621e926480a8e499699e53x15ed1bc142c127ccf1a8c6a45f7837cbx15ed1bcc1c2090e7e7a2b6240ee942d9x', 1, 1, '30-9月 -17 03.42.42.114000 下午', '', '50000002', '', 400, '', 'operationMode', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1bcf17098dc4209db4044cbaae55', '变量设置', '/sys/variableSetting/list', '', '15ed1bc142c127ccf1a8c6a45f7837cb', 'x15ed1bbe34621e926480a8e499699e53x15ed1bc142c127ccf1a8c6a45f7837cbx15ed1bcf17098dc4209db4044cbaae55x', 1, 1, '30-9月 -17 03.42.54.320000 下午', '', '50000002', '', 500, '', 'variableSet', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1bd33b0afeeb88872cb4972a650d', '默认审批语管理', '/sys/defLanguage/list', '', '15ed1bc142c127ccf1a8c6a45f7837cb', 'x15ed1bbe34621e926480a8e499699e53x15ed1bc142c127ccf1a8c6a45f7837cbx15ed1bd33b0afeeb88872cb4972a650dx', 1, 1, '30-9月 -17 03.43.11.280000 下午', '', '50000002', '', 600, '', 'defLanguage', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1bd66a4e03c8d80d2aa4f9dade82', '业务系统配置', '/sys/sysBusiSys/list', '', '15ed1bc142c127ccf1a8c6a45f7837cb', 'x15ed1bbe34621e926480a8e499699e53x15ed1bc142c127ccf1a8c6a45f7837cbx15ed1bd66a4e03c8d80d2aa4f9dade82x', 1, 1, '30-9月 -17 03.43.24.324000 下午', '', '50000002', '', 700, '', 'sysBusiSys', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1bd66a4e03c8d80d2aa4f9dade83', '接口信息维护', 'anonymous/interface/index', '', '15ed1bc142c127ccf1a8c6a45f7837cb', 'x15ed1bbe34621e926480a8e499699e53x15ed1bc142c127ccf1a8c6a45f7837cbx15ed1bd66a4e03c8d80d2aa4f9dade83x', 1, 1, '30-9月 -17 03.43.24.324000 下午', '', '50000002', '', 700, '', 'sysInterface', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1bef5ee9180676d892f48f6b4e47', '权限管理', '', '', '15ed1bbe34621e926480a8e499699e53', 'x15ed1bbe34621e926480a8e499699e53x15ed1bef5ee9180676d892f48f6b4e47x', 0, 1, '30-9月 -17 03.45.06.542000 下午', '', '50000002', '', 300, '', 'authorizeAdmin', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1bf2c1aaa20e485d4164e19bc658', '流程授权', '/sys/sysAuth/list', '', '15ed1bef5ee9180676d892f48f6b4e47', 'x15ed1bbe34621e926480a8e499699e53x15ed1bef5ee9180676d892f48f6b4e47x15ed1bf2c1aaa20e485d4164e19bc658x', 1, 1, '30-9月 -17 03.45.20.410000 下午', '', '50000002', '', 200, '', 'sysAuth', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1bf6c0ce859af234ec34a48a7e15', '角色分配', '/roleManage/roleAssign/index', '', '15ed1bef5ee9180676d892f48f6b4e47', 'x15ed1bbe34621e926480a8e499699e53x15ed1bef5ee9180676d892f48f6b4e47x15ed1bf6c0ce859af234ec34a48a7e15x', 1, 1, '30-9月 -17 03.45.36.780000 下午', '', '50000002', '', 300, '', 'roleAssign', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed1bf90c8760853b93fa347ec95b07', '角色分类设置', '/roleManage/roleCate/index', '', '15ed1bef5ee9180676d892f48f6b4e47', 'x15ed1bbe34621e926480a8e499699e53x15ed1bef5ee9180676d892f48f6b4e47x15ed1bf90c8760853b93fa347ec95b07x', 1, 1, '30-9月 -17 03.45.46.184000 下午', '', '50000002', '', 400, '', 'roleCate', 3, '8a4cf3b85f0f5514015f22d939c40008', 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_ORDER, FD_DESCRIPTION, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN)
values ('15ed067de49452ca7971a7042748a472', '流程管理', '', '', '', 'x15ed067de49452ca7971a7042748a472x', 0, 1, '01-10月-17 01.30.21.000000 上午', '18-10月-17 09.45.27.030000 上午', '50000002', '50000002', 200, '', 'wfAdmin', 3, '8a4cf3b85f0f5514015f22d939c40008', 1);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_DESCRIPTION, FD_ORDER, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN, FD_AUTHORIZE_TYPE)
values ('1606cb9b335c8c6603a25e2402ea1ddb', '流程报表', '', '', '15ed1c00b8155803bc5ad444c4484516', 'x15ed1c00b8155803bc5ad444c4484516x1606cb9b335c8c6603a25e2402ea1ddbx',0,1,sysdate,sysdate, 'U50000002','','',600,'SYS_REPORT',3,'4028fb815f3295ef015f33389c6b0000',1,0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_DESCRIPTION, FD_ORDER, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN, FD_AUTHORIZE_TYPE)
values ('1606cbbe0a18bbdbb1e1c8b42bb9265f', '流程时效统计', '', '', '1606cb9b335c8c6603a25e2402ea1ddb', 'x15ed1c00b8155803bc5ad444c4484516x1606cb9b335c8c6603a25e2402ea1ddbx1606cbbe0a18bbdbb1e1c8b42bb9265fx',0,1,sysdate,sysdate,'U50000002','','',200,'FD_REPORT_LIMIT', 3, '4028fb815f3295ef015f33389c6b0000', 1, 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_DESCRIPTION, FD_ORDER, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN, FD_AUTHORIZE_TYPE)
values ('1606cbcf6221b9b8920435c45ce8ac5a', '流程超期统计', '', '', '1606cb9b335c8c6603a25e2402ea1ddb', 'x15ed1c00b8155803bc5ad444c4484516x1606cb9b335c8c6603a25e2402ea1ddbx1606cbcf6221b9b8920435c45ce8ac5ax', 0, 1, sysdate, sysdate, 'U50000002', '', '', 300, 'FD_REPORT_OVERDUE', 3, '4028fb815f3295ef015f33389c6b0000', 1, 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_DESCRIPTION, FD_ORDER, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN, FD_AUTHORIZE_TYPE)
values ('1606cbe821bf19edf61d8694dc8bc382', '按模板统计', 'report/template/limitdate', '', '1606cbbe0a18bbdbb1e1c8b42bb9265f', 'x15ed1c00b8155803bc5ad444c4484516x1606cb9b335c8c6603a25e2402ea1ddbx1606cbbe0a18bbdbb1e1c8b42bb9265fx1606cbe821bf19edf61d8694dc8bc382x', 1, 0,sysdate,sysdate, 'U50000002', 'U50000002', '根据模板统计流程实例信息', 200, 'FD_REPORT_TEMPLATE_STATIC', 3, '4028fb815f3295ef015f33389c6b0000', 1, 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_DESCRIPTION, FD_ORDER, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN, FD_AUTHORIZE_TYPE)
values ('1606cc1854701cf0f1aa257405787435', '按模板统计', 'report/template/overdue', '', '1606cbcf6221b9b8920435c45ce8ac5a', 'x15ed1c00b8155803bc5ad444c4484516x1606cb9b335c8c6603a25e2402ea1ddbx1606cbcf6221b9b8920435c45ce8ac5ax1606cc1854701cf0f1aa257405787435x', 1, 0,sysdate,sysdate, 'U50000002', 'U50000002', '根据流程模板统计逾期审批单据实例信息', 200, 'FD_REPORT_OVERDUE_STATIC', 3, '4028fb815f3295ef015f33389c6b0000', 1, 0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_DESCRIPTION, FD_ORDER, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN, FD_AUTHORIZE_TYPE)
values ('16071f4d037a77cef0ec1904473affd3', '按节点统计', 'report/node/limitdate', '', '1606cbbe0a18bbdbb1e1c8b42bb9265f', 'x15ed1c00b8155803bc5ad444c4484516x1606cb9b335c8c6603a25e2402ea1ddbx1606cbbe0a18bbdbb1e1c8b42bb9265fx16071f4d037a77cef0ec1904473affd3x',1,0,sysdate,'','U50000002','','',300, 'limitdateNodeStatistics',3,'4028fb815f3295ef015f33389c6b0000',1,0);

insert into sys_menu (FD_ID, FD_NAME, LINK_URL, ICON, FD_PARENT_ID, FD_HIERARCHY_ID, FD_IS_LEAF, FD_IS_AVAILABLE, DOC_CREATE_TIME, DOC_ALTER_TIME, DOC_CREATOR_ID, DOC_ALTEROR_ID, FD_DESCRIPTION, FD_ORDER, FD_NO, FD_TYPE, BUSI_SYS_ID, FD_IS_OPEN, FD_AUTHORIZE_TYPE)
values ('16071f730392e1ddf3b6eea486fa817b', '按节点统计', 'report/node/overdue', '', '1606cbcf6221b9b8920435c45ce8ac5a', 'x15ed1c00b8155803bc5ad444c4484516x1606cb9b335c8c6603a25e2402ea1ddbx1606cbcf6221b9b8920435c45ce8ac5ax16071f730392e1ddf3b6eea486fa817bx',1,0,sysdate,sysdate,'U50000002','U50000002', '根据流程节点统计逾期审批单据实例信息',300,'overdueNodeStatistics',3, '4028fb815f3295ef015f33389c6b0000', 1, 0);


-- 2017/10/17 菜单角色权限关系表
create table SYS_AUTH_MRA
(
  FD_ROLEID VARCHAR2(36 CHAR) not null,
  FD_MENUID VARCHAR2(36 CHAR) not null
);
-- Add comments to the columns 
comment on column SYS_AUTH_MRA.FD_ROLEID
  is '角色主键id';
comment on column SYS_AUTH_MRA.FD_MENUID
  is '菜单主键id';
  
-- 2017/10/17 菜单参数表
create table SYS_MENU_PARAM
(
  FD_ID      VARCHAR2(36 CHAR) not null,
  FD_MENU_ID VARCHAR2(36 CHAR),
  FD_NAME    VARCHAR2(255 CHAR),
  FD_VALUE   VARCHAR2(255 CHAR)
);
-- Add comments to the columns 
comment on column SYS_MENU_PARAM.FD_MENU_ID
  is '菜单ID';
comment on column SYS_MENU_PARAM.FD_NAME
  is '参数名称';
comment on column SYS_MENU_PARAM.FD_VALUE
  is '参数值';

 -- 2017/12/21节点统计报表表创建
 -- Create table
create table SYS_NODE_STATISTICS_INFO
(
  FD_ID             VARCHAR2(100) not null,
  FD_FACT_NODE_ID   VARCHAR2(50 CHAR),
  FD_FACT_NODE_NAME VARCHAR2(200 CHAR),
  FD_TEMPLATE_ID    VARCHAR2(100),
  FD_TEMPLATE_NAME  VARCHAR2(100),
  FD_AVG_TIME       VARCHAR2(20),
  FD_OVERDUE_NUMBER VARCHAR2(20),
  FD_OVERDUE_AVG    VARCHAR2(20),
  FD_ALERT_TIME     VARCHAR2(20)
);

-- Add comments to the columns 
comment on column SYS_NODE_STATISTICS_INFO.FD_ID is '主键';
comment on column SYS_NODE_STATISTICS_INFO.FD_FACT_NODE_ID  is '节点ID';
comment on column SYS_NODE_STATISTICS_INFO.FD_FACT_NODE_NAME  is '节点名称';
comment on column SYS_NODE_STATISTICS_INFO.FD_TEMPLATE_ID  is '模板id';
comment on column SYS_NODE_STATISTICS_INFO.FD_TEMPLATE_NAME  is '模板名称';
comment on column SYS_NODE_STATISTICS_INFO.FD_AVG_TIME  is '平均处理时长';
comment on column SYS_NODE_STATISTICS_INFO.FD_OVERDUE_NUMBER  is '超期数';
comment on column SYS_NODE_STATISTICS_INFO.FD_OVERDUE_AVG  is '平均超期时长';
comment on column SYS_NODE_STATISTICS_INFO.FD_ALERT_TIME  is '预警设置时长';


--2017-08-03 通用岗位管理 
create or replace view position_managemet_v as
select t.fd_id,
       t.fd_name,
       r.fd_plugin,
       r.fd_parameter,
       r.fd_rtn_value,
       r.fd_is_multiple,
       t.fd_memo,
       t.fd_is_available
from SYS_ORG_ELEMENT t left join sys_org_role r 
on r.fd_id = t.fd_id where t.fd_org_type = 32
and r.fd_role_conf_id is null;

--2017-08-03 变量设置 视图 
create or replace view sys_var_info_v as
select a.*,
   (select b.fd_name from SYS_ORG_ELEMENT b where a.var_creator_id=b.fd_id) as fd_name,
   (select c.fd_name from sys_category_main c where a.var_use_type=c.fd_id) as var_use_type_name
 from sys_wf_var_info a;
  
--2017-08-03 业务系统配置视图
create or replace view sys_busi_sys_v as
select a.*,
   (select b.fd_name from SYS_ORG_ELEMENT b where a.fd_created_by=b.fd_id) as created_name
from sys_busi_sys a; 

--2017-08-03 组织架构层级 
--create or replace view sys_org_element_allpath_v as
--select * from (
--   select
--    fd_id,
--    fd_parentid,
--    substr(sys_connect_by_path(fd_name, '/'),2) name_zh,
--    substr(prior sys_connect_by_path(fd_name, '/'),2) org_name,
--    substr(prior sys_connect_by_path(fd_id, 'x'),2) org_id,
--    fd_org_type
--    from sys_org_element
--    start with fd_org_type = '1'
--    connect by prior fd_id = fd_parentid) t;
    
--2017-08-03 通用流程模板
create or replace view sys_wf_common_template_v as
select a.*,b.fd_name as user_name from sys_wf_common_template a left join sys_org_element b on a.fd_creator_id=b.fd_id;

--2017-08-03 群组成员视图
create or replace view group_admin as
select rownum as id,
   a.fd_id,
   a.fd_name,
   a.deal_name as fd_member_name,
   a.fd_is_available ,
   a.fd_cateid,
   b.fd_name as group_name,
   a.fd_memo
from (select * from sys_org_element a  left join
   (select e.fd_groupid,WM_CONCAT(a.fd_name) deal_name
   from sys_org_group_element e,sys_org_element a
   where  e.fd_elementid=a.fd_id
   group by e.fd_groupid) c
   on a.fd_id=c.fd_groupid
   ) a
   left join sys_org_group_cate b on a.fd_cateid=b.fd_id
where a.fd_org_type=16;
       
--2017-08-03 角色设置
create or replace view role_set_v as
select a."FD_ID",a."FD_PLUGIN",a."FD_PARAMETER",a."FD_IS_MULTIPLE",a."FD_RTN_VALUE",a."FD_ROLE_CONF_ID",
   b.fd_name,
   c.fd_id as cate_id,
   c.fd_name as cate_name,
   b.fd_is_available,
   b.fd_memo
from sys_org_role a,
 	sys_org_element b,
 	sys_org_role_conf c
where a.fd_id=b.fd_id
     and b.fd_org_type='32'
     and a.fd_role_conf_id=c.fd_id;
         
--2017-08-03 角色分配视图
create or replace view role_assign_v as
select a.fd_id,
   a.fd_name,
   a.fd_description,
   b.fd_name as category_name,
   b.fd_id as category_id,
   c.fd_name as creator_name
from sys_auth_role a
     left join sys_auth_category b on a.fd_category_id=b.fd_id,
     sys_org_element c
where a.fd_alias is null
  and a.fd_creatorid=c.fd_id;
      
--2017-08-03 角色类型视图
create or replace view role_cate_v as
select a.fd_id,
   a.fd_name,
   b.fd_name as creator_name,
   a.doc_create_time as create_time
from sys_auth_category a,
 	sys_org_element b
where a.doc_creator_id=b.fd_id;

--2017-08-03 角色线与成员
create or replace view role_line_member_v as
select a.fd_id,
   a.fd_name,
   a.fd_order,
   a.fd_role_line_conf_id,
   a.member_name,
   b.parent_name,
   c.fd_name as conf_name
from (select a.fd_id,
      a.fd_name,
      a.fd_order,
      a.fd_role_line_conf_id,
      b.fd_name as member_name,
      a.fd_parent_id
     from sys_org_element b,
      	sys_org_role_line a
     where a.fd_member_id=b.fd_id(+))a,
     (select a.fd_id,nvl2(a.fd_name,nvl2(b.fd_name,concat(concat(concat(a.fd_name,'('),b.fd_name),')'),a.fd_name),b.fd_name) as parent_name
      from sys_org_role_line a,sys_org_element b
      where a.fd_member_id=b.fd_id(+)) b,sys_org_role_conf c
where a.fd_parent_id=b.fd_id(+) and
   a.fd_role_line_conf_id=c.fd_id;

--2017-08-03 流程审批历史
create or replace view process_history_v as
select a.fd_id,
   c.fd_id as wf_instance_id,
   a.fd_fact_node_id as wf_node_id,
   a.fd_fact_node_name as wf_node_name,
   b.fd_no as wf_audit_user_no,
   b.fd_name as wf_audit_user_name,
   a.fd_audit_note as wf_audit_mind,
   case when instr(a.fd_action_info,'sysWfOperations.fdOperType.draft.submit',1,1) > 0 then '提交单据'
   when instr(a.fd_action_info,'sysWfNode.opInfo.handler.modify',1,1) > 0 then replace(a.fd_action_info,'sysWfNode.opInfo.handler.modify','将处理人')
   else  a.fd_action_info end as wf_audit_result,
   a.fd_create_date as wf_audit_date,
   0 as wf_audit_spacing_interval
from sys_wf_audit_note a,
   sys_org_element b,
   sys_wf_process c
where a.fd_handler_id = b.fd_id and
   a.fd_process_id = c.fd_id
   order by a.fd_create_date;

--2017-08-03 流程审批日志
create or replace view process_log_v as
select a.fd_id,b.fd_id as wf_instance_id,
   c.fd_node_id as wf_node_id,
   c.fd_fact_node_name as wf_node_name,
   e.fd_id as wf_op_user_no,
   e.fd_name as wf_op_user_name,
   c.fd_route_type as wf_op_code,
   c.fd_fact_node_name as wf_op_name,
   case when d.fd_action_name='%sysWfOperations.fdOperType.draft.submit%' then '提交单据'
   else d.fd_action_name end as wf_op_info,
   a.doc_create_time as wf_op_time
from sys_wf_expecter_log a,
   sys_wf_process b,
   sys_wf_history_node c,
   sys_wf_history_workitem d,
   sys_org_element e
where a.fd_wf_process_id = b.fd_id and
   b.fd_id = c.fd_process_id and
   a.fd_node_id = c.fd_fact_node_id and
   c.fd_id = d.fd_history_id and
   d.fd_handler_id = e.fd_id; 
      
--2017-08-28 待阅视图   
create or replace view process_read_v as
select a.fd_id||'_'||nvl(y.fd_id,z.fd_id) as fd_id,
    a.fd_id as todo_id,
    a.fd_subject,
    c.fd_id as wf_instance_id,
    nvl(y.fd_no,z.fd_no) as read_user_no,
    nvl(y.fd_name,z.fd_name) as read_user_name,
    case when y.fd_id is null and z.fd_id is not null then 1 
         when y.fd_id is not null and z.fd_id is null then 0
 	end as is_read,
    c.fd_news_source as source_system_id,
    decode(c.fd_news_source,'FSSC-Eas','电子报账系统') as source_system_name,
    c.apply_code as apply_code,
    e.fd_no as apply_user_no,
    e.fd_name as apply_user_name,
    c.fd_template_id as wf_template_id,
    c.doc_create_time as start_apply_date,
    c.doc_create_time as apply_date,
    c.doc_create_time as end_apply_date,
    c.doc_subject as apply_subject,
    c.fd_description as apply_reason,
    c.doc_content as apply_content,
    c.fd_importance as urgent_level,
    c.doc_status as apply_status,
    c.fd_description as wf_description,
    c.fd_link_url as wf_busi_url,
    (select fd_node_name from process_current_node_v where wf_instance_id = c.fd_id) as node_names,
    (select fd_handler_name from process_current_node_v where wf_instance_id = c.fd_id) as deal_name,
    c.attr1 as attr1,
    c.attr2 as attr2,
    c.attr3 as attr3,
    c.attr4 as attr4,
    c.attr5 as attr5,
    c.attr6 as attr6,
    c.attr7 as attr7,
    c.attr8 as attr8,
    c.attr9 as attr9,
    c.attr10 as attr10,
    c.attr11 as attr11,
    c.attr12 as attr12,
    c.attr13 as attr13,
    c.attr14 as attr14,
    c.attr15 as attr15,
    c.attr16 as attr16,
    c.attr17 as attr17,
    c.attr18 as attr18,
    c.attr19 as attr19,
    c.attr20 as attr20
from sys_notify_todo a,
    sys_news_main c,
    sys_org_element e,
    (select te.fd_id,te.fd_name,te.fd_no,t.fd_todoid from sys_notify_todotarget t,sys_org_element te where t.fd_orgid=te.fd_id) y,
    (select de.fd_id,de.fd_name,de.fd_no,d.fd_todoid from sys_notify_todo_done_info d,sys_org_element de where de.fd_id=d.fd_elementid) z
where c.fd_id = a.fd_model_id
    and c.doc_creator_id = e.fd_id
    and a.fd_type = '2'
    and a.fd_id=y.fd_todoid(+)
    and a.fd_id=z.fd_todoid(+)
order by a.fd_create_time desc;

--2017-08-28 流程授权扩展表
create table SYS_WF_AUTHORIZE_EXTEND(
  FD_ID           VARCHAR2(36) not null,
  FD_AUTHORIZE_ID VARCHAR2(36),
  FD_BOE_TYPE     VARCHAR2(36),
  FD_BUSS_TYPE    VARCHAR2(36),
  FD_DEPT_ID      VARCHAR2(36),
  MIN_AMOUNT      NUMBER(20,2),
  MAX_AMOUNT      NUMBER(20,2)
);
alter table SYS_WF_AUTHORIZE_EXTEND add constraint PK_EXTEND_ID primary key (FD_ID) ;
alter table SYS_WF_AUTHORIZE_EXTEND add constraint FK_AUTHORITY_ID foreign key (FD_AUTHORIZE_ID) references SYS_WF_AUTHORIZE (FD_ID);

--2017-12-15 角色线视图
create or replace view sys_org_role_line_v as
select nvl2(a.fd_name,nvl2(b.fd_name,concat(concat(concat(a.fd_name,'('),b.fd_name),')'),a.fd_name),b.fd_name) as new_name,
  a."FD_ID",a."FD_NAME",a."FD_ORDER",a."FD_CREATE_TIME",a."FD_HIERARCHY_ID",a."FD_MEMBER_ID",a."FD_ROLE_LINE_CONF_ID",a."FD_PARENT_ID",a.fd_is_leaf,
  b.fd_name as member_name,
  b.fd_org_type,
  b.fd_is_available
from sys_org_role_line a,
  sys_org_element b
where 
a.fd_is_available=1 and
a.fd_member_id=b.fd_id(+);

   
-- 2017-09-20 待办视图   
create or replace view v_cms_task as
select nt.fd_id as fd_id,
   n.fd_process_id as wf_instance_id,
   m.apply_code,
   m.fd_news_source,
   m.doc_status,
   e.fd_no as task_user_no,
   e.fd_name as task_user_name,
   d.fd_no as app_user_no,
   d.fd_name as app_user_name,
   m.doc_create_time as app_date,
   m.doc_publish_time as publish_date,
   n.fd_fact_node_name as node_names,
   e.fd_name as deal_name,
   t.fd_id as fd_token_id,
   m.doc_subject as app_title,
   m.fd_description as apply_reason,
   m.fd_importance as urgent_level,
   m.fd_link_url as wf_busi_link,
   m.fd_template_id,
   m.fd_description,
   m.doc_content,
   n.fd_fact_node_id as node_id,
   n.fd_fact_node_name as node_name,
   to_char(n.fd_parameter) as operator_names,
   m.attr1,m.attr2,m.attr3,m.attr4,m.attr5,
   m.attr6,m.attr7,m.attr8,m.attr9,m.attr10,
   m.attr11,m.attr12,m.attr13,m.attr14,m.attr15,
   m.attr16,m.attr17,m.attr18,m.attr19,m.attr20
from sys_news_main m,
     sys_wf_node n,
     sys_wf_token t,
     sys_org_element d,
     sys_org_element e,
     sys_notify_todo nt,
     sys_notify_todotarget ntt
where nt.fd_id = ntt.fd_todoid
  and nt.fd_type = '1'
  and t.fd_status = 'active'
  and m.doc_status in ('11','20')
  and nt.fd_parameter1 = n.fd_id
  and m.fd_id = n.fd_process_id
  and t.fd_node_instance_id  = n.fd_id
  and d.fd_id = m.doc_creator_id
  and e.fd_id = ntt.fd_orgid
  order by m.doc_create_time desc;
       

--2018-01-12 流程当前节点视图
create or replace view process_current_node_v as
select p.fd_id as wf_instance_id,
   (select listagg(tg.fd_orgid, ',') within  group(order by tg.fd_orgid)
       from sys_notify_todo td, sys_notify_todotarget tg,sys_wf_node n
       where td.fd_key = p.fd_id
           and n.fd_id = td.fd_parameter1
           and n.fd_process_id = p.fd_id
           and td.fd_type = 1
           and td.fd_id = tg.fd_todoid) as fd_handler_id,
   (select listagg(e.fd_name, ',') within  group(order by e.fd_id)
       from sys_org_element e,sys_wf_node n,sys_notify_todo td, sys_notify_todotarget tg
       where td.fd_key = p.fd_id
           and n.fd_process_id = p.fd_id
           and n.fd_id = td.fd_parameter1
           and e.fd_id = tg.fd_orgid
           and td.fd_type = 1
           and td.fd_id = tg.fd_todoid) as fd_handler_name,
   (select listagg(c.fd_fact_node_id, ',')  within  group(order by c.fd_fact_node_id) from sys_wf_node c where c.fd_process_id=p.fd_id) as fd_node_id,
   (select listagg(c.fd_fact_node_name, ',')  within  group(order by c.fd_fact_node_id) from sys_wf_node c where c.fd_process_id=p.fd_id) as fd_node_name
from sys_wf_process p
where p.fd_status <> '10';
  
-- 2017-09-25 我的单据视图
create or replace view document_submitted_v as
select a.fd_id,a.doc_subject,a.fd_description,a.fd_news_source,a.doc_create_time,a.doc_alter_time,a.doc_publish_time,
     a.doc_read_count,a.fd_top_end_time,a.fd_main_picture,a.fd_top_time,a.fd_is_link,a.fd_is_pic_news,a.fd_key,
     a.fd_model_name,a.fd_model_id,a.fd_link_url,a.auth_reader_flag,a.fd_is_rolls,a.fd_is_top,a.doc_status,
     a.fd_content_type,a.fd_html_content,a.fd_writer,a.fd_is_hide_subject,a.fd_last_modified_time,
     a.doc_creator_client_ip,a.auth_att_nodownload,a.auth_att_nocopy,a.auth_att_noprint,a.fd_change_att,
     a.fd_template_id,a.doc_creator_id,a.doc_alteror_id,a.doc_author_id,a.doc_dept_id,a.auth_area_id,
     a.fd_use_form,a.fd_top_days,a.fd_importance,a.apply_code,a.apply_descrption,a.apply_date,
     a.process_var_xml,a.fd_is_test,a.fd_is_pass,a.extend_file_path,a.doc_content,a.busi_sys_id,
     a.extend_data_xml,a.enabled,b.fd_no as creator_user_no,b.fd_name as creator_user_name,
     (select fd_node_name from process_current_node_v where wf_instance_id = a.fd_id) as node_names,
     (select fd_handler_name from process_current_node_v where wf_instance_id = a.fd_id) as deal_name,
     a.attr1,a.attr2,a.attr3,a.attr4,a.attr5,a.attr6,a.attr7,a.attr8,a.attr9,a.attr10,
     a.attr11,a.attr12,a.attr13,a.attr14,a.attr15,a.attr16,a.attr17,a.attr18,a.attr19,a.attr20
from (select * from sys_news_main snm order by snm.doc_create_time desc) a,sys_org_element b
where a.doc_creator_id = b.fd_id;

--2017-09-25 已办视图
create or replace view audit_bill_v as
select c.fd_id,
   c.apply_code,
   c.fd_news_source,
   c.doc_status,
   b.fd_todoid,
   e.fd_no as task_user_no,
   e.fd_name as task_user_name,
   d.fd_no as app_user_no,
   d.fd_name as app_user_name,
   c.doc_creator_id,
   d.fd_name as doc_creator_name,
   (select fd_node_name from process_current_node_v where wf_instance_id = c.fd_id) as node_name,
   (select fd_handler_name from process_current_node_v where wf_instance_id = c.fd_id) as deal_name,
   c.doc_create_time,
   c.doc_publish_time,
   c.fd_model_id,
   c.fd_template_id,
   c.doc_subject,
   c.fd_importance,
   c.fd_description,
   c.doc_content,
   c.fd_description as apply_reason,
   c.attr1,c.attr2,c.attr3,c.attr4,c.attr5,
   c.attr6,c.attr7,c.attr8,c.attr9,c.attr10,
   c.attr11,c.attr12,c.attr13,c.attr14,c.attr15,
   c.attr16,c.attr17,c.attr18,c.attr19,c.attr20
from sys_notify_todo a,
 	sys_notify_todo_done_info b,
 	sys_news_main c,
 	sys_org_element d,
 	sys_org_element e
where b.fd_todoid = a.fd_id 
  and b.fd_elementid = e.fd_id
  and  d.fd_id = c.doc_creator_id
  and c.fd_id = a.fd_model_id
order by c.doc_create_time desc;
 
-- 2017-09-25 单据基础信息
create or replace view sys_news_basic_v as
select a.fd_id,
       a.doc_subject as title,
       b.fd_id as sys_news_template_fd_id,
       b.fd_name as template_name,
       a.fd_importance,
       c.fd_id as fd_login_name,
       c.fd_name as creator_name,
       a.doc_create_time,
       a.doc_read_count,
       a.apply_code,
       d.fd_name as busy_name
from sys_news_main a,
   sys_news_template b,
   sys_org_element c,
   sys_busi_sys d
where a.fd_template_id = b.fd_id(+)
   and a.doc_creator_id = c.fd_id(+)
   and a.busi_sys_id = d.fd_id;

-- 2017-09-25 流程日志信息
create or replace view sys_wf_exception_log_v as
select l.fd_id,
   l.fd_process_id,
   m.doc_subject as fd_title,
   m.apply_code as fd_apply_code,
   l.fd_handler_id,
   l.fd_handler_name,
   e.fd_name as fd_doc_creator_name,
   m.doc_status as fd_status,
   l.fd_reason,
   l.fd_message,
   l.fd_exception,
   l.fd_fact_node_id,
   l.fd_fact_node_name,
   m.fd_template_id,
   t.fd_name as fd_template_name,
   m.busi_sys_id as fd_busi_sys_id,
   b.fd_name as fd_busi_sys_name,
   l.fd_create_time
from sys_wf_exception_log l,
   sys_news_main m,
   sys_busi_sys b,
   sys_news_template t,
   sys_org_element e
where l.fd_process_id=m.fd_id(+) and
   m.busi_sys_id=b.fd_id and
   t.fd_id=m.fd_template_id and
   e.fd_id=m.doc_creator_id;
   
-- 2017-09-26 组织架构层级视图
create or replace view sys_org_element_allpath_v as
select "FD_ID","FD_PARENTID","NAME_ZH","ORG_NAME","ORG_ID","FD_ORG_TYPE","FD_IS_AVAILABLE" from (
   select
    fd_id,
    fd_parentid,
    fd_is_available,
    substr(sys_connect_by_path(fd_name, '/'),2) name_zh,
    substr(prior sys_connect_by_path(fd_name, '/'),2) org_name,
    substr(prior sys_connect_by_path(fd_id, 'x'),2) org_id,
    fd_org_type
    from sys_org_element
    start with fd_org_type = '1'
    connect by prior fd_id = fd_parentid) t;

-- 2017-10-12 接口信息表
create table SYS_WF_INTERFACE
(
  fd_id        VARCHAR2(36 CHAR) not null,
  fd_url       VARCHAR2(100 CHAR),
  fd_name      VARCHAR2(200 CHAR) not null,
  fd_parent_id VARCHAR2(36 CHAR),
  fd_is_leaf   NUMBER not null,
  fd_code      VARCHAR2(100 CHAR) not null
);

-- 2017-10-12 接口信息业务系统关系表
create table SYS_WF_BUSI_INTERFACE
(
  fd_id           VARCHAR2(36 CHAR) not null,
  fd_busi_id      VARCHAR2(36 CHAR) not null,
  fd_interface_id VARCHAR2(200 CHAR),
  fd_is_back      VARCHAR2(1 CHAR),
  fd_back_address VARCHAR2(200 CHAR),
  fd_busi_type    VARCHAR2(1 CHAR)
);

-- 2017-10-12 接口参数表
create table SYS_WF_INTERFACE_PARAM
(
  fd_id           VARCHAR2(36 CHAR) not null,
  fd_interface_id VARCHAR2(36 CHAR),
  fd_name         VARCHAR2(255 CHAR),
  fd_value        VARCHAR2(255 CHAR)
);
  
 --2017/10/19 阅读记录视图
create or replace view sys_read_log_v as
select
         l.fd_reader_id,
         e1.fd_name as fd_reader_name,
         e2.fd_id as fd_reader_dept_id,
         e2.fd_name as fd_reader_dept_name,
         l.fd_id,
         l.fd_read_time,
         l.fd_reader_client_ip,
         l.fd_model_id,
         l.fd_read_type
from
         sys_org_element e1,
         sys_org_element e2,
         sys_read_log l

where
         l.fd_reader_id=e1.fd_id and
         e1.fd_parentid=e2.fd_id(+)
         order by l.fd_read_time desc;

--2017/10/25 流程模板试图
create or replace view sys_news_template_v as
select a.fd_id,a.fd_name,
    a.doc_create_time,a.fd_importance,
    a.fd_number_prefix,a.doc_content,
    a.fd_use_form,a.fd_app_link,
    a.auth_reader_flag,a.auth_tmp_att_nodownload,
    a.auth_tmp_att_nocopy,a.fd_status,a.auth_tmp_att_noprint,
    a.fd_order,a.fd_style,a.fd_content_type,
    a.fd_category_id,a.doc_creator_id,a.auth_area_id,
    a.busi_sys_id,a.auth_not_reader_flag,
    a.fd_hierarchy_id,a.doc_alteror_id,a.doc_alter_time,
    a.fd_isinherit_maintainer,a.fd_isinherit_user,a.fd_change_att,a.fd_parent_id,
    b.fd_id as creator_id,
    b.fd_name as creator_name,
    c.fd_id as busi_id,
    c.fd_name as busi_name ,
    c.fd_code as busi_code,
    d.fd_name as category_name,
    e.fd_flow_content
from sys_news_template a,
    sys_org_element b,
    sys_busi_sys c,
    sys_category_main d,
    sys_wf_template e
where a.doc_creator_id=b.fd_id
  and a.busi_sys_id=c.fd_id
  and a.fd_category_id=d.fd_id
  and a.fd_id=e.fd_model_id
  order by a.fd_number_prefix;

--2017/10/26 LaiJiashen 流程授权视图
create or replace view sys_wf_authorizer_v as
select a."FD_ID",a."FD_AUTHORIZE_TYPE",a."FD_START_TIME",a."FD_END_TIME",a."FD_EXPIRE_DELETED",a."FD_CREATE_TIME",a."FD_AUTHORIZER",a."FD_CREATOR",a."FD_AUTHORIZED_PERSON",
   (select fd_name from sys_org_element e where e.fd_id=a.fd_authorizer) as fd_authorizer_Name,
   (select fd_name from sys_org_element e where e.fd_id=a.fd_authorized_person) as fd_authorizer_person_Name,
   (select listagg(q.fd_authorize_cate_id,',')  within group(order by q.fd_authorize_cate_id) from sys_wf_authorize_scope q where a.fd_id=q.fd_authorize_id) as fd_authorize_cate_showtext_Id,
   (select listagg(fd_authorize_cate_showtext,',')  within group(order by fd_authorize_cate_id) from sys_wf_authorize_scope where a.fd_id=fd_authorize_id) as fd_authorize_cate_showtext,
   (select listagg(e.fd_id,',') within group(order by fd_authorize_org_id) from sys_wf_authorize_item left join sys_org_element e on fd_authorize_org_id=e.fd_id where a.fd_id=fd_authorize_id) as fd_Authorize_Org_Id,
   (select listagg(e.fd_name,',') within group(order by s.fd_authorize_org_id) from sys_wf_authorize_item s left join sys_org_element e on s.fd_authorize_org_id=e.fd_id where a.fd_id=fd_authorize_id) as fd_Authorize_Org_name
from sys_wf_authorize a;    

    
--2017/10/26 接口信息
insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d000', null, '流程接口', null, 0, 'INTERFACE');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d100', null, '导入类', '15d5efcccda5c9a016c5a8a473d9d000', 0, 'IMPORT_INTERFACE');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d101', '', '组织导入接口', '15d5efcccda5c9a016c5a8a473d9d100', 1, 'SbWFOrgImportSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d102', '', '岗位导入接口', '15d5efcccda5c9a016c5a8a473d9d100', 1, 'SbWFPostImportSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d103', '', '人员导入接口', '15d5efcccda5c9a016c5a8a473d9d100', 1, 'SbWFPersonImportSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d200', null, '查询类', '15d5efcccda5c9a016c5a8a473d9d000', 0, 'QUERY_INTERFACE');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d201', '/ws/sbWFInquiryProcessStatusSrv/sbWFInquiryProcessStatusSrv.wsdl', '查询流程状态接口', '15d5efcccda5c9a016c5a8a473d9d200', 1, 'SbWFInquiryProcessStatusSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d202', '/ws/sbWFInquiryTaskSrv/sbWFInquiryTaskSrv.wsdl', '查询待办接口', '15d5efcccda5c9a016c5a8a473d9d200', 1, 'SbWFInquiryTaskSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d203', '/ws/sbWFInquiryTaskDoneSrv/sbWFInquiryTaskDoneSrv.wsdl', '查询已办接口', '15d5efcccda5c9a016c5a8a473d9d200', 1, 'SbWFInquiryTaskDoneSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d204', '/ws/sbWFInquiryApplySrv/sbWFInquiryApplySrv.wsdl', '查询我的申请接口', '15d5efcccda5c9a016c5a8a473d9d200', 1, 'SbWFInquiryApplySrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d205', '/ws/sbWFInquiryProcessHistorySrv/sbWFInquiryProcessHistorySrv.wsdl', '查询审批历史接口', '15d5efcccda5c9a016c5a8a473d9d200', 1, 'SbWFInquiryProcessHistorySrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d206', '/ws/sbWFInquiryProcessLogSrv/sbWFInquiryProcessLogSrv.wsdl', '查询流程日志接口', '15d5efcccda5c9a016c5a8a473d9d200', 1, 'SbWFInquiryProcessLogSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d207', '', '常用审批意见接口', '15d5efcccda5c9a016c5a8a473d9d200', 1, 'SbWFInquiryCommonOpinionSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d208', '/ws/sbWFGetNodesSrv/sbWFGetNodesSrv.wsdl', '查询流程节点信息接口', '15d5efcccda5c9a016c5a8a473d9d200', 1, 'SbWFGetNodesSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d209', '', '查询流程模板接口', '15d5efcccda5c9a016c5a8a473d9d200', 1, 'SbWFInquiryTemplateSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d210', '/ws/sbWFApprovalPersonSrv/sbWFApprovalPersonSrv.wsdl', '查询指派节点接口', '15d5efcccda5c9a016c5a8a473d9d200', 1, 'SbWFApprovalPersonSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d211', '/ws/sbWFInquiryReadSrv/sbWFInquiryReadSrv.wsdl', '查询待阅接口', '15d5efcccda5c9a016c5a8a473d9d200', 1, 'SbWFInquiryReadSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d212', '/ws/sbWFInquiryRejectNodeSrv/sbWFInquiryRejectNodeSrv.wsdl', '查询驳回节点接口', '15d5efcccda5c9a016c5a8a473d9d200', 1, 'SbWFInquiryRejectNodeSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d213', '/ws/sbWFNextNodesSrv/sbWFNextNodesSrv.wsdl', '查询下一审批节点接口', '15d5efcccda5c9a016c5a8a473d9d200', 1, 'SbWFNextNodesSrv');


insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d300', null, '处理类', '15d5efcccda5c9a016c5a8a473d9d000', 0, 'OPERATOR_INTERFACE');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d310', '/ws/sbWFStartProcessSrv/sbWFStartProcessSrv.wsdl', '流程启动接口', '15d5efcccda5c9a016c5a8a473d9d300', 1, 'SbWFStartProcessSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d311', '/ws/sbWFApprovalSrv/sbWFApprovalSrv.wsdl', '流程审批接口', '15d5efcccda5c9a016c5a8a473d9d300', 1, 'SbWFApprovalSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d312', '/ws/sbWFNodeApprovalSrv/sbWFNodeApprovalSrv.wsdl', '节点审批接口', '15d5efcccda5c9a016c5a8a473d9d300', 1, 'SbWFNodeApprovalSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d313', '/ws/sbWFInstanceApprovalSrv/sbWFInstanceApprovalSrv.wsdl', '实例审批接口', '15d5efcccda5c9a016c5a8a473d9d300', 1, 'SbWFInstanceApprovalSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d314', '/ws/sbWFAuthorizeSrv/sbWFAuthorizeSrv.wsdl', '流程授权接口', '15d5efcccda5c9a016c5a8a473d9d300', 1, 'SbWFAuthorizeSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d315', '', '回收授权接口', '15d5efcccda5c9a016c5a8a473d9d300', 1, 'SbWFRevokeAuthorizeSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d316', '/ws/sbWFAbandonSrv/sbWFAbandonSrv.wsdl', '起草人废弃接口', '15d5efcccda5c9a016c5a8a473d9d300', 1, 'SWFAbandonSrv');


insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d317', '/ws/sbWFApprovalHandlerSrv/sbWFApprovalHandlerSrv.wsdl', '指派审批接口', '15d5efcccda5c9a016c5a8a473d9d300', 1, 'SbWFApprovalhandlerSrv');

insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d318', '', '流程授权转交接口', '15d5efcccda5c9a016c5a8a473d9d300', 1, 'SbWFTransHandlerSrv');


insert into sys_wf_interface (FD_ID, FD_URL, FD_NAME, FD_PARENT_ID, FD_IS_LEAF, FD_CODE)
values ('15d5efcccda5c9a016c5a8a473d9d319', '/ws/sbWFChangeHandlerSrv/sbWFChangeHandlerSrv.wsdl', '修改当前处理人接口', '15d5efcccda5c9a016c5a8a473d9d300', 1, 'SbWFChangeHandlerSrv');

--2017/11/1 Lijiashen 模板分类视图
create or replace view categorymain_v as
select rownum as id ,
   a.fd_id,
   a.fd_name,
   a.fd_parent_id,
   a.fd_hierarchy_id,
   b.fd_name creator_name,
   a.doc_create_time create_time,
   e.fd_name alter_name,
   a.doc_alter_time alter_time
from sys_category_main a,
    SYS_ORG_ELEMENT b,
    (select c.fd_id,d.fd_name
    from sys_category_main c, SYS_ORG_ELEMENT d
    where c.doc_alteror_id=d.fd_id(+)) e
where a.doc_creator_id=b.fd_id(+) and
  a.fd_id=e.fd_id;

-- 2017/11/03 Laijiashen 检查重复角色视图
create or replace view repeat_post_on_roleconf_v as
select p1.person_id as person_id,
   p2.fd_name as person_name,
   p1.conf_id as conf_id,
   p1.post_ids as post_ids,
   p1.post_names as post_names
from (
      select a.fd_personid as person_id,a.conf_id,wm_concat(a.member_id) post_ids,wm_concat(a.path_name) post_names
      from (select r.* ,p.*
              from role_member_v r,
                sys_org_post_person p
              where r.fd_org_type=4 and
                   r.member_id=p.fd_postid
            ) a
          group by a.fd_personid,a.conf_id having count(a.fd_personid)>1
      ) p1,
      sys_org_element p2
where p1.person_id=p2.fd_id;

-- 2017/11/08 LaiJiashen 角色权限查询
create or replace view user_authority_v as
select
       au.id,
       au.user_org_id,
       au.role_id,
       au.role_name,
       substr(au.menu_auth_name,5) as menu_auth_name
from
      (select
              ro.ROWID as id,
              ur.fd_orgelementid as user_org_id,
              ro.fd_id as role_id,
              ro.fd_name as role_name,
              (select
                         listagg(me.new_name, ' ') within  group(order by me.fd_hierarchy_id,me.fd_order)
               from (select
                           case when m.fd_is_leaf=1 then m.fd_name
                                when m.fd_is_leaf=0 then '<br>【'||m.fd_name||'】'
                           end as new_name,
                           m.*,
                           mr.fd_roleid as mra_roleid
                    from
                           sys_menu m,
                           sys_auth_mra mr
                    where
                          m.fd_id=mr.fd_menuid order by m.fd_hierarchy_id,m.fd_order) me
                where ro.fd_id=me.mra_roleid(+)
               ) as menu_auth_name
      from
              sys_auth_role ro,
              sys_auth_ura ur
      where
              ro.fd_id = ur.fd_roleid and ro.fd_alias is null) au
              order by au.role_id
              ;


--	2017/12/19	 	模板统计报表
create table SYS_TEMPLATE_STATISTICS_INFO
(
  fd_id              VARCHAR2(100) not null,
  fd_category_id     VARCHAR2(100),
  fd_category_name   VARCHAR2(100),
  fd_template_id     VARCHAR2(100),
  fd_template_name   VARCHAR2(100),
  fd_instance_number VARCHAR2(20),
  fd_max_time        VARCHAR2(20),
  fd_min_time        VARCHAR2(20),
  fd_avg_time        VARCHAR2(20),
  fd_alert_time      VARCHAR2(20),
  fd_overdue_number  VARCHAR2(20),
  fd_overdue_avg     VARCHAR2(20),
  fd_overdue_rate    VARCHAR2(20),
  fd_start_date      TIMESTAMP(6),
  fd_end_date        TIMESTAMP(6)
)

alter table SYS_TEMPLATE_STATISTICS_INFO
add constraint PK_TEMPLATE_STATISTICS_ID primary key (FD_ID);


--2017/12/19 预警视图
create or replace view sys_wf_delay_v as
select
    f."FD_ID",f."FD_DELAY_TIME",f."FD_ENABLED",f."FD_TEMPLATE_ID",f."FD_CREATED_BY",f."FD_CREATED_DATE",f."FD_LAST_UPDATED_BY",f."FD_LAST_UPDATED_DATE",

    t.fd_name as fd_template_name


from
    sys_wf_delay_flow f,
    sys_news_template t

where
    f.fd_template_id=t.fd_id;

--2017/12/19 工作日历视图
create or replace view wf_monitor_calendar_v as
select
      c.fd_id,
      c.fd_name,
      c.fd_enabled,
      e1.fd_name as fd_creator_name,
      (select s.fd_year from wf_monitor_calendar_setting s where s.fd_calendar_id = c.fd_id and rownum =1) as fd_year,
      (select listagg(e2.fd_id,';') within group (order by e2.fd_id) from sys_org_element e2 where c.fd_id=e2.fd_calendar_id(+)) as fd_scope_ids,
      (select listagg(e2.fd_name,';') within group (order by e2.fd_id) from sys_org_element e2 where c.fd_id=e2.fd_calendar_id(+)) as fd_scope_names,
      c.fd_week_start,
      c.fd_week_end,
      c.fd_created_by,
      c.fd_created_date

from
      wf_monitor_calendar_main c,
      sys_org_element e1
where
      c.fd_created_by = e1.fd_id;

--2017/12/20 流程流转日志
create or replace view wf_log_v as
select rownum id,
   b.fd_id,
   c.fd_name,
   a.fd_handler_id,
   a.fd_fact_node_id,
   a.fd_fact_node_name,
   a.fd_action_id,
   a.fd_action_name,
   a.fd_action_info,
   a.fd_audit_note,
   a.fd_notify_type,
   a.fd_create_date,
   a.fd_identity_flag
from sys_wf_audit_note a,
   sys_news_main b,
   sys_org_element c
where a.fd_handler_id=c.fd_id(+) and
   a.fd_process_id=b.fd_id
   order by a.fd_create_date;
   
--2017/12/22 laijiashen 时间设置视图
create or replace view wf_monitor_time_work_v as
select
    m.fd_name as fd_calendar_name,
    e1.fd_name as fd_creator_name,
    (select listagg(mo.fd_month,';') within group (order by mo.fd_month) from wf_monitor_time_month mo where w.fd_id=mo.fd_time_id ) as fd_months,
    w."FD_ID",w."FD_NAME",w."FD_TIME_START",w."FD_TIME_END",w."FD_NOON_START",w."FD_NOON_END",w."FD_ENABLED",w."FD_CREATED_BY",w."FD_CALENDAR_ID",w."FD_CREATED_DATE",w."FD_LAST_UPDATED_BY",w."FD_LAST_UPDATED_DATE"

from
    wf_monitor_time_work w ,
    wf_monitor_calendar_main m,
    sys_org_element e1

where
    w.fd_created_by=e1.fd_id and
    w.fd_calendar_id=m.fd_id(+);
    
--2017/12/26 Laijiashen 历史模板视图
create or replace view sys_wf_history_template_v as
select
    m.fd_name as fd_category_name,
    'V'||h.fd_status||'.'||h.fd_version as fd_version_num,
    h.fd_id,
    h.fd_model_id,
    e.fd_name as fd_modify_name,
    h.fd_modify_time,
    h.fd_status

from
    sys_wf_history_template h,
    sys_news_template t,
    sys_category_main m,
    sys_org_element e

where
    m.fd_id=t.fd_category_id and
    h.fd_model_id=t.fd_id and
    h.fd_modify_id=e.fd_id and

    h.fd_status = '1';
    
--2018/1/3 LaiJiashen 角色线成员视图
create or replace view role_member_v as
select a.FD_ID as fd_id,
   a.new_name as fd_name,
   b.fd_id as conf_id,
   b.fd_name as conf_name,
   case  when a.fd_org_type is null then 0 else a.fd_org_type end as fd_org_type,
   case when a.fd_is_available is null then 1 else a.fd_is_available end as is_available,
   concat(b.fd_name, SYS_CONNECT_BY_PATH(a.new_name, '/')) as path_name,
   a.FD_PARENT_ID as parent_id,
   c.new_name as parent_name,
   a.FD_MEMBER_ID as member_id,
   a.member_name as member_name,
   a.FD_HIERARCHY_ID as hierarchy_id,
   a.fd_is_leaf,
   a.fd_is_available
from sys_org_role_line_v a,
   sys_org_role_conf b,
   sys_org_role_line_v c
where a.FD_ROLE_LINE_CONF_ID = b.fd_id and
   a.FD_PARENT_ID=c.FD_ID(+)
   start with a.FD_PARENT_ID is null
  connect by prior a.FD_ID = a.FD_PARENT_ID;

alter table SYS_WF_AUTHORIZE add is_all_scope varchar2(1) default '1';

--2018-01-12 LaiJiashen 已办单据视图
create or replace view audit_bill_v as
select 
   c.fd_id,
   c.apply_code,
   c.fd_news_source,
   c.doc_status,
   e.fd_no as task_user_no,
   e.fd_name as task_user_name,
   d.fd_no as app_user_no,
   d.fd_name as app_user_name,
   c.doc_creator_id,
   d.fd_name as doc_creator_name,
   (select fd_node_name from process_current_node_v where wf_instance_id = c.fd_id) as node_name,
   (select fd_handler_name from process_current_node_v where wf_instance_id = c.fd_id) as deal_name,
   c.doc_create_time,
   c.doc_publish_time,
   c.fd_model_id,
   c.fd_template_id,
   c.doc_subject,
   c.fd_importance,
   c.fd_description,
   c.doc_content,
   c.fd_description as apply_reason,
   c.attr1,c.attr2,c.attr3,c.attr4,c.attr5,
   c.attr6,c.attr7,c.attr8,c.attr9,c.attr10,
   c.attr11,c.attr12,c.attr13,c.attr14,c.attr15,
   c.attr16,c.attr17,c.attr18,c.attr19,c.attr20
from 
   (select td.fd_key ,ti.fd_elementid from sys_notify_todo td,sys_notify_todo_done_info ti where td.fd_id=ti.fd_todoid group by td.fd_key,ti.fd_elementid) a,
   sys_news_main c,
   sys_org_element d,
   sys_org_element e
where 
  a.fd_elementid = e.fd_id
  and  d.fd_id = c.doc_creator_id
  and c.fd_id = a.fd_key
order by c.doc_create_time desc;

--2018-01-22 当前工作事项
create or replace view process_current_workitem_v as
select a.fd_id as fd_node_id,a.fd_fact_node_id as fd_node_no,
       a.fd_fact_node_name as fd_node_name,b.fd_id as fd_workitem_id,
       e.fd_id as fd_user_id,e.fd_no as fd_user_no,e.fd_name as fd_user_name,
       f.fd_id as fd_token_id,a.fd_process_id
from fsp_fmc.sys_wf_node a,fsp_fmc.sys_wf_workitem b,
     fsp_fmc.sys_notify_todo c,fsp_fmc.sys_notify_todotarget d,
     fsp_fmc.sys_org_element e,fsp_fmc.sys_wf_token f
where a.fd_id = b.fd_node_id
      and a.fd_id = c.fd_parameter1 and b.fd_id = c.fd_parameter2
      and c.fd_id = d.fd_todoid
      and d.fd_orgid = e.fd_id
      and f.fd_node_instance_id = a.fd_id
      and c.fd_type = '1'
      and f.fd_status = 'active'
    group by a.fd_id,a.fd_fact_node_id,a.fd_fact_node_name,b.fd_id,e.fd_id,e.fd_no,e.fd_name,f.fd_id,a.fd_process_id;

--2018-01-22 待办视图优化功能    
create or replace view v_cms_task as
select w.fd_workitem_id as fd_id,
   w.fd_token_id,
   m.fd_id as wf_instance_id,
   m.apply_code,
   m.fd_news_source,
   m.doc_status,
   w.fd_user_no as task_user_no,
   w.fd_user_name as task_user_name,
   e.fd_no as app_user_no,
   e.fd_name as app_user_name,
   m.doc_create_time as app_date,
   m.doc_publish_time as publish_date,
   m.doc_subject as app_title,
   m.fd_description as apply_reason,
   m.fd_importance as urgent_level,
   m.fd_link_url as wf_busi_link,
   m.fd_template_id,
   m.fd_description,
   m.doc_content,
   w.fd_node_no as node_id,
   w.fd_node_name as node_name,
   '' as operator_names,
   m.attr1,m.attr2,m.attr3,m.attr4,m.attr5,
   m.attr6,m.attr7,m.attr8,m.attr9,m.attr10,
   m.attr11,m.attr12,m.attr13,m.attr14,m.attr15,
   m.attr16,m.attr17,m.attr18,m.attr19,m.attr20
from sys_news_main m,
     process_current_workitem_v w,
     sys_org_element e
where m.fd_id = w.fd_process_id
  and m.doc_status in ('11','20')
  and e.fd_id = m.doc_creator_id
  order by m.doc_create_time desc;

--2018-01-22流程授权视图优化
create or replace view sys_wf_authorizer_v as
select a.fd_id,a.fd_authorize_type,a.fd_start_time,a.fd_end_time,a.fd_expire_deleted,a.fd_create_time,a.fd_authorizer,a.fd_creator,a.fd_authorized_person,
   (select fd_name from sys_org_element e where e.fd_id=a.fd_authorizer) as fd_authorizer_name,
   (select fd_name from sys_org_element e where e.fd_id=a.fd_authorized_person) as fd_authorizer_person_name,
   (select xmlagg(xmlparse(content q.fd_authorize_cate_id||',' wellformed) order by q.fd_authorize_cate_id).getclobval() 
   from sys_wf_authorize_scope q where a.fd_id=q.fd_authorize_id) as fd_authorize_cate_showtext_Id,
   (select xmlagg(xmlparse(content fd_authorize_cate_showtext||',' wellformed) order by fd_authorize_cate_id).getclobval() 
   from sys_wf_authorize_scope where a.fd_id=fd_authorize_id) as fd_authorize_cate_showtext,
   (select xmlagg(xmlparse(content e.fd_id||',' wellformed) order by fd_authorize_org_id).getclobval() 
   from sys_wf_authorize_item left join sys_org_element e on fd_authorize_org_id=e.fd_id where a.fd_id=fd_authorize_id) as fd_Authorize_Org_Id,
   (select xmlagg(xmlparse(content e.fd_name||',' wellformed) order by s.fd_authorize_org_id).getclobval() 
   from sys_wf_authorize_item s left join sys_org_element e on s.fd_authorize_org_id=e.fd_id where a.fd_id=fd_authorize_id) as fd_Authorize_Org_name
from sys_wf_authorize a;

--2018-01-23 当前处理人
create or replace view process_current_handler_v as
select a.fd_id as fd_node_id,
       a.fd_fact_node_id as fd_node_no,
       a.fd_fact_node_name as fd_node_name,
       a.fd_process_id,
       b.fd_id as fd_token_id,
       e.fd_id as fd_user_id,
       e.fd_no as fd_user_no,
       e.fd_name as fd_user_name
from fsp_fmc.sys_wf_node a,
     fsp_fmc.sys_wf_token b,
     fsp_fmc.sys_notify_todo c,
     fsp_fmc.sys_notify_todotarget d,
     fsp_fmc.sys_org_element e
where a.fd_id = b.fd_node_instance_id
      and a.fd_id = c.fd_parameter1
      and c.fd_id = d.fd_todoid
      and d.fd_orgid = e.fd_id
      and c.fd_type = '1'
      and b.fd_status = 'active'
    group by a.fd_id,a.fd_fact_node_id,a.fd_fact_node_name,a.fd_process_id,b.fd_id,e.fd_id,e.fd_no,e.fd_name;
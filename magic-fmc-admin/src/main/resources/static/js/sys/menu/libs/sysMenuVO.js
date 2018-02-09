/**
 * 分类设置模型
 */

define([ "knockout","knockout-validation", "MsgBox" ],function(ko,MsgBox) {


SysMenu = function(options) {
	
	var setting = $.extend({
		fdId : null,
		fdName: null,
		fdNo: null,
		linkUrl : null,
		fdIsLeaf : null,
		fdIsAvailable : null,
		icon : null,
		fdHierarchyId: null,
		fdParentId: null,
		docCreateTime: null,
		docAlterTime: null,
		docCreateId: null,
		docAlterorId: null,
		fdOrder: null,
		fdDescription: null,
		fdType: null,
		fdParameter: null,
		busiSysId: null,
		fdIsOpen: null,
		fdAuthorizeType: null,
		path: null,
		paramList: []
	},options || {})
	
	return {
		self: this,
		fdId: setting.fdId,
		fdName: ko.observable(setting.fdName).extend({
            required: {
            	params:true,
            	message: "请输入菜单名称。" 
            }
        }),
        fdNo: ko.observable(setting.fdNo).extend({
            required: {
            	params:true,
            	message: "请输入菜单编码。" 
            }
        }),
        linkUrl : setting.linkUrl,
		fdIsLeaf : setting.fdIsLeaf,
		fdIsAvailable : ko.observable(setting.fdIsAvailable==null||setting.fdIsAvailable==0?'0':'1'),
		icon : setting.icon,
		fdHierarchyId: setting.fdHierarchyId,
		fdParentId: setting.fdParentId,
		docCreateTime: setting.docCreateTime,
		docAlterTime: setting.docAlterTime,
		docCreateId: setting.docCreateId,
		docAlterorId: setting.docAlterorId,
		fdOrder: setting.fdOrder,
		fdDescription: setting.fdDescription,
		fdType: ko.observable(setting.fdType==null?"0":setting.fdType).extend({
			notEqual:{
                params:"0",
                message:"请选择菜单类型。"
            }
        }),
        fdParameter: ko.observable(setting.fdParameter),
        busiSysId: setting.busiSysId,
		path: setting.path,
		fdIsOpen: ko.observable(setting.fdIsOpen==null||setting.fdIsOpen==0?'0':'1'),
		fdAuthorizeType: ko.observable(setting.fdAuthorizeType==null||setting.fdAuthorizeType==0?'0':'1'),
		paramList: ko.observableArray(setting.paramList)
		
	};
	
	};
return SysMenu;
	
})
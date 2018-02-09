/**
 * 角色线成员模型
 */

define([ "knockout" ],function(ko) {

OrgRoleLine = function(options) {
	
	var setting = $.extend({
		fdId : null,
		fdName: null,
		fdOrder: null,
		fdOrgType: null,
		fdCreateTime: null,
		fdMemberId: null,
		fdHierarchyId: null,
		fdRoleLineConfId: null,
		fdParentId: null,
		newName: null,
		memberName: null,
		parentName: null,
		fdIsLeaf: null,
		fdIsAvailable: null
	},options || {})
	
	return {
		fdId: setting.fdId,
		fdName: setting.fdName,
		fdOrder: setting.fdOrder,
		fdOrgType: setting.fdOrgType,
		fdCreateTime: setting.fdCreateTime,
		fdMemberId: setting.fdMemberId,
		fdHierarchyId: setting.fdHierarchyId,
		fdRoleLineConfId: setting.fdRoleLineConfId,
		fdParentId: setting.fdParentId,
		memberName: setting.memberName,
		newName: setting.newName,
		parentName: setting.parentName,
		fdIsLeaf: setting.fdIsLeaf,
		fdIsAvailable: setting.fdIsAvailable
	}
};
return OrgRoleLine;
})










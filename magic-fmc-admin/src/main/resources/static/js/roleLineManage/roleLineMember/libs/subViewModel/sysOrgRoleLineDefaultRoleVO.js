/**
 * 角色线成员默认岗位form
 */

define([ "knockout" ],function(ko) {

DefaultRole = function(options) {
	
	var setting = $.extend({
		fdId : null,
		fdRoleLineConfId: null,
		fdPersonId: null,
		fdPostId: null
	},options || {})
	
	return {
		fdId: setting.fdId,
		fdRoleLineConfId: setting.fdRoleLineConfId,
		fdPersonId: setting.fdPersonId,
		fdPostId: ko.observable(setting.fdPostId)
	}
};
return DefaultRole;
})

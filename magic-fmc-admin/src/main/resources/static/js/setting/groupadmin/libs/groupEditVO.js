define([ "knockout","knockout-validation" ], function(ko) {

	function GroupEdit(options) {

		var setting = $.extend({ 
			fdId :  null,
			fdCalendarId : null,
			fdCateid : null,
			fdCreateTime : null,
			fdFlagDeleted : null,
			fdHierarchyId : null,
			fdImportInfo : null, 
			fdIsAbandon : null,
			fdIsAvailable : null,
			fdIsBusiness : null,
			fdKeyword : null,
			fdLdapDn : null,
			fdMemo : null,
			fdName : null,
			fdNamePinyin : null,
			fdNo : null,
			fdOrder : null,
			fdOrgType : null,
			fdParentid : null,
			fdParentorgid : null,
			fdSuperLeaderid : null,
			fdThisLeaderid : null,
			groupElementArray :　[]
		},options || {})
		
		return { 
			fdId :  setting.fdId,
			fdCalendarId : setting.fdCalendarId,
			fdCateid : setting.fdCateid,
			fdCreateTime : setting.fdCreateTime,
			fdFlagDeleted : setting.fdFlagDeleted,
			fdHierarchyId : setting.fdHierarchyId,
			fdImportInfo : setting.fdImportInfo, 
			fdIsAbandon : setting.fdIsAbandon,
			fdIsAvailable : setting.fdIsAvailable,
			fdIsBusiness : setting.fdIsBusiness,
			fdKeyword : setting.fdKeyword,
			fdLdapDn : setting.fdLdapDn,
			fdMemo : setting.fdMemo,
			fdName : ko.observable(setting.fdName).extend({
                required: {
                	params:true,
                	message: "请输入群组名称。" 
                }
            }),
			fdNamePinyin : setting.fdNamePinyin,
			fdNo : setting.fdNo,
			fdOrder : setting.fdOrder,
			fdOrgType : setting.fdOrgType,
			fdParentid : setting.fdParentid,
			fdParentorgid : setting.fdParentorgid,
			fdSuperLeaderid : setting.fdSuperLeaderid,
			fdThisLeaderid : setting.fdThisLeaderid,
			groupElementArray : setting.groupElementArray
			
			
			
		};   

	}
	;
	return GroupEdit;

})
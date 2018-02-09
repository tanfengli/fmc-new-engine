define(["knockout","knockout-validation" ],function(ko) { 
	 
	OrgElement = function(options) { 
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
			fdIsLeaf : null,
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
			
			//机构信息
			fdPersonName : null,
			fdSuperLeaderName : null,
			//岗位信息
			fdPersonId : null,
			fdPersonName : null,
			fdDeptName : null,
			fdThisLeaderName : null,
			_editorArray: [],
			
			//人员信息
			fdEmail : null,
			fdMobileNo : null,
			fdWorkPhone :  null,
			fdLoginName : null,
			fdDefaultLang : null, 
			fdRtxNo : null,
			fdCardNo : null,
			fdPostIds : null,
			fdPostNames : null,
			selectType : null
			
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
			fdIsAvailable : ko.observable(setting.fdIsAvailable==1?"1":"0"),
			fdIsBusiness : setting.fdIsBusiness,
			fdIsLeaf : setting.fdIsLeaf,
			fdKeyword : setting.fdKeyword,
			fdLdapDn : setting.fdLdapDn,
			fdMemo : setting.fdMemo,
			fdName : ko.observable(setting.fdName).extend({
                required: {
                	params:true,
                	message: "请输入名称" 
                }
            }),
			fdNamePinyin : setting.fdNamePinyin,
			fdNo : ko.observable(setting.fdNo).extend({
                required: {
                	params:true,
                	message: "请输入编号" 
                }
            }),
			fdOrder : setting.fdOrder,
			fdOrgType : setting.fdOrgType,
			fdParentid : setting.fdParentid,
			fdParentorgid : setting.fdParentorgid,
			fdSuperLeaderid : setting.fdSuperLeaderid,
			fdThisLeaderid : ko.observable(setting.fdThisLeaderid),
			
			//机构信息
			fdPersonName : setting.fdPersonName,
			fdSuperLeaderName : setting.fdSuperLeaderName,
			//岗位信息
			fdPersonId : setting.fdPersonId,
			fdPersonName : setting.fdPersonName,
			fdDeptName : setting.fdDeptName,
			fdThisLeaderName : ko.observable(setting.fdThisLeaderName),
			editorArray: setting._editorArray,
			//人员信息
			fdEmail : setting.fdEmail,
			fdMobileNo : setting.fdMobileNo,
			fdWorkPhone :  setting.fdWorkPhone,
			fdLoginName : ko.observable(setting.fdLoginName).extend({
                required: {
                	params:true,
                	message: "请输入登录名" 
                }
            }),
			fdDefaultLang : setting.fdDefaultLang, 
			fdRtxNo : setting.fdRtxNo,
			fdCardNo : setting.fdCardNo, 
			fdPostIds : setting.fdPostIds,
			fdPostNames : ko.observable(setting.fdPostNames),
			selectType : setting.selectType 
		
		
		};   
		  
		
	};
	
	return OrgElement;
})

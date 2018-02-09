define(["knockout","knockout-validation"],function(ko) { 
	
	CommonTemp = function(options) {  
		var setting = $.extend({
			fdId : null,
			fdName: null,
			fdIsDefault: '0', 
			fdFlowContent: null,
			fdModelName: null,
			fdKey: null,
			fdCreateTime: null,
			fdVersion: null,
			userName: null,
			fdCreatorId: null,
			
			description: null,
			rejectReturnChe: null,
			notifyTypeChe: null,
			notifyOnFinishChe:null,
			notifyDraftOnFinishChe: null, 
			dayOfNotifyPrivileger: null, 
			privilegerIds: null,
			privilegerNames:null
		},options || {})
		
		return {
			fdId : setting.fdId,
			fdName: ko.observable(setting.fdName).extend({
                required: {
                	params:true,
                	message: "请输入模板名称。" 
                }
            }),
			fdIsDefault: setting.fdIsDefault, 
			fdFlowContent: setting.fdFlowContent,
			fdModelName: setting.fdModelName,
			fdKey: setting.fdKey,
			fdCreateTime: setting.fdCreateTime,
			fdVersion: setting.fdVersion,
			userName: setting.userName,
			fdCreatorId: setting.fdCreatorId,
			
			description: setting.description,
			rejectReturnChe: ko.observableArray(setting.rejectReturnChe),
			notifyTypeChe: ko.observableArray(setting.notifyTypeChe),
			notifyOnFinishChe: ko.observableArray(setting.notifyOnFinishChe),
			notifyDraftOnFinishChe: ko.observableArray(setting.notifyDraftOnFinishChe), 
			dayOfNotifyPrivileger: setting.dayOfNotifyPrivileger,
			privilegerIds: setting.privilegerIds,
			privilegerNames: setting.privilegerNames
		};   
	};
	
	return CommonTemp;

})
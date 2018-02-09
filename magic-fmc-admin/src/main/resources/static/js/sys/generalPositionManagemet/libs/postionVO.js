define(["knockout","knockout-validation"],function(ko) { 

	PostionManagement = function(options) {
		var setting = $.extend({
			fdId : null,
			fdName: null,
			fdPlugin: null,
			fdParameter: null,
			fdRtnValue: null,
			fdIsMultiple: null,
			fdMemo: null,
			fdIsAvailable: null 
		
		},options || {})
		
		return {
			fdId : setting.fdId,
			fdName: ko.observable(setting.fdName).extend({
                required: {
                	params:true,
                	message: "请输入岗位名称。" 
                }
            }),
			fdPlugin: ko.observable(setting.fdPlugin).extend({
                required: {
                	params:true,
                	message: "请输入程序名。" 
                }
            }),
			fdParameter: setting.fdParameter,
			fdRtnValue: setting.fdRtnValue,
			fdIsMultiple: setting.fdIsMultiple,
			fdMemo: setting.fdMemo,
			fdIsAvailable: setting.fdIsAvailable
		}; 
		
	};

	return PostionManagement;
	
})
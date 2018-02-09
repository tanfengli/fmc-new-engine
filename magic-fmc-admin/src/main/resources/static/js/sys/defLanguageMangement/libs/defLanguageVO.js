define(["knockout","knockout-validation"],function(ko) {
		
	function DefLanguage() {
		"use strict";
		var _fdId = null,  
		    _fdUsageType = null,  
	   		_fdUsageContent = null,
	   		_fdIsSysSetup = null,
	   		_fdActiveFlag = null
		return {
			fdId: ko.observable(_fdId),
			fdUsageType :  ko.observable(_fdUsageType),
	   		fdUsageContent : ko.observable(_fdUsageContent).extend({
	            required: {
	            	params:true,
	            	message: "请输入审批语。" 
	            }
	        }),
	   		fdIsSysSetup :  ko.observable(_fdIsSysSetup),
	   		fdActiveFlag:  ko.observable(_fdActiveFlag) 
		};
	};
	
	return DefLanguage;
})
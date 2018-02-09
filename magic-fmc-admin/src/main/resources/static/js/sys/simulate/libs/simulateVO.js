define(["knockout"],function(ko) {
	
	Simulate = function(options) {
		var setting = $.extend({
			threadNum : null,
			http: null,
			flag:true,
			maxNum:null,
			isApprove:true,
			templateId:null,
			handlerIds:null,
			handlerNames:null,
		//	handlerArray: []
			
			 
		},options || {})
		
		return {
			threadNum : setting.threadNum,
			http: setting.http,
			flag: setting.flag,
			maxNum: setting.maxNum,
			isApprove: setting.isApprove,
			templateId:setting.templateId,
			handlerIds:setting.handlerIds,
			handlerNames:setting.handlerNames,
			//handlerArray: setting.handlerArray 
			
		}; 
	}
	
	return Simulate;
	 
})
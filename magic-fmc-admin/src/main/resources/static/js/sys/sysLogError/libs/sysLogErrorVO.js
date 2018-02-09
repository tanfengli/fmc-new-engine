define(["knockout"],function(ko) { 
	
	SysLogError = function(options) {  
		var setting = $.extend({
			fdId : null,
			fdCreateTime: null,
			fdIp: null,
			fdOperator: null,
			fdOperatorId: null,
			fdUrl: null,
			fdErrorInfo: null,
			fdMethod: null
		},options || {})
		
		return {
			fdId : setting.fdId, 
			fdCreateTime: setting.fdCreateTime,
			fdIp: setting.fdIp,
			fdOperator: setting.fdOperator,
			fdOperatorId: setting.fdOperatorId,
			fdUrl: setting.fdUrl,
			fdErrorInfo: setting.fdErrorInfo,
			fdMethod: setting.fdMethod
		};   
	};
	
	return SysLogError;

})
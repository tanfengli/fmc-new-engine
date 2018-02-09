define([ "knockout" ],function(ko) {
	function ProcessReadV(options) {
		var setting = $.extend({
			fdId : null,
			applyCode : null,
			fdSubject : null,
			isRead : 0
		}, options || {})
			
		return {
			fdId : setting.fdId,
			applyCode : setting.applyCode,
			fdSubject : setting.fdSubject,
			isRead : setting.isRead
		};

	};
	
	return ProcessReadV;
})
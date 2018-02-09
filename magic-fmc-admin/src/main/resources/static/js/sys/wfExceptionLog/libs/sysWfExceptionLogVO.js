define([ "knockout" ],function(ko) {

	function SysWfExceptionLog(options) {
			
			var setting = $.extend({
				fdId : null,
				fdTitle : null,
				fdApplyCode : null,
				fdFactNodeName : null,
				fdHandlerName : null
			}, options || {})
			
			return {
				fdId : setting.fdId,
				fdTitle : setting.fdTitle,
				fdApplyCode : setting.fdApplyCode,
				fdFactNodeName : setting.fdFactNodeName,
				fdHandlerName : setting.fdHandlerName
			};

		};
		return SysWfExceptionLog;

	})
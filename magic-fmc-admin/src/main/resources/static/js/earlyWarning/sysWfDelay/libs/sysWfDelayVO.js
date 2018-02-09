
define([ "knockout" ,"knockout-validation"],function(ko) {

	function vo(options) {
				
			var setting = $.extend({
				fdId : null,
				fdTemplateName : null,
				fdEnabled : null
			}, options || {})
			
			return setting;
		};
		return vo;

	})
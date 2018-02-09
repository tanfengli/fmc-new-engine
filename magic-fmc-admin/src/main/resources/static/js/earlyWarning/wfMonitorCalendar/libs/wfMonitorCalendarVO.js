
define([ "knockout" ,"knockout-validation"],function(ko) {

	function vo(options) {
				
			var setting = $.extend({
				fdId : null,
				fdName : null,
				fdYear : null,
				fdEnabled : null,
				fdCreatorName : null
			}, options || {})
			
			return setting;
		};
		return vo;

	})

define([ "knockout" ,"knockout-validation"],function(ko) {

	function vo(options) {
				
			var setting = $.extend({
				fdId : null,
				fdDelayTime : null,
				fdEnabled : null,
				fdCreatedBy:null,
				fdCreatedDate:null,
				fdLastUpdatedBy:null,
				fdLastUpdatedDate:null,
				fdTemplateId:null
			}, options || {})
			
			return {
				fdId : setting.fdId,
				fdDelayTime : setting.fdDelayTime,
				fdEnabled : setting.fdEnabled,
				fdCreatedBy : setting.fdCreatedBy,
				fdCreatedDate : setting.fdCreatedDate,
				fdLastUpdatedBy : setting.fdLastUpdatedBy,
				fdLastUpdatedDate : setting.fdLastUpdatedDate,
				fdTemplateId : setting.fdTemplateId
				};
		};
		return vo;

	})
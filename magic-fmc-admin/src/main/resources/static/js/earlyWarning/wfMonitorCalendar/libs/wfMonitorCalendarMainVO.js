
define([ "knockout" ,"knockout-validation"],function(ko) {

	function vo(options) {
				
			var setting = $.extend({
				fdId : null,
				fdName : null,
				fdEnabled : null,
				fdCreatedBy : null,
				fdCreatedDate : null,
				fdScopeIds : null,
				fdScopeNames : null,
				fdLastUpdatedBy : null,
				fdLastUpdatedDate : null,
				fdWeekStart : null,
				fdWeekEnd : null
			}, options || {})
			
			return {
				fdId : setting.fdId,
				fdName : ko.observable(setting.fdName),
				fdEnabled : setting.fdEnabled,
				fdCreatedBy : setting.fdCreatedBy,
				fdCreatedDate : setting.fdCreatedDate,
				fdScopeIds : ko.observable(setting.fdScopeIds),
				fdScopeNames : ko.observable(setting.fdScopeNames),
				fdLastUpdatedBy : setting.fdLastUpdatedBy,
				fdLastUpdatedDate : setting.fdLastUpdatedDate,
				fdWeekStart : ko.observable(setting.fdWeekStart),
				fdWeekEnd : ko.observable(setting.fdWeekEnd)
			};
		};
		return vo;

	})
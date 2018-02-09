
define([ "knockout" ,"knockout-validation"],function(ko) {

	function vo(options) {
				
			var setting = $.extend({
				fdId : null,
				fdName : null,
				fdEnabled : null,
				fdTimeStart:null,
				fdTimeEnd:null,
				fdNoonStart:null,
				fdNoonEnd:null,
				fdCreatedBy:null,
				fdCreatedDate:null,
				fdLastUpdatedBy:null,
				fdLastUpdatedDate:null,
				fdCalendarId:null,
				fdMonthList:[]
			}, options || {})
			
			return {
				fdId : setting.fdId,
				fdName : ko.observable(setting.fdName),
				fdEnabled : setting.fdEnabled,
				fdCreatedBy : setting.fdCreatedBy,
				fdCreatedDate : setting.fdCreatedDate,
				fdTimeStart:setting.fdTimeStart,
				fdTimeEnd:setting.fdTimeEnd,
				fdNoonStart:setting.fdNoonStart,
				fdNoonEnd:setting.fdNoonEnd,
				fdLastUpdatedBy : setting.fdLastUpdatedBy,
				fdLastUpdatedDate : setting.fdLastUpdatedDate,
				fdCalendarId : setting.fdCalendarId,
				fdMonthList:ko.observableArray(setting.fdMonthList)
				};
		};
		return vo;

	})
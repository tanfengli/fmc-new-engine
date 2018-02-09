define(["knockout"],function(ko) {
	
	BasicSet = function(options) {
		var setting = $.extend({
			notifyCrashTargetIds : null,
			notifyExpire: null,
			nodeNameSelectItem: null,
			notifyCrashTargetNames: null,
			notifyTargetSubmit: null,
			notifyTargetAuthor: null,
			threadPoolSize: null ,
			defaultNotifyTypeSel: null ,
			systemNotifyTypeSel: null,
			_editorArray: []
		},options || {})
		
		return {
			notifyCrashTargetIds : setting.notifyCrashTargetIds,
			notifyExpire: setting.notifyExpire,
			nodeNameSelectItem: setting.nodeNameSelectItem,
			notifyCrashTargetNames: setting.notifyCrashTargetNames,
			notifyTargetSubmit: setting.notifyTargetSubmit,
			notifyTargetAuthor: setting.notifyTargetAuthor,
			threadPoolSize: setting.threadPoolSize ,
			defaultNotifyTypeSel: ko.observableArray(setting.defaultNotifyTypeSel) ,
			systemNotifyTypeSel: ko.observableArray(setting.systemNotifyTypeSel),
			editorArray: setting._editorArray 
		}; 
	}
	
	return BasicSet;
	 
})
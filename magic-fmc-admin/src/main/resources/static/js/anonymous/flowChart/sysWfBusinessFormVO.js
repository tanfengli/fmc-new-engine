/**
 * 流程业务信息表单
 */
define(["knockout"],function(ko){
	var sysWfBusinessForm=function(options){
		var setting=$.extend({
		  fdId:null,
		  basic:null,
		  wfLog:null,
		  nodeList:null,
		  historyList:null,
		  privilegerOpr:null,
		  fdFlowContent:null
		},options||{})
		
		return {
			fdId:ko.observable(setting.fdId),
			basic:ko.observable(setting.basic),
			wfLog:ko.observableArray(setting.wfLog),
			nodeList:ko.observable(setting.nodeList),
			historyList:ko.observable(setting.historyList),
			privilegerOpr:ko.observable(setting.privilegerOpr),
			fdFlowContent:ko.observable(setting.fdFlowContent)
		};
	}
	
	return sysWfBusinessForm;
})
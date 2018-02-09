define(["knockout","./sysNewsMainVO.js"],function(ko,SysNewsMain){
	var SysWfBusinessForm=function(options){
		var setting=$.extend({
			basic:new SysNewsMain(),
			wfLog:null,
			nodeList:[],
			historyList:[],
			fdFlowContent:null,
			currentHandlerIds:null,
			currentHandlerNames:null,
			historyHandlerNames:null,
			readerNames:null,
			readTimes:null,
		 },options||{})

		return {
			basic:setting.basic,
			wfLog:setting.wfLog,
			nodeList:setting.nodeList,
			historyList:setting.historyList,
			fdFlowContent:setting.fdFlowContent,
			currentHandlerIds:setting.currentHandlerIds,
			currentHandlerNames:setting.currentHandlerNames,
			historyHandlerNames:setting.historyHandlerNames,
			readerNames:setting.readerNames,
			readTimes:setting.readTimes,
		};
	};
	
	return SysWfBusinessForm;
})
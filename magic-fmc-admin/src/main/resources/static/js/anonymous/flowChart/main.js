requirejs.config({
	paths:{
		'viewModel' : 'js/anonymous/flowChart/viewFlowChart',
		'sysWfBusinessFormVO' : 'js/anonymous/flowChart/sysWfBusinessFormVO',
		'Flowchart' : 'js/flowchart/Flowchart'
	}
});
requirejs(['../js/common.js' ],function(common){
requirejs(['knockout','jquery','viewModel','ko-amd-helpers','domReady!'],function(ko,$,ViewModel,koAmdHelpers){

	ko.amdTemplateEngine.defaultPath="getViews";
	ko.amdTemplateEngine.defaultSuffix="";
	ko.amdTemplateEngine.defaultRequireTextPluginName="text";

	ko.applyBindings(new ViewModel());
});
});
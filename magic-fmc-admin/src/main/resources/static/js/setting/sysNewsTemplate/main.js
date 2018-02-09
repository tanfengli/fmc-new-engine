requirejs.config({
	paths:{
		'Flowchart':'js/flowchart/Flowchart',
		'JqueryForm':'webjars/jquery-form/jquery.form'
	}
});

requirejs(['../js/common.js'],function(common){
	requirejs(['knockout','../js/setting/sysNewsTemplate/sysNewsTemplateViewModel.js',
			'ko-amd-helpers','JqueryForm','domReady!'],function(ko,viewModel,koAmdHelpers){
		ko.amdTemplateEngine.defaultPath="getViews";
		ko.amdTemplateEngine.defaultSuffix="";
		ko.amdTemplateEngine.defaultRequireTextPluginName="text";
		
		ko.applyBindings(new viewModel());
	});
});
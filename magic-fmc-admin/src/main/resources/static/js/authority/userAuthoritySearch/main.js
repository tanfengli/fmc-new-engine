
requirejs(['../js/common.js' ],function(common){
	requirejs(['knockout','../js/authority/userAuthoritySearch/libs/userAuthViewModel.js','ko-amd-helpers','domReady!'],
	function(ko,viewModel,koAmdHelpers){
		ko.amdTemplateEngine.defaultPath="getViews";
		ko.amdTemplateEngine.defaultSuffix="";
		ko.amdTemplateEngine.defaultRequireTextPluginName="text";

		ko.applyBindings(new viewModel());
	});
});
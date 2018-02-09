requirejs(['../js/common.js'],function(common) {
	requirejs([ 'knockout', 'js/sys/defLanguageMangement/libs/def_languageViewModel.js',
			'ko-amd-helpers', 'domReady!' ], function(ko, viewModel,
			koAmdHelpers) {

		ko.amdTemplateEngine.defaultPath = "getViews";
		ko.amdTemplateEngine.defaultSuffix = "";
		ko.amdTemplateEngine.defaultRequireTextPluginName = "text";

		var doc = document.getElementById("stage")
		if(doc!=null){
			ko.cleanNode(doc);
		}
		ko.applyBindings(new viewModel(),doc);
	});
});
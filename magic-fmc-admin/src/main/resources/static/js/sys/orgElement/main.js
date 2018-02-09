requirejs(['../js/common.js'],function(common) { 
	requirejs([ 'knockout', '../js/sys/orgElement/libs/orgElementViewModel.js',
			'ko-amd-helpers', 'domReady!' ], function(ko, viewModel,
			koAmdHelpers) {

		ko.amdTemplateEngine.defaultPath = "getViews";
		ko.amdTemplateEngine.defaultSuffix = "";

		ko.applyBindings(new viewModel());

	});
});
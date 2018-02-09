requirejs(['../js/common.js'],function(common) {
	requirejs([ 'knockout', 'js/sys/orgPerson/libs/PersonViewModel.js',
			'ko-amd-helpers', 'domReady!' ], function(ko, PersonViewModel,
			koAmdHelpers) {

		ko.amdTemplateEngine.defaultPath = "getViews";
		ko.amdTemplateEngine.defaultSuffix = "";
		ko.amdTemplateEngine.defaultRequireTextPluginName = "text";

		ko.applyBindings(new PersonViewModel());
	});
});
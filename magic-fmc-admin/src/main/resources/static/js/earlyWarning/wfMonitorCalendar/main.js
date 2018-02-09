requirejs(['../js/common.js'],function(common) {
	requirejs([ 'knockout', '../js/earlyWarning/wfMonitorCalendar/libs/wfMonitorCalendarViewModel.js',
			'ko-amd-helpers', 'domReady!' ], function(ko, viewModel,
			koAmdHelpers) {

		ko.amdTemplateEngine.defaultPath = "getViews";
		ko.amdTemplateEngine.defaultSuffix = "";
		ko.amdTemplateEngine.defaultRequireTextPluginName = "text";

		ko.applyBindings(new viewModel());

	});
});
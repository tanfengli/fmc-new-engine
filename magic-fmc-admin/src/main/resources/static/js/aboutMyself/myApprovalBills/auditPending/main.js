requirejs.config({
	paths:{'Flowchart':'js/flowchart/Flowchart'}
});

requirejs(['../js/common.js'],function(common){
	requirejs(['knockout','../js/aboutMyself/myApprovalBills/auditPending/docApprovalViewModel.js',
		'ko-amd-helpers','domReady!'],function(ko,viewModel,koAmdHelpers){
		if(!ko.components.isRegistered("defLanguageMange")){
			ko.components.register('defLanguageMange',{
			    viewModel:{
			       require:'../blocks/sys/defLanguageMange/defLanguageMange.js'
			    },
			    template:{
			    	 require:'text!blocks/sys/defLanguageMange/defLanguageMange.html'
			    }
			});
		}
		
		
		ko.amdTemplateEngine.defaultPath="getViews";
		ko.amdTemplateEngine.defaultSuffix="";
		ko.amdTemplateEngine.defaultRequireTextPluginName="text";

		ko.applyBindings(new viewModel());
	});
});
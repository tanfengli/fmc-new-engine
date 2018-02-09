requirejs.config({
	paths:{
		'jquery' : '../webjars/jquery/jquery.min',
		'bootstrap' : '../webjars/bootstrap/js/bootstrap.min',
		'knockout' : '../webjars/knockout/knockout',
		'ko-amd-helpers': '../js/vendors/knockout-amd-helpers.min',
		'knockout.mapping' : '../webjars/knockout-mapping/knockout.mapping',
		'knockout-validation' : '../webjars/knockout-validation/dist/knockout.validation.min',
		'toastr' : '../webjars/toastr/build/toastr.min',
		'components' : '../js/models/components',
		'text': '../webjars/requirejs-text/text',
		'Raphael': '../webjars/raphael/raphael.min',
		'domReady': '../webjars/requirejs-domready/domReady',
		'MsgBox' : '../js/models/MsgBox',
		'AjaxUtil' : '../js/models/AjaxUtil',
		'zTree-all' : '../webjars/zTree/js/jquery.ztree.all.min'
	},
	shim : {
		'jquery' : {
			exports : '$'
		},
		'Raphael' : {
			exports: '$',
			deps : [ 'jquery' ]
		},
		'zTree-all' : {
        	deps : ['jquery']
        }
	}
});

requirejs(['knockout','AjaxUtil','ko-amd-helpers','domReady!'],
	function(ko,AjaxUtil,koAmdHelpers){
		ko.amdTemplateEngine.defaultPath="getViews";
		ko.amdTemplateEngine.defaultSuffix="";
		ko.amdTemplateEngine.defaultRequireTextPluginName="text";

		var viewModel=new function(){
			var self=this;
			self.interfaces=ko.observableArray([]);
			
			AjaxUtil.call({
				type:"post",
				url:'anonymous/interface/getInterfaces',
				dataType:"JSON",
				success:function(data){
					self.interfaces(data);
				}
			});
		}
		
		ko.applyBindings(viewModel);
});
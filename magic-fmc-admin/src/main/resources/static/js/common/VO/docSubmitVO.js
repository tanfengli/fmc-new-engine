define(["knockout"],function(ko){
	function DocSubmit(options){
		var setting=$.extend({
			fdId:null,
			docSubject:ko.observable(),
			applyCode:ko.observable(),
			creatorUserName:ko.observable(),
			docCreateTime:null,
			docPublishTime:null,
			docStatus:null,
			dealName:ko.observable(),
			nodeNames:ko.observable(),
			fdTemplateId:null,
			fdTemplateName:ko.observable(),
		},options||{})
			
		return setting;
	};
	
	return DocSubmit;
})
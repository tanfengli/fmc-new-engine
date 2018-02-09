define(["knockout"],function(ko){
	function DocSubmit(options){
		var setting=$.extend({
			fdId:null,
			fdCategoryId:null,
			fdCategoryName:ko.observable(),
			fdTemplateId:null,
			fdTemplateName:ko.observable(),
			fdInstanceNumber:null,
			fdMaxTime:null,
			fdMinTime:null,
			fdAvgTime:null,
			fdAlertTime:null,
			fdOverdueNumber:null,
			fdOverdueAvg:null,
			fdOverdueRate:null,
			fdStartDate:new Date().getFullYear()+"-"+new Date().getMonth()+1+"-01",
			fdEndDate:new Date().getFullYear()+"-"+new Date().getMonth()+1+"-"+new Date(new Date().getFullYear(),new Date().getMonth()+1,0).getDate()
		},options||{})
			
		return setting;
	};
	
	return DocSubmit;
})
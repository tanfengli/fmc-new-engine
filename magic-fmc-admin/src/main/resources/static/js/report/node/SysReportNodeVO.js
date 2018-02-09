define(["knockout"],function(ko){
	function DocSubmit(options){
		var setting=$.extend({
			fdId:null,
			fdFactNodeId:null,
			fdFactNodeName:null,
			fdTemplateId:null,
			fdTemplateName:ko.observable(null),
			fdAvgTime:null,
			fdAlertTime:null,
			fdOverdueNumber:null,
			fdOverdueAvg:null,
			fdStartDate:new Date().getFullYear()+"-"+new Date().getMonth()+1+"-01",
			fdEndDate:new Date().getFullYear()+"-"+new Date().getMonth()+1+"-"+new Date(new Date().getFullYear(),new Date().getMonth()+1,0).getDate()
		},options||{})
			
		return setting;
	};
	
	return DocSubmit;
})
define(["knockout"],function(ko){
	function VAuditBill(options){
		var setting=$.extend({
			fdId:null,
			applyCode:null,
			docSubject:null,
			nodeName:null,
			dealName:null,
		},options||{})
		
		return {
			fdId:setting.fdId,
			applyCode:setting.applyCode,
			docSubject:setting.docSubject,
			nodeName:setting.nodeName,
			dealName:setting.dealName
		};
	};
	
	return VAuditBill;
})
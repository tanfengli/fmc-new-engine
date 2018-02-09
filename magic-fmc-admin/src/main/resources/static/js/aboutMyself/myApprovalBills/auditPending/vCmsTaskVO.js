define(["knockout"],function(ko){
	function VCmsTask(options){
		var setting=$.extend({
			fdId:null,
			wfInstanceId:null,
			applyCode:null,
			sourceSystemId:null,
			fdType:null,
			wfStatus:null,
			taskUserNo:null,
			taskUserName:null,
			appUserNo:null,
			appUserName:null,
			appDate:null,
			appTitle:null,
			applyReason:null,
			publishDate:null,
			urgentLevel:null,
			taskUrl:null,
			fdTemplateId:null,
			fdDescription:null,
			docContent:null,
			nodeName:null,
		},options||{})
		
		return {
			fdId:setting.fdId,
			wfInstanceId:setting.wfInstanceId,
			applyCode:setting.applyCode,
			sourceSystemId:setting.sourceSystemId,
			fdType:setting.fdType,
			wfStatus:setting.wfStatus,
			taskUserNo:setting.taskUserNo,
			taskUserName:setting.taskUserName,
			appUserNo:setting.appUserNo,
			appUserName:setting.appUserName,
			appDate:setting.appDate,
			appTitle:setting.appTitle,
			applyReason:setting.applyReason,
			publishDate:setting.publish_date,
			urgentLevel:setting.applyReason,
			taskUrl:setting.taskUrl,
			fdTemplateId:setting.fdTemplateId,
			fdDescription:setting.fdDescription,
			docContent:setting.docContent,
			nodeName:setting.nodeName,
			dealName:setting.dealName
		};
	};
	
	return VCmsTask;
})
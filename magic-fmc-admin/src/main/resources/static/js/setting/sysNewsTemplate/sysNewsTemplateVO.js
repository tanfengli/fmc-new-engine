/**
 * 分类设置模型
 */

define([ "knockout","knockout-validation"],function(ko) {


SysNewsTemplate = function(options) {
	
	var setting = $.extend({
		fdId : null,
		fdName: null,
		docCreateTime: null,
		fdImportance: null,
		fdNumberPrefix: null,
		docContent: null,
		fdUseForm: null,
		fdAppLink: null,
		authReaderFlag: null,
		authTmpAttNodownload: null,
		authTmpAttNocopy: null,
		fdStatus: null,
		authTmpAttNoprint: null,
		fdOrder: null,
		fdStyle: null,
		fdContentType: null,
		fdCategoryId: null,
		docCreatorId: null,
		authAreaId: null,
		busiSysId: null,
		authNotReaderFlag: null,
		fdHierarchyId: null,
		docALTERORId: null,
		docAlterTime: null,
		fdIsinheritMaintainer: null,
		fdChageAtt: null,
		fdIsinheritUser: null,
		fdParentId: null,
		creatorName: null,
		//业务系统名字
		busyName: null,
		//分类名字
		categoryName: null,
		//可修改者
		templateEditor:[],
		//可使用者
		templateReader:[],
		//组件传值判断
		clickType : null,
		//业务系统
		busiSysArray:[],
		
		
		defaultTemplate: null,
		flowXml:　null,
		otherFlowXml: null,
		otherTemplateId: null,
		pattern: null,
		fdVersion: null,
		enableTimeDate: null,
		
		docCreatorName: null,
		docAlterName: null,
		creatorTime:   null,
		alterTime:  null,
		showVersion: false
		
		
	},options || {})
	
	return {
		fdId : setting.fdId,
		fdName: ko.observable(setting.fdName).extend({
            required: {
            	params:true,
            	message: "请输入名称" 
            }
        }),
		docCreateTime: setting.docCreateTime,
		fdImportance: setting.fdImportance,
		fdNumberPrefix: ko.observable(setting.fdNumberPrefix).extend({
            required: {
            	params:true,
            	message: "请输入前缀" 
            }
        }),
		docContent: setting.docContent,
		fdUseForm: setting.fdUseForm,
		fdAppLink: setting.fdAppLink,
		authReaderFlag: setting.authReaderFlag,
		authTmpAttNodownload: setting.authTmpAttNodownload,
		authTmpAttNocopy: setting.authTmpAttNocopy,
		fdStatus: ko.observable(setting.fdStatus),
		authTmpAttNoprint: setting.authTmpAttNoprint,
		fdOrder: setting.fdOrder,
		fdStyle: setting.fdStyle,
		fdContentType: setting.fdContentType,
		fdCategoryId: setting.fdCategoryId,
		docCreatorId: setting.docCreatorId,
		authAreaId: setting.authAreaId,
		busiSysId: ko.observable(setting.busiSysId).extend({
            required: {
            	params:true,
            	message: "请输入业务系统" 
            }
        }),
		authNotReaderFlag: setting.authNotReaderFlag,
		fdHierarchyId: setting.fdHierarchyId,
		docALTERORId: setting.docALTERORId,
		docAlterTime: setting.docAlterTime,
		fdIsinheritMaintainer: setting.fdIsinheritMaintainer,
		fdChageAtt: setting.fdChageAtt,
		fdIsinheritUser: setting.fdIsinheritUser,
		fdParentId: setting.fdParentId,
		creatorName: setting.creatorName,
		//业务系统名字
		busyName: setting.busyName,
		//分类名字
		categoryName: setting.categoryName,
		//可修改者
		templateEditor:setting.templateEditor,
		//可使用者
		templateReader:setting.templateReader,
		//组件传值判断
		clickType : setting.clickType,
		busiSysArray: setting.busiSysArray,
		
		defaultTemplate: setting.defaultTemplate,
		flowXml: setting.flowXml,
		otherFlowXml: setting.otherFlowXml,
		otherTemplateId: setting.otherTemplateId,
		pattern: ko.observable(setting.pattern),
		fdVersion: ko.observable(setting.fdVersion),
		enableTimeDate: setting.enableTimeDate,
		
		docCreatorName:  setting.docCreatorName,
		docAlterName:  setting.docAlterName,
		creatorTime:  setting.creatorTime,
		alterTime:  setting.alterTime,
		showVersion: ko.observable(setting.showVersion)
	};
	
};
	return SysNewsTemplate;
	
})
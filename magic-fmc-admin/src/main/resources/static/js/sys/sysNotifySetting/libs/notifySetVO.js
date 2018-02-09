define(["knockout"],function(ko) {  
	
	NotifySet = function(options) {
		var setting = $.extend({
			orgFdId : null,
			orgFdModelName: null,
			orgFdModelId: null,  
			orgFdKey: null,
			orgFdNotifyType: [],
			orgFdSubject: null,
			orgFdContent: null,
			_editorArray: [],
			orgPerId:null,
			orgPerName:null,
		
			creFdId : null,
			creFdModelName: null,
			creFdModelId: null,
			creFdKey: null,
			creFdNotifyType: [],
			creFdSubject: null,
			creFdContent: null,
			_creEditorArray: [],
			crePerId:null,
			crePerName:null,
			
			delFdId : null,
			delFdModelName: null,
			delFdModelId: null,
			delFdKey: null,
			delFdNotifyType: [],
			delFdSubject: null,
			delFdContent: null,
			_delEditorArray: [],
			delPerId:null,
		    delPerName:null
			
		},options || {})
		
		return {
			orgFdId : setting.orgFdId,
			orgFdModelName: setting.orgFdModelName,
			orgFdModelId: setting.orgFdModelId, 
			orgFdKey: setting.orgFdKey,
			orgFdNotifyType: ko.observableArray(setting.orgFdNotifyType),
			orgFdSubject: setting.orgFdSubject,
			orgFdContent: setting.orgFdContent,
			editorArray: setting._editorArray,
			orgPerId: setting.orgPerId,
			orgPerName: setting.orgPerName,
			
			creFdId : setting.creFdId,
			creFdModelName: setting.creFdModelName,
			creFdModelId: setting.creFdModelId,
			creFdKey: setting.creFdKey,
			creFdNotifyType: ko.observableArray(setting.creFdNotifyType),
			creFdSubject: setting.creFdSubject,
			creFdContent: setting.creFdContent,
			creEditorArray: setting._creEditorArray,
			crePerId: setting.crePerId,
			crePerName: setting.crePerName,
			
			delFdId : setting.delFdId,
			delFdModelName: setting.delFdModelName,
			delFdModelId: setting.delFdModelId,
			delFdKey: setting.delFdKey,
			delFdNotifyType: ko.observableArray(setting.delFdNotifyType),
			delFdSubject: setting.delFdSubject,
			delFdContent: setting.delFdContent, 
			delEditorArray: setting._delEditorArray,
			delPerId: setting.delPerId,
		    delPerName: setting.delPerName,
		    
			clickOrgModel : function(){
				this.selectType = ko.observable('');
				this.selectType('orgEditor');
				$('#memberSelectWindow').modal();
			},
			
			clickCreModel : function(){
				this.selectType = ko.observable('');
				this.selectType('creEditor');
				$('#memberSelectWindow').modal();
			},
			
			clickDelModel : function(){
				this.selectType = ko.observable('');
				this.selectType('delEditor');
				$('#memberSelectWindow').modal();
			}
		
		}; 
	}
	
	return NotifySet;
	 
})
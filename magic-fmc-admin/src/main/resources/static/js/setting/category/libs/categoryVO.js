/**
 * 分类设置模型
 */

define([ "knockout","knockout-validation", "MsgBox" ],function(ko,MsgBox) {


SysCategory = function(options) {
	
	var setting = $.extend({
		fdId : null,
		fdName: null,
		fdOrder: null,
		fdModelName: "com.ruiyi.kmss.sys.news.model.SysNewsTemplate",
		docCreateTime: null,
		docAlterTime: null,
		fdIsinheritMaintainer: 1,
		fdIsinheritUser: 1,
		authReaderFlag: null,
		docCreateId: null,
		docAlterorId: null,
		fdHierarchyId: null,
		authAreaId: null,
		fdParentId: null,
		clickType: null,
		readerArray: [],
		editorArray: []
	},options || {})
	
	return {
		fdId: setting.fdId,
		fdName: ko.observable(setting.fdName).extend({
            required: {
            	params:true,
            	message: "请输入名称。" 
            }
        }),
		fdOrder: setting.fdOrder,
		fdModelName: setting.fdModelName,
		docCreateTime: setting.docCreateTime,
		docAlterTime: setting.docAlterTime,
		fdIsinheritMaintainer: setting.fdIsinheritMaintainer,
		fdIsinheritUser: setting.fdIsinheritUser,
		docCreateId: setting.docCreateId,
		docAlterorId: setting.docAlterorId,
		authAreaId: setting.authAreaId,
		fdHierarchyId: setting.fdHierarchyId,
		authAreaId: setting.authAreaId,
		fdParentId: setting.fdParentId,
		clickType: setting.clickType,
		readerArray: setting.readerArray,
		editorArray: setting.editorArray,
		
		send :　function(){
			MsgBox.info('send');
		},
		
		editorClick : function(){
			this.clickType='editor';
			$('#editorArray').click();
			$('#memberSelectWindow').modal();
		},
		
		readerClick :function(){
			this.clickType='reader';
			$('#readerArray').click();
			$('#memberSelectWindow').modal();
		}
	};
	
};
	return SysCategory;
	
})
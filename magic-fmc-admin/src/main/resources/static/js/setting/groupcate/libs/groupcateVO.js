/**
 * 群组分类模型
 */
define([ "knockout" ,"knockout-validation"],function(ko) {

GroupCate = function(options) {
	
	var setting = $.extend({
		fdId : null,
		fdName: null,
		fdKeyword: null,
		fdCreateTime: null,
		fdAlterTime: null,
		fdParentId: null,
		fdIsLeaf: null
	},options || {})
	
	return {
		fdId: setting.fdId,
		fdName: ko.observable(setting.fdName).extend({
            required: {
            	params:true,
            	message: "请输入类别名称。" 
            }
        }),
		fdKeyword: setting.fdKeyword,
		fdCreateTime: setting.fdCreateTime,
		fdAlterTime: setting.fdAlterTime,
		fdParentId: setting.fdParentId,
		fdIsLeaf: setting.fdIsLeaf
	};
	
};
	return GroupCate;
	
})
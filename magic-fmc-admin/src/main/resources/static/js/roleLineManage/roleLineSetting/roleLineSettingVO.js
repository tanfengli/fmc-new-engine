
define([ "knockout" ,"knockout-validation"],function(ko) {

	function RoleLineSetting(options) {
				
			var setting = $.extend({
				fdId : null,
				fdName : null,
				fdOrder : null,
				fdIsAvailable : null,
				editorArray : []
			}, options || {})
			
			return {
				fdId: setting.fdId,
				fdName : ko.observable(setting.fdName).extend({
	                required: {
	                	params:true,
	                	message: "角色线名称不能为空！" 
	                }
	            }),
				fdOrder: setting.fdOrder,
				fdIsAvailable: ko.observable((setting.fdIsAvailable==1||setting.fdIsAvailable=='1')?'1':'0'),
				editorArray : setting.editorArray
			}

		};
		return RoleLineSetting;

	})
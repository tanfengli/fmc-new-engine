
define([ "knockout","knockout-validation" ],function(ko) {

	function RoleCate(options) {
				
			var setting = $.extend({
				fdId : null,
				fdName : null,
				creatorName : null,
				createTime : null
			}, options || {})
			
			return {
				fdId: setting.fdId,
				fdName : ko.observable(setting.fdName).extend({
		            required: {
		            	params:true,
		            	message: "请输入分类名称。" 
		            }
		        }),
				creatorName: setting.creatorName,
				createTime: setting.createTime
			}

		};
		return RoleCate;
	})
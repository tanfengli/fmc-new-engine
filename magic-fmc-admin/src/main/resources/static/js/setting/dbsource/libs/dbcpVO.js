define([ "knockout","knockout-validation" ],function(ko,kv) {

	function Dbcp(options) {
				
			var setting = $.extend({
				fdId : null,
				fdName : null,
				fdUsername: null,
				fdPassword : null,
				fdUrl : null,
				fdType : null,
				fdDriver : null,
				fdDescription : null
			}, options || {})
			
			return {
				self: Dbcp,
				fdId: setting.fdId,
				fdName : ko.observable(setting.fdName).extend({
	                required: {
	                	params:true,
	                	message: "请输入名称。" 
	                }
	            }),
				fdUsername: ko.observable(setting.fdUsername).extend({
					required: {
	                	params:true,
	                	message: "请输入用户名。" 
	                }
	            }),
				fdPassword : ko.observable(setting.fdPassword).extend({
					required: {
	                	params:true,
	                	message: "请输入密码。" 
	                }
	            }),
				fdUrl: ko.observable(setting.fdUrl).extend({
					required: {
	                	params:true,
	                	message: "请输入地址。" 
	                }
	            }),
	            selFdUrl: ko.observable(false),
				fdType: ko.observable(setting.fdType).extend({
					required: {
	                	params:true,
	                	message: "请输入数据库类型。" 
	                }
	            }),
				fdDriver: ko.observable(setting.fdDriver).extend({
					required: {
	                	params:true,
	                	message: "请输入驱动类。" 
	                }
	            }),
	            selFdDriver: ko.observable(false),
				fdDescription: ko.observable(setting.fdDescription)
			}

		};
		return Dbcp;

	})
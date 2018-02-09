
define([ "knockout" ],function(ko) {

	function RoleAssign(options) {
				
			var setting = $.extend({
				_fdId : null,
				_fdName : null,
				_categoryName: null,
				_categoryId : null,
				_creatorName : null
			}, options || {})
			
			return {
				fdId: setting._fdId,
				fdName : setting._fdName,
				categoryName: setting._categoryName,
				categoryId: setting._categoryId,
				creatorName: setting._creatorName
			}

		};
		return RoleAssign;

	})
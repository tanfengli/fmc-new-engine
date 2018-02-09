/**
 * 组织人员模型
 */
define(['jquery','knockout'],function($,ko) {
	
	function PersonVO(options) {

		var setting = $.extend({
			_fdId : null,
			_fdLoginName : null,
			_fdPassword : null
		}, options || {})
		
		return {
			fdId : setting._fdId,
			fdLoginName : setting._fdLoginName,
			fdPassword : setting._fdPassword
		}
	};
	
	return PersonVO;
})
define([ "knockout" ], function(ko) {

	function Group(options) {

		var setting = $.extend({
			id : null,
			fdId : null,
			fdName : null,
			fdMemberName : null,
			fdIsAvailable : null,
			fdCateid : null,
			groupName : null,
			fdMemo :　null
		}, options || {})

		return setting;

	}
	;
	return Group;

})
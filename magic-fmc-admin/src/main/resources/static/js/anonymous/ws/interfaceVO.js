define(["knockout"],function(ko){
	function Interface(options){
		var setting=$.extend({
			fdId:null,
			fdUrl:null,
			fdCode:null,
			fdName:null,
			fdParentId:null,
			fdIsLeaf:null
		},options||{})
		
		return setting;
	};
		
	return Interface;
})
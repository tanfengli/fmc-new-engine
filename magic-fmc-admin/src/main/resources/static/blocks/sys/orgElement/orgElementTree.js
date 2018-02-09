/**
 * 
 */
define([ 'knockout' ], function(ko) {
	function orgElementTree(params) {
		
		var self = this;
		
		self.treeId = 'orgElementTree';
		
		self.selectNode = ko.observable();

		//树model
		self.orgElementTree = new ko.zTreeView(self.treeId, {
			url : 'sys/orgElement',
			callback : {
				treeNodeChanged : function(node) {
					self.selectNode(node);
				}
			}
		})
		
		self.passSelectNode = function(){
			params.callFunc(ko.toJS(self.selectNode));
		}
	};
	return orgElementTree;
});
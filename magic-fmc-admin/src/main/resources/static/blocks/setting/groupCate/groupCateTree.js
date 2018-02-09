/**
 * 
 */
define([ 'knockout' ], function(ko) {
	function groupCateTree(params) {
		
		var self = this;
		
		self.treeId = 'groupCateTree';
		
		self.selectNode = ko.observable();

		self.groupCateTree = new ko.zTreeView(self.treeId, {
			url : 'setting/groupCate',
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
	return groupCateTree;
});
/**
 * 
 */
define([ 'knockout' ], function(ko) {
	function commonTemplateTree(params) {
		
		var self = this;
		
		self.treeId = 'commonTemplateTree';
		
		self.selectNode = ko.observable();

		self.commonTemplateTree = new ko.zTreeView(self.treeId, {
			url : 'setting/sysNewsTemplate',
			callback : {
				treeNodeChanged : function(node) {
					self.selectNode(node);
				}
			}
		});
		
		self.passSelectNode = function(){
			params.callFunc(ko.toJS(self.selectNode));
		}
	};
	return commonTemplateTree;
});
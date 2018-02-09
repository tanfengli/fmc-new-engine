/**
 * 
 */
define([ 'knockout' ], function(ko) {
	function templateCateTree(params) {
		
		var self = this;
		
		self.treeId = 'templateCateTree';
		
		self.selectNode = ko.observable();

//		self.groupCateTree = new ko.treeViewModel(self.treeId, {
//			url : 'setting/category/cate',
//			width : "100%",
//			height : params.height,
//			callback : {
//				treeNodeChanged : function(node) {
//					self.selectNode(node);
//				}
//			},
//			nodeAdapter : {
//				afterTrans : function(newNode, node) {
//
//					newNode.items = new Array({
//							label : 'Loading...'
//						});
//					return newNode;
//				}
//			}
//		});
		self.templateCateTree = new ko.zTreeView(self.treeId, {
			url : 'setting/category/cate',
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
	return templateCateTree;
});
define(['knockout',"MsgBox"],function(ko,MsgBox){
	function templateTree(params){
		var self=this;
		
		self.treeId='searchTemplateTree';
		
		self.selectNode=ko.observable();

		self.groupCateTree=new ko.zTreeView(self.treeId,{
			url:"setting/sysNewsTemplate/categoryMain",
			callback:{
				treeNodeChanged:function(node){
					self.selectNode(node);
				}
			}
		})
		
		//点击前检阅
		self.submit=function(){
			var node = ko.toJS(self.selectNode);
			
			if(null!=node.fdHierarchyId||node.fdId=="0"){
				MsgBox.warning("请选择 流程模板.");
			}else{
				$("#submit").click();
			};
		}
		
		
		self.passSelectNode=function(){
			var node = ko.toJS(self.selectNode);
			params.callFunc(node);
		}
	};
	
	return templateTree;
});
 
define([ 'knockout'], function(ko) {
	function templateSelTree(params) {
		
		var self = this;
		//树Id
		self.treeId = 'authorizeMenuTree';
		//当前选中节点
		self.selectNode = ko.observable();
		//已选菜单列表
		self.chosenList = ko.observableArray([]);
		
		//模态框
		$('#authorizeMenuWindow').on('show.bs.modal',function(){
			var nodes = ko.toJS(params.data);
			var treeObj = $.fn.zTree.getZTreeObj("authorizeMenuTree");
			treeObj.checkAllNodes(false);
			for (var i=0, l=nodes.length; i < l; i++) {
				if(nodes[i].fdIsLeaf==1){
					var node = treeObj.getNodeByParam("fdId", nodes[i].fdId, null);
					if(null!=node)
						treeObj.checkNode(node, true, true);
				}
			}
			self.chosenList(nodes);
			
		})
		
		//节点勾选/取消勾选
		self.onCheck = function(treeNode){
			var changeCheckedNodes = self.groupCateTree.getChangeCheckedNodes();
			for(i=0;i<changeCheckedNodes.length;i++){
				//记录每次更改的值
				changeCheckedNodes[i].checkedOld = changeCheckedNodes[i].checked;
				//添加
				if(changeCheckedNodes[i].checked==true)
					self.chosenList.push(changeCheckedNodes[i]);
				//移除
				else
					self.chosenList.remove(function(item){return item.fdId==changeCheckedNodes[i].fdId});
			}
			var chosenList = self.chosenList();
			//去重
			self.chosenList(chosenList.unique());
		}
		
		//树model
		self.groupCateTree = new ko.zTreeView(self.treeId, {
			url : 'sys/menu/needAuth',
			chkEnable : true,
			callback : {
				treeNodeChanged : function(node) {
					self.selectNode(node);
				},
				onCheck : self.onCheck
			}
		})
		
		//数组去重
		Array.prototype.unique = function(){
			 var res = [];
			 var json = {};
			 for(var i = 0; i < this.length; i++){
			  if(!json[this[i].fdId]){
			   res.push(this[i]);
			   json[this[i].fdId] = 1;
			  }
			 }
			 return res;
			}
		
		//确定
		self.passSelectNode = function(){  
	       params.callFunc(self.chosenList());
		}
		
	};
	return templateSelTree;
});
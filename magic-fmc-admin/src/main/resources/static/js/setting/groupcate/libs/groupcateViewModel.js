
define([ "knockout","./groupcateVO.js","components","bootstrap" ], function(ko,GroupCate) {

function viewModel() {
	"use strict";

	var self = this;

	self.selectNode = ko.observable();
	self.treeId = 'groupCateTree';
	//页面显示
	self.detailList = ko.observable(false);
	self.edit = ko.observable(false);

	self.showDetail = function(){
		self.detailList(true);
		self.edit(false);
		
	}
	
	self.showEdit = function(){
		self.detailList(false);
		self.edit(true);
		
	}
	
	//节点更改触发
	self.nodeChange = function(node){
//		self.gridViewModel.findAll(node);
		self.editForm.setForm(new GroupCate(node));
	}
	
	//修改
//	self.toEdit = function(node){
//		self.editForm.setForm(new GroupCate(node));
//		// 保存成功回调
//		self.editForm.callback.saveSuccess =  function(newNode){
//			//刷新树节点
//			var treeObj = $.fn.zTree.getZTreeObj("groupCateTree");
//			var seletedNode = treeObj.getSelectedNodes()[0];
//			var node = treeObj.getNodeByParam("fdId", newNode.fdId, null);
//			node.fdName = newNode.fdName;
//			treeObj.updateNode(node);
//			//刷新列表
//			self.gridViewModel.findAll(node);
//			$("#myModal").modal("hide");
//		};
//		$("#myModal").modal();
//	}
	
	// 新增表单viewModel
	self.editForm = new ko.formViewModel({
		url : 'setting/groupCate',
		callback:{
				beforeSave: function(viewModel,formItem) {
					var isValid = ko.validation.group(formItem);
					//显示没通过消息
					isValid.showAllMessages();
					if (isValid().length!=0) {
						return -1;
					}
				},
				saveSuccess: function(newNode){
					var treeObj = $.fn.zTree.getZTreeObj("groupCateTree");
					var seletedNode = treeObj.getSelectedNodes()[0];
					var node = treeObj.getNodeByParam("fdId", newNode.fdId, null);
					node.fdName = newNode.fdName;
					node.fdAlterTime = newNode.fdAlterTime;
					treeObj.updateNode(node);
					self.nodeChange(node);
				}
			}
		});
	
	// 新增表单viewModel
	self.addForm = new ko.formViewModel({
		url : 'setting/groupCate',
		callback:{
				beforeSave: function(viewModel,formItem) {
					var isValid = ko.validation.group(formItem);
					//显示没通过消息
					isValid.showAllMessages();
					if (isValid().length!=0) {
						return -1;
					}
				},
				saveSuccess: function(){}
			}
		});
	
	/**
	 * 添加节点(添加同级或下级前操作)
	 * 
	 */
	self.toAdd = function(parentNode,addFunc){
		var parentId = null;
		var treeObj = $.fn.zTree.getZTreeObj(self.treeId);
		if(null!=parentNode){
			treeObj.expandNode(parentNode,true);
			parentId = parentNode.fdId
		}
		
		var groupCate = new GroupCate({});
		// 设置父级Id
		groupCate.fdParentId=parentId;
		self.addForm.setForm(groupCate);
		
		// 添加节点成功回调
		self.addForm.callback.saveSuccess =  function(newNode){
			addFunc(newNode);
			$("#myModal").modal("hide");
		};
		//打开添加节点窗口
		$("#myModal").modal();
	}
	//树model
	self.groupCateTree = new ko.zTreeView(self.treeId, {
		url : 'setting/groupCate',
		callback : {
			treeNodeChanged : self.nodeChange,
			beforeAddNode : self.toAdd,
			beforeRemove: function(treeId,node){
				if(confirm("确认删除节点"+node.fdName+"吗？")){
					return true;
				}
				return false;
			}
		}
	})
	
	//列表model
	self.gridViewModel = new ko.gridViewModel({
		url : 'setting/groupCate',
		size : 15,
		isAutoRetrieve : 'false',
		callback : {
			itemEdit: self.toEdit
		}
	});
	
	
};
return viewModel;
})



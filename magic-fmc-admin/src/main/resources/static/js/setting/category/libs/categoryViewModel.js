
define([ "knockout", 'AjaxUtil',"./categoryVO.js","MsgBox","components","bootstrap" ], function(ko,AjaxUtil,
		SysCategory, MsgBox) {

function viewModel() {
	"use strict";
	
	var self = this;

	self.selectNode = ko.observable();
	var sysCategory = new SysCategory({});
	self.detail = ko.observableArray();// 右侧表单
	//传给组件的列表
	self.elementArray = ko.observableArray([]);
	self.pattern = ko.observable('group');
	
	// 传给组件的form
	self.elementForm = ko.pureComputed(function() {
		return {
			elementArray : ko.toJS(self.elementArray),
			pattern : ko.toJS(self.pattern)
		};
	}, this);
	
	self.compute = function(array){
		self.elementArray(array);
	};
	
	
	// 新增、编辑表单
	self.addFormPanel = document.getElementById("add_Form");
	// 新增、编辑表单viewmodel
	self.addForm = new ko.formViewModel({
		url : 'setting/category',
		callback:{
				beforeSave : function() {
					var formItem = self.addForm.formItem();
					formItem.isValid = ko.validation.group(formItem);
					//显示没通过消息
					formItem.isValid.showAllMessages();
					if (formItem.isValid().length!=0) {
						return -1;
					}
				},
				saveSuccess: function(){}
			}
		});

	//添加节点
	self.toAdd = function(parentNode, addFunc){
		var parentId = null;
		var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
		if(null!=parentNode){
			if('0'!=parentNode.fdId){
				treeObj.expandNode(parentNode,true);
				parentId = parentNode.fdId
			}
		}else{
			MsgBox.warning('当前节点不能添加同级。');
			return;
		}
		var newSysCategory = new SysCategory({});
		newSysCategory.fdParentId = parentId;
		self.addForm.setForm(newSysCategory);
		// 添加成功回调
		self.addForm.callback.saveSuccess =  function(newNode){
			addFunc(newNode);
			$("#myModal").modal("hide");
		};
		
		$("#myModal").modal();
	}
	
	// 节点变化
	self.nodeChange = function(node){
		self.gridViewModel.findAll(node);
	}
	
	// 修改
	self.toEdit = function(node){
		// 获取修改form
		AjaxUtil.call({
			url :　'setting/category/findForm',
			type : 'post',
			data : node,
			success : function(data){
				self.addForm.setForm(new SysCategory(ko.toJS(data)));
				
				var memberNames = "";
				// 以字符串类型返回群组成员名称
				for (var i=0;i<data.editorArray.length;i++){
					memberNames = memberNames+data.editorArray[i].fdName+';';
				} 
				$('#editor').val(memberNames);
				// 以字符串类型返回群组成员名称
				memberNames = "";
				for (var i=0;i<data.readerArray.length;i++){
					memberNames = memberNames+data.readerArray[i].fdName+';';
				}
				$('#reader').val(memberNames);
				
				$("#myModal").modal();
			}
		});
		
		// 保存成功回调
		self.addForm.callback.saveSuccess =  function(newNode){
			var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
			var seletedNode = treeObj.getSelectedNodes()[0];
			//更新左边数节点
			var node = treeObj.getNodeByParam("fdId", newNode.fdId, null);
			if(null!=node){
				node.fdName = newNode.fdName;
				treeObj.updateNode(node);
			}
			//刷新右边列表
			self.gridViewModel.findAll(seletedNode);
			$("#myModal").modal("hide");
		};

	}
	
	// 树model
	self.categoryTree = new ko.zTreeView('categoryTree', {
		url : 'setting/category',
		callback : {
			treeNodeChanged : self.nodeChange,
			beforeAddNode : self.toAdd
		}
	});
	
	
	// 列表viewModel
	self.gridViewModel = new ko.gridViewModel({
		url : 'setting/category',
		size : 12,
		isAutoRetrieve : 'false',
		callback : {
			itemEdit: self.toEdit
		}
	});
	
	// 组件回调函数
	self.selectNewElement = function(elements,memberNames){
		
		if(ko.toJS(self.addForm.formItem()).clickType=='editor')
		{
			$('#editor').val(memberNames);
			self.addForm.formItem().editorArray=ko.toJS(elements);
		}else{
			$('#reader').val(memberNames);
			self.addForm.formItem().readerArray=ko.toJS(elements);
		}
		
	}
	
	
};

return viewModel;
})



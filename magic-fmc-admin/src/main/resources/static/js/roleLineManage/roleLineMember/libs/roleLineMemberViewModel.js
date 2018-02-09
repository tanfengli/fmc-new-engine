
define([ "knockout",'AjaxUtil', "./roleLineMemberVO.js","./subViewModel/checkRepeatViewModel.js","MsgBox","components","bootstrap" ], 
		function(ko,AjaxUtil,
			OrgRoleLine,
			CheckRepeatViewModel,
			MsgBox) {
	
	function viewModel() {
	"use strict";

	var self = this;
	
	// 上级名称
	self.parentName = ko.observable('');
	// 角色线名称
	self.confName = ko.observable('');
	self.showTree = ko.observable('tree');
	self.elementArray = ko.observableArray([]);
	self.selectNode = ko.observable();
	self.treeId = 'roleLineMemberTree';
	self.treePanel = $('#' + self.treeId);
	self.memberId = ko.observable('');
	
	// 角色线成员列表viewModel
	self.gridViewModel = new ko.gridViewModel({
		url : 'roleLineManage/roleLineMember/subMembers',
		size : 12
	});
	
	 // 新增、编辑节点表单viewModel
	self.addForm = new ko.formViewModel({
		url : 'roleLineManage/roleLineMember',
		callback:{
				saveSuccess: function(){}
			}
		});
	
	// 检查重复viewModel
	self.checkRepeatVM = new CheckRepeatViewModel({
		selectNode : self.selectNode
	});
	
	// 角色线成员列表查询
	self.search = function(){
		var memberName = $('#memberName_search').val();
		var	node;
		node=self.selectNode();
		if(node==undefined){
			var treeObj = $.fn.zTree.getZTreeObj(self.treeId);
			node = treeObj.getSelectedNodes()[0];
		}
		node.searchMemberName=memberName;
		var obj = {hierarchyId: node.hierarchyId,confId: node.confId,fdName: memberName}
		self.gridViewModel.findAll(node);
	}
	
	/***************************************角色线成员树节点添加，编辑，删除操作--Start*******************************************/
	
	// 单击节点
	self.onTreeNodeChanged = function(node) {
		var isBlank = function(obj){
			if(obj==undefined||obj==null||obj==""||obj==" "){
				return true;
			}
			return false;
		}
		// 获取当前选中节点
		if(isBlank(node)){
			var treeObj = $.fn.zTree.getZTreeObj(self.treeId);
			node = treeObj.getSelectedNodes()[0];
		}
		if(isBlank(node)||node.fdId=='0'){
			return;
		}
		self.selectNode(node);
		self.search();
	};
	
	// 添加节点
	 self.addTreeNode = function(parentNode, addFunc) {
		 
		var roleLineMember = new OrgRoleLine({});
		 
		if(!self.isCanAdd()){
			return ;
		 }
		//展开节点
		var treeObj = $.fn.zTree.getZTreeObj(self.treeId);
		treeObj.expandNode(parentNode,true);
		// 设置父级Id(非角色线下级时)
		if(parentNode.isRoleMember!="0"){
			roleLineMember.fdParentId=parentNode.fdId;
		}
		//角色线分类id
		roleLineMember.fdRoleLineConfId=parentNode.confId;
		roleLineMember.parentName=parentNode.fdName;
		self.addForm.setForm(roleLineMember);
		
		// 添加成功回调
		self.addForm.callback.saveSuccess =  function(newNode){
			addFunc(newNode);
			$("#myModal").modal("hide");
		};
		
		$("#myModal").modal();
	}
	
	// 修改节点
	 self.editTreeNode = function(element, editFunc) {
		 if(element.isRoleMember=="0"){
			 MsgBox.warning('当前节点不可以修改！');
			 return ;
		 }
		 
		 AjaxUtil.call({
			 url : 'roleLineManage/roleLineMember/getOne',
			 type : 'post',
			 data : {fdId : element.fdId},
			 async : false,
			 success : function(data){
				 var form = new OrgRoleLine(data);
				 if(data.fdParentId==null){
					 form.parentName=element.confName;
				 }else{
					 form.parentName=element.parentName;
				 }
				 form.memberName=element.memberName;
				 self.addForm.setForm(form);
			 }
		 })
		 
		 // 修改成功回调
		 self.addForm.callback.saveSuccess =  function(newNode){
			 	element = $.extend(element,newNode);
				editFunc(element);
				$("#myModal").modal("hide");
				self.search();
			};
		 
		 $("#myModal").modal();

	}
	 
	// 删除节点前操作
	 self.beforeDeleteNode = function(treeId,node){
		 if(node.isRoleMember=="0"){
			 MsgBox.warning('当前节点不可以删除！');
			 return false;
		 }
		 if(confirm("请确认是否继续删除["+node.fdName+"]?")){
			 return true;
		 }else{
			 return false;
		 }
	 }
	 
	// 快速新建下级节点
	 self.addQuickly = function(){
		 if(!self.isCanAdd()){
			return ;
		 }
		 $('#memberSelectWindow').modal();
	 }
	
	// 快速新建回调函数
	self.selectElementQuickly = function(element,memberNames,memberIds){
		
		var treeObj = $.fn.zTree.getZTreeObj(self.treeId);
		var roleMember = treeObj.getSelectedNodes()[0];
		treeObj.expandNode(roleMember, true);
		if(roleMember.isRoleMember=="0"){
			roleMember.newParentId=null;
		}else{
			roleMember.newParentId=roleMember.fdId;
		}
		
		AjaxUtil.call({
			url : 'roleLineManage/roleLineMember/addQuickly',
			type : 'post',
			data : {context1 : ko.toJSON(roleMember),context2 : memberIds},
			success : function(data){
				//添加节点
				var newNodeList = data;
				treeObj.addNodes(roleMember, newNodeList);
			}
		})
	}
	
	///新节点移动
	self.beforeDrag = function (treeNodes){
		var node = treeNodes[0];
	    if (node.isRoleMember == '0')
	    	return false;
	}
	
	var isTargetNodeOpen = false;
	//节点落下前
	self.beforeDrop = function (treeNodes, targetNode, moveType){
		var node = treeNodes[0];
		var dropNode = targetNode;
		var newParentNode = null;
		//获取即将移动后的新父节点
		if(moveType=="inner"){
			newParentNode = dropNode;
		}else{
			AjaxUtil.call({
				url : 'roleLineManage/roleLineMember/getParentNode',
				data : dropNode,
				async : false,
				type : 'post',
				success : function(data){
					newParentNode = data;
				}
			})
		}
		//节点落下前校验
		if(!self.beforeNodeDrop(node,newParentNode)){
			return false;
		}
		node.newParentId = newParentNode.fdId;
		node.newConfId = newParentNode.confId;
		node.isParentRoleMember = newParentNode.isRoleMember;
		var flag = false;
		//更新角色线成员架构
		AjaxUtil.call({
			url : 'roleLineManage/roleLineMember/moveNode',
			data : node,
			async : false,
			type : 'post',
			success : function(data){
				flag = true;
				self.onTreeNodeChanged();
			}
		})
		if(flag){
			//记录当前节点是否为展开状态
			isTargetNodeOpen = targetNode.open;
			return true;
		}
		return false;
	}
	//节点落下后
	self.onDrop = function (treeNodes, targetNode, moveType){
		if('inner'==moveType&&!isTargetNodeOpen){
			var treeObj = $.fn.zTree.getZTreeObj(self.treeId);
			treeObj.reAsyncChildNodes(targetNode, "refresh");
		}
	}
	/////End

	// 节点移动落下前校验
	self.beforeNodeDrop = function(node,newParentNode){
		
		if(null==newParentNode||""==newParentNode){
			MsgBox.warning("不能移动到当前位置！");
			return false;
		}
		
		if(newParentNode.fdOrgType=="1"||newParentNode.fdOrgType=="2"){
			MsgBox.warning("不能移动到机构或部门下面");
			return false;
		}
		
		if(!confirm("确认移动【"+node.fdName+"】节点吗？")){
		    return false;
		}
		
		return true;
		
	}
	
	
	/***************************************角色线成员树节点添加，编辑，删除操作--End*******************************************/
	
	 
	// 选择成员组件的form
	self.elementForm = ko.pureComputed(function() {
		return {
			elementArray : ko.toJS(self.elementArray)
		};
	}, this);
 
	// tree Model
	self.roleLineMemberTree = new ko.zTreeView(self.treeId, {
		url : 'roleLineManage/roleLineMember',
		editEnable : true,
		callback : {
			treeNodeChanged : self.onTreeNodeChanged,
			beforeAddNode : self.addTreeNode,
			beforeEditNode : self.editTreeNode,
			beforeRemove : self.beforeDeleteNode,
			beforeDrag : self.beforeDrag,
			onDrag : self.onDrag,
			beforeDrop : self.beforeDrop,
			onDrop : self.onDrop
		}
	});
	$.fn.zTree.getZTreeObj(self.treeId).setting.edit.drag.autoExpandTrigger=false;
	
	// 选择成员回调函数
	self.selectNewElement = function(element){
		self.addForm.formItem().fdMemberId=ko.toJS(element).fdId;
		self.addForm.formItem().memberName=ko.toJS(element).fdName;
		self.addForm.setForm(self.addForm.formItem());
	}
	
	 // 保存节点前校验
	 self.beforeAddSubmit = function(){
		 var member = self.addForm.formItem();
		 
		 if(!member.fdName&&!member.fdMemberId){
			 MsgBox.warning('自定义名称或成员选择至少需要填写一个！');
		 }else{
			 $('#submit').click();
		 }
		 
	 }
	
	// 添加下级节点前校验
	self.isCanAdd = function(roleMember){
		if(undefined==roleMember){
			var treeObj = $.fn.zTree.getZTreeObj(self.treeId);
			roleMember = treeObj.getSelectedNodes()[0];
		}
		if(roleMember.fdId=='0'){// 选中第一级节点
			MsgBox.warning('该节点下不能添加下级');
			return false;
		 }
		if(roleMember.fdOrgType=='1'||roleMember.fdOrgType=='2'){//机构或部门下面不能创建角色
			MsgBox.warning('机构或部门下面不能创建角色');
			return false;
		}
		return true;
	}
	
	
};
	return viewModel;
})

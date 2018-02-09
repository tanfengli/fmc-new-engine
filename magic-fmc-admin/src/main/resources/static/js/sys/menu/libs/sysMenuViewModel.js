define([ "knockout", "./sysMenuVO.js", 'AjaxUtil','MsgBox',"components","bootstrap" ], function(ko,
		SysMenu,AjaxUtil,MsgBox ) {  
	
	function viewModel() {
		if (!ko.components.isRegistered("defineParameter")){
			ko.components.register('defineParameter',{
			    viewModel:{
			       require:'../blocks/sys/defineParameter/defineParameter.js'
			    },
			    template:{
			    	 require:'text!blocks/sys/defineParameter/defineParameter.html'
			    }
			});
		}
		
		var self = this;
		//当前选中节点
		self.selectNode = ko.observable();
		
		//查询
		self.search = function(){
			var node = self.selectNode();
			var filterName = $('#filterName_search').val();
			node.filterName = filterName;
			self.gridViewModel.findAll(node);
		}
		
		
		/*********************树节点添加/编辑Start**************************/
		// 添加节点
		self.beforeAdd = function(parentNode, addFunc){
			//设置父级id和业务系统id
			var parentId = null;
			var busiId = null;
			if(null==parentNode){
				MsgBox.warning('当前节点不能添加同级。');
				return;
			}else{
				parentId = parentNode.fdHierarchyId==null?null:parentNode.fdId;
				busiId = parentNode.fdHierarchyId==null?parentNode.fdId:parentNode.busiSysId;
			}
			
			var sysMenu = new SysMenu({});
			sysMenu.fdParentId = parentId;
			sysMenu.busiSysId = busiId;
			self.editForm.setForm(sysMenu);
			// 添加成功回调
			self.editForm.callback.saveSuccess = function(newNode){
				addFunc(newNode);
				$("#menuModal").modal("hide");
			};
			
			$("#menuModal").modal();
		}
		
		self.paramList = ko.observableArray();
		self.parameterCallback=function(params){
			self.paramList(params);
			self.editForm.formItem().fdParameter(ko.toJSON(params));
		}
		// 编辑节点
		self.toEdit = function(sysMenu){
			//获取编辑form
			AjaxUtil.call({
				url:"sys/menu/getFormItem",   
				data:{context:ko.toJSON(sysMenu)},
				async:false,
				success:function(data){
					self.paramList(data.paramList);
					self.editForm.setForm(new SysMenu(data));
				}
			}); 
			
			$("#menuModal").modal();
			// 保存成功回调
			self.editForm.callback.saveSuccess =  function(newNode){
				var treeObj = $.fn.zTree.getZTreeObj("menuTree");
				var seletedNode = treeObj.getSelectedNodes()[0];
				//更新左边树节点
				var node = treeObj.getNodeByParam("fdId", newNode.fdId, null);
				if(null!=node){
					node.fdName = newNode.fdName;
					treeObj.updateNode(node);
				}
				//刷新右边列表
				self.gridViewModel.findAll(seletedNode);
				$("#menuModal").modal("hide");
			};
		}
		
		/*********************树节点添加/编辑End**************************/
		
		
		// 流程菜单树model
		self.menuTree = new ko.zTreeView('menuTree', {
			url : 'sys/menu',
			pIdKey : 'fdParentId',
			editEnable : true,
			callback : {
				treeNodeChanged : function(node) { 
					self.selectNode(node);
					self.search();
				},
				beforeAddNode : self.beforeAdd,
				beforeRemove : function(treeId,node){
					 if(node.fdHierarchyId==null||node.fdHierarchyId==""){
						 MsgBox.warning('业务系统不可删除。')
						 return false;
					 }
					 if(confirm("确认是否删除【"+node.fdName+"】，同时会删除该菜单下的所有子菜单。")){
						 return true;
					 }else{
						 return false;
					 }
				},
				beforeDrag : function (treeNodes){
					var node = treeNodes[0];
				    if (node.fdId == '0')
				    	return false;
				},
				beforeDrop : function (treeNodes, targetNode, moveType){
					var isDropSuccess = false;
					var node = treeNodes[0];
					//落下前校验
					if(targetNode.fdHierarchyId==null){
						if(moveType!='inner'){
							MsgBox.warning('不能移动到根节点。')
							return false;
						}else if(targetNode.fdId!=node.busiSysId){
							MsgBox.warning('不能移动到其它业务系统。')
							return false;
						}
					}
					if(!confirm("确认移动【"+node.fdName+"】节点吗？")){
					    return false;
					}
					
					node.moveType=moveType;
					var nodeList = new Array(2);
					nodeList[0]=node;
					nodeList[1]=targetNode;
					AjaxUtil.call({
						url : 'sys/menu/moveNode',
						type : 'post',
						data : {context : ko.toJSON(nodeList)},
						async : false,
						success : function(data){
							isDropSuccess=true;
						}
					})
					if(!isDropSuccess){
						return false;
					}
					
					return true;
				}
			}
		});
		
		// 列表viewModel
		self.gridViewModel = new ko.gridViewModel({
			url : 'sys/menu',
			size : 12,
			isAutoRetrieve : 'false',
			callback : {
				itemEdit: self.toEdit
			}
		});
		
		// 流程菜单新增/编辑model
		self.editForm = new ko.formViewModel({
			url : 'sys/menu',
			callback:{
				beforeSave : function(formViewModel,formItem) {
					var isValid = ko.validation.group(formItem());
					//显示没通过消息
					isValid.showAllMessages();
					if (isValid().length!=0) {
						return -1;
					}
				},
				saveSuccess: function(){}
			}
		},SysMenu);
	 
	};  
	 
	return viewModel;
}); 
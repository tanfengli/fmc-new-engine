
define([ "knockout",'AjaxUtil',"./roleassignEditVO.js","components","bootstrap" ], 
		function(ko,AjaxUtil,RoleAssignEdit) {  

function viewModel() {
	/**********分配用户组件**********/
	if(!ko.components.isRegistered("inheritRole")){
		ko.components.register('inheritRole',{
		    viewModel:{
		       require:'../blocks/sys/inheritRole/inheritRole.js'
		    },
		    template:{
		       require:'text!blocks/sys/inheritRole/inheritRole.html'
		    }
		});
	}
	/**************菜单授权组件*****************/
	if(!ko.components.isRegistered("authorizeMenu")){
		ko.components.register('authorizeMenu',{
		    viewModel:{
		       require:'../blocks/sys/authorizeMenu/authorizeMenu.js'
		    },
		    template:{
		       require:'text!blocks/sys/authorizeMenu/authorizeMenu.html'
		    }
		});
	}

	var self = this;
	
	self.uraList = ko.observableArray();
	// 传给组件的form
	self.elementForm = ko.observable();
	self.roleAssign = new RoleAssignEdit({});
	self.cateId = ko.observable();
	
	// 查询框使用
	self.searchForm = new ko.searchViewModel(ko.toJS(new RoleAssignEdit({})),{
		callback: {
			search: function(searhForm){
				self.roleAssignId.findAll(searhForm)
			}
		}
	});
	
	// 新增
	self.toAdd = function() {
		self.roleAssignForm.setForm(new RoleAssignEdit({}));
		$('#myModal').modal();
	};
	
	// 刷新
	self.toReFresh = function(){
		$('#myModal').modal('hide');
		self.roleAssignId.refresh();
	}

	self.isEditing = false;
	// 修改
	self.toEdit = function(roleassign) {
		AjaxUtil.call({// 获得角色分配修改form
			url: "roleManage/roleAssign/findForm",   
			type : "post",
			data :　roleassign,
			dataType : "JSON",
			success : function(data) {
				self.isEditing = true;
				self.uraList(data.uraList);
				self.roleAssignForm.setForm(new RoleAssignEdit(ko.toJS(data)));
				$('#myModal').modal();
			}
		}); 
	};
	
	//编辑框关闭时
	$('#myModal').on('hide.bs.modal', function () {
		self.isEditing = false;
	})
	
	/************菜单授权Start************/
	self.authorizeMenuForm = new ko.formViewModel({
		url : 'roleManage/roleAssign/authorizeMenu'
	})
	self.menuList = ko.observableArray([]);
	self.initAuthorizeMenu = function(role){
		AjaxUtil.call({
			url : 'roleManage/roleAssign/getAuthorizedMenu',
			data : {fdId : role.fdId()},
			success : function(data){
				self.menuList(data)
				self.authorizeMenuForm.setForm(new RoleAssignEdit(ko.toJS(role)));
				$('#authorizeMenuWindow').modal();
			}
		})
	}
	self.authorizeMenu = function(menus){
		self.authorizeMenuForm.formItem().authorizedMenus(menus);
		self.authorizeMenuForm.saveOrUpdateList();
	}
	/************菜单授权End************/
	
	/************分配用户Start*************/
	self.assignUserForm = new ko.formViewModel({
		url : 'roleManage/roleAssign/assignUser'
	})
	
	self.initAssignUser = function(role){
		AjaxUtil.call({
			url : 'roleManage/roleAssign/getAssignedUser',
			data : {fdId : role.fdId()},
			success : function(data){
				self.elementForm({elementArray : data,orgTypeArr : ['8']})
				role.uraList=data;
				self.assignUserForm.setForm(new RoleAssignEdit(ko.toJS(role)));
				$('#memberSelectWindow').modal();
			}
		})
		
	}
	
	self.assignUser = function(elements){
		self.assignUserForm.formItem().uraList(elements);
		self.assignUserForm.saveOrUpdateList();
	}
	/************分配用户End*************/
	
	/************继承角色Start****************/
	self.inheritRoleArr = ko.observableArray();
	self.initInheritRole = function(){
		var form = self.roleAssignForm.formItem();
		self.inheritRoleArr(form.inhRoleList());
		$('#inheritRoleWindow').modal();
	}
	
	self.inheritRole = function(elements,elementNames){
		var form = self.roleAssignForm.formItem();
		form.inhRoleList(elements);
		form.inhRoleName(elementNames);
	}
	/************继承角色End******************/
	
	/************角色可维护者Start*************/
	self.initSetEditor = function(){
		var form = self.roleAssignForm.formItem();
		self.elementForm({elementArray : form.edtList()});
		$('#memberSelectWindow').modal();
	}
	
	self.setEditor = function(elements,elementNames){
		var form = self.roleAssignForm.formItem();
		form.edtList(elements);
		form.edtName(elementNames);
	}
	/************角色可维护者End*************/
	
	// 获得角色分类start--------------------
	var category = function(name, id) {
		this.cateName = name;
		this.cateId = id;
	};

	self.categoryArray = ko.observableArray([new category("无分类")]);
	
	self.getIdAndName = function() {
		AjaxUtil.call({
			type : "POST",
			url : 'roleManage/roleAssign/findCategory',
			success : function(data) {
				for(var i=0;i<data.length;i++)
				{
				self.categoryArray.push(new category(data[i].fdName,data[i].fdId));
				}
			}
		});
	}();
	// 获得角色分类end--------------------
	
	// 地址本组件回调函数
	self.selectNewElement = function(elements,memberNames){
		if(!self.isEditing){
			self.assignUser(elements);
		}else{
			self.setEditor(elements,memberNames);
		}
	}
	
	//角色分配管理列表viewModel
	self.roleAssignId = new ko.gridViewModel({
		url : 'roleManage/roleAssign',
		callback : {
			itemEdit : self.toEdit
		}
	});

	// 新增、编辑表单viewModel
	self.roleAssignForm = new ko.formViewModel({
		url : 'roleManage/roleAssign',
		callback : {
			beforeSave:function(formViewModel, formItem){
				//校验
				var vaildResult = ko.validation.group(formItem);
				//显示没通过消息
				vaildResult.showAllMessages();
				if (vaildResult().length!=0) {
					return -1;
				}
			},
			saveSuccess : self.toReFresh
		}
	});

};

return viewModel;
})
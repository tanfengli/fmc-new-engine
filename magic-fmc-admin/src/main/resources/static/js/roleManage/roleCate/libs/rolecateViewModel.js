
define([ "knockout", "./rolecateVO.js","components","bootstrap" ], function(ko,
		RoleCate) {  

function viewModel() {

	// 查询框使用
	self.newRoleCate = new RoleCate({});
	self.searchForm = new ko.searchViewModel(self.newRoleCate,{
		callback: {
			search: function(searhForm){
				self.roleCateId.findAll(searhForm)
			}
		}
	});
	// 用户列表
	self.gridPanel = document.getElementById("rolecateId");

	// 新增
	self.toAdd = function() {
		self.toEdit(new RoleCate());
	};

	// 修改
	self.toEdit = function(rolecate) {
		self.roleCateForm.setForm(new RoleCate(ko.toJS(rolecate)));
		$('#myModal').modal();
	};

	// 刷新
	self.toRefresh = function() {
		$('#myModal').modal('hide');
		self.roleCateId.refresh();
	}

	self.roleCateId = new ko.gridViewModel({
		url : 'roleManage/roleCate',
		callback : {
			itemEdit : self.toEdit
		}
	});

	// 新增，编辑表单
	self.editFormPanel = document.getElementById("rolrcateForm");

	// 新增、编辑表单viewModel
	self.roleCateForm = new ko.formViewModel({
		url : 'roleManage/roleCate',
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
			saveSuccess : self.toRefresh
		}
	});

};

return viewModel;
})



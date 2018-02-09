define(["knockout","./PersonVO.js","components"], function (ko,OrgPerson) {
	 function viewModel() {
		"use strict";
	
		var self = this;

		//页面切换标识
		self.visibleCatalog = ko.observable(true);
		//查询框使用
		self.searchForm = new ko.searchViewModel(new OrgPerson({}),{
			callback: {
				search: function(searhForm){
					self.orgPersongrid.findAll(searhForm)
				}
			}
		});
	  
		//新增
		self.toAdd = function(){
			self.toEdit(new OrgPerson({}));
		};
		// 编辑
		self.toEdit = function(person) {
			self.orgPersonForm.setForm(person);
			self.visibleCatalog(false);
		}
		
		self.orgPersongrid = new ko.gridViewModel({
			url : 'sys/orgPerson',
			callback : {
				itemEdit: self.toEdit
			}
		});
		 
		//新增、编辑表单viewmode
		self.orgPersonForm = new ko.formViewModel({
			url : 'sys/orgPerson'
		});
	
	};
	return viewModel;
})
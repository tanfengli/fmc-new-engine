define([ "knockout", "./postionVO.js", "components","bootstrap" ], function(ko,
		PostionManagement) { 
	
	function viewModel() {
		var self = this;
		
		self.visibleCatalog = ko.observable(true);
	 
		//查询框使用
		self.searchForm = new ko.searchViewModel(new PostionManagement({}),{
			callback: {
				search: function(searhForm){
					self.grid.findAll(searhForm)
				}
			}
		});
		
		self.toAdd = function(){
			var form = new PostionManagement({})
			form.fdPlugin("sysOrgPlugin_Leader");
			form.fdRtnValue=12;
			self.postionForm.setForm(form);
			$('#myModal').modal();
		};
		
		self.toEdit = function(def) {		
			self.postionForm.setForm(new PostionManagement(def)); 
			$('#myModal').modal(); 
		}
		
		self.grid = new ko.gridViewModel({
			url : 'sys/generalPostion',
			callback : {
				itemEdit: self.toEdit
			}
		});
		
		ko.formViewModel.prototype = {
			elementSelected : ko.observableArray(),
			selMul: ko.observableArray()
		}
		
		self.onAfterSetForm = function(formViewModel, formItem) {
	
			formViewModel.elementSelected.removeAll();
			formViewModel.selMul.removeAll();
	
			if ((formItem.fdRtnValue & 1) > 0)
				formViewModel.elementSelected.push("1");
			if ((formItem.fdRtnValue & 2) > 0)
				formViewModel.elementSelected.push("2");
			if ((formItem.fdRtnValue & 4) > 0)
				formViewModel.elementSelected.push("4");
			if ((formItem.fdRtnValue & 8) > 0)
				formViewModel.elementSelected.push("8");
	
			if ((formItem.fdIsMultiple & 1) > 0)
				formViewModel.selMul.push("1");
			else
				formViewModel.selMul.push("0");
		};
		
		self.onBeforeSave = function(formViewModel, formItem){
			
			var checkValue = 0;  
			var cv = 0;
			for(var i=0; i<formViewModel.elementSelected().length; i++){
				checkValue += parseInt(formViewModel.elementSelected()[i]);
			} 
			for(var i=0; i<formViewModel.selMul().length; i++){
				cv += parseInt(formViewModel.selMul()[i]);
			} 
			formItem().fdRtnValue=checkValue; 
			formItem().fdIsMultiple=cv;  
			//校验
			var vaildResult = ko.validation.group(formItem);
			//显示没通过消息
			vaildResult.showAllMessages();
			if (vaildResult().length!=0) {
				return -1;
			}
		
		}
	
	
		self.onSaveSuccess = function(){
			self.grid.refresh();
			$("#myModal").modal("hide");
		}
		 
		self.postionForm = new ko.formViewModel({
			url : 'sys/generalPostion',
			callback : {
				afterSetForm : self.onAfterSetForm,
				beforeSave : self.onBeforeSave,
				saveSuccess:self.onSaveSuccess
			}
		});
	};
	
	return viewModel;
})
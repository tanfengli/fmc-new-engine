 
function viewModel() {
	var self = this;
	
	self.visibleCatalog = ko.observable(true);
	 
	self.gridPanel = document.getElementById("datagrid_panel");
	   
	self.toAdd = function(){
		self.basicSetForm.setForm(new VariableSet({}));
		$('#myModal').modal();
	};
	
	self.toEdit = function(vs) {		
		self.basicSetForm.setForm(new VariableSet(vs)); 
		$('#myModal').modal(); 
	}
	
	self.grid = new ko.gridViewModel(self.gridPanel,{
		url : 'sys/variableSetting',
		callback : {
			onItemEdit: self.toEdit
		}
	});
	
	self.editFormPanel = document.getElementById("datagrid_Form");
	
	self.basicSetForm = new ko.formViewModel(self.editFormPanel,{
		url : 'sys/variableSetting'
	});

};  

infuser.defaults.templateSuffix = "";
infuser.defaults.templateUrl = "/getViews";

ko.applyBindings(new viewModel());
define([ "knockout", "./sysLogErrorVO.js", "components","bootstrap" ], function(ko,
		SysLogError) {  
	
	function viewModel() {
		var self = this;
		
		self.visibleCatalog = ko.observable(true);
	 
		//查询框使用
		self.searchForm = new ko.searchViewModel(new SysLogError({}),{
			callback: {
				search: function(searhForm){
					self.grid.findAll(searhForm)
				}
			}
		});
		
		self.toEdit = function(om) {		
			self.form.setForm(new SysLogError(om)); 
			$('#myModal').modal(); 
		}
		
		self.grid = new ko.gridViewModel({
			url : 'sys/sysLogError',
			callback : {
				itemEdit: self.toEdit
			}
		});
		
		self.onSaveSuccess = function(){
			self.grid.refresh();
			$("#myModal").modal("hide");
		}

		self.form = new ko.formViewModel({
			url : 'sys/sysLogError',
			callback : {
				  saveSuccess:self.onSaveSuccess
			}
		});
	
	};  
	 
	return viewModel;
})
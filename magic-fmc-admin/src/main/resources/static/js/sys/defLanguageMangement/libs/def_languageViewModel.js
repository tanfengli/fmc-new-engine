define([ "knockout", "./defLanguageVO.js", "components","bootstrap" ], function(ko,
		DefLanguage) {

	function viewModel() {
		
		var self = this;
		
		self.visibleCatalog = ko.observable(true);
		
		//查询框使用
		self.searchForm = new ko.searchViewModel(new DefLanguage({}),{
			callback: {
				search: function(searhForm){
					self.grid.findAll(searhForm)
				}
			}
		});

	 
		self.toAdd = function() {
			self.defLanguageForm.setForm(new DefLanguage());
			$('#myModal').modal();
		};

		self.toEdit = function(def) {
			self.defLanguageForm.setForm(self.grid.getCurrentRow());
			$('#myModal').modal();
		}

		self.grid = new ko.gridViewModel({
			url : 'sys/defLanguage',
			callback : {
				itemEdit : self.toEdit
			}
		});
 
		self.onSaveSuccess = function() {
			$('#myModal').modal('hide');
			self.grid.refresh();
		}

		self.defLanguageForm = new ko.formViewModel({
			url : 'sys/defLanguage',
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
				saveSuccess : self.onSaveSuccess
			}
		});

	};

	return viewModel;
});
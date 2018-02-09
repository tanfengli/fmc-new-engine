define([ "knockout", "./formVO.js", "components","bootstrap" ], function(ko,
		formVo) { 
	
	function viewModel() {
		var self = this;
			
		self.basicForm = new ko.formViewModel({
			url : 'flowchart/setting'
		});
		
		self.operateForm = new ko.formViewModel({
			url : 'flowchart/setting'
		});
		
		self.seniorForm = new ko.formViewModel({
			url : 'flowchart/setting'
		});
		
		self.jurisdictionForm = new ko.formViewModel({
			url : 'flowchart/setting'
		});
		
		self.logicForm = new ko.formViewModel({
			url : 'flowchart/setting'
		});
		
		self.basicForm.setForm(new formVo({}));
		
		self.operateForm.setForm(new formVo({}));
		
		self.seniorForm.setForm(new formVo({}));
		
		self.jurisdictionForm.setForm(new formVo({}));
		
		self.logicForm.setForm(new formVo({}));
		
	};  
	 
	return viewModel;
})
define([ "knockout", "./operationVO.js", "components","bootstrap" ], function(ko,
		OperationMode) {  
	
	function viewModel() {
		var self = this;
		
		self.visibleCatalog = ko.observable(true);
	 
		//查询框使用
		self.searchForm = new ko.searchViewModel(new OperationMode({}),{
			callback: {
				search: function(searhForm){
					self.grid.findAll(searhForm)
				}
			}
		});
		  
		self.toAdd = function(){
			self.operateForm.setForm(new OperationMode({}));
			$('#myModal').modal();
			$('#typeRadioOne').prop('checked',true);
			$("#isDefault").prop('checked',true);
			var newFormItem = self.operateForm.formItem();
			newFormItem.fdNodeType="0";
			newFormItem.fdIsDefault="1";
			self.operateForm.setForm(newFormItem);
			
		};
		
		self.toEdit = function(om) {		
			var form = new OperationMode(om);
			// 设置操作类型form
			var operObject = function(a,b,c){
					var obj = {fdId: a||'', fdOperType: ko.observable(b||''), fdOperName: ko.observable(c||'')};
					obj.fdOperType.subscribe(function(val){
						switch(val){
						case '101':
							obj.fdOperName('通过');
							break;
						case '102':
							obj.fdOperName('驳回');
							break;
						case '103':
							obj.fdOperName('转办');
							break;
						case '104':
							obj.fdOperName('沟通');
							break;
						case '105':
							obj.fdOperName('废弃');
							break;
						case '202':
							obj.fdOperName('催办');
							break;
						case '203':
							obj.fdOperName('撤回');
							break;
						case '204':
							obj.fdOperName('废弃');
							break;
						}
					});
					return obj;
				};
			form.operationsList([])
			for(i=0;i<om.operationsList.length;i++){
				form.operationsList.push(operObject(om.operationsList[i].fdId,om.operationsList[i].fdOperType,om.operationsList[i].fdOperName))
			}
			form.operationsListTwo([])
			for(i=0;i<om.operationsListTwo.length;i++){
				form.operationsListTwo.push(operObject(om.operationsListTwo[i].fdId,om.operationsListTwo[i].fdOperType,om.operationsListTwo[i].fdOperName))
			}
			self.operateForm.setForm(form); 
			
			$('#myModal').modal(); 
			var val = $('input:radio[name="fdNodeType"]:checked').val();
			if(val==1){
				$('#tbOperateTwo').hide();
			}
		}
		
		self.grid = new ko.gridViewModel({
			url : 'sys/operationMode',
			callback : {
				itemEdit: self.toEdit
			}
		});
		
		self.onSaveSuccess = function(){
			self.grid.refresh();
			$("#myModal").modal("hide");
		} 
		
		self.operateForm = new ko.formViewModel({
			url : 'sys/operationMode',
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
				  saveSuccess:self.onSaveSuccess
			}
		});
	 
	};  
	 
	return viewModel;
}); 
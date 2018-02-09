define([ "knockout",'AjaxUtil',"./variableSetVO.js", "components","bootstrap" ], function(ko,AjaxUtil,
		VariableSet) {   
	
	function viewModel() {
		var self = this;
		
		self.visibleCatalog = ko.observable(true);
	
		//查询框使用
		self.searchForm = new ko.searchViewModel(new VariableSet({}),{
			callback: {
				search: function(searhForm){
					self.grid.findAll(searhForm)
				}
			}
		});
		  
		self.toAdd = function(){
			self.varform.setForm(new VariableSet({}));
			$('#myModal').modal(); 
			
			$('#isJdbcYes').prop('checked',true); 
			$('#statusYes').prop('checked',true); 
			var newFormItem = self.varform.formItem();
			newFormItem.varIsJdbc="1";
			newFormItem.varStatus="1";
			self.varform.setForm(newFormItem);
			
			$('#detailOne').hide();
			$('#detailTwo').hide();
		};
		
		self.toEdit = function(vs) {		
			self.varform.setForm(new VariableSet(vs)); 
			$('#myModal').modal(); 
			var val = $('input:radio[name="varIsJdbc"]:checked').val();
			if(val==1){
			   $('#showOne').show();$('#showTwo').show();
			   $('#detailOne').hide();$('#detailTwo').hide();
			}else{
				$('#detailOne').show();$('#detailTwo').show();
				$('#showOne').hide();$('#showTwo').hide();
			}
		}
		
		self.grid = new ko.gridViewModel({
			url : 'sys/variableSetting',
			callback : {
				itemEdit: self.toEdit
			}
		});
		
		self.onSaveSuccess = function(){
			self.grid.refresh();
			$("#myModal").modal("hide");
		}
		
		self.varform = new ko.formViewModel({
			url : 'sys/variableSetting',
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
		

	    // 获取参数分类start--------------------
		var variable = function(name, id) {
			this.name = name;
			this.id = id;
		};

		self.varTypeArray = ko.observableArray([{name:"--请选择--",id:""},{name:"通用",id:"0"}]);
		
		self.getIdAndName = function() {
			// 暂不需要模板匪类，先屏蔽
//			 AjaxUtil.call({
//				type : "POST",
//				url : 'sys/variableSetting/findSysCategoryMain',
//				success : function(data) {
//					for(var i=0;i<data.length;i++)
//					{
//     				   self.varTypeArray.push(new variable(data[i].fdName,data[i].fdId)); 
//					}  
//				}
//			}); 
			
		}();
		// 获取参数分类end--------------------
	} 

return viewModel;
})
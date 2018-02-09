define([ "knockout",'AjaxUtil',"./simulateVO.js", "components","bootstrap" ], function(ko,AjaxUtil,
		Simulate) { 
	
	function viewModel() {
		var self = this;
		
		// 可维护者
		self.elementsArray = ko.observableArray([]);
		 	
		self.simulateForm = new ko.formViewModel({
			url : 'sys/simulate'
		}); 
		self.simulateForm.setForm(new Simulate());
		
		self.elementForm = ko.pureComputed(function() {
			return {
				elementArray : ko.toJS(self.elementsArray)
			};
		}, this);
		self.simulate = function(flag){
			self.simulateForm.formItem().flag=flag;
			AjaxUtil.call({
				url: "sys/simulate/simulate",   
				type : "post",
				data :{
					'context':JSON.stringify(self.simulateForm.formItem())
					
				},
				dataType : "JSON",
				success : function(data) {     
					
				}
			}); 
		};
		
		// 传给组件的form
		
		
		// 组件回调函数
		self.selectNewElement = function(elements,memberNames){
			
			$('#handlerIds').val(elements);
			$('#handlerNames').val(memberNames);
			var arr = ko.toJS(elements)
			var handlerIds="";
			for(var i=0;i<arr.length;i++){
				if(i<arr.length-1){
					handlerIds+=arr[i].fdNo+";";
				}else{
					handlerIds+=arr[i].fdNo
				}
				
			}
			self.simulateForm.formItem().handlerNames=memberNames;
			self.simulateForm.formItem().handlerIds=handlerIds;
//			self.simulateForm.formItem().handlerArray=ko.toJS(elements);
//			self.simulateForm.formItem().handlerNames=memberNames;
//			$('#editor').val(memberNames);
//			self.basicSetForm.formItem().editorArray=ko.toJS(elements);
//			self.editorArray(ko.toJS(elements));  
		}
		
	};  
	 
	return viewModel;
})
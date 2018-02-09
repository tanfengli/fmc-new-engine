define([ "knockout",'AjaxUtil',"./basicSetVO.js", "components","bootstrap" ], function(ko,AjaxUtil,
		BasicSet) { 
	
	function viewModel() {
		var self = this;
		
		// 可维护者
		self.editorArray = ko.observableArray([]);
		 	
		self.basicSetForm = new ko.formViewModel({
			url : 'sys/basicSet'
		}); 
		
		AjaxUtil.call({
			url: "sys/basicSet/findForm",   
			type : "post",
			dataType : "JSON",
			success : function(data) {     
				if(data.notifyTargetAuthor=="false"){
					data.notifyTargetAuthor = false;
				}else{
					data.notifyTargetAuthor = true;
				}
				
				if(data.notifyTargetSubmit=="false"){
					data.notifyTargetSubmit = false;
				}else{
					data.notifyTargetSubmit = true;
				}
				
				self.basicSetForm.setForm(new BasicSet(ko.toJS(data)));
			}
		}); 
		

		// 传给组件的form
		self.elementForm = ko.pureComputed(function() {
			return {
				elementArray : ko.toJS(self.editorArray)
			};
		}, this);
		
		// 组件回调函数
		self.selectNewElement = function(elements,memberNames){

			$('#editor').val(memberNames);
			self.basicSetForm.formItem().editorArray=ko.toJS(elements);
			self.editorArray(ko.toJS(elements));  
		}
		
		self.clearEditor=function(){ 
			$('#editor').val(''); 
			$('#ids').val('');
			self.basicSetForm.formItem().notifyCrashTargetIds="";
			self.basicSetForm.formItem().notifyCrashTargetNames=""; 
		}
		
	};  
	 
	return viewModel;
})
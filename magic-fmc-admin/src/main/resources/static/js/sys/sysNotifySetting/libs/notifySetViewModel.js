define([ "knockout",'AjaxUtil',"./notifySetVO.js", "components","bootstrap" ], function(ko,AjaxUtil,
		NotifySet) { 
	
	function viewModel() {
		var self = this;
		
		// 可维护者
		self.editorArray = ko.observableArray([]);
		
		self.creEditorArray = ko.observableArray([]);
		
		self.delEditorArray = ko.observableArray([]);
		
			
		self.form = new ko.formViewModel({
			url : 'sys/notifySet'
		}); 
		
		AjaxUtil.call({
			url: "sys/notifySet/findForm",   
			type : "post",
			dataType : "JSON", 
			success : function(data) {
		 
				self.form.setForm(new NotifySet(ko.toJS(data)));
				 
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
			
			var memberIds = "";
			var memberNames = ""; 
			if(null!= elements){ 
				for (var i=0;i< ko.toJS(elements).length;i++){
					memberIds = memberIds+ ko.toJS(elements)[i].fdId+';';
					memberNames = memberNames+ ko.toJS(elements)[i].fdName+';';
				}
			} 
			
			if(ko.toJS(self.form.formItem()).selectType=='orgEditor')
			{    
				$('#editor').val(memberNames); 
				$("#orgPerId").val(memberIds);
				self.form.formItem().orgPerName=memberNames;   
				self.form.formItem().orgPerId = memberIds;
				
			}else if(ko.toJS(self.form.formItem()).selectType=='creEditor'){   
				$('#creEditor').val(memberNames); 
				$("#crePerId").val(memberIds);
				self.form.formItem().crePerName=memberNames;   
				self.form.formItem().crePerId=memberIds;
				
			}else{ 
				$('#delEditor').val(memberNames); 
				$("#delPerId").val(memberIds);
				self.form.formItem().delPerName=memberNames;   
				self.form.formItem().delPerId=memberIds;
			}   
		}
		
	};  
	 
	return viewModel;
})
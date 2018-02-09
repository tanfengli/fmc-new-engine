
define(["knockout","AjaxUtil","./interfaceVO.js","components","bootstrap"],function(ko,AjaxUtil,Interface){  
	function viewModel(){
		var self=this ;
	
		self.interfaceArray=ko.observableArray([]);
		
		self.findInterfaces=function(){
			AjaxUtil.call({
				url:"sys/sysBusiSys/grantInterface/findAllElements",   
				type:"post",
				dataType:"JSON",
				async:false,
				success:function(data){
					self.interfaceArray(data);
				}
			}); 
		}

		self.searchForm=new ko.searchViewModel(new Interface({}),{
			callback:{
				search:function(searhForm){
					self.gridModel.findAll(searhForm)
				}
			}
		});
		
		self.interfaceForm=new ko.formViewModel({
			url:'anonymous/interface',
			callback:{
				saveSuccess:function(){
					self.gridModel.refresh();
					
					$("#interfaceModal").modal("hide");
				}
			}
		});
		
		self.toAdd=function(){ 
			self.interfaceForm.setForm(new Interface());
			self.findInterfaces();
			
			$('#interfaceModal').modal();
		}; 
		
		self.toEdit=function(vs){	
			self.interfaceForm.setForm(new Interface(vs)); 
			self.findInterfaces();
			
			$('#interfaceModal').modal(); 
		}
		
		self.gridModel=new ko.gridViewModel({
			url:'anonymous/interface',
			size:10,
			callback:{ 
				itemEdit:self.toEdit
			}
		});
	};
	
	return viewModel;
})
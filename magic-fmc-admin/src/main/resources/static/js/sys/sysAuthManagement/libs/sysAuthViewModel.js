define(["knockout",'AjaxUtil',"./sysAuthVO.js","components","bootstrap"],function(ko,AjaxUtil,SysAuthModel){  
	function viewModel(){
		var self=this;
		
		self.visibleCatalog=ko.observable(true);

		//查询框使用
		self.searchForm=new ko.searchViewModel(new SysAuthModel({}),{
			callback:{
				search:function(searhForm){
					self.grid.findAll(searhForm)
				}
			}
		});
 
		self.toAdd=function(){
			self.authForm.setForm(new SysAuthModel({}));
			$('#myModal').modal(); 
			
			$('#fdAuthorizeTypeOne').prop('checked',true);
			var newFormItem = self.authForm.formItem();
			newFormItem.fdAuthorizeType="0";
			self.authForm.setForm(newFormItem);
			$('#judgeShow').show();
		};
		
		self.toEdit=function(vs) { 
			self.authForm.setForm(new SysAuthModel(vs)); 
			$('#myModal').modal(); 
			
			var newFormItem=self.authForm.formItem(); 
			//授权项查询
			AjaxUtil.call({
				url:"sys/sysAuth/findItemOption",   
				type:"post",
				data:{fdId: vs.fdAuthorizer},
				dataType:"JSON",
				success:function(data) {     
					newFormItem.fdAuthorizeContext(data);
				}
			}); 
			self.authForm.setForm(newFormItem); 
			
			var val=$('input:radio[name="fdAuthorizeType"]:checked').val(); 
			if(val==1){
				$('#judgeShow').hide();
			}
		}
		
		self.grid=new ko.gridViewModel({
			url:'sys/sysAuth',
			callback:{
				itemEdit:self.toEdit
			}
		});
		
		self.onSaveSuccess=function(){
			self.grid.refresh();
			$("#myModal").modal("hide");
		}
		
		self.authForm=new ko.formViewModel({
			url:'sys/sysAuth',
			callback:{
				  afterSetForm:function(viewModel,formItem){
					  //日期选择器配置
					  $(".form_datetime").datetimepicker({
							language:'zh-CN',
					        format:'yyyy-mm-dd',
					        weekStart:1,
					        todayBtn:1,
							autoclose:1,
							todayHighlight:1,
							startView:2,
							minView:"month",
							forceParse:0
					  });
				  },
				  saveSuccess:self.onSaveSuccess
			}
		});
		
		self.selectTypeFunc=function(data){
			if(ko.toJS(self.authForm.formItem()).selectType=='fdAuthorizer'){  
				self.selectNewOrg(data);
			}else{
				self.selectSecond(data);
			} 
			
			//授权类型控制
			var type=ko.toJS(self.authForm.formItem()).fdAuthorizeType;
			if(type==1){
				$('#judgeShow').hide();
			}else{
				$('#fdAuthorizeTypeOne').prop('checked',true);
				$('#judgeShow').show();
			}
		}
			
		self.selectSecond=function(data){
			var newFormItem=self.authForm.formItem();
			newFormItem.fdAuthorizedPerson=data.fdId;
			newFormItem.fdAuthorizerPersonName=data.fdName;  
			self.authForm.setForm(newFormItem);
			
		}
		
		self.selectNewOrg=function(org){
			var newFormItem=self.authForm.formItem();
			newFormItem.fdAuthorizer=org.fdId;
			newFormItem.fdAuthorizerName=org.fdName; 

			//授权项查询
			AjaxUtil.call({
				url:"sys/sysAuth/findItemOption",   
				type:"post",
				data:{fdId: org.fdId},
				dataType:"JSON",
				success:function(data) {     
					newFormItem.fdAuthorizeContext(data);
					newFormItem.fdAuthorizedChe([]);
				}
			}); 
			
			self.authForm.setForm(newFormItem);
		 };
		 
		//授权范围模板form
		self.templateForm=ko.observable();
		//打开授权范围选择
		 self.selectTemplate=function(){
			 var templateObj=self.authForm.formItem().selectTemplate();
			 self.templateForm(templateObj);
			 $('#templateSelWindow').modal();
		 };
		 
		//选择授权范围回调
		self.selectNewCate=function(cate,strName,strId){  
			self.authForm.formItem().selectNewCate(cate,strName,strId);
		}
	};  

	return viewModel;
})
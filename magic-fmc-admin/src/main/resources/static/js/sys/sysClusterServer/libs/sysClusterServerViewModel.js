define(["knockout",'AjaxUtil',"./sysClusterServerVO.js","components","bootstrap" ],function(ko,AjaxUtil,SysClusterServer) {   
	function viewModel(){
		var self=this;
		self.visibleCatalog=ko.observable(true);
		self.servers=ko.observableArray([]);
		self.dispatcher=ko.observableArray([]);
		
		// 加载服务信息
		self.loadServer=function(){
			AjaxUtil.call({
				url:"sys/sysClusterServer/loadServer",
				type:"post",
				async:false,
				success:function(data){
					self.servers(data.server);
					self.dispatcher(data.dispatcher);
				}
			});
		}
		
		// 查询框使用
		self.searchForm=new ko.searchViewModel(new SysClusterServer({}),{
			callback:{
				search:function(searhForm){
					self.grid.findAll(searhForm)
				}
			}
		});
		
		// 添加
		self.toAdd=function(){ 
			self.sysform.setForm(new SysClusterServer({sysOperatorType:"1"}));
			
			$('#myModal').modal();
		}; 
		
		// 編輯
		self.toEdit=function(vs){	
			var sysClusterServer=new SysClusterServer(vs);
			sysClusterServer.sysOperatorType("1");
			self.sysform.setForm(sysClusterServer); 
			
			$('#myModal').modal(); 
		}
		
		// 配置
		self.toConfig=function(){ 
			self.loadServer();
			var form = new SysClusterServer({sysOperatorType:"1"});
			for(i=0;i<self.dispatcher().length;i++){
				var dispatcher = self.dispatcher()[i];
				if(dispatcher.fdDispatcherKey=="sysWfEventService")
					form.sysWfEventService=dispatcher.fdServerKey;
				if(dispatcher.fdDispatcherKey=="sysQuartzDispatcher")
					form.sysQuartzDispatcher=dispatcher.fdServerKey;
			}
			self.sysConfigform.setForm(form);
			
			$('#myModalConfig').modal();
		}
		
		// 数据列表
		self.grid=new ko.gridViewModel({
			url:'/sys/sysClusterServer',
			callback:{
				itemEdit:self.toEdit
			}
		});
		
		// 保存信息
		self.onSaveSuccess=function(){
			self.grid.refresh();
			
			$("#myModal").modal("hide");
			$("#myModalConfig").modal("hide");
		}
		
		//校验
		self.validate = function() {
			var formItem = self.sysform.formItem();
			formItem.isValid = ko.validation.group(formItem);
			//显示没通过消息
			formItem.isValid.showAllMessages();
			if (formItem.isValid().length!=0) {
				return -1;
			}
		}
		
		// 编辑表单
		self.sysform=new ko.formViewModel({
			url:'/sys/sysClusterServer',
			callback:{
				saveSuccess:self.onSaveSuccess,
				beforeSave : self.validate
			}
		}); 
		
		// 设置表单
		self.sysConfigform=new ko.formViewModel({
			url:'/sys/sysClusterServer',
			callback:{
				saveSuccess:function(){
					$('#btn_submit').attr('disabled','false');
					self.onSaveSuccess();
				}
			}
		}); 
	};  

	return viewModel;
})
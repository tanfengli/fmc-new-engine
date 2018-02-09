define([ "knockout","./sysWfExceptionLogVO.js" , "components","bootstrap" ], function(ko,SysWfExceptionLog
		) {   
	function viewModel(){
		//流程详情组件注册
		if (!ko.components.isRegistered("billDetail")){
			ko.components.register('billDetail',{
			    viewModel:{
			       require:'../blocks/bill/billDetail/billDetail.js'
			    },
			    template:{
			    	 require:'text!blocks/bill/billDetail/billDetail.html'
			    }
			});
		}
		
		self.instanceId=ko.observable();
		// 页面显示
		self.chooseView=ko.observable('list');
		self.toList=function(){
			self.chooseView('list');
		};
		
		// 查询form
		self.searchForm=new ko.searchViewModel(new SysWfExceptionLog({}),{
			callback:{
				search:function(searhForm){
					self.exceptionLogGrid.findAll(searhForm)
				}
			}
		});
		
		// 表格
		self.exceptionLogGrid = new ko.gridViewModel({
			url : 'sys/wfExceptionLog'
		});
		
		// 错误原因窗口打开
		self.toReasonDetail = function(log){
			$('#exceptionReason').val(ko.toJS(log.fdReason))
			$('#exceptionMes').val(ko.toJS(log.fdMessage))
			$('#exceptionCode').val(ko.toJS(log.fdException))
			$('#reasonWindow').modal();
		}
		
		// 查看流程详情
		self.toDetail=function(log){
			self.instanceId(ko.toJS(log.fdProcessId));
			self.chooseView('detail');
		}
		
	}
	return viewModel;
})
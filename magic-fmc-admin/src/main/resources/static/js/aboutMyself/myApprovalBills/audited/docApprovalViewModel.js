define(["knockout","./vAuditBillVO.js","components","bootstrap"],function(ko,SysNewsMain){  
	function viewModel(){
		if(!ko.components.isRegistered("billDetail")){
			ko.components.register('billDetail',{
			    viewModel:{
			    	require:'../blocks/bill/billDetail/billDetail.js'
			    },
			    template:{
			    	require:'text!blocks/bill/billDetail/billDetail.html'
			    }
			});
		}
		
		//模型别名
		var self=this;
		
		//单据详细信息
		self.wfInstanceId=ko.observable();
		
		//主页面内容切换
		self.chooseView=ko.observable('list');
		
		//显示单据列表主页面
		self.toList=function(){
			self.chooseView('list');
		};
		
		//查看详情
		self.toDetail=function(doc){
			self.wfInstanceId(doc.fdId);
		}
		
		//查看详情回调
		self.toDetailPage=function(){
			self.chooseView('detail');
		};
		
		//单据过滤模型
		self.searchForm=new ko.searchViewModel(new SysNewsMain({}),{
			callback:{
				search:function(searhForm){
					self.docSubmitId.findAll(searhForm)
				}
			}
		});
		
		//单据列表模型
		self.docSubmitId=new ko.gridViewModel({
			url:'aboutMyself/myApprovalBills/audited',
			size:10,
			callback:{
				itemEdit:self.toDetail
			}
		});
	};
	
	return viewModel;
})
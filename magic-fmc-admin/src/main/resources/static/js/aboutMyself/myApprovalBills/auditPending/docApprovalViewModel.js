define(["knockout","./vCmsTaskVO.js","MsgBox","components","bootstrap"],function(ko,VCmsTask,MsgBox){  
	
	function viewModel(){
		if(!ko.components.isRegistered("billApprove")){
			ko.components.register('billApprove',{
			    viewModel:{
			       require:'../blocks/bill/billApprove/billApprove.js'
			    },
			    template:{
			    	 require:'text!blocks/bill/billApprove/billApprove.html'
			    }
			});
		}
		
		if(!ko.components.isRegistered("billEditor")){
			ko.components.register('billEditor',{
			    viewModel:{
			       require:'../blocks/bill/billEditor/billEditor.js'
			    },
			    template:{
			    	 require:'text!blocks/bill/billEditor/billEditor.html'
			    }
			});
		}
		
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
		
		//模型别名
		var self=this;
		
		//流程实例
		self.wfInstanceId=ko.observable();
		//单据编辑信息
		self.billEditor=ko.observable();
		//单据审批信息
		self.billApprove=ko.observable();
		
		// 主页面默认内容
		self.chooseView=ko.observable('list');
		
		//查看列表
		self.toList=function(){
			self.chooseView('list');
			self.searchForm.search();
		};
		
		//刷新列表
		self.toRefresh=function(){
			self.toList();
		}
		
		//查看详情
		self.toDetail=function(doc){
			self.wfInstanceId(ko.toJS(doc.wfInstanceId));
		}
		//查看详情回调
		self.toDetailPage=function(){
			self.chooseView('detail');
		}
		
		//编辑页面
		self.toEdit=function(doc){
			self.billEditor({fdId:doc.wfInstanceId,type:"1"});
		}
		//编辑页面回调
		self.toEditPage=function(){
			self.chooseView('editor');
		}
		
		//审批页面
		self.toApprove=function(doc){
			self.billApprove({instanceId:ko.toJS(doc.wfInstanceId),nodeId:ko.toJS(doc.nodeId)});
		}
		
		//审批页面回调
		self.toApprovePage=function(){
			self.chooseView('approve');
		}
		
		
		//单据查询模型
		self.searchForm=new ko.searchViewModel(new VCmsTask({}),{
			callback:{
				search:function(searhForm){
					self.docSubmitId.findAll(searhForm)
				}
			}
		});
		
		//单据列表模型
		self.docSubmitId=new ko.gridViewModel({
			url:'aboutMyself/myApprovalBills/auditPending',
			size:10,
			callback:{
				itemEdit:self.toEdit
			}
		});
	};
	
	return viewModel;
})
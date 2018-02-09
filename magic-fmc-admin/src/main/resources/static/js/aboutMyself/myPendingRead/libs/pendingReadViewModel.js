define(["knockout","./processReadVVO.js",'AjaxUtil',"MsgBox","components","bootstrap"],function(ko,ProcessReadV,AjaxUtil,MsgBox){   
	function viewModel(){
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
			AjaxUtil.call({
				url:'aboutMyself/myPendingRead/setIsRead',
				type:'get',
				async:false,
				data:{todoId:doc.todoId},
				success:function(data){
					self.searchForm.search();
				}
			})
			
			self.wfInstanceId(doc.wfInstanceId);
		}
		
		//查看详情回调
		self.toDetailPage=function(){
			self.chooseView('detail');
		};
		
		//计算已读/未读消息数
		self.count=function(){
			AjaxUtil.call({
				url:'aboutMyself/myPendingRead/count',
				type:'get',
				success:function(data){
					$('#unreadCount').text(data.unread)
					$('#readCount').text(data.read)
					$('#allCount').text(data.all)
				}
			})
		}
		
		//单据过滤模型
		self.searchForm=new ko.searchViewModel(new ProcessReadV({}),{
			callback:{
				search:function(searhForm){
					self.count();
					self.processReadVGrid.findAll(searhForm)
				}
			}
		});
		
		//单据列表模型
		self.processReadVGrid=new ko.gridViewModel({
			url:'aboutMyself/myPendingRead',
			isAutoRetrieve:'false'
		});
		
		self.searchForm.search();
	}
	
	return viewModel;
})
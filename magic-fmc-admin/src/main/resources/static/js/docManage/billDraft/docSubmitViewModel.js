define(["knockout",'AjaxUtil',
        "../../common/VO/docSubmitVO.js",
        "../../common/VO/sbWfApprovalFormVO.js",
        "../../../blocks/bill/onPropEdit/onPropEdit.js",'Flowchart',
        "../../common/flowchart/billEvent.js","MsgBox","components","bootstrap"],function(ko,AjaxUtil,
		DocSubmit,WfApprovalForm,OnPropEdit,FlowChart,BillEvent,MsgBox){ 
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
		
		//模型别名
		var self=this;
		//单据流转信息
		self.sysWfBusinessForm=ko.observable();
		//主页面内容切换
		self.chooseView=ko.observable('list');
		
		//审批意见
		self.apporveData=ko.observable();
		self.approvalArray=ko.observable([]);
		self.wfOptionCon=ko.observable();
		
		//当前节点
		self.nodes=ko.observable([]);
		//单据状态
		self.status=ko.observable();
		//当前处理人
		self.currentHandlerNames=ko.observable();
		//已经处理人
		self.historyHandlerNames=ko.observable();
		
		//流程实例
		self.wfInstanceId=ko.observable();
		//单据编辑信息
		self.billEditor=ko.observable();
		
		//显示单据列表主页面
		self.toList=function(){
			self.chooseView('list');
			self.searchForm.search();
		};
		
		//查看详情
		self.toDetail=function(doc){
			self.wfInstanceId(doc.fdId);
		}
		
		//切换到单据详情页面
		self.toDetailPage=function(){
			self.chooseView('detail');
		}
		
		//打开模板选择组件
		self.toAdd=function(){
			$("#templateTreeWindow").modal();
		}
	
		//选择模板回调
		self.selectTemplate=function(template){
			self.billEditor({fdId:template.fdId,type:"0"});
		}
		
		//编辑详情
		self.toEdit=function(doc){
			self.billEditor({fdId:doc.fdId,type:"1"});
		}
		
		//切换到单据起草页面
		self.toEditPage=function(){
			self.chooseView('editor');
		}
		
		//刷新列表
		self.toRefresh=function(){
			self.toList();
		}
		
		//初始化起草人操作页面
		self.toApprove=function(doc){
			//获取单据业务信息
			AjaxUtil.call({
				url:"aboutMyself/myBills/getDetail",
				data:{wfInstanceId:doc.fdId},
				type:"post",
				async:false,
				success:function(data){
					//当前节点信息
					self.nodes(data.nodeList);
					//单据状态
					self.status(doc.docStatus);
					//当前处理人
					self.currentHandlerNames=data.currentHandlerNames;
					//已经处理人
					self.historyHandlerNames=data.historyHandlerNames;
					//刷新审批意见
					self.approvalFresh();
				}
			});
			
			//获取单据审批信息
			AjaxUtil.call({
				url:"docManage/billProcess/billApprove/findForm",
				data:{wfInstanceId:doc.fdId,wfNodeId:self.nodes()[0].fdFactNodeId,identify:"drafter"},
				type:"post",
				async:false,
				success:function(data){
					self.editForm.setForm(new WfApprovalForm(data));
				}
			});
		}
		
		//获取审批意见
		self.approvalFresh=function(){
			AjaxUtil.call({
				url:'sys/defLanguage/noPageFind',
				type:'post',
				success:function(data){
					self.apporveData(data);
					self.approvalArray(data.pass);
				}
			});
		};
	
		//文本框意见更新
		self.wfOptionCon.subscribe(function(val){
			if(self.editForm.formItem()!=undefined){
				self.editForm.formItem().wfOptionCon(val);
			}
		})
		
		//操作选中触发默认意见
		self.suggestRefresh=function(val){
			self.approvalArray(val);
			var defSuggestion="";
			if(val==undefined){
				self.approvalArray([]);
				return;
			}
			
			for(i=0;i<val.length;i++){
				if(val[i].fdIsSysSetup=='1'){
					defSuggestion=val[i].fdUsageContent;
					break;
				}
			}
			
			self.editForm.formItem().wfOptionCon(defSuggestion);
		}
		
		//操作选中触发默认意见
		self.oprClick=function(val){
			//所有审批意见
			var apporveData=self.apporveData();
			//初始化默认审批意见
			self.approvalArray([]);
			//审批操作
			var opr=self.editForm.formItem().wfResult();
			switch(opr){
				case('101')://通过
					self.suggestRefresh(apporveData.pass);
					break;
				case('102')://驳回
					self.suggestRefresh(apporveData.reject);
					break;
				case('103')://转办
					self.suggestRefresh(apporveData.tranfor);
					break;
				case('104')://沟通
					self.suggestRefresh(apporveData.communicate);
					break;
				case('204')://废弃
					self.suggestRefresh(apporveData.abandon);
					break;
			}
			
			return true;
		}
	
		//起草人单据查询模型
		self.searchForm=new ko.searchViewModel(new DocSubmit({}),{
			callback:{
				search:function(searhForm){
					self.docSubmitId.findAll(searhForm)
				}
			}
		}); 
		
		//起草人单据列表模型
		self.docSubmitId=new ko.gridViewModel({
			url:'docManage/billDraft',
			size:10,
			callback:{
				itemEdit:self.toEdit,
				itemSet:self.toDetail
			}
		});
		
		//起草人处理单据模型
		self.editForm=new ko.formViewModel({
			url:'/docManage/billProcess/billApprove',
			callback:{
				beforeSave:function(){
					var editForm=self.editForm.formItem();
					editForm.isValid=ko.validation.group(editForm);
					//显示没通过消息
					editForm.isValid.showAllMessages();
					if(editForm.isValid().length!=0){
						return -1;
					}
					
					//即将流向为人工决策节点时,需要选择下一环节路径
					if(editForm.wfResult()==null||editForm.wfResult()==""||editForm.wfResult()=="null"){
						MsgBox.warning('请选择操作类型.');
						
						return -1;
					}
				},
				saveSuccess:self.toRefresh
			}
		});
	}
	
	return viewModel;
})
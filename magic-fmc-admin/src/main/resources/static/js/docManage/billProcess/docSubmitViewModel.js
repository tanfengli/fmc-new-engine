define(["knockout",'AjaxUtil',
    "../../../js/common/VO/docSubmitVO.js",
    "../../../js/common/VO/sysWfBusinessFormVO.js",
    "../../../js/common/VO/sbWfApprovalFormVO.js",
    "../../../js/common/flowchart/billEvent.js",
    "MsgBox","components","bootstrap"],function(ko,AjaxUtil,DocSubmit,
	SysWfBusinessForm,WfApprovalForm,BillEvent,MsgBox){  
	
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
		
		if(!ko.components.isRegistered("addressBook")){
			ko.components.register('addressBook',{
			    viewModel:{
			       require:'../blocks/setting/addressBook/addressBook.js'
			    },
			    template:{
			    	 require:'text!blocks/setting/addressBook/addressBook.html'
			    }
			});
		}
		
		if(!ko.components.isRegistered("searchTemplateTree")){
			ko.components.register('searchTemplateTree',{
			    viewModel:{
			       require:'../blocks/processInstance/searchTemplateTree/searchTemplateTree.js'
			    },
			    template:{
			    	 require:'text!blocks/processInstance/searchTemplateTree/searchTemplateTree.html'
			    }
			});
		}
	
		//模型别名
		var self=this;
		
		//单据实例信息
		self.wfInstanceId=ko.observable();
		//单据业务信息
		self.sysWfBusinessForm=ko.observable(new SysWfBusinessForm());
		
		//控制并行分支
		self.isInBranch=ko.observable(false);
		//监控并行分支节点信息
		self.wfAffairId=ko.observable(undefined);
		
		//审批意见
		self.apporveData=ko.observable();
		self.approvalArray=ko.observableArray([]);
		self.wfOptionCon=ko.observable();
		
		//重新定位节点
		self.flowNodeList=ko.observableArray([]);
		
		//修改当前处理人
		self.elementArray=ko.observableArray([]);
		self.pattern=ko.observable('');
		
		//回收子流程
		self.showSubprocess=ko.observable(false);
		self.subProcesses=ko.observableArray([]);
		self.checkedData=ko.observableArray();
		
		//修改流程图
		self.editFlowchart={
			'flowchart':{}
		}
		self.flowXml=ko.observable();
		self.flowNodeList=ko.observableArray([]);
		self.runningNodes=ko.observableArray([]);
		self.historyNodes=ko.observableArray([]);
		
		//主页面内容切换
		self.chooseView=ko.observable('list');
		
		//显示单据列表主页面
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
			self.wfInstanceId(doc.fdId);
		}
		
		//查看详情回调
		self.toDetailPage=function(){
			self.chooseView('detail');
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
			var defSuggestion="";
			if(val==undefined){
				self.approvalArray([]);
				return;
			}
			
			self.approvalArray(val);
			
			for(i=0;i<val.length;i++){
				if(val[i].fdIsSysSetup=='1'){
					defSuggestion=val[i].fdUsageContent;
					break;
				}
			}
	
			self.wfOptionCon(defSuggestion);
		}
		
		//监控并行分支节点信息
		self.wfAffairId.subscribe(function(val){
			if(self.editForm.formItem()!=undefined){
				if(val!=undefined){
					self.editForm.formItem().fdHandleNodeId(val.fdFactNodeId);
					self.editForm.formItem().toOtherHandlerIds(val.fdHandlerIds);
					self.editForm.formItem().toOtherHandlerNames(val.fdHandlerNames);
					self.editForm.formItem().toOldHandlerNames(val.fdHandlerNames);
					self.showSubprocess(self.billEvent.isSubProcess(self.editForm.formItem().fdHandleNodeId()));
					
					if(self.apporveData()!=undefined){
						self.suggestRefresh(self.apporveData().reject);
					}
					self.adminJump();
				}else{
					self.editForm.formItem().fdHandleNodeId(undefined);
					self.editForm.formItem().toOtherHandlerNames(self.sysWfBusinessForm().currentHandlerNames);
				}
			}
		});
		
		//以特权人身份操作
		self.initApproveForm=function(doc){
			//获取单据业务信息
			AjaxUtil.call({
				url:"aboutMyself/myBills/getDetail",
				data:{wfInstanceId:doc.fdId},
				type:"post",
				async:false,
				success:function(data){
					//工具
					self.billEvent=new BillEvent(data);
					//获取审批意见
					self.approvalFresh();
					//设置业务数据
					self.sysWfBusinessForm(data);
					//判断当前节点是否在分支内
					if(data.nodeList.length>1){
						self.isInBranch(true);
					}else{
						self.isInBranch(false);
					}
					
					//当前处理人
					self.elementArray([]);
				}
			});
			
			//获取单据审批信息
			var wfNodeId="";
			if(self.sysWfBusinessForm().nodeList.length>1){
				wfNodeId=self.sysWfBusinessForm().nodeList[0].fdFactNodeId;
			}
			
			AjaxUtil.call({
				url:"docManage/billProcess/billApprove/findForm",
				data:{wfInstanceId:self.sysWfBusinessForm().basic.fdId,wfNodeId:wfNodeId,identify:'authority'},
				type:"post",
				async:false,
				success:function(data){
					self.editForm.setForm(new WfApprovalForm(data));
					self.wfAffairId(self.sysWfBusinessForm().nodeList[0]);
					self.editForm.formItem().wfResult("");
					self.editForm.formItem().flowChartXml(self.sysWfBusinessForm().fdFlowContent);
				}
			});
			
			AjaxUtil.call({
				url:"sys/orgElement/findByIds",
				data:{ids:self.editForm.formItem().toOtherHandlerIds()},
				type:"get",
				async:false,
				success:function(data){
					self.elementArray(data);
				}
			});
			
			$('#proc-tqr').modal();
		}
		
		//特权人操作模态框关闭
		self.closePrivigeModal=$('#proc-tqr').on('hidden.bs.modal',function(){
			self.isInBranch(false);
		    self.affairId(undefined);
		})
		
		//重新定位节点过滤
		self.adminJump=function(){
			if(self.editForm.formItem().fdHandleNodeId()==undefined){
				self.flowNodeList(self.billEvent.WorkFlow_AdminOperationTypeJump("N2"));
			}else{
				self.flowNodeList(self.billEvent.WorkFlow_AdminOperationTypeJump(self.editForm.formItem().fdHandleNodeId()));
			}
		}
		
		//操作选中触发事件
		self.oprClick=function(val){
			//所有审批意见
			var apporveData=self.apporveData();
			//初始化默认审批意见
			self.approvalArray([]);
			//审批操作
			var opr=self.editForm.formItem().wfResult();
			
			switch(opr){
				case('301'): //通过
					self.suggestRefresh(apporveData.pass);
					break;
				case('302'): //重新定位
					self.suggestRefresh(apporveData.reject);
					self.adminJump();
					break;
				case('304'): //转办
					self.suggestRefresh(apporveData.tranfor);
					break;
				case('303'): //废弃
					self.suggestRefresh(apporveData.abandon);
					break;
			}
			
			return true;
		}
		
		//修改流程图
		self.flowchartEdit=function(){
			self.flowXml(self.sysWfBusinessForm().fdFlowContent);
			self.runningNodes(self.sysWfBusinessForm().nodeList);
    		self.historyNodes(self.sysWfBusinessForm().historyList);
    		
			$('#flowchart_edit_modal').modal('show');
		}
		
		//修改流程图回调
		self.flowChartSubmit=function(){
			if(self.editFlowchart.chart.checkFlow()){
				var flowXml=self.editFlowchart.chart.getFlowchartXml();
				self.editForm.formItem().flowChartXml=flowXml;
				$('#flowchart_edit_modal').modal('hide');
			}
		}
		
		//修改当前处理人
		self.handlerClick=function(){
			self.pattern('privileger');
			$('#addressBookSelectWindow').modal('show');
		}
		
		//修改当前处理人参数
		self.elementForm=ko.pureComputed(function(){
			return{
				elementArray:ko.toJS(self.elementArray),
				pattern:ko.toJS(self.pattern)
			};
		},this);
		
		//修改当前处理人回调
		self.selectElement=function(elements,elmentNames,elementIds){
			self.editForm.formItem().toOtherHandlerIds(elementIds);
			self.editForm.formItem().toOtherHandlerNames(elmentNames);
			
			self.elementArray(elements);
		}
		
		//回收子流程
		self.subProcessEdit=function(){
			AjaxUtil.call({
				url:"docManage/billProcess/getSubProcess",
				data:{wfInstanceId:self.editForm.formItem().wfInstanceId},
				type:"post",
				async:false,
				success:function(data){
					self.subProcesses(data);
				}
			});
			
			$('#subProcessWindow').modal('show');
		}
		
		//复选框全选
		self.checkboxSelect=function(){
			var objs=$("input[name='subProcessId']")
			for(var index=0;index<objs.length;index++){
				var obj=objs[index];
				obj.checked=!obj.checked
			}
			
			return true;
		}
		
		//回收子流程回调
		self.subProcessSubmit=function(){
			if(self.checkedData().length<1){
				MsgBox.warning('请至少选中一个子流程.');
			}else{
				var recoverProcessIds="";
				var recoverProcessNames="";
				for(var index=0;index<self.checkedData().length;index++){
					var obj=self.checkedData()[index];
					recoverProcessIds=";"+obj.fdId;
					recoverProcessNames=";"+obj.fdParentNodeid
				}
				
				self.editForm.formItem().recoverProcessIds(recoverProcessIds.substring(1));
				self.editForm.formItem().recoverProcessNames(recoverProcessNames.substring(1));
			}
			
			$('#subProcessWindow').modal('hide');
		}
		
		//单据过滤模型
		ko.searchViewModel.prototype={
			//模板选择回调
			selectTemplate:function(template){
				self.searchForm.formItem().fdTemplateId=template.fdId;
				self.searchForm.formItem().fdTemplateName(template.fdName);
			},
			//清除记录
			clearTemplate:function(){
				self.searchForm.formItem().fdTemplateId=null;
				self.searchForm.formItem().fdTemplateName("");
			},
			//清除所有
			clear:function(){
				self.searchForm.formItem().docSubject("");
				self.searchForm.formItem().dealName("");
				self.searchForm.formItem().applyCode("");
				self.searchForm.formItem().creatorUserName("");
				self.searchForm.formItem().fdTemplateId=null;
				self.searchForm.formItem().fdTemplateName("");
			}
		}
		
		//单据过滤模型
		self.searchForm=new ko.searchViewModel(new DocSubmit({}),{
			callback:{
				search:function(searhForm){
					self.docSubmitId.findAll(searhForm)
				}
			}
		});
		
		//单据列表模型
		self.docSubmitId=new ko.gridViewModel({
			url:'docManage/billProcess',
			size:10,
			callback:{
				itemEdit:self.initApproveForm,
				itemSet:self.toDetail
			}
		});
		
		//单据审批模型
		self.editForm=new ko.formViewModel({
			url:'docManage/billProcess/billApprove',
			callback:{
				beforeSave:function(){
					var editForm=self.editForm.formItem();
					
					//即将流向为人工决策节点时,需要选择下一环节路径
					if(editForm.wfResult()==null||editForm.wfResult()==""){
						MsgBox.warning('请选择操作类型.');
						
						return -1;
					}
				},
				saveSuccess:self.toRefresh
			}
		});
	};
	
	return viewModel;
})
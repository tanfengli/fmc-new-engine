define(["knockout",'Flowchart',"../onPropEdit/onPropEdit.js",
    "../../../js/common/flowchart/billEvent.js",
    "../../../js/common/VO/sysWfBusinessFormVO",
    "../../../js/common/VO/sbWfApprovalFormVO.js","MsgBox","AjaxUtil"],
    function(ko,FlowChart,OnPropEdit,BillEvent,WfBusinessForm,WfApprovalForm,MsgBox,AjaxUtil){  
	
	function BillApprove(params){
		if(!ko.components.isRegistered("flowchartView")){
			ko.components.register('flowchartView',{
			    viewModel:{
			       require:'../blocks/flowchart/flowchartView/flowchartView.js'
			    },
			    template:{
			    	 require:'text!blocks/flowchart/flowchartView/flowchartView.html'
			    }
			});
		}
		
		if(!ko.components.isRegistered("formulaModal")){
			ko.components.register('formulaModal',{
			    viewModel:{
			       require:'../blocks/flowchart/formulaModal/formulaModal.js'
			    },
			    template:{
			    	 require:'text!blocks/flowchart/formulaModal/formulaModal.html'
			    }
			});
		}
		
		if(!ko.components.isRegistered("shortlist")){
			ko.components.register('shortlist',{
			    viewModel:{
			       require:'../blocks/setting/shortlist/shortlist.js'
			    },
			    template:{
			    	 require:'text!blocks/setting/shortlist/shortlist.html'
			    }
			});
		}
		
		if(!ko.components.isRegistered("communicatersSelect")){
			ko.components.register('communicatersSelect',{
			    viewModel:{
			       require:'../blocks/bill/communicaters/communicaters.js'
			    },
			    template:{
			    	 require:'text!blocks/bill/communicaters/communicaters.html'
			    }
			});
		}
		
		//模型别名
		var self=this;
		
		//流程实例信息
		self.wfInstanceId=ko.observable();
		//当前节点信息
		self.nodeId=ko.observable();
		
		//单据业务信息
		self.sysWfBusinessForm=ko.observable(new WfBusinessForm());
		//流程节点信息
		self.flowNodeList=ko.observableArray([]);
		//即将流向节点信息
		self.nextNodeList=ko.observableArray([]);
		//可以修改节点信息
		self.modifyNodeList=ko.observableArray([]);
		//所有建议
		self.apporveData=ko.observable();
		//默认建议
		self.approvalArray=ko.observable([]);
		//选中建议
		self.wfOptionCon=ko.observable();
		
		//控制组件加载
		self.isComponentShow=ko.observable(false);
		//控制流程图工具栏显示
		self.isToolbarShow=ko.observable(false);
		//控制流程图显示
		self.isFlowchartShow=ko.observable(true);
		//控制流程明细信息
		self.isMoreInfoShow=ko.observable(true);
		//控制审批日志明细信息
		self.isApproveInfoShow=ko.observable(true);
		//控制修改节点权限
		self.isCanModifyNodeShow=ko.observable(false);
		
		
		//传递参数监控
		params.data.subscribe(function(val){
			if(val){
				self.nodeId(val.nodeId)
				self.wfInstanceId(val.instanceId);
				self.initApprovePage();
			}
		})
		
		//初始化审批页面
		self.initApprovePage=function(){
			AjaxUtil.call({
				url:"flowchart/setting/getContentAndTranByInstanceId",
				type:"get",
				data:{instanceId:self.wfInstanceId()},
				success:function(data){
					self.flowNodeList(data.nodeList);
				}
			});
			
			AjaxUtil.call({
				url:"docManage/billProcess/billApprove/findForm",
				data:{wfInstanceId:self.wfInstanceId(),wfNodeId:self.nodeId(),identify:"processor"},
				type:"post",
				async:false,
				success:function(data){
					self.approveForm.setForm(new WfApprovalForm(data));
				}
			});
			
			AjaxUtil.call({
				url:"aboutMyself/myBills/getDetail",
				data:{wfInstanceId:self.wfInstanceId()},
				type:"post",
				success:function(data){
					$('#approve_main_tab li:eq(1) a').tab('show');
					$('#approve_process_tab a:first').tab('show');
					
					self.approvalFresh();
					
					self.sysWfBusinessForm(data);
					self.billEvent=new BillEvent(self.sysWfBusinessForm());
					self.nextNodeList(self.billEvent.WorkFlow_DrafterOperationTypeNextNode(self.nodeId()));
					self.approveForm.formItem().futureNodeId(self.nextNodeList()[0].id);
					self.isComponentShow(true);
					self.isFlowchartShow=ko.observable(true);
					
					if('function'==typeof params.initSuccess){
						params.initSuccess();
					}
				}
			});
		}
		
		//意见刷新
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
		
		//点击操作按钮时触发
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
			self.approveForm.formItem().wfOptionCon(defSuggestion);
		}
		
		//默认意见下拉选中更新
		self.wfOptionCon.subscribe(function(val){
			if(self.approveForm.formItem()!=undefined){
				self.approveForm.formItem().wfOptionCon(val);
			}
		})

		//处理人操作,驳回节点过滤
		self.handlerOperationTypeRefuse=function(){		
			self.nextNodeList([]);
			
			/*AjaxUtil.call({
				url:"aboutMyself/myBills/findRejectNodes",
				data:{wfInstanceId:self.wfInstanceId(),nodeId:self.nodeId()},
				type:"post",
				async:false,
				success:function(data){
					self.nextNodeList(data);
					self.approveForm.formItem().futureNodeId(self.nextNodeList()[0].id);
				}
			});*/
		}
		
		//审批操作选中触发事件
		self.oprClick=function(val){
			self.toApproveInit();
			
			switch(val.oprId){
				case('101')://通过
				case('106')://签字
					self.nextNodeList(self.billEvent.WorkFlow_DrafterOperationTypeNextNode(self.nodeId()));
					self.approveForm.formItem().futureNodeId(self.nextNodeList()[0].id);
					self.suggestRefresh(self.apporveData().pass);
					break;
				case('102')://驳回
					self.nextNodeList(self.billEvent.WorkFlow_HandlerOperationTypeRefuse(self.nodeId()));
					self.suggestRefresh(self.apporveData().reject);
					break;
				case('103')://转办
					self.suggestRefresh(self.apporveData().tranfor);
					break;
				case('104')://沟通
					self.suggestRefresh(self.apporveData().communicate);
					break;
				case('105')://废弃
					self.suggestRefresh(self.apporveData().abandon);
					break;
			}
			
			return true;
		}
		
		//选择转办人员回调函数
		self.selectNewElement=function(element){
			self.approveForm.formItem().toOtherHandlerIds(element.fdId);
			self.approveForm.formItem().toOtherHandlerNos(element.fdNo);
			self.approveForm.formItem().toOtherHandlerNames(element.fdName);
		}
		
		//自定义意见回调函数
		self.defLangCallBack=function(){
			self.approvalFresh();
		}
		
		//地址本选择参数
		self.handlerElementForm=function(){
			return {
				elementArray:[]
			};
		};
		
		//地址本选择回调函数
		self.orgSelectCallFunc=function(elements,memberNames,memberIds){
			var approveForm=self.approveForm.formItem();
			switch(approveForm.clickType){
			case 'toHandler':
			case 'scopeHandler':
				self.selectMember(elements,memberNames,memberIds);
				break;
			case 'seleteByOrg':
				//self.selectNewHandler(elements,memberNames,memberIds);
				break;
			}
		}
		
		//沟通人员选择参数
		self.elementForm={
			communicateScope:[],
			elementArray:[]
		};
		
		//沟通人员选择回调函数
		self.selectMember=function(elements,memberNames,memberIds){
			var approveForm=self.approveForm.formItem();

			if(approveForm.clickType=='toHandler'){
				approveForm.commicateHandlerIds(memberIds);
				approveForm.commicateHandlerNames(memberNames);
				self.handlerElementForm.elementArray=elements;
			}else if(approveForm.clickType=='scopeHandler'){
				approveForm.communicateScopeHandlerIds(memberIds);
				approveForm.communicateScopeHandlerNames(memberNames);
				self.elementForm.communicateScope=elements;
			}
		}
		
		//打开选择沟通人员组件 
		self.selectCommunicaters=function(){
			var approveForm=self.approveForm.formItem();
			approveForm.clickType='toHandler'
			AjaxUtil.call({
				url:'docManage/billProcess/getCommunicateScope',
				data:{workitemId:approveForm.fdHandleWorkItemId()},
				success:function(data){
					if(null!=data){
						self.elementForm.elementArray=self.handlerElementForm.elementArray;
						self.elementForm.communicateScope=data;
						$('#communicatersWindow').modal();
					}else{
						self.handlerElementForm.orgTypeArr=['4','8']
						$('#memberSelectWindow').modal();
					}
				}
			})
		}
		
		//沟通范围人员选择
		self.openCommunicateWindow=function(){
			var form=self.approveForm.formItem();
			form.clickType='scopeHandler';
			self.handlerElementForm.orgTypeArr=['4','8'];
			self.handlerElementForm.elementArray=self.elementForm.communicateScope;
			$('#memberSelectWindow').modal();
		}
		
		//备选列表中选择参数
		self.shortlistForm={
			elementArray:[],
			bussinessForm:'',
			nodeId:''
		};
		
		//备选列表中选择回调函数
		self.selectShortlist=function(arr,names,ids){
//			self.modifiedNodeList.remove(function(item){return item.id==self.modifyNodeId()});
//			var node = self.modifiedNode(self.modifyNodeId(),self.modifyNodeName(),ids,names,'org');
//			self.modifiedNodeList.push(node);
//			//修改下一节点处理人时直接保存
//			if(self.modifyNodeId()==self.nextNodeId()){
//				var form=self.approveForm.formItem();
//				form.modifiedNodeList=self.modifiedNodeList();
//			}
//			
//			if(node.id==self.nextNodeId()){
//				$('#handlerShowNames').text(names);
//			}else{
//				$("#node_"+self.modifyNodeId()).val(elementNames);
//			}
		}
		
		//选择公式定义器参数
		self.formulaName=ko.observable('');
		self.formulaForm=ko.pureComputed(function(){
			return {
				formulaName:ko.toJS(self.formulaName)
			};
	 	},this);
		
		//选择公式定义器回调函数
	 	self.selectformula=function(data){ 
//			self.modifiedNodeList.remove(function(item){return item.id==self.modifyNodeId()});
//			var node = self.modifiedNode(self.modifyNodeId(),self.modifyNodeName(),data.fdId,data.fdName,'formula');
//			self.modifiedNodeList.push(node);
//			//修改下一节点处理人时直接保存
//			if(self.modifyNodeId()==self.nextNodeId()){
//				var form=self.approveForm.formItem();
//				form.modifiedNodeList=self.modifiedNodeList();
//			}
//			
//			if(node.id==self.nextNodeId())
//				$('#handlerShowNames').text('暂时无法计算处理人');
//			else
//				$("#node_"+self.modifyNodeId()).val(elementNames);
		 }
		
		
		//流程节点双击事件引入
		self.onPropEdit=new OnPropEdit();
		self.props=ko.observable();
		self.dbclick_approve=function(props,attr){
			var obj=self.onPropEdit.onPropEdit(props,attr);
			obj.flowchart=self.sysWfBusinessForm.fdFlowContent;
			self.props(obj);
		}
		
		//初始化流程图
		self.approveInit=function(){
			self.flowchart_approve=new FlowChart({
				flowchartId:"approve_flowchart",
				showprops:self.dbclick_approve,
				mod:ko.observable()
			});
		
			//过滤流程图
			self.flowchart_approve.toolbar.filterFlow=function(){
			}
			
			//清除过滤流程图
			self.flowchart_approve.toolbar.clearFlow=function(){
			}
			
			$('#approve_process_tab li:eq(1) a').on('shown.bs.tab',function(e){
				if(self.isFlowchartShow()){
					var chartflowData=new Object();
		    		chartflowData.runningNodes=self.sysWfBusinessForm().nodeList;
		    		chartflowData.historyNodes=self.sysWfBusinessForm().historyList;
		    		chartflowData.xmlString=self.sysWfBusinessForm().fdFlowContent;
		    		self.flowchart_approve.init(chartflowData);
		    		
		    		self.isToolbarShow(true);
					self.isFlowchartShow(false);
				}
			});
		}
		
		//审批提交前检测
		self.toApproveBefore=function(){
			var approveForm=self.approveForm.formItem();
			//审批结果
			var wfResult=approveForm.wfResult();
			//审批意见
			var opinion=approveForm.wfOptionCon();
			//转交人
			var toOtherHandlerIds=approveForm.toOtherHandlerIds();
			
			if(wfResult==undefined||wfResult==null||wfResult=="null"||wfResult==""){
				MsgBox.warning('请您选择对应的操作项,进行提交.');
				
				return -1;
			}
			
			//转办时必须选择转办人员
			if(wfResult=='102'&&opinion==null){
				MsgBox.warning('请填写审批意见.');
				
				return -1;
			}
			
			//转办时必须选择转办人员
			if(wfResult=='103'&&toOtherHandlerIds==null){
				MsgBox.warning('请选择转办人员.');
				
				return -1;
			}
			
			//沟通时必须选择沟通人员
			if(wfResult=='104'){
				if(approveForm.commicateHandlerIds()==null){
					MsgBox.warning('请选择沟通人员.');
					
					return -1;
				}
				if(null==opinion||""==opinion){
					MsgBox.warning('请填写审批意见.');
					
					return -1;
				}
			}
			
			//回复沟通必须填写意见
			if(wfResult=='107'){
				if(null==opinion||""==opinion){
					MsgBox.warning('请填写审批意见.');
					
					return -1;
				}
			}
			
			//撤销沟通必须选择人员和意见
			if(wfResult=='108'){
				if(approveForm.celCommunicaterList().length==0){
					MsgBox.warning('请至少选中一个取消沟通人员');
					
					return -1;
				}
				
				if(null==opinion||""==opinion){
					MsgBox.warning('请填写审批意见.');
					
					return -1;
				}
			}
			
			//工作事项
			if(approveForm.fdHandleWorkItemId()==null){
				MsgBox.warning('请选择相关工作事项.');
				
				return -1;
			}
			
			//即将流向为“人工决策节点”时,需要选择下一环节路径
			if(approveForm.futureNodeId()==null||approveForm.futureNodeId()==''){
				MsgBox.warning('请选择即将流向的下一处理路径.');
				
				return -1;
			}
		}
		
		//审批页面返回
		self.toApproveBack=function(){
			params.callFunc()
		}

		//重置审批表单
		self.toApproveInit=function(){
			var approveForm=self.approveForm.formItem();
			approveForm.refusePassedToThisNode(false);
			approveForm.wfTransferUserNo(null);
			approveForm.toOtherHandlerIds(null);
			approveForm.toOtherHandlerNames(null);
			approveForm.communicateScopeHandlerIds(null);
			approveForm.communicateScopeHandlerNames(null)
			self.approveForm.setForm(approveForm);
		}
		
		//单据审批表单
		self.approveForm=new ko.formViewModel({
			url:'docManage/billProcess/billApprove',
			callback:{
				beforeSave:self.toApproveBefore,
				saveSuccess:self.toApproveBack
			}
		});
	};

	return BillApprove;
})
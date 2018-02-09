define(["knockout",'AjaxUtil','MsgBox',
        "../../../js/common/VO/sysWfBusinessFormVO.js",
        "../../../js/common/VO/sbWfApprovalFormVO.js",
        "../onPropEdit/onPropEdit.js",'Flowchart',
        "../../../js/common/flowchart/billEvent.js",
        "components","bootstrap"],function(ko,AjaxUtil,MsgBox,
		SysWfBusinessForm,WfApprovalForm,OnPropEdit,FlowChart,BillEvent){ 
	function BillEditorModel(params){
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
		
		//模型别名
		var self=this;
		//控制流程图工具栏
		self.isToolbarShow=ko.observable(false);
		//控制组件加载
		self.isComponentShow=ko.observable(false);
		//控制流程明细信息
		self.isMoreInfoShow=ko.observable(true);
		//控制是否配置高级选项
		self.isProcessSeniorShow=ko.observable(true);
		
		//单据业务信息
		self.sysWfBusinessForm=ko.observable(new SysWfBusinessForm());
		//流程节点信息
		self.flowNodeList=ko.observableArray([]);
		//即将流向节点
		self.nextNodeList=ko.observableArray([]);
		//所有建议
		self.apporveData=ko.observable();
		//默认建议
		self.approvalArray=ko.observable([]);
		//选中建议
		self.wfOptionCon=ko.observable();
		
		//监控组件数据
		params.data.subscribe(function(val){
			var fdId=val.fdId;
			var type=val.type;
			if(type=="1"){
				self.initBillEditorPage(fdId);
			}else{
				self.initBillCreatorPage(fdId);
			}
			
			params.data();
		});
		
		//初始化单据编辑信息
		self.initBillEditorPage=function(wfInstanceId){
			//获取单据审批信息
			AjaxUtil.call({
				url:"docManage/billProcess/billApprove/findForm",
				data:{wfInstanceId:wfInstanceId,wfNodeId:"N2",identify:"drafter"},
				type:"post",
				async:false,
				success:function(data){
					self.docSubmitForm.setForm(new WfApprovalForm(data));
				}
			});
			
			//获取单据业务信息
			AjaxUtil.call({
				url:"aboutMyself/myBills/getDetail",
				data:{wfInstanceId:wfInstanceId},
				type:"post",
				success:function(data){
					$('#billEditor_main_tab a:first').tab('show');
					$('#billEditor_process_tab a:first').tab('show');
					
					self.approvalFresh();
					data.basic.fdImportance='1';
					self.sysWfBusinessForm(data);
					self.flowchartInit("1",wfInstanceId);
					self.isComponentShow(true);
					self.isProcessSeniorShow(false);
					
					self.billEvent=new BillEvent(data);
					self.nextNodeList(self.billEvent.WorkFlow_DrafterOperationTypeNextNode("N2"));
					self.docSubmitForm.formItem().futureNodeId(self.nextNodeList()[0].id);
					
					if("function"===typeof params.initSuccess){
						params.initSuccess()
					}
				}
			});
		}
		
		//初始化单据创建信息
		self.initBillCreatorPage=function(fdTemplateId){
			//获取单据审批信息
			AjaxUtil.call({
				url:"docManage/billProcess/billApprove/findForm",
				data:{wfInstanceId:"",wfNodeId:"N2",identify:"drafter"},
				type:"post",
				async:false,
				success:function(data){
					self.docSubmitForm.setForm(new WfApprovalForm(data));
				}
			});
			
			//初始化单据信息
			AjaxUtil.call({
				url:'aboutMyself/myBills/initDetail',
				data:{fdTemplateId:fdTemplateId},
				type:"post",
				success:function(data){
					$('#billEditor_main_tab a:first').tab('show');
					$('#billEditor_process_tab a:first').tab('show');
					
					self.approvalFresh();
					data.basic.fdImportance='1';
					self.sysWfBusinessForm(data);
					self.flowchartInit("0",fdTemplateId);
					self.isComponentShow(true);
					self.isProcessSeniorShow(true);
					
					self.billEvent=new BillEvent(data);
					self.nextNodeList(self.billEvent.WorkFlow_DrafterOperationTypeNextNode("N2"));
					self.docSubmitForm.formItem().futureNodeId(self.nextNodeList()[0].id);
					
					if("function"===typeof params.initSuccess){
						params.initSuccess()
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
		
		//文本框意见更新
		self.wfOptionCon.subscribe(function(val){
			if(self.docSubmitForm.formItem()!=undefined){
				self.docSubmitForm.formItem().wfOptionCon(val);
			}
		})
		
		//人员组件初始化
		self.elementForm=ko.pureComputed(function(){
			return {
				elementArray:[]
			};
		},this);
		
		//人员组件回调函数
		self.selectNewElement=function(elements,memberNames){
			var formItem=self.docSubmitForm.formItem();
			switch(formItem.clickType){
				case ('editor'):
					$('#editor').val(memberNames);
					self.docSubmitForm.formItem().editorArray=ko.toJS(elements);
					break;
				case ('reader'):
					$('#reader').val(memberNames);
					self.docSubmitForm.formItem().readerArray=ko.toJS(elements);
					break;
			}
		}
		
		//提交单据校验
		self.docSubmitValidate=function(){
			self.docSubmitForm.formItem().sysNewsMain(self.sysWfBusinessForm().basic);
			if(self.sysWfBusinessForm().basic.docSubject==null){
				MsgBox.warning('单据主题不能为空.');
				return -1;
			}
			
			$('#billEditor_button').button('loading')
		}
		
		//提交单据成功
		self.docSubmitSuccess=function(){
			$('#billEditor_button').button('reset')
			self.isToolbarShow(false);
			self.isComponentShow(false);
			params.callFunc();
		}
		
		//提交单据失败
		self.docSubmitFailure=function(){
			$('#billEditor_button').button('reset')
		}
		
		//编辑单据表单
		self.docSubmitForm=new ko.formViewModel({
			url:'/aboutMyself/myBills',
			callback:{
				beforeSave:self.docSubmitValidate,
				saveSuccess:self.docSubmitSuccess,
				saveFailure:self.docSubmitFailure
			}
		});
		
		//流程节点双击事件引入
		self.flowchart_props=ko.observable();
		self.onPropEdit=new OnPropEdit();
		self.flowchart_dbclick=function(props,attr){
			var flowchart_param=self.onPropEdit.onPropEdit(props,attr);
			flowchart_param.flowchart=self.sysWfBusinessForm().fdFlowContent;
			flowchart_param.subProcessArray=ko.toJS(self.billEditor_flowchart.getNodesByType("startSubProcessNode"));
			self.flowchart_props(flowchart_param);
		}
		
		//初始化流程图
		self.docSubmitInit=function(){
			//流程图初始化
			self.billEditor_flowchart=new FlowChart({
				flowchartId:"billEditor_flowchart",
				showprops:self.flowchart_dbclick,
				mod:ko.observable()
			});
			
			//过滤流程图
			self.billEditor_flowchart.toolbar.filterFlow=function(){
			}
			
			//清除过滤流程图
			self.billEditor_flowchart.toolbar.clearFlow=function(){
			}
		}
		
		//加载流程图数据
		self.flowchartInit=function(type,param){
			$('#billEditor_main_tab li:eq(1) a').on('shown.bs.tab',function(e){
				if(type=="0"){
					AjaxUtil.call({
						url:"flowchart/setting/getContentAndNodeByTemplateId",
						type:"get",
						data:{templateId:param},
						success:function(data){
							var chartflowData=new Object();
							chartflowData.xmlString=data.xml;
							chartflowData.runningNodes=self.sysWfBusinessForm().nodeList;
		            		chartflowData.historyNodes=self.sysWfBusinessForm().historyList;
							self.billEditor_flowchart.init(chartflowData);
							self.isToolbarShow(true);
							self.flowNodeList(data.nodeList);
						}
					});
				}else{
					AjaxUtil.call({
		        		url:"flowchart/setting/getContentAndTranByInstanceId",
		        		type:"get",
		        		data:{instanceId:param},
		        		success:function(data){
		        			var chartflowData=new Object();
		            		chartflowData.xmlString=self.sysWfBusinessForm().fdFlowContent;
		            		chartflowData.runningNodes=self.sysWfBusinessForm().nodeList;
		            		chartflowData.historyNodes=self.sysWfBusinessForm().historyList;
		            		self.billEditor_flowchart.init(chartflowData);
		        			self.isToolbarShow(true);
		        			self.flowNodeList(data.nodeList);
		        		}
		    		});
				}
			});
		}
	}
	
	return BillEditorModel;
})
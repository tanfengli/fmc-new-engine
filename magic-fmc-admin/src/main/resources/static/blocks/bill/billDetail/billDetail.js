define(["knockout",'Flowchart','AjaxUtil','MsgBox',
        "../../../js/common/flowchart/billEvent.js",
        "../onPropEdit/onPropEdit.js"],function(ko,FlowChart,
	AjaxUtil,MsgBox,BillEvent,OnPropEdit){  

	function BillDetailModel(params){
		if (!ko.components.isRegistered("flowchartView")){
			ko.components.register('flowchartView',{
			    viewModel:{
			       require:'../blocks/flowchart/flowchartView/flowchartView.js'
			    },
			    template:{
			    	 require:'text!blocks/flowchart/flowchartView/flowchartView.html'
			    }
			});
		}
		
		//模型别名
		var self=this;
		
		//双击事件
		self.props_detail=ko.observable();
		
		//通用工具
		self.billEvent=null;
	
		//流程实例
		self.sysWfBusinessForm=ko.observable();
	
		//当前单据信息
		self.instanceId=ko.observable();
		
		//流程图节点列表
		self.flowNodeList=ko.observableArray([]);
		
		//控制是否刷新流程图
		self.flag=ko.observable(true);
		self.flowchartInited=ko.observable(false);
	
		//显示
		self.isPageShow=ko.observable(false);
		self.showAppro=ko.observable(true);
		self.moreInfo=ko.observable(true);
		self.showNode=ko.observable(true);
	
		//监控数据变化
		params.data.subscribe(function(val){
			if(""!=val){
				self.isPageShow(true)
				self.initDetailPage(ko.toJS(val))
				params.data("");
			}
		})
	
		//更新页面数据
		self.initDetailPage=function(instanceId){
			//设置流程实例
			self.instanceId(instanceId);
			
			//插入阅读记录
			AjaxUtil.call({
				url:'sys/readLog/insertOne',
				data:{billId:instanceId},
				async:false
			})
			
			//获取单据详情
			AjaxUtil.call({
				url:"aboutMyself/myBills/getDetail",
				data:{wfInstanceId:instanceId},
				type:"post",
				success:function(data){
					$('#detailFirstTab a:first').tab('show');
					$('#detailSecondTab a:first').tab('show');
					
					self.sysWfBusinessForm(data);
					self.billEvent=new BillEvent(self.sysWfBusinessForm());
					
					self.readGrid.findAll({fdModelId:instanceId})
					if('function'==typeof params.initSuccess){
						params.initSuccess();
					}
				}
			});
		}
		
		//阅读日志表格
		self.readGrid=new ko.gridViewModel({
			url:'sys/readLog',
			size:10,
			isAutoRetrieve:'false'
		});
	
		//流程节点双击事件引入
		self.onPropEdit=new OnPropEdit();
		self.detail_dbclick=function(props,attr){
			var obj=self.onPropEdit.onPropEdit(props,attr);
			obj.subProcessArray=ko.toJS(self.flowchart_detail.getNodesByType("startSubProcessNode"));
			obj.flowchart=self.sysWfBusinessForm().fdFlowContent;
			self.props_detail(obj);
		}
		
		//更新后处理,即生成DOM元素的后处理
		self.afterRender_detail=function(){
			//流程图初始化
			self.flowchart_detail=new FlowChart({
				flowchartId:"flowchart_detail",
				showprops:self.detail_dbclick,
				mod:ko.observable()
			});
			
			//过滤流程图
			self.flowchart_detail.toolbar.filterFlow=function(){
				var chartflowData=new Object();
				var nodes=[],lines=[];
				self.billEvent.filterFlowChartNodes(self.sysWfBusinessForm().historyList,self.sysWfBusinessForm().nodeList,"N1",nodes);
				self.billEvent.filterFlowChartLines(nodes,lines);
				
				self.flowchart_detail.clear();
        		chartflowData.xmlString=self.billEvent.filterFlowChart(nodes,lines);
        		chartflowData.runningNodes=self.sysWfBusinessForm().nodeList;
        		chartflowData.historyNodes=self.sysWfBusinessForm().historyList;
        		self.flowchart_detail.init(chartflowData);
			}
			
			//清除过滤流程图
			self.flowchart_detail.toolbar.clearFlow=function(){
				self.flowchart_detail.clear();
				
				var chartflowData=new Object();
        		chartflowData.xmlString=self.sysWfBusinessForm().fdFlowContent;
        		chartflowData.runningNodes=self.sysWfBusinessForm().nodeList;
        		chartflowData.historyNodes=self.sysWfBusinessForm().historyList;
        		self.flowchart_detail.init(chartflowData);
			}
			
			//当前页是流程tab时刷新流程图
			$('#lct_detail').on('shown.bs.tab',function(e){
	            if(self.flag()){
	            	AjaxUtil.call({
	            		url:"flowchart/setting/getContentAndTranByInstanceId",
	            		type:"get",
	            		data:{instanceId:self.instanceId()},
	            		success:function(data){
	            			var chartflowData=new Object();
		            		chartflowData.xmlString=self.sysWfBusinessForm().fdFlowContent;
		            		chartflowData.runningNodes=self.sysWfBusinessForm().nodeList;
		            		chartflowData.historyNodes=self.sysWfBusinessForm().historyList;
		            		self.flowchart_detail.init(chartflowData);
		            		self.flowchartInited(true);
		        			self.flowNodeList(data.nodeList);
		        			self.flag(false);
	            		}
	            	});
	            }
	        });
		}
		 
		//返回
		self.toBack_detail=function(){
			self.flag(true);
			self.flowchartInited(false);
			self.isPageShow(false);
			params.callFunc()
		}
	};
	
	return BillDetailModel;
})
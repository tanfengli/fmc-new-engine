var selectType;
define(['knockout','AjaxUtil',
         '../setting/vo/xmlNodeVO.js',
         '../setting/vo/lineVO.js', 				//线
         '../setting/vo/commonNodeVO.js',			//通用简单节点
         '../setting/vo/splitNodeVO.js',			//通用简单节点
         '../setting/vo/draftNodeVO.js',			//起草节点
         '../setting/vo/sendNodeVO.js',				//抄送节点
         '../setting/vo/reviewNodeVO.js',			//审批节点
         '../setting/vo/robotNodeVO.js',			//审批节点
         '../setting/vo/startSubProcessNodeVO.js',		//启动流程节点
    	 '../setting/vo/recoverSubProcessNodeVO.js',		//回收流程节点
    	 '../../../js/common/flowchart/billEvent.js',		 //流程图解析js
         "components"],function(ko,AjaxUtil,XmlNode,Line,CommonNode,SplitNode,DraftNode,
    		 SendNode,ReviewNode,RobotNode,StartSubProcessNode,RecoverSubProcessNode,BillEvent){

	function flowchartDesigner(param){
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
		
		if(!ko.components.isRegistered("reviewNode")){
			ko.components.register('reviewNode',{
			    viewModel:{
			       require:'../blocks/flowchart/reviewNode/reviewNode.js'
			    },
			    template:{
			    	 require:'text!blocks/flowchart/reviewNode/reviewNode.html'
			    }
			}); 
		}
		
		if(!ko.components.isRegistered("robotNode")){
			ko.components.register('robotNode',{
			    viewModel:{
			       require:'../blocks/flowchart/robotNode/robotNode.js'
			    },
			    template:{
			    	 require:'text!blocks/flowchart/robotNode/robotNode.html'
			    }
			}); 
		}	
		
		if(!ko.components.isRegistered("authorityNode")){
			ko.components.register('authorityNode',{
			    viewModel:{
			       require:'../blocks/flowchart/authorityNode/authorityNode.js'
			    },
			    template:  {
			    	 require:'text!blocks/flowchart/authorityNode/authorityNode.html'
			    }
			}); 
		}	
		
		var self=this;
		
		self.editFlag=false;
		var xmlNodeInfo=new XmlNode();
		self.nodeForm=ko.observable(xmlNodeInfo);
	    //定义操作方式
	    
	    self.xmlString='<process />'
	    self.elementArray=ko.observableArray([]);
	    self.pattern=ko.observable('group');
	    //公式参数
	    self.formulaName=ko.observable();//公式参数
	    self.formulaType=ko.observable();//公式回调类别 line条件线条 conditionLine条件分支
		//选择节点组件参数
	    self.reviewArray=ko.observableArray([]);
	    self.selectNodes=ko.observableArray([]);
	    //操作方式
	    self.operaterArr=ko.observableArray([]);
	    //机器人节点
	    self.robotNodeContent=ko.observable();
	    self.nodeNames=ko.observableArray([]);
	    
	    self.labelName=ko.observable();
	    self.labelId=ko.observable();
	    
	    //保存子流程节点
	    self.subProcessNodes=ko.observableArray([]);
	    
		self.onPropEdit=function(props, attr){
			 self.labelName(props.name);
			 self.labelId(props.id);
			if(props.XMLNODENAME=='line'){
				self.initLineNode(props,attr)
			}else if(props.XMLNODENAME=='manualBranchNode'||props.XMLNODENAME=='autoBranchNode'){
				self.initCommonNode(props,attr);
			}else if(props.XMLNODENAME=='splitNode'||props.XMLNODENAME=='joinNode'){
				self.initSplitNode(props,attr);
			}else if(props.XMLNODENAME=='draftNode'){
				self.initDraftNode(props,attr);
			}else if(props.XMLNODENAME=='sendNode'){
				self.initSendNode(props,attr);
			}else if(props.XMLNODENAME=='reviewNode'||props.XMLNODENAME=='signNode'){
				self.initReviewNode(props,attr);
			}else if(props.XMLNODENAME=='robotNode'){
				self.initRobotNode(props,attr);
			}else if(props.XMLNODENAME=='startSubProcessNode'){
				self.initStartSubProcessNode(props,attr);
			}else if(props.XMLNODENAME=='recoverSubProcessNode'){
				self.initRecoverSubProcessNode(props,attr);
			}
		};
		
		self.editWindowClose=function(props){
			if(props.XMLNODENAME=='autoBranchNode'||props.XMLNODENAME=='splitNode'){
				var attr = new Object();
				var lineArr = ko.toJS(props.lineOut);
				var tempData = ko.toJS(props.lineData);
				for(i=0;i<lineArr.length;i++){
					lineArr[i].data=tempData[i];
				}
				attr.lineOut = lineArr;
				self.flowchart.updateprops(props,attr);
			}else if(props.XMLNODENAME=='reviewNode'||props.XMLNODENAME=='signNode'){
				props.saveData();
				self.flowchart.updateprops(props);
			}else if(props.XMLNODENAME=='robotNode'){
				props.saveRobot();
				self.flowchart.updateprops(props);
			}else{
				self.flowchart.updateprops(props);
			}
		
			$('#flowviewModal').modal("hide");
			
			self.flowchart.setFocus();
		}
		
		// 初始化prop属性
		self.initPropData = function(props,flowchart){
			if(!flowchart||!props){
				return ;
			}
			var flowChartFunc = new BillEvent({fdFlowContent:flowchart})
			if(props.canModifyHandlerNodeIds&&''!=props.canModifyHandlerNodeIds){
				var canModifyHandlerNodeNames = flowChartFunc.nodeIdsToNodeNames(props.canModifyHandlerNodeIds);
				props.canModifyHandlerNodeNames = canModifyHandlerNodeNames;
			}
			if(props.mustModifyHandlerNodeIds&&''!=props.mustModifyHandlerNodeIds){
				var mustModifyHandlerNodeNames = flowChartFunc.nodeIdsToNodeNames(props.mustModifyHandlerNodeIds);
				props.mustModifyHandlerNodeNames = mustModifyHandlerNodeNames;
			}
			
		}
		
		param.data.subscribe(function(){
			var infoData=param.data()
			var props=infoData.props;
			var attr=infoData.attr;
			var flowchart=infoData.flowchart
			self.subProcessNodes(infoData.subProcessArray);
			self.initPropData(props,flowchart);
			self.onPropEdit(props,attr);
		})
		 
		//获取特权人
		self.privilegerSelect=function(data){
			 var elementArr=[];
			 
			 if(ko.toJS(data).privilegerIds!=undefined){
				 var idArr=ko.toJS(data).privilegerIds.split(';');
				 var nameArr=ko.toJS(data).privilegerNames.split(';');
				 
				 for(i=0;i<idArr.length;i++){
					 elementArr.push({"fdId":idArr[i],"fdName":nameArr[i]})
				 }
			 }
			 
			 self.pattern('privileger');
			 self.elementArray(elementArr);
			 $('#memberSelectWindow').modal();
			 self.flowchart.processDefine.ifSelect=true;
		}
		 
		 
		self.initLineNode=function(props,attr){
    	   var line=new Line();
    	   $.extend(line,props);
    	   
    	   if(props.XMLNODENAME=='line'){
				var startNodeName = attr.startNode.XMLNODENAME;
				if(startNodeName=='autoBranchNode'||startNodeName=='splitNode'){
					self.formulaType('line');
					line.isBranch(true);
				}else{
					line.isBranch(false);
				}
			}
    	   self.nodeForm(line);
    	   $('#flowviewModal').modal();
       }
       
       self.initCommonNode=function(props,attr){
    	   var commonNode=new CommonNode();
    	   $.extend(commonNode,props);
    	   if(props.XMLNODENAME=='autoBranchNode'){
    		   self.formulaType('conditionLine');
    		   commonNode.setLineData(attr);
    	   }
    	   self.nodeForm(commonNode);
    	   $('#flowviewModal').modal();
       }
       
       self.initSplitNode=function(props,attr){
    	   var splitNode=new SplitNode ();
    	   $.extend(splitNode,props);
    	   self.formulaType('splitLine');
    	   splitNode.setLineData(attr);
    	   splitNode.initData();
    	   self.nodeForm(splitNode);
    	   $('#flowviewModal').modal();
       }
       
       self.initReviewNodes=function(nodeId){
    	    self.reviewArray.removeAll();
    	    var reviewArr=self.flowchart.getAllReviewNode();
			var objArr=new Array();
			for(i=0;i<reviewArr.length;i++){
				if(reviewArr[i].id!=xmlNodeInfo.nodeId){
					objArr[i]=reviewArr[i];
				}
			}
			self.reviewArray(objArr);
       }
       
       self.initDraftNode=function(props,attr){
    	   var draftNode=new DraftNode ();
    	   $.extend(draftNode,props);
    	   draftNode.setTab('basical');
    	   self.nodeForm(draftNode);
    	   $('#flowviewModal').modal();
       }
       
       self.initSendNode=function(props,attr){
    	   var sendNode=new SendNode();
    	   $.extend(sendNode,props);
    	   sendNode.setTab('basical');
    	   self.nodeForm(sendNode);
    	   $('#flowviewModal').modal();
       }
       
       self.initReviewNode=function(props,attr){
    	   var reviewNode=new ReviewNode();
    	   $.extend(reviewNode,props);
    	   reviewNode.setTab('basical');
    	   reviewNode.initData();
    	   reviewNode.initOperation(ko.toJS(self.operaterArr),ko.toJS(self.nodeNames));
    	   self.nodeForm(reviewNode);
    	   var br=navigator.userAgent.toLowerCase();  
    	   var browserVer=(br.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [0, '0'])[1];  
    	   $('#flowviewModal').modal();
       }

       self.initRobotNode=function(props,attr){
    	   var robotNode=new RobotNode();
    	   $.extend(robotNode,props);
    	   robotNode.initData(ko.toJS(self.compDbcpArr),ko.toJS(self.varArr));
    	   self.nodeForm(robotNode);
    	   $('#flowviewModal').modal();
       }
       
       //初始化启动子流程信息
       self.initStartSubProcessNode=function(props,attr){
    	   var startSubProcessNode=new StartSubProcessNode();
    	   $.extend(startSubProcessNode,props);
    	   startSubProcessNode.initData(props);
    	   self.nodeForm(startSubProcessNode);
    	   
    	   $('#flowviewModal').modal();
       }
       
       //初始化回收子流程信息
       self.initRecoverSubProcessNode=function(props,attr){
    	   var recoverSubProcessNode=new RecoverSubProcessNode();
    	   $.extend(recoverSubProcessNode,props);
    	   recoverSubProcessNode.initData(props,self.subProcessNodes());
    	   self.nodeForm(recoverSubProcessNode);
    	   
    	   $('#flowviewModal').modal();
       }
       
       self.setAuthorty=function(){
       }
       
       self.confirmAuthority=function(){
       }
	      
       self.elementForm=ko.pureComputed(function(){
			return {
				elementArray:ko.toJS(self.elementArray),
				pattern:ko.toJS(self.pattern)
			};
	 	}, this); 
       
       	self.formulaForm=ko.pureComputed(function(){
			return {
				formulaName:ko.toJS(self.formulaName)
			};
	 	}, this);
       	
       	self.reviewNodeForm=ko.pureComputed(function() {
			return {
				reviewArray:ko.toJS(self.reviewArray),
				newSelectMember:ko.toJS(self.selectNodes)
			};
	 	}, this);
       	
       	self.robotNodeForm=ko.pureComputed(function() {
			return {
				robotNodeContent:ko.toJS(self.robotNodeContent)
			};
	 	},this);
	 	
       
     	self.computeHandler=function(handlerNames){
     		var newFormItem=self.nodeForm();
     		var objArr=new Array();
     		
			if(handlerNames!=null&&handlerNames!=''){
				 var arr=handlerNames.split(";");
				 var fdIdArr=newFormItem.handlerIds.split(";");
				 for(var i=0;i<arr.length;i++){
					 var obj=new Object();
					 obj.fdName=arr[i];
					 obj.fdId=fdIdArr[i];
					 objArr[i]=obj;
				 }
     		}
			 
			self.elementArray(objArr);
		 };
		 
		 self.reviewHandle=function(reviewNodes){
			 var newFormItem=self.nodeForm();
			 var arr=newFormItem.getReviewNodes();
			 self.selectNodes(arr);
		 };
		 
		 self.selectReviewNode=function(data){
			 var newFormItem=self.nodeForm();
			 $('#flowviewModal').modal("hide");
			 var newFormItem=self.nodeForm();
			 newFormItem.setReviewNodes(data);
			 self.nodeForm(newFormItem);
			 $('#flowviewModal').modal();
		 }
		 self.formulaHandler=function(formulaName){
			 var newFormItem=self.nodeForm();
			 if(newFormItem.XMLNODENAME=='autoBranchNode'||newFormItem.XMLNODENAME=='splitNode'){
				 self.formulaName(newFormItem.setFormula(formulaName));
			 }else if(newFormItem.XMLNODENAME=='robotNode'){
				 self.formulaName(newFormItem.initFormula());
	     	 }else{
				 self.formulaName(formulaName);
			 }
		 }
		 
		 //选择设置机器人前置/后置
		 self.computeRobot=function(robotType){
			 var newFormItem=self.nodeForm();
			 self.robotNodeContent(newFormItem.getRobotContent(robotType));
		 };
		 
		 //公式组件回调处理
		 self.selectformula=function(data){ 
			 $('#flowviewModal').modal("hide");
			 var formulaType=ko.toJS(self.formulaType);
			 var newFormItem=self.nodeForm();
			 
			 if(formulaType=='line'){
				 newFormItem.condition=data.fdId;
				 newFormItem.disCondition=data.fdName; 
			 }else if('conditionLine'==formulaType){
				 newFormItem.selectformula(data)
				 self.formulaName(ko.toJS(newFormItem.formulaName));
			 }else if('splitLine'==formulaType){
				 newFormItem.selectformula(data)
				 self.formulaName(ko.toJS(newFormItem.formulaName));
			 }else if('joinLine'==formulaType){
				 newFormItem.selectformula(data)
				 self.formulaName(ko.toJS(newFormItem.formulaName));
			 }else{
				 if(newFormItem.XMLNODENAME=='robotNode'){
					 newFormItem.selectformula(data)
				 }
			 }
			 
			 self.nodeForm(newFormItem);
			 
			 $('#flowviewModal').modal();
		 }
		 
		 //组件人员组织回调方法
		 self.selectNewElement=function(data,memberNames){
			if(!self.flowchart.processDefine.ifSelect){
				$('#flowviewModal').modal("hide");
			}
			var handlerIds="";
			var handlerNames="";
			for(i=0;i<data.length;i++){
				if(i==data.length-1){
					handlerIds=handlerIds+data[i].fdId;
					handlerNames=handlerNames+data[i].fdName;
				}else{
					handlerIds=handlerIds+data[i].fdId+";";
					handlerNames=handlerNames+data[i].fdName+";";
				}
			}
			
			var newFormItem=self.nodeForm();
			if(self.flowchart.processDefine.ifSelect){
				self.processDefine.privilegerIds(handlerIds);
				self.processDefine.privilegerNames(handlerNames);
			}else{
				newFormItem.selectNewElement(handlerIds,handlerNames);
			}
			
			self.nodeForm(newFormItem);
			
			if(!self.flowchart.processDefine.ifSelect){
				$('#flowviewModal').modal();
			}
		 }
		 
		 self.confirmRobot=function(data){   
		 } 
		 
		 self.openWindow=function(type){
			var valueT=$('input:radio[name="'+type+'"]:checked').val();
			if(type=='handlerSelectType'||type=='optHandlerSelectType'){
				 self.pattern('handler');
			}else{
				 self.pattern('group');
			}
			if(valueT=='org'){
				$('#memberSelectWindow').modal();
			}else{
				$('#formulaModal').modal();
			}
		}
		 
		AjaxUtil.call({
    	   url:'sys/operationMode/findList',
    	   success:function(data){
    		   self.operaterArr(data);
    	   }
        })
       
        AjaxUtil.call({
			url:"sys/basicSet/findForm", 
			type:"POST",
			success:function(allData){
				var nodeName=allData.nodeNameSelectItem;
				if(nodeName!=null){
					var selectNodeArr=nodeName.split(";")
					for(i=0;i<selectNodeArr.length;i++){
						var nodeObj=new Object();
						nodeObj.name=selectNodeArr[i];
						self.nodeNames.push(nodeObj);
					}
				}
			}
        })
		 
		  
        self.compDbcpArr=ko.observableArray([]);
	 	AjaxUtil.call({
	 		url:"sys/variableSetting/findCompDbcp", 
	 		type:"post",
	 		success:function(dataAll){
	 			for(i=0;i<dataAll.length;i++){
	 				self.compDbcpArr.push(dataAll[i]);
	 			}
	 		}
	 	})
	  
	 	var solidName="固定变量.";
	 	var defineName="自定义变量.";
	 	self.varArr=ko.observableArray([]);
	 	AjaxUtil.call({
	 		url:"sys/variableSetting/initData", 
	 		type:"get",
	 		success:function(data) {
	 			for(i=0;i<data.varList.length;i++){
	 				var node=data.varList[i];
	 				if(node.varType=='solid'){
	 					node.varName=solidName+node.varName;
	 				}else{
	 					node.varName=defineName+node.varName;
	 				}
	 				self.varArr.push(node);
	 			}
	 		}
	 	});
	}

	return flowchartDesigner;
})

//控制
function checkRadio(flag){
	var objArr = $('input:radio[name="passType"]');
	for(i=0;i<objArr.length;i++){
		objArr[i].disabled=flag;
	}
}

//控制
function selectJoinType(flag){
	var formula = $('#formula');
	if(flag){
		formula[0].style.display="block";
	}else{
		formula[0].style.display="none";
	}
}
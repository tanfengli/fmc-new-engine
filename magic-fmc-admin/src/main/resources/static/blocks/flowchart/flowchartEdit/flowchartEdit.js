var selectType;
define(['knockout',  
	 'AjaxUtil',
	 '../../../blocks/flowchart/setting/vo/xmlNodeVO.js',
	 '../../../blocks/flowchart/setting/vo/lineVO.js', 						//线
	 '../../../blocks/flowchart/setting/vo/commonNodeVO.js',				//通用简单节点
	 '../../../blocks/flowchart/setting/vo/splitNodeVO.js',					//条件分支节点
	 '../../../blocks/flowchart/setting/vo/draftNodeVO.js',					//起草节点
	 '../../../blocks/flowchart/setting/vo/sendNodeVO.js',					//抄送节点
	 '../../../blocks/flowchart/setting/vo/reviewNodeVO.js',				//审批节点
	 '../../../blocks/flowchart/setting/vo/robotNodeVO.js',					//机器人节点
	 '../../../blocks/flowchart/setting/vo/authorityVO.js',					//审批节点
	 '../../../blocks/flowchart/setting/vo/startSubProcessNodeVO.js',		//启动流程节点
	 '../../../blocks/flowchart/setting/vo/recoverSubProcessNodeVO.js',		//回收流程节点
	 "components"],function(ko,AjaxUtil,XmlNode,Line,CommonNode,SplitNode,DraftNode,
		 SendNode,ReviewNode,RobotNode,AuthortyInfo,StartSubProcessNode,RecoverSubProcessNode){

	function flowchartEdit(param){
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
		
		if(!ko.components.isRegistered("reviewNode2")){
			ko.components.register('reviewNode2',{
			    viewModel:{
			       require:'../blocks/flowchart/reviewNode/reviewNode2.js'
			    },
			    template:{
			    	 require:'text!blocks/flowchart/reviewNode/reviewNode2.html'
			    }
			}); 
		}
		
		if(!ko.components.isRegistered("robotNode")){
			ko.components.register('robotNode',{
			    viewModel:{
			       require:'../blocks/flowchart/robotNode/robotNode.js'
			    },
			    template:{
			    	 require:'text!blocks/flowchart/robotNode/robotNode.html',
			    }
			}); 
		}	
		
		if(!ko.components.isRegistered("authorityNode")){
			ko.components.register('authorityNode',{
			    viewModel:{
			       require:'../blocks/flowchart/authorityNode/authorityNode.js'
			    },
			    template:{
			    	 require:'text!blocks/flowchart/authorityNode/authorityNode.html',
			    }
			}); 
		}	
		
		var self=this;
		
		self.editFlag=true;
		var xmlNodeInfo=new XmlNode();
		self.nodeForm=ko.observable(xmlNodeInfo);
	    //定义操作方式
	    self.elementArray=ko.observableArray([]);
	    self.pattern=ko.observable();
	    //公式参数
	    self.formulaName=ko.observable();
	    //公式回调类别 line条件线条 conditionLine条件分支
	    self.formulaType=ko.observable();
		//选择节点组件参数
	    self.reviewArray=ko.observableArray([]);
	    self.reviewElement=ko.observableArray([]);
	    self.selectNodes=ko.observableArray([]);
	    //操作方式
	    self.operaterArr=ko.observableArray([]);
	    //机器人节点
	    self.robotNodeContent=ko.observable();
	    self.nodeNames=ko.observableArray([]);
	    self.reviewNodeData=ko.observableArray([]);
	    self.authorityInfo=ko.observable();
	    self.labelName=ko.observable();
	    self.labelId=ko.observable();
	    
	    //保存子流程节点
	    self.subProcessNodes=ko.observableArray([]);
	    
		self.onPropEdit=function(props,attr){
			self.labelId(props.id);
			self.labelName(props.name);
			
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
			console.info(props)
			param.callFunc(props); 
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
    	    
    	    $('#myModal').modal();
		}
       
        self.initCommonNode=function(props,attr){
    	    var commonNode=new CommonNode();
    	    $.extend(commonNode,props);
    	    if(props.XMLNODENAME=='autoBranchNode'){
    		   self.formulaType('conditionLine');
    		   commonNode.setLineData(attr);
    	    }
    	    self.nodeForm(commonNode);
    	    
    	    $('#myModal').modal();
        }

        // 并行分支节点
		self.initSplitNode=function(props,attr){
			var splitNode=new SplitNode();
			$.extend(splitNode,props);
			self.formulaType('splitLine');
			splitNode.setLineData(attr);
			splitNode.initData();
			self.nodeForm(splitNode);

			$('#myModal').modal();
		}

		// 审批 签字节点
		self.initReviewNodes=function(nodeId){
			self.reviewArray.removeAll();
			var reviewArr=parent.flowchart.getAllReviewNode();
			var objArr=new Array();
			var num=0;
			for(i=0;i<reviewArr.length;i++){
				if(reviewArr[i].id!=nodeId){
					objArr[num++]=reviewArr[i];
				}
			}
			self.reviewArray(objArr);
		}
       
       //起草节点
       self.initDraftNode=function(props,attr){
    	   var draftNode=new DraftNode ();
    	   $.extend(draftNode,props);
    	   draftNode.setTab('basical');
    	   self.nodeForm(draftNode);
    	   
    	   $('#myModal').modal();
       }
       
       //抄送节点
       self.initSendNode=function(props,attr){
    	   var sendNode=new SendNode();
    	   $.extend(sendNode,props);
    	   sendNode.initData();
    	   sendNode.setTab('basical');
    	   self.nodeForm(sendNode);
    	   
    	   $('#myModal').modal();
       }
       
       //初始化审批节点信息
       self.initReviewNode=function(props,attr){
    	   var reviewNode=new ReviewNode();
    	   $.extend(reviewNode,props);
    	   reviewNode.setTab('basical');
    	   reviewNode.initData();
    	   reviewNode.initOperation(ko.toJS(self.operaterArr),ko.toJS(self.nodeNames));
    	   self.nodeForm(reviewNode);
    	   
    	   $('#myModal').modal();
       }
       
       //初始化机器人信息
       self.initRobotNode=function(props,attr){
    	   var robotNode=new RobotNode();
    	   $.extend(robotNode,props);
    	   robotNode.initData(ko.toJS(self.compDbcpArr),ko.toJS(self.varArr));
    	   self.nodeForm(robotNode);
    	   
    	   $('#myModal').modal();
       }
       
       //初始化启动子流程信息
       self.initStartSubProcessNode=function(props,attr){
    	   var startSubProcessNode=new StartSubProcessNode();
    	   $.extend(startSubProcessNode,props);
    	   startSubProcessNode.initData(props);
    	   self.nodeForm(startSubProcessNode);
    	   
    	   $('#myModal').modal();
       }
       
       //初始化回收子流程信息
       self.initRecoverSubProcessNode=function(props,attr){
    	   var recoverSubProcessNode=new RecoverSubProcessNode();
    	   $.extend(recoverSubProcessNode,props);
    	   recoverSubProcessNode.initData(props,self.subProcessNodes());
    	   self.nodeForm(recoverSubProcessNode);
    	   
    	   $('#myModal').modal();
       }
       
	   //组件代码  
       self.elementForm=ko.pureComputed(function(){
    	   return{
    		   elementArray:ko.toJS(self.elementArray),
    		   pattern:ko.toJS(self.pattern)
    	   };
	   },this); 
       
   	   self.formulaForm=ko.pureComputed(function(){
   		   return{
   			   formulaName:ko.toJS(self.formulaName)
		   };
	   },this);
       	
       self.reviewNodeForm2=ko.pureComputed(function(){
		   return{
			   reviewArray:ko.toJS(self.reviewArray),
			   newSelectMember:ko.toJS(self.selectNodes)
		   };
	   },this);
       	
       self.robotNodeForm=ko.pureComputed(function(){
			return{
				robotNodeContent:ko.toJS(self.robotNodeContent)
			};
       },this);
       	
       self.authorityForm=ko.pureComputed(function(){
			return{
				reviewNodeData:ko.toJS(self.reviewNodeData)
			};
       },this);      
       	
       self.computeHandler=function(handlerNames){
    	   var newFormItem=self.nodeForm();
    	   var objArr=new Array();

    	   if (handlerNames!=null&&handlerNames!=''){
				var arr=handlerNames.split(";");
				var fdIdArr=newFormItem.handlerIds.split(";");
				for (var i=0;i<arr.length;i++){
					var obj=new Object();
					obj.fdName=arr[i];
					obj.fdId=fdIdArr[i];
					objArr[i]=obj;
				}
   		   }
			
    	   if(newFormItem.XMLNODENAME=='reviewNode'||newFormItem.XMLNODENAME=='sendNode'||newFormItem.XMLNODENAME=='signNode'){
    		   self.formulaName(newFormItem.setFormula());
    		   self.formulaType(ko.toJS(newFormItem.selectType));
    	   }
			
    	   self.elementArray(objArr);
    	   param.nameFunc(objArr,ko.toJS(self.pattern));
       };
		 
       //回显选中节点信息
       self.reviewHandle=function(reviewNodes){
    	   var newFormItem=self.nodeForm();
    	   var arr=newFormItem.getReviewNodes();
    	   self.reviewArray(ko.toJS(self.reviewElement));
    	   self.selectNodes(arr);
       };
		 
       //回调选中节点
       self.selectReviewNode=function(data){
    	   var newFormItem=self.nodeForm();
    	   if(newFormItem.XMLNODENAME=='authority'){
    		   var authorityInfo=ko.toJS(self.authorityInfo);
    		   var reviewNodesArr=ko.toJS(self.reviewNodeData);
    		   var robRow=parseInt(authorityInfo.substring(16));
    		   var nodeObj=reviewNodesArr[robRow];
    		   reviewNodesArr[robRow]=newFormItem.setReviewNodes(data,nodeObj,authorityInfo);
    		   self.reviewNodeData(reviewNodesArr);
				
    		   return ;
    	   }else{
    		   newFormItem.setReviewNodes(data);
    		   self.nodeForm(newFormItem);
    	   }
       }
		 
       //回显公式内容
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
    	   self.formulaType('robotFormula');
    	   self.robotNodeContent(newFormItem.getRobotContent(robotType));
       };
		 
       //公式组件回调处理
       self.selectformula=function(data){ 
    	   var formulaType=ko.toJS(self.formulaType);
    	   var newFormItem=self.nodeForm();
    	   if(formulaType=='line'){
    		   newFormItem.condition=data.fdId;
    		   newFormItem.disCondition=data.fdName; 
    	   }else if('conditionLine'==formulaType){
    		   newFormItem.selectformula(data)
    	   }else if('splitLine'==formulaType){
    		   newFormItem.selectformula(data)
    	   }else if('joinLine'==formulaType){
    		   newFormItem.selectformula(data)
    	   }else if('handlerType'==formulaType||'optHandlerType'==formulaType){
    		   newFormItem.selectformula(data)
    	   }else{
    		   if(newFormItem.XMLNODENAME=='robotNode'||newFormItem.XMLNODENAME=='startSubProcessNode'||newFormItem.XMLNODENAME=='recoverSubProcessNode'){
    			   newFormItem.selectformula(data)
    		   }else{
    			   self.robotNodeContent(JSON.stringify(newFormItem.setRobotFormula(data)));
    		   }
    	   }
    	   self.formulaName(data.fdName); 
		
    	   self.nodeForm(newFormItem);
       }
       
       //流程模板回调方法
       self.selectTemplate=function(data){
    	   var newFormItem=self.nodeForm();
    	   newFormItem.selectTemplate(data);
       }
		 
       //组件人员组织回调方法
       self.selectNewElement=function(handlerIds,handlerNames){
    	   var newFormItem=self.nodeForm();
    	   if(newFormItem.XMLNODENAME=='authority'){
    		   var authorityInfo=ko.toJS(self.authorityInfo);
			   var reviewNodesArr=ko.toJS(self.reviewNodeData);
			   var robRow=parseInt(authorityInfo.substring(16));
			   var nodeObj=reviewNodesArr[robRow];
			   nodeObj.otherCanViewCurNodeIds=handlerIds;
			   nodeObj.otherCanViewCurNodeNames=handlerNames;
			   reviewNodesArr[robRow]=nodeObj;
			   self.reviewNodeData(reviewNodesArr);
				
			   return ;
    	   }else{
    		   newFormItem.selectNewElement(handlerIds,handlerNames);
    	   }
			
		   self.nodeForm(newFormItem); 
       }

       self.confirmRobot=function(data){
    	   var newFormItem=self.nodeForm();
    	   newFormItem.saveRobot(data);
    	   self.robotNodeContent(JSON.stringify(data));
       }
		 
       self.openWindow=function(type){
    	   var valueT=$('input:radio[name="'+type+'"]:checked').val();
    	   if(type=='handlerSelectType'||type=='optHandlerSelectType'){
    		   self.pattern('handler');
    		   param.nameFunc(ko.toJS(self.elementArray),ko.toJS(self.pattern));
    	   }else{
    		   self.pattern('group');
    	   }
			
    	   if(type=='otherCanViewCurNode'||valueT=='org'){
    		   $('#memberSelectWindow').modal();
    	   }else{
    		   var newFormItem=self.nodeForm();
    		   self.formulaName("");
    		   if(type=='handlerSelectType'){
    			   self.formulaName(newFormItem.handlerNames);
    		   }else{
    			   self.formulaName(newFormItem.optHandlerNames);
    		   }
				
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
    	   success:function(allData) {  
    		   var nodeName=allData.nodeNameSelectItem;
    		   if(null!=nodeName){
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
    	   success:function(data){
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
       
       self.computeReview=function(authorityInfo){
    	   self.authorityInfo(authorityInfo);
    	   var reviewArr=self.flowchart.getAllReviewNode();
    	   var arr=new Array();
    	   var robRow=parseInt(authorityInfo.substring(16));
    	   for(i=0;i<reviewArr.length;i++){
    		   if(i!=robRow){
    			   arr.push(reviewArr[i])
    		   }
    	   }
    	   
    	   var newFormItem=self.nodeForm();
    	   var reviewNodesArr=ko.toJS(self.reviewNodeData);
    	   var robRow=parseInt(authorityInfo.substring(16));
    	   var nodeObj=reviewNodesArr[robRow];
    	   self.selectNodes(newFormItem.getReviewNodes(nodeObj,authorityInfo));
    	   self.reviewArray(arr);
       }
       
       self.clearHandler=function(type){
  	       var newFormItem=self.nodeForm();
  	       if(type=='handlerType'){
  	    	   newFormItem.handlerIds='';
  	    	   newFormItem.handlerNames='';  
  	       }else{
  	    	   newFormItem.optHandlerIds='';
  	    	   newFormItem.optHandlerNames='';  
  	       }
  	       self.nodeForm(newFormItem);
       }
      
       param.data.subscribe(function(){
    	   var infoData=param.data()
    	   var type=infoData.type;
    	   if(type=="init"){
    		   var props=infoData.props;
    		   var attr=infoData.attr;
    		   self.reviewElement(infoData.reviewArray);
    		   self.subProcessNodes(infoData.subProcessArray);
    		   self.onPropEdit(props,attr);
    	   }else if(type=="member"){
    		   self.selectNewElement(infoData.handlerIds,infoData.handlerNames);
    	   }
   		})
	}
	
	return flowchartEdit;
})

//控制
function checkRadio(flag){
	var objArr=$('input:radio[name="passType"]');
	for(i=0;i<objArr.length;i++){
		objArr[i].disabled=flag;
	}
}

//控制
function selectJoinType(flag){
	var formula=$('#formula');
	if(flag){
		formula[0].style.display="block";
	}else{
		formula[0].style.display="none";
	}
}
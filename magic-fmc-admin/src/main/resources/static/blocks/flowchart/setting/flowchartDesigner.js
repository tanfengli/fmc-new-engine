requirejs.config({
	paths:{
		'Flowchart':'js/flowchart/Flowchart'
	}
});
var selectType;
define(['knockout','Flowchart',
         '../setting/vo/xmlNodeVO.js',
         '../setting/vo/lineVO.js', 			//线
         '../setting/vo/commonNodeVO.js',		//通用简单节点
         '../setting/vo/splitNodeVO.js',		//通用简单节点
         '../setting/vo/draftNodeVO.js',		//起草节点
         '../setting/vo/sendNodeVO.js',		//抄送节点
         '../setting/vo/reviewNodeVO.js',		//审批节点
         '../setting/vo/robotNodeVO.js',		//审批节点
         '../setting/vo/authorityVO.js',		//审批节点
         "components"],function(ko,Flowchart,XmlNode,Line,CommonNode,
		 SplitNode,DraftNode,SendNode,ReviewNode,RobotNode,AuthortyInfo){

	function flowchartDesigner(param){
		if (!ko.components.isRegistered("authorityNode")){
			ko.components.register('authorityNode',{
			    viewModel:{
			       require:'../blocks/flowchart/authorityNode/authorityNode.js'
			    },
			    template:{
			       require:'text!blocks/flowchart/authorityNode/authorityNode.html',
			    }
			}); 
		}	
		
		if(!ko.components.isRegistered("flowchartEdit")){
			ko.components.register('flowchartEdit',{
			    viewModel:{
			       require:'../blocks/flowchart/flowchartEdit/flowchartEdit.js'
			    },
			    template:{
			    	 require:'text!blocks/flowchart/flowchartEdit/flowchartEdit.html',
			    }
			}); 
		}	
		
		var self=this;
		
		self.editFlag=true;
		var xmlNodeInfo=new XmlNode();
		self.nodeForm=ko.observable(xmlNodeInfo);
	    //定义操作方式
	    self.xmlString='<process />'
	    self.elementArray=ko.observableArray([]);
	    self.pattern=ko.observable('group');
	    self.processShow=ko.observable(true);
	    if(param.privile!=undefined){
	    	self.processShow(false);
	    }
	    	
	    //公式参数
	    self.formulaName=ko.observable('');	
	    //公式回调类别 line条件线条 conditionLine条件分支
	    self.formulaType=ko.observable('');	
		//选择节点组件参数
	    self.reviewArray=ko.observableArray([]);
	    self.selectNodes=ko.observableArray([]);
	    //操作方式
	    self.operaterArr = ko.observableArray([]);
	    //机器人节点
	    self.robotNodeContent=ko.observable('');
	    self.nodeNames=ko.observableArray([]);
	    self.reviewNodeData=ko.observableArray([]);
	    self.authorityInfo=ko.observable('');
	    
	    self.flowchartData=ko.observable();
	    
		self.onPropEdit=function(props,attr){
			var obj=new Object();
			self.initReviewNodes(props.id);
			obj.props=props;
			obj.attr=attr;
			obj.type="init";
			obj.reviewArray=ko.toJS(self.reviewArray);
			obj.subProcessArray=ko.toJS(self.flowchart.getNodesByType("startSubProcessNode"));
			self.flowchartData(obj);
		};
		
		self.editWindowClose=function(props){
			if(props.XMLNODENAME=='autoBranchNode'||props.XMLNODENAME=='splitNode'){
				var attr=new Object();
				var lineArr=ko.toJS(props.lineOut);
				var tempData=ko.toJS(props.lineData);
				var result=new Array();
				var isConditionEmpty=true;
				
				for(i=0;i<tempData.length;i++){
					for(j=0;j<lineArr.length;j++){
						if(tempData[i].endNodeId==lineArr[j].data.endNodeId){
							result[i]=lineArr[j];
							result[i].data=tempData[i];
							break;
						}
					}
					
					if(tempData[i].condition!=""){
						isConditionEmpty=false;
					}
				}
			
				if(isConditionEmpty&&props.splitType!='all'){
					alert('条件公式不能全为空');
					
					return;
				}
				attr.lineOut=result;
				self.flowchart.updateprops(props,attr);
			}else if(props.XMLNODENAME=='reviewNode'||props.XMLNODENAME=='signNode'||
				props.XMLNODENAME=='startSubProcessNode'||props.XMLNODENAME=='recoverSubProcessNode'){
				props.saveData();
				self.flowchart.updateprops(props);
			}else if(props.XMLNODENAME=='robotNode'){
				props.saveRobot();
				self.flowchart.updateprops(props);
			}else{
				self.flowchart.updateprops(props);
			}
		
			$('#myModal').modal("hide");
			
			self.flowchart.setFocus();
		}
		
		self.flowchart=new Flowchart({
			flowchartId:'flowchart',
			editable:true,
			showprops:self.onPropEdit,
			mod:ko.observable()
		});
		
		self.processDefine=ko.mapping.fromJS(self.flowchart.processDefine);
		
		self.initFlowchart=function(){
			self.flowchart.init({xmlString:param.xmlString()});
			// 获取流程头信息
			self.processDefine.recalculateHandler(self.flowchart.processDefine.recalculateHandler);
			self.processDefine.rejectReturn(self.flowchart.processDefine.rejectReturn);
			self.processDefine.notifyOnFinish(self.flowchart.processDefine.notifyOnFinish);
			var notifyType = self.flowchart.processDefine.notifyType=='todo'?true:false;
			self.processDefine.notifyType(notifyType);
			self.processDefine.dayOfNotifyPrivileger(self.flowchart.processDefine.dayOfNotifyPrivileger);
			self.processDefine.notifyDraftOnFinish(self.flowchart.processDefine.notifyDraftOnFinish);
			self.processDefine.privilegerIds(self.flowchart.processDefine.privilegerIds);
			self.processDefine.privilegerNames(self.flowchart.processDefine.privilegerNames);
			self.processDefine.description(self.flowchart.processDefine.description);
		}
		
		self.checkFlow=function(){
			return self.flowchart.checkFlow();
		}
		
		self.getFlowchartXml=function(){
			//设置流程头信息
			//通知方式转化	
			var notifyType=self.processDefine.notifyType();
			notifyType=(notifyType==true||notifyType=='todo')?'todo':"";
			self.processDefine.notifyType(notifyType);
			self.flowchart.processDefine=ko.toJS(self.processDefine)
			
			return self.flowchart.getFlowchartXml();
		};
		
		if(param.editFlowchart){
			param.editFlowchart.chart=self;
		}
		
		if(param.xmlString()==null||param.xmlString() ==''){
			self.flowchart.clear(true);
		}else{
			self.initFlowchart();
		}
		
　　　　　this.subscription=param.xmlString.subscribe(function(val){	
			if(self.xmlString!=val){
				self.initFlowchart();
			}
　　　　　});
		
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
	
	   self.elementForm=ko.pureComputed(function(){
		   return{
			   elementArray:ko.toJS(self.elementArray),
			   pattern:ko.toJS(self.pattern)
		   };
	 	},this); 
       	
       	self.authorityForm=ko.pureComputed(function(){
       		return{
				reviewNodeData:ko.toJS(self.reviewNodeData)
			};
	  	},this);      
       	
		//组件人员组织回调方法
		self.selectNewElement=function(data,memberNames){
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
					var obj=new Object();
					obj.type="member";
					obj.handlerIds=handlerIds;
					obj.handlerNames=handlerNames;
					self.flowchartData(obj);
				}
			}
			
			self.flowchart.processDefine.ifSelect=false;
		}
	  
		self.authorityReview=function(authorityInfo){
			self.authorityInfo(authorityInfo);
			var objArr=new Array();
			var reviewNodes=ko.toJS(self.reviewNodeData);
			var robRow=parseInt(authorityInfo.substring(16));
			var dataObj=reviewNodes[robRow];
			if(dataObj.otherCanViewCurNodeIds!=undefined&&dataObj.otherCanViewCurNodeIds!=null&&dataObj.otherCanViewCurNodeIds!=''){
			   var arr=dataObj.otherCanViewCurNodeNames.split(";");
			   var fdIdArr=dataObj.otherCanViewCurNodeIds.split(";");
			   for(var i=0;i<arr.length;i++){
				  var obj=new Object();
				  obj.fdName=arr[i];
				  obj.fdId=fdIdArr[i];
				  objArr[i]=obj;
			   }
			}
			
			self.elementArray(objArr);
		}
	  
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
	  
		self.setAuthorty=function(data){
	   	  	var arr=self.flowchart.getAllReviewNode();
	   	  	var authortyInfo=new AuthortyInfo();
	   	  	authortyInfo.XMLNODENAME='authority';
	   	  	self.nodeForm(authortyInfo);
	   	  	var nodeArr=new Array();
	   	  	for(i=0;i<arr.length;i++){
	   		  nodeArr.push(arr[i]);
	   	  	}
	   	  	self.reviewNodeData(nodeArr);
	   	  	$('#authorityModel').modal();
		}
      
		//获取特权人
		self.setAuthority=function(data){
	   	  	var arr=self.flowchart.getAllReviewNode();
	   	  	var authortyInfo=new AuthortyInfo();
	   	  	authortyInfo.XMLNODENAME='authority';
	   	  	self.nodeForm(authortyInfo);
	   	  	var nodeArr=new Array();
	   	  	for(i=0;i<arr.length;i++){
	   	  		nodeArr.push(arr[i]);
	   	  	}
	   	  	self.reviewNodeData(nodeArr);
	   	  	$('#authorityModel').modal();
		}
      
		self.confirmAuthority=function(data){
   	   	  	for(i=0;i<data.length;i++){
   	   	  		self.flowchart.updateprops(data[i]);
   	   	  	}
		}
      
		self.initReviewNodes=function(nodeId){
			self.reviewArray.removeAll();
			var reviewArr=self.flowchart.getAllReviewNode();
			var objArr=new Array();
			var num=0;
			for(i=0;i<reviewArr.length;i++){
				if(reviewArr[i].id!=nodeId){
					objArr[num++]=reviewArr[i];
				}
			}
			self.reviewArray(objArr);
		}
      
	    self.setElement=function(objArr,pattern){
	    	self.elementArray(objArr);
	    	self.pattern(pattern);
	    } 
     
	    self.openWindow=function(type){
	    	var valueT=$('input:radio[name="'+type+'"]:checked').val();
	    	if(type=='handlerSelectType'||type=='optHandlerSelectType'){
	    		self.pattern('group');
	    	}else{
	    		self.pattern('handler');
	    	}
			
	    	if(type=='otherCanViewCurNode'||valueT=='org'){
	    		$('#memberSelectWindow').modal();
	    	}else{
	    		$('#formulaModal').modal();
	    	}
	    }
	}
	
	flowchartDesigner.prototype.dispose=function(){
		this.subscription.dispose();
	}
	
	return flowchartDesigner;
})
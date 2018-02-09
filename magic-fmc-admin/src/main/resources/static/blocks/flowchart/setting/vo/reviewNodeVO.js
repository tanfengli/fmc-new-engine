define(["knockout","MsgBox"],function(ko,MsgBox) { 
	ReviewNode=function(options) { 
		var setting=$.extend({ 
			XMLNODENAME:null,
			advice:[],
			bizInfoType:null,
			handlerIds:null,
			handlerNames:null,
			handlerSelectType:null,
			handlerSelectTypeObv:ko.observable(),
			hasInitSysOrgElment:null,
			
			id:null,
			ignoreOnHandlerEmpty:null,
			ignoreOnHandlerSame:null,
			name:null,
			operations:[],
			variables:[],
			optHandlerCalType:null,
			optHandlerSelectType:null,
			passPercent:null,
			passType:null,
			processType:null,
			recalculateHandler:null,
			useOptHandlerOnly:null,
			variants:[],
			x:null,
			y:null,
			
			canModifyFlow:null,
			canModifyNotionPopedom:null,
			ignoreOnSerialHandlerSame:null,
			
			optHandlerIds:null,
			optHandlerNames:null,
			canModifyHandlerNodeNames:null,
			canModifyHandlerNodeIds:null,
			mustModifyHandlerNodeNames:null,
			mustModifyHandlerNodeIds:null,
			nodeCanViewCurNodeNames:null,
			nodeCanViewCurNodeIds:null,
			otherCanViewCurNodeNames:null,
			otherCanViewCurNodeIds:null,
			description:null,
			
			tab:ko.observable(),
			selectType:ko.observable(),
			refIdVal:ko.observable(),
			operaterArr:ko.observableArray([]),
			operationsList:ko.observableArray([]),//自定义操作方式
			operationsListTwo:ko.observableArray([]),
			nodeNames:ko.observableArray([]),
			handlerOperations:ko.observableArray([]),
			creatorOperations:ko.observableArray([]),
			variablesArr:[],
			selectNodes:ko.observable(),
			robotNodeType:ko.observable()
		},options||{})
		
		return { 
			XMLNODENAME:setting.XMLNODENAME,
			advice:setting.advice,
			id:setting.id,
			name:setting.name,
			x:setting.x,
			y:setting.y,
			
			handlerIds:setting.handlerIds,
			handlerNames:setting.handlerNames,
			handlerSelectType:setting.handlerSelectType,
			hasInitSysOrgElment:setting.hasInitSysOrgElment,
			ignoreOnHandlerEmpty:setting.ignoreOnHandlerEmpty,
			ignoreOnHandlerSame:setting.ignoreOnHandlerSame,
			operations:setting.operations,
			variables:setting.variables,
			optHandlerCalType:setting.optHandlerCalType,
			optHandlerSelectType:setting.optHandlerSelectType,
			passType:setting.passType,
			processType:setting.processType,
			variablesArr:ko.observableArray(setting.variablesArr),
			recalculateHandler:setting.recalculateHandler,
			useOptHandlerOnly:setting.useOptHandlerOnly,
			variants:setting.variants,
			ignoreOnSerialHandlerSame:setting.ignoreOnSerialHandlerSame,
			handlerSelectType:setting.handlerSelectType,
			nodeDelay:setting.nodeDelay,
			bizInfoType:setting.bizInfoType,
			optHandlerIds:setting.optHandlerIds,
			optHandlerNames:setting.optHandlerNames,
			canModifyHandlerNodeNames:setting.canModifyHandlerNodeNames,
			canModifyHandlerNodeIds:setting.canModifyHandlerNodeIds,
			mustModifyHandlerNodeNames:setting.mustModifyHandlerNodeNames,
			mustModifyHandlerNodeIds:setting.mustModifyHandlerNodeIds,
			nodeCanViewCurNodeNames:setting.nodeCanViewCurNodeNames,
			nodeCanViewCurNodeIds:setting.nodeCanViewCurNodeIds,
			otherCanViewCurNodeNames:setting.otherCanViewCurNodeNames,
			otherCanViewCurNodeIds:setting.otherCanViewCurNodeIds,
			canModifyNotionPopedom:setting.canModifyNotionPopedom,
			canModifyFlow:setting.canModifyFlow,
			operationType:setting.operationType,
			creatorOperation_type:setting.creatorOperation_type,
			handlerOperation_type:setting.handlerOperation_type,
			selectType:setting.selectType,
			operater:setting.operater,
			condition:setting.condition,
			disCondition:setting.disCondition,
			elementArray:setting.elementArray,
			
			description :setting.description ,
			passPercent:setting.passPercent,
			
			advice:setting.advice,
			refIdVal:setting.refIdVal,
			
			tab:setting.tab,
			selectType:setting.selectType,
			operaterArr:setting.operaterArr,
			nodeNames:setting.nodeNames,
			operationsList:setting.operationsList,
			operationsListTwo:setting.operationsListTwo,
			handlerOperations:setting.handlerOperations,
			creatorOperations:setting.creatorOperations,
			selectNodes:setting.selectNodes,
			robotNodeType:setting.robotNodeType,
			setTab:function(value){
	    	   this.tab=value;
		    },
		    getTab:function(){
		    	return ko.toJS(this.tab);
		    },
		    setSelectType:function(value){
		    	this.selectType(value);
		    },
		    getProcessType:function(){
		    	return this.processType!='2';
		    },
		    setProcessType:function(value){
		    	this.processType=value;
		    },
		    ignoreOnSerialHandlerSameClick:function(data,event){
		    	if(event.target.checked){
		    		this.ignoreOnSerialHandlerSame=true;
		    	}else{
		    		this.ignoreOnSerialHandlerSame=false;
		    	}
		    	
		    	return true;
		    },
		    ignoreOnHandlerSameClick:function(){
		    	if(event.target.checked){
		    		this.ignoreOnHandlerSame=true;
		    	}else{
		    		this.ignoreOnHandlerSame=false;
		    	}
		    	
		    	return  true;
		    },
		    //定义组件回调函数
			//群组选择返回
			selectNewElement:function(handlerIds,handlerNames){ 
				var selectType=ko.toJS(this.selectType);
				if(selectType=='handlerType'){    
					this.handlerIds=handlerIds;
					this.handlerNames=handlerNames;  
				}else if(selectType=='optHandlerType'){
					this.optHandlerIds=handlerIds;
					this.optHandlerNames=handlerNames;  
				}else if(selectType=='lineCondition'){
					this.condition=handlerIds;
					this.disCondition=data.fdName;  
				}else if(selectType=='otherCanViewCurNode'){
					this.otherCanViewCurNodeIds=handlerIds;
					this.otherCanViewCurNodeNames=handlerNames;  
				}  
			},
			initData:function(){
				if(this.handlerSelectType==undefined||this.handlerSelectType==''){
					this.handlerSelectType='org';
				}
				
				if(this.processType==undefined||this.processType==''){
					this.processType='0';
				}
				
				if(this.optHandlerSelectType==undefined||this.optHandlerSelectType==''){
					this.optHandlerSelectType='org';
				}
				
				if(this.optHandlerCalType==undefined||this.optHandlerCalType==''){
					this.optHandlerCalType='2';
				}
				
				if(this.canModifyFlow==undefined||this.canModifyFlow==''){
					this.canModifyFlow='false';
				}
				
				if(this.canModifyNotionPopedom==undefined||this.canModifyNotionPopedom==''){
					this.canModifyNotionPopedom='false';
				}
				
				if(this.ignoreOnHandlerEmpty=='true'||this.ignoreOnHandlerEmpty==true){
					this.ignoreOnHandlerEmpty=true;
				}else{
					this.ignoreOnHandlerEmpty=false;
				}
				
				if(this.ignoreOnSerialHandlerSame=='true'||this.ignoreOnSerialHandlerSame==true){
					this.ignoreOnSerialHandlerSame=true;
				}else{
					this.ignoreOnSerialHandlerSame=false;
				}
				
				if(this.ignoreOnHandlerSame=='true'||this.ignoreOnHandlerSame==true){
					this.ignoreOnHandlerSame=true;
				}else{
					this.ignoreOnHandlerSame=false;
				}
				
				if(this.recalculateHandler=='false'||this.recalculateHandler==false){
					this.recalculateHandler=false;
				}else{
					this.recalculateHandler=true;
				}
				
				if(this.useOptHandlerOnly=='true'||this.useOptHandlerOnly==true){
					this.useOptHandlerOnly=true;
				}else{
					this.useOptHandlerOnly=false;
				}
				
				this.initVariables();
			},
			initOperation:function(operaterArr,nodeNames){
				this.operaterArr.removeAll();
				this.nodeNames.removeAll();
				this.creatorOperations.removeAll();
				this.handlerOperations.removeAll();
				this.operationsList.removeAll();
				this.operationsListTwo.removeAll();
				this.nodeNames(nodeNames);
			    //初始化操作方式列表
				for(i=0;i<operaterArr.length;i++){
					if(this.XMLNODENAME=='signNode'){
						if(operaterArr[i].fdNodeType=='1')
							this.operaterArr.push(operaterArr[i])
					}else {
						if(operaterArr[i].fdNodeType=='0'){
							this.operaterArr.push(operaterArr[i])
						}
					}
				}
				//设置默认值
				var refIdVal='';
				var operaterArrT=ko.toJS(this.operaterArr);
				if(this.operations.refId==undefined||this.operations.refId==null||this.operations.refId==''){
					if(this.operations.length==0){
						for(i=0;i<operaterArrT.length;i++){
							if(this.XMLNODENAME=='signNode'){
								if(operaterArrT[i].fdNodeType=='1'){
									refIdVal=operaterArrT[i].fdId;
									break;
								}
							}else{
								if(operaterArrT[i].fdNodeType=='0'){
									this.operaterArr.push(operaterArrT[i])
									refIdVal=operaterArrT[i].fdId;
									break;
								}
							}
						}
					}else{
						this.handlerOperations.removeAll();
						this.creatorOperations.removeAll()
				    	for(o=0;o<this.operations.length;o++){
				    		 var obj=this.operations[o];
				    		 var operObj=new Object();
			    			 operObj.name=obj.name;
			    			 operObj.type=obj.type;
			    			 operObj.XMLNODENAME=obj.XMLNODENAME;
				    		 if(obj.XMLNODENAME=='handlerOperation'){
				    			 this.handlerOperations.push(operObj)
				    		 }else{
				    			 this.creatorOperations.push(operObj);
				    		 }
				    	}
					}
				}else{
					refIdVal=this.operations.refId;
				} 
				//设置下拉列表
				if(refIdVal!=''){
					for(i=0;i<operaterArrT.length;i++){
			    		if(operaterArrT[i].fdId==refIdVal){
			    			 for(j=0;j<operaterArrT[i].operationsList.length;j++){
			    				 this.operationsList.push(operaterArrT[i].operationsList[j]);
			    			 }
			    			 for(j=0;j<operaterArrT[i].operationsListTwo.length;j++){
			    				 this.operationsListTwo.push(operaterArrT[i].operationsListTwo[j]);
			    			 }
			    			 break;
			    		}
			    	}
				}
				
				this.refIdVal(refIdVal);
			},
			initVariables:function(){
				this.variablesArr.removeAll();
				ko.utils.arrayPushAll(this.variablesArr,this.variables);
			},
			getReviewNodes:function(){
		    	var selectType=ko.toJS(this.selectType);
		    	if(selectType=='canModifyHandlerNode'){ 
					if(this.canModifyHandlerNodeNames!=null&&this.canModifyHandlerNodeNames!=''){
						return this.canModifyHandlerNodeNames.split(";");
					}
				}else if(selectType=='mustModifyHandlerNode'){
					if(this.mustModifyHandlerNodeNames!=null&&this.mustModifyHandlerNodeNames!=''){
						return this.mustModifyHandlerNodeNames.split(";");
					}
				}else if(selectType=='nodeCanViewCurNode'){
					if(this.nodeCanViewCurNodeNames!=null&&this.nodeCanViewCurNodeNames!=''){
						return this.nodeCanViewCurNodeNames.split(";");
					}
				}else if(selectType=='otherCanViewCurNode'){
					if(this.otherCanViewCurNodeNames!=null&&this.otherCanViewCurNodeNames!=''){
						return this.otherCanViewCurNodeNames.split(";");
					}
				}
		    	
		    	return new Array();
	    	},
			setReviewNodes:function(dataArr){
		    	var handlerIds="";
				var handlerNames="";
				var selectType=ko.toJS(this.selectType);
				for(i=0;i<dataArr.length;i++){
					if(i==dataArr.length-1){
						handlerIds=handlerIds+dataArr[i].split(".")[0];
						handlerNames=handlerNames+dataArr[i];
					}else{
						handlerIds=handlerIds+dataArr[i].split(".")[0]+";";
						handlerNames=handlerNames+dataArr[i]+";";
					}
				}
				
				if(selectType=='canModifyHandlerNode'){    
					this.canModifyHandlerNodeIds=handlerIds;
					this.canModifyHandlerNodeNames=handlerNames; 
				}else if(selectType=='mustModifyHandlerNode'){
					this.mustModifyHandlerNodeIds=handlerIds;
					this.mustModifyHandlerNodeNames=handlerNames;  
				}else if(selectType=='nodeCanViewCurNode'){
					this.nodeCanViewCurNodeIds=handlerIds;
					this.nodeCanViewCurNodeNames=handlerNames;  
				}else if(selectType=='otherCanViewCurNode'){
					this.otherCanViewCurNodeIds=handlerIds;
					this.otherCanViewCurNodeNames=handlerNames;  
				}
		    },
			getRefId:function(){
				 var refIdVal=ko.toJS(this.refIdVal);
				 if(refIdVal==undefined||refIdVal==null||refIdVal==''){
					 return false;
				 }else{
					 return true;
				 }
			},
			changeOperList:function(){
				 var refIdVal=ko.toJS(this.refIdVal);
				 if(refIdVal!=undefined&&refIdVal!=null&&refIdVal!=''){
					 this.operationsList.removeAll();
					 this.operationsListTwo.removeAll();
					 var operaterArrT = ko.toJS(this.operaterArr);
					 for(i=0;i<operaterArrT.length;i++){
				    		if(operaterArrT[i].fdId==refIdVal){
				    			 for(j=0;j<operaterArrT[i].operationsList.length;j++){
				    				 this.operationsList.push(operaterArrT[i].operationsList[j]);
				    			 }
				    			 for(j=0;j<operaterArrT[i].operationsListTwo.length;j++){
				    				 this.operationsListTwo.push(operaterArrT[i].operationsListTwo[j]);
				    			 }
				    		}
				    }
				 }
			},
			addOperation:function(value){
				if(value=="handlerOperation"){
	        		this.handlerOperations.push({XMLNODENAME:value,name:"通过",type:'101'}); 
	        		console.info(this.handlerOperations())
	        	}else{
	        		this.creatorOperations.push({XMLNODENAME:value,name:"催办",type:'202'}); 
	        	}
				
			},
			deleteOper:function(value){
				 var robRow = parseInt(value.substring(16));
		    	if(value.indexOf('handlerOperation')>-1){
		    		var operArr =ko.toJS(this.handlerOperations)
		    		this.handlerOperations.removeAll();
		 			for(i=0;i<operArr.length;i++){
		 				if(i!=robRow){
		 					this.handlerOperations.push(operArr[i]);
		 				}
		 			}
		    	}else{
		    		var operArr =ko.toJS(this.creatorOperations)
		    		this.creatorOperations.removeAll();
		    		for(i=0;i<operArr.length;i++){
		  				if(i!=robRow){
		  					this.creatorOperations.push(operArr[i]);
		  				}
		  			}
		    	}
			},
			addVariable:function(){
        		this.variablesArr.push({XMLNODENAME:"variableDefined",name:"",value:""}); 
			},
			deleteVariable:function(variable){
				this.variablesArr.remove(variable)
			},
			upVariable:function(index){
				var swapItems = function(arr, index1, index2) {
			        arr[index1] = arr.splice(index2, 1, arr[index1])[0];
			        return arr;
			    };
			    if(null==index() || 0 == index()) {
		            return;
		        }
			    this.variablesArr(swapItems(this.variablesArr(), index(), index() - 1))
			    
			},
			downVariable:function(index){
				var swapItems = function(arr, index1, index2) {
			        arr[index1] = arr.splice(index2, 1, arr[index1])[0];
			        return arr;
			    };
			    if(null==index() || index() == this.variablesArr().length -1) {
		            return;
		        }
			    this.variablesArr(swapItems(this.variablesArr(), index(), index() + 1))
			},
			saveData:function(){
				this.operations.length=0;
				this.operations.XMLNODENAME='operations';
				this.operations.refId=ko.toJS(this.refIdVal);
				var arr = new Array();
				if(ko.toJS(this.refIdVal)==''||ko.toJS(this.refIdVal)==undefined){
					var operArr=ko.toJS(this.handlerOperations)
					var num=0;
		 			for(i=0;i<operArr.length;i++){
		 				this.operations.push(operArr[i]);
		 				num=i;
		 			}
		 			
		 			var operArr2=ko.toJS(this.creatorOperations)
		    		for(i=0;i<operArr2.length;i++){
		    			this.operations.push(operArr2[i]);
		  			}
				}
				
				//自定义属性
				this.variables=this.variablesArr();
			},
			selectNodeName:function(value){
			},
			removeRobot:function(value){
				var arrType=new Array();
				arrType.XMLNODENAME=value;
				var advice=this.advice;
				advice.CHILDRENISARRAY="false";
				advice.XMLNODENAME='advice';
				var msg='';
				if(value=='before'){
					advice.before=arrType;
					msg="前置逻辑";
				}else if(value=='after'){
					advice.after=arrType;
					msg="后置逻辑";
				}
				this.advice=advice;
				MsgBox.info(msg+"数据删除成功.");
			},
			getRobotContent:function(value){
				 this.robotNodeType(value);
				 var adviceArr=this.advice;
				 var robotObj=new Object();
				 robotObj.type='init'
				 robotObj.robotType=value;
				 if(value=='before'){
					 if(adviceArr.before.length==0){
						 robotObj.type='newInit';
					 }else{
						 robotObj.content=adviceArr.before[0].content;
						 robotObj.unid=adviceArr.before[0].unid;
					 }
					 
					 return JSON.stringify(robotObj);
				 }else if(value=='after'){
					 if(adviceArr.after.length==0){
						 robotObj.type='newInit';
					 }else{
						 robotObj.content=adviceArr.after[0].content;
						 robotObj.unid=adviceArr.after[0].unid;
					 }
					 
					 return JSON.stringify(robotObj);
				 }
			},
			saveRobot:function(data){
				var arr1=new Array();
				arr1.unid=data.unid;
				arr1.content=data.content;
				arr1.XMLNODENAME=data.XMLNODENAME;
				
				var arrType=new Array();
				arrType.XMLNODENAME=ko.toJS(this.robotNodeType);
				arrType.push(arr1);
			 
				var advice=this.advice;
				advice.CHILDRENISARRAY="false";
				advice.XMLNODENAME='advice';
				if(ko.toJS( this.robotNodeType)=='before'){
					advice.before=arrType;
				}else if(ko.toJS(this.robotNodeType)=='after'){
					advice.after=arrType;
				}
				this.advice=advice;
			 },
			 setRobotFormula:function(data){
				 var robotObj2=new Object()
				 robotObj2.type='formula';
				 robotObj2.idField=data.fdId;
				 robotObj2.nameField=data.fdName;
				 
				 return robotObj2; 
			 },
			 selectformula:function(data){
				 if(ko.toJS(this.selectType)=='handlerType'){
					 this.handlerIds=data.fdId;
					 this.handlerNames=data.fdName; 
				 }else{
					 this.optHandlerIds=data.fdId;
					 this.optHandlerNames=data.fdName;  
				 }
			 },
			 setFormula:function(){
				 if(ko.toJS(this.selectType)=='handlerType'){
					 return this.handlerNames; 
				 }else{
					 return this.optHandlerNames;
				 }
			 }
		};
	};
	
	return ReviewNode;
})

define(["knockout"],function(ko) { 
	 
	SendNode = function(options) { 
		var setting = $.extend({ 
			XMLNODENAME:  null,
			advice : [],
			handlerIds:null,
			handlerNames:null,
			handlerSelectType:null,
			hasInitSysOrgElment:null,
			id:null,
			ignoreOnHandlerEmpty:null,
			name : null,
			optHandlerCalType:null,
			optHandlerIds:null,
			optHandlerNames:null,
			optHandlerSelectType:null,
			useOptHandlerOnly:null,
			x : null,
			y : null,
			description:null,
			recalculateHandler:null,
			
			tab:ko.observable(),
			selectType:ko.observable()
			 
		},options || {})
		
		return { 
			XMLNODENAME :  setting.XMLNODENAME,
			advice : setting.advice,
			handlerIds:setting.handlerIds,
			handlerNames:setting.handlerNames,
			handlerSelectType:setting.handlerSelectType,
			hasInitSysOrgElment:setting.hasInitSysOrgElment,
			id:setting.id,
			ignoreOnHandlerEmpty:setting.ignoreOnHandlerEmpty,
			name : setting.name,
			optHandlerCalType:setting.optHandlerCalType,
			optHandlerIds:setting.optHandlerIds,
			optHandlerNames:setting.optHandlerNames,
			optHandlerSelectType:setting.optHandlerSelectType,
			useOptHandlerOnly:setting.useOptHandlerOnly,
			x : setting.x,
			y : setting.y,
			description:setting.description,
			recalculateHandler:setting.recalculateHandler,
			
			tab:setting.tab,
			selectType:setting.selectType,
			
			
			setTab:function(value){
		    	   this.tab=value;
		    },
		       
		    getTab:function(){
		    	   return ko.toJS(this.tab);
		    },
		    
		    setSelectType:function(value){
		    	  this.selectType(value);
		    },
		    initData:function(){
				if(this.handlerSelectType==undefined||this.handlerSelectType==''){
					this.handlerSelectType='org';
				}
				
				if(this.optHandlerSelectType==undefined||this.optHandlerSelectType==''){
					this.optHandlerSelectType='org';
				}
				
			},
		    //定义组件回调函数
			//群组选择返回
			selectNewElement: function(handlerIds,handlerNames){ 
				var selectType = ko.toJS(this.selectType);
				if(selectType == 'handlerType'){    
					this.handlerIds=handlerIds;
					this.handlerNames=handlerNames;  
				}else if(selectType == 'optHandlerType'){
					this.optHandlerIds=handlerIds;
					this.optHandlerNames=handlerNames;  
				}else if(newFormItem.selectType == 'lineCondition'){
					this.condition=handlerIds;
					this.disCondition=data.fdName;  
				}else if(newFormItem.selectType == 'otherCanViewCurNode'){
					this.otherCanViewCurNodeIds=handlerIds;
					this.otherCanViewCurNodeNames=handlerNames;  
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
	
	return SendNode;
})

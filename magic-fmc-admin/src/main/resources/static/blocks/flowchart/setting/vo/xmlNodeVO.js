define(["knockout"],function(ko) { 
	 
	XMLNode = function(options) { 
		var setting = $.extend({ 
			XMLNODENAME:  null,
			canModifyFlow:null,
			canModifyNotionPopedom:null,
			handlerIds:null,
			handlerNames:null,
			handlerSelectType:null,
			hasInitSysOrgElment:null,
			ignoreOnHandlerEmpty:null,
			ignoreOnHandlerSame:null,
			operations:[],
			optHandlerCalType:null,
			optHandlerSelectType:null,
			passType:null,
			processType:null,
			recalculateHandler:null,
			useOptHandlerOnly:null,
			variants:[],
			advice : [],
			id : null,
			name : null,
			x : null,
			y : null,
			
			ignoreOnSerialHandlerSame:null,
			ignoreOnHandlerSame:null,
			handlerSelectType:null,
			nodeDelay:null,
			bizInfoType:null,
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
			canModifyNotionPopedom:null,
			canModifyFlow:null,
			operationType:null,
			creatorOperation_type:[],
			handlerOperation_type:[],
			splitType:null,
			operater:[],
			
			condition:null,
			discondition:null,
			
			elementArray:[],
			
			description :null,
			joinType:null,
			formulaJoinTypeText:null,
			formulaJoinTypeValue:null,
			rejectNotifyDraft:null,
			content:null,
			unid:null,
			advice:[],
			
			refId:null,
			tab:null,
			splitFlag:ko.observable(),
			passPercent:null
			
			 
		},options || {})
		
		return { 
			XMLNODENAME :  setting.XMLNODENAME,
			advice : setting.advice,
			id : setting.id,
			name : setting.name,
			x : setting.x,
			y : setting.y,
			
			canModifyFlow: setting.canModifyFlow,
			canModifyNotionPopedom: setting.canModifyNotionPopedom,
			handlerIds: setting.handlerIds,
			handlerNames: setting.handlerNames,
			handlerSelectType: setting.handlerSelectType,
			hasInitSysOrgElment: setting.hasInitSysOrgElment,
			ignoreOnHandlerEmpty: setting.ignoreOnHandlerEmpty,
			ignoreOnHandlerSame: setting.ignoreOnHandlerSame,
			operations: setting.operations,
			optHandlerCalType: setting.optHandlerCalType,
			optHandlerSelectType: setting.optHandlerSelectType,
			passType: setting.passType,
			processType: setting.processType,
			recalculateHandler: setting.recalculateHandler,
			useOptHandlerOnly: setting.useOptHandlerOnly,
			variants: setting.variants,
			ignoreOnSerialHandlerSame:setting.ignoreOnSerialHandlerSame,
			ignoreOnHandlerSame:setting.ignoreOnHandlerSame,
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
			operater:setting.operater,
			condition:setting.condition,
			disCondition:setting.disCondition,
			elementArray:setting.elementArray,
			
			description :setting.description ,
			joinType:setting.joinType ,
			formulaJoinTypeText:setting.formulaJoinTypeText ,
			formulaJoinTypeValue:setting.formulaJoinTypeValue,
			rejectNotifyDraft:setting.rejectNotifyDraft,
			splitType:setting.splitType,
			content:setting.content,
			unid:setting.unid,
			
			advice:setting.advice,
			refId:setting.refId,
			passPercent :setting.passPercent,
			tab:setting.tab,
			splitFlag:setting.splitFlag,
			
			setTab:function(value){
		    	   this.tab=value;
		    },
		       
		    getTab:function(){
		    	   return this.tab;
		    },
		    
		    getProcessType:function(){
		    	   return this.processType!='2';
		    },
		    setProcessType:function(value){
		    	   this.processType=value;
		    },
		    
		    setSplitType:function(value){
		    	
		    	   this.splitFlag(value);
		    },
		       
		    getSplitType:function(){
		    	   return this.splitType;
		    }
		};   
		  
	};
	
	return XMLNode;
})

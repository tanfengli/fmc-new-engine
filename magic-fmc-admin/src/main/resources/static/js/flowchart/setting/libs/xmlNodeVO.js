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
			handlerSelectType:null,
			nodeDelay:null,
			bizInfoType:null,
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
			selectType : null,
			splitType:null,
			operater:[],
			
			condition:null,
			discondition:null,
			
			elementArray:[],
			reviewNodes:[],
			
			selectNodes:[],
			operationsList:[],
			operationsListTwo:[],
			
			description :null,
			joinType:null,
			formulaJoinTypeText:null,
			formulaJoinTypeValue:null,
			rejectNotifyDraft:null,
			content:null,
			unid:null,
			advice:[],
			
			tab:null
			
			 
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
			handlerSelectType:setting.handlerSelectType,
			nodeDelay:setting.nodeDelay,
			bizInfoType:setting.bizInfoType,
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
			reviewNodes:setting.reviewNodes,
			selectNodes:setting.selectNodes,
			operationsList:setting.operationsList,
			operationsListTwo:setting.operationsListTwo,
			
			
			description :setting.description ,
			joinType:setting.joinType ,
			formulaJoinTypeText:setting.formulaJoinTypeText ,
			formulaJoinTypeValue:setting.formulaJoinTypeValue,
			rejectNotifyDraft:setting.rejectNotifyDraft,
			splitType:setting.splitType,
			content:setting.content,
			unid:setting.unid,
			
			advice:setting.advice,
			
			tab:setting.tab,
			
			setTab:function(value){
		    	   this.tab=value;
		    },
		       
		    getTab:function(){
		    	   return this.tab;
		    }
		};   
		  
	};
	
	return XMLNode;
})

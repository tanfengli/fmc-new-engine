define(["knockout"],function(ko) { 
	
	DraftNode = function(options) { 
		var setting = $.extend({ 
			XMLNODENAME:  null,
			advice : [],
			canModifyFlow:'false',
			canModifyHandlerNodeIds:null,
			canModifyNotionPopedom:'false',
			description:null,
			id:null,
			mustModifyHandlerNodeIds:null,
			name:null,
			nodeCanViewCurNodeIds:null,
			nodeCanViewCurNodeNames:null,
			otherCanViewCurNodeIds:null,
			otherCanViewCurNodeNames:null,
			rejectNotifyDraft:null,
		
			x : null,
			y : null,
			canModifyHandlerNodeNames:null,
			mustModifyHandlerNodeNames:null,
			tab:ko.observable(),
			selectType:ko.observable()
		},options || {})
		
		return { 
			XMLNODENAME :  setting.XMLNODENAME,
			advice : setting.advice,
			canModifyFlow: setting.canModifyFlow,
			canModifyHandlerNodeIds: setting.canModifyHandlerNodeIds,
			canModifyNotionPopedom: setting.canModifyNotionPopedom,
			description: setting.description,
			id: setting.id,
			mustModifyHandlerNodeIds: setting.mustModifyHandlerNodeIds,
			name: setting.name,
			nodeCanViewCurNodeIds: setting.nodeCanViewCurNodeIds,
			nodeCanViewCurNodeNames: setting.nodeCanViewCurNodeNames,
			otherCanViewCurNodeIds: setting.otherCanViewCurNodeIds,
			otherCanViewCurNodeNames: setting.otherCanViewCurNodeNames,
			rejectNotifyDraft: setting.rejectNotifyDraft,
		
			x :  setting.x,
			y :  setting.y,
			
			canModifyHandlerNodeNames: setting.canModifyHandlerNodeNames,
			mustModifyHandlerNodeNames: setting.mustModifyHandlerNodeNames,
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
		    getReviewNodes:function(){
		    	var selectType = ko.toJS(this.selectType);
		    	if(selectType == 'canModifyHandlerNode'){ 
					if(this.canModifyHandlerNodeNames!=null)
						return this.canModifyHandlerNodeNames.split(";");
				}else if(selectType == 'mustModifyHandlerNode'){
					if(this.mustModifyHandlerNodeNames!=null)
						return this.mustModifyHandlerNodeNames.split(";");
				}else if(selectType == 'nodeCanViewCurNode'){
					if(this.nodeCanViewCurNodeNames!=null)
						return this.nodeCanViewCurNodeNames.split(";");
				}else if(selectType == 'otherCanViewCurNode'){
					if(this.otherCanViewCurNodeNames!=null)
						return this.otherCanViewCurNodeNames.split(";");
				}
		    	return new Array();
		    },
		    setReviewNodes:function(dataArr){
		    	var handlerIds="";
				var handlerNames="";
				var selectType = ko.toJS(this.selectType);
				for(i=0;i<dataArr.length;i++){
					if(i==dataArr.length-1){
						handlerIds=handlerIds+dataArr[i].split(".")[0];
						handlerNames=handlerNames+dataArr[i];
					}else{
						handlerIds=handlerIds+dataArr[i].split(".")[0]+";";
						handlerNames=handlerNames+dataArr[i]+";";
					}
				}
				if(selectType == 'canModifyHandlerNode'){    
					this.canModifyHandlerNodeIds=handlerIds;
					this.canModifyHandlerNodeNames=handlerNames; 
				}else if(selectType == 'mustModifyHandlerNode'){
					this.mustModifyHandlerNodeIds=handlerIds;
					this.mustModifyHandlerNodeNames=handlerNames;  
				}else if(selectType == 'nodeCanViewCurNode'){
					this.nodeCanViewCurNodeIds=handlerIds;
					this.nodeCanViewCurNodeNames=handlerNames;  
				}else if(selectType == 'otherCanViewCurNode'){
					this.otherCanViewCurNodeIds=handlerIds;
					this.otherCanViewCurNodeNames=handlerNames;  
				}
		    },
		    selectNewElement: function(handlerIds,handlerNames){ 
		    	this.otherCanViewCurNodeIds=handlerIds;
				this.otherCanViewCurNodeNames=handlerNames;  
		    }
		};   
	};
	
	return DraftNode;
})

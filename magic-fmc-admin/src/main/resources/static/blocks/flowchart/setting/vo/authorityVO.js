define(["knockout"],function(ko) { 
	
	AuthorityInfo = function(options) { 
		var setting = $.extend({ 
			XMLNODENAME:  null,
			id:null,
			name : null,
			selectType:ko.observable()
		},options || {})
		
		return { 
			XMLNODENAME :  setting.XMLNODENAME,
			id:setting.id,
			name:setting.name,
			selectType:setting.selectType,
			
			getReviewNodes:function(nodeObj,authorityInfo){
				if(authorityInfo.indexOf('CanModi')>-1){
					if(nodeObj.canModifyHandlerNodeNames!=null
							&&nodeObj.canModifyHandlerNodeNames!=undefined
							&&nodeObj.canModifyHandlerNodeNames!="")
						return  nodeObj.canModifyHandlerNodeNames.split(";");
				}else if(authorityInfo.indexOf('MustMod')>-1){
					if(nodeObj.mustModifyHandlerNodeNames!=null
							&&nodeObj.mustModifyHandlerNodeNames!=undefined
							&&nodeObj.mustModifyHandlerNodeNames!="")
						return  nodeObj.mustModifyHandlerNodeNames.split(";");
				}else if(authorityInfo.indexOf('CanView')>-1){
					if(nodeObj.nodeCanViewCurNodeNames!=null
							&&nodeObj.nodeCanViewCurNodeNames!=undefined
							&&nodeObj.nodeCanViewCurNodeNames!="")
						return  nodeObj.nodeCanViewCurNodeNames.split(";");
				}
		    	return new Array();
		    },
		setReviewNodes:function(dataArr,nodeObj,authorityInfo){
	    	var handlerIds="";
			var handlerNames="";
			for(i=0;i<dataArr.length;i++){
				if(i==dataArr.length-1){
					handlerIds=handlerIds+dataArr[i].split(".")[0];
					handlerNames=handlerNames+dataArr[i];
				}else{
					handlerIds=handlerIds+dataArr[i].split(".")[0]+";";
					handlerNames=handlerNames+dataArr[i]+";";
				}
			}
			if(authorityInfo.indexOf('CanModi')>-1){
				nodeObj.canModifyHandlerNodeIds=handlerIds;
				nodeObj.canModifyHandlerNodeNames=handlerNames;
			}else if(authorityInfo.indexOf('MustMod')>-1){
				nodeObj.mustModifyHandlerNodeIds=handlerIds;
				nodeObj.mustModifyHandlerNodeNames=handlerNames;
			}else if(authorityInfo.indexOf('CanView')>-1){
				nodeObj.nodeCanViewCurNodeIds=handlerIds;
				nodeObj.nodeCanViewCurNodeNames=handlerNames;
			}
			return nodeObj;
	    },
	    selectNewElement:function(handlerIds,handlerNames,nodeObj,authorityInfo){ 
	    	
	    }
			
		};   
	};
	
	return AuthorityInfo;
})

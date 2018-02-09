define(["knockout"],function(ko) { 
	
	SplitNode = function(options) { 
		var setting = $.extend({ 
			XMLNODENAME:  null,
			advice : [],
			id:null,
			name:null,
			relatedNodeIds:null,
			splitType:null,
			joinType:null,
			formulaJoinTypeValue:null,
			formulaJoinTypeText:null,
			x : null,
			y : null,
			relatedNodeIds:null,
			
			lineOut:ko.observableArray([]),
			lineData:ko.observableArray([]),
			index:ko.observable(),
			splitFlag:ko.observable(),
			joinFlag:ko.observable(),
			formulaName : ko.observable()//公式参数
		},options || {})
		
		return { 
			XMLNODENAME :  setting.XMLNODENAME,
			advice : setting.advice,
			id : setting.id,
			name : setting.name,
			relatedNodeIds : setting.relatedNodeIds,
			splitType : setting.splitType,
			joinType : setting.joinType,
			formulaJoinTypeText:setting.formulaJoinTypeText,
			formulaJoinTypeValue:setting.formulaJoinTypeValue,
			x  :  setting.x ,
			y : setting.y,
			relatedNodeIds:setting.relatedNodeIds,
			
			lineOut : setting.lineOut,
			lineData : setting.lineData,
			index:setting.index,
			formulaName:setting.formulaName,
			splitFlag:setting.splitFlag,
			joinFlag:setting.joinFlag,
			
			setLineData :function(attr){
				if(attr!=undefined&&attr.lineOut!=undefined&&attr.lineOut!=null){
					this.lineOut(attr.lineOut);
					var lineArr = attr.lineOut;
					var tempArr = new Array();
					for(i=0;i<lineArr.length;i++){
						tempArr[i]=lineArr[i].data;
						var endNode = lineArr[i].getEndNode();
						if(tempArr[i].disCondition==undefined){
							tempArr[i].disCondition='';
							tempArr[i].condition='';
						}
						tempArr[i].endNodeName=endNode.data.name+"("+endNode.data.id+")";
					}
					this.lineData(tempArr);
				}
			},
			
			selectformula:function(data){
				var index = ko.toJS(this.index);
				if(index!=undefined){
					 var lineData = ko.toJS(this.lineData);
					 var num = parseInt(ko.toJS(this.index))
					 var lineObj = lineData[num];
					 lineObj.disCondition=data.fdName;
					 lineObj.condition=data.fdId;
					 lineData[num]=lineObj;
					 this.lineData(lineData);
				 }
			},
			setIndex : function(index){
				this.index(index);
			},
			
			setSplitType:function(value){
			    	
		    	   this.splitFlag(value);
		    },
		       
		    getSplitType:function(){
		    	   return this.splitType;
		    },
		    initData : function(){
		    	if(this.splitType==null||this.splitType==undefined){
		    		this.splitType='all';
		    	}
		    	if(this.joinType==null||this.joinType==undefined){
		    		this.joinType='all';
		    	}
		    },
		    selectJoinFlag:function(flag){
		    	 this.joinFlag(flag);
		    },
		    
		    setFormula : function(index){
				 var lineData = ko.toJS(this.lineData);
				 var num = parseInt(index)
				 var lineObj = lineData[num];
				 return lineObj.disCondition
		    },
		    
		    upOperation:function(selectedItem){ 
				var index = ko.utils.arrayIndexOf(this.lineData(),
						selectedItem);   
				
				ko.utils.arrayRemoveItem(this.lineData(), selectedItem); 
				
				this.lineData.splice(index - 1, 0, selectedItem);
			},
			downOperation:function(selectedItem){
				var index = ko.utils.arrayIndexOf(this.lineData(),
						selectedItem);
 
				ko.utils.arrayRemoveItem(this.lineData(), selectedItem);

				this.lineData.splice(index + 1, 0, selectedItem)
			}
		};   
	};
	
	return SplitNode;
})

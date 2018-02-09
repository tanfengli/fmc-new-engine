define(["knockout"],function(ko) { 
	 
	BranchNodeVO = function(options) { 
		var setting = $.extend({ 
			XMLNODENAME:  null,
			advice : [],
			id:null,
			name : null,
			x : null,
			y : null,
			
			
			lineOut:ko.observableArray([]),
			lineData:ko.observableArray([]),
			index:ko.observable(),
			formulaName : ko.observable()//公式参数
			 
		},options || {})
		
		return { 
			XMLNODENAME :  setting.XMLNODENAME,
			advice : setting.advice,
			id : setting.id,
			name : setting.name,
			x : setting.x,
			y : setting.y,
			
			lineOut : setting.lineOut,
			lineData : setting.lineData,
			index:setting.index,
			formulaName:setting.formulaName,
			
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
					// 根据优先级排序
					if(this.lineData()[0]&&this.lineData()[0].priority){
						this.lineData.sort(function(left,right){
							return left.priority>=right.priority?1:-1;
						});
					}
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
			
			setFormula : function(index){
				 var lineData = ko.toJS(this.lineData);
				 var num = parseInt(index)
				 var lineObj = lineData[num];
				 return lineObj.disCondition;
		    },
		    
		    upOperation:function(selectedItem){ 
				var index = ko.utils.arrayIndexOf(this.lineData(),selectedItem);   
				// 连线数据上移
				ko.utils.arrayRemoveItem(this.lineData(), selectedItem); 
				this.lineData.splice(index - 1, 0, selectedItem);
				for(i=0;i<this.lineData().length;i++){
					this.lineData()[i].priority=i;
				}
			},
			downOperation:function(selectedItem){
				var index = ko.utils.arrayIndexOf(this.lineData(),selectedItem);
				// 连线数据下移
				ko.utils.arrayRemoveItem(this.lineData(), selectedItem);
				this.lineData.splice(index + 1, 0, selectedItem);
				for(i=0;i<this.lineData().length;i++){
					this.lineData()[i].priority=i;
				}
			}
			
		};   
		  
	};
	
	return BranchNodeVO;
})

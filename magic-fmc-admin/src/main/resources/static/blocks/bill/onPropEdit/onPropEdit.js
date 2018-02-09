define(["knockout"],function(ko){
	function OnPropEdit(){
		var self=this;
		
		self.props=ko.observable()
		self.onPropEdit=function(props,attr){
			var obj=new Object();
			obj.props=props;
			obj.attr=attr;
			obj.operationList=[];
			obj.refId=null;
			obj.advice=null;
			
			if(props.operations!=undefined&&props.operations!=null){
				if(props.operations.refId!=undefined&&props.operations.refId!=null&&props.operations.refId!=''){
					obj.refId=props.operations.refId;
				}else{
					if(props.operations.length>0){
						var arr = new Array();
						for(i=0;i<props.operations.length;i++){
							var temp = new Object();
							temp.XMLNODENAME=props.operations[i].XMLNODENAME;
							temp.name=props.operations[i].name;
							temp.type=props.operations[i].type;
							arr[i]=temp;
						}
						obj.operationList = arr;
					}
				}
			}
		
			var advice=new Object();
			var after=new Object();
			var before=new Object();
			
			if(props.advice!=undefined&&props.advice.after!=undefined&&props.advice.after.length>0){
				after.XMLNODENAME=props.advice.after[0].XMLNODENAME;
				after.content=props.advice.after[0].content;
				after.unid=props.advice.after[0].unid;
			}
			
			if(props.advice!=undefined&&props.advice.before!=undefined&&props.advice.before.length>0){
				before.XMLNODENAME=props.advice.before[0].XMLNODENAME;
				before.content=props.advice.before[0].content;
				before.unid=props.advice.before[0].unid;
			}
			
			obj.after=after;
			obj.before=before;
			
			return obj;
		}
		
		
	};
		
	return OnPropEdit;
})
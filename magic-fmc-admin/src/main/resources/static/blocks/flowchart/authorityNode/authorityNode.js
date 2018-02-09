 define([ 'knockout','../../../blocks/flowchart/setting/vo/xmlNodeVO.js'],function(ko,XmlNode) {
	function authorityNode(params) {
		if (!ko.components.isRegistered("reviewNode")) {
			ko.components.register('reviewNode', {
			    viewModel:  {
			       require:'../blocks/flowchart/reviewNode/reviewNode.js'
			    },
			    template:  {
			    	 require:'text!blocks/flowchart/reviewNode/reviewNode.html'
			    }
			}); 
		}
		var self = this;
		// 待选列表
		self.reviewNodeData = ko.observableArray([]);
		self.reviewArray = ko.observableArray([]);
		self.selectNodes = ko.observableArray([]);
		self.authorityInfo = ko.observable('');
		//获取可选群组成员
		params.data.subscribe(function() {
			 var data = ko.toJS(params.data).reviewNodeData;
			 var objArr = new Array();
			 for(var i=0;i<data.length;i++){
				 var node = new XmlNode();
				 $.extend(node,data[i]);
				 objArr[i]=node
			 }
			 self.reviewNodeData(objArr);
		}
	  )

	  self.tab = ko.observable("edit");
	  self.setTab = function(tab){
		self.tab(tab)	
	  }
		
	  self.getTab = function(){
			return ko.toJS(self.tab)
	  }
	  
		self.reviewNodeForm = ko.pureComputed(function() {
			return {
				reviewArray : ko.toJS(self.reviewArray),
				newSelectMember : ko.toJS(self.selectNodes)
			};
	 	}, this);
		
		self.reviewHandle = function(reviewNodes){
			 var newFormItem = self.nodeForm();
			 var arr = newFormItem.getReviewNodes();
			 self.selectNodes(arr);
		 };
		 
		 //回调选中节点
		 self.selectReviewNode = function(data){
				 	var authorityInfo = ko.toJS(self.authorityInfo);
					var reviewNodesArr =  ko.toJS(self.reviewNodeData);
					var robRow = parseInt(authorityInfo.substring(16));
					var nodeObj = reviewNodesArr[robRow];
					reviewNodesArr[robRow]=self.setReviewNodes(data,nodeObj,authorityInfo);
					self.reviewNodeData(reviewNodesArr);
			 
		 }
		 
		 self.computeReview = function(authorityInfo){
			 	self.authorityInfo(authorityInfo);
				var reviewArr =  ko.toJS(self.reviewNodeData);
				var  arr = new Array();
				var robRow = parseInt(authorityInfo.substring(16));
				for(i=0;i<reviewArr.length;i++){
					if(i!=robRow){
						arr.push(reviewArr[i])
					}
				}
				var nodeObj = reviewArr[robRow];
				var selectNodeArr = new Array();
				if(authorityInfo.indexOf('CanModi')>-1){
					if(nodeObj.canModifyHandlerNodeNames!=null
							&&nodeObj.canModifyHandlerNodeNames!=undefined
							&&nodeObj.canModifyHandlerNodeNames!="")
						selectNodeArr=nodeObj.canModifyHandlerNodeNames.split(";");
				}else if(authorityInfo.indexOf('MustMod')>-1){
					if(nodeObj.mustModifyHandlerNodeNames!=null
							&&nodeObj.mustModifyHandlerNodeNames!=undefined
							&&nodeObj.mustModifyHandlerNodeNames!="")
						selectNodeArr=nodeObj.mustModifyHandlerNodeNames.split(";");
				}else if(authorityInfo.indexOf('CanView')>-1){
					if(nodeObj.nodeCanViewCurNodeNames!=null
							&&nodeObj.nodeCanViewCurNodeNames!=undefined
							&&nodeObj.nodeCanViewCurNodeNames!="")
						selectNodeArr = nodeObj.nodeCanViewCurNodeNames.split(";");
				}
				self.selectNodes(selectNodeArr);
				self.reviewArray(arr);
		  }
		 
		 self.setReviewNodes=function(dataArr,nodeObj,authorityInfo){
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
		    }
		 
		 self.confirmAuthority=function(){
			 params.callFunc(ko.toJS(self.reviewNodeData)); 
			 $('#authorityModel').modal('hide');
		 }
	};
	
	return authorityNode;
});
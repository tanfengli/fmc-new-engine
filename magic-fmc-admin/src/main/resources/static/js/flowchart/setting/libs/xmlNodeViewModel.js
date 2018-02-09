define([ "knockout","AjaxUtil", "./xmlNodeVO.js",'Flowchart',"components","bootstrap" ], function(ko,AjaxUtil,
		XMLNode,flowchart) {  
	 
    function viewModel() {
    	var self = this;

		self.buttongroup = [ {
			name : 'clear',
			func : function() {
				self.flowchart.clear()
			}
		}, {
			name : 'save',
			func : function() {
				var xml = self.flowchart.getFlowchartXml();
			}
		}];
		
		self.XMLNode = ko.observable(null);
		
		
		self.XMLNODENAME = ko.observable("");
		self.onPropEdit = function(e, XMLNode, src){
			var obj =ko.toJS(XMLNode);
			obj.ignoreOnSerialHandlerSame=ko.observable("");
			obj.nodeDelay=ko.observable("");
			self.XMLNode(obj);
			$('#myModal').modal();
		};
		
		self.editWindowClose = function(XMLNode){
		}

		self.flowchart = new FlowChart({
			showprops : self.onPropEdit
		});
		
		var data;

		AjaxUtil.call({
			url:"flowchart/setting/getFlowContent",
			type:"get",
			data: data, 
			success:function(xml) {  
				self.flowchart.init(xml);
			}
		});
		
		self.selectTypeFunc = function(data){   
			//机构
			var newFormItem = self.porp.formItem();
			if(ko.toJS(self.orgForm.formItem()).selectType == 'fdThisLeader'){    
				newFormItem.fdThisLeaderid=data.fdId;
				newFormItem.fdThisLeaderName=data.fdName;  
			}else if(ko.toJS(self.orgForm.formItem()).selectType == 'fdSuperLeader'){
				newFormItem.fdSuperLeaderid=data.fdId;
				newFormItem.fdSuperLeaderName=data.fdName;  
			}else if(ko.toJS(self.orgForm.formItem()).selectType == 'fdParent'){
				newFormItem.fdParentid=data.fdId;
				newFormItem.fdDeptName=data.fdName;  
			}
			self.orgForm.setForm(newFormItem); 
		} ;
		
		self.selectNewOrg = function(org){ 
			var newFormItem = self.orgForm.formItem();
			newFormItem.fdPersonId=org.fdId;
			newFormItem.fdParentorgid=org.fdId;
			newFormItem.fdPersonName=org.fdName;   
			self.orgForm.setForm(newFormItem);
		};
		
	};
 
    return viewModel;
})

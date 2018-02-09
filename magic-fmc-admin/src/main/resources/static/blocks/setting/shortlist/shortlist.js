define([ 'knockout' ,'AjaxUtil','MsgBox',
         "../../../js/common/flowchart/billEvent.js",
         "../../../js/common/listSelectModal/listSelectModal.js"], 
		function(ko,AjaxUtil,MsgBox,BillEvent,ListSelectModal) {
	
	function shortlistViewModel(params) {
	
	var self = this;
	
	self.billEvent=null;
	self.nodeDeatil=null;
	
	self.listSelectModal = new ListSelectModal(params);
    
    
    //模态框初始化加载
	self.initModal = $('#shortlistWindow').on('show.bs.modal', function () {
		self.listSelectModal.rightElement.removeAll();
		ko.utils.arrayPushAll(self.listSelectModal.rightElement,params.data.elementArray);
		self.billEvent = new BillEvent(params.data.bussinessForm);
		self.nodeDeatil = self.billEvent.WorkFlow_GetNodeObj(params.data.nodeId);
		if(undefined==self.nodeDeatil.optHandlerIds){
			self.listSelectModal.leftElement([]);
			self.listSelectModal.shortlist = [];
		}else{
			if(self.nodeDeatil.optHandlerSelectType=='org'){
				AjaxUtil.call({
					url : 'sys/orgElement/findByIds',
					data : {ids : self.nodeDeatil.optHandlerIds},
					async : false,
					success : function(data){
						self.listSelectModal.leftElement(data);
						self.listSelectModal.shortlist = data;
					}
				})
			}
		}
	})
    
	
	}
	return shortlistViewModel;
})
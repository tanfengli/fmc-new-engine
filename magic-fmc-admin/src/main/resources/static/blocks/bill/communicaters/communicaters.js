define([ 'knockout' ,'AjaxUtil','MsgBox',
         "../../../js/common/listSelectModal/listSelectModal.js"], 
		function(ko,AjaxUtil,MsgBox,ListSelectModal) {
	
	function communicatersViewModel(params) {
	
	var self = this;
	
	self.listSelectModal = new ListSelectModal(params);
    
    //模态框初始化加载
	self.initModal = $('#communicatersWindow').on('show.bs.modal', function () {
		self.listSelectModal.rightElement.removeAll();
		self.listSelectModal.leftElement.removeAll();
		if(params.data&&params.data.communicateScope){
			ko.utils.arrayPushAll(self.listSelectModal.rightElement,params.data.elementArray?params.data.elementArray:[]);
			ko.utils.arrayPushAll(self.listSelectModal.leftElement,params.data.communicateScope?params.data.communicateScope:[]);
			self.listSelectModal.shortlist = params.data.communicateScope?params.data.communicateScope:[];
		}
		
	})
    
	
	}
	return communicatersViewModel;
})
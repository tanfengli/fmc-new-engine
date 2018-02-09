define([ 'knockout' ,'MsgBox'], function(ko,MsgBox) {
	function searchFormPanel(params) {
		
		var self = this;
		
		self.searchBodyVisiable = ko.observable(true);
		
		//查询form viewModel
		self.searchForm = null;
		if(params.data){
			self.searchForm = params.data;
		}else{
			MsgBox.error('查询form没有定义')
		}
		
		$('#search_expand_switch').click(function(){
			if(self.searchBodyVisiable()){
				$(".search-box").css('margin-bottom','5px');
				$('#search_expand_switch i').removeClass('fa-angle-double-up');
	            $('#search_expand_switch i').addClass('fa-angle-double-down');
				self.searchBodyVisiable(false);
			}else{
				$(".search-box").css('margin-bottom','0px');
				$('#search_expand_switch i').removeClass('fa-angle-double-down');
	            $('#search_expand_switch i').addClass('fa-angle-double-up');
				self.searchBodyVisiable(true);
			}
	    })
	}

	return searchFormPanel;
})
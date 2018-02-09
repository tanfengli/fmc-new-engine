
define([ "knockout","./conditionalVO.js","components","bootstrap" ], function(ko,Vo) {

function viewModel() {
	"use strict";	
	
	var self = this;
	
	self.selectNodeId = ko.observable();
	
	
	self.form = new ko.formViewModel({
		url : 'flowchart/setting'
	});
  
	self.form.setForm(new Vo({}));
	
};
return viewModel;
})


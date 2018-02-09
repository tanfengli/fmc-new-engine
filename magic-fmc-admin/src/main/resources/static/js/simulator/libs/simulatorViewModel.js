define([ "knockout","MsgBox","./subViewModel/simulatorFormViemModel.js","components","bootstrap" ], 
		function(ko,MsgBox,SimulatorFormVM) {  

function viewModel() {
	
	var self = this;
	
	self.simulatorForm = new SimulatorFormVM(); 
	
};
return viewModel;
})
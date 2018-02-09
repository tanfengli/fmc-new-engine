 define(['knockout'],function(ko){
	function defineParameter(params) {
		var self=this;
		
		self.parameters=ko.observableArray();
		
		params.data.subscribe(function(val){
			self.parameters(val)
		})
		
		self.addParameter=function(){
			self.parameters.push({fdName:"",fdValue:""}); 
		}
		
		self.removeParameter=function() {    
			self.parameters.remove(this); 
		}
		
		self.callback=function(){
			params.callFunc(self.parameters());
		}
	};
	
	
	return defineParameter;
});
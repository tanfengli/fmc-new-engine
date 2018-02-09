define(["knockout","knockout-validation"],function(ko,kv) {  
	SysClusterServer=function(configuration) { 
	    var self=this;
	        
	    self.fdId=ko.observable(''||configuration.fdId); 
	    self.fdName=ko.observable(''||configuration.fdName); 
	    self.fdKey=ko.observable(''||configuration.fdKey).extend({
            required: {
            	params:true,
            	message: "标识不能为空！" 
            }
        });  
	    self.fdAnonymous=ko.observable(''||configuration.fdAnonymous); 
	    self.fdDispatcherType=ko.observable(''||configuration.fdDispatcherType); 
	    self.fdUrl=ko.observable(''||configuration.fdUrl);  
	    self.fdPid=ko.observable(''||configuration.fdPid);  
	    self.fdAddress=ko.observable(''||configuration.fdAddress);  
	    self.fdStartTime=ko.observable(''||configuration.fdStartTime);  
	    self.fdRefreshTime=ko.observable(''||configuration.fdRefreshTime);  
	    self.fdShutdown=ko.observable(''||configuration.fdShutdown);   
	    
	    self.sysWfEventService=ko.observable(''||configuration.sysWfEventService); 
	    self.sysQuartzDispatcher=ko.observable(''||configuration.sysQuartzDispatcher); 
	    self.sysOperatorType=ko.observable(''||configuration.sysOperatorType); 
	 };
		
	return SysClusterServer;
})
define(["knockout","knockout-validation"],function(ko){ 
	SysBusiSys=function(configuration){ 
	    var self=this;
	    self.fdId=ko.observable('' || configuration.fdId); 
	    self.fdName=ko.observable('' || configuration.fdName).extend({
            required:{
            	params:true,
            	message:"请输入系统名称." 
            }
        }); 
	    
	    self.fdCode = ko.observable('' || configuration.fdCode).extend({
            required:{
            	params:true,
            	message: "请输入系统编码." 
            }
        }); 
	    
	    self.fdCreatedBy=ko.observable('' || configuration.fdCreatedBy); 
	    self.fdCreatedDate=ko.observable('' || configuration.fdCreatedDate); 
	    self.fdEnabled=ko.observable('' || configuration.fdEnabled);  
	    self.createdName=ko.observable('' || configuration.createdName);  
	    self.fdParameter=ko.observable('' || configuration.fdParameter); 
	 };
		
	return SysBusiSys;
})
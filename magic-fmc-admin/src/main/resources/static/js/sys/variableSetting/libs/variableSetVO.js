define(["knockout",'AjaxUtil',"knockout-validation"],function(ko,AjaxUtil) { 
	
	VariableSet = function(configuration) {
	    var self = this;
	     
	    self.fdId = ko.observable('' || configuration.fdId); 
	    self.varUseType = ko.observable('' || configuration.varUseType); 
	    self.varName = ko.observable('' || configuration.varName).extend({
            required: {
            	params:true,
            	message: "请输入变量名称。" 
            }
        }); 
	    self.varIsJdbc = ko.observable('' || configuration.varIsJdbc); 
	    self.varConnFdId = ko.observable('' || configuration.varConnFdId); 
	    self.varStatus = ko.observable('' || configuration.varStatus); 
	    self.varCreatorId = ko.observable(0 || configuration.varCreatorId); 
	    self.varSql = ko.observable('' || configuration.varSql);  
	    self.varCreateTime = ko.observable('' || configuration.varCreateTime); 
	    self.varType = ko.observable('' || configuration.varType); 
	    self.varCode = ko.observable('' || configuration.varCode); 
	    self.varUseTypeName = ko.observable('' || configuration.varUseTypeName); 
		
		// 获取数据库名start--------------------
		var dbcp = function(name, id) {
			this.name = name;
			this.id = id;
		};

		self.dbcpTypeArray = ko.observableArray([{name:"--请选择--",id:""}]);
		
		self.getDbcpIdAndName = function() {
			
			 AjaxUtil.call({
				type : "POST",
				url : 'sys/variableSetting/findCompDbcp',
				success : function(data) {
					for(var i=0;i<data.length;i++)
					{
     				   self.dbcpTypeArray.push(new dbcp(data[i].fdName,data[i].fdId)); 
					}  
				}
			}); 
			
		}();
		// 获取参数分类end--------------------
	 
	 };
		
	return VariableSet;

})
/*
VariableSet = function(options) {
 	var setting = $.extend({
 		fdId : null,
 		varUseType: null,
 		varName: null,
 		varIsJdbc: null,
 		varConnFdId: null,
 		varStatus: null,
 		varCreatorId: null,
 		varSql: null,
 		varCreateTime:null
 	},options || {})
 	
 	return setting; 
 	
};*/
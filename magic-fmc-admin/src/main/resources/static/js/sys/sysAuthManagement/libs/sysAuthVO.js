define(["knockout"],function(ko){ 
    SysAuthModel=function(configuration){
	    var self = this;
	     
	    self.fdId=ko.observable(''||configuration.fdId); 
	    self.fdAuthorizeType=ko.observable(''||configuration.fdAuthorizeType); 
	    self.fdStartTime=ko.observable(''||configuration.fdStartTime); 
	    self.fdEndTime=ko.observable(''||configuration.fdEndTime);  
	    self.fdStartTime.subscribe(function(val){
	    	if(val.split(" ").length>1){
	    		return ;
	    	}
	    	val+=" 00:00:00";
	    	self.fdStartTime(val)
	    })
	    
	    self.fdEndTime.subscribe(function(val){
	    	if(val.split(" ").length>1){
	    		return ;
	    	}
	    	val+=" 00:00:00";
	    	self.fdEndTime(val)
	    })
	    
	    //self.fdExpireDeleted = ko.observable('' || configuration.fdExpireDeleted); 
	    self.fdExpireDeletedChe = ko.observableArray(configuration.fdExpireDeletedChe || []); 
	    self.fdCreateTime = ko.observable('' || configuration.fdCreateTime); 
	    self.fdAuthorizer = ko.observable(0 || configuration.fdAuthorizer); 
	    self.fdCreator = ko.observable('' || configuration.fdCreator);  
	    self.fdAuthorizedPerson = ko.observable('' || configuration.fdAuthorizedPerson); 
	    self.fdAuthorizerName = ko.observable('' || configuration.fdAuthorizerName); 
	    self.fdAuthorizerPersonName = ko.observable('' || configuration.fdAuthorizerPersonName); 
	    self.fdAuthorizedCateText = ko.observable('' || configuration.fdAuthorizedCateText);  
	    self.fdAuthorizeOrgId = ko.observable('' || configuration.fdAuthorizeOrgId);  
	    self.fdAuthorizeOrgName = ko.observable('' || configuration.fdAuthorizeOrgName);  
	    self.fdAuthorizedCateTextId = ko.observable('' || configuration.fdAuthorizedCateTextId);  
	    self.authorizeScopeArray = ko.observableArray(configuration.authorizeScopeArray || []); 
	    self.fdAuthorizeContext = ko.observableArray(configuration.fdAuthorizeContext || []);
	    self.fdAuthorizedChe = ko.observableArray(configuration.fdAuthorizedChe || []); 
	    
		self.clickPerson=function(vals){  
		    self.selectType=ko.observable('');
			self.selectType(vals);
			$('#treeSelectPerWindow').modal(); 
		};
		
		//打开授权范围选择前赋值
		 self.selectTemplate=function(){
			 var templateObj={templateIds:self.fdAuthorizedCateTextId(),templateNames:self.fdAuthorizedCateText()}
			 return templateObj;
		 };
		 
		//选择授权范围回调
		self.selectNewCate=function(cate,strName,strId){  
			self.fdAuthorizedCateTextId(strId);
			self.fdAuthorizedCateText(strName);
			self.authorizeScopeArray(ko.toJS(cate));
			
			
			//授权类型控制
			var type=ko.toJS(self.fdAuthorizeType);
			if(type==1){
				$('#judgeShow').hide();
			}else{
				$('#fdAuthorizeTypeOne').prop('checked',true);
				$('#judgeShow').show();
			}
		}
	 }; 

	 return SysAuthModel; 
})
define(["knockout", "MsgBox"],function(ko, MsgBox) { 
	
	QuartzJob = function(configuration) {
	    var self = this;
	     
	    self.fdId = ko.observable('' || configuration!=null?configuration.fdId:''); 
	    self.fdModelName = ko.observable('' || configuration!=null?configuration.fdModelName:''); 
	    self.fdModelId = ko.observable('' || configuration!=null?configuration.fdModelId:''); 
	    self.fdKey = ko.observable('' || configuration!=null?configuration.fdKey:''); 

	    self.fdSubject = ko.observable('' || configuration!=null?configuration.fdSubject:''); 
	    self.fdJobService = ko.observable('' || configuration!=null?configuration.fdJobService:''); 
	    self.fdJobMethod = ko.observable('' || configuration!=null?configuration.fdJobMethod:''); 
	    
	    self.fdLink = ko.observable(0 || configuration!=null?configuration.fdLink:0); 
	    self.fdParameter = ko.observable('' || configuration!=null?configuration.fdParameter:'');  
	    
	    self.fdCronExpression = ko.observable('' || configuration!=null?configuration.fdCronExpression:''); 
	    self.fdEnabled = ko.observable(false || configuration!=null?configuration.fdEnabled:false); 
	    self.fdIsSysJob = ko.observable(false || configuration!=null?configuration.fdIsSysJob:false); 
	    self.fdRunType = ko.observable('' || configuration!=null?configuration.fdRunType:''); 
	 
	    self.fdRunServer = ko.observable('' || configuration!=null?configuration.fdRunServer:''); 
	    self.fdRunTime = ko.observable('' || configuration!=null?configuration.fdRunTime:''); 
	    self.fdRequired = ko.observable(false || configuration!=null?configuration.fdRequired:false); 
	    
	    self.year = ko.observable('' || configuration!=null?configuration.year:''); 
	    self.month = ko.observable('' || configuration!=null?configuration.month:''); 
	    self.day = ko.observable('' ||configuration!=null? configuration.day:''); 
	    self.hour = ko.observable('' || configuration!=null?configuration.hour:''); 
	    self.min = ko.observable('' || configuration!=null?configuration.min:''); 
	    self.second = ko.observable('' || configuration!=null?configuration.second:''); 
	    self.week = ko.observable('' || configuration!=null?configuration.week:''); 
	    self.every = ko.observable('' || configuration!=null?configuration.every:''); 
	    self.inputType = ko.observable('0' || configuration!=null?configuration.inputType:'0'); 
	    
	    self.fdFrequency=ko.observable('' || configuration!=null?configuration.fdFrequency:''); 
	    
	    self.changeInputType=function(value){
	    	self.inputType(value);
	    }
	    
	    self.getFrequency = function(value){
	    	
	    	if(value=='br'){
	    		 var fd = ko.toJS(self.fdFrequency);
	    		 if(fd=='year'||fd=='once'||fd=='week'){
	    			 return true;
	    		 }else {
	    			 return false;
	    		 }
	    	 } 
	    	
	    	 if(ko.toJS(self.fdFrequency)=='once'){
	    		 if(value=='week'||value=='every'){
	    			 return false;
	    		 }else {
	    			 return true;
	    		 }
	    	 }else if(ko.toJS(self.fdFrequency)=='year'){
	    		 if(value=='week'||value=='year'||value=='every'){
	    			 return false;
	    		 }else {
	    			 return true;
	    		 }
	    	 }else if(ko.toJS(self.fdFrequency)=='month'){
	    		 if(value=='week'||value=='year'||value=="month"||value=='every'){
	    			 return false;
	    		 }else {
	    			 return true;
	    		 }
	    	 }else if(ko.toJS(self.fdFrequency)=='day'){
	    		 if(value=='week'||value=='year'||value=='month'||value=='day'||value=='every'){
	    			 return false;
	    		 }else {
	    			 return true;
	    		 }
	    	 }else if(ko.toJS(self.fdFrequency)=='hour'){
	    		 if(value=='week'||value=='year'||value=='month'||value=='day'||value=='hour'||value=='every'){
	    			 return false;
	    		 }else {
	    			 return true;
	    		 }
	    	 }else if(ko.toJS(self.fdFrequency)=='every'){
	    		 if(value!='every'){
	    			 return false;
	    		 }else {
	    			 return true;
	    		 }
	    	 }else if(ko.toJS(self.fdFrequency)=='week'){
	    		 if(value=='year'||value=='month'||value=='day'||value=='every'){
	    			 return false;
	    		 }else {
	    			 return true;
	    		 }
	    	 }   
	    	 
	    	 
	    }
	    
	  //解释CronExpression，并将值写入到相关的设置项中
	    self.parseCronExpression = function(){
	    	var expressionField = ko.toJS(self.fdCronExpression);
	    	if(expressionField==null||expressionField==undefined||expressionField==""){
				return;
			}
	    	var expression;
	    	expression = expressionField.split(/\s+/gi);
	    	var data = new Array();
	    	var frequency = null;
	    	try{
	    		switch(expression.length){
	    		case 7:
	    			//判断年
	    			if(!self.checkCronExpressionField("year", expression[6], data, frequency))
	    				frequency = "once";
	    		case 6:
	    			//判断月
	    			if(!self.checkCronExpressionField("month", expression[4], data, frequency) && frequency==null)
	    				frequency = "year";
	    			//判断星期
	    			if(expression[5]!="?"){
	    				if(expression[3]!="?" || frequency!=null)
	    					throw "";
	    				if(expression[5]!="*"){
	    					if(re_Number.test(expression[5]))
	    						throw "";
	    					data.week = expression[5];
	    					frequency = "week";
	    				}
	    			}else{
	    			//判断日期
	    				if(!self.checkCronExpressionField("day", expression[3], data, frequency) && frequency==null)
	    					frequency = "month";
	    			}
	    			//判断小时
	    			if(!self.checkCronExpressionField("hour", expression[2], data, frequency) && frequency==null){
	    				if(data.week==null)
	    					frequency = "day";
	    				else
	    					frequency = "week";
	    			}
	    			//判断分
	    			if(expression[1]=="*")
	    				throw "";
	    			if(re_Number.test(expression[1])){
	    				if(frequency!=null)
	    					throw "";
	    				var tmpArr = expression[1].split("/");
	    				if(tmpArr.length!=2 || re_Number.test(tmpArr[0]) || re_Number.test(tmpArr[1]))
	    					throw "";
	    				self.every(tmpArr[1]);
	    				self.min(tmpArr[1]);
	    				self.second(tmpArr[0]);
	    				frequency = "every";
	    			}else{
	    				if(frequency==null)
	    					frequency = "hour";
	    				
	    				data.minute = expression[1];
	    				self.min(expression[1])
	    			}
	    			//判断秒
	    			if(self.checkCronExpressionField("second", expression[0], data, frequency))
	    				throw "";
	    		}
	    	}catch(e){
	    		if(e=="")
	    			frequency = null;
	    		else
	    			throw e;
	    	}
	    	if(frequency==null)
	    		data = new Array();
	    	else{
	    		data.frequency = frequency;
	    		self.fdFrequency(frequency);
	    	}
	    		
	    	
	    	//setCronExpressionField(data);
	    };
	    
	    /*
	    校验CronExpression的域（年、月、时、秒），并把值写到data中。
	    返回：true（该字段未限定）false（该字段已经限定）
	    抛出：""，该域无法解释
	    */
	    var re_Number = /[^\d]/gi;

	   self.checkCronExpressionField=function(fieldName, fieldValue, data, frequency){
		   
	    	if(fieldValue=="*" || fieldValue==""||fieldValue==undefined){
	    		//若前面频率已经确定，但当前字段却没有限定，不满足常用的模式，抛出无法解释
	    		if(frequency!=null)
	    			throw "";
	    		return true;
	    	}
	    	if(re_Number.test(fieldValue))
	    		throw "";
	    	if(fieldName=='year'){
	    		self.year(fieldValue)
	    	}else if(fieldName=='month'){
	    		self.month(fieldValue)
	    	}else if(fieldName=='day'){
	    		self.day(fieldValue)
	    	}else if(fieldName=='hour'){
	    		self.hour(fieldValue)
	    	}else if(fieldName=='minute'){
	    		self.min(fieldValue)
	    	}else if(fieldName=='second'){
	    		self.second(fieldValue)
	    	}
	    	
	    	data[fieldName] = fieldValue;
	    	return false;
	    }

	   self.setExpression = function(flag){
		  if(flag){
			  self.parseCronExpression();
		  }else{
			  self.buildCronExpression();
		  }
	   }
	   
	  //构造CronExpression，并写入fdCronExpression中，返回false则表示构造失败
	   self.buildCronExpression=function (){
		    var fieldMessages = "秒,分,时,日,月,周,年".split(",");
		    
			var frequency = ko.toJS(self.fdFrequency);
			//若没有选择频率，不处理
			if(frequency=='')
				return true;
			//获取所有设置项的信息
			var year = ko.toJS(self.year);
			var month = ko.toJS(self.month);
			var day = ko.toJS(self.day);
			var week = ko.toJS(self.week);
			var hour = ko.toJS(self.hour);
			var minute = ko.toJS(self.min);
			var second =ko.toJS(self.second);
			var every = ko.toJS(self.every);
//				document.getElementsByName("fdEvery")[0].options[document.getElementsByName("fdEvery")[0].selectedIndex].value;
			//根据频率调整参数
			switch(frequency){
				case "every":
					minute = second+"/"+every;
				case "hour":
					hour = "*";
				case "day":
					day = "*";
				case "week":
				case "month":
					month = "*";
				case "year":
					year = "";
				break;
			}
			//构造CronExpression
			try{
				var expression = formatCronExpressionField(second, fieldMessages[0], 0, 59)+" ";
				if(frequency=="every")
					expression += minute+" ";
				else
					expression += formatCronExpressionField(minute, fieldMessages[1], 0, 59)+" ";
				expression += formatCronExpressionField(hour, fieldMessages[2], 0, 23)+" ";
				if(frequency=="week"){
					expression += "? ";
					expression += formatCronExpressionField(month, fieldMessages[4], 1, 12)+" ";
					expression += week;
				}else{
					expression += formatCronExpressionField(day, fieldMessages[3], 1, 31)+" ";
					expression += formatCronExpressionField(month, fieldMessages[4], 1, 12)+" ";
					expression += "?";
				}
				if(year!="")
					expression += " "+formatCronExpressionField(year, fieldMessages[6], 1970, 2099);
			}catch(e){
				//构造过程校验出错，返回false，e==""表示是formatCronExpressionField函数抛出的错误
				if(e==""){
					return false;
				}
					
				throw e;
			}
			self.fdCronExpression(expression);
			return true;
		}
	   var errorRange = "{0} 没有在区间 {1} 和 {2}之间.";
	 //整理域的信息，若校验出错，抛出""
	   function formatCronExpressionField(value, fieldMsg, minValue, maxValue){
		   	if(value=="*")
		   		return value;
		   	if(value=="")
		   		return minValue;
		   	value = parseInt(value, 10);
		   	if(isNaN(value)){
//		   		MsgBox.warning(errorInteger.replace(/\{0\}/, fieldMsg));
		   		throw "";
		   	}
		   	if(value<minValue || value>maxValue){
		   		var msg = errorRange.replace(/\{0\}/, fieldMsg);
		   		msg = msg.replace(/\{1\}/, minValue);
		   		msg = msg.replace(/\{2\}/, maxValue);
		   		MsgBox.warning(msg);
		   		throw "";
		   	}
		   	if(value<minValue || value>maxValue)
		   		return value;
		   	return value;
	   }
	   
	 };
		
	return QuartzJob;

})

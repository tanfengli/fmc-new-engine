
define([ 'knockout','AjaxUtil'], function(ko,AjaxUtil) {
	function robotNode(params) {  
		var self = this;
		self.robotIndex= ko.observable("33");
		//机器人数据库链接
		self.compDbcp=ko.observableArray([]);
		 //输入参数
		self.inputParams  = ko.observableArray([]);
		 //输出参数
		self.outParams = ko.observableArray([]);
		 //数据库
		self.dbConnect =  ko.observable("");
		 //填写sql
		self.sql = ko.observable("");
		//机器人节点类别
		self.category= ko.observable("RDB");
		//选择小类别
		self.unid = ko.observable("");
		//小类别列表
		self.robotOption= ko.observableArray([]);
		//类别列表总和
	    self.robotInfo=ko.observableArray([]);
	       
	    self.dataTypeArr=ko.observableArray([]);
		 
	    self.content= ko.observable("");
	    
	    self.labelName = ko.observable("前置逻辑");
	    
		
		self.getUnid = function(){
			//console.info(ko.toJS(self.unid));
			 return ko.toJS(self.unid);
		}
		self.getCategory= function(){
//			console.info(ko.toJS(self.category));
			return ko.toJS(self.category);
		}
		
		self.showRDB = function(){
//			var newFormItem = self.nodeForm.formItem();
//			self.unid(newFormItem.unid);
		}
		 //获取入参
		self.fetchParam = function(){
			 var sql = ko.toJS(self.sql);
			 var sqlArr = sql.split(":");
			 self.inputParams.removeAll();
			 for(i=1;i<sqlArr.length;i++){
				 var sqlIndex = sqlArr[i].indexOf(" ");
				 var paramObj = new Object();
				 if(sqlIndex>-1){
					 paramObj.name=sqlArr[i].substring(0,sqlIndex).replace(',',"").replace(')',"");
				 }else{
					 paramObj.name=sqlArr[i].replace(',',"").replace(')',"");
				 }
				 paramObj.dataType="String";
				 paramObj.idField="";
				 paramObj.nameField="";
				 paramObj.dataTypeArr = ko.toJS(self.dataTypeArr);
				 self.inputParams.push(paramObj);
			 }
		}
		
		
		self.fetchOutParam = function(){
			 var sql = ko.toJS(self.sql);
			 var start = sql.toLocaleLowerCase().indexOf("select");
			 var end = sql.toLocaleLowerCase().indexOf("from");
			 if(start==-1||end==-1){
				 return;
			 }
			 var outSql = sql.substring(start+6,end).trim();
			 var sqlArr = outSql.split(",");
			 self.outParams.removeAll();
			 for(i=0;i<sqlArr.length;i++){
				 var paramObj = new Object();
				 paramObj.name=sqlArr[i];
				 paramObj.dataType="String";
				 paramObj.idField="";
				 paramObj.nameField="";
				 paramObj.dataTypeArr=ko.toJS(self.dataTypeArr);
				 self.outParams.push(paramObj);
			 }
		}
		 //获取java示例代码
		self.fetchJavaExp = function(){
			  var exampleJava = createExample();
			  self.content(exampleJava);
		}
		//初始化机器人信息
		self.initRobotContent = function(robotContent){
			self.inputParams.removeAll();
			self.outParams.removeAll();
			self.fieldArr.removeAll();
			self.sql("");
			self.dbConnect("");
			self.content("");
			if(robotContent==null||robotContent==''||robotContent==undefined){
				return;
			}
//			robotContent = (robotContent);
			var dataArr =  ko.toJS(self.dataTypeArr);
			var robotObj = eval("("+robotContent+")");
//			console.info(robotContent);
			if(ko.toJS(self.category)=='RDB'){
				if(robotObj.inputParams!=undefined&&robotObj.inputParams!=null&&robotObj.inputParams.length>0){
					for(i=0;i<robotObj.inputParams.length;i++){
						robotObj.inputParams[i].dataTypeArr = dataArr;
						 self.inputParams.push(robotObj.inputParams[i]);
					 }
				}

				if(robotObj.outParams!=undefined&&robotObj.outParams!=null&&robotObj.outParams.length>0){
					for(i=0;i<robotObj.outParams.length;i++){
						robotObj.outParams[i].dataTypeArr = dataArr;
						self.outParams.push(robotObj.outParams[i]);
					 }
				}
				
				self.sql(robotObj.sql);
				self.dbConnect(robotObj.dbConnect);
			}else if(ko.toJS(self.category)=='java'){
				self.content(strDeEscape(robotObj.content));
			}else if(ko.toJS(self.category)=='other'){
				var varArr =  ko.toJS(self.varArr);
				for(i=0;i<robotObj.params.length;i++){
					robotObj.params[i].varArr = varArr;
					self.fieldArr.push(robotObj.params[i]);
				 }
			}
			
		 }
		
		//处理机器人节点公式
		 self.robotFormula = function(rowIndex,idField,nameField,type){
			 if(type=="field"){
				 var fieldArr = ko.toJS(self.fieldArr);
				 self.fieldArr.removeAll();
				 for(i=0;i<fieldArr.length;i++){
					 var param = fieldArr[i];
					 if(i==rowIndex){
						 param.idField=idField;
						 param.nameField=nameField;
					 } 
					 self.fieldArr.push(param);
				 }
			 }else{
				 var inputParams = ko.toJS(self.inputParams);
				 var outParams = ko.toJS(self.outParams);
				 if(type=='input'){
					 self.inputParams.removeAll();
					 for(i=0;i<inputParams.length;i++){
						 var param = inputParams[i];
						 if(i==rowIndex){
							 param.idField=idField;
							 param.nameField=nameField;
						 } 
						 self.inputParams.push(param);
					 }
				 }else if(type=='out'){
					 self.outParams.removeAll();
					 for(i=0;i<outParams.length;i++){
						 var param = outParams[i];
						 if(i==rowIndex){
							 param.idField=idField;
							 param.nameField=nameField;
						 } 
						 self.outParams.push(param);
					 }
				 }
			 }
			 
		 }

		 self.saveRobot = function(){
			 var advice = new Object();
			 advice.XMLNODENAME = "advice";
			 advice.unid = ko.toJS(self.unid);
			 if(ko.toJS(self.category)=='RDB'){
				 var robObj = new Object();
				 var inputArr= ko.toJS(self.inputParams);
				 var result = new Array();
				 for(i=0;i<inputArr.length;i++){
					 var input = new Object();
					 input.name= inputArr[i].name;
					 input.dataType= inputArr[i].dataType;
					 input.idField= inputArr[i].idField;
					 input.nameField= inputArr[i].nameField;
					 result.push(input)
				 }
				 var outArr= ko.toJS(self.outParams);
				 var outResult = new Array();
				 for(i=0;i<outArr.length;i++){
					 var out = new Object();
					 out.name= outArr[i].name;
					 out.dataType= outArr[i].dataType;
					 out.idField= outArr[i].idField;
					 out.nameField= outArr[i].nameField;
					 outResult.push(out)
				 }
				
				 robObj.inputParams= result;
				 robObj.outParams= outResult;
				 robObj.dbConnect= ko.toJS(self.dbConnect);
				 robObj.sql = ko.toJS(self.sql);
				// console.info(robObj)
//				 console.info(JSON.stringify(robObj))
//				 var content = ko.toJSON(robObj);
				 advice.content =JSON.stringify(robObj);
//				 console.info(advice.content)
			 }else if(ko.toJS(self.category)=='java'){
				 var robObj = new Object();
				 robObj.content=ko.toJS(self.content);
				 var content = JSON.stringify(robObj);
//				 console.info(content)
				 advice.content =content;
			 }else if(ko.toJS(self.category)=='other'){
				 var robObj = new Object();
				 var fieldArr= ko.toJS(self.fieldArr);
				 var result = new Array();
				 for(i=0;i<fieldArr.length;i++){
					 var fieldT = new Object();
					 fieldT.field= fieldArr[i].field;
//					 fieldT.fieldName= fieldArr[i].field;
					 fieldT.dataType= fieldArr[i].dataType;
					 fieldT.idField= fieldArr[i].idField;
					 fieldT.nameField= fieldArr[i].nameField;
					 result.push(fieldT)
				 }
				 robObj.params=result;
				 advice.content =JSON.stringify(robObj);
			 }
			 params.callFunc(advice); 
		 }
		
		//初始化基本属性
	       self.initRobot = function(){
	    	 
	    	   var robotObj= ko.observableArray([]);
	    	   var optionObj1 = {'name':'执行SQL语句读取数据','value':'*@Robot@ReadUseSQL','type':'RDB'}
	    	   var optionObj2 = {'name':'执行SQL语句写入数据','value':'*@Robot@WriteUseSQL','type':'RDB'}
	    	   self.robotInfo.push(optionObj1)
	    	   self.robotInfo.push(optionObj2)
	    	   
	    	   var optionObj3 = {'name':'修改表单数据','value':'*@Robot@UpdateFormData','type':'other'}
	    	   self.robotInfo.push(optionObj3)
	    	   
	    	   var optionObj4 = {'name':'创建子流程','value':'*@Robot@CreateSubflow','type':'sub'}
	    	   self.robotInfo.push(optionObj4)
	    		  
	    	   var optionObj5 = {'name':'一般语句','value':'*@Robot@RunScript','type':'java'}
	    	   self.robotInfo.push(optionObj5)
	    	   
	    	  
	       }
	       self.initRobot();
	       
	       self.changeRobot = function(){
		    	  var  category =ko.toJS(self.category);
		    	  self.robotOption.removeAll();
		    	  var robotInfoArr = ko.toJS(self.robotInfo);
		    	  for(var i=0;i<robotInfoArr.length;i++){
		    		  if(category==robotInfoArr[i].type){
		    			  self.robotOption.push(robotInfoArr[i]);
			    	  } 
		    	  }
		       }
		
		 var compDbcpArr=ko.observableArray([]);
		 	AjaxUtil.call({
				 url:"sys/variableSetting/findCompDbcp", 
				 type:"post",
				 success:function(dataAll) {
		//			self.compDbcp.removeAll();
					for(i=0;i<dataAll.length;i++){
						compDbcpArr.push(dataAll[i]);
					}
				 }
		 	})
		 
		 self.fieldArr=ko.observableArray([]);
		 
		 self.varArr=ko.observableArray([]);
		 var solidName="固定变量.";
		 var defineName="自定义变量.";
		 AjaxUtil.call({
			 url:"sys/variableSetting/initData",
			 success:function(data) {
				for(i=0;i<data.varList.length;i++){
					var node=data.varList[i];
					if(node.varType=='solid'){
						node.varName=solidName+node.varName;
						self.varArr.push(node);
					}else{
						node.varName=defineName+node.varName;
					}
					
				}
			}
		 });
		 
		 self.initData = function(){
			 self.compDbcp.removeAll();
			 for(i=0;i< ko.toJS(compDbcpArr).length;i++){
		    	  self.compDbcp.push(ko.toJS(compDbcpArr)[i]);
		     }
			 self.inputParams.removeAll();
			 self.outParams.removeAll();
			 self.fieldArr.removeAll();
			 self.sql("");
			 self.dbConnect("");
			 self.content("");
			 self.changeRobot('RDB');
		 }
		
		 params.data.subscribe(function() {
//			 console.info(ko.toJS(self.dbConnect))
			 self.dataTypeArr.removeAll();
			 self.dataTypeArr.push({'name':'字符串','value':'String'});
	    	 self.dataTypeArr.push({'name':'日期','value':'DateTime'});
	    	 self.dataTypeArr.push({'name':'数字','value':'Double'});
			 var robotNodeContent = ko.toJS(params.data).robotNodeContent;
			// return;
//			 console.info(robotNodeContent)
			 var robotObj = eval("("+robotNodeContent+")");
			 var robotType = robotObj.robotType;
			 if(robotType=='before'){
				 self.labelName("前置逻辑")
			 }else{
				 self.labelName("后置逻辑")
			 }
			 
			 if(robotObj.type=='init'){
				 
				 for(i=0;i< ko.toJS(compDbcpArr).length;i++){
			    	  self.compDbcp.push(ko.toJS(compDbcpArr)[i]);
			     }
				
				 for(i=0;i< ko.toJS(self.robotInfo).length;i++){
				    	if(robotObj.unid==ko.toJS(self.robotInfo)[i].value){
				    		self.category(ko.toJS(self.robotInfo)[i].type);
				    		self.changeRobot(ko.toJS(self.robotInfo)[i].type);
				    		break;
				    	}
				  }
				 
				 self.initData();
				 self.content(robotObj.content);
				 self.unid(robotObj.unid);
				 self.initRobotContent(robotObj.content);
			 }else if(robotObj.type=='formula'){
				 
				 var str = ko.toJS(self.robotIndex);
				 if(ko.toJS(self.unid)=='*@Robot@UpdateFormData'){
					 var indexNum = str.indexOf('robotField');
					 var robRow = parseInt(str.substring(indexNum+10));
					 type="field";
					 self.robotFormula(robRow,robotObj.idField,robotObj.nameField,type);
				 }else{
					 var robLogicIndexNum = str.indexOf('robLogic');
					 var robRow;
					 var type;
					 if(robLogicIndexNum==-1){
						 robLogicIndexNum = str.indexOf('robOutLogic');
						 robRow = parseInt(str.substring(robLogicIndexNum+11));
						 type="out";
					 }else{
						 type="input";
						 robRow = parseInt(str.substring(robLogicIndexNum+8));
					 }
					 if(robLogicIndexNum==-1){
						 return;
					 }
					 
					 self.robotFormula(robRow,robotObj.idField,robotObj.nameField,type);
				 }
				 
			 }else if(robotObj.type=='newInit'){
				 self.initData();
			 }
			 
		 });
		 self.confirmRobot = function(){
			 
		 }
		 
		 self.robotHandler = function(nameField){
//			console.info(nameField)
			self.robotIndex($('#robotIndex').val())
			$('#formulaModal').modal();
		 };
		 
		 
		 self.addField = function(){
			 
			 self.fieldArr.push({'varArr':ko.toJS(self.varArr),'field':'fdId',"dataType":"String","idField":"","nameField":""})
		 }
		 
		 self.deleteFiled = function(index){
			index = $('#robotIndex').val();
			 var fieldArr =ko.toJS(self.fieldArr)
			 self.fieldArr.removeAll();
			 for(i=0;i<fieldArr.length;i++){
				 if(i!=parseInt(index)){
					 self.fieldArr.push(fieldArr[i]);
				 }
			 }
		 }
		 
	};
	return robotNode;
});

/***********************************************
功能：转义代码中的敏感字符
***********************************************/
function strEscape(s){
	if(s==null || s=="")
		return "";
	var re = /&/g;
	s = s.replace(re, "&amp;");
	re = /\\/g;
	s = s.replace(re, "\\\\");
	re = /\"/g;
	s = s.replace(re, "&quot;");
	re = /'/g;
	s = s.replace(re, '&#39;');
	re = /</g;
	s = s.replace(re, "&lt;");
	re = /\r\n|[\r\n]/g;
	s = s.replace(re, "<BR>");
	re = />/g;
	s.replace(re, "&gt;");
	re = /\r\n|[\r\n]/g;
	return s = s.replace(re, "<BR>");
}


/***********************************************
功能：代码中转义符还原成敏感字符
***********************************************/
function strDeEscape(s){
	if(s==null || s=="")
		return "";
	var re = /<BR>/ig;
	s = s.replace(re, "\r\n");
	re = /&amp;/g;
	s = s.replace(re, "&");
	re = /&quot;/g;
	s = s.replace(re, "\"");
	re = /&#39;/g;
	s = s.replace(re, "'");	
	re = /&lt;/g;
	s = s.replace(re, "<");	
	re = /&gt;/g;
	return s.replace(re, ">");
}


//function init(){
//	var contentObj = document.getElementById("content");
//	if (parent.NodeContent != null && parent.NodeContent != '') {
//		var json =eval('('+ parent.NodeContent + ')');
//		contentObj.value = strDeEscape(json.content);
//	}
//}

function createExample(){
	var exampleCode = "";
	exampleCode += "//引入相关包\r\n";
	exampleCode += "import com.ruiyi.kmss.sys.organization.interfaces.ISysOrgCoreService;\r\n";
	exampleCode += "import com.ruiyi.kmss.sys.organization.model.SysOrgPerson;\r\n";
	exampleCode += "import com.ruiyi.kmss.util.SpringBeanUtil;\r\n";
	exampleCode += "\r\n";
	exampleCode += "protected ISysOrgCoreService sysOrgCoreService;\r\n";
	exampleCode += "public void init() {\r\n";
	exampleCode += "	// 获取bean\r\n";
	exampleCode += "	if (sysOrgCoreService == null) {\r\n";
	exampleCode += "		sysOrgCoreService = (ISysOrgCoreService) SpringBeanUtil\r\n";
	exampleCode += "				.getBean(\"sysOrgCoreService\");\r\n";
	exampleCode += "	}\r\n";
	exampleCode += "}\r\n";
	exampleCode += "\r\n";
	exampleCode += "// 以下为程序片段\r\n";
	exampleCode += "try {\r\n";
	exampleCode += "	init();\r\n";
	exampleCode += "	SysOrgPerson person = sysOrgCoreService.findByLoginName(\"admin\");\r\n";
	exampleCode += "	if (person != null) {\r\n";
	exampleCode += "		System.out.println(\"登陆名为admin的用户的用户名是：\" + person.getFdName());\r\n";
	exampleCode += "	}\r\n";
	exampleCode += "} catch (Exception e) {\r\n";
	exampleCode += "	e.printStackTrace();\r\n";
	exampleCode += "}\r\n";
		
	return exampleCode;
	
	
}
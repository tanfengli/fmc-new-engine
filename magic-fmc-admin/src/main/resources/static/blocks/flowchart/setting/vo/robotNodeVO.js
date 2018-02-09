define(["knockout"],function(ko) { 
	 
	RobotNode = function(options) { 
		var setting = $.extend({ 
			XMLNODENAME:  null,
			advice : [],
			content:null,
			id : null,
			name : null,
			unid:null,
			x : null,
			y : null,
			
			compDbcp:ko.observableArray([]),
		 	//输入参数
			inputParams  : ko.observableArray([]),
			 //输出参数
			outParams : ko.observableArray([]),
			 //数据库
			dbConnect :  ko.observable(""),
			 //填写sql
			sql : ko.observable(""),
			
			disContent : ko.observable(""),
			//机器人节点类别
			category: ko.observable(""),
			//选择小类别
			
			//小类别列表
			robotOption :ko.observableArray([]),
			//类别列表总和
		    robotInfo:ko.observableArray([]),
		
			robotNodeContent:ko.observable(),
			robotNodeUnid:ko.observable(),
			robotNodeType : ko.observable(),
		    dataTypeArr:ko.observableArray([]),
		    robotIndex : ko.observable(""),
		    compDbcpArr:ko.observableArray([]),
		    showUnid:ko.observable(),
		    varArr:ko.observableArray([]),
		    fieldArr:ko.observableArray([])
			 
		},options || {})
		
		return { 
			XMLNODENAME :  setting.XMLNODENAME,
			advice : setting.advice,
			content:setting.content,
			id : setting.id,
			name : setting.name,
			unid:setting.unid,
			x : setting.x,
			y : setting.y,
			
			compDbcp : setting.compDbcp,
			inputParams:setting.inputParams,
			outParams : setting.outParams,
			dbConnect : setting.dbConnect,
			showUnid : setting.showUnid,
			
			sql : setting.sql,
			category:setting.category,
			unid : setting.unid,
			robotOption : setting.robotOption,
			robotInfo : setting.robotInfo,
			
			robotNodeContent : setting.robotNodeContent,
			robotNodeUnid:setting.robotNodeUnid,
			robotNodeType : setting.robotNodeType,
			dataTypeArr : setting.dataTypeArr,
			robotIndex: setting.robotIndex,
			compDbcpArr:setting.compDbcpArr,
			disContent:setting.disContent,
			varArr:setting.varArr,
			fieldArr:setting.fieldArr,
		    //定义样式选择器
		    getUnid : function(){
//		    	console.info(ko.toJS(this.showUnid))
				return ko.toJS(this.showUnid);
			},
			getCategory: function(){
				return ko.toJS(this.category);
			},
			 //获取入参
			fetchParam : function(){
				 var sql = ko.toJS(this.sql);
				 var sqlArr = sql.split(":");
				 this.inputParams.removeAll();
				 for(i=1;i<sqlArr.length;i++){
					 var sqlIndex = sqlArr[i].indexOf(" ");
					 var paramObj = new Object();
					 if(sqlIndex>-1){
						 paramObj.name=sqlArr[i].substring(0,sqlIndex).replace(',',"").replace(')',"");;
					 }else{
						 paramObj.name=sqlArr[i].replace(',',"").replace(')',"");;
					 }
					 paramObj.dataType="String";
					 paramObj.idField="";
					 paramObj.nameField="";
//					 paramObj.dataTypeArr=ko.toJS(this.dataTypeArr);
					 this.inputParams.push(paramObj);
				 }
			},
			
			fetchOutParam : function(){
				 var sql = ko.toJS(this.sql);
				 var start = sql.toLocaleLowerCase().indexOf("select");
				 var end = sql.toLocaleLowerCase().indexOf("from");
				 if(start==-1||end==-1){
					 return;
				 }
				 var outSql = sql.substring(start+6,end).trim();
				 var sqlArr = outSql.split(",");
				 this.outParams.removeAll();
				 for(i=0;i<sqlArr.length;i++){
					 var paramObj = new Object();
					 paramObj.name=sqlArr[i];
					 paramObj.dataType="String";
					 paramObj.idField="";
					 paramObj.nameField="";
//					 paramObj.dataTypeArr=ko.toJS(this.dataTypeArr);
					 this.outParams.push(paramObj);
				 }
			},
			
			 //获取java示例代码
			fetchJavaExp : function(){
				  var exampleJava = this.createExample();
				  this.disContent(exampleJava);
			},
			//初始化机器人信息
			initRobotContent : function(robotContent){
				this.inputParams.removeAll();
				this.outParams.removeAll();
				this.fieldArr.removeAll();
				this.sql("");
				this.dbConnect("");
//				this.content("");
				if(robotContent==null||robotContent==''||robotContent==undefined){
					return;
				}
//					robotContent = (robotContent);
				var robotObj = eval("("+robotContent+")");
				if(ko.toJS(this.category)=='RDB'){
					if(robotObj.inputParams!=undefined&&robotObj.inputParams!=null&&robotObj.inputParams.length>0){
						for(i=0;i<robotObj.inputParams.length;i++){
							robotObj.inputParams[i].dataTypeArr=ko.toJS(this.dataTypeArr);
							this.inputParams.push(robotObj.inputParams[i]);
						 }
					}
					
					if(robotObj.outParams!=undefined&&robotObj.outParams!=null&&robotObj.outParams.length>0){
						for(i=0;i<robotObj.outParams.length;i++){
							 robotObj.outParams[i].dataTypeArr=ko.toJS(this.dataTypeArr);
							 this.outParams.push(robotObj.outParams[i]);
						 }
					}
					
					this.sql(robotObj.sql);
					this.dbConnect(robotObj.dbConnect);
//					console.info(ko.toJS(this.sql))
				}else if(ko.toJS(this.category)=='java'){
					this.disContent(this.strDeEscape(robotObj.content));
				}else if(ko.toJS(this.category)=='other'){
					for(i=0;i<robotObj.params.length;i++){
						this.fieldArr.push(robotObj.params[i]);
					 }
				}
			 },
			//处理机器人节点公式
			robotFormula : function(idField,nameField){
				 var str = $("#robotIndex").val();
				 var robLogicIndexNum = str.indexOf('robLogic');
				 var robRow;
				 var type;
				 if(robLogicIndexNum==-1){
					 robLogicIndexNum = str.indexOf('robotField');
					 robRow = parseInt(str.substring(robLogicIndexNum+10));
					 type="out";
				 }else{
					 type="input";
					 robRow = parseInt(str.substring(robLogicIndexNum+8));
				 }
				 if(robLogicIndexNum==-1){
					 return;
				 }
				 
				 var inputParams = ko.toJS(this.inputParams);
				 var fieldArr = ko.toJS(this.fieldArr);
				 if(type=='input'){
					 this.inputParams.removeAll();
					 for(i=0;i<inputParams.length;i++){
						 var param = inputParams[i];
						 if(i==robRow){
							 param.idField=idField;
							 param.nameField=nameField;
						 } 
						 this.inputParams.push(param);
					 }
				 }else{
					 this.fieldArr.removeAll();
					 for(i=0;i<fieldArr.length;i++){
						 var param = fieldArr[i];
						 if(i==robRow){
							 param.idField=idField;
							 param.nameField=nameField;
						 } 
						 this.fieldArr.push(param);
					 }
				 }
			 },
			 

			 saveRobot : function(){
				 this.unid=ko.toJS(this.showUnid);
				 if(ko.toJS(this.category)=='RDB'){
					 var robObj = new Object();
					
					 var inputArr= ko.toJS(this.inputParams);
					 var result = new Array();
					 for(i=0;i<inputArr.length;i++){
						 var input = new Object();
						 input.name= inputArr[i].name;
						 input.dataType= inputArr[i].dataType;
						 input.idField= inputArr[i].idField;
						 input.nameField= inputArr[i].nameField;
						 result.push(input)
					 }
					 
					 var outArr= ko.toJS(this.outParams);
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
					 robObj.dbConnect= ko.toJS(this.dbConnect);
					 robObj.sql= ko.toJS(this.sql);
					 var content = JSON.stringify(robObj);
//						 return strEscape(content);
					 this.content=content;
				 }else if(ko.toJS(this.category)=='java'){
					 var robObj = new Object();
					 robObj.content=ko.toJS(this.disContent);
					 var content = JSON.stringify(robObj);
					 this.content=content;
				 }else if(ko.toJS(this.category)=='other'){
					 
					 var robObj = new Object();
					 var fieldArr= ko.toJS(this.fieldArr);
					 var result = new Array();
					 for(i=0;i<fieldArr.length;i++){
						 var fieldT = new Object();
						 fieldT.field= fieldArr[i].field;
//						 fieldT.fieldName= fieldArr[i].field;
						 fieldT.dataType= fieldArr[i].dataType;
						 fieldT.idField= fieldArr[i].idField;
						 fieldT.nameField= fieldArr[i].nameField;
						 result.push(fieldT)
					 }
					 robObj.params=result;
					 var content =JSON.stringify(robObj);
//						 return strEscape(content);
					 this.content=content;
				 }
				
			 },
			 
			//初始化基本属性
		     initRobot : function(){
		    	 this.robotInfo.removeAll();
		    	 this.dataTypeArr.removeAll();
		    	   var robotObj= ko.observableArray([]);
		    	   var optionObj1 = {'name':'执行SQL语句读取数据','value':'*@Robot@ReadUseSQL','type':'RDB'}
		    	   var optionObj2 = {'name':'执行SQL语句写入数据','value':'*@Robot@WriteUseSQL','type':'RDB'}
		    	   this.robotInfo.push(optionObj1)
		    	   this.robotInfo.push(optionObj2)
		    	   
		    	   var optionObj3 = {'name':'修改表单数据','value':'*@Robot@UpdateFormData','type':'other'}
		    	   this.robotInfo.push(optionObj3)
		    	   
		    	   var optionObj4 = {'name':'创建子流程','value':'*@Robot@CreateSubflow','type':'sub'}
		    	   this.robotInfo.push(optionObj4)
		    		  
		    	   var optionObj5 = {'name':'一般语句','value':'*@Robot@RunScript','type':'java'}
		    	   this.robotInfo.push(optionObj5)
		    	   
		    	   this.dataTypeArr.push({'name':'字符串','value':'String'});
		    	   this.dataTypeArr.push({'name':'日期','value':'DateTime'});
		    	   this.dataTypeArr.push({'name':'数字','value':'Double'});
		    	   
		      },
		       
		      changeRobot : function(){
			    	  var  category =ko.toJS(this.category);
			    	  this.robotOption.removeAll();
			    	  var robotInfoArr = ko.toJS(this.robotInfo);
			    	  for(var i=0;i<robotInfoArr.length;i++){
			    		  if(category==robotInfoArr[i].type){
			    			  this.robotOption.push(robotInfoArr[i]);
				    	  } 
			    	  }
			   },
		       
			 
			 robotHandler : function(value){
					this.robotIndex(value);
					$('#formulaModal').modal();
		     },
			   //选择设置机器人前置/后置
			 computeRobot : function(robotType){
				 self.robotNodeType(robotType);
				 var newFormItem = self.nodeForm();
				 var adviceArr = newFormItem.advice;
				 
				 var robotObj = new Object();
				 robotObj.type='init'
				 if(robotType=='before'){
					 if(adviceArr.before.length==0){
						 robotObj.type='newInit';
					 }else{
						 robotObj.content=adviceArr.before[0].content;
						 robotObj.unid = adviceArr.before[0].unid;
					 }
					 this.robotNodeContent(JSON.stringify(robotObj));
					
				 }else if(robotType=='after'){
					 if(adviceArr.after.length==0){
						 robotObj.type='newInit';
					 }else{
						 robotObj.content=adviceArr.after[0].content;
						 robotObj.unid = adviceArr.after[0].unid;
					 }
					 this.robotNodeContent(JSON.stringify(robotObj));
				 }
			 },
				 
				//处理逻辑机器人节点公式
				 robotLogicFormula : function(idField,nameField){
					 var robotObj2 = new Object()
					 robotObj2.type='formula';
//					 robotObj2.rowIndex=rowIndex;
					 robotObj2.idField=idField;
					 robotObj2.nameField=nameField;
					 this.robotNodeContent(JSON.stringify(robotObj2));
			    },
				 //移除机器人节点
				removeRobot:function(data){   
				 	$('#myModal').modal("hide");
					var arrType = new Array();
					arrType.XMLNODENAME=ko.toJS(data);
					var newFormItem = self.nodeForm();
					var advice = newFormItem.advice;
					advice.CHILDRENISARRAY="false";
					advice.XMLNODENAME='advice';
					if(data=='before'){
						advice.before = arrType;
					}else if(data=='after'){
						advice.after = arrType;
					}
					newFormItem.advice=advice;
					self.nodeForm(newFormItem);
					$('#myModal').modal();
				},
				addField:function(){
					 this.fieldArr.push({'field':'fdId',"dataType":"String","idField":"","nameField":""})
				},
				deleteFiled:function(index){
					 var fieldArr =ko.toJS(this.fieldArr)
					 this.fieldArr.removeAll();
					 for(i=0;i<fieldArr.length;i++){
						 if(i!=parseInt(index)){
							 this.fieldArr.push(fieldArr[i]);
						 }
					 }
				},
				initData:function(compDbcpArr,varArr){
					this.compDbcpArr.removeAll();
					this.varArr.removeAll();
					this.compDbcpArr(compDbcpArr);
					var arrT = new Array();
					for(i=0;i<varArr.length;i++){
						if(varArr[i].varType=="solid"){
							arrT.push(varArr[i]);
						}
					}
					this.varArr(arrT)
					this.initRobot();
			    	this.category("RDB");
			    	if(this.unid==undefined||this.unid==null||this.unid==''){
			    		this.unid="*@Robot@ReadUseSQL";
			    		this.showUnid("*@Robot@ReadUseSQL");
			    	}else{
			    		this.showUnid(this.unid);
			    	}
			    	var robotInfoArr=ko.toJS(this.robotInfo);
			    	for(i=0;i<robotInfoArr.length;i++){
				    	if(this.unid==robotInfoArr[i].value){
				    		this.category(robotInfoArr[i].type);
				    		this.changeRobot(robotInfoArr[i].type);
				    		break;
				    	}
				    }
			    	this.initRobotContent(this.content);
					
				},
				selectformula:function(data){
					this.robotFormula(data.fdId,data.fdName)
				},
				initFormula:function(data){
					 var str = $("#robotIndex").val();
					 var robLogicIndexNum = str.indexOf('robLogic');
					 var robRow;
					 var type;
					 var inputParams = ko.toJS(this.inputParams);
					 var fieldArr = ko.toJS(this.fieldArr);
					 if(robLogicIndexNum==-1){
						 robLogicIndexNum = str.indexOf('robotField');
						 var robRow = parseInt(str.substring(robLogicIndexNum+10));
						 return fieldArr[robRow].nameField;
					 }else{
						 var robRow = parseInt(str.substring(robLogicIndexNum+8));
						 return inputParams[robRow].nameField;
					 }
				},
				strDeEscape:function(s){
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
				},
				
				createExample:function(){
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
				
				
		};   
		  
	};
	
	return RobotNode;
})

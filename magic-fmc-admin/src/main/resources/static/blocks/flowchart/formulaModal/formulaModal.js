
define([ 'knockout','MsgBox','AjaxUtil'], function(ko,MsgBox,AjaxUtil) {
	function formulaTree(params) {  
		
		var self = this;
		
		self.treeId = 'formulaTree';
		
		self.selectNode = ko.observable();

		self.context = ko.observableArray([]);
		
		self.selectContext = ko.observable();
		
		self.formulaRegex = ko.observable();
		self.formulaName = ko.observable(" ");
		self.formulaRegexIds = ko.observable(" ");
		self.functionArr= ko.observableArray([]);
		self.varArr= ko.observableArray([]);
		
//		$("#formulaModal").draggable({   
//		    handle: ".modal-header",   
//		    cursor: 'move',   
//		    refreshPositions: false  
//		});  
		
		AjaxUtil.call({
			url:"sys/variableSetting/initData",
			type:"get",
			success:function(data) {
				for(i=0;i<data.varList.length;i++){
					self.varArr.push(data.varList[i]);
				}
				for(i=0;i<data.functionList.length;i++){
					self.functionArr.push(data.functionList[i]);
				}
			}
		});
		
		var solidName="固定变量.";
		var defineName="自定义变量.";
		
//		self.formulaTree = new ko.treeViewModel(self.treeId, {
//			url : 'sys/variableSetting',  
//			height :400,
//			width:180,
//			callback : {
//				treeNodeChanged : function(node) {
////					self.selectNode(node);
//					if(node.varCode!=''){
//						var preValue = ko.toJS(self.formulaName);
//						var preIds = ko.toJS(self.formulaRegexIds);
//						if(preValue==undefined){
//							preValue='';
//						}
//						var nodeVarName=node.varName;
//						var nodeVarCode;
//						if(node.varType=='function'){
//							nodeVarName="$"+node.varCode+"$(";
//							if(node.varSql!=null&&node.varSql!=""){
//								nodeVarName+=node.varSql;
//							}
//							nodeVarName+=")";
//							
//						}else if(node.fdId!=undefined&&node.fdId!=null&&node.fdId!=''){
//							if(node.varType=='solid'){
//								nodeVarName="$"+solidName+nodeVarName+"$";
//							}else{
//								nodeVarName="$"+defineName+nodeVarName+"$";
//							}
//						}
//						
//						var obj =document.getElementById("formulaCompent");  
//						var formulaContent;
//						if (document.selection) {
//			                    obj.focus();
//			                    var sel = document.selection.createRange();
//			                    formulaContent = str;
//			                } else if (typeof obj.selectionStart === 'number' && typeof obj.selectionEnd === 'number') {
//			                    var startPos = obj.selectionStart;
//			                    var endPos = obj.selectionEnd;
//			                    var tmpStr = obj.value;
//			                    formulaContent = tmpStr.substring(0, startPos) + nodeVarName + tmpStr.substring(endPos, tmpStr.length);
////			                    self.formulaName(preValue+nodeVarName);
//			                } else {
//			                	formulaContent = preValue+nodeVarName;
//			                }
//						self.formulaName(formulaContent);
//						
//						
//					}else{
//						self.formulaRegexIds='';
//						self.formulaRegex='';
//					}
//					
//				}
//			},
//			nodeAdapter : {
//				afterTrans : function(newNode, node) {
////					console.info(newNode)
//					newNode.fdName=node.varName;
//					newNode.label=node.varName;
////					if(node.varType=='function'){
////						self.functionArr.push(node);
////					}else{
////						self.varArr.push(node);
////					}
//					
//					if (node.varCode == '') {
//						// this.icon = '/images/mailIcon.png'
//						newNode.items = new Array({
//							label : 'Loading...'
//						});
//					}
//					return newNode;
//				}
//			}
//		});
		self.formulaTree = new ko.zTreeView(self.treeId, {
			url : 'sys/variableSetting',
			name : 'varName',
			callback : {
				clicked : function(node) {
//					self.selectNode(node);
					if(node.varCode!=''){
						var preValue = ko.toJS(self.formulaName);
						var preIds = ko.toJS(self.formulaRegexIds);
						if(preValue==undefined){
							preValue='';
						}
						var nodeVarName=node.varName;
						var nodeVarCode;
						if(node.varType=='function'){
							nodeVarName="$"+node.varCode+"$(";
							if(node.varSql!=null&&node.varSql!=""){
								nodeVarName+=node.varSql;
							}
							nodeVarName+=")";
							
						}else if(node.fdId!=undefined&&node.fdId!=null&&node.fdId!=''){
							if(node.varType=='solid'){
								nodeVarName="$"+solidName+nodeVarName+"$";
							}else{
								nodeVarName="$"+defineName+nodeVarName+"$";
							}
						}
						
						var obj =document.getElementById("formulaCompent");  
						var formulaContent;
						if (document.selection) {
			                    obj.focus();
			                    var sel = document.selection.createRange();
			                    formulaContent = preValue+nodeVarName;
			                } else if (typeof obj.selectionStart === 'number' && typeof obj.selectionEnd === 'number') {
			                    var startPos = obj.selectionStart;
			                    var endPos = obj.selectionEnd;
			                    var tmpStr = obj.value;
			                    formulaContent = tmpStr.substring(0, startPos) + nodeVarName + tmpStr.substring(endPos, tmpStr.length);
//			                    self.formulaName(preValue+nodeVarName);
			                } else {
			                	formulaContent = preValue+nodeVarName;
			                }
						self.formulaName(formulaContent);
						
						
					}else{
						self.formulaRegexIds='';
						self.formulaRegex='';
					}
					
				}
			}
		})
		
		
		
		
		self.passSelectNode = function(){ 
			//获取的左侧选中树节点
//			params.callFunc(ko.toJS(self.selectNode)); 
			//获取待选列表选中值对象 
			var formulaInfo = new Object();
			var flag = self.validateFormula();
			if(!flag){
				return;
			}
			formulaInfo.fdId=ko.toJS(self.formulaRegexIds);
			formulaInfo.fdName=ko.toJS(self.formulaName);
			$('#formulaModal').modal('hide')
			params.callFunc(ko.toJS(formulaInfo)); 
			
		}
		
		
		self.confirmFormula = function(){ 
//			console.info(ko.toJS(self))
			self.validateFormula();
			//params.callFunc(ko.toJS(self.formulaRegex)); 
		}
		
		self.definedFormula=function(value,data, event){
			var preValue = ko.toJS(self.formulaName);
			var preIds = ko.toJS(self.formulaRegexIds);
			if(preValue==null||preValue=='null'||preValue==undefined){
				preValue='';
			}
			self.formulaName(preValue+value);
//			self.formulaRegexIds=preIds+value;
			
		}
		
		self.clearFormula=function(){
			self.formulaName("");
			self.formulaRegexIds="";
		}
		
		self.changeText = function(obj){
		}
		
		self.getNextInfo = function(script, preInfo){
			var rtnVal = {};
			rtnVal.leftIndex = script.indexOf("$", preInfo==null?0:preInfo.rightIndex+1);
			if(rtnVal.leftIndex==-1)
				return null;
			rtnVal.rightIndex = script.indexOf("$", rtnVal.leftIndex+1);
			if(rtnVal.rightIndex==-1)
				return null;
			rtnVal.varName = script.substring(rtnVal.leftIndex + 1, rtnVal.rightIndex);
			rtnVal.isFunc = script.charAt(rtnVal.rightIndex+1)=="(";
			return rtnVal;
		}
		
		self.validateFormula=function (){
			//转换表达式
			var preInfo = {rightIndex:-1};
			
			var preValue = ko.toJS(self.formulaName);
			
			var scriptOut = "";
			for (var nxtInfo = self.getNextInfo(preValue, preInfo); nxtInfo!=null; nxtInfo = self.getNextInfo(preValue, nxtInfo)) {
				var varId = self.getVarIdByName(nxtInfo.varName, nxtInfo.isFunc);
				if(varId==null){
//					MsgBox.warning('请填写审批意见！');
					MsgBox.warning((nxtInfo.isFunc ? '不存在的函数' : '不存在的遍历') + nxtInfo.varName);
					return false;
				}
				scriptOut += preValue.substring(preInfo.rightIndex+1, nxtInfo.leftIndex) + "$" + varId + "$";
				preInfo = nxtInfo;
			}
			scriptOut += preValue.substring(preInfo.rightIndex+1);
			self.formulaRegexIds=scriptOut;
			//校验两个变量并列的错误
			if(scriptOut.indexOf("$$")>-1){
				MsgBox.warning("表达式校验不通过，可能由于表达式书写错误导致！");
				return false;
			}
			
			return true;
		}
		
		//根据ID取变量名
		self.getVarNameById= function(varName, isFunc){
			if(isFunc){
				var funcInfo = dialogObject.formulaParameter.funcInfo;
				for(var i=0; i<funcInfo.length; i++){
					if(funcInfo[i].text==varName)
						return varName;
				}
			}else{
				var varInfo =  ko.toJS(self.varInfo);
				for(var i=0; i<varInfo.length; i++){
					if(varInfo.varType=='solid'){
						if(solidName+varInfo[i].name==varName)
							return varInfo[i].label;
					}else {
						if(defineName+varInfo[i].name==varName)
							return varInfo[i].label;
					}
				}
			}
		}
		
		//根据变量名取ID
		self.getVarIdByName = function(varName, isFunc){
			if(isFunc){
				var funcInfo = ko.toJS(self.functionArr);
				for(var i=0; i<funcInfo.length; i++){
					if(funcInfo[i].varCode==varName)
						return funcInfo[i].varCode;
				}
			}else{
				var varInfo = ko.toJS(self.varArr);
				for(var i=0; i<varInfo.length; i++){
					if(varInfo[i].varType=='solid'){
//						console.info(solidName+varInfo[i].varName);
						if((solidName+varInfo[i].varName)==varName)
							return varInfo[i].varCode;
					}else {
//						console.info(defineName+varInfo[i].varName);
						if((defineName+varInfo[i].varName)==varName){
							var varCode = varInfo[i].varCode;
							if(varCode.indexOf("SQL:")>-1||varCode.indexOf("JDBC:")>-1){
								return varInfo[i].varCode;
							}else{
								return "TYPE:"+varInfo[i].varType+":"+varInfo[i].varCode;
							}
								
						}
							
					}
						
				}
			}
		}
		
		params.data.subscribe(function() {
//			console.info(ko.toJS(params.data).formulaName)
			self.formulaName(ko.toJS(params.data).formulaName);
		});
	};
	return formulaTree;
});
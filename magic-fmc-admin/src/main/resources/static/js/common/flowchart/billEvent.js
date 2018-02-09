/**
 * 现有事件：
 * 1.处理人操作:驳回(驳回节点过滤)
 * 2.特权人操作:重新定位(重新定位节点过滤)
 * 3.生成即将流向HTML信息
 */
define(["knockout","../flowchart/workflow.js","../flowchart/concurrency.js",],function(ko,WorkFlow,ConCurrency){
	function BillEvent(sysWfBusinessForm){
		//开始结点标记
		var XMLNODENAME_START_NODE="startNode";
		//起草结点标记
		var XMLNODENAME_DRAFT_NODE="draftNode";
		//结束结点标记
		var XMLNODENAME_END_NODE="endNode";
		//分支节点
		var XMLNODENAME_MANUALBRANCH_NODE="manualBranchNode";
		//抄送节点
		var XMLNODENAME_SEND_NODE="sendNode";
		//人工决策
		var XMLNODENAME_AUTOBRANCH_NODE="autoBranchNode";
		//并行分支开始
		var XMLNODENAME_SPLIT_NODE="splitNode";
		//并行分支结束
		var XMLNODENAME_JOIN_NODE="joinNode";
		//审批节点
		var XMLNODENAME_REVIEW_NODE="reviewNode";
		//签字节点
		var XMLNODENAME_SIGN_NODE="signNode";
		//机器人节点
		var XMLNODENAME_ROBOT_NODE="robotNode";
		//子流程启动节点
		var XMLNODENAME_START_SUB_PROCESS_NODE="startSubProcessNode";
		//子流程回收节点
		var XMLNODENAME_RECOVER_SUB_PROCESS_NODE="recoverSubProcessNode";
		//流转方式:串行
		var PROCESSTYPE_SERIAL=0;
		//流转方式:并行
		var PROCESSTYPE_SINGLE=1;
		//流转方式:会审/会签
		var PROCESSTYPE_ALL=2;
		//模型别名
		var self=this;
		//页面业务数据
		self.sysWfBusinessForm=sysWfBusinessForm;
		//流程图对象
		self.workFlow=new WorkFlow();
		
		/**
		 * 获取流程XML详情并转化为对象
		 */
		self.WorkFlow_GetDetailsXMLObjArray=function(){
			var wfForm=self.sysWfBusinessForm;
			var fdFlowContent=wfForm.fdFlowContent;
			var detailsXMLObjArray=self.workFlow.WorkFlow_LoadXMLData(fdFlowContent);
			
			return detailsXMLObjArray;
		}
		
		/**
		 * 驳回,重新定位方法处理
		 */
		self.conCurrency=new ConCurrency(self.WorkFlow_GetDetailsXMLObjArray());
		
		/**
		 * 人工决策,下一个节点
		 */
		self.WorkFlow_DrafterOperationTypeNextNode=function(nodeId){
			var result=new Array();
			var detailsXMLObjArray=self.WorkFlow_GetDetailsXMLObjArray();
			var nextNode=self.WorkFlow_GetNextNodeObj(nodeId,detailsXMLObjArray);
			
			var currNode=self.WorkFlow_GetNodeObj(nodeId);
			if(currNode.handlerIds!=undefined){
				var lastHandlerId=currNode.handlerIds.substring(currNode.handlerIds.lastIndexOf(";")+1);
				var nodes=self.sysWfBusinessForm.nodeList;
				var currHandlerId="";
				for(var index=0;index<nodes.length;index++){
					if(nodes[index].fdFactNodeId==currNode.id){
						currHandlerId=nodes[index].fdHandlerIds;
					}
				}
				 
				if(currNode.processType=="0"&&currHandlerId!=lastHandlerId){
					result.push(currNode);
					
					return result;
				}
			}
			
			if(nextNode.XMLNODENAME==XMLNODENAME_MANUALBRANCH_NODE){
				var linesArray=detailsXMLObjArray.lines;
				var nextNodesId="";
				for(var i=0;i<linesArray.length;i++){
					var lineObj=linesArray[i];
					if(nextNode.id==lineObj.startNodeId){
						nextNodesId+=lineObj.endNodeId+";";
						continue;
					}
				}
				
				var nextNodeIds=nextNodesId.split(";");
				for(var i=0;i<nextNodeIds.length;i++){
					if(nextNodeIds[i]!=""){
						result.push(self.WorkFlow_GetNodeObj(nextNodeIds[i]));
					} 
				}
			}else{
				result.push(nextNode);
			}
			
			return result;
		}
		
		/**
		 * 处理人操作:驳回(驳回节点过滤)
		 */
		self.WorkFlow_HandlerOperationTypeRefuse=function(nodeId){
			//过滤后节点存放数组
			var optionsPassNodeArray=[];
			
			//当前处理人信息和节点详细信息
			var currNode=self.WorkFlow_GetNodeObj(nodeId);
			
			//有效节点
			var nodes=[];
			var workflow=workflow||self.WorkFlow_GetDetailsXMLObjArray();
			self.conCurrency.WorkFlow_ConcurrencyBranch_FindPreNodes(currNode,workflow,nodes);
			
			var joinNode=nodes['joinNode'];
			var splitNode=nodes['splitNode'];
			
			for(var i=0;i<nodes.length;i++){
				var nodeInfo=self.WorkFlow_GetNodeObj(nodes[i].id);
				
				//过滤当前节点
			 	if(nodeInfo.id==currNode.id){
					continue;
				}
			 	
				
			 	if(nodeInfo.XMLNODENAME=="joinNode"){
			 		continue;
			 	}
				
				var itemShowStr=nodeInfo.id+"."+nodeInfo.name;
				if(nodeInfo.handlerNames!=null){
					itemShowStr+="("+nodeInfo.handlerNames+")";
				} 
				
				optionsPassNodeArray.push({id:nodeInfo.id,name:itemShowStr});
			}
		
			if(joinNode!=undefined&&splitNode!=undefined){
				optionsPassNodeArray.push({id:joinNode.id,name:joinNode.name});
			}
			
			return optionsPassNodeArray;
		}
		
		/**
		 * 特权人操作:重新定位(重新定位节点过滤)
		 * @return 重新定位节点数组
		 */
		self.WorkFlow_AdminOperationTypeJump=function(nodeId){
			//过滤后节点存放数组
			var optionsPassNodeArray=[];
			
			//当前处理人信息和节点详细信息
			var currNode=self.WorkFlow_GetNodeObj(nodeId);
			
			//有效节点
			var nodes=[];
			var workflow=workflow||self.WorkFlow_GetDetailsXMLObjArray();
			self.conCurrency.WorkFlow_ConcurrencyBranch_FindPreNodes(currNode,workflow,nodes);
			var autoBranchNode=nodes['autoBranchNode'];
			if(autoBranchNode!=undefined){
				self.conCurrency.WorkFlow_ConcurrencyBranch_FindNextNodes(autoBranchNode,workflow,nodes);
			}
			self.conCurrency.WorkFlow_ConcurrencyBranch_FindNextNodes(currNode,workflow,nodes);
			
			var joinNode=nodes['joinNode'];
			var splitNode=nodes['splitNode'];
			
			for(var i=0;i<nodes.length;i++){
				var nodeInfo=self.WorkFlow_GetNodeObj(nodes[i].id);
				
				//过滤当前节点
			 	if(nodeInfo.id==currNode.id){
					continue;
				}
			 	
				
			 	if(nodeInfo.XMLNODENAME=="joinNode"){
			 		continue;
			 	}
				
				var itemShowStr=nodeInfo.id+"."+nodeInfo.name;
				if(nodeInfo.handlerNames!=null){
					itemShowStr+="("+nodeInfo.handlerNames+")";
				} 
				
				optionsPassNodeArray.push({id:nodeInfo.id,name:itemShowStr});
			}
		
			if(joinNode!=undefined&&splitNode!=undefined){
				optionsPassNodeArray.push({id:joinNode.id,name:joinNode.name});
			}
			
			return optionsPassNodeArray;
		}
		
		/**
		 * 生成即将流向HTML信息
		 * BUSSIFORM中所需参数:1.模板XML详情,2.fdProcessorInfoXML
		 * @return 即将流向HTML
		 */
		self.WorkFlow_GenerateNextNodeInfo=function(){
			//即将流向HTML
			var html='';
			//下一处理人
			var nextShowHandlerId,nextShowHandlerName;
			//当前处理人信息
			var operatorInfo=self.WorkFlow_AnalysisProcessorInfoToObject();
			//当前节点信息
			var currentNodeObj=self.WorkFlow_GetNodeObj(operatorInfo.nodeId);
			
			
			var currHandlerOffset=operatorInfo.currHandlerOffset-0+1;
			if(currentNodeObj.handlerIds!=null){
				var nodeHandlerIdArray=currentNodeObj.handlerIds.split(";");
				var nodeHandlerNameArray=currentNodeObj.handlerNames.split(";");
				if(currHandlerOffset<=nodeHandlerIdArray.length){ 
					nextShowHandlerId=nodeHandlerIdArray[currHandlerOffset];
					nextShowHandlerName=nodeHandlerNameArray[currHandlerOffset];
				} 
			}
			
			//串行按比例,判断是否通过比例人数,通过则显示流转到下一节点
			//0为全部,1为按比例
			if(currentNodeObj.processType==0){
				if(currentNodeObj.passType==1){
					var currentNodeHandlerIds=currentNodeObj.srcHandlerIds.split(";");
					var requiredPassPersons=currentNodeObj.passPercent/100*currentNodeHandlerIds.length;
					if(currHandlerOffset>=requiredPassPersons){
						nextShowHandlerId=null;
					}
				}
			} 
			
			//是则显示同一节点下一个处理人并不允许编辑
			//不是则显示下一个节点的所有处理人并根据权限显示是否编辑
			if(operatorInfo.toRefuseThisNodeId!=""&&operatorInfo.toRefuseThisHandlerName!=null){
				var nodeObj=self.WorkFlow_GetNodeObj(operatorInfo.toRefuseThisNodeId);
				var lineObj=self.WorkFlow_GetLineObj(nodeObj.id);
				var lineName=lineObj.name == null?"":lineObj.name;
				var handlerName=operatorInfo.toRefuseThisHandlerName;
				//如果下一节点为驳回节点,检查驳回人是否还在,不在则即将流向改为下一节点处理人列表 
				var handlerNamesArray=nodeObj.handlerNames.split(/[,;]/);		
				var isChanged=true;
				for(var i=0;i<handlerNamesArray.length;i++){
					if(handlerNamesArray[i]==handlerName){
						isChanged=false;
					}
				}
				
				if(nodeObj.processType!=PROCESSTYPE_SERIAL||isChanged){
					handlerName=nodeObj.handlerNames;
				}else if(nextShowHandlerId!=null){
					nodeObj=currentNodeObj;
					handlerName=nextShowHandlerName;
				}
				html = '<label id="nextNodeName"><b>' + nodeObj.id + "." + nodeObj.name + '</b></label>';
				html += '(<input type="hidden" id="handlerIds" value="">';
				html += '<input type="hidden" id="handlerNames" readonly class="inputSgl" onChange="WorkFlow_SetHandlerInfoes();" value="' + Com_HtmlEscape(operatorInfo.toRefuseThisHandlerName) + '">';
				html += '<label id="handlerShowNames" nodeId="' + nodeObj.id + '">' + Com_HtmlEscape(handlerName.replace(/;/g, '; ')) + '</label>';
				html += ')';
			}else if(nextShowHandlerId != null && currentNodeObj.processType == PROCESSTYPE_SERIAL){
				html = '<label id="nextNodeName"><b>' + currentNodeObj.id + "." + currentNodeObj.name + '</b></label>';
				html += '(<input type="hidden" id="handlerIds" value="' +nextShowHandlerId+ '">';
				html += '<input type="hidden" id="handlerNames" readonly class="inputSgl" onChange="WorkFlow_SetHandlerInfoes();" value="' + Com_HtmlEscape(nextShowHandlerName) + '">';
				html += '<label id="handlerShowNames" class=handlerNamesLabel nodeId="' + currentNodeObj.id + '">' + Com_HtmlEscape(nextShowHandlerName.replace(/;/g, '; ')) + '</label>';
				html += ')';
			}else{
				var nextNodeObj=self.WorkFlow_GetNextNodeObj(operatorInfo.nodeId);
				//分支节点(人工决策节点生成)
				if(nextNodeObj.XMLNODENAME==XMLNODENAME_MANUALBRANCH_NODE){
					html=self.WorkFlow_GetNextNodesInfo(nextNodeObj.id);
					
					return html;
				}else{
					var nextNodeLineObj=self.WorkFlow_GetLineObj(operatorInfo.nodeId);
					var nextNodeLineName=nextNodeLineObj.name == null?"":nextNodeLineObj.name;
					//下一节点名称生成
					html='<label id="nextNodeName"><b>'+nextNodeLineName+" "+nextNodeObj.id + "."+nextNodeObj.name+'</b></label>';
					var nextNodeHandlerIds="";
					var nextNodeHandlerNames="";
					if(nextNodeObj.handlerIds!=null){
						nextNodeHandlerIds=nextNodeObj.handlerIds;
						nextNodeHandlerNames=nextNodeObj.handlerNames;
					}
					
					if(nextNodeObj.XMLNODENAME==XMLNODENAME_REVIEW_NODE||nextNodeObj.XMLNODENAME==XMLNODENAME_SEND_NODE||nextNodeObj.XMLNODENAME==XMLNODENAME_SIGN_NODE){
						//增加敏感字符的处理 
						html += '(<input type="hidden" id="handlerIds" value="' +Com_HtmlEscape(nextNodeHandlerIds)+ '" isFormula='+(nextNodeObj.handlerSelectType == 'org' ? false : true)+'>';	
						html += '<input type="hidden" id="handlerNames" readonly class="inputSgl" onChange="WorkFlow_SetHandlerInfoes();" value="' + Com_HtmlEscape(nextNodeHandlerNames) + '">';			
						//如果是处理人为公式计算则不显示原公式改为显示“公式计算” 
						
						var dataNextNodeHandler=[];
						//公式人员计算
						if(nextNodeObj.handlerSelectType=="formula"){	
							html += '<label id="handlerShowNames" class=handlerNamesLabel nodeId="' + nextNodeObj.id + '">' + '暂时无法计算处理人' + '</label>';
						}
						else{//组织架构人员计算
							html += '<label id="handlerShowNames" class=handlerNamesLabel nodeId="' + nextNodeObj.id + '">' + nextNodeHandlerNames + '</label>';
						}
						html += ')';
					}
				}
			}
			
			return html;
		}
		
		/**
		 * 取得有效节点
		 */
		self.WorkFlow_GetAvailableNodes=function(){
			var availableNodeInfo="";
			//取得流程定义XML中有效的流程节点
			var detailsXMLObj=self.sysWfBusinessForm.fdFlowContent;
			var detailsXMLObjArray=self.workFlow.WorkFlow_LoadXMLData(detailsXMLObj);
			var nodesArray=detailsXMLObjArray.nodes;
			
			for(var i=0;i<nodesArray.length;i++){
				var nodeObj=nodesArray[i];
				if(nodeObj.XMLNODENAME==XMLNODENAME_START_NODE||nodeObj.XMLNODENAME==XMLNODENAME_END_NODE|| 
					nodeObj.XMLNODENAME==XMLNODENAME_MANUALBRANCH_NODE|| 
					nodeObj.XMLNODENAME==XMLNODENAME_AUTOBRANCH_NODE){
					continue;
				}
				
				availableNodeInfo+=nodeObj.id+":"+nodeObj.name+";";
			}
			
			if(availableNodeInfo.lastIndexOf(";")==(availableNodeInfo.length-1)){
				availableNodeInfo=availableNodeInfo.substring(0,availableNodeInfo.length-1);
			}
			
			return availableNodeInfo;
		}
		
		/**
		 * 取得当前节点的对象信息
		 */
		self.WorkFlow_GetNodeObj=function(nodeId,detailsXMLObjArray){
			if(nodeId==''||nodeId==null){
				return {};
			}
			
			detailsXMLObjArray=detailsXMLObjArray||self.WorkFlow_GetDetailsXMLObjArray();
			var nodesArray=detailsXMLObjArray.nodes;
			for(var i=0;i<nodesArray.length;i++){
				var nodeObj=nodesArray[i];
				if(nodeId==nodeObj.id){
					return nodeObj;
				}
			}
		}
		
		/**
		 * 节点IDs转化为Name
		 */
		self.nodeIdsToNodeNames=function(nodeIds,splitString,detailsXMLObjArray){
			if(nodeIds==''||nodeIds==null){
				return '';
			}
			var nodeIdArray = nodeIds.split(splitString||';');
			if(!nodeIdArray||nodeIdArray.length==0)
				return '';
			
			detailsXMLObjArray=detailsXMLObjArray||self.WorkFlow_GetDetailsXMLObjArray();
			var nodeNames = '';
			var nodesArray=detailsXMLObjArray.nodes;
			for(i=0;i<nodeIdArray.length;i++){
				for(var j=0;j<nodesArray.length;j++){
					var nodeObj=nodesArray[j];
					if(nodeIdArray[i]==nodeObj.id){
						nodeNames+=nodeObj.name;
						if(i!=nodeIdArray.length-1)
							nodeNames+=';';
					}
			}}
			return nodeNames;
		}
		
		/**
		 * 解析当前处理人的Info,返回当前操作对象
		 */
		self.WorkFlow_AnalysisProcessorInfoToObject=function(){
			var processorInfoObj=self.WorkFlow_GetProcessorInfoObj();
			if(processorInfoObj==null||processorInfoObj.length==0){
				return;
			}
			
			return processorInfoObj[0];
		} 
		
		/**
		 * 获取当前处理人信息(起草人,特权人,审批人)
		 */
		self.WorkFlow_GetProcessorInfoObj=function(){
			//审批人
			var fdProcessorInfoXML=self.sysWfBusinessForm.fdProcessorInfoXML;
			if(fdProcessorInfoXML!=undefined){
				return self.workFlow.WorkFlow_LoadXMLData(fdProcessorInfoXML);
			}
		}
		
		/**
		 * 判断是否在分支
		 */
		self.isInBranch=function(nodeId){
			//当前节点详细信息
			var currNode=self.WorkFlow_GetNodeObj(nodeId);
			var isInBranch=self.conCurrency.WorkFlow_ConcurrencyBranch_GetBranchNodes(currNode).length>0;
			
			return isInBranch;
		}
		
		/**
		 * 判断是否含有子流程节点
		 */
		self.isSubProcess=function(nodeId){
			var workflow=workflow||self.WorkFlow_GetDetailsXMLObjArray();
			var currNode=self.WorkFlow_GetNodeObj(nodeId);
			var nodes=[];
			self.conCurrency.WorkFlow_ConcurrencyBranch_FindPreAllNodes(currNode,workflow,nodes);
			if(nodes.length>0){
				for(var i=0;i<nodes.length;i++){
					var node=nodes[i];
					if(node.XMLNODENAME==XMLNODENAME_START_SUB_PROCESS_NODE){
						return true;
					}
				}
			}
			
			return false;
		}
		
		/**
		 * 过滤流程图节点
		 */
		self.filterFlowChartNodes=function(historyNodes,currentNodes,node,nodes){
			var workflow=workflow||self.WorkFlow_GetDetailsXMLObjArray();
			var currNode=self.WorkFlow_GetNodeObj(node);
			nodes.push(currNode);
			
			var nextNodes=self.conCurrency.WorkFlow_ConcurrencyBranch_GetOutingNodes(currNode,workflow);
			
			for(var i=0;i<nextNodes.length;i++){
				var nextNode=nextNodes[i];
				
				for(var j=0;j<historyNodes.length;j++){
					var historyNode=historyNodes[j];
					if(nextNode.id==historyNode.fdTargetId){
						self.filterFlowChartNodes(historyNodes,currentNodes,nextNode.id,nodes);
						
						return nodes;
					}
				}
				
				for(var k=0;k<currentNodes.length;k++){
					var currentNode=currentNodes[k];
					if(nextNode.id==currentNode.fdFactNodeId){
						self.filterFlowChartNodes(historyNodes,currentNodes,nextNode.id,nodes);
						
						return nodes;
					}
				}
				
				self.filterFlowChartNodes(historyNodes,currentNodes,nextNode.id,nodes);
			}
			
			return nodes;
		}
		
		/**
		 * 过滤流程图线
		 */
		self.filterFlowChartLines=function(nodes,lines){
			var workflow=workflow||self.WorkFlow_GetDetailsXMLObjArray();
			var workflowLines=workflow.lines;
			for(var i=0;i<workflowLines.length;i++){
				var workflowLine=workflowLines[i];
				var startNode=false;
				var endNode=false;
				for(var j=0;j<nodes.length;j++){
					var node=nodes[j];
					if(workflowLine.startNodeId==node.id){
						startNode=true;
					}
					
					if(workflowLine.endNodeId==node.id){
						endNode=true;
					}
				}
				
				if(startNode&&endNode){
					lines.push(workflowLine);
				}
			}
		}
		
		self.filterFlowChart=function(nodes,lines){
			var workflow=workflow||self.WorkFlow_GetDetailsXMLObjArray();
			workflow.lines=lines;
			workflow.nodes=nodes;
			
			return self.workFlow.WorkFlow_BuildXMLString(workflow,workflow.XMLNODENAME);
		}
		
		/**
		 * 取得手工决策节点下的所有节点的信息
		 */
		self.WorkFlow_GetNextNodesInfo=function(nodeId){
			var detailsXMLObjArray=self.WorkFlow_GetDetailsXMLObjArray();
			//所有连线信息
			var linesArray=detailsXMLObjArray.lines;
			var currentNodeObj=self.WorkFlow_GetNodeObj(nodeId);
			
			//获取流向节点
			var nextNodesId="";
			for(var i=0;i<linesArray.length;i++){
				var lineObj=linesArray[i];
				if(nodeId==lineObj.startNodeId){
					nextNodesId+=lineObj.endNodeId+";";
					continue;
				}
			}
			
			//所有节点信息
			var nodesArray=detailsXMLObjArray.nodes;
			var nextNodeIds=nextNodesId.split(";");
			
			var arr=new Array();
			for(var i=0;i<nextNodeIds.length;i++){
				if(nextNodeIds[i]!=""){
					var obj={name:"",value:""};
					var html=""; 
					var nodeObj=self.WorkFlow_GetNodeObj(nextNodeIds[i]);
					var lineObj={};
					var lineObjs=self.WorkFlow_GetLineObjs(nodeObj.id,false);
					for(var lineIndex=0;lineIndex<lineObjs.length;lineIndex++){
						if(currentNodeObj.id==lineObjs[lineIndex].startNodeId){
							lineObj=lineObjs[lineIndex];
						}
					}
					var lineName=lineObj.name==null?"":lineObj.name;
					html+="<b>"+lineName+" "+nodeObj.id+"."+nodeObj.name+"</b>"
					
					obj.value = nodeObj.id;
					
					if(nodeObj.XMLNODENAME!="endNode"){
						var hiddenIdObj=document.createElement("input");
						hiddenIdObj.type="hidden"; 
						hiddenIdObj.id="handlerIds["+i+"]";
						var handlerIds,handlerNames,isFormulaType=(nodeObj.handlerSelectType=='<%=OAConstant.HANDLER_SELECT_TYPE_FORMULA%>');
						handlerIds=nodeObj.handlerIds==null?"":nodeObj.handlerIds;
						handlerNames=nodeObj.handlerNames==null?"":(isFormulaType?"<公式计算>":nodeObj.handlerNames);
						hiddenIdObj.value=handlerIds;
						hiddenIdObj.isFormula=isFormulaType.toString(); //设置是否为公式
						html+=hiddenIdObj.outerHTML; 
						
						var hiddenNameObj=document.createElement("input");
						hiddenNameObj.type="hidden"; 
						hiddenNameObj.id="handlerNames[" + i + "]";
						hiddenNameObj.value=handlerNames;
						if(handlerNames!=''){ html+="（";}
						html+=hiddenNameObj.outerHTML;
						//如果是处理人为公式计算则不显示原公式改为显示“公式计算”  
						if(nodeObj.handlerSelectType=="formula"){
							html+="<label id='handlerShowNames[" + i + "]' class='handlerNamesLabel'";
							html+=" nodeId='" + nodeObj.id + "'>" + '公式计算' + "</label>";
						}
						else{
							html+="<label id='handlerShowNames[" + i + "]' class='handlerNamesLabel'";
							html+=" nodeId='" + nodeObj.id + "'>" + Com_HtmlEscape(handlerNames.replace(/;/g, '; ')) + "</label>";
						}
						
						if(handlerNames!=''){html += "）";}
					}
					html+="<br>"; 
					obj.name=html;
					arr.push(obj);
				} 
			}

			return arr; 
		}
		
		/**
		 * 检查当前处理节点是否有修改下一节点的权限
		 */
		self.WorkFlow_CheckModifyNextNodeAuthorization=function(nodeId){
			if(undefined==nodeId){
				var operatorInfo=self.WorkFlow_AnalysisProcessorInfoToObject();
				nodeId=operatorInfo.nodeId;
			}
			
			var currentNodeId=nodeId; 	
			var currentNodeObj=self.WorkFlow_GetNodeObj(currentNodeId);
			var nextNodeObj=self.WorkFlow_GetNextNodeObj(currentNodeId);
			var nextNodeId=nextNodeObj.id;
			
			if(currentNodeObj.canModifyHandlerNodeIds!=null&&currentNodeObj.canModifyHandlerNodeIds!=""){
				var index=(currentNodeObj.canModifyHandlerNodeIds+";").indexOf(nextNodeId+";");
				if(index!=-1){
					return true;
				}
			}
			
			if(currentNodeObj.mustModifyHandlerNodeIds!=null&&currentNodeObj.mustModifyHandlerNodeIds!=""){
				var index=(currentNodeObj.mustModifyHandlerNodeIds+";").indexOf(nextNodeId+";");
				if(index!=-1){
					return true;
				}
			}

			return false;
		}
		
		/**
		 * 检查是否有修改其他节点权限（不包含下一节点）
		 */
		self.WorkFlow_GetModifyNodeList=function(nodeId){
			if(undefined==nodeId){
				var operatorInfo=self.WorkFlow_AnalysisProcessorInfoToObject();
				nodeId=operatorInfo.nodeId;
			}
			var currentNodeId=nodeId; 	
			var currentNodeObj=self.WorkFlow_GetNodeObj(currentNodeId);
			var nextNodeObj=self.WorkFlow_GetNextNodeObj(currentNodeId);
			var nextNodeId=nextNodeObj.id;
			var ids=new Array();
			
			
			
			//可修改节点
			if(currentNodeObj.canModifyHandlerNodeIds!=null&&currentNodeObj.canModifyHandlerNodeIds!=""){
				ids=currentNodeObj.canModifyHandlerNodeIds.split(";")
			}
			
			//必须修改节点
			if(currentNodeObj.mustModifyHandlerNodeIds!=null&&currentNodeObj.mustModifyHandlerNodeIds!=""){
				var mustModefiyIds=currentNodeObj.mustModifyHandlerNodeIds.split(';');
				ids=ids.concat(mustModefiyIds);
			}
			
			//去重并去除相邻节点
			ids.unique();
			var arr=ko.observableArray(ids);
			arr.remove(nextNodeId);
			ids=arr();
			var idList=[];
			for(i=0;i<ids.length;i++){
				var nodeObj=self.WorkFlow_GetNodeObj(ids[i]);
				idList.push(nodeObj)
			}
			
			return idList;
		}
		
		/**
		 * 取得下一个节点
		 */
		self.WorkFlow_GetNextNodeId=function(nodeId,detailsXMLObjArray){
			detailsXMLObjArray=detailsXMLObjArray||self.WorkFlow_GetDetailsXMLObjArray();
		 	var linesArray=detailsXMLObjArray.lines;
		 	var nextNodeId=null;
		 	for(var i=0;i<linesArray.length;i++){
		 		var lineObj=linesArray[i];
		 		if(nodeId==lineObj.startNodeId){
		 			nextNodeId=lineObj.endNodeId;
		 			break;
		 		}
		 	}
		 	
		 	return nextNodeId;
		}
		
		/**
		 * 取得当前节点的连线对象信息
		 */
		self.WorkFlow_GetLineObj=function(nodeId,showStartNode,detailsXMLObjArray){
		 	detailsXMLObjArray=detailsXMLObjArray||self.WorkFlow_GetDetailsXMLObjArray();
		 	var linesArray=detailsXMLObjArray.lines;
		 	for(var i=0;i<linesArray.length;i++){
		 		var lineObj=linesArray[i];
		 		if(showStartNode==null||showStartNode==true){
		 			if(nodeId==lineObj.startNodeId){
		 				return lineObj;
		 			}
		 		}else{
		 			if(nodeId==lineObj.endNodeId){
		 				return lineObj;
		 			}
		 		}
		 	}
		 }
	 
		/**
		 * 取得当前节点的连线对象信息数组
		 */
		self.WorkFlow_GetLineObjs=function(nodeId,showStartNode,detailsXMLObjArray){
		 	detailsXMLObjArray=detailsXMLObjArray||self.WorkFlow_GetDetailsXMLObjArray();
		 	var linesArray=detailsXMLObjArray.lines;
		 	var lines=[];
		 	for(var i=0;i<linesArray.length;i++){
		 		var lineObj=linesArray[i];
		 		if(showStartNode==null||showStartNode==true){
		 			if(nodeId==lineObj.startNodeId){
		 				lines.push(lineObj);
		 			}
		 		}else{
		 			if(nodeId==lineObj.endNodeId){
		 				lines.push(lineObj);
		 			}
		 		}
		 	}
		 	
		 	return lines;
		}
		 
		/**
		 * 取得下一个节点的对象
		 */
		self.WorkFlow_GetNextNodeObj=function(nodeId,detailsXMLObjArray){
		 	detailsXMLObjArray=detailsXMLObjArray||self.WorkFlow_GetDetailsXMLObjArray();
		 	var linesArray=detailsXMLObjArray.lines;
		 	var nextNodeId=null;
		 	for(var i=0;i<linesArray.length;i++){
		 		var lineObj=linesArray[i];
		 		if(nodeId==lineObj.startNodeId){
		 			nextNodeId=lineObj.endNodeId;
		 			break;
		 		}
		 	}
		 	
		 	return self.WorkFlow_GetNodeObj(nextNodeId,detailsXMLObjArray)
		}
		
		/**
		 * 数组删除元素
		 */
	 	Array.prototype.remove=function(dx){
			if(isNaN(dx)||dx>this.length){
				return false;
			}
			
			for(var i=0,n=0;i<this.length;i++){
				if(this[i]!=this[dx]){
					this[n++]=this[i]
				}
			}
			
			this.length-=1;
	 	}
	 
		/**
		 * 数组去重
		 */
		Array.prototype.unique=function(){
			var res=[];
			var json={};
			for(var i=0;i<this.length;i++){
				if(!json[this[i].fdId]){
				  res.push(this[i]);
				  json[this[i].fdId]=1;
				}
			}
			
			return res;
		}
		 
		/**
		 *功能：替换HTML代码中的敏感字符
		 */
		function Com_HtmlEscape(s){
			var re = /&/g;
			
			if(s==null||s==""){
				return "";
			}
			s = s.replace(re,"&amp;");
			re = /\"/g;
			s = s.replace(re,"&quot;");
			re = /'/g;
			s = s.replace(re,'&#39;');
			re = /</g;
			s = s.replace(re,"&lt;");
			re = />/g;
			
			return s.replace(re,"&gt;");
		}
	}

	return BillEvent;
});
define(["knockout"],function(ko){
	function ConCurrency(xml){
	   /**
		* 开始结点标记
		*/
		var XMLNODENAME_START_NODE="startNode";
		
	   /**
		* 起草结点标记
		*/
		var XMLNODENAME_DRAFT_NODE="draftNode";
		
	   /**
		* 结束结点标记
		*/
		var XMLNODENAME_END_NODE="endNode";
		
	   /**
		* 分支节点
		*/
		var XMLNODENAME_MANUALBRANCH_NODE="manualBranchNode";
		
	   /**
		* 抄送节点
		*/
		var XMLNODENAME_SEND_NODE="sendNode";
		
	   /**
		* 人工决策
		*/
		var XMLNODENAME_AUTOBRANCH_NODE="autoBranchNode";

	   /**
		* 并行分支开始
		*/
		var XMLNODENAME_SPLIT_NODE="splitNode";

	   /**
		* 并行分支结束
		*/
		var XMLNODENAME_JOIN_NODE="joinNode";

	   /**
		* 审批节点
		*/
		var XMLNODENAME_REVIEW_NODE="reviewNode";
		
		/**
		 * 签字节点
		 */
		var XMLNODENAME_SIGN_NODE="signNode";
		
		/**
		 * 机器人节点
		 */
		var XMLNODENAME_ROBOT_NODE="robotNode";
		
		/**
		 * 子流程启动节点
		 */
		var XMLNODENAME_START_SUB_PROCESS_NODE="startSubProcessNode";
		
		/**
		 * 子流程回收节点
		 */
		var XMLNODENAME_RECOVER_SUB_PROCESS_NODE="recoverSubProcessNode";

		/**
		 * 流程图所有节点
		 */
		var WorkFlow_GetDetailsXMLObjArray=ko.observableArray(xml);
	
		var self=this;
		
		/**
		 * 判断是否有并行分支
		 */
		self.WorkFlow_ConcurrencyBranch_HasBranch=function(workflow){
			workflow=workflow||WorkFlow_GetDetailsXMLObjArray();
			var nodes=workflow.nodes;
			for(var i=0;i<nodes.length;i++){
				var n=nodes[i];
				if(n.XMLNODENAME==XMLNODENAME_SPLIT_NODE||n.XMLNODENAME==XMLNODENAME_JOIN_NODE){
					return true;
				}
			}
			
			return false;
		}
	
		/**
		 * 查找当前节点的输入节点
		 */
		self.WorkFlow_ConcurrencyBranch_GetIncommingNodes=function(node,workflow){
			return self.WorkFlow_ConcurrencyBranch_GetInOutNodes(node,workflow,false);
		}
	
		/**
		 * 查找当前节点的输出节点 
		 */
		self.WorkFlow_ConcurrencyBranch_GetOutingNodes=function(node,workflow){
			return self.WorkFlow_ConcurrencyBranch_GetInOutNodes(node,workflow,true);
		}
	
		/**
		 * 查找当前节点的输入或输出节点(next:true为输出,false为输入)
		 */
		self.WorkFlow_ConcurrencyBranch_GetInOutNodes=function(node,workflow,next){
			var lines=workflow.lines;
			var nodes=workflow.nodes;
			var resultNodeIds=[];
			var nodeObjs=[];
			var i,j;
			
			for(i=0;i<lines.length;i++){
				var line=lines[i];
				var nodeId=next?line.startNodeId:line.endNodeId;
			
				if(node.id==nodeId){
					resultNodeIds.push(next?line.endNodeId:line.startNodeId);
				}
			}
			
			if(resultNodeIds.length>0){
				for(i=0;i<nodes.length;i++){
					for(j=0;j<resultNodeIds.length;j ++){
						if(nodes[i].id==resultNodeIds[j]){
							nodeObjs.push(nodes[i]);
							break;
						}
					}
					
					if(nodeObjs.length==resultNodeIds.length){
						break;
					}
				}
			}
			
			return nodeObjs;
		}
	
		/**
		 * 查看流程图是否包含该节点
		 */
		self.WorkFlow_ConcurrencyBranch_ContentsNode=function(nodes,node){
			for(var n=0;n<nodes.length;n++){
				if(node.id==nodes[n].id){
					return true;
				}
			}
			
			return false;
		}
	
		/**
		 * 获取当前节点前面所有节点
		 */
		self.WorkFlow_ConcurrencyBranch_FindPreNodes=function(curr,workflow,nodes){
			var pres=self.WorkFlow_ConcurrencyBranch_GetIncommingNodes(curr,workflow);
			
			for(var i=0;i<pres.length;i++){
				var pNode=pres[i];
				if(self.WorkFlow_ConcurrencyBranch_ContentsNode(nodes,pNode)){
					continue;
				}
				
				if(pNode.XMLNODENAME==XMLNODENAME_START_NODE){
					return [];
				}
				
				if(pNode.XMLNODENAME==XMLNODENAME_SPLIT_NODE){
					nodes["splitNode"]=pNode;
					continue;
				}
				
				if(pNode.XMLNODENAME==XMLNODENAME_AUTOBRANCH_NODE){
					nodes["autoBranchNode"]=pNode;
				}
				
				nodes.push(pNode);
				
				if(pNode.XMLNODENAME==XMLNODENAME_JOIN_NODE){
					var subBranch=[];
					self.WorkFlow_ConcurrencyBranch_FindPreNodes(pNode,workflow,subBranch);
					pNode=subBranch["splitNode"];
					nodes.push(pNode);
				}
				
				self.WorkFlow_ConcurrencyBranch_FindPreNodes(pNode,workflow,nodes);
			}
		}
	    
		/**
		 * 获取当前节点前面所有节点
		 */
		self.WorkFlow_ConcurrencyBranch_FindPreAllNodes=function(curr,workflow,nodes){
			var pres=self.WorkFlow_ConcurrencyBranch_GetIncommingNodes(curr,workflow);
			for(var i=0;i<pres.length;i++){
				var pNode=pres[i];
				if(self.WorkFlow_ConcurrencyBranch_ContentsNode(nodes,pNode)){
					continue;
				}
				
				if(pNode.XMLNODENAME==XMLNODENAME_START_NODE){
					return [];
				}
			
				nodes.push(pNode);
				
				self.WorkFlow_ConcurrencyBranch_FindPreNodes(pNode,workflow,nodes);
			}
		}
		
		/**
		 * 获取当前节点后面所有节点
		 */
		self.WorkFlow_ConcurrencyBranch_FindNextNodes=function(curr,workflow,nodes){
			var nexts=self.WorkFlow_ConcurrencyBranch_GetOutingNodes(curr,workflow);
			for(var i=0;i<nexts.length;i++){
				var nNode=nexts[i];
				
				if(self.WorkFlow_ConcurrencyBranch_ContentsNode(nodes,nNode)) {
					continue;
				}
			
				if(nNode.XMLNODENAME==XMLNODENAME_END_NODE){
					return [];
				}
			
				if(nNode.XMLNODENAME==XMLNODENAME_JOIN_NODE){
					nodes["joinNode"]=nNode;
					continue;
				}
			
				nodes.push(nNode);
			
				if(nNode.XMLNODENAME==XMLNODENAME_SPLIT_NODE){
					var subBranch=[];
					self.WorkFlow_ConcurrencyBranch_FindNextNodes(nNode,workflow,subBranch);
					nNode=subBranch["joinNode"];
					nodes.push(nNode);
				}
				
				self.WorkFlow_ConcurrencyBranch_FindNextNodes(nNode,workflow,nodes);
			}
		}
	
		/**
		 * 获取当前节点的所有节点
		 */
		self.WorkFlow_ConcurrencyBranch_GetBranchNodes=function(curr,workflow){
			var nodes=[];
			workflow=workflow||WorkFlow_GetDetailsXMLObjArray();
			self.WorkFlow_ConcurrencyBranch_FindNextNodes(curr,workflow,nodes);
			self.WorkFlow_ConcurrencyBranch_FindPreNodes(curr,workflow,nodes);
			
			return nodes;
		}
	}
	
	return ConCurrency;
});	
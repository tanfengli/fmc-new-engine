define([ "knockout",'AjaxUtil',"./orgElementVO.js","components","bootstrap" ], function(ko,AjaxUtil,
		OrgElement) {  
	 
    function viewModel() {
		"use strict";
	
		var self = this;
		//组织类型
		self.elementSelected = ko.observableArray([ "1", "2", "4", "8" ])
		//查询条件
		self.filterName = ko.observable();
		//组织架构状态
		self.orgStatus = ko.observableArray(["1"]);
		//单击组织架构树选择节点
		self.selectNode = ko.observable();
		
		self.selectNodeType = ko.observable();
		  
		self.editorArray = ko.observableArray([]);
		
		//所属上级
		self.description = ko.observable();
		
		//描述
		self.showDescription = function(fdId){
			 if(fdId){
				 AjaxUtil.call({
					url: "sys/orgElement/getElementAllPath",   
					type : "post",
					data : {fdId: fdId},
					dataType : "JSON",
					async:false,
					success : function(data) {
						var description="";
						description=data.description;
						var newFormItem = self.orgForm.formItem();
						newFormItem.fdParentorgid=fdId;
						newFormItem.fdParentid=fdId;
						newFormItem.fdDeptName=description;
						self.orgForm.setForm(newFormItem);
					}
				 }); 
			 }
		}
		
		//选择上级机构，部门
		self.selectNewOrg = function(org){ 
			var newFormItem = self.orgForm.formItem();
			newFormItem.fdPersonId=org.fdId;
			newFormItem.fdParentorgid=org.fdId;
			newFormItem.fdPersonName=org.fdName;   
			self.orgForm.setForm(newFormItem);
		};
			
		self.sysOrgElementForm = ko.pureComputed(function() {
			return {
				nodeId : self.selectNode(),
				checkedValue : ko.toJS(self.elementSelected),
				filterName : self.filterName(),
				availableFlag : ko.toJS(self.orgStatus)
			};
		}, this);
		
		//监控，响应条件变化时刷新数据表格
		self.sysOrgElementForm.subscribe(function() {
			self.gridViewModel.findAll(self.sysOrgElementForm);
		});
		
		//增加下级节点
		
		//新增、编辑表单viewmode
		self.addForm = new ko.formViewModel({
			callback:{
				saveSuccess: function(){}
			}
		});
		 
		//新增
		self.addTreeNode = function(obj){
			var orgElement = new OrgElement({}); 
			var element = self.orgTree.checkNode;
			var treeObj = $.fn.zTree.getZTreeObj('orgTree');
			treeObj.expandNode(element,true);
			
 			if(obj != 1){
 				orgElement.fdParentid=element.fdId;
 			}  
 			orgElement.fdIsBusiness="1"; 
 			orgElement.fdIsAvailable="1";
 			
 			orgElement.fdOrgType = obj;
 	    	self.orgForm.setForm(orgElement);
 			 
 			self.orgForm.callback.saveSuccess =  function(newNode){ 
 				self.orgTree.addChild(newNode);
 				$("#myModal").modal("hide");
 			};   
 			
 			self.showDescription(element.fdId);
            $('#myModal').modal();
		}
	 
		self.orgTree = new ko.zTreeView('orgTree', {
			url : 'sys/orgElement',
			callback : {
				treeNodeChanged : function(node) { 
					self.selectNodeType(node.fdOrgType);
					self.selectNode(node.fdHierarchyId);
				},
				beforeAddNode : self.addTreeNode
			}
		})
		
		self.gridViewModel = new ko.gridViewModel({
			url : 'sys/orgElement',
			size : 12,
			isAutoRetrieve: 'false',
			callback : {
				itemEdit : function(om) {
					AjaxUtil.call({
						url:'/sys/orgElement/getFormItem',
						data:{context:ko.toJSON(om)},
						async:false,
						success:function(data){
							self.orgForm.setForm(new OrgElement(data));
							self.showDescription(om.fdParentid);
						}
					})
					
					self.orgForm.callback.saveSuccess =  function(data) { 
						$('#myModal').modal('hide');
						var treeObj = $.fn.zTree.getZTreeObj("orgTree");
						var seletedNode = treeObj.getSelectedNodes()[0];
						//更新左边数节点
						var node = treeObj.getNodeByParam("fdId", self.orgForm.formItem().fdId, null);
						if(null!=node){
							node.fdName = self.orgForm.formItem().fdName();
							treeObj.updateNode(node);
						}
						self.gridViewModel.refresh();
					}
					
					$('#myModal').modal();
				}
			}
		});
	
		self.orgForm = new ko.formViewModel({
			url : 'sys/orgElement',
			callback : {
				beforeSave : function(formViewModel,formItem){
					if(formItem().fdOrgType==8){
						formItem().fdNo._disposeValidation();
					}else{
						formItem().fdLoginName._disposeValidation();
					}
					var isValid = ko.validation.group(formItem());
					
					//显示没通过消息
					isValid.showAllMessages();
					if (isValid().length!=0) {
						return -1;
					}
					
				},
				saveSuccess : function(node) { 
				}
			}
		},OrgElement);
		 
		//人员列表
		self.elementForm = ko.observable({
			elementIds : "",
			elementNames : "",
			pattern : 'group',
			orgTypeArr : ["8"]
		})
		self.openElementWindow = function(ids,names,types){
			self.elementForm({elementIds : ids,elementNames : names,orgTypeArr : types})
			$('#memberSelectWindow').modal();
		}
		//选择员工列表 
		self.selectPersonList = function(){
			var formItem = self.orgForm.formItem();
			formItem.selectType = 'selectPerson';
			self.openElementWindow(formItem.fdPersonId,formItem.fdPersonName,["8"])
		}
		//选择岗位列表
		self.selectPostList = function(){
			var formItem = self.orgForm.formItem();
			formItem.selectType = 'selectPost';
			self.openElementWindow(formItem.fdPostIds,formItem.fdPostNames,["4"])
		}
		// 组件回调函数
		self.selectNewElement = function(elements,elementNames,elementIds){
			var formItem = self.orgForm.formItem();
			if(formItem.selectType == 'selectPerson'){
				$('#editor').val(elementNames);
				self.orgForm.formItem().fdPersonId=elementIds;
				self.orgForm.formItem().fdPersonName=elementNames;
			}else if(formItem.selectType == 'selectPost'){
				self.orgForm.formItem().fdPostIds=elementIds;
				self.orgForm.formItem().fdPostNames(elementNames);
			}
		}
		//-------end
		
		//弹窗组织架构选择回调--start
		self.selectTypeFunc = function(data){   
			//机构
			var newFormItem = self.orgForm.formItem();
			if(ko.toJS(self.orgForm.formItem()).selectType == 'fdThisLeader'){    
				newFormItem.fdThisLeaderid(data.fdId);
				newFormItem.fdThisLeaderName(data.fdName);  
			}else if(ko.toJS(self.orgForm.formItem()).selectType == 'fdSuperLeader'){
				newFormItem.fdSuperLeaderid=data.fdId;
				newFormItem.fdSuperLeaderName=data.fdName;  
			}else if(ko.toJS(self.orgForm.formItem()).selectType == 'fdParent'){
				newFormItem.fdParentid=data.fdId;
				newFormItem.fdDeptName=data.fdName;  
			}
			self.orgForm.setForm(newFormItem);
		} 
		//弹窗组织架构选择回调---end
		
	};
 
    return viewModel;
})

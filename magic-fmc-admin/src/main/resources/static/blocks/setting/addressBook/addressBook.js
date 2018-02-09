/**
 * 模式:
 * 1.group:		 	组织架构类型: 机构,部门,岗位,员工,群组	 左边树支持: 组织管理,群组
 * 2.handler:	 	组织架构类型: 岗位,员工,角色			 左边树支持: 组织管理,群组,通用岗位,角色线
 * 3.privileger:	组织架构类型: 岗位,员工				 左边树支持: 组织管理,群组
 * 
 * 
 * 确认回调函数：
 * @return 组织架构成员列表，成员名称，成员id。    
 */

/**
 * 问题暂记：组件冲突:目前出现在特权人操作的修改处理人和修改流程时修改节点处理人之间的冲突  
 */

define([ 'knockout','AjaxUtil','MsgBox' ], function(ko,AjaxUtil,MsgBox) {
	function groupMemberTree(params) {
		
		var self = this;
		
		//模式监控
		self.pattern = ko.observable('group')
		
		//组织架构树Id
		self.treeId = 'elementTree_addressBook';
		//常用地址本树Id(群组、通用岗位、角色线)
		self.addressId = 'addressTree_addressBook';
		//常用地址本树Id(群组)
		self.groupId = 'groupTree_addressBook';
		
		//
		self.fdOrgType = ko.observable(0);
		
		//
		self.orgTypes1=[ "1", "2", "4", "8","16"];
		self.orgTypes2=[ "4", "8","32"];
		self.orgTypes3=[ "4", "8"];
		//操作类型列表
		self.orgTypeList = ko.observable([]);
		
		//当前节点
		self.currentNode = ko.observable();
		
		// 待选列表
		self.leftElement = ko.observableArray(null);
		// 已选列表
		self.rightElement = ko.observableArray(null);
		
		//描述
		self.description = ko.observable();
		
		//组织类型
		self.elementSelected = ko.observableArray(self.orgTypes1);
		//查询条件
		self.filterName = ko.observable("");
		//查询模式
		self.searchMode = ko.observable(true);
		//单击组织架构树选择节点
		self.selectNode = ko.observable();
		
		//模态框初始化加载
		self.initModal = $('#addressBookSelectWindow').on('shown.bs.modal', function () {
			self.rightElement([]);
			self.refreshModal();
			self.nodeChange(self.currentNode());
		})
		
		//监控data(获取已选群组成员,获取模式)
		self.refreshModal = function() {
			
			//监控选中模式
			self.pattern(ko.toJS(params.data).pattern)
			if(self.pattern()==undefined){
				self.pattern('group')
			}
			
			switch(self.pattern()){
			case ('handler'):
				self.orgTypeList(self.orgTypes2);
				self.elementSelected(self.orgTypes2)
				break;
			case ('group'):
				self.orgTypeList(self.orgTypes1);
				self.elementSelected(self.orgTypes1)
				break;
			case ('privileger'):
				self.orgTypeList(self.orgTypes3);
				self.elementSelected(self.orgTypes3)
				break;
			}
			
			//监控已选列表
			self.rightElement.removeAll();
			var elementArray = ko.toJS(params.data).elementArray;
			if(elementArray!=undefined){
				for (i=0;i<elementArray.length;i++){
					self.rightElement.push(elementArray[i]);
				}
			}
			
		};
		
		
		//查询Form
		self.sysOrgElementForm = ko.pureComputed(function() {
			return {
				nodeId : null,
				checkedValue : ko.toJS(self.elementSelected),
				filterName : self.filterName(),
				searchMode : self.searchMode(),
				ifSearch : true
			};
		}, this);
		
		//搜索按钮触发
		self.search = function(){
			if(self.sysOrgElementForm().filterName==null||self.sysOrgElementForm().filterName==""){
				alert("请填写关键字在进行搜索！");
				return;
			}else{
				self.inquiry();
			}
		}
		
		//搜索
		self.inquiry = function(){
			AjaxUtil.call({
				url : 'sys/orgElement/findFdNameType',
				type : 'post',
				data : {context: ko.toJSON(self.sysOrgElementForm)},
				success : function(data){
					self.leftElement(data);
					if(self.leftElement().length==1){
						self.addOne(self.leftElement()[0]);
					}
				}
			})
		}
		
		//重置
		self.reset = function(){
			self.filterName(null);
		}
		
		//监控，响应条件变化时刷新待选列表数据
		self.sysOrgElementForm.subscribe(function(val) {
			if(val.filterName==null||val.filterName==""){
			}else{
				self.inquiry();
			}
		});
		
		/*****************************树节点改变方法Start*********************************/
		
		
		// 组织架构节点改变触发
		self.nodeChange = function(node){
			self.currentNode(node);
			self.selectNode(node.fdId);
			self.resetList();
			AjaxUtil.call({
				url : 'groupMemberTree/getOrgElements',
				data : {context: 
							ko.toJSON({
								nodeId :　node.fdId ,
								checkedValue : self.elementSelected()})},
				type: "post",
				success : function(data){
					self.leftElement(data);
				}
			});
		}
		
		//常用地址本节点改变触发
		self.groupNodeChange = function(node){
			self.currentNode(node);
			self.resetList();
			//群组模式时点击群组分类可在待选列表获得群组
			if(!node.isLeaf==1&&self.pattern()=='group'){
				AjaxUtil.call({
					url : 'groupMemberTree/getGroup',
					data : {fdId :　node.fdId},
					type : "post",
					success : function(data){
						self.leftElement(data);
					}
				});
			}
			
			//尾节点--根据尾节点不同请求路径不一样
			if(node.isLeaf==1){
				if(node.nodeType=="group"){
					var fdOrgTypes = $(self.elementSelected()).serializeArray();
					AjaxUtil.call({
						url : 'groupMemberTree/getGroupMember',
						data : {'parentId' :　node.fdId,'fdOrgTypes' : fdOrgTypes},
						type: "post",
						traditional: true,
						success : function(data){
							self.leftElement(data);
						}
					});
				}
				if(node.nodeType=="role")
					AjaxUtil.call({
						url : 'roleLineManage/roleLineSetting/getRoleByConfId',
						data : {fdId :　node.fdId},
						type: "post",
						success : function(data){
							self.leftElement(data);
						}
					});
				if(node.nodeType=="post")
					AjaxUtil.call({
						url : 'sys/generalPostion/getAll',
						success : function(data){
							self.leftElement(data);
						}
					});
			}
		}
		
		
		
		/*********************************树节点改变方法End*****************************/
		
		
		/*******************************描述Start*********************************/
		// 获取选中项的描述
		self.showDescription = function(obj){
			if(undefined!=obj)
				AjaxUtil.call({
					url : 'groupMemberTree/getDescription',
					data : {id : obj.fdId,name : obj.fdName},
					type : 'post',
					success : function(data){
						self.description(data);
					}
				});
		}
		
		/*******************************描述End*********************************/
		
		//
		self.rightIndex = ko.observable(null)
		
		//待选/已选框中单击选择的option
		self.leftOption = ko.observable(null)
		self.rightOption = ko.observable(null)
		
		//待选/已选框中选择的options
		self.chosenLeftOptions = ko.observableArray([])
		self.chosenRightOptions = ko.observableArray([])
		
		//重置
		self.resetList = function(){
			self.leftOption(null);
			self.rightOption(null);
			self.chosenLeftOptions([]);
			self.chosenRightOptions([]);
			self.description("");
		}
		
		// 单击待选列表
		self.clickMember = function(member){ 
			self.chosenLeftOptions(ko.toJS(member))
			self.showDescription(member);
			return true;
		}
		
		// 单击已选列表
		self.clickNewMember = function(member,index){
			self.chosenRightOptions(ko.toJS(member));
			self.rightIndex(index);
			self.showDescription(member);
		}
		
		//上下选中更新描述
		$("#select_left").change(function(){
			var index = $("#select_left option:selected").index();
			var leftArr = self.leftElement();
			self.showDescription(leftArr[index]);
		});
		
		$("#select_right").change(function(){
			var index = $("#select_right option:selected").index();
			var rightArr = self.rightElement();
			self.showDescription(rightArr[index]);
		});
		
		/*********************************列表添加删除操作Start**************************************/
		
		//待选框双击事件(添加一个)
		self.addOne = function(val){
			var rightArr = self.rightElement();
			var flag = true;
			for(var i=0; i<rightArr.length ; i++){
				if(rightArr[i].fdId==val.fdId){
					flag=false;
					break;
				}
			}
			if(flag)
				self.rightElement.push(val);
		}
		
		// 添加
		self.add = function(){

			var leftArr = self.chosenLeftOptions();
			var rightArr = self.rightElement();
			var newArr = [];
			
			//多选
			if(leftArr.length>0){
				//合并，去重
				newArr = rightArr.concat(leftArr).unique();
				self.rightElement(newArr);
			}else{
				//单选
				if('object'==typeof leftArr&&null!=leftArr&&leftArr.length!=0){
					self.addOne(leftArr);
				}
			}
		}
		
		// 全部添加
		self.addAll = function(){
			
			var leftArr = self.leftElement();
			var rightArr = self.rightElement();
			var newArr = [];
			
			if(leftArr.length>0){
				//合并，去重
				newArr = rightArr.concat(leftArr).unique();
				self.rightElement(newArr);
			}
		}
		
		//已选框双击事件
		self.delOne = function(val){
			self.rightElement.remove(function(item){return item.fdId==val.fdId});
		}
		
		
		
		// 删除
		self.del = function(){
			var rightArr = self.chosenRightOptions();
			if(rightArr.length!=undefined)
				for(i=0;i<rightArr.length;i++){
					self.rightElement.remove(function(item){return item.fdId==rightArr[i].fdId});
				}
			else
				self.delOne(rightArr);
		}
		
		// 全部删除
		self.delAll = function(){
			self.rightElement.removeAll();
		}
		
		
		//数组去重
		Array.prototype.unique = function(){
			 var res = [];
			 var json = {};
			 for(var i = 0; i < this.length; i++){
			  if(!json[this[i].fdId]){
			   res.push(this[i]);
			   json[this[i].fdId] = 1;
			  }
			 }
			 return res;
			}
		
		
		// 交换数组元素
	    self.swapItems = function(arr, index1, index2) {
	        arr[index1] = arr.splice(index2, 1, arr[index1])[0];
	        return arr;
	    };
	 
	    // 上移
	    self.upRecord = function() {
	    	var arr = self.rightElement();
	    	var index = self.rightIndex();
	        if(index == 0) {
	            return;
	        }
	        self.rightElement(self.swapItems(arr, index, index - 1));
	        self.rightIndex(index-1)
	    };
	 
	    // 下移
	    self.downRecord = function() {
	    	var arr = self.rightElement();
	    	var index = self.rightIndex();
	        if(index == arr.length -1) {
	            return;
	        }
	        self.rightElement(self.swapItems(arr, index, index + 1));
	        self.rightIndex(index+1)
	    };
		
		/*********************************列表添加删除操作End**************************************/
		
		
		
		
		
		/*****************************回调函数Start********************************/
		// 确认回调函数
		self.passSelectNode = function(){
			
			//数据清空
			self.leftElement([]);
			
			var memberNames = "";
			var memberIds = "";
			//以字符串类型返回群组成员名称和ID
			for (var i=0;i<self.rightElement().length;i++){
				memberNames = memberNames+self.rightElement()[i].fdName;
				memberIds = memberIds+self.rightElement()[i].fdId;
				if(i<self.rightElement().length-1){
					memberNames+=";";
					memberIds+=";";
				}
			}
			params.callFunc(self.rightElement(),memberNames,memberIds);
		}
		/*****************************回调函数End********************************/
		
		
		
		/****************************************Model**************************************/
		//pattern: handler
		// 常用地址本树model(群组、通用岗位、角色线)
		self.groupCateTree = new ko.zTreeView(self.addressId, {
			url : 'groupMemberTree',
			callback : {
				treeNodeChanged : self.groupNodeChange
				}
		})
		
		self.groupTree = new ko.zTreeView(self.groupId, {
			url : 'groupMemberTree/onlyGroup',
			callback : {
				treeNodeChanged : self.groupNodeChange
				}
		})
		
		// 组织架构树model
		self.elementTree = new ko.zTreeView('elementTree_addressBook', {
			url : 'groupMemberTree/orgElement',
			callback : {
				treeNodeChanged : self.nodeChange
				}
		});
		
	};
	return groupMemberTree;
});
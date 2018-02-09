 
define([ 'knockout','AjaxUtil','MsgBox'], function(ko,AjaxUtil,MsgBox) {
	function memberSelectTree(params) {
		
		var self = this;
		
		self.treeId = 'memberSelectTree';
		
		// 待选列表
		self.leftElement = ko.observableArray([]);
		// 待选列表选项
		self.selectMember = ko.observable();
		
		// 已选列表
		self.rightElement = ko.observableArray([]);
		
		for (i=0;i<(ko.toJS(params.data).elementArray).length;i++){
			self.rightElement.push((ko.toJS(params.data).elementArray)[i]);
		}
		
		// 已选列表选项
		self.newSelectMember = ko.observable();
		
		//描述
		self.description = ko.observable();
		
		//组织类型
		self.elementSelected = ko.observableArray(["8","16"])
		//查询条件
		self.filterName = ko.observable("");
		//查询模式
		self.searchMode = ko.observable(true);
		//单击组织架构树选择节点
		self.selectNode = ko.observable();
		
		//查询Form
		self.sysOrgElementForm = ko.pureComputed(function() {
			return {
				nodeId : self.selectNode(),
				checkedValue : ko.toJS(self.elementSelected),
				filterName : self.filterName(),
				searchMode : self.searchMode()
			};
		}, this);
		
		//搜索
		self.search = function(){
			if(self.sysOrgElementForm().filterName==null||self.sysOrgElementForm().filterName==""){
				return;
			}else{
				AjaxUtil.call({
					url : 'sys/orgElement/findFdNameType',
					type : 'post',
					data : {context: ko.toJSON(self.sysOrgElementForm)},
					success : function(data){
						self.leftElement(data);
					}
				})
			}
		}
		
		//重置
		self.reset = function(){
			self.filterName(null);
		}
		
		//监控，响应条件变化时刷新待选列表数据
		self.sysOrgElementForm.subscribe(function() {
			self.search();
		});
		
		// 节点改变触发
		self.nodeChange = function(node){
			self.selectNode(node.fdId);
			AjaxUtil.call({
				url : 'setting/groupAdmin/findMember',
				data : {context: 
					ko.toJSON({
						nodeId :　node.fdId ,
						checkedValue : [8]})},
				type: "post",
				success : function(data){
					self.leftElement(data);
				}
			});
		}
		
		//描述
		self.showDescription = function(obj){
			
			 //获取被选中的option标签选项 
			 AjaxUtil.call({
					url: "sys/orgElement/getElementPathDescription",   
					type : "post",
					data : {fdId: obj.fdId},
					dataType : "JSON",
					async:false,
					success : function(data) {
						self.description(data);
					}
			 }); 
		}
		
		//获取已选群组成员
		params.data.subscribe(function() {
			self.rightElement.removeAll();
				
				for (i=0;i<(ko.toJS(params.data).elementArray).length;i++){
					self.rightElement.push((ko.toJS(params.data).elementArray)[i]);
				}
		});
		
		// 单击待选列表
		self.clickMember = function(member){ 
			
			self.selectMember(ko.toJS(member));
			
			self.showDescription(member);
		}
		
		// 单击已选列表
		self.clickNewMember = function(member){
			
			self.newSelectMember(ko.toJS(member));
			
			self.showDescription(member);
		}
		
		//树model
		self.groupCateTree = new ko.zTreeView(self.treeId, {
			url : 'sys/orgElement',
			callback : {
				treeNodeChanged : self.nodeChange
				}
		})
		
		
		// 确认回调
		self.passSelectNode = function(){
			var memberNames = "";
			var memberIds = "";
			//以字符串类型返回群组成员名称
			for (var i=0;i<self.rightElement().length;i++){
				memberNames = memberNames+self.rightElement()[i].fdName;
				memberIds = memberIds+self.rightElement()[i].fdId;
				if(i!=self.rightElement().length-1){
					memberNames+=";";
					memberIds+=";";
				}
			}
			
			
			params.callFunc(ko.toJS(self.rightElement),memberNames,memberIds);
		}
		
		// 添加
		self.addOne = function(){

			var flag = true;
			
			//检验重复
			if(self.rightElement().length>0){
			for (var i=0;i<self.rightElement().length;i++){
				if((ko.toJS(self.rightElement()[i])).fdId==(ko.toJS(self.selectMember())).fdId){
					flag = false;
					break;}
			}}
			
			if(flag&&ko.toJS(self.selectMember)!=undefined){
				self.rightElement.push(ko.toJS(self.selectMember));
			}
		}
		
		// 全部添加
		self.addAll = function(){
			
			var array = ko.toJS(self.rightElement);
			
			//检验重复
			if(array.length==0){
				for(var j=0;j<self.leftElement().length;j++){
						self.rightElement.push(ko.toJS(self.leftElement()[j]));}
				}else{
					for (var i=0;i<array.length;i++){
						for(var j=0;j<self.leftElement().length;j++){
							if(array[i].fdId!=self.leftElement()[j].fdId){
								self.rightElement.push(ko.toJS(self.leftElement()[j]));}}
			}
			}
		}
		
		// 删除
		self.delOne = function(){
			del = ko.toJS(self.newSelectMember);
			self.rightElement.remove(function(item){return item.fdId==del.fdId});
		}
		
		// 全部删除
		self.delAll = function(){
			self.rightElement.removeAll();
		}
		
	};
	return memberSelectTree;
});
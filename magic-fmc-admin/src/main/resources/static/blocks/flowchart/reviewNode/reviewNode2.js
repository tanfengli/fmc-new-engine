 
define([ 'knockout' ], function(ko) {
	function reviewNode2(params) {
		
		var self = this;
		
		self.treeId = 'groupMemberTree';
		
		
		// 待选列表
		self.leftNodes = ko.observableArray([]);
		// 待选列表选项
		self.selectMember = ko.observable({});
		//待选/已选框中选择的options
		self.chosenLeftOptions = ko.observableArray([])
		self.chosenRightOptions = ko.observableArray([])
		
		// 已选列表
		self.rightNodes = ko.observableArray([]);
		
		for (i=0;i<(ko.toJS(params.data).reviewArray).length;i++){
			self.leftNodes.push((ko.toJS(params.data).reviewArray)[i]);
		}
		
		// 已选列表选项
		self.newSelectMember = ko.observableArray([]);
		
		//描述
		self.description = ko.observable('');
		
		//组织类型
		self.elementSelected = ko.observableArray([ "1", "2", "4", "8","16"])
		//查询条件
		self.filterName = ko.observable("");
		//查询模式
		self.searchMode = ko.observable(true);
		//单击组织架构树选择节点
		self.selectNode = ko.observable({});
		
		
		//描述
		self.showDescription = function(obj){
			
			self.description('('+obj.id+')'+'-' + obj.name);
		}
		
		
		
		//获取可选群组成员
		params.data.subscribe(function() {
			
				self.leftNodes.removeAll();
				self.rightNodes.removeAll();
				var data = ko.toJS(params.data);
				for (i=0;i<data.reviewArray.length;i++){
					self.leftNodes.push((data.reviewArray)[i]);
				}
//				self.member(data.reviewArray);
				var selectMem = new Array();
				for (i=0;i<data.newSelectMember.length;i++){
					var obj = new Object();
					var temp  = data.newSelectMember[i];
					obj.id=temp.split(".")[0];
					obj.name=temp.split(".")[1];
					selectMem[i]=obj;
				}
				self.rightNodes(selectMem);
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
		
		// 确认回调
		self.passSelectNode = function(){
			var memberNames = "";
			//以字符串类型返回群组成员名称
			var memberArr = new Array();
			for (var i=0;i<self.rightNodes().length;i++){
				memberArr[i] = self.rightNodes()[i].id+"."+self.rightNodes()[i].name;
			}
			$('#reviewNodeWin2').modal('hide')
			params.callFunc(memberArr);
		}
		
		self.addOne = function(val){
			var rightArr = self.rightNodes();
			var flag = true;
			for(var i=0; i<rightArr.length ; i++){
				if(rightArr[i].id==val.id){
					flag=false;
					break;
				}
			}
			if(flag)
				self.rightNodes.push(val);
		}
		
		// 添加
		self.add = function(){
			var options= $("#selected11").find("option:selected")
//			var leftArr = self.chosenLeftOptions();
			var rightArr = self.rightNodes();
			var newArr = [];
			
//			//多选
//			if(leftArr.length>0){
//				//合并，去重
//				newArr = rightArr.concat(leftArr).unique();
//				self.newMember(newArr);
//			} 
			var num=0;
			for (var i=0;i<options.length;i++){
				var text = options[i].text;
				var arr = text.split(".");
				var obj=new Object();
				obj.id=arr[0];
				obj.name=arr[1];
				
				var flag = true;
				for(j=0;j<rightArr.length;j++){
					if(obj.id==rightArr[j].id){
						flag =false;
					}
				}
				if(flag){
					self.rightNodes.push(obj);
				}
			}
//			var flag = true;
//			//检验重复
//			if(self.newMember().length>0){
//				for (var i=0;i<self.newMember().length;i++){
//					if((ko.toJS(self.newMember()[i])).id==(ko.toJS(self.selectMember())).id){
//						flag = false;
//						break;}
//				}}
//			var obj = ko.toJS(self.selectMember);
//			if(flag&&ko.toJS(self.selectMember)!=undefined){
//				self.newMember.push(ko.toJS(self.selectMember));
//			}
		}
		
		// 全部添加
		self.addAll = function(){
			
			var array = ko.toJS(self.rightNodes);
			
			//检验重复
			if(array.length==0){
				for(var j=0;j<self.leftNodes().length;j++){
						self.rightNodes.push(ko.toJS(self.leftNodes()[j]));}
				}else{
					for (var i=0;i<array.length;i++){
						for(var j=0;j<self.member().length;j++){
							if(array[i].id!=self.leftNodes()[j].id){
								self.rightNodes.push(ko.toJS(self.leftNodes()[j]));}}
			}
			}
		}
		
		// 删除
		self.del = function(){
			var options= $("#selected22").find("option:selected")
			var rightArr = self.chosenRightOptions();
			if(rightArr.length!=undefined)
				for(i=0;i<options.length;i++){
					var text = options[i].text;
					var arr = text.split(".");
					self.rightNodes.remove(function(item){return item.id==arr[0]});
				}
		}
		
		// 全部删除
		self.delAll = function(){
			self.rightNodes.removeAll();
		}
		
	 self.robotHandler = function(nameField){
		  self.formulaName(nameField);
	 };
	
	
		
	};
	return reviewNode2;
});
define([ 'knockout' ,'AjaxUtil','MsgBox'], function(ko,AjaxUtil,MsgBox) {
	function shortlistViewModel(params) {
	
	var self = this;
	
	self.billEvent=null;
	self.nodeDeatil=null;
    
    //关键字
    self.filterName = ko.observable('');
    //描述
    self.description = ko.observable('');
    //备选列表
    self.shortlist = [];
    //待选列表(左)
    self.leftElement = ko.observableArray();
    //已选列表(右)
    self.rightElement = ko.observableArray();
    
    //模态框初始化加载
	self.initModal = $('#inheritRoleWindow').on('show.bs.modal', function () {
		self.rightElement(ko.toJS(params.data));
		AjaxUtil.call({
			url : 'roleManage/roleAssign/findAllNoPage',
			success : function(data){
				self.leftElement(data);
			}
		})
	})
    
//    //搜索
//	self.search = function(){
//		self.leftElement([]);
//		for(i=0;i<self.shortlist.length;i++){
//			var element = self.shortlist[i];
//			if(element.fdName.indexOf(self.filterName())!=-1||element.fdNo.indexOf(self.filterName())!=-1){
//				self.leftElement.push(element)
//			}
//		}
//	}
	//清除
    self.reset = function(){
    	self.leftElement(self.shortlist)
    	self.filterName('');
	}
    
	/*******************************描述Start*********************************/
	// 获取选中项的描述
	self.showDescription = function(obj){
		if(undefined!=obj) 
			self.description(obj.fdName);
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
        if(null==index || 0 == index) {
            return;
        }
        self.rightElement(self.swapItems(arr, index, index - 1));
        self.rightIndex(index-1)
    };
 
    // 下移
    self.downRecord = function() {
    	var arr = self.rightElement();
    	var index = self.rightIndex();
        if(null==index || index == arr.length -1) {
            return;
        }
        self.rightElement(self.swapItems(arr, index, index + 1));
        self.rightIndex(index+1)
    };
	
    
    /**********End***************/
    //确定
    self.submit = function(){
		//数据清空
		self.leftElement([]);
		self.filterName('');
		
		var memberNames = "";
		//以字符串类型返回群组成员名称
		for (var i=0;i<self.rightElement().length;i++){
			memberNames = memberNames+self.rightElement()[i].fdName;
			if(i<self.rightElement().length-1)
				memberNames+=";";
		}
		var memberIds = "";
		//以字符串类型返回群组成员Id
		for (var i=0;i<self.rightElement().length;i++){
			memberIds = memberIds+self.rightElement()[i].fdId;
			if(i<self.rightElement().length-1)
				memberIds+=";";
		}
		params.callFunc(self.rightElement(),memberNames,memberIds);
	}
	
	}
	return shortlistViewModel;
})
define([ "knockout",'AjaxUtil',"MsgBox"], 
		function(ko,AjaxUtil,MsgBox) {  

function SimulatorFormVM() {
	
	var self = this;
	//人员列表
	self.elementForm = {
		elementArray : [],
		pattern : 'handler',
		orgTypeArr : [ "1", "2", "4", "8","16","32"]
	}
	//模拟用户
	self.simulatePerson = ko.observable({fdName : null});
	//计算结果
	self.result = ko.observable('');
	
	
	//人员列表选择
	self.addressBookCallback = function(elements,names,ids){
		self.elementForm.elementArray = elements;
		$("#personList").val(names);
		
	}
	
	//模拟用户选择
	self.userSeleteCallback = function(element){
		self.simulatePerson = element;
		$("#simulateUser").val(element.fdName);
	}
	
	//获取当前登录用户
	AjaxUtil.call({
		url : 'sys/orgElement/getCurrentElement',
		success : function(data){
				self.simulatePerson(data);
		}
	})
	
	//开始计算
	self.startSimulate = function(){
		if(self.elementForm.elementArray.length==0){
			MsgBox.warning('请先选择人员列表信息，再执行计算操纵！')
			return ;
		}
		//计算
		AjaxUtil.call({
			url : 'simulator/startSimulate',
			data : {
					personList : ko.toJSON(self.elementForm.elementArray),
					simulatePerson : ko.toJSON(self.simulatePerson)
				   },
			type : 'post',
			success : function(data){
				self.result(data);
			}
		})
	}
	
};
return SimulatorFormVM;
})
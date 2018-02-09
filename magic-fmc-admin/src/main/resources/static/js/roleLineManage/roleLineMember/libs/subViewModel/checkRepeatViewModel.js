/**
 * 重复检查viewModel
 */

define([ "knockout" ,'AjaxUtil', "MsgBox","./sysOrgRoleLineDefaultRoleVO.js","components"],
		function(ko,AjaxUtil,MsgBox,DefaultRole) {

CheckRepeatViewModel = function(config) {
	
	var self = this;
	
	self.selectNode = config.selectNode || "";
	//重复成员列表
	self.repeatList = ko.observableArray();
	//成员默认岗位列表
	self.defaultRoleList = [];
	
	self.openWindow = function(){
		
		self.checkRepeat();
	}
	
	/**
	 * 
	 */
	self.checkRepeat = function(){
		var val = self.selectNode();
		if(typeof val != 'object'||val==null){
			return ;
		}
		var confId = val.confId;
		if(undefined==confId)
			return ;
		AjaxUtil.call({
			url : 'roleLineManage/roleLineMember/checkRepeat/findRepeat',
			type : 'post',
			data : {confId : confId},
			success : function(data){
				self.defaultRoleList=[];
				if(data.repeatList.length==0){
					MsgBox.info("没有出现人员角色重复的情况  ");
					return ;
				}
				for(i=0;i<data.defaultRoleList.length;i++){
					self.defaultRoleList.push(new DefaultRole(data.defaultRoleList[i]));
				}
				self.repeatList(data.repeatList);
				$("#checkRepeatModal").modal();
			}
		})
	};
	
	self.beforeSave = function(){
		
		if(self.repeatList().length==0){
			$("#checkRepeatModal").modal('hide');
			return -1;
		}
		
		self.defaultRoleForm.setForm(self.defaultRoleList);
	}
	
	 // 设置默认岗位表单viewModel
	self.defaultRoleForm = new ko.formViewModel({
		url : 'roleLineManage/roleLineMember/checkRepeat',
		callback:{
			beforeSave : self.beforeSave,
			saveSuccess: function(){
				$("#checkRepeatModal").modal('hide');
			}
		}
		});
	

	
	
};
return CheckRepeatViewModel;
})

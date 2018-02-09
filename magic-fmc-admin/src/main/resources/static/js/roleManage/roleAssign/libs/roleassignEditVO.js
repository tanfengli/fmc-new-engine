
define([ "knockout","knockout-validation" ],function(ko) {

	function RoleAssignEdit(options) {
				
			var setting = $.extend({
				fdId : null,
				fdName : null,
				fdDescription : null,
				categoryName: null,
				categoryId : null,
				creatorName : null,
				authArray : [],
				uraList : [],
				uraName : null,
				edtList : [],
				edtName : null,
				inhRoleList : [],
				inhRoleName : null,
				authorizedMenus : [],
				clickType : null
//				,
//				roleSelect : false
			}, options || {})
			
			return {
				fdId: setting.fdId,
				fdName : ko.observable(setting.fdName).extend({
		            required: {
		            	params:true,
		            	message: "请输入分类名称。" 
		            }
		        }),
				fdDescription : setting.fdDescription,
				categoryName: setting.categoryName,
				categoryId: setting.categoryId,
				creatorName: setting.creatorName,
				authArray: ko.observableArray(setting.authArray),
//				roleSelect: ko.observable(setting.roleSelect),
				uraList: ko.observableArray(setting.uraList),
				uraName: ko.observable(setting.uraName),
				edtList: ko.observableArray(setting.edtList),
				edtName: ko.observable(setting.edtName),
				inhRoleList : ko.observableArray(setting.inhRoleList),
				inhRoleName : ko.observable(setting.inhRoleName),
				authorizedMenus : ko.observableArray(setting.authorizedMenus),
				clickType: setting.clickType,
				
				//权限下拉列表
				selectAuth : function(data, event){
					var value = event.target.checked;
					this.authArray.removeAll(['ROLE_SYSNEWS_MAIN','ROLE_SYSROLE_LINE_ADMIN','ROLE_SYSORGGROUPCATE_ADMIN','ROLE_SYSORGGROUP_ADMIN']);
					if(value){
						this.authArray.push('ROLE_SYSNEWS_MAIN','ROLE_SYSROLE_LINE_ADMIN','ROLE_SYSORGGROUPCATE_ADMIN','ROLE_SYSORGGROUP_ADMIN');
					}
				},
				
				
//				//监控
//				this.roleSelect.subscribe : function(nvalue){
//					
//					this.authArray.removeAll(['ROLE_SYSNEWS_MAIN','ROLE_SYSROLE_LINE_ADMIN','ROLE_SYSORGGROUPCATE_ADMIN','ROLE_SYSORGGROUP_ADMIN']);
//					
//					if(nvalue){
//						this.authArray.push('ROLE_SYSNEWS_MAIN','ROLE_SYSROLE_LINE_ADMIN','ROLE_SYSORGGROUPCATE_ADMIN','ROLE_SYSORGGROUP_ADMIN');
//							 }
//				},
				
				
				
				//权限下拉列表
				selectFlowAuth : function(data, event){

					var value = event.target.checked;
					this.authArray.remove('ROLE_SYSNEWS_COMMON_TEMP');
					if(value){
						this.authArray.push('ROLE_SYSNEWS_COMMON_TEMP');
					}
					
					

				},
				
				//权限下拉列表
				selectSysAuth : function(data, event){
					var value = event.target.checked;
					this.authArray.removeAll(['ROLE_SYS_WORKFLOW_SET','ROLE_SYS_WORKFLOW_OPRATION','ROLE_SYS_WORKFLOW_APPROVE_WORD','ROLE_SYS_WORKFLOW_VAR','ROLE_SYS_WORKFLOW_AUTHORITY_ADMIN']);
					if(value){
						this.authArray.push('ROLE_SYS_WORKFLOW_SET','ROLE_SYS_WORKFLOW_OPRATION','ROLE_SYS_WORKFLOW_APPROVE_WORD','ROLE_SYS_WORKFLOW_VAR','ROLE_SYS_WORKFLOW_AUTHORITY_ADMIN');
					}
				}
			}

			
		};
		return RoleAssignEdit;

	})





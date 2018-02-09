
define([ "knockout" ,'AjaxUtil',"knockout-validation"],function(ko,AjaxUtil) {

	function RoleSet(options) {
				
			var setting = $.extend({
				fdId : null,
				fdName : null,
				cateId : null,
				cateName : null,
				fdPlugin : null,
				fdParameter : null,
				fdIsMultiple : null,
				fdMemo : null,
				fdIsAvailable: null,
				fdRtnValue : null,
				fdRoleConfId : null,
				modeShow : 'commonModel',
				leaderType : false,
				
				
				//角色关系计算所需的属性
				type : '0',
				level : '-1',
				end : '0',
				includeme : false,
				rtnvalue : null,
				
				fdOrg : false,
				fdDept : false,
				fdPost : false,
				fdPerson : false
				
				
			}, options || {})
			
			return {
				fdId: setting.fdId,
				fdName : ko.observable(setting.fdName).extend({
	                required: {
	                	params:true,
	                	message: "角色名称不能为空！" 
	                }
	            }),
				cateId: setting.cateId,
				cateName: setting.cateName,
				fdPlugin : setting.fdPlugin,
				fdParameter: ko.observable(setting.fdParameter),
				fdIsMultiple: ko.observable(setting.fdIsMultiple),
				fdMemo : setting.fdMemo,
				fdIsAvailable: setting.fdIsAvailable,
				fdRtnValue : setting.fdRtnValue,
				fdRoleConfId : setting.fdRoleConfId,
				
				//显示用
				modeShow : ko.observable(setting.modeShow),
				leaderType :　ko.observable(setting.leaderType),
				
				//角色关系计算所需的属性
				type : ko.observable(setting.type),
				level : ko.observable(setting.level),
				end : ko.observable(setting.end),
				includeme : ko.observable(setting.includeme),
				rtnvalue : setting.rtnvalue,
				fdOrg : ko.observable(setting.fdOrg),
				fdDept : ko.observable(setting.fdDept),
				fdPost : ko.observable(setting.fdPost),
				fdPerson : ko.observable(setting.fdPerson),
				
				
				
				
				
				//切换到普通模式
				changeCommonModel : function(value){
					
					var self = this;
					
					//添加时赋予初始值
					if(value){
						self.fdPlugin='sysOrgRolePluginService';
						self.fdParameter('type=0&level=0&includeme=true');
					};
					
					AjaxUtil.call({
						url : "roleLineManage/roleLineSetting/paramTran",
						type : "post",
						async : false,
						data : {param : this.fdParameter()},
						success : function(data){
							self.type(data.type);
							self.level(data.level);
							self.end(data.end);
							if(data.includeme=="true"){
								self.includeme(true);
							}else{
								self.includeme(false);
							}
						}
					});
					
					this.modeShow('commonModel');
					
					$("#compute").click();
					
					return true;
				},
				
				codeTran :　function(){
					
					var self = this;
					
					var parameter = "type=" + this.type();
					parameter += "&level=" + this.level();
					if(this.type()=="1")
						parameter += "&end=" + this.end();
					if(this.includeme()) {
						parameter += "&includeme=true";
					}
					this.fdParameter(parameter);
					
					switch(this.type()){
					case "0":
						this.fdIsMultiple(false);
						this.fdOrg(false);
						this.fdDept(false);
					break;
					case "1":
						this.fdIsMultiple(true);
						this.fdOrg(false);
						this.fdDept(false);
					break;
					case "2":
					case "3":
						this.fdIsMultiple(true);
						this.fdOrg(true);
						this.fdDept(true);
					break;
					}
					this.fdPost(true);
					this.fdPerson(true);
					
					var value = 0;
					if(this.fdOrg())
						value |= 1;
					if(this.fdDept())
						value |= 2;
					if(this.fdPost())
						value |= 4;
					if(this.fdPerson())
						value |= 8;
					this.fdRtnValue = value;
					
					
				},
				
				//切换到编程模式
				changeCodeModel : function(){
					this.codeTran();
					this.modeShow('codeModel');
					return true;
				},
				
				
				//提交表单
				submit : function(){
					
					//提交时将常用模式参数编码
					if(this.modeShow()=='commonModel'){
						this.codeTran();
					}
					
					$("#submit").click();
				}
				

				
			}

		};
		return RoleSet;

	})
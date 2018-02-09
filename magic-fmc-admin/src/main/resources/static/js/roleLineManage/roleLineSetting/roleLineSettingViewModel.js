
define(
		[ "knockout",'AjaxUtil', "./roleLineSettingVO.js", "./roleSetVO.js", "MsgBox","components",
				"bootstrap" ],
		function(ko,AjaxUtil, RoleLineSetting, RoleSet,MsgBox) {

			function viewModel() {

				// 查询框使用
				self.searchForm = new ko.searchViewModel(new RoleLineSetting({}), {
					callback : {
						search : function(searhForm) {
							self.roleLineSettingId.findAll(searhForm)
						}
					}
				});


				// 查询框使用
				self.newRoleSet = ko.observable(new RoleSet({}));
				// 角色线信息
				self.lineMes = ko.observable();
				//
				self.editorArray = ko.observableArray([]);
				// 页面显示
				self.choosenView = ko.observable('list');

				self.toList = function() {
					self.choosenView('list');
				}

				self.toSetView = function() {
					self.choosenView('set');
				}

				// 新增
				self.toAdd = function() {
					var line = new RoleLineSetting({});
					line.fdIsAvailable("1");
					self.roleLineSettingForm.setForm(line);
					self.editorArray([]);
					$('#myModal').modal();
				};

				// 刷新角色线配置
				self.toRefresh = function() {
					self.roleLineSettingId.refresh();
				}

				// 修改
				self.toEdit = function(roleLineSetting) {
					AjaxUtil.call({
						url : 'roleLineManage/roleLineSetting/findForm',
						type : 'post',
						data : {
							context : ko.toJSON(roleLineSetting)
						},
						success : function(data) {
							self.roleLineSettingForm.setForm(new RoleLineSetting(data));
							console.info(new RoleLineSetting(data))
							self.editorArray(data.editorArray);

							$('#editor').val(data.editorName);
							$('#myModal').modal();
						}
					})
				};

				// 传给组件的form
				self.elementForm = ko.pureComputed(function() {
					return {
						elementArray : ko.toJS(self.editorArray)
					};
				}, this);
				
				//角色线配置保存前校验
				self.lineValidate = function() {
					var lineForm = self.roleLineSettingForm.formItem();
					lineForm.isValid = ko.validation.group(lineForm);
					//显示没通过消息
					lineForm.isValid.showAllMessages();
					if (lineForm.isValid().length!=0) {
						return -1;
					}
					$('#myModal').modal('hide');
				}
				
				
				
				/*******************************配置角色关系********************************/
				

				// 配置角色线关系
				self.toSet = function(roleLineSetting) {
					self.toSetView();
					AjaxUtil.call({
						url : 'roleLineManage/roleLineSetting/findForm',
						type : 'post',
						data : {
							context : ko.toJSON(roleLineSetting)
						},
						success : function(data) {
							self.lineMes(data);
							// 添加查询条件
							self.newRoleSet().cateId = roleLineSetting.fdId;
							self.newRoleSet().cateName = roleLineSetting.fdName;
							// 查询当前角色线下的成员关系
							self.roleSetId.findAll(ko.toJS(self.newRoleSet()));
						}
					})
				};

				//复制
				self.copyOne = function(roleLineSetting) {
					var a = confirm("您确定要复制["+roleLineSetting.fdName()+"]角色线吗？");
					if(a){
						AjaxUtil.call({
							url : 'roleLineManage/roleLineSetting/copyOne',
							type : 'post',
							data : {
								fdId : roleLineSetting.fdId
							},
							success : function(data) {
								self.toRefresh();
							}
						});
					}
				}

				// 刷新角色关系管理
				self.toSetRefresh = function() {
					$('#roleModal').modal('hide');
					var roleSet = new RoleSet({});
					roleSet.cateId = ko.toJS(self.newRoleSet()).cateId;

					self.roleSetId.findAll(roleSet);
				}

				// 新增角色关系
				self.toAddRole = function() {
					var role = new RoleSet(ko.toJS(self.newRoleSet()));
					role.fdIsAvailable="1";
					// 初始化添加数据
					role.changeCommonModel(true);
					self.roleSetForm.setForm(role);
					// 计算结果
					self.compute();
					$('#roleModal').modal();
				};

				// 计算结果
				self.compute = function() {

					var role = self.roleSetForm.formItem();

					// 切换到多个领导时给end赋予初始值
					if (role.end() == undefined) {
						role.end(0);
						self.roleSetForm.setForm(role);
					}

					var s_level = getPosition(role.level());
					var e_level = s_level;

					switch (role.type()) {
					case "1":
						e_level = getPosition(role.end());
						break;
					case "2":
						s_level++;
						e_level = s_level;
						break;
					case "3":
						s_level++;
						e_level = 12;
						break;
					}

					if (s_level > e_level) {
						var tmp = s_level;
						s_level = e_level;
						e_level = tmp;
					}


					for (var i = 0; i < 13; i++) {
						// 上行图标
						var element = document
								.getElementById("img_leader_" + i);
						element.className = i >= s_level && i <= e_level ? "fa fa-street-view text-red"
								: "fa fa-street-view";
						// 下行图标
						if (i > 0) {
							element = document.getElementById("img_colleague_"
									+ i);
							element.className = i >= s_level
									&& i <= e_level
									&& (role.type() == "2" || role.type() == "3") ? "fa fa-street-view text-red"
									: "fa fa-street-view";
						}
					}

				};

				// 坐标转化
				function getPosition(level) {
					var l = parseInt(level);
					if (l == 0)
						return 10;
					if (l > 0)
						return l - 1;
					return 10 + l;
				}
				;

				// 修改角色关系
				self.toEditRole = function(roleSet) {
					var role = new RoleSet(roleSet);
					self.roleSetForm.setForm(self.codeTran(role));
					// 计算结果
					self.compute();
					$('#roleModal').modal();

				};

				// 编码数据转换
				self.codeTran = function(role) {
					AjaxUtil.call({
						url : "roleLineManage/roleLineSetting/paramTran",
						type : "post",
						async : false,
						data : {
							param : role.fdParameter
						},
						success : function(data) {
							role.type(data.type);
							role.level(data.level);
							role.end(data.end);
							if (data.includeme == "true")
								role.includeme(true);
						}
					});
					return role;
				}

				//角色关系配置保存前校验
				self.roleValidate = function() {
					var roleForm = self.roleSetForm.formItem();
					roleForm.isValid = ko.validation.group(roleForm);
					//显示没通过消息
					roleForm.isValid.showAllMessages();
					if (roleForm.isValid().length!=0) {
						return -1;
					}
				}
				
				// 组件回调函数
				self.selectNewElement = function(elements, memberNames) {
					$('#editor').val(memberNames);
					self.roleLineSettingForm.formItem().editorArray = ko.toJS(elements);
					self.editorArray(elements);
				}
				
				
				
				/*********viewModel**********/
				self.roleSetId = new ko.gridViewModel( {
					url : 'roleLineManage/roleLineSetting/roleSet',
					isAutoRetrieve : 'false',
					callback : {
						itemEdit : self.toEditRole,
					}
				});

				// 新增、编辑角色关系表单viewModel
				self.roleSetForm = new ko.formViewModel({
					url : 'roleLineManage/roleLineSetting/roleSet',
					callback : {
						beforeSave : self.roleValidate,
						saveSuccess : self.toSetRefresh
					}
				});
				
				self.roleLineSettingId = new ko.gridViewModel({
					url : 'roleLineManage/roleLineSetting',
					callback : {
						itemEdit : self.toEdit,
						itemSet : self.toSet
					}
				});

				// 新增、编辑表单viewModel
				self.roleLineSettingForm = new ko.formViewModel({
					url : 'roleLineManage/roleLineSetting',
					callback : {
						beforeSave : self.lineValidate,
						saveSuccess : self.toRefresh
					}
				});

			};
			return viewModel;
		})

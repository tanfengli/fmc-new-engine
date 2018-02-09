define([ "knockout",'AjaxUtil', "./groupVO.js", "./groupEditVO.js","components", "bootstrap" ],
		function(ko,AjaxUtil,Group,GroupEdit) {

			function viewModel() {
				var self = this;
				
				//选中的node
				self.selectNodeId = ko.observable();
				self.selectNodeName = ko.observable();
				
				//成员列表
				self.memberArray = ko.observableArray();
				
				//传给组件的form
				self.elementForm = ko.pureComputed(function() {
					return {
						elementArray : ko.toJS(self.memberArray)
					};
				}, this);
				
				self.groupEdit = new GroupEdit({});
				
				// 查询框使用
				self.newGroup = new Group({});
				self.searchForm = new ko.searchViewModel(new Group({fdCateid :　self.selectNodeId()}),{
					callback: {
						search: function(searhForm){
							searhForm().fdCateid = self.selectNodeId();
							self.groupAdminId.findAll(searhForm);
						}
					}
				});
				
				// 树
				self.people = ko.observableArray();
				
				//选择新的群组类型返回函数
				self.selectNewCate = function(cate){
					
					var newFormItem = self.groupAdminForm.formItem();
					//设置新的群组类型id和名称
					newFormItem.fdCateid=cate.fdId;
					self.groupAdminForm.setForm(newFormItem);
					$('#groupName').val(cate.fdName);
					
				};
				
				//选择新的群组成员返回函数
				self.selectNewMember = function(elements,memberNames){
					
					$('#member').val(memberNames);
					self.groupAdminForm.formItem().groupElementArray=ko.toJS(elements);
					self.memberArray(ko.toJS(elements));
				};
				
				// 用户列表
				self.gridPanel = document.getElementById("groupadminId");
				
				// 刷新
				self.toRefresh = function() {
					$('#myModal').modal('hide');
					self.groupAdminId.refresh();
				}

				// 新增
				self.toAdd = function() {
					var groupEdit = new GroupEdit({});
					
					groupEdit.fdIsAvailable="1";
					groupEdit.fdCateid =  self.selectNodeId();
					self.groupAdminForm.setForm(groupEdit);
					$('#groupName').val(self.selectNodeName());
					$('#myModal').modal();

				};
				// 编辑
				self.toEdit = function(Group) {
					
					AjaxUtil.call({
						url : 'setting/groupAdmin/findForm',
						type : 'post',
						data : {fdId : Group.fdId},
						success : function(data){
							var groupEdit = new GroupEdit({});
							
							groupEdit = data;
							self.memberArray(data.groupElementArray);
							self.groupAdminForm.setForm(groupEdit);
							$('#groupName').val(self.selectNodeName());
							
							var memberNames = "";
							// 以字符串类型返回群组成员名称
							for (var i=0;i<self.memberArray().length;i++){
								memberNames = memberNames+self.memberArray()[i].fdName+';';
							} 
							$('#member').val(memberNames);
						}
					});
					
					$('#myModal').modal();
				}
				
				//node改变
				self.nodeChange = function (node){
					self.selectNodeId(node.fdId);
					self.selectNodeName(node.fdName);
					self.groupAdminId.findAll(new Group({fdCateid :　self.selectNodeId}));
				}

				self.groupAdminId = new ko.gridViewModel({
					url : 'setting/groupAdmin',
					size : 10,
					isAutoRetrieve : 'false',
					callback : {
						itemEdit : self.toEdit
					}
				});

				// 新增、编辑表单
				self.editFormPanel = document.getElementById("groupadminForm");
				// 新增、编辑表单viewModel
				self.groupAdminForm = new ko.formViewModel({
					url : 'setting/groupAdmin',
					callback : {
						beforeSave:function() {
							var formItem = self.groupAdminForm.formItem();
							formItem.isValid = ko.validation.group(formItem);
							//显示没通过消息
							formItem.isValid.showAllMessages();
							if (formItem.isValid().length!=0) {
								return -1;
							}
						},
						saveSuccess : self.toRefresh
					}
				});
				
				//树model
				self.groupCateTree = new ko.zTreeView('groupCateTree_groupAdmin', {
					url : 'setting/groupCate',
					callback : {
						treeNodeChanged : self.nodeChange
						}
				})

			};

			return viewModel;
		})

define([ "knockout",'AjaxUtil', "./dbcpVO.js","MsgBox","knockout-validation","components", "bootstrap" ],
		function(ko,AjaxUtil,Dbcp,MsgBox,kv) {

			function viewModel() {
				var self = this;

				// 页面显示
				self.choosenView = ko.observable('list');
				// 返回列表
				self.toList = function() {
					self.choosenView('list');
				};

				// 查询框使用
				self.searchForm = new ko.searchViewModel(new Dbcp({}), {
					callback : {
						search : function(searhForm) {
							self.compDbcpId.findAll(searhForm)
						}
					}
				});

				// 刷新
				self.toRefresh = function() {
					$('#myModal').modal('hide');
					self.compDbcpId.refresh();
				}

				// 新增
				self.toAdd = function() {
					self.compDbcpForm.setForm(new Dbcp({}));
					$('#myModal').modal();
				};

				// 修改
				self.toEdit = function(dbcp) {
					self.compDbcpForm.setForm(new Dbcp(dbcp));
					$('#myModal').modal();
				};

				// 详情
				self.toDetail = function(dbcp) {
					self.compDbcpForm.setForm(new Dbcp(dbcp));
					$('#detailModal').modal();
				};
				
				//测试连接
				self.testConnection = function(){
					var compDbcp = ko.toJS(self.compDbcpForm.formItem());
					$('#testConnection').button('loading')
					AjaxUtil.call({
						url : "setting/compDbcp/testConnection",
						data : compDbcp,
						type : 'post',
						success: function(data) {
							$('#testConnection').button('reset')
						}
					});
				}

				//校验
				self.validate = function() {
					var formItem = self.compDbcpForm.formItem();
					formItem.isValid = ko.validation.group(formItem);
					//显示没通过消息
					formItem.isValid.showAllMessages();
					if (formItem.isValid().length!=0) {
						return -1;
					}
				}

				/** **********************************Model************************************ */

				self.compDbcpId = new ko.gridViewModel({
					url : 'setting/compDbcp',
					callback : {
						itemEdit : self.toEdit,
						itemSet : self.toDetail
					}
				});

				// 新增、编辑表单viewModel
				self.compDbcpForm = new ko.formViewModel({
					url : 'setting/compDbcp',
					callback : {
						beforeSave : self.validate,
						saveSuccess : self.toRefresh
					}
				});

				// 多条删除
				self.delDbcp = function() {

					// 把选中的fdId以空格隔开
					var fdIdListJson = $("input[name='fdIdArr']:checked")
							.serializeArray();
					
					var fdIdList = "";
					for (var i = 0; i < fdIdListJson.length; i++) {
						fdIdList = fdIdList + fdIdListJson[i].value + " ";
					}

//					AjaxUtil.call({
//						url : "/setting/compDbcp/delDbcp",
//						data : {
//							"fdIdList" : fdIdList
//						},
//						type : "get",
//						success : function(data, textStatus, XMLHttpRequest) {
//							if (data.info == 'success') {
//							}
//							if (data.info == 'error') {
//							}
//							self.getAnotherPage();
//						}
//					});

				}

			}
			;

			return viewModel;

		})

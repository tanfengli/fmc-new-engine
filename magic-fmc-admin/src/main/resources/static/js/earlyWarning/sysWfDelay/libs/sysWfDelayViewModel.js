
define([ "knockout",'AjaxUtil', "./sysWfDelayVO.js",  "./sysWfDelayFlowVO.js", "MsgBox","components",
			"bootstrap" ],
function(ko,AjaxUtil, SysWfDelay,SysWfDelayFlow, MsgBox) {

	function viewModel() {
		
		
		// 查询form
		self.searchForm = new ko.searchViewModel(new SysWfDelay({}), {
			callback : {
				search : function(searhForm) {
					self.gridViewModel.findAll(searhForm)
				}
			}
		});

		// toolbar
		self.gridToolbarForm = {
			//新增
			toAdd : function(){
				var form = new SysWfDelayFlow();
				self.editForm.setForm(form);
				
				$('#delayWindow').modal();
			},
			defalutDelayForm : ko.observable(null),
			//启用/禁用
			initDefalutDelay : function(){
				$('#defalutDelayWindow').modal();
				AjaxUtil.call({
					url:'sys/wfDelay/getDefaultDelayTime',
					success:function(data){
						self.gridToolbarForm.defalutDelayForm(data);
					}
				})
			},
			saveDefalutDelay : function(){
				AjaxUtil.call({
					url:'sys/wfDelay/saveDefaultDelayTime',
					type:'post',
					data:ko.toJS(self.gridToolbarForm.defalutDelayForm),
					success:function(){
						$('#defalutDelayWindow').modal('hide');
					}
				})
			}
		}
		
		
		/*********viewModel**********/
		ko.formViewModel.prototype = {
			// 编辑
			toEdit : function(val){
				var form = new SysWfDelayFlow(val);
				self.editForm.setForm(form);
				$('#templateName').val(val.fdTemplateName);
				$('#delayWindow').modal();
			},
			selectTemplate : function(template){
				self.editForm.formItem().fdTemplateId=template.fdId;
				$('#templateName').val(template.fdName)
			}
		}
		// 新增、编辑角色关系表单viewModel
		self.editForm = new ko.formViewModel({
			url : 'sys/wfDelay',
			callback : {
				beforeSave : function(vm,formItem){
				},
				saveSuccess : function(){
					self.gridViewModel.refresh();
					$('#delayWindow').modal('hide');
				}
			}
		});
		
		
		// 表格viewModel
		self.gridViewModel = new ko.gridViewModel( {
			url : 'sys/wfDelay',
			callback : {
				itemEdit : self.editForm.toEdit
			}
		});


		

	};
	return viewModel;
})

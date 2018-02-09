
define([ "knockout",'AjaxUtil', "./wfMonitorTimeVO.js",  "./wfMonitorTimeWorkVO.js", "MsgBox","components",
			"bootstrap" ],
function(ko,AjaxUtil, WfMonitorTime,WfMonitorTimeWork, MsgBox) {

	function viewModel() {
		
		
		// 查询form
		self.searchForm = new ko.searchViewModel(new WfMonitorTime({}), {
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
				var form = new WfMonitorTimeWork();
				form.fdMonthList(['1','2','3','4','5','6','7','8','9','10','11','12'])
				self.editForm.setForm(form);
				
				$('#timeWindow').modal();
			},
			//启用/禁用
			toUse : function(val){
				
			}
		}
		
		
		/*********viewModel**********/
		ko.formViewModel.prototype = {
			// 编辑
			toEdit : function(val){
				var form = new WfMonitorTimeWork(val);
				if(val.fdMonths&&val.fdMonths!=''){
					form.fdMonthList(val.fdMonths.split(';'))
				}
				self.editForm.setForm(form);
				$('#timeWindow').modal();
			},
			getCalendarList : function(){
				AjaxUtil.call({
					url:'wfMonitor/calendar/getEnabled',
					data:{fdId:''},
					success:function(data){
						self.editForm.calendarList(data);
					}
				})
			},
			calendarList : ko.observableArray()
		}
		// 新增、编辑角色关系表单viewModel
		self.editForm = new ko.formViewModel({
			url : 'wfMonitor/time',
			callback : {
				beforeSave : function(vm,formItem){
				},
				saveSuccess : function(){
					self.gridViewModel.refresh();
					$('#timeWindow').modal('hide');
				}
			}
		});
		
		
		// 表格viewModel
		self.gridViewModel = new ko.gridViewModel( {
			url : 'wfMonitor/time',
			callback : {
				itemEdit : self.editForm.toEdit
			}
		});


		

	};
	return viewModel;
})

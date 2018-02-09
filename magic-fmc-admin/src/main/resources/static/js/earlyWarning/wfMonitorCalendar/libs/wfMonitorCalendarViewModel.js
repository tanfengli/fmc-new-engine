
define([ "knockout",'AjaxUtil', "./wfMonitorCalendarVO.js", "./wfMonitorCalendarMainVO.js", "MsgBox","components",
			"bootstrap" ],
function(ko,AjaxUtil, WfMonitorCalendar, WfMonitorCalendarMain, MsgBox) {

	function viewModel() {
		
		if(!ko.components.isRegistered("calendar")){
			ko.components.register('calendar',{
			    viewModel:{
			       require:'../blocks/setting/calendar/calendar.js'
			    },
			    template:{
			    	 require:'text!blocks/setting/calendar/calendar.html'
			    }
			});
		}
		
		var self = this;
		
		// 查询form
		self.searchForm = new ko.searchViewModel(new WfMonitorCalendar({}), {
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
				var wfMonitorCalendarMain = new WfMonitorCalendarMain();
				var form = new Object();
				form.wfMonitorCalendarMain = wfMonitorCalendarMain ;
				form.calendarList = self.editForm.calendarList;
				form.wfMonitorCalendarMain.fdWeekStart('1');
				form.wfMonitorCalendarMain.fdWeekEnd('5');
				self.editForm.setForm(form);
				
				$('#calendarWindow').modal();
				self.editForm.calendarForm({calendarId:null,year:null,startWeek:'1',endWeek:'5'});
			},
			//启用/禁用
			toUse : function(val){
				
			}
		}
		
		
		/*********viewModel**********/
		ko.formViewModel.prototype = {
			// 编辑
			toEdit : function(val){
				var wfMonitorCalendarMain = new WfMonitorCalendarMain(val);
				var form = new Object();
				form.wfMonitorCalendarMain = wfMonitorCalendarMain;
				form.calendarList = self.editForm.calendarList;
				self.editForm.setForm(form);
				$('#calendarWindow').modal();
				self.editForm.calendarForm({calendarId:val.fdId,startWeek:form.wfMonitorCalendarMain.fdWeekStart(),endWeek:form.wfMonitorCalendarMain.fdWeekEnd()});
			},
			// 选择范围
			elementForm : {
				elementIds : '',
				elementNames : ''
			},
			openScopeWindow : function(){
				var form = self.editForm.formItem();
				self.editForm.elementForm.elementIds = form.wfMonitorCalendarMain.fdScopeIds();
				self.editForm.elementForm.elementNames = form.wfMonitorCalendarMain.fdScopeNames();
				$('#memberSelectWindow').modal();
			},
			scopeCallBack : function(elements,names,ids){
				var form = self.editForm.formItem();
				form.wfMonitorCalendarMain.fdScopeIds(ids);
				form.wfMonitorCalendarMain.fdScopeNames(names);
			},
			// 日历组件form
			calendarForm : ko.observable({
				calendarId : '',
				year : '',
				startWeek : '',
				endWeek : ''
			}),
			calendarList : ko.observable([])
		}
		// 新增、编辑角色关系表单viewModel
		self.editForm = new ko.formViewModel({
			url : 'wfMonitor/calendar',
			callback : {
				afterSetForm:function(vm,formItem){
					formItem.wfMonitorCalendarMain.fdWeekStart.subscribe(function(val){
						var calForm = self.editForm.calendarForm();
						calForm.startWeek=val;
						self.editForm.calendarForm(calForm)
					});
					formItem.wfMonitorCalendarMain.fdWeekEnd.subscribe(function(val){
						var calForm = self.editForm.calendarForm();
						calForm.endWeek=val;
						self.editForm.calendarForm(calForm)
					});
				},
				beforeSave : function(vm,formItem){
					console.info(formItem());
//					return -1;
				},
				saveSuccess : function(){
					self.searchForm.search();
					$('#calendarWindow').modal('hide');
				}
			}
		});
		
		
		// 表格viewModel
		self.gridViewModel = new ko.gridViewModel( {
			url : 'wfMonitor/calendar',
			callback : {
				itemEdit : self.editForm.toEdit
			}
		});


	};
	return viewModel;
})

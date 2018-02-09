 
define([ "knockout", "AjaxUtil","./commonTemplateVO.js",'Flowchart',"components","bootstrap" ], function(ko,AjaxUtil,
		CommonTemp,FlowChart) {  	
	
	function viewModel() {
		var self = this;
		
		//页面切换标识
		self.visibleCatalog = ko.observable("list");
		
		self.editorArray = ko.observableArray([]);
		
		//流程图
		self.editFlowchart = {
			flowchart : {}
		}
		
		//流程图Xml
		self.flowXml = ko.observable();
		
		//流程图节点列表
		self.flowNodeList = ko.observableArray([]);
		
		//查询框使用
		self.searchForm = new ko.searchViewModel(new CommonTemp({}),{
			callback: {
				search: function(searhForm){ 
					self.grid.findAll(searhForm)
				}
			}
		});
		 
		self.toAdd = function(){
			self.visibleCatalog("edit");
			self.flowXml(null);
			self.form.setForm(new CommonTemp({}));
		};
		
		self.toEdit = function(om) {
			//获取流程图
			AjaxUtil.call({
				url:"flowchart/setting/getCommonContentAndTran",
				data:{fdId:om.fdId}, 
				success:function(data){
					self.visibleCatalog("edit");
					self.flowXml(data); 
					self.form.setForm(new CommonTemp(om)); 
					
				}
			});
		}
		
		self.beforeSave = function(viewModel,formItem){
			if(self.editFlowchart.chart!=undefined){
				// 校验流程图是否符合规则
				if(!self.editFlowchart.chart.checkFlow()){
					return -1;
				}
				self.form.formItem().fdFlowContent=self.editFlowchart.chart.getFlowchartXml();
			}
			// 校验必填项
			var isValid = ko.validation.group(formItem);
			isValid.showAllMessages();
			if (isValid().length!=0) {
				return -1;
			}
		
		}
		
		
		
		self.returnForm=function(){
			self.visibleCatalog("list");
		}
		
		self.grid = new ko.gridViewModel({
			url : 'sys/commonTemplate',
			callback : {
				itemEdit: self.toEdit
			}
		});
		
		self.onSaveSuccess = function(){
			self.grid.refresh();
			self.visibleCatalog("list");
		}
		
		self.form = new ko.formViewModel({
			url : 'sys/commonTemplate',
			callback : {
				  beforeSave: self.beforeSave,
				  saveSuccess:self.onSaveSuccess
			}
		});
	
		// 传给组件的form
		self.elementForm = ko.pureComputed(function() {
			return {
				elementArray : ko.toJS(self.editorArray)
			};
		}, this);
		
		// 组件回调函数
		self.selectNewElement = function(elements,memberNames){  
			
			var memberIds = "";
			var memberNames = ""; 
			if(null!= elements){ 
				for (var i=0;i< ko.toJS(elements).length;i++){
					memberIds = memberIds+ ko.toJS(elements)[i].fdId+';';
					memberNames = memberNames+ ko.toJS(elements)[i].fdName+';';
				}
			} 
			  
			$('#editor').val(memberNames); 
			$("#orgPerId").val(memberIds);
			self.form.formItem().privilegerNames=memberNames;   
			self.form.formItem().privilegerIds = memberIds;    
		}
		
	};  
	 
	return viewModel;
})

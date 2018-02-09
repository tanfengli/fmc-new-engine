define(["knockout","AjaxUtil","../SysReportNodeVO.js","components","bootstrap"],function(ko,AjaxUtil,SysReportNode){  
	function viewModel(){
		//模型别名
		var self=this;
		
		//主页面内容切换
		self.chooseView=ko.observable('list');
		
		ko.searchViewModel.prototype = {
			initForm:function(){
				$(".form_datetime").datetimepicker({
					language:'zh-CN',
			        format:'yyyy-mm-dd',
			        weekStart:1,
			        todayBtn:1,
					autoclose:1,
					todayHighlight:1,
					startView:2,
					minView:"month",
					forceParse:0
			    });
				
				self.sysReportNodeSearchForm.search();
			},
			//模板选择回调
			selectTemplate:function(template){
				self.sysReportNodeSearchForm.formItem().fdTemplateName(template.fdName);
			},
			//清除记录
			clearTemplate:function(){
				self.sysReportNodeSearchForm.formItem().fdTemplateName("");
			}
		}
		//单据过滤模型
		self.sysReportNodeSearchForm=new ko.searchViewModel(new SysReportNode({}),{
			callback:{
				search:function(searhForm){
					if(searhForm().fdStartDate&&searhForm().fdStartDate.split(" ").length==1){
						searhForm().fdStartDate+=" 00:00:00";
			    	}
					if(searhForm().fdEndDate&&searhForm().fdEndDate.split(" ").length==1){
						searhForm().fdEndDate+=" 23:59:59";
			    	}
					self.sysReportNodeGridForm.findAll(searhForm);
				}
			}
		});
		
		//工具栏模型
		self.sysReportNodeToolbarForm={
			toExport:function(){
				var form=$("<form>"); 
				form.attr('style','display:none');  
				form.attr('target','');  
				form.attr('method','post');  
				form.attr('action','../report/node/overdue/export'); 
				
				var context=$('<input>');  
				context.attr('type','hidden');  
				context.attr('name','context');  
				context.attr('value',ko.toJSON(self.sysReportNodeSearchForm.formItem)); 
				
				var pageVO=$('<input>');  
				pageVO.attr('type','hidden');  
				pageVO.attr('name','pageVO');  
				pageVO.attr('value',ko.toJSON({number:self.sysReportNodeGridForm.data.number,size:self.sysReportNodeGridForm.data.size}));  
				
				$('body').append(form);  
				form.append(context);  
				form.append(pageVO);
				form.submit();  
				form.remove();
			},
			toImport:function(){
				AjaxUtil.call({
					url:"report/node/import", 
					dataType:"json",
					data:{context:ko.toJSON(self.sysReportNodeSearchForm.formItem)}, 
					type:"post",
					success:function(data){
					}
				});
			}
		}
		
	
		//单据列表模型
		self.sysReportNodeGridForm=new ko.gridViewModel({
			url:'/report/node',
			size:10
		});
	};
	
	return viewModel;
})
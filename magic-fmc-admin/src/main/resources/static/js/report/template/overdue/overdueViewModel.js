define(["knockout","AjaxUtil","../SysReportTemplateVO.js","components","bootstrap"],function(ko,AjaxUtil,SysReportTemplate){  
	function viewModel(){
		//模型别名
		var self=this;
		
		//主页面内容切换
		self.chooseView=ko.observable('list');
		
		//单据过滤模型
		ko.searchViewModel.prototype={
			//模板类别选择
			selectNewCate:function(cate){
				self.sysReportTemplateSearchForm.formItem().fdCategoryId=cate.fdId;
				self.sysReportTemplateSearchForm.formItem().fdCategoryName(cate.fdName);
			},
			//清除模板类别
			clearNewCate:function(){
				self.sysReportTemplateSearchForm.formItem().fdCategoryId=null;
				self.sysReportTemplateSearchForm.formItem().fdCategoryName("");
			},
			//模板选择回调
			selectTemplate:function(template){
				self.sysReportTemplateSearchForm.formItem().fdTemplateId=template.fdId;
				self.sysReportTemplateSearchForm.formItem().fdTemplateName(template.fdName);
			},
			//清除模板记录
			clearTemplate:function(){
				self.sysReportTemplateSearchForm.formItem().fdTemplateId=null;
				self.sysReportTemplateSearchForm.formItem().fdTemplateName("");
			},
			//清除所有
			clear:function(){
				self.sysReportTemplateSearchForm.formItem().fdCategoryId=null;
				self.sysReportTemplateSearchForm.formItem().fdCategoryName("");
				
				self.sysReportTemplateSearchForm.formItem().fdTemplateId=null;
				self.sysReportTemplateSearchForm.formItem().fdTemplateName("");
			}
		}
		
		//单据过滤模型
		self.sysReportTemplateSearchForm=new ko.searchViewModel(new SysReportTemplate({}),{
			callback:{
				search:function(searhForm){
					if(searhForm().fdStartDate&&searhForm().fdStartDate.split(" ").length==1){
						searhForm().fdStartDate += " 00:00:00";
			    	}
					
					if(searhForm().fdEndDate&&searhForm().fdEndDate.split(" ").length==1){
						searhForm().fdEndDate += " 23:59:59";
			    	}
					
					self.sysReportTemplateGridForm.findAll(searhForm);
				}
			}
		});
		
		//初始化日期控件
		self.initReportTemplateSearchForm=function(){
			$(".sysReportTemplateTime").datetimepicker({
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
			
			self.sysReportTemplateSearchForm.search();
		}
		
		//工具栏模型
		self.sysReportTemplateToolbarForm={
			toExport:function(){
				var form=$("<form>"); 
				form.attr('style','display:none');  
				form.attr('target','');  
				form.attr('method','post');  
				form.attr('action','../report/template/overdue/export'); 
				
				var context=$('<input>');  
				context.attr('type','hidden');  
				context.attr('name','context');  
				context.attr('value',ko.toJSON(self.sysReportTemplateSearchForm.formItem)); 
				
				var pageVO=$('<input>');  
				pageVO.attr('type','hidden');  
				pageVO.attr('name','pageVO');  
				pageVO.attr('value',ko.toJSON({number:self.sysReportTemplateGridForm.data.number,size:self.sysReportTemplateGridForm.data.size}));  
				
				$('body').append(form);  
				form.append(context);  
				form.append(pageVO);
				form.submit();  
				form.remove();
			},
			toImport:function(){
				AjaxUtil.call({
					url:"report/template/import", 
					dataType:"json",
					data:{context:ko.toJSON(self.sysReportTemplateSearchForm.formItem)}, 
					type:"post",
					success:function(data){
					}
				});
			}
		}
		
	
		//单据列表模型
		self.sysReportTemplateGridForm=new ko.gridViewModel({
			url:'/report/template',
			size:10
		});
	};
	
	return viewModel;
})
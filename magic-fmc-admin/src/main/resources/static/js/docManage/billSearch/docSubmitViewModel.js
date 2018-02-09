define(["knockout","../../../js/common/VO/docSubmitVO.js","components","bootstrap"],function(ko,DocSubmit){  
	function viewModel(){
		if (!ko.components.isRegistered("billDetail")){
			ko.components.register('billDetail',{
			    viewModel:{
			       require:'../blocks/bill/billDetail/billDetail.js'
			    },
			    template:{
			    	 require:'text!blocks/bill/billDetail/billDetail.html'
			    }
			});
		}
	
		//模型别名
		var self=this;
		
		//单据详细信息
		self.wfInstanceId=ko.observable();
		
		//主页面内容切换
		self.chooseView=ko.observable('list');
		
		//显示单据列表主页面
		self.toList=function(){
			self.chooseView('list');
		};
		
		//查看详情
		self.toDetail=function(doc){
			self.wfInstanceId(doc.fdId);
		}
		
		//查看详情回调
		self.toDetailPage=function(){
			self.chooseView('detail');
		}
		
		//单据过滤模型
		ko.searchViewModel.prototype={
			//模板选择回调
			selectTemplate:function(template){
				self.searchForm.formItem().fdTemplateId=template.fdId;
				self.searchForm.formItem().fdTemplateName(template.fdName);
			},
			//清除记录
			clearTemplate:function(){
				self.searchForm.formItem().fdTemplateId=null;
				self.searchForm.formItem().fdTemplateName("");
			},
			//清除所有
			clear:function(){
				self.searchForm.formItem().docSubject("");
				self.searchForm.formItem().dealName("");
				self.searchForm.formItem().applyCode("");
				self.searchForm.formItem().creatorUserName("");
				self.searchForm.formItem().fdTemplateId=null;
				self.searchForm.formItem().fdTemplateName("");
			}
		}
		
		//单据过滤模型
		self.searchForm=new ko.searchViewModel(new DocSubmit({}),{
			callback:{
				search:function(searhForm){
					self.docSubmitId.findAll(searhForm)
				}
			}
		});
		
		//工具栏模型
		self.toolbarViewModel={
			deleteInstance:function(){
				var fdIdStr=$("input[name='fdIdArr']:checked").serialize();
				if(confirm("确认删除当前选中实例.")){
					AjaxUtil.call({
						url:'/aboutMyself/myBills/deleteInstance',
						data:{fdIdStr:fdIdStr},
						success:function(){
							self.searchForm.search();
						}
					})
				}
			}
		}
		
		//扩展单据列表模型
		ko.gridViewModel.prototype={
			checkboxSelect:function(data,event){
	            if(event.currentTarget.checked){  
	                $('input[name="fdIdArr"]').each(function(){  
	                    $(this).prop("checked",true);  
	                });  
		        }else{  
		            $('input[name="fdIdArr"]').each(function(){  
	                    $(this).removeAttr("checked",false);  
	                });  
		        } 
	            
	            return true;
			}
		}
	
		//单据列表模型
		self.docSubmitId=new ko.gridViewModel({
			url:'/docManage/billSearch',
			size:10,
			callback:{ 
				itemEdit:self.toDetail,
				retrieveEnd:function(){
					$("#selectAll").prop("checked",false);
				}
			}
		});
	};
	
	return viewModel;
})
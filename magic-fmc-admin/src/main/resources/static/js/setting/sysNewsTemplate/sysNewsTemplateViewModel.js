define(["knockout",'AjaxUtil',"./sysNewsTemplateVO.js",'Flowchart',"MsgBox","components","bootstrap"], 
	function(ko,AjaxUtil,SysNewsTemplate,FlowChart,MsgBox){  

	function viewModel(){
		var self=this;
	
		// 选中节点
		self.selectNodeId=ko.observable();
		self.selectNodeName=ko.observable();
	
		// 流程图
		self.editFlowchart={
			flowchart:{}
		}
		
		// 流程图XML
		self.flowXml=ko.observable();
		// 其他模板名称
		self.otherTemName=ko.observable();
	
		// 可维护者
		self.templateEditor=ko.observableArray([]);
		// 可使用者
		self.templateReader=ko.observableArray([]);
	
		// 详情页所用
		self.detailForm=ko.observable(new SysNewsTemplate());
	
		// 流程运用方式
		self.pattern=ko.observable("custom");
	
		// 当前模板
		self.temId=ko.observable();
	
		// 窗口显示
		self.window=ko.observable('list');
		self.curPage=ko.observable('');
	
		self.flag=ko.observable(true);
		self.tab=ko.observable('basic');
		self.rendered=ko.observable(false);
	
		// 回到首页
		self.showList=function(){
			self.window('list');
			self.flag(true);
			// 清空其它模板名称
			self.otherTemName('');
			self.tab('basic');
			self.rendered(false);
			self.flowXml(null);
			self.editFlowchart={flowchart:{}}
			if(self.curPage()=='detail')
			self.flowchart_detail.clear();
		}
	
		// 显示编辑页面
		self.showEdit=function(){
			self.window('edit');
		}
	
		// 显示详情页面
		self.showDetail=function(){
			self.window('detail');
		}
	
	
		var sysNewsTemplate=new SysNewsTemplate({});
		self.searchForm=new ko.searchViewModel(ko.toJS(new SysNewsTemplate({fdCategoryId:self.selectNodeId()})),{
			callback:{
				search:function(searhForm){
					searhForm.fdCategoryId = self.selectNodeId();
					self.gridViewModel.findAll(searhForm);
				}
			}
		});
	
		// 获得业务系统
		var busy=function(name,id){
			this.busyName=name;
			this.busyId=id;
		};
	
		self.busiSysArray=ko.observableArray();
		
		self.getIdAndName=function(){
			AjaxUtil.call({
				type:"POST",
				url:'/setting/sysNewsTemplate/findBusiSys',
				success:function(data){
					self.busiSysArray(data);
				}
			});
		}();
	
	
		// 初始化并打开新增页面
		self.toAdd=function(){
			self.curPage('add');
			if(self.selectNodeId()=="0"){
				MsgBox.warning("请选择具体的模板类别.");
				
				return ;
			}
			
			var temp=new SysNewsTemplate({busiSysArray:ko.toJS(self.busiSysArray)});
			temp.fdCategoryId=self.selectNodeId();
			temp.categoryName=self.selectNodeName();
			temp.pattern('default');
			
			self.editForm.setForm(temp);
			self.showEdit();
			$('#myTab a:first').tab('show');
		};
	
		// 初始化并打开编辑页面
		self.toEdit=function(val,fdId){
			self.curPage('edit');
			self.temId(val.fdId);
			AjaxUtil.call({
				url:'setting/sysNewsTemplate/findForm',
				type:'post',
				data:{context:ko.toJSON(val)},
				success:function(data){
					var template=new SysNewsTemplate(data);
					template.busiSysArray=ko.toJS(self.busiSysArray);
					template.pattern('custom');
					template.fdVersion('0');
					// 若为复制操作
					if(fdId!=undefined){
						template.fdId=null;
						template.fdName="复制 "+template.fdName();
					}
					self.editForm.setForm(template);
					self.editForm.versionGridViewModel.findAll(data.fdId);
					self.templateEditor(data.templateEditor);
					self.templateReader(data.templateReader);
					var memberNames=self.elementToName(self.templateEditor());
					$('#editor').val(memberNames);
					
					var memberNames2=self.elementToName(self.templateReader());
					$('#reader').val(memberNames2);
					
					self.showEdit();
					$('#myTab a:first').tab('show');
				}
			})
		};
	
		self.afterRender=function(){
			$("#lct_edit").on('shown.bs.tab',function(e){
				 self.rendered(true);
				 if(self.curPage()=='edit'){
					self.getFlowChart(self.temId(),self.flowchart_edit);
				 }
	        });
		}
	
		// 通过模板获取流程图
		self.getFlowChart=function(templateId){
			if(self.flag()){
				AjaxUtil.call({
					url:"flowchart/setting/getContentByTemplateId", 
					data:{templateId:self.temId()}, 
					type:"get",
					async:false,
					success:function(data){
						self.flowXml(data);
						self.flag(false);
					}
			});
		}}
	
		// 刷新
		self.toRefresh=function(){
			self.gridViewModel.refresh();
			self.showList();
		}

		// 复选框全选
		self.checkboxSelect=function(data,event){
            if(event.currentTarget.checked){  
                $('input[name="fdIdArr"]').each(function(){  
                    //此处如果用attr，会出现第三次失效的情况  
                    $(this).prop("checked",true);  
                });  
	        }else{  
	            $('input[name="fdIdArr"]').each(function(){  
                    $(this).removeAttr("checked",false);  
                });  
	        }  
            return true;
		}
	
		// 节点变化
		self.nodeChange=function(node){
			// 监控当前节点
			self.selectNodeId(node.fdId);
			self.selectNodeName(node.fdName);
			if(self.searchForm.formItem()!=undefined){
				self.searchForm.formItem().fdCategoryId=node.fdId;
			}
			
			// 根据选中刷新列表
			self.searchForm.search();
		};
	
		self.toUse=function(val){
			var fdIdStr=$("input[name='fdIdArr']:checked").serialize();
		
			if(fdIdStr==""){
				MsgBox.warning('请至少选中一个模板.');
			}else{
				AjaxUtil.call({
					url:"setting/sysNewsTemplate/toEnable", 
					data:{ids:fdIdStr,status:val}, 
					type:"get",
					success:function(data){
						self.gridViewModel.refresh();
					}
				});
			}
		}
		
		self.toExport=function(){
			var fdIdStr=$("input[name='fdIdArr']:checked").serialize();
			if(fdIdStr==""){
				MsgBox.warning('请至少选中一个模板.');
			}else{
				var form=$("<form>"); 
				form.attr('style','display:none');  
				form.attr('target','');  
				form.attr('method','post');  
				form.attr('action','../setting/sysNewsTemplate/exportTemplate');  
				var ids=$('<input>');  
				ids.attr('type','hidden');  
				ids.attr('name','ids');  
				ids.attr('value',fdIdStr);  
				$('body').append(form);  
				form.append(ids);  
				form.submit();  
				form.remove();  
			}
		}
		
		self.toImport=function(){
			$('#template_upload_modal').modal('show');
		}
		
		self.importFileName=ko.observable();
		
		self.importFileSelect=function(){
			var index=$("input[name='filename']").val().lastIndexOf("\\");
			var fileName=$("input[name='filename']").val().substr((index+1));
			self.importFileName(fileName)
		}
		
		self.callBack=function(){
			$('#template_upload_form').ajaxSubmit({
				url:"../setting/sysNewsTemplate/importTemplate",
				contentType:"application/x-www-form-urlencoded;charset=utf-8",
				type:"post",
				success:function(data){
					if(data.code=="100"){
						self.gridViewModel.refresh();
						MsgBox.success(data.message);
					}else{
						MsgBox.warning(data.message);
					}
				},
				error:function(data){
				} 
			});
			
			
			$('#template_upload_modal').modal('hide');
		}
	
		// 以字符串类型返回群组成员名称
		self.elementToName=function(elements){
			var memberNames="";
			for(var i=0;i<elements.length;i++){
				memberNames=memberNames+elements[i].fdName+';';
			}
			
			return memberNames;
		}
	
		// 以字符串类型返回群组成员名称
		self.elementToId=function(elements){
			var memberNames="";
			for(var i=0;i<elements.length;i++){
				memberNames=memberNames+elements[i].fdId+';';
			}
			
			return memberNames;
		}
	
		// 传给组件
		self.elementForm=ko.pureComputed(function(){
			return {
				elementArray:ko.toJS(self.templateEditor)
			};
		},this);
	
		// 表单提交前处理
		self.toValidate=function(){
			var form=self.editForm.formItem();
			form.isValid=ko.validation.group(form);
			// 显示没通过消息
			form.isValid.showAllMessages();
			if(form.isValid().length!=0){
				return -1;
			}
			
			if(form.pattern()=='custom'){
				if(self.editFlowchart.chart!=undefined){
					// 校验流程图是否符合规则
					if(!self.editFlowchart.chart.checkFlow()){
						return -1;
					}
					self.editForm.formItem().flowXml=self.editFlowchart.chart.getFlowchartXml();
				}else{// 第一次进入页面复制模板时需要初始化
					self.getFlowChart(self.temId(),self.flowchart_edit);
					self.editForm.formItem().flowXml=self.flowXml();
				}
			}
		}
	
		// 保存成功回调函数
		self.saveSuccess=function(){
			self.showList();
			self.gridViewModel.refresh();
		}
	
		/*********************************组件回调函数************************************/
		// 权限修改组件回调
		self.selectNewElement=function(elements,memberNames){
			// 回调设置可维护者和可使用者信息
			if(self.editForm.formItem().clickType=='editor'){
				$('#editor').val(memberNames);
				self.editForm.formItem().templateEditor=ko.toJS(elements);
				self.templateEditor(ko.toJS(elements));
			}else if(self.editForm.formItem().clickType=='reader'){
				$('#reader').val(memberNames);
				self.editForm.formItem().templateReader=ko.toJS(elements);
				self.templateReader(ko.toJS(elements));
			}else{
				MsgBox.error('发生错误.');
			}
		}
		
		// 选择新类别回调
		self.selectNewCate=function(cate){
			self.editForm.formItem().fdCategoryId=cate.fdId;
			self.editForm.formItem().categoryName=cate.fdName;
			$('#cate').val(cate.fdName);
		}
	
		// 选择通用模板回调
		self.selectNewTempalte=function(template){
			var form = self.editForm.formItem();
			// 自定义模板
			if(form.pattern()=='custom'){
				form.flowXml=ko.toJS(template).fdFlowContent;
				self.flowXml(ko.toJS(template).fdFlowContent);
				MsgBox.success('['+template.fdName+']模板加载成功。')
			}
		}
	
		// 选择其他模板回调
		self.selectTemplate = function(template){
			self.editForm.formItem().otherTemplateId=ko.toJS(template).fdId;
			self.otherTemName(ko.toJS(template).fdName);
		}
	
		/*********************************Model************************************/
		self.gridViewModel=new ko.gridViewModel({
			url:'setting/sysNewsTemplate',
			size:9,
			isAutoRetrieve:'false',
			callback:{
				itemEdit:self.toEdit,
				retrieveEnd: function(){
					$("#selectAll").attr("checked",false);
				}
			}
		});
		
		ko.formViewModel.prototype = {
			versionGridViewModel:new ko.gridViewModel({
				url:'setting/sysNewsTemplate/getHistoryVersion',
				isAutoRetrieve:'false',
				size:10
			})
		}
	
		// 新增,编辑表单模型
		self.editForm=new ko.formViewModel({
			url:'setting/sysNewsTemplate',
			callback:{
				beforeSave:self.toValidate,
				saveSuccess:self.saveSuccess,
				afterSetForm:function(){
					 //日期选择器配置
					  $(".form_datetime").datetimepicker({
							language:  'zh-CN',
					        format: 'yyyy-mm-dd hh:ii:ss',
					        weekStart: 1,
					        todayBtn:  1,
							autoclose: 1,
							todayHighlight: 1,
							startView: 2,
							minView: "month",
							forceParse: 0
					    });
				}
			}
		});
		
		self.treeId='jqxTree_categorySetting';
		// 类别树
		self.categoryTree=new ko.zTreeView(self.treeId,{
			url:'setting/category',
			callback:{
				treeNodeChanged:self.nodeChange
			}
		});
	};
	
	return viewModel;
})


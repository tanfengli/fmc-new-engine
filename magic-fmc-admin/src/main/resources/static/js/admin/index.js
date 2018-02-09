requirejs(['../js/common.js'],function(common){
	requirejs(['knockout','knockout.mapping','jquery','AjaxUtil','ko-amd-helpers','domReady!'],
		function(ko,mapping,$,AjaxUtil,koAmdHelpers){

		ko.amdTemplateEngine.defaultPath="getViews";
		ko.amdTemplateEngine.defaultSuffix="";
		ko.amdTemplateEngine.defaultRequireTextPluginName="text";
		
		var viewModel=new function(){
			var self=this;
			
			self.queryMethod=function(){
				ko.utils.domData.clear(window.document.body);
				
				AjaxUtil.call({
					url:'/docManage/billSearch/index',
					type:"get",
					dataType:"html",
					success:function(responseText){
						$("#stage").html(responseText)
					}
				});
			};
			
			self.adjustMethod=function(){
				ko.utils.domData.clear(window.document.body);
				
				AjaxUtil.call({
					url:'/docManage/billProcess/index',
					type:"get",
					dataType:"html",
					success:function(responseText){
						$("#stage").html(responseText)
					}
				});
			};
			
			self.reportMethod=function(){
				ko.utils.domData.clear(window.document.body);
				
				AjaxUtil.call({
					url:'/sys/wfExceptionLog/index',
					type:"get",
					dataType:"html",
					success:function(responseText){
						$("#stage").html(responseText)
					}
				});
			};
			
			self.buildMethod=function(){
				ko.utils.domData.clear(window.document.body);
				
				AjaxUtil.call({
					url:'/setting/sysNewsTemplate/index',
					type:"get",
					dataType:"html",
					success:function(responseText){
						$("#stage").html(responseText)
					}
				});
			};
			
			self.taskData=mapping.fromJS({
				content:[],
				totalElements:0,
				totalPages:0,
				size:10,
				number:0,
				sort:null,
				first:false,
				numberOfElements:0
			});
			
			self.taskSize=ko.observable(0);
			
			AjaxUtil.call({
				type:"post",
				url:'aboutMyself/myApprovalBills/auditPending/findAll',
				dataType:"json",
				data:{
					context:ko.toJSON({}),
					pageVO:ko.toJSON({
						number:0,
						size:5
					})
				},
				success:function(data) {
					mapping.fromJS(data,self.taskData);
					self.taskSize(data.totalElements);
				}
			});
			
			self.taskMethod=function(){
				ko.utils.domData.clear(window.document.body);
				
				AjaxUtil.call({
					url:'aboutMyself/myApprovalBills/index?status=todo',
					type:"get",
					dataType:"html",
					success:function(responseText){
						$("#stage").html(responseText)
					}
				});
			};
			
			self.auditData=mapping.fromJS({
				content:[],
				totalElements:0,
				totalPages:0,
				size:10,
				number:0,
				sort:null,
				first:false,
				numberOfElements:0
			});
			
			self.auditSize=ko.observable(0);
			
			AjaxUtil.call({
				type:"post",
				url:'aboutMyself/myApprovalBills/audited/findAll',
				dataType:"json",
				data:{
					context:ko.toJSON({}),
					pageVO:ko.toJSON({
						number:0,
						size:5
					})
				},
				success:function(data) {
					mapping.fromJS(data,self.auditData);
					self.auditSize(data.totalElements);
				}
			});
			
			self.auditMethod=function(){
				ko.utils.domData.clear(window.document.body);
				
				AjaxUtil.call({
					url:'aboutMyself/myApprovalBills/index?status=done',
					type:"get",
					dataType:"html",
					success:function(responseText){
						$("#stage").html(responseText)
					}
				});
			};
			
			self.submitData=mapping.fromJS({
				content:[],
				totalElements:0,
				totalPages:0,
				size:10,
				number:0,
				sort:null,
				first:false,
				numberOfElements:0
			});
			
			self.submitSize=ko.observable(0);
			
			AjaxUtil.call({
				type:"post",
				url:'docManage/billDraft/findAll',
				dataType:"json",
				data:{
					context:ko.toJSON({}),
					pageVO:ko.toJSON({
						number:0,
						size:5
					})
				},
				success:function(data) {
					mapping.fromJS(data,self.submitData);
					self.submitSize(data.totalElements);
				}
			});
			
			self.submitMethod=function(){
				ko.utils.domData.clear(window.document.body);
				
				AjaxUtil.call({
					url:'docManage/billDraft/index',
					type:"get",
					dataType:"html",
					success:function(responseText){
						$("#stage").html(responseText)
					}
				});
			};
			
			self.readData=mapping.fromJS({
				content:[],
				totalElements:0,
				totalPages:0,
				size:10,
				number:0,
				sort:null,
				first:false,
				numberOfElements:0
			});
			
			self.readSize=ko.observable(0);
			
			AjaxUtil.call({
				type:"post",
				url:'aboutMyself/myPendingRead/findAll',
				dataType:"json",
				data:{
					context:ko.toJSON({}),
					pageVO:ko.toJSON({
						number:0,
						size:5
					})
				},
				success:function(data) {
					mapping.fromJS(data,self.readData);
					self.readSize(data.totalElements);
				}
			});
			
			self.readMethod=function(){
				ko.utils.domData.clear(window.document.body);
				
				AjaxUtil.call({
					url:'aboutMyself/myPendingRead/index',
					type:"get",
					dataType:"html",
					success:function(responseText){
						$("#stage").html(responseText)
					}
				});
			};
		};
		
		ko.applyBindings(viewModel);
	});
});
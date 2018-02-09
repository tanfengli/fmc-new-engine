define(['knockout','AjaxUtil',"MsgBox"],function(ko,AjaxUtil,MsgBox){
	function orgCommonTree(params){  
		var self=this;
		
		self.treeId='orgCommonTree';
		
		self.selectNode=ko.observable();
		
		self.firstNode=ko.observable();

		self.context=ko.observableArray([]);
		
		self.selectContext=ko.observable();
		//标记
		self.initFlag=true;
		
		//组织架构类型
		self.orgTypeArr=ko.observableArray(["4","8"]);
		
		//选中的组织类型
		self.elementSelected=ko.observableArray(["4","8"])
		//查询条件
		self.filterName=ko.observable("");
		//查询模式
		self.searchMode=ko.observable(true);
		//查询Form
		self.sysOrgElementForm=ko.pureComputed(function(){
			return {
				nodeId:null,
				checkedValue:ko.toJS(self.elementSelected),
				filterName:self.filterName(),
				searchMode:self.searchMode()
			};
		},this);

		//模态框初始化加载
		self.initModal=$('#treeSelectPerWindow').on('show.bs.modal',function(){
			  $('#chosen_element').html('');
			  if(params.orgTypeArr&&params.orgTypeArr.length){
				  self.orgTypeArr.removeAll();
				  self.elementSelected.removeAll();
				  for(i=0;i<params.orgTypeArr.length;i++){
					  self.orgTypeArr.push(params.orgTypeArr[i]);
					  self.elementSelected.push(params.orgTypeArr[i]);
				  }
			  }
			  //self.nodeChange(self.selectNode());
			  
			  if(ko.toJS(params.elementId)&&ko.toJS(params.elementId)!=''){
				  AjaxUtil.call({
						url:"sys/orgElement/getElementAllPath",   
						type:"post",
						data:{fdId:ko.toJS(params.elementId)},
						success:function(data){
							$('#chosen_element').html(data.description);
						}
				  });
			  }
		})
		
		//模态框关闭后触发
		$('#treeSelectPerWindow').on('hidden.bs.modal',function(){
		})
		
		
		self.nodeChange=function(node){
			if(self.initFlag){
				self.firstNode(node);
			}
			self.initFlag=false;

			self.selectNode(node);
			
			//获取form
			var form=self.sysOrgElementForm();
			form.nodeId=node.fdId;
			
			//后台查询相应人员对象
			AjaxUtil.call({
				url:"sys/orgElement/findElements",   
				type:"post",
				data:{context:ko.toJSON(form)},
				dataType:"JSON",
				success:function(data){     
					self.context(data);    
				}
			}); 
		}
		
		//树model
		self.orgCommonTree = new ko.zTreeView(self.treeId, {
			url : 'sys/orgElement',
			callback : {
				treeNodeChanged : self.nodeChange
				}
		})

		
		self.selectOnchang = function(obj){ 
			 //获取被选中的option标签选项 
			 AjaxUtil.call({
					url: "sys/orgElement/getElementPathDescription",   
					type : "post",
					data : {fdId: obj.fdId},
					dataType : "JSON",
					async:false,
					success : function(data) {
						$("#fdName_info").html(data); 
						//选中复制，用于点击确定取值
						self.selectContext(obj);
					}
			 }); 
		} 
		  
		
		//搜索
		self.inquiry = function(){
			AjaxUtil.call({
				url : 'sys/orgElement/findFdNameType',
				type : 'post',
				data : {context: ko.toJSON(self.sysOrgElementForm)},
				success : function(data){
					self.context(data);
				}
			})
		}
		
		//监控，响应条件变化时刷新待选列表数据
		self.sysOrgElementForm.subscribe(function(val) {
			if(val.filterName==null||val.filterName==""){
			}else{
				self.inquiry();
			}
		});
		
		self.search = function(){
			if(self.filterName()==null||self.filterName()==""){
				MsgBox.warning("请输入关键字！");
			}else{
				self.inquiry();
			}
		}
		
		//查询框清除
		self.reset = function(){
			self.filterName(null);
		}
		
		
		self.passSelectNode = function(){ 
			//获取待选列表选中值对象 
			params.callFunc(ko.toJS(self.selectContext)); 
		}
		
		self.cancelSelectNode = function(){
			params.callFunc({fdId:'',fdName:''}); 
		}
		
	};
	return orgCommonTree;
});
define(["knockout","AjaxUtil","./sysBusiSysVO.js","components","bootstrap"],function(ko,AjaxUtil,SysBusiSys) {   
	function viewModel(){
		if (!ko.components.isRegistered("defineParameter")){
			ko.components.register('defineParameter',{
			    viewModel:{
			       require:'../blocks/sys/defineParameter/defineParameter.js'
			    },
			    template:{
			    	 require:'text!blocks/sys/defineParameter/defineParameter.html'
			    }
			});
		}
		
		var self=this;
		
		self.visibleCatalog=ko.observable(true);
	 
		self.searchForm=new ko.searchViewModel(new SysBusiSys({}),{
			callback:{
				search:function(searhForm){
					self.grid.findAll(searhForm)
				}
			}
		});
		   
		self.parameters=ko.observableArray();
		self.parameterCallback=function(params){
			self.sysBusiSysForm.formItem().fdParameter(ko.toJSON(params));
		}
		
		self.toDefineParameter=function(){
			$('#defineParameterWindow').modal();
		}
		
		self.toAdd=function(){
			self.parameters([]);
			self.sysBusiSysForm.setForm(new SysBusiSys({fdEnabled:"1"}));
			$('#sysBusiSysWindow').modal();
		};
		
		self.toEdit=function(vs){	
			AjaxUtil.call({
				url:"sys/sysBusiSys/getSysBusiSysParam",   
				type:"post",
				data:{fdId:vs.fdId},
				dataType:"JSON",
				async:false,
				success:function(data){
					self.parameters(data);
				}
			}); 
			
			
			self.sysBusiSysForm.setForm(new SysBusiSys(vs)); 
			$('#sysBusiSysWindow').modal(); 
		}
		
		self.grid=new ko.gridViewModel({
			url:'/sys/sysBusiSys',
			callback:{
				itemEdit:self.toEdit
			}
		});
		
		self.sysBusiSysForm=new ko.formViewModel({
			url:'/sys/sysBusiSys',
			callback:{
				beforeSave:function(formViewModel,formItem){
					//校验
					var vaildResult=ko.validation.group(formItem);
					//显示没通过消息
					vaildResult.showAllMessages();
					if(vaildResult().length!=0){
						return -1;
					}
				},
				saveSuccess:function(){
					self.grid.refresh();
					$("#sysBusiSysWindow").modal("hide");
				}
			}
		});
		
		self.grantInterfaceForm=new ko.formViewModel({
			url:'/sys/sysBusiSys/grantInterface',
			callback:{
				saveSuccess:function(){
					self.grid.refresh();
					$("#grantInterfaceWindow").modal("hide");
				}
			}
		});
		
		self.toGrantInterface=function(vs){
			self.grantInterfaceForm.setForm({fdBusiId:vs.fdId,interfaceIds:ko.observable("")});
			
			AjaxUtil.call({
				url:"sys/sysBusiSys/grantInterface/findAllElements",   
				type:"post",
				dataType:"JSON",
				async:false,
				success:function(data){
					var setting={
			            view:{
			                selectedMulti:false,
			                nameIsHTML:false,
			                showIcon:true,
			                showLine:true,
			                showTitle:true,
			                expandSpeed:""
			            },
			            data:{
			                keep:{
			                    parent:true
			                },
			                key:{
			                    name:"fdName"
			                },
			                simpleData:{
			                    enable:true,
			                    idKey:"fdId",
			                    pIdKey:"fdParentId",
			                    rootPId:null
			                }
			            },
			            edit:{
			                enable:false,
			                showRemoveBtn:false,
			                showRenameBtn:false,
			                drag:{
			                    isCopy:false,
			                    isMove:true
			                }
			            },
			            check:{
			                enable:true,
			                chkStyle:"checkbox"
			            },
			            callback:{
			                onCheck:function(event,treeId,treeNode){
			                	var treeObj = $.fn.zTree.getZTreeObj(treeId);
			                	var changeCheckedNodes = treeObj.getChangeCheckedNodes();
								var interfaceIds="";
								for(var i=0;i<changeCheckedNodes.length;i++){
									if(changeCheckedNodes[i].fdIsLeaf==1&&changeCheckedNodes[i].checked==true){
										interfaceIds=interfaceIds+changeCheckedNodes[i].fdId+";";
									}
								}
								if(interfaceIds!=""){
									interfaceIds=interfaceIds.substring(0,interfaceIds.length-1);
								}
								
								self.grantInterfaceForm.formItem().interfaceIds(interfaceIds);
			                }
			            }
			        };
					
					$.fn.zTree.init($("#grantInterfaceTree"),setting,data);
				}
			}); 
			
			AjaxUtil.call({
				url:"sys/sysBusiSys/grantInterface/getSysInterface",   
				type:"post",
				data:{fdBusiId:vs.fdId},
				dataType:"JSON",
				async:false,
				success:function(data){
					if (data.length>1){
						var interfaceIds="";
						for(var i=0;i<data.length;i++){
							interfaceIds=interfaceIds + data[i].fdId + ";"
						}
						if(interfaceIds!=""){
							interfaceIds=interfaceIds.substring(0,interfaceIds.length-1);
						}
						
						self.grantInterfaceForm.formItem().interfaceIds(interfaceIds);
						
						var treeObj=$.fn.zTree.getZTreeObj("grantInterfaceTree");
						for(var i=0,l=data.length;i<l;i++){
							var node=treeObj.getNodeByParam("fdId",data[i].fdId,null);
							treeObj.checkNode(node,true,true);
						}
					}
				}
			}); 
			
			$('#grantInterfaceWindow').modal();
		}
		
		self.grantCallbackForm=new ko.formViewModel({
			url:'/sys/sysBusiSys/grantCallback/',
			callback:{
				saveSuccess:function(){
					self.grid.refresh();
					$("#grantCallbackModal").modal("hide");
				}
			}
		});
		
		self.toGrantCallback=function(vs){	
			AjaxUtil.call({
				url:"sys/sysBusiSys/getSysBusiInterface",   
				type:"post",
				data:{fdId:vs.fdId,fdType:"1"},
				dataType:"JSON",
				async:false,
				success:function(data){
					if (data.length<1){
						self.grantCallbackForm.setForm({fdBusiId:vs.fdId,fdIsBack:"",fdBackAddress:""});
					}else{
						self.grantCallbackForm.setForm(data[0]);
					}
				}
			}); 
			
			$('#grantCallbackModal').modal(); 
		}
	};  

	return viewModel;
})
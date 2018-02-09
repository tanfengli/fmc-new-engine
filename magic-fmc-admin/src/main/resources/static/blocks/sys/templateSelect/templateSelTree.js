define(['knockout','AjaxUtil'],function(ko,AjaxUtil){
	function templateSelTree(params){
		var self=this;
		//树Id
		self.treeId='templateSelTree';
		//当前选中节点
		self.selectNode=ko.observable();
		//已选列表
		self.chosenList=ko.observableArray([]);
		
		//选中相关节点
		self.templateTreeSelect=function(templateIds){
			AjaxUtil.call({
				url:'setting/sysNewsTemplate/categoryMain/findCheckedElements',
				type:'post',
				data:{fdIds:templateIds},
				success:function(data){
					self.groupCateTree.checkNodes(data,true);
				}
			})
		}
		
		//模态框
		$('#templateSelWindow').on('show.bs.modal',function(){
			//获取已选列表
			var templateArr=[];
			var data=ko.toJS(params.data);
			
			self.groupCateTree.checkAllNodes(false);

			if(data&&data.templateIds&&data.templateNames){
				var ids=data.templateIds.split(',');
				var names=data.templateNames.split(',');
				for(i=0;i<ids.length;i++){
					var templateObj={fdId:ids[i],fdName:names[i]}
					templateArr.push(templateObj);
				}
				
				//设置选中记录
				self.chosenList(templateArr);
				
				//初始化模板树
				self.templateTreeSelect(data.templateIds);
			}
		})
		
		
		
		//节点勾选/取消勾选
		self.onCheck=function(){
			var changeCheckedNodes=self.groupCateTree.getCheckedNodes(true);
			self.chosenList([]);
			
			for(var index=0;index<changeCheckedNodes.length;index++){
				if(changeCheckedNodes[index].fdIsLeaf==1){
					self.chosenList.push(changeCheckedNodes[index]);
				}
			}
			
			//去重
			var chosenList=self.chosenList();
			self.chosenList(chosenList.unique());
		}
		
		//树
		self.groupCateTree=new ko.zTreeView(self.treeId,{
			url:"setting/sysNewsTemplate/categoryMain",
			chkEnable:true,
			callback:{
				treeNodeChanged:function(node){
					self.selectNode(node);
				},
				onCheck:self.onCheck,
				clicked:function(currNode){
				}
			}
		})
		
		//数组去重
		Array.prototype.unique=function(){
			var res=[];
			var json={};
			for(var i=0;i<this.length;i++){
				if(!json[this[i].fdId]){
					res.push(this[i]);
					json[this[i].fdId]=1;
				}
			}
		 
			return res;
		}
		
		//删除
		self.delOne=function(){
			var selOpt=$("#selOption option:selected");   
		    selOpt.remove();  
		    self.chosenList.remove(function(item){return item.fdId==selOpt.val()});
		  
		    var templateIds="";
		    for(var index=0;index<self.chosenList().length;index++){
		    	templateIds=templateIds+self.chosenList()[index].fdId;
		    	templateIds=templateIds+",";
		    }
		    if(templateIds!=null){
		    	templateIds=templateIds.substring(0,templateIds.length-1);
		    }
		    
		    self.groupCateTree.checkAllNodes(false);
		    self.templateTreeSelect(templateIds);
		}
		
		//全部删除
		self.delAll=function(){
			self.chosenList([]);
			self.groupCateTree.checkAllNodes(false);
		}
		
		//上移
		self.up=function(){
			//获取当前选中元素的索引
			var selectedIndex=$("#selOption option:selected").index(); 
			if(selectedIndex>=1){
				//插入上一个
				$("#selOption option:selected").insertBefore($("#selOption option:selected").prev('option'));
			}
	    }
		
		//下移
		self.down=function(){
            //获取最后一个option的索引值
            var optionNum=$("#selOption option").size()-1;
            //获取当前选中元素的索引值
            var selectedIndex=$("#selOption option:selected").index(); 
            if(selectedIndex<6){
                //插入下一个
                $("#selOption option:selected").insertAfter($("#selOption option:selected").next('option'));
            }
        }
		
		//确定
		self.passSelectNode=function(){  
			var strName="";  
	        var strId="";  
	        var allOptions=[];
	        $("#selOption option").each(function(){
	        	 //获取单个text
	             var txt=$(this).text(); 
	             //获取单个value 
	             var val=$(this).val(); 
	             allOptions.push({fdId:val,fdName:txt});  
	             strName+=txt+',';
	             strId+=val+',';
	        });
	        strName=strName.substr(0,strName.length-1);
	        strId=strId.substr(0,strId.length-1);
	        
	        self.chosenList([]);
	        //self.selectNode选中树节点
	        params.callFunc(allOptions,strName,strId);
	        
		}
	};
	
	return templateSelTree;
});
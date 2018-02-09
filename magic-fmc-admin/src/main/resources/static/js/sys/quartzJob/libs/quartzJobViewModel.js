define([ "knockout","AjaxUtil", "./quartzJobVO.js", "components","bootstrap" ], function(ko,AjaxUtil,
		QuartzJob) {   
	
	function viewModel() {
		var self = this;
		
		self.visibleCatalog = ko.observable(true);
	
		//查询框使用
		self.newQuartzJob = new ko.searchViewModel(new QuartzJob({}),{
			callback: {
				search: function(searhForm){
					self.grid.findAll(searhForm)
				}
			}
		});
		var fieldStr = '秒,分,时,日,月,周,年';
		var weekdayNameStr = '星期日,星期一,星期二,星期三,星期四,星期五,星期六';
		
		self.changeCronTrigger = function(expression){
			var obj = ko.toJS(expression);
			if(obj==null||obj==undefined||obj==""){
				return;
			}
			var fieldNames =  fieldStr.split(",");
			var weekdayNames=  weekdayNameStr.split(",");
			var rtnVal = "";
			var fields = ko.toJS(expression).split(/\s+/);
			if (fields.length == 7 && !fields[6]=="*")
				rtnVal = fields[6] + fieldNames[6];
			if (!fields[4]=="*")
				rtnVal += fields[4] + fieldNames[4];
			if (fields[5]=="?") {
				if (!fields[3]=="*")
					rtnVal += fields[3] + fieldNames[3];
			} else {
				if (!fields[5]=="*") {
					for (var i = 0; i < fields[5].length; i++) {
						var c = fields[5];
						if (c >= '1' && c <= '7')
							rtnVal += weekdayNames[c - '1'];
						else
							rtnVal += c;
					}
				}
			}
			for (var i = 2; i >= 0; i--){
				var obj = fields[i]+"";
				if (!rtnVal=="" ||obj!="*"){
					rtnVal += fields[i] + fieldNames[i];
				}
			}
				
			return  rtnVal;
			
		}
		
		self.fdSubjectText=function(value){
			var strArr = ko.toJS(value).split(":");
			if(strArr.length>1){
				return strArr[1];
			}else{
				return value;
			}
		}
		
		self.toEdit = function(vs) {	
			var quartzObj = new QuartzJob(vs);
			quartzObj.parseCronExpression();
			self.quartzForm.setForm(quartzObj); 
			$('#myModal').modal(); 
//			var val = $('input:radio[name="varIsJdbc"]:checked').val();
//			if(val==1){
//			   $('#showOne').show();$('#showTwo').show();
//			   $('#detailOne').hide();$('#detailTwo').hide();
//			}else{
//				$('#detailOne').show();$('#detailTwo').show();
//				$('#showOne').hide();$('#showTwo').hide();
//			}
		}
		
		//初始化日期控件
		self.initQuartzJobTime=function(){
			$(".quartzJobTime").datetimepicker({
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
		}
		self.sysQuartzJobSearchForm=new ko.searchViewModel(new QuartzJob({}),{
			callback:{
				search:function(searhForm){
					
					self.grid.findAll(searhForm);
				}
			}
		});
		self.toAdd = function() {	
			var quartzObj = new QuartzJob(new Object());
			self.quartzForm.setForm(quartzObj); 
			$('#myModal').modal(); 
			self.initQuartzJobTime();
//			var val = $('input:radio[name="varIsJdbc"]:checked').val();
//			if(val==1){
//			   $('#showOne').show();$('#showTwo').show();
//			   $('#detailOne').hide();$('#detailTwo').hide();
//			}else{
//				$('#detailOne').show();$('#detailTwo').show();
//				$('#showOne').hide();$('#showTwo').hide();
//			}
		};
		self.init=function(){
			AjaxUtil.call({
				url:'sys/quartzJob/init',
				type:'post',
				//data:{context:ko.toJSON(val)},
				success:function(data){
					console.info(data);
				}
			})
		}
		self.toUse = function(flag,val){
			val.fdEnabled(flag);
//			console.info(ko.toJSON(val));
			AjaxUtil.call({
				url:'sys/quartzJob/toEnable',
				type:'post',
				data:{context:ko.toJSON(val)},
				success:function(data){
					console.info(data);
				}
			})
		};
		self.execute = function(job){
//			var job = ko.toJSON(val);
			AjaxUtil.call({
				url:'sys/quartzJob/execute',
				type:'post',
				data:{fdId:job.fdId()},
				success:function(data){
//					console.info(data);
				}
			})
		}
		self.grid = new ko.gridViewModel({
			url : 'sys/quartzJob',
			callback : {
				itemEdit: self.toEdit
			}
		});
//		self.sysQuartzJobGridForm=new ko.gridViewModel({
//			url:'sys/quartzJob',
//			size:10
//		});
		self.onSaveSuccess = function(){
			self.grid.refresh();
		}
		
		self.quartzForm = new ko.formViewModel({
			url : 'sys/quartzJob',
			callback : {
				beforeSave:function(formViewModel, formItem){
					//校验
					 console.info(ko.toJSON(formItem));
				},
			  saveSuccess:self.onSaveSuccess
			}
			 
		}); 
		

		var variable = function(name, id) {
			this.name = name;
			this.id = id;
		};

		self.varTypeArray = ko.observableArray([{name:"--请选择--",id:""},{name:"通用",id:"0"}]);
		
		
	} 

return viewModel;
})
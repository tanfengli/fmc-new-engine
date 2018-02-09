define(['knockout','Flowchart','AjaxUtil','sysWfBusinessFormVO'],
	function(ko,Flowchart,AjaxUtil,SysWfBusinessForm){
	function viewModel(){
		if(!ko.components.isRegistered("flowchartView")){
			ko.components.register('flowchartView',{
			    viewModel:{
			       require:'../blocks/flowchart/flowchartView/flowchartView.js'
			    },
			    template:{
			    	 require:'text!blocks/flowchart/flowchartView/flowchartView.html'
			    }
			});
		}
		
		var self=this;
		var fdId="";
		var reg=new RegExp("(^|&)fdId=([^&]*)(&|$)","i"); 
	    var r=window.location.search.substr(1).match(reg);
	    if(r==null){
	    	fdId="";
	    }else{
	    	fdId=unescape(r[2])
	    }
	    
		self.sysWfBusinessForm=ko.observable(new SysWfBusinessForm());
		var chartflowData=new Object();
		self.flowchart=new Flowchart({
			flowchartId:"flowchart_detail",
			mod:ko.observable()
		});
		
		self.loadData=function(){
			AjaxUtil.call({
				url:"anonymous/getSysWfBusiness",
				data:{fdId:fdId},
				type:"post",
				async:false,
				success:function(data){
					self.sysWfBusinessForm(new SysWfBusinessForm(data));
					
					chartflowData.xmlString=self.sysWfBusinessForm().fdFlowContent();
		    		chartflowData.runningNodes=self.sysWfBusinessForm().nodeList();
		    		chartflowData.historyNodes=self.sysWfBusinessForm().historyList();
		    		self.flowchart.init(chartflowData);
				}
			});
		}
		
		self.loadData();
	}
	
	return viewModel;
})
var selectType;

requirejs([ '../js/common.js' ], function(common) {
	requirejs([ 'knockout','AjaxUtil','js/flowchart/setting/libs/xmlNodeVO.js', "components", 'ko-amd-helpers', 'bootstrap' ,'domReady!' ],
			function(ko,XmlNode,AjaxUtil,koAmdHelpers) {
			if (!ko.components.isRegistered("flowchartDesigner")) {
				ko.components.register('flowchartDesigner', {
				    viewModel:  {
				       require:'blocks/flowchart/setting/flowchartDesigner.js'
				    },
				    template:  {
				    	 require:'text!blocks/flowchart/setting/flowchartDesigner.html'
				    }
				}); 
			}	
				
				var viewModel = function() {
					
					var self = this;
					
					//初始化机器人信息
					self.xmlString = ko.observable("");
					
					var data;
					var reviewNodes;
					var xmlContent;
					AjaxUtil.call({
						url:"flowchart/setting/getFlowContent",
						type:"get", 
						data:data, 
						success:function(xml) {  
							xmlContent=xml;
						}
					});
						
					 self.flowXml = ko.pureComputed(function() {
						 
							return  ko.toJS(self.xmlString)
					 }, this);
					
					
					//延迟加载xml
					setTimeout(function() { 
						self.xmlString(xmlContent);
					 }, 500); 
					
					
				};
				
				ko.amdTemplateEngine.defaultPath = "getViews";
				ko.amdTemplateEngine.defaultSuffix = "";
				ko.amdTemplateEngine.defaultRequireTextPluginName = "text";

				ko.applyBindings(new viewModel());
			});
});




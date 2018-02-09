define(["knockout"],function(ko){
	function WorkFlow(){
		var self=this;
		
		self.WorkFlow_Com_HtmlEscape=function(s){
			var re = /&/g;
			
			if(s==null||s==""){
				return "";
			}
			
			s=s.replace(re,"&amp;");
			re=/\"/g;
			s=s.replace(re,"&quot;");
			re=/'/g;
			s=s.replace(re,'&#39;');
			re=/</g;
			s=s.replace(re,"&lt;");
			re=/>/g;
			
			return s.replace(re,"&gt;");
		}
	
		//功能:转换对象为字符串,适合XML字符数据的拼装
		//参数:data:数据对象,nodeName:根对象对应XML的节点名
		self.WorkFlow_BuildXMLString=function(data,nodeName){
			var rtnStr="<"+nodeName;
			var childStr="";
			
			for(var p in data){
				//XMLNODENAME是为了组织XML方便使用的对象
				if(p=="XMLNODENAME"){
					continue;
				}
				
				var value = data[p];
				if(value==null){
					continue;
				}
				
				if(typeof(value)=="function"){
					continue;
				}
				
				//若p是个数字,则使用XMLNODENAME作为子对象名
				if(!isNaN(p)){
					p=value.XMLNODENAME;
				}
				
				switch(typeof(value)){
					case "string":
						if(value==""){
							continue;
						}
						value=self.WorkFlow_Com_HtmlEscape(data[p]);
						
						break;
					case "object":
						childStr+="\r\n"+self.WorkFlow_BuildXMLString(value,p);
						continue;
				}
				rtnStr+=" "+p+"=\""+value+"\"";
			}
			
			if(childStr==""){
				rtnStr+=" />";
			}else{
				rtnStr+=">"+childStr+"\r\n</"+nodeName+">";
			}
			
			return rtnStr;
		}

		//功能:将XML字符串转换为对象
		//参数:XML字符串,或XML节点对象,isArray:是否为数组子对象,该参数仅在内部调用的时候使用,其它调用请忽略该参数
		//nodeNames:需要到得该节点内容的节点名,如果不需要取值,调用请忽略该参数
		self.WorkFlow_LoadXMLData=function(xml,isArray,nodeNames){
			var xmlObj=xml;
			if(typeof(xml)=="string"){
				if(xml==null||xml==""){
					return;
				}
				
				//非IE方式XML解析方法
				if(!document.all){
					xmlObj=(new DOMParser()).parseFromString(xml,"text/xml");
				}else{
					xmlObj=new ActiveXObject("MSXML2.DOMDocument.3.0");
					xmlObj.loadXML(xml);
				}
				xmlObj=xmlObj.firstChild;
			}
			
			var rtnVal=new Array();
	
			//读取属性
			rtnVal.XMLNODENAME=xmlObj.nodeName;
			var attNodes=xmlObj.attributes;
			for(var i=0;i<attNodes.length;i++){
				rtnVal[attNodes[i].nodeName]=attNodes[i].nodeValue;
			}
			
			if(rtnVal.CHILDRENISARRAY!=null){
				isArray=rtnVal.CHILDRENISARRAY=="true";
			}
	
			//读取节点内容
			if(nodeNames!=null){
				if(nodeNames.indexOf(xmlObj.nodeName)!=-1){
					return xmlObj.text;  
				}
			}
	
			//读取子对象
			for(var node=xmlObj.firstChild;node!=null;node=node.nextSibling){
				if(node.nodeType==1){
					if(isArray){
						rtnVal[rtnVal.length]=self.WorkFlow_LoadXMLData(node,false,nodeNames);
					}else{					
						rtnVal[node.nodeName]=self.WorkFlow_LoadXMLData(node,true,nodeNames);
					}
				}
			}
			
			return rtnVal;
		}
	}

	return WorkFlow;
});
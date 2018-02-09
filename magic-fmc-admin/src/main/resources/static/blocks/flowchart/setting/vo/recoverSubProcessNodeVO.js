define(["knockout","MsgBox"],function(ko,MsgBox){ 
	RecoverSubProcessNode=function(options){ 
		var setting=$.extend({ 
			XMLNODENAME:null,
			advice:[],
			id:null,
			name:null,
			x:null,
			y:null,
			description:null,
			configContent:null,
			subProcessNodes:ko.observableArray([]),
			subProcessNode:ko.observable(""),
			variableScope:ko.observable("2"),
			formulaType:ko.observable("0"),
			startParamenters:ko.observableArray([]),
			showParamenters:ko.observableArray([]),
		},options||{})
		
		return{ 
			XMLNODENAME:setting.XMLNODENAME,
			advice:setting.advice,
			id:setting.id,
			name:setting.name,
			x:setting.x,
			y:setting.y,
			description:setting.description,
			configContent:setting.configContent,
			subProcessNodes:setting.subProcessNodes,
			subProcessNode:setting.subProcessNode,
			variableScope:setting.variableScope,
			formulaType:setting.formulaType,
			startParamenters:setting.startParamenters,
			showParamenters:setting.showParamenters,
			initData:function(data,nodes){
				this.subProcessNodes([]);
				if(nodes!=undefined){
					for(var index=0;index<nodes.length;index++){
						this.subProcessNodes.push({subProcessNodeText:nodes[index].name,subProcessNodeValue:nodes[index].id});
						this.subProcessNode(nodes[0].id);
					}
				}	
				
				if(data.configContent!=undefined){
					var configContent=$.parseJSON(data.configContent);
					
					this.subProcessNode(configContent.subProcessNode);
					this.variableScope(configContent.variableScope+"");
					this.startParamenters(configContent.recoverParamenters);
					for(var index=0;index<configContent.recoverParamenters.length;index++){
						var startParamenter=configContent.recoverParamenters[index];
						var value=startParamenter.expression.text;
						var key=value.replace(/\$/g,"").split(".")[1];
						this.showParamenters.push({key:ko.observable(key),value:ko.observable(value)});
					}
				}
			},
			selectformula:function(data){
				if(data.fdName!=""){
					var name=data.fdName.replace(/\$/g,"").split(".")[1];
					var expression=data.fdId.replace(/\$/g,"");
					
					this.startParamenters()[this.formulaType()-1].name().text=name;
					this.startParamenters()[this.formulaType()-1].name().value=expression;
					this.startParamenters()[this.formulaType()-1].name().type="String";
					this.startParamenters()[this.formulaType()-1].name().notNull="true";
					this.startParamenters()[this.formulaType()-1].expression().text=data.fdName;
					this.startParamenters()[this.formulaType()-1].expression().value=data.fdId;
					
					this.showParamenters()[this.formulaType()-1].key(name);
					this.showParamenters()[this.formulaType()-1].value(data.fdName);
				}
			},
			addStartParamenter:function(){
				this.showParamenters.push({key:ko.observable(""),value:ko.observable("")});
				this.startParamenters.push({name:ko.observable({}),expression:ko.observable({})})
			},
			selectStartParamenter:function(index){
				this.formulaType(index);
				$('#formulaModal').modal();
			},
			saveData:function(){
				this.configContent="{\"subProcessNode\":\""+ko.toJS(this.subProcessNode)+"\","+
				"\"variableScope\":"+ko.toJS(this.variableScope)+","+
				"\"recoverRule\":{\"type\":1,\"expression\":{\"text\":\"\",\"value\":\"\"}}"+","+
				"\"recoverParamenters\":"+ko.toJSON(this.startParamenters())+"}";
			}
		};
	};
	
	return RecoverSubProcessNode;
})

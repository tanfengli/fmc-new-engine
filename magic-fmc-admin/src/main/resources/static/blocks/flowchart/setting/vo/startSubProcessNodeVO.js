define(["knockout","MsgBox"],function(ko,MsgBox){ 
	StartSubProcessNode=function(options){ 
		var setting=$.extend({ 
			XMLNODENAME:null,
			advice:[],
			id:null,
			name:null,
			x:null,
			y:null,
			synchronizeRight:true,
			description:null,
			configContent:null,
			subProcessName:ko.observable(),
			subProcessValue:ko.observable({}),
			startIdentityType:ko.observable("1"),
			startIdentityValue:ko.observable(""),
			startIdentityName:ko.observable(""),
			startIdentityShow:ko.observable(false),
			startCountType:ko.observable("1"),
			skipDraftNode:ko.observable(true),
			onErrorNotify:ko.observableArray(["1","2"]),
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
			synchronizeRight:setting.synchronizeRight,
			description:setting.description,
			configContent:setting.configContent,
			subProcessName:setting.subProcessName,
			subProcessValue:setting.subProcessValue,
			startIdentityType:setting.startIdentityType,
			startIdentityValue:setting.startIdentityValue,
			startIdentityName:setting.startIdentityName,
			startIdentityShow:setting.startIdentityShow,
			startCountType:setting.startCountType,
			skipDraftNode:setting.skipDraftNode,
			onErrorNotify:setting.onErrorNotify,
			formulaType:setting.formulaType,
			startParamenters:setting.startParamenters,
			showParamenters:setting.showParamenters,
			selectParameters:ko.observableArray([]),
			initData:function(data){
				if(data.configContent!=undefined){
					var configContent=$.parseJSON(data.configContent);
					this.subProcessName(configContent.subProcess.templateName);
					this.subProcessValue(configContent.subProcess);
					
					this.startIdentityType(configContent.startIdentity.type+"");
					this.startIdentityValue(configContent.startIdentity.values);
					this.startIdentityName(configContent.startIdentity.names);
					if(this.startIdentityType()=="1"){
						this.startIdentityShow(false);
					}else{
						this.startIdentityShow(true);
					}
					
					this.startCountType(configContent.startCountType+"");
					this.skipDraftNode(configContent.skipDraftNode);
					this.onErrorNotify(configContent.onErrorNotify);
					
					this.startParamenters(configContent.startParamenters);
					this.showParamenters([]);
					for(var index=0;index<configContent.startParamenters.length;index++){
						var startParamenter=configContent.startParamenters[index];
						if(startParamenter.expression!=undefined&&startParamenter.expression!=""){
							var value=startParamenter.expression.text;
							var key=startParamenter.name.text+";"+startParamenter.name.value;
							//var key=value.replace(/\$/g,"").split(".")[1];
							this.showParamenters.push({key:ko.observable(key),value:ko.observable(value)});
						}
					}
				}
				
				this.selectParameters().push({"key":"ID","value":"fdId"});
				this.selectParameters().push({"key":"单据类型","value":"fdModelId"});
				this.selectParameters().push({"key":"单据编号","value":"applyCode"});
				this.selectParameters().push({"key":"主题","value":"docSubject"});
				this.selectParameters().push({"key":"创建时间","value":"docCreateTime"});
				this.selectParameters().push({"key":"文档内容","value":"docContent"});
				this.selectParameters().push({"key":"点击率","value":"docReadCount"});
				this.selectParameters().push({"key":"文档重要度","value":"fdImportance"});
				this.selectParameters().push({"key":"内容简要","value":"fdDescription"});
				this.selectParameters().push({"key":"业务类型","value":"fdModelName"});
				this.selectParameters().push({"key":"单据状态","value":"docStatus"});
				this.selectParameters().push({"key":"报账人","value":"docCreator"});
			},
			selectSubProcess:function(){
				$("#templateTreeWindow").modal();
			},
			selectTemplate:function(data){
				this.subProcessName(data.fdName);
				this.subProcessValue().modelName="com.ruiyi.kmss.sys.news.model.SysNewsMain";
				this.subProcessValue().dictBean="";
				this.subProcessValue().templateId=data.fdId;
				this.subProcessValue().templateName=data.fdName;
				this.subProcessValue().createParam="DICT_BEAN=&fdTemplateId="+data.fdId;
			},
			setStartIdentity:function(data,event){
				if(data.startIdentityType()=="3"||data.startIdentityType()=="4"){
					this.startIdentityShow(true);
				}
				
				if(data.startIdentityType()=="1"){
					this.startIdentityShow(false);
				}
				
				return true;
			},
			selectStartIdentity:function(data,event){
				if(data.startIdentityType()=="3"){
					$('#memberSelectWindow').modal();
				}
				
				if(data.startIdentityType()=="4"){
					this.formulaType("0");
					$('#formulaModal').modal();
				}
			},
			selectNewElement:function(handlerIds,handlerNames){
				this.startIdentityName(handlerNames);
				this.startIdentityValue(handlerIds);
			},
			selectformula:function(data){
				ko.toJS(this.showParamenters());
				
				if(this.formulaType()=="0"){
					this.startIdentityName(data.fdName);
					this.startIdentityValue(data.fdId);
				}else{
					if(data.fdName!=""){
						var name=this.showParamenters()[this.formulaType()-1].key();
						if(name==undefined){
							var expression=data.fdId.replace(/\$/g,"");
							name=data.fdName.replace(/\$/g,"").split(".")[1]+";"+expression.trim();
						}
						
						this.startParamenters()[this.formulaType()-1].name().text=$.trim(name.split(";")[0]);
						this.startParamenters()[this.formulaType()-1].name().value=$.trim(name.split(";")[1]);
						this.startParamenters()[this.formulaType()-1].name().type="String";
						this.startParamenters()[this.formulaType()-1].name().notNull="true";
						this.startParamenters()[this.formulaType()-1].expression().text=$.trim(data.fdName);
						this.startParamenters()[this.formulaType()-1].expression().value=$.trim(data.fdId);
						
						this.showParamenters()[this.formulaType()-1].key(name);
						this.showParamenters()[this.formulaType()-1].value(data.fdName);
					}
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
				var startIdentity={type:"",names:"",values:""};
				startIdentity.type=this.startIdentityType();
				startIdentity.names=this.startIdentityName();
				startIdentity.values=this.startIdentityValue();
				
				this.configContent="{\"subProcess\":"+ko.toJSON(this.subProcessValue())+","+
				"\"startIdentity\":"+ko.toJSON(startIdentity)+","+
				"\"startCountType\":"+ko.toJS(this.startCountType)+","+
				"\"skipDraftNode\":"+ko.toJS(this.skipDraftNode)+","+
				"\"onErrorNotify\":"+ko.toJSON(this.onErrorNotify())+","+
				"\"startParamenters\":"+ko.toJSON(this.startParamenters())+"}";
			}
		};
	};
	
	return StartSubProcessNode;
})

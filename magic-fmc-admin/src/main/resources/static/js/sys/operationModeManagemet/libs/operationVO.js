define(["knockout","knockout-validation"],function(ko) { 
	
	OperationMode = function(options) {  
		var setting = $.extend({
			fdId : null,
			fdName: null,
			fdNodeType: null,
			fdIsDefault: null,
			operationsList: null,
			operationsListTwo: null 
		},options || {})
		
		return {
			fdId : setting.fdId,
			fdName: ko.observable(setting.fdName).extend({
                required: {
                	params:true,
                	message: "请输入方式名称。" 
                }
            }),
			fdNodeType: setting.fdNodeType,
			fdIsDefault: setting.fdIsDefault,
			operationsList: ko.observableArray(setting.operationsList),
			operationsListTwo: ko.observableArray(setting.operationsListTwo),
			operTypeChange: function(a,b){
				console.info(a)
				console.info(b);
				alert(1)
			},
			addOperation:function() { 
				var operObject = {
						fdId: '', 
						fdOperName: ko.observable(''), 
						fdOperType: ko.observable(''),
					};
				operObject.fdOperType.subscribe(function(val){
					switch(val){
					case '202':
						operObject.fdOperName('催办');
						break;
					case '203':
						operObject.fdOperName('撤回');
						break;
					case '204':
						operObject.fdOperName('废弃');
						break;
					}
				});
				this.operationsList.push(operObject); 
			},
			addOperationTwo:function() { 
				var operObject = {
						fdId: '', 
						fdOperName: ko.observable(''), 
						fdOperType: ko.observable(''),
					};
				operObject.fdOperType.subscribe(function(val){
					switch(val){
					case '101':
						operObject.fdOperName('通过');
						break;
					case '102':
						operObject.fdOperName('驳回');
						break;
					case '103':
						operObject.fdOperName('转办');
						break;
					case '104':
						operObject.fdOperName('沟通');
						break;
					case '105':
						operObject.fdOperName('废弃');
						break;
					}
				});
				this.operationsListTwo.push(operObject); 
			},
			removeOperation:function(Operation) {    
				this.operationsList.remove(Operation); 
			},
			removeOperationTwo:function(OperationTwo) {
				this.operationsListTwo.remove(OperationTwo);
			},
			upOperation:function(selectedItem){ 
				var index = ko.utils.arrayIndexOf(this.operationsList(),
						selectedItem);   
				
				ko.utils.arrayRemoveItem(this.operationsList(), selectedItem); 
				
				this.operationsList.splice(index - 1, 0, selectedItem);
			},
			downOperation:function(selectedItem){
				var index = ko.utils.arrayIndexOf(this.operationsList(),selectedItem);
 
				ko.utils.arrayRemoveItem(this.operationsList(), selectedItem);

				this.operationsList.splice(index + 1, 0, selectedItem)
			},
			upOperationTwo:function(selectedItem){ 
				var index = ko.utils.arrayIndexOf(this.operationsListTwo(),selectedItem);   
				
				ko.utils.arrayRemoveItem(this.operationsListTwo(), selectedItem); 
				
				this.operationsListTwo.splice(index - 1, 0, selectedItem);
			},
			downOperationTwo:function(selectedItem){
				var index = ko.utils.arrayIndexOf(this.operationsListTwo(),selectedItem);
 
				ko.utils.arrayRemoveItem(this.operationsListTwo(), selectedItem);

				this.operationsListTwo.splice(index + 1, 0, selectedItem)
			}
			
		};   
	};
	
	return OperationMode;

})
define(["knockout"],function(ko) { 
	
	LineNode = function(options) { 
		var setting = $.extend({ 
			XMLNODENAME:  null,
			condition:null,
		    disCondition:null,
		    endNodeId:null,
		    endPosition:null,
		    id:null,
		    name:null,
		    points:null,
		    startNodeId:null,
		    startPosition:null,
		    textPos:null,
		    
		    isBranch:ko.observable()
		},options || {})
		
		return { 
			XMLNODENAME :  setting.XMLNODENAME,
			condition : setting.condition,
			disCondition : setting.disCondition,
			endNodeId : setting.endNodeId,
			endPosition : setting.endPosition,
			id : setting.id,
			
			name :  setting.name,
			points : setting.points,
			startNodeId : setting.startPosition,
			endNodeId : setting.endNodeId,
			startPosition : setting.startPosition,
			textPos : setting.textPos,
			isBranch:setting.isBranch
			
		};   
	};
	
	return LineNode;
})

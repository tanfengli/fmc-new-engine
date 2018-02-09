VariableSet = function(configuration) {
    var self = this;
     
    self.fdId = ko.observable('' || configuration.fdId); 
    self.fdField = ko.observable('' || configuration.fdField); 
    self.fdValue = ko.observable('' || configuration.fdValue);  
 };
 
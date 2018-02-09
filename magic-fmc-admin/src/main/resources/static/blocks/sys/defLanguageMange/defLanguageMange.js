/**
 * 
 */
define([ 'knockout' ,'AjaxUtil',"MsgBox"], function(ko,AjaxUtil,MsgBox) {
	function defLanguageMange(params) {
		
		var self = this;
		
		
		self.DefLanguage = function () {
			return {
				fdId:ko.observable(null),
				fdUsageType : ko.observable(null),
		   		fdUsageContent : ko.observable(null),
		   		fdIsSysSetup : ko.observable(null),
		   		fdActiveFlag: ko.observable(null)
			};
		};
		
		self.formItem = ko.observable(null);
			
		self.formItem(new self.DefLanguage());
		
		self.passSelectNode = function(){
			
			AjaxUtil.call({
				url : 'sys/defLanguage/saveOrUpdateForm',
				type : 'post',
				data : {context : ko.toJSON(self.formItem)},
				success :　function(data){
					var defLanguage = new self.DefLanguage();
					self.formItem(defLanguage);
				}
			});
			
			params.callFunc();
		}
	};
	return defLanguageMange;
});
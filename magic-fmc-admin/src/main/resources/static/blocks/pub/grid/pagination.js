/**
 * 
 */
define([ 'knockout' ], function(ko) {
	function pagination(params) {

		var pagination = params.data;

		this.totalPages = pagination.totalPages;
		this.number = pagination.number;
		this.size = pagination.size;

		this.toPage = params.toPage;

		this.startPage = ko.computed(function() {
			return Math.floor(ko.utils.unwrapObservable(this.number) / 10) * 10;
		}, this);
		
		this.endPage = ko.computed(function() {
			var page = ko.utils
					.unwrapObservable(this.startPage) + 9;
			return page > ko.utils
					.unwrapObservable(this.totalPages) - 1 ? ko.utils
					.unwrapObservable(this.totalPages) - 1
					: page;
		}, this);
	}
	
	return pagination;
})
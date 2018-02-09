
define(["knockout","AjaxUtil","./orgElementVO.js","components","bootstrap"],function(ko,AjaxUtil,SysOrgElement){  
	function viewModel(){
	
		var self=this ;
		
		/***********************权限查询Start*********************/
		// 人员名称
		self.personName = ko.observable('');
		// 所属岗位名称
		self.postsName = ko.observable('');
		self.userClick = function(user){
			self.personName(user.fdName);
			AjaxUtil.call({
				url : 'authority/userAuth/getPost',
				data : {fdId : user.fdId},
				success : function(data){
					self.postsName(data);
				}
			})
			self.authorityGrid.findAll(user.fdId);
			$('#authorityWindow').modal();
		}
		
		// 权限列表
		self.authorityGrid=new ko.gridViewModel({
			url:'/authority/userAuth',
			size:2
		});
		/***********************权限查询End*********************/
		
		/***********************用户列表页面start***********************/
		
		self.searchForm=new ko.searchViewModel(new SysOrgElement({}),{
			callback:{
				search:function(searhForm){
					self.userGrid.findAll(searhForm)
				}
			}
		}); 
		
		// 用户列表
		self.userGrid=new ko.gridViewModel({
			url:'/authority/userAuth/user',
			size:10,
			callback:{
				itemEdit:self.userClick
			}
		});
		/***********************用户列表页面end***********************/
		
	};
	return viewModel;
})
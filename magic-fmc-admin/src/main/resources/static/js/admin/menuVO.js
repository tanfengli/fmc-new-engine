requirejs(['../js/common.js'],function(common){
	requirejs(['jquery','knockout','MsgBox','AjaxUtil','zTree-all',"components",'domReady!'],
		function($,ko,MsgBox,AjaxUtil){
			 $('#leftListswitch').click(function(){
		        $('#sys_menu_tree').toggle();
		        if($("#sys_menu_tree").is(":hidden")){
		            $(".main-box .main-center").css('margin-left','10px');
		            $("#leftListswitch").css('margin-left','0px');
		            $('#leftListswitch i').removeClass('glyphicon-chevron-left');
		            $('#leftListswitch i').addClass('glyphicon-chevron-right');
		        }else{
		            $(".main-box .main-center").css('margin-left','230px');
		            $("#leftListswitch").css('margin-left','220px');
		            $('#leftListswitch i').removeClass('glyphicon-chevron-right');
		            $('#leftListswitch i').addClass('glyphicon-chevron-left');		           
		        }
		    })
		    
		    $("#leftListswitch").css({"margin-left":"220px", "display":"inline"});
		    
			var zTreeObj;
			function zTreeOnClick(treeNode){
				if (treeNode.linkUrl!=null){
					AjaxUtil.call({
						url:treeNode.linkUrl,
						type:"GET",
						dataType:"html",
						success:function(responseText) {
							ko.utils.domData.clear(window.document.body);
							$("#stage").html(responseText)
						}
					});
				}
			}

			var menuTree=new ko.zTreeView('treeDemo',{
				url:'sys/menu/menuVO',
				pIdKey:'fdParentId',
				async:'false',
				dblClickExpand:false,
				showLine:false,
				callback:{
					treeNodeChanged:zTreeOnClick
				}
			})
			
			// main-center高度
			var mainHeight=$(window).height();
			var mainCenter=$('.main-center')

			mainCenter.css('height',mainHeight-40);

			var navLeft=$('.nav-left');
			var mainCenterHeight=$('.main-center').innerHeight()

			navLeft.css('height',mainCenterHeight);
			
			// 日期时间转换绑定
			ko.bindingHandlers.dateConvert = {
				init: function () {
					return {
						controlsDescendantBindings: true
					}
				},
				update: function (element, value) {
					var dateStr = ko.utils.unwrapObservable(value() || {});
					if(!dateStr){
						ko.utils.setTextContent(element,dateStr);
					}
					var date = new Date(Date.parse(dateStr));
					if(date&&date!="Invalid Date"){
						var month = parseInt(date.getMonth())+1;
						var day = parseInt(date.getDate());
						ko.utils.setTextContent(element, date.getFullYear() + "-" + (month<10?'0'+month:month) + "-" + (day<10?'0'+day:day))
					}else{
						ko.utils.setTextContent(element,dateStr);
					}
				}
			};
	});
})
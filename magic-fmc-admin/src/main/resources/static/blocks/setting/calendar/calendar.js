/**
 * 
 */
define([ 'knockout','AjaxUtil'], function(ko,AjaxUtil) {
	function calendar(params) {
		
		var self = this;
		
		self.daysPerMonList = ko.observableArray([]);
		self.firstDateList = ko.observableArray([]);
		self.calendarList = ko.observableArray([]);
		
		// 日历查询/更新form
		self.calendarForm = function(calId,year,startWeek,endWeek){
			return{
				calendarId:calId||'',
				year:year||'',
				startWeek:startWeek||'',
				endWeek:endWeek||''
			}
		}
		self.selectYear = ko.observable('');
		self.selectYear.subscribe(function(val){
			self.calendarForm.year=val;
		})
		
		// 日历数据初始化
		self.init = params.data.subscribe(function(val){
			self.calendarForm = val;
			self.refreshCalendar(self.calendarForm);
		});
		
		// 刷新日历
		self.refreshCalendar = function(calendarForm){
			if(!calendarForm)
				calendarForm = self.calendarForm;
			AjaxUtil.call({
				url:'wfMonitor/calendar/getCalendar',
				data:ko.toJS(calendarForm),
				success:function(data){
					self.firstDateList(data.firstDateList);
					self.daysPerMonList(data.daysPerMonList);
					self.calendarList(data.calendarList);
					params.calendarList(data.calendarList);
					self.selectYear(self.calendarList()[0][0].fdYear);
				}
			})
		}
		
		// 更改年份
		self.changeYear = function(val){
			var currYear = self.selectYear();
			if(val=='next')
				currYear = parseInt(currYear)+1;
			if(val=='last')
				currYear = parseInt(currYear)-1;
			self.calendarForm.year=currYear;;
			self.refreshCalendar();
		}
		
		$("#calendarTable").bind("contextmenu", function(){
		    return false;
		})
		
		
		// 当前季度最大星期数
		self.maxWeeksInCurrSeason = 6;
		// 日历计算
		self.countWeek = function(index){
			// 每季第一月 计算当前季度最大星期数
			if((index+1)%3==1){
				var maxWeeksCount = parseInt(0);
				for(j=0;j<3;j++){
					var daysCount = self.daysPerMonList()[index+j]+self.firstDateList()[index+j];
					var i = 0;
					if(daysCount%7>0)
						i=1;
					var weekCount = parseInt(daysCount/7)+i;
					if(weekCount>maxWeeksCount){
						maxWeeksCount = weekCount;
					}
				}
				self.maxWeeksInCurrSeason = maxWeeksCount;
				
			}
			var array = new Array(self.maxWeeksInCurrSeason);
//			console.info(array.length)
			return array;
		}
		
		self.rightClickDateId = '';
		// 获取当前日（几号）,并注册右键菜单事件
		self.getCurrentDay = function(weekIndex,monthIndex,dateIndex,element){
			var currentDate = (weekIndex*7+dateIndex)-self.firstDateList()[monthIndex];
			var dayData = self.calendarList()[monthIndex][currentDate];
			
			$(element.parentElement).mousedown(function(e) {
			    //右键为3
			    if (3 == e.which) {
			    	//设置右键菜单的显示位置
			    	var offsettop=0;   
			    	var offsetleft=0;  
			    	var parentModalElement = $(this).parents('.modal-dialog:first');
			    	var bodyElement = $(this).parents('.modal-body:first');
			    	if(parentModalElement&&parentModalElement[0]){
			    		offsettop=parentModalElement[0].offsetTop+bodyElement[0].offsetTop;
			    		offsetleft=parentModalElement[0].offsetLeft+bodyElement[0].offsetLeft;  
			    	};
					$("#menu").css({
						position: "absolute",
						'top':e.clientY-offsettop+"px",
						'left':e.clientX-offsetleft+"px"
						});   
					$('#menu').show();
					//设置右键点击的日期id
					if($(this)[0].children[0])
						self.rightClickDateId=$(this)[0].children[0].id;
			    }
			})
			return dayData;
		}
		
		//更改当前日类型
		self.changeDay = function(val){
			var mdIndex = self.rightClickDateId.split('_')
			
			var currentDay = self.calendarList()[mdIndex[0]-1][mdIndex[1]-1]
			currentDay.fdType=val;
			console.info(currentDay);
			self.calendarList()[mdIndex[0]-1][mdIndex[1]-1]=currentDay;
			params.calendarList()[mdIndex[0]-1][mdIndex[1]-1]=currentDay;
			$("#"+self.rightClickDateId).css({'color' : val=='1'?'black':val=='2'?'red':'green'})
			$('#menu').hide();
		}
		
		// 检查当前位置是否有日期
		self.checkHasDay = function(weekIndex,monthIndex,dateIndex){
			//当前为几号
			var currentDate = (weekIndex*7+dateIndex)-self.firstDateList()[monthIndex];
			if(currentDate>=0&&currentDate<=self.daysPerMonList()[monthIndex]){
				return true;
			}
			return false;
		}
		
		
		// 日期样式控制
		self.dayColor = function(day){
			if(!day.fdType)
				return '';
			if(day.fdType=='2')
				return 'red';
			if(day.fdType=='3')
				return 'green';
		}
		
		
		
	};
	return calendar;
});
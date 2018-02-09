package com.vispractice.fmc.web.controller.setting;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.base.domain.WfMonitorCalendarForm;
import com.vispractice.fmc.business.entity.sys.config.WfMonitorCalendarMain;
import com.vispractice.fmc.business.entity.sys.config.view.WfMonitorCalendarV;
import com.vispractice.fmc.business.service.sys.config.IWfMonitorCalendarMainService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/wfMonitor/calendar")
public class WfMonitorCalendarController extends AbstractController{

	@Autowired
	private IWfMonitorCalendarMainService wfMonitorCalendarMainService;
	
	@RequestMapping("/index")
	public String index(){
		return "earlyWarning/wfMonitorCalendar/wf_monitor_calendar_index";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<WfMonitorCalendarV> findAll(@RequestParam String context,@RequestParam String pageVO) {

		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			WfMonitorCalendarV wfMonitorCalendarV = mapper.readValue(context,WfMonitorCalendarV.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			return wfMonitorCalendarMainService.findAll(wfMonitorCalendarV,pageVo.getPageable());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 新增或修改
	 */
	@RequestMapping(value = "/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult save(@RequestParam String context) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			WfMonitorCalendarForm wfMonitorCalendarForm = mapper.readValue(context, WfMonitorCalendarForm.class);
			WfMonitorCalendarMain calendarMain = wfMonitorCalendarForm.getWfMonitorCalendarMain();
			if (StringUtils.isEmpty(calendarMain.getFdId())) {
				calendarMain.setFdCreatedBy(this.getCurrentUser().getFdId());
				calendarMain.setFdCreatedDate(new Date());
			}
			wfMonitorCalendarMainService.saveCalendarAndDays(wfMonitorCalendarForm,this.getCurrentUser().getFdId());
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure")+e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value="/getEnabled")
	@ResponseBody
	public WebMessageResult getCalendarAll(String fdId){
		WebMessageResult result = new WebMessageResult();
		try {
			List<WfMonitorCalendarMain> list = wfMonitorCalendarMainService.getEnabled(fdId);
			result.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("获取已启用日历发生错误");
		}
		return result;
	}
	
	@RequestMapping(value="/getCalendar")
	@ResponseBody
	public WebMessageResult getCalendar(String calendarId,String year,String startWeek,String endWeek){
		WebMessageResult result = new WebMessageResult();
		try {
			WfMonitorCalendarForm form = wfMonitorCalendarMainService.getCalendarView(calendarId, year, startWeek, endWeek);
			result.setData(form);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("获取日历发生错误");
		}
		return result;
	}
	
	
	/**
	 * 删除时间设置
	 */
	@RequestMapping(value = "/deleteOne", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult delete(@RequestParam String fdId) {
		WebMessageResult result = new WebMessageResult();
		try {
			wfMonitorCalendarMainService.deleteByFdId(fdId);
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		
		return result;
	}
	
}

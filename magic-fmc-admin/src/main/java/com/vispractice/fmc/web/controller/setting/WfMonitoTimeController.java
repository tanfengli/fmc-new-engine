package com.vispractice.fmc.web.controller.setting;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.business.entity.sys.config.WfMonitorTimeWork;
import com.vispractice.fmc.business.entity.sys.config.view.WfMonitorTimeWorkV;
import com.vispractice.fmc.business.service.sys.config.IWfMonitorTimeWorkService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/wfMonitor/time")
public class WfMonitoTimeController extends AbstractController{

	
	@Autowired
	private IWfMonitorTimeWorkService WfMonitorTimeWorkService;
	
	@RequestMapping("/index")
	public String index(){
		return "earlyWarning/wfMonitorTime/wf_monitor_time_index";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<WfMonitorTimeWorkV> findAll(@RequestParam String context,@RequestParam String pageVO) {

		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			WfMonitorTimeWorkV wfMonitorTimeV = mapper.readValue(context,WfMonitorTimeWorkV.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			return WfMonitorTimeWorkService.findAll(wfMonitorTimeV,pageVo.getPageable());
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
			WfMonitorTimeWork wfMonitorTimeWork = mapper.readValue(context, WfMonitorTimeWork.class);
			if (StringUtils.isEmpty(wfMonitorTimeWork.getFdId())) {
				wfMonitorTimeWork.setFdCreatedBy(this.getCurrentUser().getFdId());
				wfMonitorTimeWork.setFdCreatedDate(new Date());
			}
			if (StringUtils.isEmpty(wfMonitorTimeWork.getFdEnabled())) {
				wfMonitorTimeWork.setFdEnabled(CommonConstant.AVAILABLE_FLAG);
			}
			WfMonitorTimeWorkService.save(wfMonitorTimeWork);
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure")+e.getMessage());
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
			WfMonitorTimeWorkService.deleteOne(fdId);
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		
		return result;
	}
	
	
	
}

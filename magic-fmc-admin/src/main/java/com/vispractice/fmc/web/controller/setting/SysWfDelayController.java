package com.vispractice.fmc.web.controller.setting;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.vispractice.fmc.business.entity.sys.config.SysParam;
import com.vispractice.fmc.business.entity.sys.config.SysWfDelayFlow;
import com.vispractice.fmc.business.entity.sys.config.view.SysWfDelayV;
import com.vispractice.fmc.business.service.sys.config.ISysParamService;
import com.vispractice.fmc.business.service.sys.config.ISysWfDelayFlowService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/sys/wfDelay")
public class SysWfDelayController extends AbstractController{

	@Autowired
	private ISysWfDelayFlowService sysWfDelayFlowService;
	
	@Autowired
	private ISysParamService sysParamService;
	
	@RequestMapping("/index")
	public String index(){
		return "earlyWarning/sysWfDelay/sys_wf_delay_index";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysWfDelayV> findAll(@RequestParam String context,@RequestParam String pageVO) {

		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysWfDelayV sysWfDelayV = mapper.readValue(context,SysWfDelayV.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			return sysWfDelayFlowService.findAll(sysWfDelayV,pageVo.getPageable());
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
			SysWfDelayFlow sysWfDelayFlow = mapper.readValue(context, SysWfDelayFlow.class);
			if (StringUtils.isEmpty(sysWfDelayFlow.getFdId())) {
				sysWfDelayFlow.setFdCreatedBy(this.getCurrentUser().getFdId());
				sysWfDelayFlow.setFdCreatedDate(new Date());
			}
			if (StringUtils.isEmpty(sysWfDelayFlow.getFdEnabled())) {
				sysWfDelayFlow.setFdEnabled(CommonConstant.AVAILABLE_FLAG);
			}
			sysWfDelayFlowService.save(sysWfDelayFlow);
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure")+e.getMessage());
		}
		
		return result;
	}
	
	
	/**
	 * 新增或修改
	 */
	@RequestMapping(value = "/deleteOne", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult delete(@RequestParam String fdId) {
		WebMessageResult result = new WebMessageResult();
		try {
			sysWfDelayFlowService.deleteOne(fdId);
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		
		return result;
	}
	
	/**
	 * 获取默认处理时长
	 */
	@RequestMapping(value = "/getDefaultDelayTime")
	@ResponseBody
	public WebMessageResult getDefaultDelayTime() {
		WebMessageResult result = new WebMessageResult();
		try {
			Map<String, String> map = new HashMap<String, String>();
			List<String> fdCodeList = new ArrayList<String>();
			String nodeCode = "node_delay";
			String processCode = "process_delay";
			fdCodeList.add(nodeCode);
			fdCodeList.add(processCode);
			List<SysParam> paramList = sysParamService.findByFdCodeIn(fdCodeList);
			for (SysParam sysParam : paramList) {
				if (nodeCode.equals(sysParam.getFdCode())) {
					map.put(nodeCode, sysParam.getFdValue());
				}
				if (processCode.equals(sysParam.getFdCode())) {
					map.put(processCode, sysParam.getFdValue());
				}
			}
			result.setData(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 新增或修改
	 */
	@RequestMapping(value = "/saveDefaultDelayTime", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult saveDefaultDelayTime(String node_delay,String process_delay) {
		WebMessageResult result = new WebMessageResult();
		try {
//			String nodeCode = "node_delay";
//			String processCode = "process_delay";
//			if (map.containsKey(processCode)) {
				sysParamService.updateFdValueByFdCode("node_delay", node_delay);
//			}
//			if (map.containsKey(processCode)) {
				sysParamService.updateFdValueByFdCode("process_delay", process_delay);
//			}
			
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure")+e.getMessage());
		}
		
		return result;
	}
	
	
}

package com.vispractice.fmc.web.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfExceptionLogV;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfExceptionLogService;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.PageVO;

@Controller
@RequestMapping("/sys/wfExceptionLog")
public class SysWfExceptionLogController {
	
	@Autowired
	private ISysWfExceptionLogService sysWfExceptionLogService;
	
	@RequestMapping("index")
	public String wfExceptionIndex(Model model){
		return "sys/wfExceptionLog/sysWfExceptionLog_list";
	}
	
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysWfExceptionLogV> findAll(@RequestParam String context, @RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysWfExceptionLogV exceptionLogV = mapper.readValue(context, SysWfExceptionLogV.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			return sysWfExceptionLogService.findBySearch(exceptionLogV, pageVo.getPageable());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

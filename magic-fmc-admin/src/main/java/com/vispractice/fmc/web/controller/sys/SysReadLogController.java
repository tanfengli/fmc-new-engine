package com.vispractice.fmc.web.controller.sys;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.entity.sys.read.SysReadLogV;
import com.vispractice.fmc.business.service.sys.read.ISysReadLogService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

/**
 * 
 * 编  号：
 * 名  称：SysReadLogController
 * 描  述：阅读日志控制器
 * 完成日期：2017年10月19日 上午10:54:49
 * 编码作者："LaiJiashen"
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/sys/readLog")
public class SysReadLogController extends AbstractController{

	@Autowired
	private ISysReadLogService sysReadLogService;

	@RequestMapping(value = "/insertOne")
	@ResponseBody
	public WebMessageResult add(@RequestParam String billId) {
		WebMessageResult result = new WebMessageResult();
		String readerId = this.getCurrentUser().getFdId();
		try {
			sysReadLogService.save(billId, readerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult findAll(@RequestParam String context,@RequestParam String pageVO) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysReadLogV sysReadLogV = mapper.readValue(context,SysReadLogV.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			Page<SysReadLogV> page = sysReadLogService.getReaderList(sysReadLogV.getFdModelId(), pageVo.getPageable());
			result.setData(page);
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getActionInfo("webController.dataGridSearchFailure"));
			e.printStackTrace();
		}
		return result;
	}

}

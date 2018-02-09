package com.vispractice.fmc.web.controller.setting;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.base.utils.ResourceUtil;
import com.vispractice.fmc.business.entity.sys.config.CompDbcp;
import com.vispractice.fmc.business.service.sys.config.ICompDbcpService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.PageVO;

@Slf4j
@Controller
@RequestMapping("/setting/compDbcp")
public class CompDbcpController {

	@Autowired
	private ICompDbcpService compDbcpService;
	
	@Autowired
	private ResourceUtil resourceUtil;

	/**
	 * 数据源页面入口
	 * */
	@RequestMapping("/index")
	public String dbcpIndex(Model model) {
		
		return "settings/dbsource/comp_dbcp_index";
	}


	// 查询
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult findAll(@RequestParam String context,
			@RequestParam String pageVO) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			CompDbcp compDbcp = mapper.readValue(context, CompDbcp.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			Page<CompDbcp> page = compDbcpService.findBySearch(compDbcp,pageVo.getPageable());
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setErrorMessage(e.getMessage());
		}
		return result;
	}

	// 修改，插入
	@RequestMapping(value = "/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult saveOrUpdate(CompDbcp compDbcp) {
		WebMessageResult result = new WebMessageResult();
		try {
			compDbcpService.save(compDbcp);
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		return result;
	}
	
	// 删除
	@RequestMapping(value = "/deleteOne", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult deleteOne(CompDbcp compDbcp) {

		WebMessageResult result = new WebMessageResult();
		try {
			compDbcpService.delById(compDbcp.getFdId());
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		return result;
	}
	
	/**
	 * 测试连接
	 * @Title: testConnection 
	 * @param compDbcp
	 * @return
	 */
	@RequestMapping(value = "/testConnection", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult testConnection(CompDbcp compDbcp){
		WebMessageResult result = new WebMessageResult();
		try {
			if (compDbcpService.testConnection(compDbcp)) {
				result.setMessage(resourceUtil.getLocaleMessage("db.test.success"));
			}else {
				result.setErrorMessage(resourceUtil.getLocaleMessage("db.test.failure"));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("db.test.failure")+e.getMessage());
		}
		return result;
	}

	/**
	 * 多条删除
	 * */
	@RequestMapping(value = "/delDbcp", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public Map<String, String> delDbcp(String fdIdList) {
		Map<String, String> map = new HashMap<String, String>();

		String fdIdArr[] = fdIdList.split(" ");

		try {
			if (fdIdArr.length > 0) {
				for (int i = 0; i < fdIdArr.length; i++) {
					compDbcpService.delById(fdIdArr[i]);
				}
			}
			map.put("info", "success");

		} catch (Exception e) {
			map.put("info", "error");
			map.put("errorCode", "0");
		}

		return map;
	}



}

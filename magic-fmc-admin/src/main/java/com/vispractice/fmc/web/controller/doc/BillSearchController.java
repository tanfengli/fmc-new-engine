package com.vispractice.fmc.web.controller.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.entity.sys.news.view.DocumentSubmmitedV;
import com.vispractice.fmc.business.service.sys.news.ISysNewsMainService;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.PageVO;

@Controller
@RequestMapping("/docManage/billSearch")
public class BillSearchController {
	@Autowired
	private ISysNewsMainService sysNewsMainService;

	@RequestMapping("/index")
	public String indexProcess() {
		return "docManage/billSearch/sys_news_main";
	}

	/**
	 * 查询流程单据
	 */
	@RequestMapping(value = "/findAll",method = RequestMethod.POST)
	@ResponseBody
	public Page<DocumentSubmmitedV> findAll(@RequestParam String context,@RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			DocumentSubmmitedV documentSubmmitedV = mapper.readValue(context,DocumentSubmmitedV.class);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			Page<DocumentSubmmitedV> page = sysNewsMainService.searchDocumentSubmmitedV(documentSubmmitedV,pageVo.getPageable());
			
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
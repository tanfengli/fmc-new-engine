package com.vispractice.fmc.web.controller.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.base.constrant.SysDocConstant;
import com.vispractice.fmc.business.entity.sys.news.view.DocumentSubmmitedV;
import com.vispractice.fmc.business.service.sys.news.ISysNewsMainService;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

@Controller
@RequestMapping("/docManage/billDraft")
public class BillDraftController<T> extends AbstractController<T> {
	/**
	 * 流程业务信息服务
	 */
	@Autowired
	private ISysNewsMainService sysNewsMainService;
	
	@RequestMapping("/index")
	public String index() {
		return "docManage/billDraft/sys_news_main";
	}
	
	/**
	 * 查询流程草稿单据
	 */
	@RequestMapping(value = "/findAll",method = RequestMethod.POST)
	@ResponseBody
	public Page<DocumentSubmmitedV> findAll(@RequestParam String context,@RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			DocumentSubmmitedV documentSubmmitedV = mapper.readValue(context,DocumentSubmmitedV.class);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			documentSubmmitedV.setCreatorUserNo(this.getCurrentUser().getFdLoginName());
			documentSubmmitedV.setDocStatus(SysDocConstant.DOC_STATUS_DRAFT + "," 
						+ SysDocConstant.DOC_STATUS_DISCARD + ","
						+ SysDocConstant.DOC_STATUS_REFUSE + "," 
						+ SysDocConstant.DOC_STATUS_EXAMINE + ","
						+ SysDocConstant.DOC_STATUS_PUBLISH);
			Page<DocumentSubmmitedV> page = sysNewsMainService.searchDocumentSubmmitedV(documentSubmmitedV,pageVo.getPageable());
			
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

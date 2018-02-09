package com.vispractice.fmc.web.controller.templateManage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfCommonTemplate;
import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfCommonTemplateV;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfCommonTemplateService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

/**
 * 通用模板管理 编 号：<br/>
 * 名 称：SysWfCommonTemplateController<br/>
 * 描 述：<br/>
 * 完成日期：2017年1月9日 下午3:45:16<br/>
 * 编码作者：Administrator<br/>
 */
@Controller
@RequestMapping("/sys/commonTemplate")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysWfCommonTemplateController extends AbstractController<SysWfCommonTemplate> {
	@Autowired
	private ISysWfCommonTemplateService commonTemplateService;

	@Autowired
	private ISysOrgElementService elementService;

	/**
	 * 数据源页面入口
	 */
	@RequestMapping("/list")
	public String goIndex(Model model) {
		return "templateManage/commonTemplate/commonTemplate_List";
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysWfCommonTemplateV> findAll(@RequestParam String context, @RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysWfCommonTemplateV commonTemplate = mapper.readValue(context, SysWfCommonTemplateV.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			return commonTemplateService.findBySearch(commonTemplate, pageVo.getPageable());
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
	public WebMessageResult addSysWfUsage(String context) {
		WebMessageResult result = new WebMessageResult();
		try {
			ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
			SysWfCommonTemplate commonTemplate = mapper.readValue(context, SysWfCommonTemplate.class);

			SysWfCommonTemplateV ctv = mapper.readValue(context, SysWfCommonTemplateV.class);

			// 验证：默认的通用流程模板有且只能有一个
			if (null != ctv && ctv.getFdIsDefault().equals("1")) {
				String fdId = ctv.getFdId();
				if (null == fdId || "".equals(fdId)) {
					fdId = "0";
				}
				int count = commonTemplateService.findByFdIsDefault(ctv.getFdIsDefault(), fdId);
				if (count >= 1) {
					result.setErrorMessage(resourceUtil.getLocaleMessage("onlyOneComTemp"));
					return result;
				}
			}
			
			if (null==commonTemplate.getFdId()) {
				commonTemplate.setFdCreateTime(new Date(System.currentTimeMillis()));
				commonTemplate.setFdCreatorId(this.getCurrentUser().getFdId());
			}

			commonTemplateService.save(commonTemplate);
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		return result;
	}


	@RequestMapping("/deleteOne")
	@ResponseBody
	public WebMessageResult deleteOne(String fdId){
		WebMessageResult result = new WebMessageResult();
		try {
			commonTemplateService.deleteByFdId(fdId);
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		}
		return result;
	}

}

package com.vispractice.fmc.web.controller.sys;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfUsage;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.services.sys.ISysWfUsageService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

/**
 * @ClassName DefLanguageManController
 * @Description TODO(默认审批语管理)
 * @author 
 * @Date 2016年12月14日 上午10:39:41
 * @version 1.0.0
 */
@Controller
@RequestMapping("/sys/defLanguage")
public class DefLanguageManController extends AbstractController<SysWfUsage> {
	@Autowired
	private ISysWfUsageService iSysWfUsageService;

	@Autowired
	private ISysOrgElementService elementService;

	/**
	 * 数据源页面入口
	 */
	@RequestMapping("/list")
	public String defLanguageIndex(Model model) {
		return "sys/defLanguageMangement/def_language_list";
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysWfUsage> findAll(@RequestParam String context, @RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysWfUsage sysWfUsage = mapper.readValue(context, SysWfUsage.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			return iSysWfUsageService.findBySearch(sysWfUsage, pageVo.getPageable());
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
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysWfUsage usage = mapper.readValue(context,SysWfUsage.class);

			//验证审批类型是否默认'是'唯一性
			if (null != usage) {
				if (null != usage.getFdUsageType() && null != usage.getFdIsSysSetup()
						&& "1".equals(usage.getFdIsSysSetup())) {
					String fdId = usage.getFdId();
					if (null == fdId || "".equals(fdId)) {
						fdId = "0";
					}

					int swu = iSysWfUsageService.findByFdUsageTypeAndFdIsSysSetup(usage.getFdUsageType(),
							usage.getFdIsSysSetup(), fdId);

					if (swu >= 1) {
						result.setErrorMessage(resourceUtil.getLocaleMessage("defLanguageExisted"));
						return result;
					}
				}
			}

			if (usage.getFdId() == null) {
				SysOrgElement element = elementService.findByFdNo(this.getCurrentUser().getFdLoginName());
				usage.setFdCreatorId(element.getFdId());
				Date utilDate = new Date();
				usage.setFdCreateTime(new java.util.Date(utilDate.getTime()));
			}
			iSysWfUsageService.save(usage);
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		
		return result;
	}

	@RequestMapping("/deleteOne")
	@ResponseBody
	public WebMessageResult deleteOne(String fdId) throws Exception {
		WebMessageResult result = new WebMessageResult();
		try {
			iSysWfUsageService.deleteById(fdId);
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		
		return result;
	}

	@RequestMapping(value = "/noPageFind",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,List<SysWfUsage>> noPageFind() {
		return iSysWfUsageService.noPageFind();
	}
}

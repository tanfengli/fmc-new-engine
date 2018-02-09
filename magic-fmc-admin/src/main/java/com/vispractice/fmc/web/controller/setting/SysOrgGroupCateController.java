package com.vispractice.fmc.web.controller.setting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.entity.sys.org.SysOrgGroupCate;
import com.vispractice.fmc.business.service.sys.org.ISysOrgGroupService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

/**
 * 编 号：<br/>
 * 名 称：SysOrgGroupCateController<br/>
 * 描 述：<br/>
 * 完成日期：2016年12月14日 下午3:32:19<br/>
 * 编码作者：LaiJiashen<br/>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/setting/groupCate")
public class SysOrgGroupCateController extends AbstractController{

	@Autowired
	private ISysOrgGroupService sysOrgGroupCateService;

	@RequestMapping("/index")
	public String categoryIndex() {
		return "settings/groupcate/groupcate_index";
	}

	/**
	 * 
	 * 获取根节点<br/>
	 */
	@RequestMapping("/findRootElements")
	@ResponseBody
	public List<SysOrgGroupCate> getRootElements() {
		return sysOrgGroupCateService.findRootElements();
	}

	/**
	 * 找子节点
	 */
	@RequestMapping("/findSubElements")
	@ResponseBody
	public List<SysOrgGroupCate> getSubElements(String fdId) {
		return sysOrgGroupCateService.findSubElements(fdId);
	}

	/**
	 * 查询
	 */
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysOrgGroupCate> findAll(@RequestParam String context, @RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysOrgGroupCate SysOrgGroupCate = mapper.readValue(context, SysOrgGroupCate.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			return sysOrgGroupCateService.findBySearch(SysOrgGroupCate, pageVo.getPageable());
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
	public WebMessageResult addSysWfUsage(@RequestParam String context) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysOrgGroupCate sysOrgGroupCate = mapper.readValue(context, SysOrgGroupCate.class);
			SysOrgGroupCate newNode = new SysOrgGroupCate();
			newNode = sysOrgGroupCateService.save(sysOrgGroupCate);
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
			result.setData(newNode);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure")+e.getMessage());
		}
		
		return result;
	}

	@RequestMapping("/deleteNode")
	@ResponseBody
	public WebMessageResult deleteNode(SysOrgGroupCate sysOrgGroupCate) {
		WebMessageResult result = new WebMessageResult();
		try {
			if (sysOrgGroupCateService.delete(sysOrgGroupCate)) {
				result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
			} else {
				result.setErrorMessage(resourceUtil.getLocaleMessage("groupCateCannotDel"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		
		return result;
	}
}

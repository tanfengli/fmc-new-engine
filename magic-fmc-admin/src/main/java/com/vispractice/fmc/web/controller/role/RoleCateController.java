package com.vispractice.fmc.web.controller.role;


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
import com.vispractice.fmc.business.entities.setting.view.RoleCateV;
import com.vispractice.fmc.business.entities.sys.SysAuthCategory;
import com.vispractice.fmc.business.services.setting.IRoleCateVService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;



@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("roleManage/roleCate")
public class RoleCateController extends AbstractController{
	
	@Autowired
	private IRoleCateVService roleCateVService;
	
	
	@RequestMapping("/index")
	public String index(){
		return "roleManage/roleCate/role_cate_index";
	}
	
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<RoleCateV> findAll(@RequestParam String context,
			@RequestParam String pageVO) {

		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			RoleCateV roleCateV = mapper.readValue(context, RoleCateV.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			return roleCateVService.findBySearch(roleCateV,
					pageVo.getPageable());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 修改，插入
	@RequestMapping(value = "/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult saveOrUpdate(@RequestParam String context) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		String currUserId = this.getCurrentUser().getFdId();
		try {
			RoleCateV roleCateV = mapper.readValue(context, RoleCateV.class);
			SysAuthCategory roleCategory = null;
			if (StringUtils.isNotEmpty(roleCateV.getFdId())) {
				roleCategory = roleCateVService.findOne(roleCateV.getFdId());
			}else {
				roleCategory = new SysAuthCategory();
				roleCategory.setDocCreateId(currUserId);
				roleCategory.setDocCreateTime(new Date());
			}
			roleCategory.setFdName(roleCateV.getFdName());
			roleCateVService.save(roleCategory);
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		return result;
	}
	
	// 删除
	@RequestMapping(value = "/deleteOne", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult deleteOne(RoleCateV roleCateV) {

		WebMessageResult result = new WebMessageResult();
		try {
			roleCateVService.deleteByFdId(roleCateV);
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		return result;
	}
	
	

}

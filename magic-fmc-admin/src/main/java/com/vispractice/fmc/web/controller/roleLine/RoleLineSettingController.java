package com.vispractice.fmc.web.controller.roleLine;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.entities.setting.RoleSetV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleConf;
import com.vispractice.fmc.business.service.sys.org.ISysOrgRoleConfService;
import com.vispractice.fmc.business.services.setting.IRoleSetVService;
import com.vispractice.fmc.business.services.setting.impl.ParamTranService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("roleLineManage/roleLineSetting")
public class RoleLineSettingController extends AbstractController{

	@Autowired
	private ISysOrgRoleConfService sysOrgRoleConfService;

	@Autowired
	private IRoleSetVService roleSetVService;

	@Autowired
	private ParamTranService paramTranService;

	@RequestMapping("/index")
	public String index() {

		return "roleLineManage/roleLineSetting/role_line_setting_index";
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysOrgRoleConf> findAll(@RequestParam String context, @RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysOrgRoleConf roleConf = mapper.readValue(context, SysOrgRoleConf.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			Page<SysOrgRoleConf> list = sysOrgRoleConfService.findBySearch(roleConf, pageVo.getPageable());
			return list;
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
			SysOrgRoleConf roleConf = mapper.readValue(context, SysOrgRoleConf.class);
			if (sysOrgRoleConfService.save(roleConf))
				result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
			else {
				result.setErrorMessage(resourceUtil.getLocaleMessage("roleLineNameNotNull"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		return result;
	}

	@RequestMapping(value = "/findForm", method = RequestMethod.POST)
	@ResponseBody
	public SysOrgRoleConf findForm(String context) {

		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysOrgRoleConf roleConf = mapper.readValue(context, SysOrgRoleConf.class);
			roleConf = sysOrgRoleConfService.findForm(roleConf.getFdId());
			return roleConf;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	@RequestMapping(value = "/findOne", method = RequestMethod.POST)
	@ResponseBody
	public SysOrgRoleConf findOne(String fdId) {

		try {
			SysOrgRoleConf roleConf = sysOrgRoleConfService.getOne(fdId);
			return roleConf;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/copyOne", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult copyOne(String fdId) {

		WebMessageResult result = new WebMessageResult();
		try {
			sysOrgRoleConfService.updateCopy(fdId);
			result.setMessage(resourceUtil.getLocaleMessage("webController.copySuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.copyFailure")+e.getMessage());
		}
		return result;
	}

	/**
	 * 角色关系设置详情
	 */
	@RequestMapping(value = "/roleSet/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<RoleSetV> roleFindAll(@RequestParam String context, @RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			RoleSetV roleSet = mapper.readValue(context, RoleSetV.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			Page<RoleSetV> list = roleSetVService.findByExample(roleSet, pageVo.getPageable());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 新增或修改
	 */
	@RequestMapping(value = "/roleSet/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult addRoleSet(String context) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			RoleSetV roleSet = mapper.readValue(context, RoleSetV.class);

			if (roleSetVService.save(roleSet)) {
				result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
			} else {
				result.setErrorMessage(resourceUtil.getLocaleMessage("roleLineNameRepeated"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		return result;
	}
	
	/**
	 * 删除角色关系
	 * @param roleAssignV
	 * @return
	 */
	@RequestMapping(value = "/roleSet/deleteOne",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult deleteOne(RoleSetV roleSetV) {
		WebMessageResult result = new WebMessageResult();
		try {
			roleSetVService.deleteById(roleSetV.getFdId());
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));

		}
		
		return result;
	}

	/**
	 * 参数转换
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/paramTran")
	@ResponseBody
	public Map<String, String> paramTran(String param) {

		return paramTranService.tran(param);

	}

	/**
	 * 
	 * 获取根节点<br/>
	 */
	@RequestMapping("/findRootElements")
	@ResponseBody
	public List<SysOrgRoleConf> getRootElements() {
		return sysOrgRoleConfService.findRootElements();
	}

	/**
	 * 找子节点
	 */
	@RequestMapping(value = "/findSubElements")
	@ResponseBody
	public List<SysOrgRoleConf> getSubElements(String parentId) {
		return sysOrgRoleConfService.findSubElements(parentId);
	}

	@RequestMapping(value = "/getRoleByConfId")
	@ResponseBody
	public List<SysOrgElement> getRoleByConfId(String fdId) {
		return sysOrgRoleConfService.getRoleByConfId(fdId);
	}

}

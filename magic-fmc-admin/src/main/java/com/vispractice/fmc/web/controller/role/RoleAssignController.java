package com.vispractice.fmc.web.controller.role;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.entities.setting.view.RoleAssignV;
import com.vispractice.fmc.business.entities.setting.view.RoleCateV;
import com.vispractice.fmc.business.entity.sys.menu.SysMenu;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.services.setting.IRoleAssignVService;
import com.vispractice.fmc.business.services.setting.IRoleCateVService;
import com.vispractice.fmc.business.services.sys.ISysAuthMraService;
import com.vispractice.fmc.business.services.sys.ISysAuthUraService;
import com.vispractice.fmc.web.domain.setting.SysRoleAssignForm;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

@Slf4j
@Controller
@RequestMapping("roleManage/roleAssign")
public class RoleAssignController extends AbstractController<RoleAssignV> {

	@Autowired
	private IRoleAssignVService roleAssignVService;

	@Autowired
	private IRoleCateVService roleCateVService;

	@Autowired
	private ISysOrgElementService elementService;
	
	@Autowired
	private ISysAuthUraService sysAuthUraService;
	
	@Autowired
	private ISysAuthMraService sysAuthMraService;
	
	@RequestMapping("/index")
	public String index() {

		return "roleManage/roleAssign/role_assign_index";
	}

	// 查询
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<RoleAssignV> findAll(@RequestParam String context, @RequestParam String pageVO) {

		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			RoleAssignV roleAssignV = mapper.readValue(context, RoleAssignV.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			return roleAssignVService.findBySearch(roleAssignV, pageVo.getPageable());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取编辑form
	 * @param roleAssignV
	 * @return
	 */
	@RequestMapping(value = "/findForm", method = RequestMethod.POST)
	@ResponseBody
	private SysRoleAssignForm findForm(RoleAssignV roleAssignV) {

		return roleAssignVService.findForm(roleAssignV);
	}

	/**
	 * 修改
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateForm",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult saveOrUpdate(@RequestParam String context) {

		SysOrgElement sysOrgElement = elementService.findByFdNo(this.getCurrentUser().getFdLoginName());
		String id = sysOrgElement.getFdId();
		WebMessageResult result = new WebMessageResult();
		try {
			ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
			SysRoleAssignForm sysRoleAssignForm = mapper.readValue(context, SysRoleAssignForm.class);
			roleAssignVService.save(sysRoleAssignForm, id);
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}

		return result;

	}

	/**
	 * 删除
	 * @param roleAssignV
	 * @return
	 */
	@RequestMapping(value = "/deleteOne",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult deleteOne(RoleAssignV roleAssignV) {
		WebMessageResult result = new WebMessageResult();
		try {
			roleAssignVService.deleteById(roleAssignV.getFdId());
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));

		}
		
		return result;
	}

	/**
	 * 获取角色分类
	 */
	@RequestMapping(value = "/findCategory",method = RequestMethod.POST)
	@ResponseBody
	public List<RoleCateV> findCategory() {
		return roleCateVService.getIdAndName();
	}
	
	
	/**
	 * 获取已分配用户
	 */
	@RequestMapping(value = "/getAssignedUser")
	@ResponseBody
	public WebMessageResult getAssignedUser(String fdId) {
		WebMessageResult result = new WebMessageResult();
		try {
			List<SysOrgElement> assignedUserList = sysAuthUraService.getAssignedUser(fdId);
			result.setData(assignedUserList);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("获取已分配角色失败");
		}
		return result;
	}
	
	/**
	 * 保存分配用户
	 */
	@RequestMapping(value = "/assignUser/saveOrUpdateForm",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult saveAssignedUser(String context) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysRoleAssignForm sysRoleAssignForm = mapper.readValue(context, SysRoleAssignForm.class);
			sysAuthUraService.saveAssignedUser(sysRoleAssignForm);
			result.setMessage(resourceUtil.getLocaleMessage("assignUserSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(resourceUtil.getLocaleMessage("assignUserFailure"));
			result.setErrorMessage(resourceUtil.getLocaleMessage("assignUserFailure"));
		}
		return result;
	}
	
	/**
	 * 获取已授权菜单
	 */
	@RequestMapping(value = "/getAuthorizedMenu")
	@ResponseBody
	public WebMessageResult getAuthorizedMenu(String fdId) {
		WebMessageResult result = new WebMessageResult();
		try {
			List<SysMenu> menuAuthList = sysAuthMraService.getMenu(fdId);
			result.setData(menuAuthList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取已授权菜单失败。");
			result.setErrorMessage("获取已授权菜单失败。");
		}
		return result;
	}
	
	/**
	 * 保存授权菜单
	 */
	@RequestMapping(value = "/authorizeMenu/saveOrUpdateForm",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult saveMenuAuth(String context) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysRoleAssignForm sysRoleAssignForm = mapper.readValue(context, SysRoleAssignForm.class);
			sysAuthMraService.saveMenuAuth(sysRoleAssignForm);
			result.setMessage(resourceUtil.getLocaleMessage("authorizeMenuSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(resourceUtil.getLocaleMessage("authorizeMenuFailure"));
			result.setErrorMessage(resourceUtil.getLocaleMessage("authorizeMenuFailure"));
		}
		return result;
	}
	
	/**
	 * 获取所有权限角色
	 * @Title: findAllNoPage 
	 * @return
	 */
	@RequestMapping(value = "/findAllNoPage")
	@ResponseBody
	public WebMessageResult findAllNoPage(){
		WebMessageResult result = new WebMessageResult();
		try {
			result.setData(roleAssignVService.findAll());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取所有权限角色发生错误.");
			result.setErrorMessage("获取所有权限角色发生错误.");
		}
		return result;
	}
	
}

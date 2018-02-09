package com.vispractice.fmc.web.controller.roleLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.base.utils.ResourceUtil;
import com.vispractice.fmc.business.entities.setting.view.RepeatPostOnRoleconfV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleConf;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleLine;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleLineDefaultRole;
import com.vispractice.fmc.business.entity.sys.org.view.RoleMemberV;
import com.vispractice.fmc.business.service.sys.org.ISysOrgRoleConfService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgRoleLineDefaultRoleService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgRoleLineService;
import com.vispractice.fmc.business.services.setting.impl.ParamTranService;
import com.vispractice.fmc.business.services.sys.IRoleLineMemberVService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.PageVO;
/**
 * 
 * 编  号：
 * 名  称：RoleLineMemberController
 * 描  述：RoleLineMemberController.java
 * 完成日期：2017年8月8日 上午11:01:46
 * 编码作者："LaiJiashen"
 */
@Controller
@RequestMapping("roleLineManage/roleLineMember")
@Slf4j
public class RoleLineMemberController {

	@Autowired
	private ISysOrgRoleConfService sysOrgRoleConfService;

	@Autowired
	private ISysOrgRoleLineService sysOrgRoleLineService;

	@Autowired
	private IRoleLineMemberVService roleLineMemberVService;

	@Autowired
	private ParamTranService sysOrgRolePluginService;
	
	/**
	 * 国际化工具
	 */
	@Autowired
	private ISysOrgRoleLineDefaultRoleService sysOrgRoleLineDefaultRoleService;
	
	@Autowired
	private ResourceUtil resourceUtil;

	@RequestMapping("/index")
	public String index() {

		return "roleLineManage/roleLineMember/roleLineMember";
	}

	@RequestMapping("/findRoleLine")
	@ResponseBody
	public List<SysOrgRoleConf> findRoleLine() {
		return sysOrgRoleConfService.findIsAvailable();

	}

	/**
	 * 
	 * 获取根节点<br/>
	 */
	@RequestMapping("/findRootElements")
	@ResponseBody
	public List<RoleMemberV> getRootElements(String rootId) {
		return sysOrgRoleLineService.findRootElements(rootId);
	}

	/**
	 * 获取子节点
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/findSubElements")
	@ResponseBody
	public List getSubElements(String fdId) {
		return sysOrgRoleLineService.findSubElements(fdId);
	}
	
	/**
	 * 获取角色线成员父节点
	 */
	@RequestMapping(value = "/getParentNode", method = RequestMethod.POST)
	@ResponseBody
	public RoleMemberV getParentNode(RoleMemberV roleMemberV) {
		
		RoleMemberV roleMemberV2 = sysOrgRoleLineService.getParentNode(roleMemberV);
		
		return roleMemberV2;
	}
	
	/**
	 * 获取角色线成员父节点
	 */
	@RequestMapping(value = "/moveNode", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult moveNode(RoleMemberV roleMemberV) {
		WebMessageResult result = new WebMessageResult();
		try {
			sysOrgRoleLineService.moveNode(roleMemberV);
			result.setMessage(resourceUtil.getLocaleMessage("moveNodeSuccessed"));
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getLocaleMessage("moveNodeFailed"));
		}
		return result;
	}

	/**
	 * 获取所有下级角色线成员
	 */
	@RequestMapping("/subMembers/findAll")
	@ResponseBody
	public Page<RoleMemberV> findAllSubMembers(@RequestParam String context,@RequestParam String pageVO) {
		
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			RoleMemberV roleMember = mapper.readValue(context, RoleMemberV.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			return sysOrgRoleLineService.findAllSubMembers(roleMember,pageVo.getPageable());
		} catch (Exception e) {
			log.error("获取所有下级角色线成员失败："+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取角色线成员
	 */
	@RequestMapping("/getOne")
	@ResponseBody
	public SysOrgRoleLine getOne(String fdId) {
		return sysOrgRoleLineService.getOne(fdId);
	}
	
	/**
	 * 获取角色线成员
	 */
	@RequestMapping("/getOneMember")
	@ResponseBody
	public RoleMemberV getOneMember(String fdId) {
		return sysOrgRoleLineService.getOneMember(fdId);
	}

	/**
	 * 新增或修改
	 */
	@RequestMapping(value = "/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult save(@RequestParam String context) {
		WebMessageResult result = new WebMessageResult();
		SysOrgRoleLine sysOrgRoleLine = new SysOrgRoleLine();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			sysOrgRoleLine = mapper.readValue(context, SysOrgRoleLine.class);
			RoleMemberV newNode = null;
			sysOrgRoleLineService.save(sysOrgRoleLine);
			newNode = roleLineMemberVService.findByFdId(sysOrgRoleLine.getFdId());
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
			result.setData(newNode);
		}catch (RuntimeException e) {
			e.printStackTrace();
			result.setErrorMessage(e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		return result;
	}

	/**
	 * 快速新建下级
	 */
	@RequestMapping(value = "/addQuickly", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult addQuickly(String context1, String context2) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			RoleMemberV roleMemberParent = mapper.readValue(context1, RoleMemberV.class);
			//组织架构列表
			List<String> idList = this.toList(context2, ";");
			List<SysOrgRoleLine> roleLineList =  roleLineMemberVService.addQuickly(roleMemberParent, idList);
			//返回角色线成员视图列表
			List<String> roleIdList = new ArrayList<String>();
			for (SysOrgRoleLine sysOrgRoleLine : roleLineList) {
				roleIdList.add(sysOrgRoleLine.getFdId());
			}
			List<RoleMemberV> rolemMemberVList = roleLineMemberVService.findByFdId(roleIdList);
			result.setMessage(resourceUtil.getLocaleMessage("webController.quicklySaveSuccess"));
			result.setData(rolemMemberVList);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.quicklySaveFailure"));
		}
		return result;
	}
	
	private List<String> toList(String string,String split) {
		List<String> list = new ArrayList<String>();
		if (StringUtils.isNotEmpty(string)) {
			String [] stringArr = string.split(split);
			for (String string2 : stringArr) {
				list.add(string2);
			}
		}
		return list;
	}
	
	/**
	 * 获取角色线成员
	 */
	@RequestMapping(value = "/checkRepeat/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult checkRepeat(String context) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, SysOrgRoleLineDefaultRole.class);
			List<SysOrgRoleLineDefaultRole> defaultRoleList = mapper.readValue(context, javaType);
			sysOrgRoleLineDefaultRoleService.save(defaultRoleList);
			result.setMessage(resourceUtil.getLocaleMessage("setDefPostSuccess"));
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getLocaleMessage("setDefPostFailure"));
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询重复
	 */
	@RequestMapping(value = "/checkRepeat/findRepeat", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult defaultRoleSave(String confId) {
		List<RepeatPostOnRoleconfV> list = new ArrayList<RepeatPostOnRoleconfV>();
		List<SysOrgRoleLineDefaultRole> defaultRoleList = new ArrayList<SysOrgRoleLineDefaultRole>();
		WebMessageResult result = new WebMessageResult();
		try {
			Map<String, Object> valueMap = new HashMap<String, Object>();
			list = roleLineMemberVService.checkRepeat(confId);
			defaultRoleList = this.getDefaultRoles(list, defaultRoleList);
			valueMap.put("repeatList", list);
			valueMap.put("defaultRoleList", defaultRoleList);
			result.setData(valueMap);
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getLocaleMessage("checkRepeatFaliure"));
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取角色默认岗位
	 */
	private List<SysOrgRoleLineDefaultRole> getDefaultRoles(List<RepeatPostOnRoleconfV> list,List<SysOrgRoleLineDefaultRole> defaultRoleList) {
		for (RepeatPostOnRoleconfV repeatPostOnRoleconfV : list) {
			String confId = repeatPostOnRoleconfV.getConfId();
			String personId = repeatPostOnRoleconfV.getPersonId();
			String[] postIds = repeatPostOnRoleconfV.getPostIds().split(",");
			//获取角色默认岗位
			SysOrgRoleLineDefaultRole defaultRole = sysOrgRoleLineDefaultRoleService.getOrNewOne(confId, personId);
			boolean flag = false;
			for (int i = 0; i < postIds.length; i++) {
				if(postIds[i].equals(defaultRole.getFdPostId())){
					flag = true;
				}
			}
			//设置角色默认岗位
			if(null==defaultRole.getFdPostId()||!flag){
				defaultRole.setFdPostId(postIds[0]);
			}
			defaultRoleList.add(defaultRole);
		}
		return defaultRoleList;
	}

	/**
	 * 删除节点
	 */
	@RequestMapping(value = "/deleteNode", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult deleteNode(SysOrgRoleLine sysOrgRoleLine) {

		WebMessageResult result = new WebMessageResult();
		try {
			if (sysOrgRoleLineService.deleteByFdId(sysOrgRoleLine.getFdId())) {
				result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
			}else {
				result.setErrorMessage(resourceUtil.getLocaleMessage("deleteSubsFirst"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		return result;
	}

}

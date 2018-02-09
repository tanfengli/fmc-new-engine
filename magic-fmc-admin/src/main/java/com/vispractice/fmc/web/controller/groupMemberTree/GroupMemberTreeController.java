package com.vispractice.fmc.web.controller.groupMemberTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.base.constrant.SysOrgConstant;
import com.vispractice.fmc.business.base.domain.SysOrgElementForm;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgGroupCate;
import com.vispractice.fmc.business.services.groupMemberTree.IGroupMemberTreeService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
/**
 * 
 * 编  号：
 * 名  称：GroupMemberTreeController
 * 描  述：GroupMemberTreeController.java
 * 完成日期：2017年9月12日 上午11:07:56
 * 编码作者："LaiJiashen"
 */
@Slf4j
@Controller
@RequestMapping("/groupMemberTree")
public class GroupMemberTreeController {

	@Autowired
	private IGroupMemberTreeService groupMemberTreeService;
	

	/**
	 * 
	 * 获取根节点(群组、通用岗位、角色线)<br/>
	 */
	@RequestMapping("/findRootElements")
	@ResponseBody
	public List<SysOrgGroupCate> getRootElements() {
		return groupMemberTreeService.findRootElements();
	}

	/**
	 * 找子节点(群组、通用岗位、角色线)
	 */
	@RequestMapping(value = "/findSubElements")
	@ResponseBody
	public List<SysOrgGroupCate> getSubElements(String fdId) {
		return groupMemberTreeService.findSubElements(fdId);
	}

	/**
	 * 
	 * 获取根节点(群组)<br/>
	 */
	@RequestMapping("/onlyGroup/findRootElements")
	@ResponseBody
	public List<SysOrgGroupCate> getRootElementsOnlyGroup() {
		return groupMemberTreeService.findRootElements();
	}

	/**
	 * 找子节点(群组)
	 */
	@RequestMapping(value = "/onlyGroup/findSubElements")
	@ResponseBody
	public List<SysOrgGroupCate> getSubElementsOnlyGroup(String fdId) {
		return groupMemberTreeService.findSubElementsOnlyGroup(fdId);
	}
	
	
	/**
	 * 
	 * 获取根节点(组织架构)<br/>
	 */
	@RequestMapping(value = "/orgElement/findRootElements")
	@ResponseBody
	public List<SysOrgElement> getRootElementsOrgElement() {
		return groupMemberTreeService.findRootOrgElements();
	}

	/**
	 * 找子节点(组织架构)
	 */
	@RequestMapping(value = "/orgElement/findSubElements")
	@ResponseBody
	public List<SysOrgElement> getSubElementsOrgElement(String fdId) {
		return groupMemberTreeService.findSubOrgElements(fdId);
	}
	
	@RequestMapping(value = "/getOrgElements", method = RequestMethod.POST)
	@ResponseBody
	public List<SysOrgElement> getOrgElements(String context){
		
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		
		SysOrgElementForm form;
		try {
			form = mapper.readValue(context, SysOrgElementForm.class);
			return groupMemberTreeService.getOrgElements(form.getNodeId(),form.getCheckedValue());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取群组成员
	 */
	@RequestMapping(value = "/getGroupMember", method = RequestMethod.POST)
	@ResponseBody
	public List<SysOrgElement> getGroupMember(String parentId,String[] fdOrgTypes) {
		List<Long> types = new ArrayList<Long>();
		types.add(new Long(SysOrgConstant.ORG_TYPE_POST));
		types.add(new Long(SysOrgConstant.ORG_TYPE_PERSON));
		return groupMemberTreeService.getGroupMember(parentId,types);
	}

	/**
	 * 获取群组
	 */
	@RequestMapping(value = "/getGroup", method = RequestMethod.POST)
	@ResponseBody
	public List<SysOrgElement> getGroup(String fdId) {
		return groupMemberTreeService.getGroup(fdId);
	}
	
	/**
	 * 获取地址本成员描述
	 */
	@RequestMapping(value = "/getDescription", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult getDescription(String id,String name) {
		WebMessageResult result = new WebMessageResult();
		String description = "";
		try {
			description = groupMemberTreeService.getDescription(id, name);
			result.setData(description);
		} catch (Exception e) {
			log.error("获取描述失败");
			e.printStackTrace();
			result.setErrorMessage("获取描述失败");
		}
		return result;
	}
	
	

}

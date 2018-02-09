package com.vispractice.fmc.web.controller.setting;

import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.base.domain.SysOrgElementForm;
import com.vispractice.fmc.business.entities.setting.view.GroupAdmin;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.services.setting.IGroupAdminService;
import com.vispractice.fmc.web.domain.setting.GroupMemberForm;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

/**
 * 
 * 编  号：<br/>
 * 名  称：GroupAdminController<br/>
 * 描  述：<br/>
 * 完成日期：2016年12月15日 下午5:24:02<br/>
 * 编码作者："LaiJiashen"<br/>
 */


@Slf4j
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/setting/groupAdmin")
public class GroupAdminController extends AbstractController{
	
	@Autowired
	private IGroupAdminService groupAdminService;
	
	@Autowired
	private ISysOrgElementService sysOrgElementService;

	
	@RequestMapping("/index")
	public String groupIndex(Model model){

		return "settings/groupadmin/groupadmin_index";
	}
	
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<GroupAdmin> findAll(@RequestParam String context, @RequestParam String pageVO) {

		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			GroupAdmin compDbcp = mapper.readValue(context, GroupAdmin.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			return groupAdminService.findBySearch(compDbcp, pageVo.getPageable());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult saveOrUpdate(@RequestParam String context){
		
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			GroupMemberForm groupMemberForm = mapper.readValue(context, GroupMemberForm.class);
			
			if(groupAdminService.save(groupMemberForm)){
				result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
			}else{
				result.setErrorMessage(resourceUtil.getLocaleMessage("groupNameRepeated"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(resourceUtil.getLocaleMessage("webController.saveFailure"));
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/deleteOne", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult deleteOne(GroupAdmin groupAdmin){
		WebMessageResult result = new WebMessageResult();
		try {
			groupAdminService.delById(groupAdmin);
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		return result;
	}
	
	@RequestMapping(value = "/findMember", method = RequestMethod.POST)
	@ResponseBody
	public List<SysOrgElement> findMember(String context){
		
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		
		SysOrgElementForm form;
		try {
			form = mapper.readValue(context, SysOrgElementForm.class);
			return groupAdminService.findMember(form.getNodeId(),form.getCheckedValue());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/findPerson", method=RequestMethod.POST)
	@ResponseBody
	public List<SysOrgElement> findPerson(String fdId,Long fdOrgType){
		return groupAdminService.findPerson(fdId,fdOrgType);
	}
	
	
	/**
	 * 群组成员
	 */
	@RequestMapping(value = "/findGroupMember", method = RequestMethod.POST)
	@ResponseBody
	public List<SysOrgElement> findGroupMember(String groupId){
		
		return groupAdminService.findGroupMember(groupId);
	}
	
	
	/**
	 * 获取form
	 */
	@RequestMapping(value = "/findForm", method = RequestMethod.POST)
	@ResponseBody
	public GroupMemberForm findForm (String fdId){
		
		SysOrgElement sysOrgElement = sysOrgElementService.findByFdId(fdId);
		
		GroupMemberForm groupMemberForm = trans(sysOrgElement);
		
		groupMemberForm.setGroupElementArray(groupAdminService.findGroupMember(fdId));
		
		return groupMemberForm;
	}
	
	
	/**
	 * 保存element到form
	 */
	public GroupMemberForm trans(SysOrgElement sysOrgElement){
		
		GroupMemberForm groupMemberForm = new GroupMemberForm();
		
		groupMemberForm.setFdAlterTime(sysOrgElement.getFdAlterTime());
		groupMemberForm.setFdCalendarId(sysOrgElement.getFdCalendarId());
		groupMemberForm.setFdCateid(sysOrgElement.getFdCateid());
		groupMemberForm.setFdCreateTime(sysOrgElement.getFdCreateTime());
		groupMemberForm.setFdFlagDeleted(sysOrgElement.getFdFlagDeleted());
		groupMemberForm.setFdHierarchyId(sysOrgElement.getFdHierarchyId());
		groupMemberForm.setFdId(sysOrgElement.getFdId());
		groupMemberForm.setFdImportInfo(sysOrgElement.getFdImportInfo());
		groupMemberForm.setFdIsAbandon(sysOrgElement.getFdIsAbandon());
		groupMemberForm.setFdIsAvailable(String.valueOf(sysOrgElement.getFdIsAvailable()));
		groupMemberForm.setFdIsBusiness(sysOrgElement.getFdIsBusiness());
		groupMemberForm.setFdKeyword(sysOrgElement.getFdKeyword());
		groupMemberForm.setFdLdapDn(sysOrgElement.getFdLdapDn());
		groupMemberForm.setFdMemo(sysOrgElement.getFdMemo());
		groupMemberForm.setFdName(sysOrgElement.getFdName());
		groupMemberForm.setFdNamePinyin(sysOrgElement.getFdNamePinyin());
		groupMemberForm.setFdNo(sysOrgElement.getFdNo());
		groupMemberForm.setFdOrder(sysOrgElement.getFdOrder());
		groupMemberForm.setFdOrgType(sysOrgElement.getFdOrgType());
		groupMemberForm.setFdParentid(sysOrgElement.getFdParentorgid());
		groupMemberForm.setFdParentorgid(sysOrgElement.getFdParentorgid());
		groupMemberForm.setFdSuperLeaderid(sysOrgElement.getFdSuperLeaderid());
		groupMemberForm.setFdThisLeaderid(sysOrgElement.getFdThisLeaderid());
		
		return groupMemberForm;
	}
	
	
	
	
	
}

package com.vispractice.fmc.web.controller.sys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.entities.sys.SysNotifySetting;
import com.vispractice.fmc.business.entities.sys.SysNotifySettingTarget;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.services.sys.ISysNotifySettingService;
import com.vispractice.fmc.business.services.sys.ISysNotifySettingTargetService;
import com.vispractice.fmc.web.domain.sys.SysNotifySettingForm;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;

/**
 * 通知配置
 * 编  号：<br/>
 * 名  称：SysNotifySettingController<br/>
 * 描  述：<br/>
 * 完成日期：2017年1月18日 下午3:07:24<br/>
 * 编码作者：Administrator<br/>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/sys/notifySet")
public class SysNotifySettingController extends AbstractController{

	@Autowired
	private ISysNotifySettingService sysNotifySettingService;
	
	@Autowired
	private ISysNotifySettingTargetService notifySettingTargetService;
	
	@Autowired
	private ISysOrgElementService sysOrgElementService;
	
	/**
	 * 数据源页面入口
	 * */
	@RequestMapping("/list")
	public String goIndex(Model model) {  
		return "sys/sysNotifySetting/notifySet_list";
	}  
	
	
	@RequestMapping(value = "/findForm", method = RequestMethod.POST)
	@ResponseBody
	private SysNotifySettingForm findForm(){
		List<SysNotifySetting> list = sysNotifySettingService.findList();
		SysNotifySettingForm snsf = new SysNotifySettingForm();
		if(list.size()>0){
			for (SysNotifySetting sns : list) {
				if(sns.getFdKey().equals("orgSynchroMessageSetting")){
					snsf.setOrgFdId(sns.getFdId());
					snsf.setOrgFdKey(sns.getFdKey()); 
					if(null!=sns.getFdNotifyType()){
						List<String> str = new ArrayList<String>();
						str.add(sns.getFdNotifyType());
						snsf.setOrgFdNotifyType(str); 
					}
					List<String> listOrgId = notifySettingTargetService.findFdOrgId(sns.getFdId());
					if(listOrgId.size()>0){
					    String ids = "";
					    String names = "";
						List<SysOrgElement> editor = sysOrgElementService.findByFdIdIn(listOrgId);  
						for (SysOrgElement soe : editor) {
							ids = ids+soe.getFdId() + ";";
							names = names+soe.getFdName() +";";
						}
						snsf.setOrgPerId(ids);
						snsf.setOrgPerName(names);
					} 
					snsf.setOrgFdSubject(sns.getFdSubject());
					snsf.setOrgFdContent(sns.getFdContent());
				}
				if(sns.getFdKey().equals("createAccountMessageSetting")){
					snsf.setCreFdId(sns.getFdId());
					snsf.setCreFdKey(sns.getFdKey()); 
					if(null!=sns.getFdNotifyType()){
						List<String> str = new ArrayList<String>();
						str.add(sns.getFdNotifyType()); 
						snsf.setCreFdNotifyType(str);
					}  
					List<String> listCreId = notifySettingTargetService.findFdOrgId(sns.getFdId());
					if(listCreId.size()>0){
						 String ids = "";
						 String names = "";
						 List<SysOrgElement> editor = sysOrgElementService.findByFdIdIn(listCreId); 
						 for (SysOrgElement soe : editor) {
								ids = ids+soe.getFdId() + ";";
								names = names+soe.getFdName() +";";
						 }
						 snsf.setCrePerId(ids);
						 snsf.setCrePerName(names);
					}	
					
					snsf.setCreFdSubject(sns.getFdSubject());
					snsf.setCreFdContent(sns.getFdContent());
				}
				if(sns.getFdKey().equals("deleteAccountMessageSetting")){
					snsf.setDelFdId(sns.getFdId());
					snsf.setDelFdKey(sns.getFdKey()); 
					if(null!=sns.getFdNotifyType()){
						List<String> str = new ArrayList<String>();
						str.add(sns.getFdNotifyType()); 
						snsf.setDelFdNotifyType(str);
					}
					List<String> listDelId = notifySettingTargetService.findFdOrgId(sns.getFdId());
					if(listDelId.size()>0){
						 String ids = "";
						 String names = "";
						 List<SysOrgElement> editor = sysOrgElementService.findByFdIdIn(listDelId); 
						 for (SysOrgElement soe : editor) {
								ids = ids+soe.getFdId() + ";";
								names = names+soe.getFdName() +";";
						 }
						 snsf.setDelPerId(ids);
						 snsf.setDelPerName(names);
						 
					}	
					snsf.setDelFdSubject(sns.getFdSubject());
					snsf.setDelFdContent(sns.getFdContent());
				}
			}
		} 
        return snsf;
	}

	 
	@RequestMapping(value = "/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult add(@RequestParam String context) {
		WebMessageResult result = new WebMessageResult();
		try {  
			ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
			SysNotifySettingForm form = mapper.readValue(context,SysNotifySettingForm.class);
			
			//删除从表对象
			notifySettingTargetService.deleteByFdNotifySettingId(form.getOrgFdId());
			notifySettingTargetService.deleteByFdNotifySettingId(form.getCreFdId());
			notifySettingTargetService.deleteByFdNotifySettingId(form.getDelFdId());
			
			//删除原来的值对象，重新添加
			if(null!=form.getOrgFdId()){
				sysNotifySettingService.delete(form.getOrgFdId());
			}
			if(null!=form.getCreFdId()){
			    sysNotifySettingService.delete(form.getCreFdId());
			}
		    if(null!=form.getDelFdId()){
				sysNotifySettingService.delete(form.getDelFdId());
			}
			SysNotifySetting orgForm = new SysNotifySetting();
			orgForm.setFdKey("orgSynchroMessageSetting"); 
			if(form.getOrgFdNotifyType().size()>0){ 
				orgForm.setFdNotifyType(form.getOrgFdNotifyType().get(0));
			}else{
				orgForm.setFdNotifyType("");
			}  
			orgForm.setFdSubject(form.getOrgFdSubject());
			orgForm.setFdContent(form.getOrgFdContent());
			SysNotifySetting org = sysNotifySettingService.save(orgForm);
			  
			if(null!=form.getOrgPerId()){
				List<String> orgPerArray = StringToList(form.getOrgPerId(),";");
				for (String str : orgPerArray) {
					SysNotifySettingTarget snst = new SysNotifySettingTarget();
					snst.setFdNotifySettingId(org.getFdId());
					snst.setFdOrgId(str);
					notifySettingTargetService.save(snst);
				}
			}
			
			
			SysNotifySetting creForm = new SysNotifySetting();
			creForm.setFdKey("createAccountMessageSetting");  
			if(form.getCreFdNotifyType().size()>0){ 
				creForm.setFdNotifyType(form.getCreFdNotifyType().get(0));
			}else{
				creForm.setFdNotifyType("");
			}  
			creForm.setFdSubject(form.getCreFdSubject());
			creForm.setFdContent(form.getCreFdContent());
			SysNotifySetting cre = sysNotifySettingService.save(creForm);
			 
			if(null!=form.getCrePerId()){
				List<String> crePerArray = StringToList(form.getCrePerId(),";");
				for (String str : crePerArray) {
					SysNotifySettingTarget snst = new SysNotifySettingTarget();
					snst.setFdNotifySettingId(cre.getFdId());
					snst.setFdOrgId(str);
					notifySettingTargetService.save(snst);
				}
			}
			
			
			SysNotifySetting delForm = new SysNotifySetting();
			delForm.setFdKey("deleteAccountMessageSetting");  
			if(form.getDelFdNotifyType().size()>0){ 
				delForm.setFdNotifyType(form.getDelFdNotifyType().get(0));
			}else{
				delForm.setFdNotifyType("");
			} 
			delForm.setFdSubject(form.getDelFdSubject());
			delForm.setFdContent(form.getDelFdContent());
			SysNotifySetting del = sysNotifySettingService.save(delForm); 
		 
			if(null!=form.getDelPerId()){
				List<String> delPerArray = StringToList(form.getDelPerId(),";");
				for (String str : delPerArray) {
					SysNotifySettingTarget snst = new SysNotifySettingTarget();
					snst.setFdNotifySettingId(del.getFdId());
					snst.setFdOrgId(str);
					notifySettingTargetService.save(snst);
				}
			}
			
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		return result;
	 }
	
	/** 
	 * @Description:把list转换为一个用逗号分隔的字符串 
	 */  
	public static String listToString(List<String> list,String sign) {  
	    StringBuilder sb = new StringBuilder();  
	    if (list != null && list.size() > 0) {  
	        for (int i = 0; i < list.size(); i++) {  
	            if (i < list.size() - 1) {  
	                sb.append(list.get(i) + sign);  
	            } else {  
	                sb.append(list.get(i));  
	            }  
	        }  
	    }  
	    return sb.toString();  
	}  
	
	/**
	 *  字符串转数组
	 */
	public static List<String> StringToList(String obj,String sign){
		String[] array = obj.split(sign);
        List<String> list = new ArrayList<String>();
        for (String str : array)
        {
            list.add(str);
        }
        return list;
	}
	
}

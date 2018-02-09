package com.vispractice.fmc.web.controller.sys;
   
import java.util.ArrayList;
import java.util.List;

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
import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorize;
import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfAuthorizerV;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgPostPersonService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfAuthorizeItemService;
import com.vispractice.fmc.business.services.sys.ISysWfAuthorizeScopeService;
import com.vispractice.fmc.business.services.sys.ISysWfAuthorizerService;
import com.vispractice.fmc.business.services.sys.ISysWfAuthorizerVService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;
  
/**
 * 流程授权管理
 * 编  号：<br/>
 * 名  称：SysAuthManController<br/>
 * 描  述：<br/>
 * 完成日期：2016年12月28日 下午4:21:56<br/>
 * 编码作者：
 */
@Controller
@RequestMapping("/sys/sysAuth")
public class SysAuthManController extends AbstractController<SysWfAuthorize>{
	@Autowired
	private ISysWfAuthorizerService wfAuthorizerService; 
	
	@Autowired
	private ISysWfAuthorizerVService authorizerVService; 

	@Autowired
	private ISysOrgElementService elementService;
	
	@Autowired
	private ISysWfAuthorizeScopeService authorizeScopeService;
	
	@Autowired
	private ISysWfAuthorizerVService sysWfAuthorizerVService;

	@Autowired
	private ISysOrgPostPersonService orgPostPersonService;
	
	@Autowired
	private ISysWfAuthorizeItemService authorizeItemService;
	
	/**
	 * 数据源页面入口
	 * */
	@RequestMapping("/list")
	public String goIndex(Model model) { 
		return "sys/sysAuthManagement/sysAuth_list";
	} 
	
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysWfAuthorizerV> findAll(@RequestParam String context,@RequestParam String pageVO){  
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysWfAuthorizerV wfAuthorizeV = mapper.readValue(context, SysWfAuthorizerV.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class); 
			return authorizerVService.findBySearch(wfAuthorizeV,pageVo.getPageable());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 
	/** 
	 * 把list转换为一个用逗号分隔的字符串 
	 */  
	public static String listToString(List<String> list) {  
	    StringBuilder sb = new StringBuilder();  
	    if (list != null && list.size() > 0) {  
	        for (int i = 0; i < list.size(); i++) {  
	            if (i < list.size() - 1) {  
	                sb.append(list.get(i) + ",");  
	            } else {  
	                sb.append(list.get(i));  
	            }  
	        }  
	    }  
	    
	    return sb.toString();  
	} 
	
	/**
	 * 新增或修改
	 **/ 
	@RequestMapping(value = "/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult add(@RequestParam String context) {
		WebMessageResult result = new WebMessageResult();
		try {  
			ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
			SysWfAuthorizerV wfAuthorizeV = mapper.readValue(context,SysWfAuthorizerV.class);
			sysWfAuthorizerVService.save(wfAuthorizeV,this.getCurrentUser());
			
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		
		return result;
	}
	
	/**
	 * 字符串转数组
	 */
	public static List<String> StringToList(String obj) {
		String[] array = obj.split(";");
        List<String> list = new ArrayList<String>();
        for (String str : array) {
            list.add(str);
        }
        
        return list;
	}

	/**
	 * 实现流程:(查询授权项)
	 */
	@RequestMapping(value = "/findItemOption", method = RequestMethod.POST)
	@ResponseBody
	public List<SysOrgElement> findItemOption(@RequestParam String fdId){  
		try {
			//根据fdId查询人员所有的岗位信息
			List<String> listFdPostId = orgPostPersonService.findFdPostId(fdId);
			listFdPostId.add(fdId);
			//查询人员,岗位信息明细
			List<SysOrgElement> elements = elementService.findByFdIdIn(listFdPostId);
			 
			return elements;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping("/deleteOne")
	@ResponseBody
	public WebMessageResult deleteOne(String fdId) throws Exception {  
		WebMessageResult result = new WebMessageResult();
		try {  
			//删除详表
			authorizeItemService.deleteByFdAuthorizeId(fdId);//授权项
			authorizeScopeService.deleteByfdAuthorizeId(fdId); //授权范围
			wfAuthorizerService.deleteByFdId(fdId);
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		return result;
	}
}

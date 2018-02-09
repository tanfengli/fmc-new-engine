package com.vispractice.fmc.web.controller.authority;

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
import com.vispractice.fmc.business.entity.sys.authority.UserAuthorityV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.service.sys.authority.IUserAuthSearchService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgPostPersonService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;
/**
 * 
 * 编  号：
 * 名  称：UserAuthoritySearchController
 * 描  述：员工权限查询控制器
 * 完成日期：2017年10月13日 上午10:49:14
 * 编码作者："LaiJiashen"
 */
@SuppressWarnings("rawtypes")
@Slf4j
@Controller
@RequestMapping(value="/authority/userAuth")
public class UserAuthoritySearchController extends AbstractController{
	
	@Autowired
	private IUserAuthSearchService userAuthSearchService;
	
	@Autowired
	private ISysOrgPostPersonService sysOrgPostPersonService;
	
	@RequestMapping("/index")
	public String index() {
		return "authority/userAuthoritySearch/userAuthoritySearch_index";
	}
	
	@RequestMapping(value = "/user/findAll",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult findAll(@RequestParam String context,@RequestParam String pageVO) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysOrgElement element = mapper.readValue(context, SysOrgElement.class);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			Page<SysOrgElement> page = userAuthSearchService.findUser(element, pageVo.getPageable());
			result.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("查询人员列表失败");
		}
		
		return result;
	}
	
	private String tran(String context) {
		context = context.replaceAll("[\"{}]", "");
		return context;
	}
	
	@RequestMapping(value = "/getPost")
	@ResponseBody
	public WebMessageResult getPost(String fdId) {
		WebMessageResult result = new WebMessageResult();
		try {
			List<SysOrgElement> list = sysOrgPostPersonService.getPosts(fdId);
			StringBuffer postsName = new StringBuffer();
			if (list.size()>0) {
				for (SysOrgElement sysOrgElement : list) {
					postsName.append(sysOrgElement.getFdName());
					postsName.append(";");
				}
			}
			String name = postsName.length()>0?postsName.substring(0, postsName.length()-1):"";
			result.setData(name);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/findAll",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult findAuthorityAll(@RequestParam String context,@RequestParam String pageVO) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			String orgId = this.tran(context);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			Page<UserAuthorityV> page = userAuthSearchService.findUserAuthority(orgId, pageVo.getPageable());
			result.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("查询员工权限失败");
		}
		return result;
	}
	
}

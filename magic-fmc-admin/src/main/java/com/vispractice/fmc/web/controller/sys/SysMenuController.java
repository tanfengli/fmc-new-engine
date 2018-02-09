package com.vispractice.fmc.web.controller.sys;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.base.utils.IDGenerator;
import com.vispractice.fmc.business.entity.sys.menu.SysMenu;
import com.vispractice.fmc.business.entity.sys.menu.SysMenuParam;
import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;
import com.vispractice.fmc.business.service.sys.menu.ISysMenuParamService;
import com.vispractice.fmc.business.service.sys.menu.ISysMenuService;
import com.vispractice.fmc.business.services.sys.ISysAuthMraService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

/**
 * 
 * 编  号：
 * 名  称：SysMenuController
 * 描  述：流程菜单控制器
 * 完成日期：2017年9月28日 下午6:22:40
 * 编码作者："LaiJiashen"
 */
@Slf4j
@Controller
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController<SysMenu>{
	
	@Autowired
	private ISysMenuService sysMenuService;
	
	@Autowired 
	private ISysAuthMraService sysAuthMraService;
	
	@Autowired
	private ISysMenuParamService sysMenuParamService;
	
	@RequestMapping("/index")
	public String toIndex(Model model){
		return "sys/menu/sysMenu_list";
	}
	
	/**
	 * 
	 * 获取根节点<br/>
	 */
	@RequestMapping("/findRootElements")
	@ResponseBody
	public List<SysMenu> getRootElements() {
		return sysMenuService.getRootElements();
	}

	/**
	 * 找子节点
	 */
	@RequestMapping("/findSubElements")
	@ResponseBody
	public List<SysMenu> getSubElements(@RequestParam String fdId) {
		return sysMenuService.getSubElements(fdId);
	}
	
	/**
	 * 
	 * 获取根节点<br/>
	 */
	@RequestMapping("/needAuth/findRootElements")
	@ResponseBody
	public List<SysMenu> getNeedAuthRootElements() {
		return sysMenuService.getRootElements();
	}

	/**
	 * 找子节点
	 */
	@RequestMapping("/needAuth/findSubElements")
	@ResponseBody
	public List<SysMenu> getNeedAuthSubElements(@RequestParam String fdId) {
		return sysMenuService.getNeedAuthSubElements(fdId);
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult findAll(@RequestParam String context,@RequestParam String pageVO) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysMenu sysMenu = mapper.readValue(context,SysMenu.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			Page<SysMenu> page = sysMenuService.findByPage(sysMenu, pageVo.getPageable());
			result.setData(page);
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getActionInfo("webController.dataGridSearchFailure"));
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询对象
	 */
	@RequestMapping(value = "/getFormItem", method = RequestMethod.GET)
	@ResponseBody
	public SysMenu getFormItem(String context){
		SysMenu sysMenu = null;
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			sysMenu = mapper.readValue(context, SysMenu.class);
			sysMenu = sysMenuService.getFormItem(sysMenu);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return sysMenu;
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(value = "/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult saveOrUpdate(@RequestParam String context) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		String userId = this.getCurrentUser().getFdId();
		try {
			String currDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(System.currentTimeMillis()));
			SysMenu sysMenu = mapper.readValue(context,SysMenu.class);
			if (null==sysMenu.getFdId()) {
				String id = IDGenerator.generateID();
				sysMenu.setFdId(id);
				sysMenu.setDocCreateId(userId);
				sysMenu.setDocCreateTime(currDate);
				sysMenu.setFdIsLeaf(1L);
				//设置排序号
				Long maxOrder = sysMenuService.findMaxFdOrderByFdParentId(sysMenu.getFdParentId());
				sysMenu.setFdOrder(maxOrder+SysMenu.increaseNumber);
			}else {
				sysMenu.setDocAlterorId(userId);
				sysMenu.setDocAlterTime(currDate);
			}
			//设置层级id
			if (null!=sysMenu.getFdParentId()) {
				SysMenu parentMenu = sysMenuService.findByFdId(sysMenu.getFdParentId());
				parentMenu.setFdIsLeaf(0L);
				sysMenu.setFdHierarchyId(parentMenu.getFdHierarchyId()+sysMenu.getFdId()+"x");
			}else {
				sysMenu.setFdHierarchyId("x"+sysMenu.getFdId()+"x");
			}
			//保存自定义参数
			String fdParameter = sysMenu.getFdParameter();
			List<SysMenuParam> sysParams = null;
			if (StringUtils.isNotEmpty(fdParameter)) {
				sysParams = mapper.readValue(fdParameter,mapper.getTypeFactory().constructParametricType(ArrayList.class,SysMenuParam.class));
				sysMenuParamService.saveSysMenuParams(sysParams, sysMenu.getFdId());
			}
			sysMenu = sysMenuService.save(sysMenu);
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
			result.setData(sysMenu);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			result.setErrorMessage(e.getMessage());
		}
		return result;
	}

	
	/**
	 * 移动节点
	 */
	@RequestMapping(value = "/moveNode", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult moveNode(String context){
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			JavaType javaType = mapper.getTypeFactory().constructCollectionLikeType(List.class, SysMenu.class);
			List<SysMenu> list = mapper.readValue(context,javaType);
			SysMenu sysMenu = list.get(0);
			SysMenu targetNode = list.get(1);
			sysMenu = sysMenuService.moveNode(sysMenu,targetNode);
			result.setData(sysMenu);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("移动失败。");
		}
		return result;
	}
	//SysOrgGroupCate sysOrgGroupCate
	//SysMenu sysMenu
	/**
	 * 删除节点
	 */
	@RequestMapping(value = "/deleteNode")
	@ResponseBody
	public WebMessageResult deleteNode(SysMenu sysMenu) {
		WebMessageResult result = new WebMessageResult();
		try {
			String fdId = sysMenu.getFdId();
			sysMenuService.deleteByFdId(fdId);
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			log.error(resourceUtil.getLocaleMessage("webController.deleteFailure"));
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		return result;
	}
	
	/**
	 * 获取所有所有已授权菜单<br/>
	 */
	@RequestMapping("/menuVO/findAllElements")
	@ResponseBody
	public List<SysMenu> getMenuVOElements() {
		SysOrgPerson currUser = this.getCurrentUser();
		List<SysMenu> list = sysAuthMraService.getMenuByUser(currUser.getFdId());
		return list;
	}
}

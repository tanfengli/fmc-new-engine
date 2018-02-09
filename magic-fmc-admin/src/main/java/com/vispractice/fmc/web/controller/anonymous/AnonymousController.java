package com.vispractice.fmc.web.controller.anonymous;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.base.domain.SysWfBusinessForm;
import com.vispractice.fmc.business.entity.sys.config.SysWfInterface;
import com.vispractice.fmc.business.service.sys.config.ISysWfInterfaceService;
import com.vispractice.fmc.business.service.sys.news.ISysNewsMainService;
import com.vispractice.fmc.business.services.setting.ILoadBillFormService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

@Slf4j
@Controller
@RequestMapping(value="/anonymous")
public class AnonymousController<T> extends AbstractController<T> {
	@Autowired
	private ISysNewsMainService sysNewsMainService;
	
	@Autowired
	private ISysWfInterfaceService sysWfInterfaceService;
	
	@Autowired
	private ILoadBillFormService loadBillFormService;
	
	/**
	 * 获取用户单据审批流程信息
	 * @return
	 */
	@RequestMapping(value = "/viewFlowChart",method = {RequestMethod.GET,RequestMethod.POST})
 	public String approvalProcess(@RequestParam String fdId,Model model){
		model.addAttribute("fdId",fdId);
		
		return "anonymous/flowChart/viewFlowChart";
	}
	
	/**
	 * 获取流程实例信息
	 * @param fdId
	 * @return
	 */
	@RequestMapping(value = "/getSysWfBusiness")
	@ResponseBody
	public WebMessageResult getSysWfBusiness(@RequestParam String fdId) {
		WebMessageResult result = new WebMessageResult();
		
		try {
			SysWfBusinessForm sysWfBusinessForm = loadBillFormService.loadForm(fdId,null);
			result.setCode(WebMessageResult.SUCCESS);
			result.setData(sysWfBusinessForm);
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setCode(WebMessageResult.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 对外接口查询页面
	 */
	@RequestMapping("/wslist")
	public ModelAndView wslist(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<SysWfInterface> sysWfInterfaces = sysWfInterfaceService.findInterface();
		mav.addObject("wsList", sysWfInterfaces);
		mav.setViewName("anonymous/ws/serviceList");
		
		return mav;
	}
	
	/**
	 * 获取对外发布接口信息
	 */
	@RequestMapping(value = "/interface/getInterfaces",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult getInterfaces() {
		WebMessageResult result = new WebMessageResult();
		
		try {
			List<SysWfInterface> sysWfInterfaces = sysWfInterfaceService.findInterface();
			result.setData(sysWfInterfaces);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("获取有效接口信息失败.");
		}
		
		return result;
	}

	/**
	 * 接口维护界面
	 * @return
	 */
	@RequestMapping("/interface/index")
	public String interfaceIndex() {
		return "anonymous/ws/sys_interface_main";
	}

	/**
	 * 查询接口信息
	 */
	@RequestMapping(value = "/interface/findAll",method = RequestMethod.POST)
	@ResponseBody
	public Page<SysWfInterface> findAll(@RequestParam String context,@RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysWfInterface sysWfInterface = mapper.readValue(context,SysWfInterface.class);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			Page<SysWfInterface> page = sysWfInterfaceService.searchInterface(sysWfInterface,pageVo.getPageable());
			
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 新增或者更新
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "/interface/saveOrUpdateForm",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult saveOrUpdateForm(String context) {
		WebMessageResult result = new WebMessageResult();
		try {  
			ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
			SysWfInterface sysWfInterface = mapper.readValue(context,SysWfInterface.class);
			sysWfInterfaceService.saveInterface(sysWfInterface);
			
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		
		return result;
	}
	
	/**
	 * 删除
	 * @param fdId
	 * @return
	 */
	@RequestMapping("/interface/deleteOne")
	@ResponseBody
	public WebMessageResult deleteOne(String fdId) { 
		WebMessageResult result = new WebMessageResult();
		try {
			sysWfInterfaceService.deleteInterface(fdId);
			
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		
		return result;
	}

}

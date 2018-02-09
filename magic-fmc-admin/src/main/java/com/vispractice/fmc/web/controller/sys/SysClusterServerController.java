package com.vispractice.fmc.web.controller.sys;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.entity.sys.cluster.SysClusterDispatcher;
import com.vispractice.fmc.business.entity.sys.cluster.SysClusterServer;
import com.vispractice.fmc.business.service.sys.cluster.IDispatcherSwitcherService;
import com.vispractice.fmc.business.service.sys.cluster.ISysClusterDispatcherService;
import com.vispractice.fmc.business.service.sys.cluster.ISysClusterServerService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

/**
 * 群集信息
 * 编  号：<br/>
 * 名  称：SysClusterServerController <br/>
 * 描  述：<br/>
 * 完成日期：2017年1月10日 下午4:43:40 <br/>
 * 编码作者：ZhouYanYao
 */
@Controller
@RequestMapping("/sys/sysClusterServer")
public class SysClusterServerController extends AbstractController<SysClusterServer> {
	/**
	 * 集群服务器服务
	 */
	@Autowired
	private ISysClusterServerService sysClusterServerService;
 
	/**
	 * 集群服务器分发服务
	 */
	@Autowired
	private ISysClusterDispatcherService sysClusterDispatcherService;
	
	@Autowired  
    private ApplicationContext applicationContext;  
	
//	@Autowired(required=false)
//	private IDispatcherSwitcherService dispatcherSwitcherService;
	
	/**
	 * 页面入口
	 */
	@RequestMapping("/list")
	public String goIndex(Model model) { 
		return "sys/sysClusterServer/sysClusterServer_list";
	} 
	
	/**
	 * 获取服务
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/loadServer",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult loadServer() {  
		WebMessageResult result = new WebMessageResult();
		
		try {
			List<String> dispatcherTypeList = new ArrayList<String>();
			dispatcherTypeList.add(SysClusterServer.DISPATCHERTYPE_LOCAL);
			List<SysClusterServer> sysClusterServers = sysClusterServerService.getSeverByDispatcherType(dispatcherTypeList);
			List<SysClusterDispatcher> sysClusterDispatchers = sysClusterDispatcherService.findAll();
			Map<String, List> map = new HashMap<String, List>();
			map.put("server", sysClusterServers);
			map.put("dispatcher", sysClusterDispatchers);
			result.setCode(WebMessageResult.SUCCESS);
			result.setData(map);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(WebMessageResult.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 条件查询
	 * @param context
	 * @param pageVO
	 * @return
	 */
	@RequestMapping(value = "/findAll",method = RequestMethod.POST)
	@ResponseBody
	public Page<SysClusterServer> findAll(@RequestParam String context,@RequestParam String pageVO) {  
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		
		try {
			SysClusterServer sysClusterServer = mapper.readValue(context,SysClusterServer.class);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			
			return sysClusterServerService.findBySearch(sysClusterServer,pageVo.getPageable());
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
	@RequestMapping(value = "/saveOrUpdateForm",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult saveOrUpdateForm(String context) {
		WebMessageResult result = new WebMessageResult();
		try {  
			ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
			SysClusterServer sysClusterServer = mapper.readValue(context,SysClusterServer.class);
			if ("0".equals(sysClusterServer.getSysOperatorType())) {
				sysClusterServerService.save(sysClusterServer);
			} else {
				Map<String, String> dispatcherMap = new HashMap<String, String>();
				if (StringUtils.isNotEmpty(sysClusterServer.getSysWfEventService())) {
					dispatcherMap.put("sysWfEventService", sysClusterServer.getSysWfEventService());
				}
				
				if (StringUtils.isNotEmpty(sysClusterServer.getSysQuartzDispatcher())) {
					dispatcherMap.put("sysQuartzDispatcher", sysClusterServer.getSysQuartzDispatcher());
				}
				IDispatcherSwitcherService dispatcherSwitcherService = (IDispatcherSwitcherService) applicationContext.getBean("dispatcherSwitcherServiceImpl");
				dispatcherSwitcherService.startSwitch(dispatcherMap);
			}
			
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
	 * @throws Exception
	 */
	@RequestMapping("/deleteOne")
	@ResponseBody
	public WebMessageResult deleteOne(String fdId) throws Exception { 
		WebMessageResult result = new WebMessageResult();
		try {
			sysClusterServerService.deleteByFdId(fdId);
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		return result;
	}
}

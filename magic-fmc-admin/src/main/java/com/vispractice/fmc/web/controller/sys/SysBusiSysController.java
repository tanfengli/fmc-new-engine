package com.vispractice.fmc.web.controller.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.entity.sys.config.SysBusiSys;
import com.vispractice.fmc.business.entity.sys.config.SysInterfaceParam;
import com.vispractice.fmc.business.entity.sys.config.SysWfBusiInterface;
import com.vispractice.fmc.business.entity.sys.config.SysWfInterface;
import com.vispractice.fmc.business.entity.sys.config.view.SysBusiSysV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.service.sys.config.ISysBusiSysService;
import com.vispractice.fmc.business.service.sys.config.ISysInterfaceParamService;
import com.vispractice.fmc.business.service.sys.config.ISysWfBusiInterface;
import com.vispractice.fmc.business.service.sys.config.ISysWfInterfaceService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

/**
 * 业务系统配置
 * 编  号：<br/>
 * 名  称：SysBusiSysController<br/>
 * 描  述：<br/>
 * 完成日期：2017年1月3日 下午4:26:08<br/>
 * 编码作者：
 */
@Controller
@RequestMapping("/sys/sysBusiSys")
public class SysBusiSysController extends AbstractController<SysBusiSys> {
	@Autowired
	private ISysBusiSysService sysBusiSysService;

	@Autowired
	private ISysOrgElementService sysOrgElementService;
	
	@Autowired
	private ISysWfInterfaceService sysWfInterface;
	
	@Autowired
	private ISysWfBusiInterface sysWfBusiInterface;
	
	@Autowired
	private ISysInterfaceParamService sysParamService;
	
	/**
	 * 业务系统首页
	 */
	@RequestMapping("/list")
	public String operationIndex(Model model) { 
		return "sys/sysBusiSys/sysBusiSys_list";
	} 
	
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysBusiSysV> findAll(@RequestParam String context,@RequestParam String pageVO) {  
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysBusiSysV sysBusiSys = mapper.readValue(context,SysBusiSysV.class);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			
			return sysBusiSysService.findBySearch(sysBusiSys,pageVo.getPageable());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取接口参数
	 */
	@RequestMapping(value = "/getSysBusiSysParam",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult getSysBusiSysParam(@RequestParam String fdId) {
		WebMessageResult result = new WebMessageResult();
		
		try {
			List<SysInterfaceParam> sysParams = sysParamService.findSysInterfaceParam(fdId);
			result.setCode(WebMessageResult.SUCCESS);
			result.setData(sysParams);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(WebMessageResult.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 新增或修改
	 */ 
	@RequestMapping(value = "/saveOrUpdateForm",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult addSysBusiSys(SysBusiSys sysBusiSys) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
		List<SysInterfaceParam> sysParams = null;
		String fdParameter = sysBusiSys.getFdParameter();
		
		try {  
			if (sysBusiSys.getFdId() == null) {
				SysOrgElement sysOrgElement = sysOrgElementService.findByFdNo(this.getCurrentUser().getFdLoginName());
				sysBusiSys.setFdCreatedBy(sysOrgElement.getFdId());	
				sysBusiSys.setFdCreatedDate(new java.util.Date(new Date().getTime()));
			}
			
			sysBusiSys = sysBusiSysService.save(sysBusiSys);
			
			if (StringUtils.isNotEmpty(fdParameter)) {
				sysParams = objectMapper.readValue(fdParameter,
					objectMapper.getTypeFactory().constructParametricType(ArrayList.class,SysInterfaceParam.class));
				
				sysParamService.saveSysInterfaceParams(sysParams,sysBusiSys.getFdId());
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
	 */
	@RequestMapping("/deleteOne")
	@ResponseBody
	public WebMessageResult deleteOne(SysBusiSys sysBusiSys) throws Exception { 
		WebMessageResult result = new WebMessageResult();
		try {
			sysBusiSysService.delete(sysBusiSys);
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		
		return result;
	}
	
	
	/**
	 * 获取接口树形根节点
	 */
	@RequestMapping("/grantInterface/findAllElements")
	@ResponseBody
	public WebMessageResult findAllElements() {
		WebMessageResult result = new WebMessageResult();
		
		try {
			List<SysWfInterface> sysWfInterfaces = sysWfInterface.findAllElements();
			result.setCode(WebMessageResult.SUCCESS);
			result.setData(sysWfInterfaces);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(WebMessageResult.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 获取接口调用授权
	 */
	@RequestMapping(value = "/grantInterface/getSysInterface",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult getSysInterface(@RequestParam String fdBusiId) {
		WebMessageResult result = new WebMessageResult();
		
		try {
			List<SysWfInterface> sysWfInterfaces = sysWfInterface.findByBusiAndType(fdBusiId,"0");
			result.setCode(WebMessageResult.SUCCESS);
			result.setData(sysWfInterfaces);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(WebMessageResult.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 获取接口树形根节点
	 */
	@RequestMapping("/grantInterface/findRootElements")
	@ResponseBody
	public List<SysWfInterface> getRootElements() {
		return sysWfInterface.getRootElements();
	}

	/**
	 * 获取接口树形子节点
	 */
	@RequestMapping("/grantInterface/findSubElements")
	@ResponseBody
	public List<SysWfInterface> getSubElements(String fdId) {
		return sysWfInterface.getSubElements(fdId);
	}
	
	/**
	 * 获取接口回调授权
	 */
	@RequestMapping(value = "/getSysBusiInterface",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult getSysBusiInterface(@RequestParam String fdId,@RequestParam String fdType) {
		WebMessageResult result = new WebMessageResult();
		
		try {
			List<SysWfBusiInterface> sysWfBusiInterfaces = sysWfBusiInterface.findBusiInterface(fdId,fdType);
			result.setCode(WebMessageResult.SUCCESS);
			result.setData(sysWfBusiInterfaces);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(WebMessageResult.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 接口授权
	 */
	@RequestMapping(value = "/grantInterface/saveOrUpdateForm",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult grantInterface(String context) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
		try {  
			Map<String,String> data = objectMapper.readValue(context,new TypeReference<Map<String,String>>(){});
			String fdBusiId = data.get("fdBusiId");
			String interfaceIds = data.get("interfaceIds");
			sysWfBusiInterface.saveGrantInterface(fdBusiId,interfaceIds);
			
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		
		return result;
	}
	
	/**
	 * 回调授权
	 */
	@RequestMapping(value = "/grantCallback/saveOrUpdateForm",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult grantCallback(String context) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
		try {  
			Map<String,String> data = objectMapper.readValue(context,new TypeReference<Map<String,String>>(){});
			String fdBusiId = data.get("fdBusiId");
			String fdIsBack = data.get("fdIsBack");
			String fdBackAddress = data.get("fdBackAddress");
			sysWfBusiInterface.saveGrantCallback(fdBusiId,fdIsBack,fdBackAddress);
			
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		
		return result;
	}
}

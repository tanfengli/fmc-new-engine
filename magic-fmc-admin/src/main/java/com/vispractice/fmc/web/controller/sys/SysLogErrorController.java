package com.vispractice.fmc.web.controller.sys;

import java.util.Date;
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
import com.vispractice.fmc.business.entities.sys.SysLogError;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.services.sys.ISysLogErrorService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;
 
/**
 * 错误日志
 * 编  号：<br/>
 * 名  称：SysLogErrorController<br/>
 * 描  述：<br/>
 * 完成日期：2017年1月11日 下午4:20:42<br/>
 * 编码作者：Administrator
 */
@Controller
@RequestMapping("/sys/sysLogError")
public class SysLogErrorController extends AbstractController<SysLogError>{
	@Autowired
	private ISysLogErrorService errorService;

	@Autowired
	private ISysOrgElementService elementService;
	
	/**
	 * 数据源页面入口
	 * */
	@RequestMapping("/list")
	public String operationIndex(Model model) { 
		return "sys/sysLogError/sysLogError_list";
	} 
	
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysLogError> findAll(@RequestParam String context,
			@RequestParam String pageVO){  
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysLogError sysLogError = mapper.readValue(context, SysLogError.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			Page<SysLogError> page = errorService.findBySearch(sysLogError, pageVo.getPageable());
			List<SysLogError> list = page.getContent();
			for (SysLogError sle : list) { 
			    String str = sle.getFdUrl(); 
			    int idx = str.indexOf('?');  
				if(idx>-1){
					String url = str.substring(0, idx); 
					sle.setFdUrl(url);
				} 
			}
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 新增或修改
	 * */ 
	@RequestMapping(value = "/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult add(SysLogError sysLogError) {
		WebMessageResult result = new WebMessageResult();
		try {  
			if(sysLogError.getFdId() == null){
				SysOrgElement element = elementService.findByFdNo(this.getCurrentUser().getFdLoginName());
				sysLogError.setFdOperatorId(element.getFdId());	
				
				Date utilDate=new Date();
				sysLogError.setFdCreateTime(new java.util.Date(utilDate.getTime()));
			}
			errorService.save(sysLogError);
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		return result;
	}

	@RequestMapping("/deleteOne")
	@ResponseBody
	public WebMessageResult deleteOne(SysLogError sysLogError) throws Exception { 
		WebMessageResult result = new WebMessageResult();
		try {
			errorService.delete(sysLogError);
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		return result;
	}

}

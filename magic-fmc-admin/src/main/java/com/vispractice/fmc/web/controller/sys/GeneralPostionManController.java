package com.vispractice.fmc.web.controller.sys;
    
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
import com.vispractice.fmc.business.entities.sys.view.PositionManagemetV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.services.sys.IPostionManagementVService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;
 
/**
  * @ClassName GeneralPostionManController
  * @Description TODO(通用岗位管理)
  * @author zhaoxiu
  * @Date 2016年12月14日 上午10:39:41
  * @version 1.0.0
  */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/sys/generalPostion")
public class GeneralPostionManController extends AbstractController{
	@Autowired
	private IPostionManagementVService managementVService;

	/**
	 * 数据源页面入口
	 * */
	@RequestMapping("/list")
	public String generalPostionIndex(Model model) { 
		return "sys/generalPositionManagemet/postion_list";
	} 
	
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<PositionManagemetV> findAll(@RequestParam String context,
			@RequestParam String pageVO){  
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			PositionManagemetV managemetV = mapper.readValue(context, PositionManagemetV.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			return managementVService.findBySearch(managemetV,pageVo.getPageable());
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
	public WebMessageResult add(@RequestParam String context) {
		WebMessageResult result = new WebMessageResult();
		try {  
			ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
			PositionManagemetV managemetV = mapper.readValue(context, PositionManagemetV.class);
			//验证岗位名称唯一性
			if(null != managemetV){
				if(null!=managemetV.getFdName()){
					String fdId = managemetV.getFdId();
					if(null==fdId||"".equals(fdId)){
						fdId="0";
					}
					int pmv = managementVService.findByFdName(managemetV.getFdName(),fdId);
			    	if(pmv>=1){
			    		result.setErrorMessage(resourceUtil.getLocaleMessage("postNameExist"));
						return result; 
			    	}
				} 
			} 
			
			managementVService.save(managemetV); 
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		return result;
	}
	
	@RequestMapping("/deleteOne")
	@ResponseBody
	public WebMessageResult deleteOne(PositionManagemetV managemetV) throws Exception { 
		WebMessageResult result = new WebMessageResult();
		try {
			managementVService.delete(managemetV);
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		return result;
	} 
	
	@RequestMapping("/getAll")
	@ResponseBody
	public List<SysOrgElement> getAll()  { 
		try {
			return managementVService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 
	
}

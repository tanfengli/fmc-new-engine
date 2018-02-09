package com.vispractice.fmc.web.controller.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.base.utils.function.PropertiesFuncProvider;
import com.vispractice.fmc.business.entity.sys.job.SysQuartzJob;
import com.vispractice.fmc.business.service.sys.job.ISysQuartzJobService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

/**
 * 变量设置 编 号：<br/>
 * 名 称：SysQuartzJobController<br/>
 * 描 述：<br/>
 * 编码作者：tanfengli<br/>
 */
@Controller
@RequestMapping("/sys/quartzJob")
public class SysQuartzJobController extends AbstractController<SysQuartzJob> {
	@Autowired
	private ISysQuartzJobService quartzService;
	
	/**
	 * 数据源页面入口
	 */
	@RequestMapping("/list")
	public String operationIndex(Model model) {
		return "sys/quartzJob/quartzjob_list";
	}

	/**
	 * 查询所有系统任务
	 * @param context
	 * @param pageVO
	 * @return
	 */
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysQuartzJob> findAll(@RequestParam String context, @RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysQuartzJob infoV = mapper.readValue(context, SysQuartzJob.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
//			infoV.setOrder("desc");
			return quartzService.findBySearch(infoV, pageVo.getPageable());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 启动或禁用操作
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "/toEnable", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult toEnable(String context) {
		WebMessageResult result = new WebMessageResult();
		boolean flag = true;
		try {
			ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
			SysQuartzJob quartzJob = mapper.readValue(context,SysQuartzJob.class);
			quartzService.save(quartzJob);
			quartzService.executeJob(quartzJob,"quartz");
			
//			asyncSysQuartzThread.execute(quartzJob);
//			publishJob(quartzJob,false);
			flag = quartzJob.isFdEnabled();
			if(flag)
				result.setMessage(resourceUtil.getLocaleMessage("quartzJob.startSuccess"));
			else{
				result.setMessage(resourceUtil.getLocaleMessage("quartzJob.stopSuccess"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(flag)
				result.setMessage(resourceUtil.getLocaleMessage("quartzJob.startFailure"));
			else{
				result.setMessage(resourceUtil.getLocaleMessage("quartzJob.stopFailure"));
			}
		}
		return result;
	}
	
	
	/**
	 * 立即运行操作
	 * @param fdId
	 * @return
	 */
	@RequestMapping(value = "/execute", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult execute(String fdId) {
		WebMessageResult result = new WebMessageResult();
		try {
//			asyncSysQuartzThread.execute(fdId);
			SysQuartzJob quartzJob = quartzService.findByFdId(fdId);
//			publishJob(quartzJob);
			quartzService.executeJob(quartzJob,"execute");
			result.setMessage(resourceUtil.getLocaleMessage("quartzJob.executeSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(resourceUtil.getLocaleMessage("quartzJob.executeFailure"));
		}
		return result;
	}

	/**
	 * 新增修改数据
	 * @param quartzJob
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult saveOrUpdateQuartzJob(SysQuartzJob quartzJob) {
		WebMessageResult result = new WebMessageResult();
		try {
			quartzService.save(quartzJob);
			
			quartzService.executeJob(quartzJob,"quartz");
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		return result;
	}
	
	
	/**
	 * 初始系统任务
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult init() {
		WebMessageResult result = new WebMessageResult();
		try {
//			asyncSysQuartzThread.clearAll();
		
			 List<Map<String,String>> list = PropertiesFuncProvider.initSysQuartzJob();
			 quartzService.initSysQuartzJob(list);
//			asyncSysQuartzThread.startSquartzJob();
//			publishJob(quartzJob);
			
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		return result;
	}
}

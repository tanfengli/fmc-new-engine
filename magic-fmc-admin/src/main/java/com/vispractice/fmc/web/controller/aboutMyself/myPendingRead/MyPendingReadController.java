package com.vispractice.fmc.web.controller.aboutMyself.myPendingRead;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.entity.sys.news.view.ProcessReadV;
import com.vispractice.fmc.business.service.sys.news.IProcessReadService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

/**
 * 
 * 编  号：
 * 名  称：MyPendingReadController
 * 描  述：我的待阅控制器
 * 完成日期：2017年8月28日 下午5:51:27
 * 编码作者："LaiJiashen"
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/aboutMyself/myPendingRead")
public class MyPendingReadController  extends AbstractController{
	
	@Autowired
	private IProcessReadService processReadService;
	

	/**
	 * 我提交的单据
	 */
	@RequestMapping("/index")
	public String toIndex() {
		return "aboutMyself/myPendingRead/my_pending_read_index";
	}
	
	/**
	 * 实现流程:获取当前用户提交的单据
	 * @Title:findAll 
	 * @param context
	 * @param pageVO
	 * @return
	 */
	@RequestMapping(value = "/findAll",method = RequestMethod.POST)
	@ResponseBody
	public Page<ProcessReadV> findAll(@RequestParam String context,@RequestParam String pageVO) {
		String curUserNo = this.getCurrentUser().getFdLoginName();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			ProcessReadV processReadV = mapper.readValue(context,ProcessReadV.class);
			processReadV.setReadUserNo(curUserNo);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			Page<ProcessReadV> page = processReadService.searchProcessRead(processReadV,pageVo.getPageable());
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping("/count")
	@ResponseBody
	public WebMessageResult count(){
		WebMessageResult result = new WebMessageResult();
		String userNo = this.getCurrentUser().getFdLoginName();
		try {
			Map map = processReadService.count(userNo);
			result.setCode(WebMessageResult.SUCCESS);
			result.setData(map);
		} catch (Exception e) {
			result.setCode(WebMessageResult.ERROR);
			result.setMessage("计算已读/未读消息发生错误。");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 设置为已读
	 * @Title: setIsRead 
	 * @param todoId 通知表ID
	 */
	@RequestMapping("/setIsRead")
	@ResponseBody
	public WebMessageResult setIsRead(@RequestParam String todoId) {
		WebMessageResult result = new WebMessageResult();
		String currentUserId = this.getCurrentUser().getFdId();
		try {
			processReadService.setIsRead(todoId, currentUserId);
			result.setCode(WebMessageResult.SUCCESS);
		} catch (Exception e) {
			result.setCode(WebMessageResult.ERROR);
			result.setMessage("设置为已阅时发生错误.");
			e.printStackTrace();
		}
		return result;
	}
	
	
}

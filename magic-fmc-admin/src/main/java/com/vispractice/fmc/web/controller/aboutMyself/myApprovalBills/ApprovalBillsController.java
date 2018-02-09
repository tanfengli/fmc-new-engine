package com.vispractice.fmc.web.controller.aboutMyself.myApprovalBills;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.base.utils.ResourceUtil;
import com.vispractice.fmc.business.entity.aboutmyself.AuditBillV;
import com.vispractice.fmc.business.entity.aboutmyself.VCmsTask;
import com.vispractice.fmc.business.service.aboutmyself.IAuditBillVService;
import com.vispractice.fmc.business.service.aboutmyself.IVCmsTaskService;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

@Controller
@RequestMapping("/aboutMyself/myApprovalBills")
public class ApprovalBillsController extends AbstractController<AuditBillV> {
	/**
	 * 审批记录服务
	 */
	@Autowired
	private IAuditBillVService auditBillVService;
	
	/**
	 * 待审信息服务
	 */
	@Autowired
	private IVCmsTaskService cmsTaskService;
	
	/**
	 * 国际化工具
	 */
	@Autowired
	private ResourceUtil resourceUtil;

	/**
	 * 跳转到待办或者已办页面
	 * @param status
	 * @return
	 */
	@RequestMapping("/index")
	public String toIndex(@RequestParam(value = "status") String status) {
		String index = "";
		
		switch (status) {
			case "todo":
				index = "aboutMyself/myApprovalBills/auditPending/sys_news_main";
				break;
			case "done":
				index = "aboutMyself/myApprovalBills/audited/sys_news_main";
				break;
		}

		return index;
	}

	/**
	 * 查询待审
	 */
	@RequestMapping(value = "/auditPending/findAll",method = RequestMethod.POST)
	@ResponseBody
	public Page<VCmsTask> findAll(@RequestParam String context,@RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			VCmsTask vCmsTask = mapper.readValue(context,VCmsTask.class);
			vCmsTask.setTaskUserNo(this.getCurrentUser().getFdLoginName());
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			Page<VCmsTask> page = cmsTaskService.searchVCmsTask(vCmsTask,pageVo.getPageable());
			
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 查询已审
	 */
	@RequestMapping(value = "/audited/findAll",method = RequestMethod.POST)
	@ResponseBody
	public Page<AuditBillV> findAuditAll(@RequestParam String context,@RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			AuditBillV auditBillV = mapper.readValue(context,AuditBillV.class);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			auditBillV.setTaskUserNo(this.getCurrentUser().getFdLoginName());
			Page<AuditBillV> page = auditBillVService.searchTask(auditBillV,pageVo.getPageable());
			
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}

package com.vispractice.fmc.web.controller.setting;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.base.utils.ResourceUtil;
import com.vispractice.fmc.business.base.exception.SavingVaildateException;
import com.vispractice.fmc.business.base.utils.ExcelUtils;
import com.vispractice.fmc.business.entity.sys.category.SysCategoryMain;
import com.vispractice.fmc.business.entity.sys.config.SysBusiSys;
import com.vispractice.fmc.business.entity.sys.news.SysNewsTemplate;
import com.vispractice.fmc.business.entity.sys.news.view.SysNewsTemplateV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfCommonTemplate;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfTemplate;
import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfHistoryTemplateV;
import com.vispractice.fmc.business.service.category.ICategoryMainService;
import com.vispractice.fmc.business.service.sys.config.ISysBusiSysService;
import com.vispractice.fmc.business.service.sys.news.ISysNewsTemplateService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfCommonTemplateService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfHistoryTemplateService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfTemplateService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

@Controller
@RequestMapping("/setting/sysNewsTemplate")
@Slf4j
public class SysNewsTemplateController extends AbstractController<SysNewsTemplate> {
	@Autowired
	private ICategoryMainService categoryMainService;
	
	@Autowired
	private ISysNewsTemplateService sysNewsTemplateService;

	@Autowired
	private ISysBusiSysService sysBusiSysService;

	@Autowired
	private ISysWfCommonTemplateService sysWfCommonTemplateService;

	@Autowired
	private ISysOrgElementService elementService;

	@Autowired
	private ISysWfTemplateService wfTemplateService;
	
	@Autowired
	private ISysWfHistoryTemplateService sysWfHistoryTemplateService;
	
	@Autowired
	private ResourceUtil resourceUtil;

	/**
	 * 页面入口
	 */
	@RequestMapping("/index")
	public String operationIndex(Model model) {
		return "settings/sysNewsTemplate/sysNewsTemplate_index";
	}
	
	/**
	 * 获取所有模板
	 */
	@RequestMapping(value = "/findAll",method = RequestMethod.POST)
	@ResponseBody
	public Page<SysNewsTemplateV> findAll(@RequestParam String context,@RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysNewsTemplateV operMain = mapper.readValue(context,SysNewsTemplateV.class);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			Page<SysNewsTemplateV> page = sysNewsTemplateService.findBySearch(operMain,pageVo.getPageable());
			
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 获取指定模板
	 */
	@RequestMapping(value = "/findForm",method = RequestMethod.POST)
	@ResponseBody
	public SysNewsTemplate findForm(String context) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysNewsTemplateV sysNewsTemplateV = mapper.readValue(context,SysNewsTemplateV.class);
			
			return sysNewsTemplateService.findTemplate(sysNewsTemplateV);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 保存或修改
	 */
	@RequestMapping(value = "/saveOrUpdateForm",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult saveOrUpdate(@RequestParam String context) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();//ObjectMapperFactory.getObjectMapper();
		try {
			SysNewsTemplate sysNewsTemplate = mapper.readValue(context,SysNewsTemplate.class);
			SysWfTemplate wfTemp = wfTemplateService.findTemplateByModelIdOrNewOne(sysNewsTemplate.getFdId());
			
			//当前登录用户
			SysOrgElement element = elementService.findByFdNo(this.getCurrentUser().getFdLoginName());
			// 写入或修改XML
			if (null == sysNewsTemplate.getFdId()) {
				// 操作人,操作时间
				sysNewsTemplate.setDocCreatorId(element.getFdId());
				sysNewsTemplate.setDocCreateTime(new Date());
				if (StringUtils.isEmpty(sysNewsTemplate.getFdStatus())) {
					sysNewsTemplate.setFdStatus("1");
				}
				
			} else {
				//保存修改时间,修改人
				sysNewsTemplate.setDocAlterTime(new Date());
				sysNewsTemplate.setDocAlterId(element.getFdId());
			}
			
			if (sysNewsTemplate.getPattern().equals("custom")) {
				if (sysNewsTemplate.getFlowXml() != null) {
					wfTemp.setFdFlowContent(sysNewsTemplate.getFlowXml());
				}
				wfTemp.setFdType(SysWfTemplate.DEFINE_TEMPLATE);
			} else if (sysNewsTemplate.getPattern().equals("other")) {
				String flowChart = wfTemplateService.getContentByTemplateId(sysNewsTemplate.getOtherTemplateId());
				wfTemp.setFdFlowContent(flowChart);
				wfTemp.setFdType(SysWfTemplate.OTHER_TEMPLATE);
			} else {
				SysWfCommonTemplate defaultCommonTemplate = sysWfCommonTemplateService.getDefaultTemplate();
				if (null!=defaultCommonTemplate) {
					wfTemp.setFdType(SysWfTemplate.DEFAULT_TEMPLATE);
					wfTemp.setFdFlowContent(defaultCommonTemplate.getFdFlowContent());
				}else {
					result.setErrorMessage(resourceUtil.getLocaleMessage("请先配置默认模板"));
					return result;
				}
			}
			
			sysNewsTemplateService.save(sysNewsTemplate,wfTemp);
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		}catch(SavingVaildateException e){
			log.error(e.getMessage());
			result.setErrorMessage(e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			log.error(resourceUtil.getLocaleMessage("webController.saveFailure"));
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		
		return result;
	}

	/**
	 * 获取业务系统
	 * @return
	 */
	@RequestMapping(value = "/findBusiSys",method = RequestMethod.POST)
	@ResponseBody
	public List<SysBusiSys> findBusiSys() {
		return sysBusiSysService.findByFdEnabled(Long.valueOf(CommonConstant.AVAILABLE_FLAG));
	}

	/**
	 * 获取通用模板
	 */
	@RequestMapping(value = "/findRootElements")
	@ResponseBody
	public List<SysWfCommonTemplate> findCommonTem() {
		return sysWfCommonTemplateService.findRoot();
	}
	
	/**
	 * 获取根节点
	 */
	@RequestMapping("/categoryMain/findRootElements")
	@ResponseBody
	public List<SysCategoryMain> getRootElements() {
		return categoryMainService.findRootElements();
	}

	/**
	 * 找子节点
	 */
	@RequestMapping("/categoryMain/findSubElements")
	@ResponseBody
	public List<SysCategoryMain> getSubElements(String fdId) {
		return categoryMainService.findTemplateSubElements(fdId);
	}
	
	/**
	 * 获取已经选中节点
	 * @param fdIds
	 * @return
	 */
	@RequestMapping(value = "/categoryMain/findCheckedElements",method = RequestMethod.POST)
	@ResponseBody
	public List<SysCategoryMain> getCheckedElements(String fdIds) {
		List<String> fdTemplateIds = new ArrayList<String>();
		for (String fdId : fdIds.split(",")) {
			fdTemplateIds.add(fdId);
		}
		
		List<SysCategoryMain> result = categoryMainService.findCheckedElements(fdTemplateIds);
		
		return result;
	}

	/**
	 * 物理删除
	 */
	@RequestMapping(value = "/deleteOne",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult deleteOne(String fdId) throws Exception {
		WebMessageResult result = new WebMessageResult();
		try {
			if (sysNewsTemplateService.delete(fdId)) {
				result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
			} else {
				result.setErrorMessage(resourceUtil.getLocaleMessage("cannotDelete.docUsed"));
			}
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		
		return result;
	}
	
	/**
	 * 逻辑删除
	 */
	@RequestMapping(value = "/toEnable",method = RequestMethod.GET)
	@ResponseBody
	public WebMessageResult toEnable(String ids,String status) throws Exception {
		WebMessageResult result = new WebMessageResult();
		try {
			sysNewsTemplateService.enable(ids,status);
			if (CommonConstant.AVAILABLE_FLAG.equals(status)) {
				result.setMessage(resourceUtil.getLocaleMessage("enabledSuccess"));
			}else {
				result.setMessage(resourceUtil.getLocaleMessage("disableSuccess"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (CommonConstant.AVAILABLE_FLAG.equals(status)) {
				result.setErrorMessage(resourceUtil.getLocaleMessage("enabledFailure"));
			}else {
				result.setErrorMessage(resourceUtil.getLocaleMessage("disableFaliure"));
			}
		}

		return result;
	}

	
	/**
	 * 流程模板导出
	 */
	@RequestMapping(value = "/exportTemplate",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public void exportTemplate(String ids,HttpServletResponse response) throws Exception {
		BufferedInputStream br = null;
		OutputStream out = null;
		String fileTrueName = "";
		byte[] buf = new byte[1024];
		int len = 0;
		
		
		try {
			List<SysNewsTemplate> sysNewsTemplates = sysNewsTemplateService.exportTemplate(ids);
			File file = ExcelUtils.exportTemplate("流程模板导出",sysNewsTemplates);
			if (!file.exists()) {
				response.sendError(404,"File not found.");
				
				return;
			}
			
			br = new BufferedInputStream(new FileInputStream(file));
			response.reset();

			fileTrueName = URLEncoder.encode(file.getName(),"utf-8");
			fileTrueName = fileTrueName.replace("+","%20");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition","attachment;filename=" + fileTrueName);

			out = response.getOutputStream();
			while ((len = br.read(buf)) > 0) {
				out.write(buf,0,len);
			}
			
			br.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			try {
				if (br != null) {
					br.close();
				}
				
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
			}
		}
	}
	
	/**
	 * 流程模板导入
	 */
	@PostMapping(value = "/importTemplate")
	@ResponseBody
    public WebMessageResult importTemplate(@RequestParam(value="filename") MultipartFile file) throws Exception {
		WebMessageResult result = new WebMessageResult();
		try {
			// 判断文件是否为空
	        if (file == null) {
	        	result.setErrorMessage(resourceUtil.getLocaleMessage("fileNotEmpty"));
	        	return result;
	        }
	        
	        //获取文件名
	        String fileName = file.getOriginalFilename();
	        
	        if (!ExcelUtils.isSupport(fileName)) {
	        	result.setErrorMessage(resourceUtil.getLocaleMessage("flieFormatNotSupport"));
	        	return result;
	        }
	        
	        if (StringUtils.isEmpty(fileName) || file.getSize() == 0){
	        	result.setErrorMessage(resourceUtil.getLocaleMessage("fileNotEmpty"));
	        	return result;
	        }
	        
	        sysNewsTemplateService.importTemplate(file.getInputStream());
	        
	        result.setMessage(resourceUtil.getLocaleMessage("templateImportSuccess"));
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getLocaleMessage("templateImportFailure"));
		}
        
        return result;
    }
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/getHistoryVersion/findAll",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult getHistoryVersion(@RequestParam String context,@RequestParam String pageVO) throws Exception {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			String templateId = "";
			if (StringUtils.isNotEmpty(context)) {
				templateId = context.replaceAll("\"","");
			}
			Page<SysWfHistoryTemplateV> page = sysWfHistoryTemplateService.findAllByModelId(templateId,pageVo.getPageable());
			result.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}

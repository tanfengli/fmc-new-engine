package com.vispractice.fmc.web.controller.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.vispractice.fmc.business.entities.sys.view.SysVarInfoV;
import com.vispractice.fmc.business.entity.sys.category.SysCategoryMain;
import com.vispractice.fmc.business.entity.sys.config.CompDbcp;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfVarInfo;
import com.vispractice.fmc.business.service.category.ICategoryMainService;
import com.vispractice.fmc.business.service.sys.config.ICompDbcpService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.services.sys.ISysVarInfoVService;
import com.vispractice.fmc.business.services.sys.ISysWfVarInfoService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

/**
 * 变量设置 编 号：<br/>
 * 名 称：VariableSettingController<br/>
 * 描 述：<br/>
 * 完成日期：2016年12月17日 上午11:42:27<br/>
 * 编码作者：zhaoxiu<br/>
 */
@Controller
@RequestMapping("/sys/variableSetting")
public class VariableSettingController extends AbstractController<SysWfVarInfo> {
	@Autowired
	private ISysWfVarInfoService infoService;

	@Autowired
	private ISysVarInfoVService infoVService;

	@Autowired
	private ISysOrgElementService elementService;

	@Autowired
	private ICategoryMainService categoryMainService;

	@Autowired
	private ICompDbcpService compDbcpService; // 数据库名称下拉

	/**
	 * 数据源页面入口
	 */
	@RequestMapping("/list")
	public String operationIndex(Model model) {
		return "sys/variableSetting/variable_list";
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysVarInfoV> findAll(@RequestParam String context, @RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysVarInfoV infoV = mapper.readValue(context, SysVarInfoV.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			return infoVService.findBySearch(infoV, pageVo.getPageable());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 新增或修改
	 */
	@RequestMapping(value = "/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult addSysWfUsage(SysWfVarInfo sysWfVarInfo) {
		WebMessageResult result = new WebMessageResult();
		try {
			// 变量名唯一性验证
			if (null != sysWfVarInfo.getVarName()) {
				String fdId = sysWfVarInfo.getFdId();
				if (null == fdId || "".equals(fdId)) {
					fdId = "0";
				}
				int swv = infoService.findByVarName(sysWfVarInfo.getVarName(), fdId);
				if (swv >= 1) {
					result.setErrorMessage(resourceUtil.getLocaleMessage("variableNameExist"));
					return result;
				}
			}
			// 不通过JDBC连接的变量编码唯一
			if (null != sysWfVarInfo.getVarCode()) {
				String fdId = sysWfVarInfo.getFdId();
				if (null == fdId || "".equals(fdId)) {
					fdId = "0";
				}
				int swv = infoService.findByVarCode(sysWfVarInfo.getVarCode(), fdId);
				if (swv >= 1) {
					result.setErrorMessage(resourceUtil.getLocaleMessage("variableCodeExist"));
					return result;
				}
			}

			if (sysWfVarInfo.getFdId() == null) {
				SysOrgElement element = elementService.findByFdNo(this.getCurrentUser().getFdLoginName());
				sysWfVarInfo.setVarCreatorId(element.getFdId());

				Date utilDate = new Date();
				sysWfVarInfo.setVarCreateTime(new java.util.Date(utilDate.getTime()));
			}
			infoService.save(sysWfVarInfo);
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		return result;
	}

	/**
	 * 查询数据源 实现流程:TODO(描述这个方法的作用)<br/>
	 * 1.XXX<br/>
	 * 
	 * @Title: findSysCategoryMain
	 * @return
	 */
	@RequestMapping(value = "/findCompDbcp", method = RequestMethod.POST)
	@ResponseBody
	public List<CompDbcp> findCompDbcp() {
		return compDbcpService.findAll();
	}

	/**
	 * 查询参数分类 实现流程:TODO(描述这个方法的作用)<br/>
	 * 1.XXX<br/>
	 * 
	 * @Title: findSysCategoryMain
	 * @return
	 */
	@RequestMapping(value = "/findSysCategoryMain", method = RequestMethod.POST)
	@ResponseBody
	public List<SysCategoryMain> findSysCategoryMain() {
		return categoryMainService.findAll();
	}

	@RequestMapping("/deleteOne")
	@ResponseBody
	public WebMessageResult deleteOne(SysWfVarInfo sysWfVarInfo) throws Exception {
		WebMessageResult result = new WebMessageResult();
		try {
			infoService.delete(sysWfVarInfo);
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		return result;
	}

	/**
	 * 
	 * 获取根节点<br/>
	 */
	@RequestMapping("/findRootElements")
	@ResponseBody
	public List<SysWfVarInfo> getRootElements() {
		List<SysWfVarInfo> root = new ArrayList<SysWfVarInfo>();
		SysWfVarInfo var1 = new SysWfVarInfo();
		var1.setFdId("bianliang");
		var1.setVarName("变量");
		var1.setVarCode("");

		SysWfVarInfo var2 = new SysWfVarInfo();
		var2.setFdId("hanshu");
		var2.setVarName("函数");
		var2.setVarCode("");

		// try {
		// (new PropertiesFuncProvider("")).getFunctionScript("");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		root.add(var1);
		root.add(var2);
		// root.addAll(infoService.findAll());
		return root;
	}

	/**
	 * 找子节点
	 */
	@RequestMapping("/findSubElements")
	@ResponseBody
	public List<SysWfVarInfo> getSubElements(String fdId) {
		List<SysWfVarInfo> root = new ArrayList<SysWfVarInfo>();
		if ("bianliang".equals(fdId)) {

			SysWfVarInfo var1 = new SysWfVarInfo();
			var1.setFdId("bianliang-solid");
			var1.setVarName("固定变量");
			var1.setVarCode("");

			SysWfVarInfo var2 = new SysWfVarInfo();
			var2.setFdId("bianliang-defined");
			var2.setVarName("自定义变量");
			var2.setVarCode("");

			root.add(var1);
			root.add(var2);
			return root;
		} else if ("hanshu".equals(fdId)) {
			SysWfVarInfo var1 = new SysWfVarInfo();
			var1.setFdId("OtherFunctionWeb");
			var1.setVarName("列表");
			var1.setVarCode("");

			SysWfVarInfo var2 = new SysWfVarInfo();
			var2.setFdId("DateTimeFunctionWeb");
			var2.setVarName("时间");
			var2.setVarCode("");

			SysWfVarInfo var3 = new SysWfVarInfo();
			var3.setFdId("OrgFunctionWeb");
			var3.setVarName("组织机构");
			var3.setVarCode("");
			root.add(var1);
			root.add(var2);
			root.add(var3);
			return root;
		} else if ("bianliang-defined".equals(fdId)) {

			return infoService.findAll();
		} else if ("bianliang-solid".equals(fdId)) {
			return getSolidVarList();
		} else {
			return PropertiesFuncProvider.getFunctionList(fdId);
		}

		// return root;

	}

	// 获取固定变量列表
	private List<SysWfVarInfo> getSolidVarList() {
		List<SysWfVarInfo> list = new ArrayList<SysWfVarInfo>();
		SysWfVarInfo var1 = new SysWfVarInfo();
		var1.setFdId("fdId");
		var1.setVarName("ID");
		var1.setVarCode("fdId");
		var1.setVarType("solid");

		SysWfVarInfo var2 = new SysWfVarInfo();
		var2.setFdId("fdModelId");
		var2.setVarName("单据类型");
		var2.setVarCode("fdModelId");
		var2.setVarType("solid");

		SysWfVarInfo var3 = new SysWfVarInfo();
		var3.setFdId("applyCode");
		var3.setVarName("单据编号");
		var3.setVarCode("applyCode");
		var3.setVarType("solid");

		SysWfVarInfo var4 = new SysWfVarInfo();
		var4.setFdId("docSubject");
		var4.setVarName("主题");
		var4.setVarCode("docSubject");
		var4.setVarType("solid");

		SysWfVarInfo var5 = new SysWfVarInfo();
		var5.setFdId("docCreateTime");
		var5.setVarName("创建时间");
		var5.setVarCode("docCreateTime");
		var5.setVarType("solid");

		SysWfVarInfo var6 = new SysWfVarInfo();
		var6.setFdId("docContent");
		var6.setVarName("文档内容");
		var6.setVarCode("docContent");
		var6.setVarType("solid");

		SysWfVarInfo var7 = new SysWfVarInfo();
		var7.setFdId("docReadCount");
		var7.setVarName("点击率");
		var7.setVarCode("docReadCount");
		var7.setVarType("solid");

		SysWfVarInfo var8 = new SysWfVarInfo();
		var8.setFdId("fdImportance");
		var8.setVarName("文档重要度");
		var8.setVarCode("fdImportance");
		var8.setVarType("solid");

		SysWfVarInfo var9 = new SysWfVarInfo();
		var9.setFdId("fdDescription");
		var9.setVarName("内容简要");
		var9.setVarCode("fdDescription");
		var9.setVarType("solid");

		SysWfVarInfo var10 = new SysWfVarInfo();
		var10.setFdId("fdModelName");
		var10.setVarName("业务类型");
		var10.setVarCode("fdModelName");
		var10.setVarType("solid");

		SysWfVarInfo var11 = new SysWfVarInfo();
		var11.setFdId("docStatus");
		var11.setVarName("单据状态");
		var11.setVarCode("docStatus");
		var11.setVarType("solid");

		SysWfVarInfo var12 = new SysWfVarInfo();
		var12.setFdId("docCreator");
		var12.setVarName("报账人");
		var12.setVarCode("docCreator");
		var12.setVarType("solid");

		list.add(var1);
		list.add(var2);
		list.add(var3);
		list.add(var4);
		list.add(var5);
		list.add(var6);
		list.add(var7);
		list.add(var8);
		list.add(var9);
		list.add(var10);
		list.add(var11);
		list.add(var12);
		return list;
	}

	/**
	 * 初始化节点信息
	 */
	@RequestMapping("/initData")
	@ResponseBody
	public Map<String, List<SysWfVarInfo>> initData() {
		List<SysWfVarInfo> varList = new ArrayList<SysWfVarInfo>();
		List<SysWfVarInfo> defineList = infoService.findAll();
		List<SysWfVarInfo> solidList = getSolidVarList();
		varList.addAll(solidList);
		varList.addAll(defineList);

		List<SysWfVarInfo> functionList = new ArrayList<SysWfVarInfo>();
		List<SysWfVarInfo> functionList1 = PropertiesFuncProvider.getFunctionList("OtherFunctionWeb");
		List<SysWfVarInfo> functionList2 = PropertiesFuncProvider.getFunctionList("DateTimeFunctionWeb");
		List<SysWfVarInfo> functionList3 = PropertiesFuncProvider.getFunctionList("OrgFunctionWeb");

		functionList.addAll(functionList1);
		functionList.addAll(functionList2);
		functionList.addAll(functionList3);

		Map<String, List<SysWfVarInfo>> result = new HashMap<String, List<SysWfVarInfo>>();
		result.put("varList", varList);
		result.put("functionList", functionList);

		return result;
	}
}

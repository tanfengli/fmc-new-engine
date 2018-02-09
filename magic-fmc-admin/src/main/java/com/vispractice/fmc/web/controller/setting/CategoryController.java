package com.vispractice.fmc.web.controller.setting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.entity.sys.category.SysCategoryMain;
import com.vispractice.fmc.business.entity.sys.category.view.CategoryMain;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.service.category.ICategoryMainService;
import com.vispractice.fmc.business.service.category.impl.CategoryMainServiceImpl;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

@Controller
@RequestMapping("/setting/category")
public class CategoryController extends AbstractController<SysCategoryMain> {
	@Autowired
	private CategoryMainServiceImpl categoryMainServiceImpl;

	@Autowired
	private ICategoryMainService categoryMainService;

	@Autowired
	private ISysOrgElementService sysOrgElementService;

	@RequestMapping("/index")
	public String categoryIndex() {
		return "settings/category/category_index";
	}

	/**
	 * 查询
	 */
	@RequestMapping(value = "/findAll",method = RequestMethod.POST)
	@ResponseBody
	public Page<CategoryMain> findAll(@RequestParam String context,@RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			CategoryMain sysCategoryMain = mapper.readValue(context,CategoryMain.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			return categoryMainService.findBySearch(sysCategoryMain,pageVo.getPageable());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 获取根节点
	 */
	@RequestMapping("/findRootElements")
	@ResponseBody
	public List<SysCategoryMain> getRootElements() {
		return categoryMainService.findRootElements();
	}

	/**
	 * 找子节点
	 */
	@RequestMapping("/findSubElements")
	@ResponseBody
	public List<SysCategoryMain> getSubElements(@RequestParam String fdId) {
		return categoryMainService.findSubElements(fdId);
	}
	
	/**
	 * 获取模板分类组件根节点
	 */
	@RequestMapping("/cate/findRootElements")
	@ResponseBody
	public List<SysCategoryMain> findRootCategorys() {
		return categoryMainService.findRootCategorys();
	}

	/**
	 * 找模板分类组件子节点
	 */
	@RequestMapping("/cate/findSubElements")
	@ResponseBody
	public List<SysCategoryMain> findSubCategorys(String fdId) {
		return categoryMainService.findSubCategorys(fdId);
	}

	/**
	 * 查找编辑
	 */
	@RequestMapping(value = "/findForm",method = RequestMethod.POST)
	@ResponseBody
	public SysCategoryMain findForm(SysCategoryMain sysCategoryMain) {
		try {
			List<SysOrgElement> editor = categoryMainService.findEditorElement(sysCategoryMain.getFdId());
			List<SysOrgElement> reader = categoryMainService.findReaderElement(sysCategoryMain.getFdId());
			sysCategoryMain.setEditorArray(editor);
			sysCategoryMain.setReaderArray(reader);
			
			return sysCategoryMain;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/saveOrUpdateForm",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult saveOrUpdate(@RequestParam String context) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		String id = this.getCurrentUser().getFdId();
		try {
			SysCategoryMain sysCategoryMain = mapper.readValue(context,SysCategoryMain.class);
			sysCategoryMain = categoryMainService.save(sysCategoryMain,id);
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
			result.setData(sysCategoryMain);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		
		return result;
	}
	
	/**
	 * 删除模板类别
	 */
	@RequestMapping(value = "/deleteNode",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult deleteNode(SysCategoryMain sysCategoryMain) {
		WebMessageResult result = new WebMessageResult();
		String fdId = sysCategoryMain.getFdId();
		try {
			if (categoryMainService.deleteByFdId(fdId)) {
				result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
			} else {
				result.setErrorMessage(resourceUtil.getLocaleMessage("categoryCannotDelete"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure"));
		}
		
		return result;
	}
}
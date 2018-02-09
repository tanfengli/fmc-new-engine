package com.vispractice.fmc.web.controller.sys;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.vispractice.fmc.business.base.constrant.SysOrgConstant;
import com.vispractice.fmc.business.base.domain.SysOrgElementForm;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementAllPathVService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgPersonService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgPostPersonService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.PageVO;

@Controller
@RequestMapping("/sys/orgElement")
public class SysOrgElementController extends AbstractController<SysOrgElement> {

	@Autowired
	private ISysOrgElementService sysOrgElementService;

	@Autowired
	private ISysOrgPostPersonService sysOrgPostPersonService;

	@Autowired
	private ISysOrgPersonService sysOrgPersonService;

	@Autowired
	private ISysOrgElementAllPathVService allPathVService;

	@RequestMapping("/list")
	public String ElementList() {
		return "sys/orgElement/sys_org_element";
	}

	/**
	 * 
	 * 获取根节点<br/>
	 */
	@RequestMapping("/findRootElements")
	@ResponseBody
	public List<SysOrgElement> getRootElements() {
		return sysOrgElementService.findRootElements();
	}

	@RequestMapping("/findSubElements")
	@ResponseBody
	public List<SysOrgElement> getSubElements(String fdId) {
		return sysOrgElementService.findSubElements(fdId);
	}

	/**
	 * 根据父节点和 fd_org_type 查询 1：机构 2：部门 4：岗位 8：人员
	 * 
	 * @Title: getSubElements
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/findElements")
	@ResponseBody
	public List<SysOrgElement> findElements(@RequestParam String context) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			List<SysOrgElement> elements = new ArrayList<SysOrgElement>();
			SysOrgElementForm form = mapper.readValue(context, SysOrgElementForm.class);
			elements = sysOrgElementService.findElements(form.getNodeId(),form.getCheckedValue());
			
			return elements;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysOrgElement> findAll(@RequestParam String context, @RequestParam String pageVO) {

		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysOrgElementForm sysOrgElementForm = mapper.readValue(context, SysOrgElementForm.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			return sysOrgElementService.findHierarchyElements(sysOrgElementForm, pageVo.getPageable());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/findByFdNameAndType", method = RequestMethod.POST)
	@ResponseBody
	public List<SysOrgElement> findByFdNameAndType(@RequestParam String context) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysOrgElementForm sysOrgElementForm = mapper.readValue(context, SysOrgElementForm.class);
			return sysOrgElementService.findFdNameElements(sysOrgElementForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/findFdNameType", method = RequestMethod.POST)
	@ResponseBody
	public List<SysOrgElement> findFdNameType(@RequestParam String context) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysOrgElementForm sysOrgElementForm = mapper.readValue(context, SysOrgElementForm.class);
			return sysOrgElementService.findFdNameType(sysOrgElementForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/findByFdName")
	@ResponseBody
	public List<SysOrgElement> findByFdName(@RequestParam String fdName, @RequestParam Long fdOrgType) {
		return sysOrgElementService.findByName(fdName, fdOrgType);
	}

	/**
	 * 新增或修改
	 */
	@RequestMapping(value = "/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult add(String context) {
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysOrgElement sysOrgElement = mapper.readValue(context, SysOrgElement.class);

			int type = Integer.parseInt(Long.toString(sysOrgElement.getFdOrgType()));
			if (null == sysOrgElement.getFdId()) {
				sysOrgElement.setFdIsAbandon(0L);
				SysOrgElement element = sysOrgElementService.findByFdNo(this.getCurrentUser().getFdLoginName());
				sysOrgElement.setFdCateid(element.getFdId());
				sysOrgElement.setFdCreateTime(new Date(System.currentTimeMillis()));
				sysOrgElement.setFdIsLeaf(1L);
			}
			sysOrgElement.setFdAlterTime(new Date(System.currentTimeMillis()));
			sysOrgElement.setFdNamePinyin(sysOrgElement.getFdNo());
	
			// 机构及 部门
			if (type == SysOrgConstant.ORG_TYPE_ORG || type == SysOrgConstant.ORG_TYPE_DEPT) {				
				sysOrgElementService.save(sysOrgElement);
			}
			
			// 岗位
			if (type == SysOrgConstant.ORG_TYPE_POST) {
				sysOrgElementService.save(sysOrgElement);
				// 新增人员，部门关系表
				if (null != sysOrgElement.getFdPersonId()) {
					List<String> fdPersonId = StringToList(sysOrgElement.getFdPersonId(),";");
					sysOrgPostPersonService.deleteByPostId(sysOrgElement.getFdId());
					for (String personId : fdPersonId) {
						if ("" != personId && "" != sysOrgElement.getFdId()) {
							sysOrgPostPersonService.add(personId,sysOrgElement.getFdId());
						}
					}
				}
			}
			// 人员
			if (type == SysOrgConstant.ORG_TYPE_PERSON) {
				sysOrgElement.setFdNo(sysOrgElement.getFdLoginName());
				String pinyin = "";
				try {
					pinyin = PinyinHelper.convertToPinyinString(sysOrgElement.getFdName(), "",
							PinyinFormat.WITHOUT_TONE);// 汉字转拼音
				} catch (PinyinException e) {
					e.printStackTrace();
				}
				sysOrgElement.setFdNamePinyin(pinyin);
				sysOrgElementService.save(sysOrgElement);
				
				if (null != sysOrgElement.getFdPostIds()) {
					sysOrgPostPersonService.deleteByPersonId(sysOrgElement.getFdId());
					List<String> fdPostIds = StringToList(sysOrgElement.getFdPostIds(),";");
					for (String postId : fdPostIds) {
						if ("" != postId && "" != sysOrgElement.getFdId()) {
							sysOrgPostPersonService.add(sysOrgElement.getFdId(),postId);
						}
					}
				}
				
				// 人员表修改
				SysOrgPerson sysOrgPerson = sysOrgPersonService.get(sysOrgElement.getFdId());
				if (null==sysOrgPerson) {
					sysOrgPerson = new SysOrgPerson();
					sysOrgPerson.setFdPassword("c4ca4238a0b923820dcc509a6f75849b");
				}
				sysOrgPerson.setFdId(sysOrgElement.getFdId());
				sysOrgPerson.setFdEmail(sysOrgElement.getFdEmail()); // 邮件地址
				sysOrgPerson.setFdMobileNo(sysOrgElement.getFdMobileNo());
				sysOrgPerson.setFdWorkPhone(sysOrgElement.getFdWorkPhone());// 办公电话
				sysOrgPerson.setFdLoginName(sysOrgElement.getFdLoginName());// 登录名
				sysOrgPerson.setFdDefaultLang(sysOrgElement.getFdDefaultLang());// 默认语言
				sysOrgPersonService.save(sysOrgPerson);
			}
			// 设置层级id
			if (StringUtils.isBlank(sysOrgElement.getFdHierarchyId())){
				if (StringUtils.isNotBlank(sysOrgElement.getFdParentid())) {
					SysOrgElement parentElement = sysOrgElementService.findByFdId(sysOrgElement.getFdParentid());
					sysOrgElement.setFdHierarchyId(parentElement.getFdHierarchyId()+sysOrgElement.getFdId()+"x");
				}else {
					sysOrgElement.setFdHierarchyId("x"+sysOrgElement.getFdId()+"x");
				}
				sysOrgElementService.save(sysOrgElement);
			}
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
			result.setData(sysOrgElement);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		return result;
	}

	/**
	 * 字符串转数组
	 */
	public static List<String> StringToList(String obj) {
		String[] array = obj.split(";");
		List<String> list = new ArrayList<String>();
		for (String str : array) {
			list.add(str);
		}
		return list;
	}
	/**
	 * 字符串转数组
	 */
	public static List<String> StringToList(String obj,String splitString) {
		String[] array = obj.split(splitString);
		List<String> list = new ArrayList<String>();
		for (String str : array) {
			list.add(str);
		}
		return list;
	}

	/**
	 * 把list转换为一个用分号分隔的字符串
	 */
	public static String listToString(List<String> list) {
		StringBuilder sb = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i < list.size() - 1) {
					sb.append(list.get(i) + ";");
				} else {
					sb.append(list.get(i));
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 查询对象
	 */
	@RequestMapping(value = "/getFormItem", method = RequestMethod.GET)
	@ResponseBody
	public SysOrgElement getFormItem(String context) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysOrgElement sysOrgElement = mapper.readValue(context, SysOrgElement.class);
			int type = Integer.parseInt(Long.toString(sysOrgElement.getFdOrgType()));
			// 机构,部门,岗位,人员 入口
			sysOrgElement = sysOrgElementService.findByFdId(sysOrgElement.getFdId());

			// 机构,部门
			if (type == SysOrgConstant.ORG_TYPE_ORG || type == SysOrgConstant.ORG_TYPE_DEPT) {
				// 上级机构,上级部门
				if (null != sysOrgElement.getFdParentorgid()) {
					SysOrgElement org = sysOrgElementService.findByFdId(sysOrgElement.getFdParentorgid());
					sysOrgElement.setFdPersonName(org.getFdName());
				}
				// 机构领导,部门领导
				if (null != sysOrgElement.getFdThisLeaderid()) {
					SysOrgElement orgLeader = sysOrgElementService.findByFdId(sysOrgElement.getFdThisLeaderid());
					sysOrgElement.setFdThisLeaderName(orgLeader.getFdName());
				}
				// 上级领导
				if (null != sysOrgElement.getFdSuperLeaderid()) {
					SysOrgElement superLeader = sysOrgElementService.findByFdId(sysOrgElement.getFdSuperLeaderid());
					sysOrgElement.setFdSuperLeaderName(superLeader.getFdName());
				}
			}
			// 岗位
			if (type == SysOrgConstant.ORG_TYPE_POST) {
				// 所在部门
				if (null != sysOrgElement.getFdParentid()) {
					SysOrgElement dept = sysOrgElementService.findByFdId(sysOrgElement.getFdParentid());
					sysOrgElement.setFdDeptName(dept.getFdName());
				}
				// 岗位领导
				if (null != sysOrgElement.getFdThisLeaderid()) {
					SysOrgElement postLeader = sysOrgElementService.findByFdId(sysOrgElement.getFdThisLeaderid());
					sysOrgElement.setFdThisLeaderName(postLeader.getFdName());
				}
				// 根据中间表查询岗位关联人员
				List<String> listStr = sysOrgPostPersonService.findFdPersonId(sysOrgElement.getFdId());
				if (null != listStr && listStr.size() > 0) {
					String fdPersonId = listToString(listStr);
					sysOrgElement.setFdPersonId(fdPersonId);
					StringBuilder sb = new StringBuilder();
					for (String fdPerson : listStr) {
						SysOrgElement soe = sysOrgElementService.findByFdId(fdPerson);
						sb.append(soe.getFdName() + ";");
					}
					/*List<SysOrgElement> list = sysOrgElementService.findByFdIdIn(listStr);
					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							if (i < list.size() - 1) {
								sb.append(list.get(i).getFdName() + ";");
							} else {
								sb.append(list.get(i).getFdName());
							}
						}
					}*/
					sysOrgElement.setFdPersonName(sb.toString().substring(0,sb.toString().length() - 1));
				}

			}

			// 人员
			if (type == SysOrgConstant.ORG_TYPE_PERSON) {
				// 查询人员对象
				SysOrgPerson sysOrgPerson = sysOrgPersonService.get(sysOrgElement.getFdId());
				sysOrgElement.setFdEmail(sysOrgPerson.getFdEmail()); // 邮件地址
				sysOrgElement.setFdMobileNo(sysOrgPerson.getFdMobileNo());
				sysOrgElement.setFdWorkPhone(sysOrgPerson.getFdWorkPhone());// 办公电话
				sysOrgElement.setFdLoginName(sysOrgPerson.getFdLoginName());// 登录名
				sysOrgElement.setFdDefaultLang(sysOrgPerson.getFdDefaultLang());// 默认语言
				// 所在部门
				if (null != sysOrgElement.getFdParentid()) {
					SysOrgElement dept = sysOrgElementService.findByFdId(sysOrgElement.getFdParentid());
					sysOrgElement.setFdDeptName(dept.getFdName());
				}
				// 根据中间表查询岗位关联人员
				List<String> listStr = sysOrgPostPersonService.findFdPostId(sysOrgElement.getFdId());
				if (null != listStr && listStr.size() > 0) {
//					String fdPersonId = listToString(listStr);
//					sysOrgElement.setFdPostIds(fdPersonId);
					List<SysOrgElement> list = sysOrgElementService.findByFdIdIn(listStr);
					StringBuilder sb = new StringBuilder();
					StringBuilder sb_ids = new StringBuilder();
					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							if (i < list.size() - 1) {
								sb_ids.append(list.get(i).getFdId() + ";");
								sb.append(list.get(i).getFdName() + ";");
							} else {
								sb_ids.append(list.get(i).getFdId());
								sb.append(list.get(i).getFdName());
							}
						}
					}
					sysOrgElement.setFdPostIds(sb_ids.toString());
					sysOrgElement.setFdPostNames(sb.toString());
				}
			}

			return sysOrgElement;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 实现流程:查询组织架构路径描述<br/>
	 * @Title: findsysOrgElement
	 * @param fdId
	 * @param fdOrgType
	 * @return
	 */
	@RequestMapping("/getElementPathDescription")
	@ResponseBody
	public WebMessageResult getElementDescription(@RequestParam String fdId) {
		WebMessageResult result = new WebMessageResult();
		try {
			result.setData(allPathVService.getDescription(fdId));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("获取描述时出错。");
		}
		return result;
	}
	
	/**
	 * 实现流程:查询组织架构层级关系<br/>
	 * @Title: findsysOrgElement
	 * @param fdId
	 * @param fdOrgType
	 * @return
	 */
	@RequestMapping("/getElementAllPath")
	@ResponseBody
	public WebMessageResult getElementAllPath(@RequestParam String fdId) {
		WebMessageResult result = new WebMessageResult();
		try {
			result.setData(allPathVService.getElementAllPath(fdId));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("获取组织架构层级关系时出错。");
		}
		return result;
	}

	@RequestMapping("/deleteNode")
	@ResponseBody
	public WebMessageResult deleteNode(SysOrgElement sysOrgElement) {

		WebMessageResult result = new WebMessageResult();
		try {
			sysOrgElementService.deleteByFdId(sysOrgElement.getFdId());
			result.setMessage(resourceUtil.getLocaleMessage("webController.deleteSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.deleteFailure")+e.getMessage());
		}
		return result;

	}
	
	@RequestMapping("/findOne")
	@ResponseBody
	public SysOrgElement findOne(@RequestParam String fdId) {
		 SysOrgElement element = sysOrgElementService.findByFdId(fdId);
		 return element;
	}
	
	@RequestMapping("/findByIds")
	@ResponseBody
	public List<SysOrgElement> findByIds(@RequestParam String ids) {
		 List<String> idList = new ArrayList<String>();
		 String[] idArr = ids.split(";");
		 for (String string : idArr) {
			 idList.add(string);
		}
		 List<SysOrgElement> elementList = sysOrgElementService.findByFdIdIn(idList);
		 return elementList;
	}
	
	/**
	 * 获取当前登录用户的组织架构实体
	 * @Title: getCurrentElement 
	 * @return
	 */
	@RequestMapping("/getCurrentElement")
	@ResponseBody
	public WebMessageResult getCurrentElement() {
		String fdId = this.getCurrentUser().getFdId();
		WebMessageResult result = new WebMessageResult();
		SysOrgElement element = null;
		try {
			element = sysOrgElementService.findByFdId(fdId);
			result.setData(element);
		} catch (Exception e) {
			result.setErrorMessage(resourceUtil.getLocaleMessage("error.getCurrentUser"));
			e.printStackTrace();
		}
		return result;
	}
	
}

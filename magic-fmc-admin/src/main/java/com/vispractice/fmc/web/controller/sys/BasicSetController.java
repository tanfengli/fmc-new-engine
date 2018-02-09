package com.vispractice.fmc.web.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.entity.sys.config.SysAppConfig;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.service.sys.config.ISysAppConfigService;
import com.vispractice.fmc.web.domain.sys.SysAppConfigForm;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;

/**
 * 基本设置 编 号：<br/>
 * 名 称：BasicSetController<br/>
 * 描 述：<br/>
 * 完成日期：2016年12月21日 下午2:38:46<br/>
 * 编码作者：zhaoxiu<br/>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/sys/basicSet")
public class BasicSetController extends AbstractController{

	@Autowired
	private ISysAppConfigService appConfigService;

	/**
	 * 数据源页面入口
	 */
	@RequestMapping("/list")
	public String goIndex(Model model) {
		return "sys/basicSet/basicSet_list";
	}

	@RequestMapping(value = "/findForm", method = RequestMethod.POST)
	@ResponseBody
	private SysAppConfigForm findForm() {
		List<SysAppConfig> list = appConfigService.findList();
		Map<String, String> map = new HashMap<String, String>();
		for (SysAppConfig sysAppConfig : list) {
			map.put(sysAppConfig.getFdField(), sysAppConfig.getFdValue());
		}
		SysAppConfigForm sacf = new SysAppConfigForm();
		sacf.setDefaultNotifyType(map.get("defaultNotifyType"));
		if (null != map.get("defaultNotifyType")) {
			List<String> defaultNotifyTypeSel = StringToList(map.get("defaultNotifyType"), ",");
			sacf.setDefaultNotifyTypeSel(defaultNotifyTypeSel);
		}
		sacf.setNotifyCrashTargetIds(map.get("notifyCrashTargetIds"));
		sacf.setNotifyExpire(map.get("notifyExpire"));
		sacf.setNodeNameSelectItem(map.get("nodeNameSelectItem"));
		sacf.setSystemNotifyType(map.get("systemNotifyType"));
		if (null != map.get("systemNotifyType")) {
			List<String> systemNotifyTypeSel = StringToList(map.get("systemNotifyType"), ",");
			sacf.setSystemNotifyTypeSel(systemNotifyTypeSel);
		}
		sacf.setNotifyCrashTargetNames(map.get("notifyCrashTargetNames"));
		sacf.setNotifyTargetSubmit(map.get("notifyTargetSubmit"));
		sacf.setNotifyTargetAuthor(map.get("notifyTargetAuthor"));
		sacf.setThreadPoolSize(map.get("threadPoolSize"));

		return sacf;
	}

	@RequestMapping(value = "/saveOrUpdateForm", method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult add(@RequestParam String context) {// SysWfOperMain
																	// operMain
		WebMessageResult result = new WebMessageResult();
		try {
			ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
			SysAppConfigForm appConfigForm = mapper.readValue(context, SysAppConfigForm.class);
			Map<String, String> m = new HashMap<String, String>();
			List<String> defaultNotifyTypeSel = appConfigForm.getDefaultNotifyTypeSel();
			String deftype = listToString(defaultNotifyTypeSel, ",");
			m.put("defaultNotifyType", deftype);
			List<SysOrgElement> elements = appConfigForm.getEditorArray();
			StringBuilder sbIds = new StringBuilder();
			StringBuilder sbNames = new StringBuilder();
			if (null != elements && elements.size() > 0) {
				for (int i = 0; i < elements.size(); i++) {
					if (i < elements.size() - 1) {
						sbIds.append(elements.get(i).getFdId() + ";");
						sbNames.append(elements.get(i).getFdName() + ";");
					} else {
						sbIds.append(elements.get(i).getFdId());
						sbNames.append(elements.get(i).getFdName());
					}
				}
				m.put("notifyCrashTargetIds", sbIds.toString());
				m.put("notifyCrashTargetNames", sbNames.toString());
			} else {
				m.put("notifyCrashTargetIds", appConfigForm.getNotifyCrashTargetIds());
				m.put("notifyCrashTargetNames", appConfigForm.getNotifyCrashTargetNames());
			}

			m.put("notifyExpire", appConfigForm.getNotifyExpire());
			m.put("nodeNameSelectItem", appConfigForm.getNodeNameSelectItem());
			List<String> systemNotifyTypeSel = appConfigForm.getSystemNotifyTypeSel();
			String systype = listToString(systemNotifyTypeSel, ",");
			m.put("systemNotifyType", systype);
			m.put("notifyTargetSubmit", appConfigForm.getNotifyTargetSubmit());
			m.put("notifyTargetAuthor", appConfigForm.getNotifyTargetAuthor());
			m.put("threadPoolSize", appConfigForm.getThreadPoolSize());

			for (Iterator it = m.keySet().iterator(); it.hasNext();) {
				String field = (String) it.next();
				String value = (String) m.get(field);
				SysAppConfig sysAppConfig = new SysAppConfig();
				sysAppConfig.setFdField(field);
				sysAppConfig.setFdValue(value);
				// 删除所有
				appConfigService.delete(field);

				appConfigService.save(sysAppConfig);
			}
			result.setMessage(resourceUtil.getLocaleMessage("webController.saveSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage(resourceUtil.getLocaleMessage("webController.saveFailure"));
		}
		return result;
	}

	/**
	 * @Description:把list转换为一个用逗号分隔的字符串
	 */
	public static String listToString(List<String> list, String sign) {
		StringBuilder sb = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i < list.size() - 1) {
					sb.append(list.get(i) + sign);
				} else {
					sb.append(list.get(i));
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 字符串转数组
	 */
	public static List<String> StringToList(String obj, String sign) {
		String[] array = obj.split(sign);
		List<String> list = new ArrayList<String>();
		for (String str : array) {
			list.add(str);
		}
		return list;
	}

	/**
	 * 查询所有的基本操作列表
	 */
	@RequestMapping("/findSysAppConfig")
	@ResponseBody
	public SysAppConfig findSysAppConfig(String fdField) {

		return appConfigService.findSysAppConfig(fdField);
	}
}

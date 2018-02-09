package com.vispractice.fmc.web.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;
import com.vispractice.fmc.business.service.sys.org.ISysOrgPersonService;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.AbstractController;
import com.vispractice.fmc.web.utils.vo.GridController;
import com.vispractice.fmc.web.utils.vo.PageVO;

@Controller
@RequestMapping("/sys/orgPerson")
public class SysOrgPersonController extends AbstractController<SysOrgPerson> implements GridController {

	@Autowired
	private ISysOrgPersonService sysOrgPersonService;

	@RequestMapping("/list")
	public String personList(Model model) {
		model.addAttribute("user", this.getCurrentUser().getFdLoginName());
		return "sys/orgPerson/sys_org_person";
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysOrgPerson> findAll(@RequestParam String context, @RequestParam String pageVO) {

		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
		// false);
		try {
			SysOrgPerson sysOrgPerson = mapper.readValue(context, SysOrgPerson.class);
			PageVO pageVo = mapper.readValue(pageVO, PageVO.class);
			if (sysOrgPerson.getFdId() == "")
				sysOrgPerson.setFdId(null);
			if (sysOrgPerson.getFdLoginName() == "")
				sysOrgPerson.setFdLoginName(null);
			return sysOrgPersonService.findByExample(sysOrgPerson, pageVo.getPageable());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@RequestMapping("/deleteOne")
	public void deleteOne(@RequestParam String context) throws Exception {
		@SuppressWarnings("unused")
		SysOrgPerson sysOrgPerson = (SysOrgPerson) coventJsonToEntity(context, SysOrgPerson.class);
		// sysOrgPersonService.delete(sysOrgPerson.getFdId());
	}

}

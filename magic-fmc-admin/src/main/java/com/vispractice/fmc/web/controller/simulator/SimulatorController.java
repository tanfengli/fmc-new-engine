package com.vispractice.fmc.web.controller.simulator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.services.simulator.ISimulatorService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;

/**
 * 
 * 名  称：模拟器控制器
 * 描  述：SimulatorController.java
 * 完成日期：2017年8月9日 上午9:41:30
 * 编码作者："LaiJiashen"
 */

@Controller
@RequestMapping("/simulator")
public class SimulatorController {
	
	@Autowired
	private ISimulatorService simulatorService;
	
	@RequestMapping("/index")
	public String simulatorIndex(){
		
		return "simulator/simulator_index";
	}
	
	/**
	 * 模拟器计算
	 * @Title: startSimulate 
	 * @param personList
	 * @param simulatePerson
	 * @return 计算结果String
	 */
	@ResponseBody
	@RequestMapping("/startSimulate")
	public WebMessageResult startSimulate(String personList,String simulatePerson){
		WebMessageResult result = new WebMessageResult();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysOrgElement simulateUser = mapper.readValue(simulatePerson, SysOrgElement.class);
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, SysOrgElement.class);
			List<SysOrgElement> personList2 = mapper.readValue(personList, javaType);
			String simulateResult = simulatorService.startSimulate(personList2, simulateUser);
			result.setCode(WebMessageResult.SUCCESS);
			result.setData(simulateResult);
		} catch (Exception e) {
			result.setCode(WebMessageResult.ERROR);
			result.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	

}

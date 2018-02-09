//package com.vispractice.fmc.web.controller.simulator;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.vispractice.fmc.business.base.simulator.SimulateForm;
//import com.vispractice.fmc.business.services.simulator.IWorkflowSimulateService;
//
//@Controller
//@RequestMapping("/sys/simulate")
//public class WorkflowSimulateController {
//
//	@Autowired
//	private IWorkflowSimulateService simulateService;
//	/**
//	 * 数据源页面入口
//	 */
//	@RequestMapping("/index")
//	public String goIndex(Model model) {
//		return "sys/simulate/simulate";
//	}
//	
//	@RequestMapping(value = "/simulate")
//	@ResponseBody
//	public void simulate(@RequestParam String context) {// SysWfOperMain
//																	// operMain
////		Map<String, String> map = new HashMap<String, String>();
//		try {
//			ObjectMapper mapper = new ObjectMapper();
//			SimulateForm form = mapper.readValue(context, SimulateForm.class);
////			SimulateForm form = new SimulateForm();
////			form.setHttp(http);
////			form.setThreadNum(threadNum);
////			form.setFlag(flag);
//			simulateService.start(form);
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//		
////
////	@RequestMapping(value = "/start", method = RequestMethod.POST)
////	@ResponseBody
////	private SysAppConfigForm findForm() {
////		
////		return sacf;
////	}
//
//}

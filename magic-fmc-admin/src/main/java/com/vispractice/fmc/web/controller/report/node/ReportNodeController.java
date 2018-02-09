package com.vispractice.fmc.web.controller.report.node;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.base.utils.ExcelUtils;
import com.vispractice.fmc.business.entity.report.node.SysReportNode;
import com.vispractice.fmc.business.service.report.node.ISysReportNodeService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.PageVO;

@Controller
@RequestMapping("/report/node")
public class ReportNodeController {
	@Autowired
	private ISysReportNodeService sysReportNodeService;
	
	@RequestMapping("/limitdate")
	public String limit() {
		return "report/node/limitdate/sys_report_node";
	}
	
	
	@RequestMapping("/overdue")
	public String overdue() {
		return "report/node/overdue/sys_report_node";
	}

	/**
	 * 查询流程单据
	 */
	@RequestMapping(value = "/findAll",method = RequestMethod.POST)
	@ResponseBody
	public Page<SysReportNode> findAll(@RequestParam String context,@RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysReportNode sysReportNode = mapper.readValue(context,SysReportNode.class);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			Page<SysReportNode> page = sysReportNodeService.searchSysReportNode(sysReportNode,pageVo.getPageable());
			
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/import",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult importSysReportNode(String context) {
		WebMessageResult result = new WebMessageResult();
		
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysReportNode sysReportNode = mapper.readValue(context,SysReportNode.class);
			sysReportNodeService.importSysReportNode(sysReportNode.getFdStartDate(),sysReportNode.getFdEndDate());
			
			result.setMessage("初始化样本数据成功.");;
		} catch (Exception e) {
			result.setErrorMessage("初始化样本数据失败.");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/overdue/export",method = RequestMethod.POST)
	@ResponseBody
	public void exportOverdueSysReportNode(@RequestParam String context,@RequestParam String pageVO,HttpServletResponse response) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		
		BufferedInputStream br = null;
		OutputStream out = null;
		String fileTrueName = "";
		byte[] buf = new byte[1024];
		int len = 0;
		
		try {
			SysReportNode sysReportNode = mapper.readValue(context,SysReportNode.class);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			Page<SysReportNode> page = sysReportNodeService.searchSysReportNode(sysReportNode,pageVo.getPageable());
			
			File file = this.exportOverdueSysReportNodeDate("按节点统计流程超期",page.getContent());
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
				e.printStackTrace();
			}
		}
	}
	
	private File exportOverdueSysReportNodeDate(String title,List<SysReportNode> sysReportNodes) {
		File file = null;
		WritableWorkbook book = null;
		
		try {
			file = new File(ExcelUtils.getExcelName(title));
			book = Workbook.createWorkbook(file);
			
			WritableSheet sheet = book.createSheet("按节点统计流程超期",0);
			sheet.setColumnView(0,30);
			sheet.setColumnView(1,30);
			sheet.setColumnView(2,30);
			sheet.setColumnView(3,30);
			sheet.setColumnView(4,30);
			sheet.setColumnView(5,20);
			
			//在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			WritableCellFormat writableCellFormat = ExcelUtils.getDefaultTitleFormat();
			String[] columnHead = new String[8];
			columnHead[0] = "序号";
			columnHead[1] = "流程节点";
			columnHead[2] = "流程模板";
			columnHead[3] = "预警设置时长";
			columnHead[4] = "节点超期数";
			columnHead[5] = "平均超期时长";
			int rowIndex = 0;
			int columnIndex = 0;
			for (String columnName : columnHead) {
				WritableCell headTitle = ExcelUtils.createCell(columnIndex++,rowIndex,columnName,writableCellFormat);
				sheet.addCell(headTitle);
			}
			
			
			//写入数据
			if (null != sysReportNodes && sysReportNodes.size() > 0) {
				for (SysReportNode sysReportNode : sysReportNodes) {
					rowIndex++;
					columnIndex = 0;
					
					WritableCell bodyContent1 = ExcelUtils.createCell(columnIndex++,rowIndex,rowIndex,ExcelUtils.getDataFormat(rowIndex));
					sheet.addCell(bodyContent1);
						
					WritableCell bodyContent2 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportNode.getFdFactNodeName(),ExcelUtils.getDataFormat(sysReportNode.getFdFactNodeName()));
					sheet.addCell(bodyContent2);
					
					WritableCell bodyContent3 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportNode.getFdTemplateName(),ExcelUtils.getDataFormat(sysReportNode.getFdTemplateName()));
					sheet.addCell(bodyContent3);
					
					WritableCell bodyContent4 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportNode.getFdAlertTime(),ExcelUtils.getDataFormat(sysReportNode.getFdAlertTime()));
					sheet.addCell(bodyContent4);
					
					WritableCell bodyContent5 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportNode.getFdOverdueNumber(),ExcelUtils.getDataFormat(sysReportNode.getFdOverdueNumber()));
					sheet.addCell(bodyContent5);
					
					WritableCell bodyContent6 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportNode.getFdOverdueAvg(),ExcelUtils.getDataFormat(sysReportNode.getFdOverdueAvg()));
					sheet.addCell(bodyContent6);
				}
			}
			
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (book != null) {
					book.write();
					book.close();
				}
			} catch (Exception e) {
			}
		}

		return file;
	}
	
	@RequestMapping(value = "/limitdate/export",method = RequestMethod.POST)
	@ResponseBody
	public void exportLimitdateSysReportNode(@RequestParam String context,@RequestParam String pageVO,HttpServletResponse response) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		
		BufferedInputStream br = null;
		OutputStream out = null;
		String fileTrueName = "";
		byte[] buf = new byte[1024];
		int len = 0;
		
		try {
			SysReportNode sysReportNode = mapper.readValue(context,SysReportNode.class);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			Page<SysReportNode> page = sysReportNodeService.searchSysReportNode(sysReportNode,pageVo.getPageable());
			
			File file = this.exportLimitdateSysReportNodeData("按节点统计流程时效",page.getContent());
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
				e.printStackTrace();
			}
		}
	}
	
	private File exportLimitdateSysReportNodeData(String title,List<SysReportNode> sysReportNodes) {
		File file = null;
		WritableWorkbook book = null;
		
		try {
			file = new File(ExcelUtils.getExcelName(title));
			book = Workbook.createWorkbook(file);
			
			WritableSheet sheet = book.createSheet("按节点统计流程时效",0);
			sheet.setColumnView(0,30);
			sheet.setColumnView(1,30);
			sheet.setColumnView(2,30);
			sheet.setColumnView(3,30);
			
			//在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			WritableCellFormat writableCellFormat = ExcelUtils.getDefaultTitleFormat();
			String[] columnHead = new String[8];
			columnHead[0] = "序号";
			columnHead[1] = "流程节点";
			columnHead[2] = "所属流程模板";
			columnHead[3] = "平均处理时长";
			int rowIndex = 0;
			int columnIndex = 0;
			for (String columnName : columnHead) {
				WritableCell headTitle = ExcelUtils.createCell(columnIndex++,rowIndex,columnName,writableCellFormat);
				sheet.addCell(headTitle);
			}
			
			
			//写入数据
			if (null != sysReportNodes && sysReportNodes.size() > 0) {
				for (SysReportNode sysReportNode : sysReportNodes) {
					rowIndex++;
					columnIndex = 0;
					
					WritableCell bodyContent1 = ExcelUtils.createCell(columnIndex++,rowIndex,rowIndex,ExcelUtils.getDataFormat(rowIndex));
					sheet.addCell(bodyContent1);
						
					WritableCell bodyContent2 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportNode.getFdFactNodeName(),ExcelUtils.getDataFormat(sysReportNode.getFdFactNodeName()));
					sheet.addCell(bodyContent2);
					
					WritableCell bodyContent3 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportNode.getFdTemplateName(),ExcelUtils.getDataFormat(sysReportNode.getFdTemplateName()));
					sheet.addCell(bodyContent3);
					
					WritableCell bodyContent4 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportNode.getFdAvgTime(),ExcelUtils.getDataFormat(sysReportNode.getFdAvgTime()));
					sheet.addCell(bodyContent4);
				}
			}
			
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (book != null) {
					book.write();
					book.close();
				}
			} catch (Exception e) {
			}
		}

		return file;
	}
}
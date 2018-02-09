package com.vispractice.fmc.web.controller.report.template;

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
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.business.base.utils.ExcelUtils;
import com.vispractice.fmc.business.entity.report.template.SysReportTemplate;
import com.vispractice.fmc.business.service.report.template.ISysReportTemplateService;
import com.vispractice.fmc.web.result.WebMessageResult;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;
import com.vispractice.fmc.web.utils.vo.PageVO;

@Slf4j
@Controller
@RequestMapping("/report/template")
public class ReportTemplateController {
	@Autowired
	private ISysReportTemplateService sysReportTemplateService;
	
	@RequestMapping("/limitdate")
	public String limit() {
		return "report/template/limitdate/sys_news_main";
	}
	
	
	@RequestMapping("/overdue")
	public String overdue() {
		return "report/template/overdue/sys_news_main";
	}

	/**
	 * 查询流程单据
	 */
	@RequestMapping(value = "/findAll",method = RequestMethod.POST)
	@ResponseBody
	public Page<SysReportTemplate> findAll(@RequestParam String context,@RequestParam String pageVO) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysReportTemplate sysReportTemplate = mapper.readValue(context,SysReportTemplate.class);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			Page<SysReportTemplate> page = sysReportTemplateService.searchSysReportTemplate(sysReportTemplate,pageVo.getPageable());
			
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/import",method = RequestMethod.POST)
	@ResponseBody
	public WebMessageResult importSysReportTemplate(String context) {
		WebMessageResult result = new WebMessageResult();
		
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		try {
			SysReportTemplate sysReportTemplate = mapper.readValue(context,SysReportTemplate.class);
//			sysReportTemplateService.importSysReportTemplate(sysReportTemplate.getFdStartDate(),sysReportTemplate.getFdEndDate());
			
			result.setMessage("初始化样本数据成功.");;
		} catch (Exception e) {
			log.error("初始化样本数据失败.");
			result.setErrorMessage("初始化样本数据失败.");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/overdue/export",method = RequestMethod.POST)
	@ResponseBody
	public void exportOverdueSysReportTemplate(@RequestParam String context,@RequestParam String pageVO,HttpServletResponse response) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		
		BufferedInputStream br = null;
		OutputStream out = null;
		String fileTrueName = "";
		byte[] buf = new byte[1024];
		int len = 0;
		
		try {
			SysReportTemplate sysReportTemplate = mapper.readValue(context,SysReportTemplate.class);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			Page<SysReportTemplate> page = sysReportTemplateService.searchSysReportTemplate(sysReportTemplate,pageVo.getPageable());
			
			File file = this.exportOverdueSysReportTemplateDate("按模板统计流程超期",page.getContent());
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
	
	private File exportOverdueSysReportTemplateDate(String title,List<SysReportTemplate> sysReportTemplates) {
		File file = null;
		WritableWorkbook book = null;
		
		try {
			file = new File(ExcelUtils.getExcelName(title));
			book = Workbook.createWorkbook(file);
			
			WritableSheet sheet = book.createSheet("按模板统计流程超期",0);
			sheet.setColumnView(0,30);
			sheet.setColumnView(1,30);
			sheet.setColumnView(2,30);
			sheet.setColumnView(3,30);
			sheet.setColumnView(4,30);
			sheet.setColumnView(5,20);
			sheet.setColumnView(6,20);
			sheet.setColumnView(7,15);
			
			//在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			WritableCellFormat writableCellFormat = ExcelUtils.getDefaultTitleFormat();
			String[] columnHead = new String[8];
			columnHead[0] = "序号";
			columnHead[1] = "流程分类";
			columnHead[2] = "流程模板";
			columnHead[3] = "流程实例总数";
			columnHead[4] = "预警设置时长";
			columnHead[5] = "流程超期数";
			columnHead[6] = "平均超期时长";
			columnHead[7] = "超期时率";
			int rowIndex = 0;
			int columnIndex = 0;
			for (String columnName : columnHead) {
				WritableCell headTitle = ExcelUtils.createCell(columnIndex++,rowIndex,columnName,writableCellFormat);
				sheet.addCell(headTitle);
			}
			
			
			//写入数据
			if (null != sysReportTemplates && sysReportTemplates.size() > 0) {
				for (SysReportTemplate sysReportTemplate : sysReportTemplates) {
					rowIndex++;
					columnIndex = 0;
					
					WritableCell bodyContent1 = ExcelUtils.createCell(columnIndex++,rowIndex,rowIndex,ExcelUtils.getDataFormat(rowIndex));
					sheet.addCell(bodyContent1);
						
					WritableCell bodyContent2 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportTemplate.getFdCategoryName(),ExcelUtils.getDataFormat(sysReportTemplate.getFdCategoryName()));
					sheet.addCell(bodyContent2);
					
					WritableCell bodyContent3 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportTemplate.getFdTemplateName(),ExcelUtils.getDataFormat(sysReportTemplate.getFdTemplateName()));
					sheet.addCell(bodyContent3);
					
					WritableCell bodyContent4 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportTemplate.getFdInstanceNumber(),ExcelUtils.getDataFormat(sysReportTemplate.getFdInstanceNumber()));
					sheet.addCell(bodyContent4);
					
					WritableCell bodyContent5 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportTemplate.getFdAlertTime(),ExcelUtils.getDataFormat(sysReportTemplate.getFdAlertTime()));
					sheet.addCell(bodyContent5);
					
					WritableCell bodyContent6 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportTemplate.getFdOverdueNumber(),ExcelUtils.getDataFormat(sysReportTemplate.getFdOverdueNumber()));
					sheet.addCell(bodyContent6);
					
					WritableCell bodyContent7 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportTemplate.getFdOverdueAvg(),ExcelUtils.getDataFormat(sysReportTemplate.getFdOverdueAvg()));
					sheet.addCell(bodyContent7);
					
					WritableCell bodyContent8 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportTemplate.getFdOverdueRate(),ExcelUtils.getDataFormat(sysReportTemplate.getFdOverdueRate()));
					sheet.addCell(bodyContent8);
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
	public void exportLimitdateSysReportTemplate(@RequestParam String context,@RequestParam String pageVO,HttpServletResponse response) {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		
		BufferedInputStream br = null;
		OutputStream out = null;
		String fileTrueName = "";
		byte[] buf = new byte[1024];
		int len = 0;
		
		try {
			SysReportTemplate sysReportTemplate = mapper.readValue(context,SysReportTemplate.class);
			PageVO pageVo = mapper.readValue(pageVO,PageVO.class);
			Page<SysReportTemplate> page = sysReportTemplateService.searchSysReportTemplate(sysReportTemplate,pageVo.getPageable());
			
			File file = this.exportLimitdateSysReportTemplateDate("按模板统计流程时效",page.getContent());
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
	
	private File exportLimitdateSysReportTemplateDate(String title,List<SysReportTemplate> sysReportTemplates) {
		File file = null;
		WritableWorkbook book = null;
		
		try {
			file = new File(ExcelUtils.getExcelName(title));
			book = Workbook.createWorkbook(file);
			
			WritableSheet sheet = book.createSheet("按模板统计流程时效",0);
			sheet.setColumnView(0,30);
			sheet.setColumnView(1,30);
			sheet.setColumnView(2,30);
			sheet.setColumnView(3,30);
			sheet.setColumnView(4,30);
			sheet.setColumnView(5,20);
			sheet.setColumnView(6,20);
			
			//在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			WritableCellFormat writableCellFormat = ExcelUtils.getDefaultTitleFormat();
			String[] columnHead = new String[8];
			columnHead[0] = "序号";
			columnHead[1] = "流程分类";
			columnHead[2] = "流程模板";
			columnHead[3] = "流程实例总数";
			columnHead[4] = "最长处理时长";
			columnHead[5] = "最短处理时长";
			columnHead[6] = "平均处理时长";
			int rowIndex = 0;
			int columnIndex = 0;
			for (String columnName : columnHead) {
				WritableCell headTitle = ExcelUtils.createCell(columnIndex++,rowIndex,columnName,writableCellFormat);
				sheet.addCell(headTitle);
			}
			
			
			//写入数据
			if (null != sysReportTemplates && sysReportTemplates.size() > 0) {
				for (SysReportTemplate sysReportTemplate : sysReportTemplates) {
					rowIndex++;
					columnIndex = 0;
					
					WritableCell bodyContent1 = ExcelUtils.createCell(columnIndex++,rowIndex,rowIndex,ExcelUtils.getDataFormat(rowIndex));
					sheet.addCell(bodyContent1);
						
					WritableCell bodyContent2 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportTemplate.getFdCategoryName(),ExcelUtils.getDataFormat(sysReportTemplate.getFdCategoryName()));
					sheet.addCell(bodyContent2);
					
					WritableCell bodyContent3 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportTemplate.getFdTemplateName(),ExcelUtils.getDataFormat(sysReportTemplate.getFdTemplateName()));
					sheet.addCell(bodyContent3);
					
					WritableCell bodyContent4 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportTemplate.getFdInstanceNumber(),ExcelUtils.getDataFormat(sysReportTemplate.getFdInstanceNumber()));
					sheet.addCell(bodyContent4);
					
					WritableCell bodyContent5 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportTemplate.getFdMaxTime(),ExcelUtils.getDataFormat(sysReportTemplate.getFdMaxTime()));
					sheet.addCell(bodyContent5);
					
					WritableCell bodyContent6 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportTemplate.getFdMinTime(),ExcelUtils.getDataFormat(sysReportTemplate.getFdMinTime()));
					sheet.addCell(bodyContent6);
					
					WritableCell bodyContent7 = ExcelUtils.createCell(columnIndex++,rowIndex,sysReportTemplate.getFdAvgTime(),ExcelUtils.getDataFormat(sysReportTemplate.getFdAvgTime()));
					sheet.addCell(bodyContent7);
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
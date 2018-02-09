package com.vispractice.fmc.business.base.utils;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.vispractice.fmc.business.entity.sys.category.SysCategoryMain;
import com.vispractice.fmc.business.entity.sys.news.SysNewsTemplate;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfTemplate;

public class ExcelUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final static String FILE_PATH = null;
	private final static String EXCEL_Extension = ".xls";
	
	private static String[] columnList1 = new  String[14];
	private static String[] columnList2 = new  String[28];
	private static String[] columnList3 = new  String[13];
	
	/**
	 * 工具类初始化
	 */
	public static void init() {
		columnList1[0] = "fdId";
		columnList1[1] = "fdName";
		columnList1[2] = "fdHierarchyId";
		columnList1[3] = "fdOrder";
		columnList1[4] = "fdModelName";
		columnList1[5] = "docCreateTime";
		columnList1[6] = "docAlterTime";
		columnList1[7] = "fdIsinheritMaintainer";
		columnList1[8] = "fdIsinheritUser";
		columnList1[9] = "authReaderFlag";
		columnList1[10] = "docCreatorId";
		columnList1[11] = "docAlterorId";
		columnList1[12] = "fdParentID";
		columnList1[13] = "authAreaId";

		columnList2[0] = "fdId";
		columnList2[1] = "fdName";
		columnList2[2] = "docCreateTime";
		columnList2[3] = "fdImportance";
		columnList2[4] = "fdNumberPrefix";
		columnList2[5] = "docContent";
		columnList2[6] = "fdUseForm";
		columnList2[7] = "fdAppLink";
		columnList2[8] = "authReaderFlag";
		columnList2[9] = "authTmpAttNodownload";
		columnList2[10] = "authAttNocopy";
		columnList2[11] = "fdStatus";
		columnList2[12] = "authTmpAttNoprint";
		columnList2[13] = "fdOrder";
		columnList2[13] = "fdStyle";
		columnList2[14] = "fdContentType";
		columnList2[15] = "docCategory";
		columnList2[16] = "docCreator";
		columnList2[17] = "authArea";
		columnList2[19] = "sysBusiSys";
		columnList2[20] = "authNotReaderFlag";
		columnList2[21] = "fdHierarchyId";
		columnList2[22] = "docAlteror";
		columnList2[23] = "docAlterTime";
		columnList2[24] = "fdIsinheritMaintainer";
		columnList2[25] = "fdIsinheritUser";
		columnList2[26] = "authChangeAtt";
		columnList2[27] = "fdParent";

		columnList3[0] = "fdId";
		columnList3[1] = "fdFlowContent";
		columnList3[2] = "fdModelName";
		columnList3[3] = "fdModelId";
		columnList3[4] = "fdKey";
		columnList3[5] = "fdNodeNum";
		columnList3[6] = "fdType";
		columnList3[7] = "fdCreateTime";
		columnList3[8] = "fdVersion";
		columnList3[9] = "fdCommonTemplate";
		columnList3[10] = "fdCreator";
		columnList3[11] = "fdAllNodeNum";
	}
	
	/**
	 * 上传文件夹所有文件
	 * @param path
	 * @return
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		
		if (!file.exists()) {
			return flag;
		}
		
		if (!file.isDirectory()) {
			return flag;
		}
		
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			
			if (temp.isFile()) {
				temp.delete();
			}
			
			if (temp.isDirectory()) {
				// 先删除文件夹里面的文件
				delAllFile(path + "/" + tempList[i]);
				// 再删除空文件夹
				delFolder(path + "/" + tempList[i]);
				flag = true;
			}
		}
		
		return flag;
	}
	
	/**
	 * 删除文件夹
	 * @param folderPath
	 */
	public static void delFolder(String folderPath) {
	     try {
	    	// 删除完里面所有内容
	        delAllFile(folderPath); 
	        String filePath = folderPath;
	        filePath = filePath.toString();
	        File myFilePath = new File(filePath);
	        
	        // 删除空文件夹
	        myFilePath.delete(); 
	     } catch (Exception e) {
	       e.printStackTrace(); 
	     }
	}
	
	/**
	 * 根据标题获取文件路径
	 * @param title
	 * @return
	 */
	public static String getExcelName(String title) {
		File file = null;
		String tempPath = FILE_PATH;
		if (null == FILE_PATH) {
			tempPath = System.getProperty("user.dir") + "/tempData";
			file = new File(tempPath);
		} else {
			file = new File(FILE_PATH);
		}
		
		delFolder(tempPath);
		
		if (!file.exists()) {
			boolean isSuccess = file.mkdir();
			if (!isSuccess) {
				throw new RuntimeException(file.getAbsolutePath() + " the directory was created failed.");
			}
		}

		return tempPath + "/" + title + "-" + System.currentTimeMillis() + EXCEL_Extension;
	}
	
	/**
	 * 设置单元格格式
	 * @return
	 */
	public static WritableCellFormat getDefaultTitleFormat() {
		WritableCellFormat titleFormat = new WritableCellFormat(NumberFormats.TEXT);
		try {
			titleFormat.setBackground(Colour.GRAY_25);
			titleFormat.setBorder(Border.ALL,BorderLineStyle.THIN);
			titleFormat.setAlignment(Alignment.CENTRE);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		
		return titleFormat;
	}
	
	/**
	 * 设置单元格内容格式
	 * @param obj
	 * @return
	 */
	public static WritableCellFormat getDataFormat(Object obj) {
		WritableCellFormat titleFormat = null;
		if (obj instanceof Long || obj instanceof Integer) {
			NumberFormat fivedps1 = new NumberFormat("###,##0");
			titleFormat = new WritableCellFormat(fivedps1);
		} else if (obj instanceof Double||obj instanceof BigDecimal){
			NumberFormat fivedps1 = new NumberFormat("###,##0.00");
			titleFormat = new WritableCellFormat(fivedps1);
		} else {
			titleFormat = new WritableCellFormat( NumberFormats.TEXT);
		}
		
		try {
			titleFormat.setBorder(Border.ALL,BorderLineStyle.THIN);
			titleFormat.setAlignment(Alignment.LEFT);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		
		return titleFormat;
	}
	
	/**
	 * 创建单元格 
	 * @param colIndex
	 * @param rowIndex
	 * @param value
	 * @param format
	 * @return
	 */
	public static WritableCell createCell(int colIndex,int rowIndex,Object value,WritableCellFormat format) {
		WritableCell cell = null;
		if (value instanceof Number) {
			value = value == null ? 0 : value;
			cell = new jxl.write.Number(colIndex,rowIndex,Double.parseDouble(value.toString()), format);

			return cell;
		}
		
		if (value instanceof Date || cell instanceof Timestamp || cell instanceof java.util.Date) {
			cell = new DateTime(colIndex,rowIndex,(Date) value,format);
			
			return cell;
		}
		
		cell = new Label(colIndex, rowIndex, value == null ? "" : value.toString(),format);
		
		return cell;
	}
	
	/**
	 * 导出流程模板
	 * @param title
	 * @param data
	 * @return
	 */
	public static File exportTemplate(String title,List<SysNewsTemplate> data) {
		init();
		
		File file = null;
		WritableWorkbook book = null;
		
		try {
			file = new File(ExcelUtils.getExcelName(title));
			book = Workbook.createWorkbook(file);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			WritableSheet sheet1 = book.createSheet("SYS_CATEGORY_MAIN",0);
			sheet1.setColumnView(0,30);
			sheet1.setColumnView(1,30);
			sheet1.setColumnView(2,30);
			sheet1.setColumnView(3,30);
			sheet1.setColumnView(4,30);
			sheet1.setColumnView(5,20);
			sheet1.setColumnView(6,20);
			sheet1.setColumnView(7,15);
			sheet1.setColumnView(8,15);
			sheet1.setColumnView(9,15);
			sheet1.setColumnView(10,15);
			sheet1.setColumnView(11,15);
			sheet1.setColumnView(12,15);
			sheet1.setColumnView(13,15);

			WritableSheet sheet2 = book.createSheet("SYS_NEWS_TEMPLATE",1);
			sheet2.setColumnView(0,30);
			sheet2.setColumnView(1,30);
			sheet2.setColumnView(2,30);
			sheet2.setColumnView(3,30);
			sheet2.setColumnView(4,30);
			sheet2.setColumnView(5,20);
			sheet2.setColumnView(6,20);
			sheet2.setColumnView(7,15);
			sheet2.setColumnView(8,15);
			sheet2.setColumnView(9,15);
			sheet2.setColumnView(10,15);
			sheet2.setColumnView(11,15);
			sheet2.setColumnView(12,15);

			sheet2.setColumnView(13,30);
			sheet2.setColumnView(14,30);
			sheet2.setColumnView(15,30);
			sheet2.setColumnView(16,30);
			sheet2.setColumnView(17,30);
			sheet2.setColumnView(18,20);
			sheet2.setColumnView(19,20);
			sheet2.setColumnView(20,15);
			sheet2.setColumnView(21,15);
			sheet2.setColumnView(22,15);
			sheet2.setColumnView(23,15);
			sheet2.setColumnView(24,15);
			sheet2.setColumnView(25,15);

			sheet2.setColumnView(26,15);
			sheet2.setColumnView(27,15);

			WritableSheet sheet3 = book.createSheet("SYS_WF_TEMPLATE",2);
			sheet3.setColumnView(0,30);
			sheet3.setColumnView(1,30);
			sheet3.setColumnView(2,30);
			sheet3.setColumnView(3,30);
			sheet3.setColumnView(4,30);
			sheet3.setColumnView(5,20);
			sheet3.setColumnView(6,20);
			sheet3.setColumnView(7,15);
			sheet3.setColumnView(8,15);
			sheet3.setColumnView(9,15);
			sheet3.setColumnView(10,15);
			sheet3.setColumnView(11,15);
			sheet3.setColumnView(12,15);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			int rowIndex = 0;
			int rowCate = 0;
			WritableCellFormat titleFormat = getDefaultTitleFormat();
			// 如果表头不为空,则写入表头
			int i = 0;
			for (String columnName : columnList1) {
				WritableCell label = createCell(i++,rowIndex,columnName,titleFormat);
				sheet1.addCell(label);
			}
			
			i = 0;
			for (String columnName : columnList2) {
				WritableCell label = createCell(i++, rowIndex,columnName,titleFormat);
				sheet2.addCell(label);
			}
			
			i = 0;
			for (String columnName : columnList3) {
				WritableCell label = createCell(i++, rowIndex, columnName,titleFormat);
				sheet3.addCell(label);
			}
			rowCate++;
			rowIndex++;
			
			// 写入数据
			Set<String> categorySet = new HashSet<String>();
			if (null != data) {
				for (SysNewsTemplate obj : data) {
					int index = 0;
					int index2 = 0;
					int index3 = 0;
					// 模板分类
					SysCategoryMain categoryMain = obj.getDocCategory();
					if (!categorySet.contains(categoryMain.getFdId())) {
						categorySet.add(categoryMain.getFdId());
						WritableCell labelTemp1 = createCell(index++,rowCate,categoryMain.getFdId(),getDataFormat(categoryMain.getFdId()));
						sheet1.addCell(labelTemp1);
						
						WritableCell labelTemp2 = createCell(index++,rowCate,categoryMain.getFdName(),getDataFormat(categoryMain.getFdName()));
						sheet1.addCell(labelTemp2);
						
						WritableCell labelTemp3 = createCell(index++,rowCate,categoryMain.getFdHierarchyId(),getDataFormat(categoryMain.getFdHierarchyId()));
						sheet1.addCell(labelTemp3);
						
						WritableCell labelTemp4 = createCell(index++,rowCate,categoryMain.getFdOrder(),getDataFormat(categoryMain.getFdOrder()));
						sheet1.addCell(labelTemp4);
						
						WritableCell labelTemp5 = createCell(index++,rowCate,categoryMain.getFdModelName(),getDataFormat(categoryMain.getFdModelName()));
						sheet1.addCell(labelTemp5);
						
						String createDate = null;
						if (categoryMain.getDocCreateTime() != null) {
							createDate = sdf.format(categoryMain.getDocCreateTime());
						}
						WritableCell labelTemp6 = createCell(index++,rowCate,createDate, getDataFormat(createDate));
						sheet1.addCell(labelTemp6);
						
						String alterDate = null;
						if (categoryMain.getDocAlterTime() != null) {
							alterDate = sdf.format(categoryMain.getDocAlterTime());
						}
						WritableCell labelTemp7 = createCell(index++,rowCate,alterDate,getDataFormat(alterDate));
						sheet1.addCell(labelTemp7);
						
						WritableCell labelTemp8 = createCell(index++,rowCate,categoryMain.getFdIsinheritMaintainer(),getDataFormat(categoryMain.getFdIsinheritMaintainer()));
						sheet1.addCell(labelTemp8);
						
						WritableCell labelTemp9 = createCell(index++,rowCate,categoryMain.getFdIsinheritUser(),getDataFormat(categoryMain.getFdIsinheritUser()));
						sheet1.addCell(labelTemp9);
						
						WritableCell labelTemp10 = createCell(index++,rowCate,categoryMain.getAuthReaderFlag(),getDataFormat(categoryMain.getAuthReaderFlag()));
						sheet1.addCell(labelTemp10);
						
						if (categoryMain.getDocCreateId() == null) {
							WritableCell labelTemp11 = createCell(index++,rowIndex,"",getDataFormat(""));
							sheet1.addCell(labelTemp11);
						} else {
							WritableCell labelTemp11 = createCell(index++,rowCate,categoryMain.getDocCreateId(),getDataFormat(categoryMain.getDocCreateId()));
							sheet1.addCell(labelTemp11);
						}

						if (categoryMain.getDocAlterorId() == null) {
							WritableCell labelTemp12 = createCell(index++,rowIndex,"",getDataFormat(""));
							sheet1.addCell(labelTemp12);
						} else {
							WritableCell labelTemp12 = createCell(index++,rowCate,categoryMain.getDocAlterorId(),getDataFormat(categoryMain.getDocAlterorId()));
							sheet1.addCell(labelTemp12);
						}

						if (categoryMain.getFdParentId() == null) {
							WritableCell labelTemp13 = createCell(index++,rowIndex,"",getDataFormat(""));
							sheet1.addCell(labelTemp13);
						} else {
							WritableCell labelTemp13 = createCell(index++,rowCate,categoryMain.getFdParentId(),getDataFormat(categoryMain.getFdParentId()));
							sheet1.addCell(labelTemp13);
						}
					
						rowCate++;
					}

					// 插入sys_news_template
					WritableCell labelTemp21 = createCell(index2++,rowIndex,obj.getFdId(),getDataFormat(obj.getFdId()));
					sheet2.addCell(labelTemp21);
					
					WritableCell labelTemp22 = createCell(index2++,rowIndex,obj.getFdName(),getDataFormat(obj.getFdName()));
					sheet2.addCell(labelTemp22);

					String createDate = null;
					if (obj.getDocCreateTime() != null) {
						createDate = sdf.format(obj.getDocCreateTime());
					}
					WritableCell labelTemp23 = createCell(index2++,rowIndex,createDate,getDataFormat(createDate));
					sheet2.addCell(labelTemp23);
					
					WritableCell labelTemp24 = createCell(index2++,rowIndex,obj.getFdImportance(),getDataFormat(obj.getFdImportance()));
					sheet2.addCell(labelTemp24);
					
					WritableCell labelTemp25 = createCell(index2++,rowIndex,obj.getFdNumberPrefix(),getDataFormat(obj.getFdNumberPrefix()));
					sheet2.addCell(labelTemp25);
					
					WritableCell labelTemp26 = createCell(index2++,rowIndex,obj.getDocContent(),getDataFormat(obj.getDocContent()));
					sheet2.addCell(labelTemp26);
					
					WritableCell labelTemp27 = createCell(index2++,rowIndex,obj.getFdUseForm(),getDataFormat(obj.getFdUseForm()));
					sheet2.addCell(labelTemp27);
					
					WritableCell labelTemp28 = createCell(index2++,rowIndex,obj.getFdAppLink(),getDataFormat(obj.getFdAppLink()));
					sheet2.addCell(labelTemp28);
					
					WritableCell labelTemp29 = createCell(index2++,rowIndex,obj.getAuthReaderFlag(),getDataFormat(obj.getAuthReaderFlag()));
					sheet2.addCell(labelTemp29);
					
					WritableCell labelTemp210 = createCell(index2++,rowIndex,obj.getAuthTmpAttNodownload(),getDataFormat(obj.getAuthTmpAttNodownload()));
					sheet2.addCell(labelTemp210);

					WritableCell labelTemp211 = createCell(index2++,rowIndex,obj.getAuthTmpAttNocopy(),getDataFormat(obj.getAuthTmpAttNocopy()));
					sheet2.addCell(labelTemp211);
					
					WritableCell labelTemp212 = createCell(index2++, rowIndex,obj.getFdStatus(),getDataFormat(obj.getFdStatus()));
					sheet2.addCell(labelTemp212);
					
					WritableCell labelTemp213 = createCell(index2++,rowIndex,obj.getAuthTmpAttNoprint(),getDataFormat(obj.getAuthTmpAttNoprint()));
					sheet2.addCell(labelTemp213);
					
					WritableCell labelTemp214 = createCell(index2++,rowIndex,obj.getFdOrder(),getDataFormat(obj.getFdOrder()));
					sheet2.addCell(labelTemp214);
					
					WritableCell labelTemp215 = createCell(index2++,rowIndex,obj.getFdStyle(),getDataFormat(obj.getFdStyle()));
					sheet2.addCell(labelTemp215);
					
					WritableCell labelTemp216 = createCell(index2++,rowIndex,obj.getFdContentType(),getDataFormat(obj.getFdContentType()));
					sheet2.addCell(labelTemp216);
					
					if (obj.getDocCategory() == null) {
						WritableCell labelTemp217 = createCell(index2++,rowIndex,"",getDataFormat(""));
						sheet2.addCell(labelTemp217);
					} else {
						WritableCell labelTemp217 = createCell(index2++,rowIndex,obj.getDocCategory().getFdId(),getDataFormat(obj.getDocCategory().getFdId()));
						sheet2.addCell(labelTemp217);
					}
					
					if (obj.getDocCreatorId() == null) {
						WritableCell labelTemp218 = createCell(index2++,rowIndex,"",getDataFormat(""));
						sheet2.addCell(labelTemp218);
					} else {
						WritableCell labelTemp218 = createCell(index2++,rowIndex,obj.getDocCreatorId(),getDataFormat(obj.getDocCreatorId()));
						sheet2.addCell(labelTemp218);
					}

					if (obj.getAuthAreaId() == null) {
						WritableCell labelTemp219 = createCell(index2++,rowIndex,"",getDataFormat(""));
						sheet2.addCell(labelTemp219);
					} else {
						WritableCell labelTemp219 = createCell(index2++,rowIndex,obj.getAuthAreaId(),getDataFormat(obj.getAuthAreaId()));
						sheet2.addCell(labelTemp219);
					}

					if (obj.getBusiSysId() == null) {
						WritableCell labelTemp219 = createCell(index2++,rowIndex,"",getDataFormat(""));
						sheet2.addCell(labelTemp219);
					} else {
						WritableCell labelTemp220 = createCell(index2++,rowIndex,obj.getBusiSysId(),getDataFormat(obj.getBusiSysId()));
						sheet2.addCell(labelTemp220);
					}

					WritableCell labelTemp221 = createCell(index2++,rowIndex,obj.getAuthNotReaderFlag(),getDataFormat(obj.getAuthNotReaderFlag()));
					sheet2.addCell(labelTemp221);
					
					WritableCell labelTemp222 = createCell(index2++,rowIndex,obj.getFdHierarchyId(),getDataFormat(obj.getFdHierarchyId()));
					sheet2.addCell(labelTemp222);
					
					if (obj.getDocAlterId() == null) {
						WritableCell labelTemp223 = createCell(index2++,rowIndex,"",getDataFormat(""));
						sheet2.addCell(labelTemp223);
					} else {
						WritableCell labelTemp223 = createCell(index2++,rowIndex,obj.getDocAlterId(),getDataFormat(obj.getDocAlterId()));
						sheet2.addCell(labelTemp223);
					}

					String altereDate = null;
					if (obj.getDocAlterTime() != null) {
						altereDate = sdf.format(obj.getDocAlterTime());
					}
					WritableCell labelTemp224 = createCell(index2++,rowIndex,altereDate,getDataFormat(altereDate));
					sheet2.addCell(labelTemp224);
					
					WritableCell labelTemp225 = createCell(index2++,rowIndex,obj.getFdIsinheritMaintainer(),getDataFormat(obj.getFdIsinheritMaintainer()));
					sheet2.addCell(labelTemp225);
					
					WritableCell labelTemp226 = createCell(index2++,rowIndex,obj.getFdIsinheritUser(),getDataFormat(obj.getFdIsinheritUser()));
					sheet2.addCell(labelTemp226);
					
					WritableCell labelTemp227 = createCell(index2++,rowIndex,obj.getFdChageAtt(),getDataFormat(obj.getFdChageAtt()));
					sheet2.addCell(labelTemp227);
					
					if (obj.getFdParentId() == null) {
						WritableCell labelTemp228 = createCell(index2++,rowIndex,"",getDataFormat(""));
						sheet2.addCell(labelTemp228);
					} else {
						WritableCell labelTemp228 = createCell(index2++,rowIndex,obj.getFdParentId(),getDataFormat(obj.getFdParentId()));
						sheet2.addCell(labelTemp228);
					}

					// 插入sys_wf_template
					SysWfTemplate wfTemplate = obj.getSysWfTemplates().get(0);
					WritableCell labelTemp31 = createCell(index3++,rowIndex,wfTemplate.getFdId(),getDataFormat(wfTemplate.getFdId()));
					sheet3.addCell(labelTemp31);
					
					WritableCell labelTemp32 = createCell(index3++,rowIndex,wfTemplate.getFdFlowContent(),getDataFormat(wfTemplate.getFdFlowContent()));
					sheet3.addCell(labelTemp32);
					
					WritableCell labelTemp33 = createCell(index3++,rowIndex,wfTemplate.getFdModelName(),getDataFormat(wfTemplate.getFdModelName()));
					sheet3.addCell(labelTemp33);
					
					WritableCell labelTemp34 = createCell(index3++,rowIndex,wfTemplate.getFdModelId(),getDataFormat(wfTemplate.getFdModelId()));
					sheet3.addCell(labelTemp34);
					
					WritableCell labelTemp35 = createCell(index3++,rowIndex,wfTemplate.getFdKey(),getDataFormat(wfTemplate.getFdKey()));
					sheet3.addCell(labelTemp35);
					
					WritableCell labelTemp36 = createCell(index3++,rowIndex,wfTemplate.getFdNodeNum(),getDataFormat(wfTemplate.getFdNodeNum()));
					sheet3.addCell(labelTemp36);
					
					WritableCell labelTemp37 = createCell(index3++,rowIndex,wfTemplate.getFdType(),getDataFormat(wfTemplate.getFdType()));
					sheet3.addCell(labelTemp37);
					
					String fdCreateDate = null;
					if (wfTemplate.getFdCreateTime() != null) {
						fdCreateDate = sdf.format(wfTemplate.getFdCreateTime());
					}
					WritableCell labelTemp38 = createCell(index3++,rowIndex,fdCreateDate,getDataFormat(fdCreateDate));
					sheet3.addCell(labelTemp38);
					
					WritableCell labelTemp39 = createCell(index3++,rowIndex,wfTemplate.getFdVersion(),getDataFormat(wfTemplate.getFdVersion()));
					sheet3.addCell(labelTemp39);

					if (wfTemplate.getFdCommonId() == null) {
						WritableCell labelTemp310 = createCell(index3++,rowIndex,"",getDataFormat(""));
						sheet3.addCell(labelTemp310);
					} else {
						WritableCell labelTemp310 = createCell(index3++,rowIndex,wfTemplate.getFdCommonId(),getDataFormat(wfTemplate.getFdCommonId()));
						sheet3.addCell(labelTemp310);
					}

					if (wfTemplate.getFdCreatorId() == null) {
						WritableCell labelTemp311 = createCell(index3++,rowIndex,"",getDataFormat(""));
						sheet3.addCell(labelTemp311);
					} else {
						WritableCell labelTemp311 = createCell(index3++,rowIndex,wfTemplate.getFdCreatorId(),getDataFormat(wfTemplate.getFdCreatorId()));
						sheet3.addCell(labelTemp311);
					}

					WritableCell labelTemp312 = createCell(index3++,rowIndex,wfTemplate.getFdNodeNum(),getDataFormat(wfTemplate.getFdNodeNum()));
					sheet3.addCell(labelTemp312);

					rowIndex++;
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

	/**
	 * 判断是否支持文件格式
	 * @param fileName
	 * @return
	 */
	public static boolean isSupport (String fileName) {
		if (fileName.endsWith(EXCEL_Extension)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 获取对应页表
	 * @param sheetNum
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static Sheet[] getSheets(InputStream is) throws Exception {
		Sheet[] sheets = Workbook.getWorkbook(is).getSheets();
		
		return sheets;
	}
	
	/**
	 * 获取单元格内容
	 * @param st
	 * @param row
	 * @param col
	 * @return
	 */
	public static String getCellValue(Sheet st,int row,int col) {
		String value = st.getCell(col,row).getContents();
		
		return value == null ? "" : value;
	}
}

package com.back.base.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.util.StringUtils;

/**
 * @author dragon
 */
public class PoiUtils {

	/**
	 * 导出Excel
	 * 
	 * @param sheetName
	 *            sheet页的名称
	 * @param titles
	 *            标题行
	 * @param colWidth
	 *            列宽
	 * @param columns
	 *            内容列
	 * @param outputStream
	 *            Excel的输出流
	 * @throws IOException
	 *             此方法可能抛出IO异常
	 */
	public static void outputExcel(String sheetName, String[] titles, int colWidth, List<String[]> columns, OutputStream outputStream) {
		try {
			HSSFWorkbook workbook2003 = getExcel(sheetName, titles, colWidth, columns);
			workbook2003.write((outputStream));
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 
	 * @param:@param sheetName
	 * @param:@param titles
	 * @param:@param fieldNames
	 * @param:@param dataList
	 * @param:@param colWidth
	 * @param:@param outputStream
	 * @return:void
	 * @author:dragon
	 * @time:2014年8月9日 下午5:30:12
	 */
	public static <T> void outputExcel(String sheetName, String[] titles, String[] fieldNames, List<T> dataList, int colWidth, OutputStream outputStream) {


		List<String[]> list = new ArrayList<String[]>();


		for (T t : dataList) {

			String[] values = new String[titles.length];

			for (int i = 0; i < fieldNames.length; i++) {

				String methodName = "get" + fieldNames[i].substring(0, 1).toUpperCase() + fieldNames[i].substring(1);// getName
				Object val = null;
				;
				try {
					Method method = t.getClass().getMethod(methodName);
					val = method.invoke(t);
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				if (null != val) {
					values[i] = val.toString();
				}
			}
			list.add(values);
		}

		HSSFWorkbook workbook2003 = getExcel(sheetName, titles, colWidth, list);
		try {
			workbook2003.write((outputStream));
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成Excel,从outputExcel抽出。
	 * 
	 * @param sheetName
	 *            sheet页的名称
	 * @param titles
	 *            标题行
	 * @param colWidth
	 *            列宽
	 * @param columns
	 *            内容列
	 * @param outputStream
	 *            Excel的输出流
	 * @throws IOException
	 *             此方法可能抛出IO异常
	 */
	public static HSSFWorkbook getExcel(String sheetName, String[] titles, int colWidth, List<String[]> columns) {
		HSSFWorkbook workbook2003 = new HSSFWorkbook();
		// 创建工作表对象并命名
		HSSFSheet sheet = workbook2003.createSheet(sheetName);
		// 表的格式设置
		for (int i = 0; i < columns.get(0).length; i++) {
			sheet.setColumnWidth(i, colWidth);
		}

		// 设置标题的样式
		HSSFCellStyle titleStyle = workbook2003.createCellStyle();
		HSSFFont titleFont = workbook2003.createFont();
		titleFont.setFontName("宋体");
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleFont.setFontHeightInPoints((short) 14);
		titleStyle.setFont(titleFont);// 设置字体;
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框

		HSSFRow headRow = sheet.createRow(0);// 标题行
		for (int i = 0; i < titles.length; i++) {
			HSSFCell headCell = headRow.createCell(i);
			headCell.setCellValue(titles[i]);
			headCell.setCellStyle(titleStyle);
		}

		// 内容样式
		HSSFCellStyle contentStyle = workbook2003.createCellStyle();
		HSSFFont contentFont = workbook2003.createFont();
		contentFont.setFontName("宋体");
		contentFont.setFontHeightInPoints((short) 10);
		contentStyle.setFont(contentFont);// 设置字体
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框

		for (int i = 0; i < columns.size(); i++) {

			HSSFRow row = sheet.createRow(i + 1);

			row.setHeightInPoints(16f);

			String[] contentCols = columns.get(i);

			for (int j = 0; j < contentCols.length; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(contentCols[j]);
				cell.setCellStyle(contentStyle);
			}
		}
		return workbook2003;
	}

	/**
	 * 输出excel中的中信息
	 * 
	 * @param sheet
	 *            操作的工作表
	 * @param rowPos在第几行开始输出数据
	 * @param columns
	 *            内容列
	 * @throws IOException
	 */
	public static void outputExcel(HSSFSheet sheet, int rowPos, List<String[]> columns) {
		for (int i = 0; i < columns.size(); i++) {
			HSSFRow row = sheet.createRow(i + rowPos);
			String[] contentCols = columns.get(i);
			for (int j = 0; j < contentCols.length; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(contentCols[j]);
			}
		}
	}

	public static void outputExcel(HSSFWorkbook wb, HSSFSheet sheet, int rowPos, String[] titles, List<String[]> columns) {

		// 设置标题的样式
		HSSFCellStyle titleStyle = wb.createCellStyle();
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		HSSFFont titleFont = wb.createFont();
		titleFont.setFontName("宋体");
		titleFont.setFontHeightInPoints((short) 12);
		titleStyle.setFont(titleFont);// 设置字体;

		HSSFRow headRow = null;
		for (int i = 0; i < titles.length; i++) {
			titleFont.setBoldweight((i == 0) ? HSSFFont.BOLDWEIGHT_BOLD : HSSFFont.BOLDWEIGHT_NORMAL);

			headRow = sheet.createRow(i + rowPos);
			headRow.setHeightInPoints(21f);// 设置行高度
			HSSFCell headCell = headRow.createCell(1);
			headCell.setCellValue(titles[i]);
			headCell.setCellStyle(titleStyle);
		}

		// 内容样式
		HSSFCellStyle contentStyle = wb.createCellStyle();
		HSSFFont contentFont = wb.createFont();
		contentFont.setFontName("宋体");
		contentFont.setFontHeightInPoints((short) 10);
		contentFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		contentStyle.setFont(contentFont);// 设置字体
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框

		for (int i = 0; i < columns.size(); i++) {
			// contentFont.setBoldweight((i==0)? HSSFFont.BOLDWEIGHT_BOLD :
			// HSSFFont.BOLDWEIGHT_NORMAL);

			HSSFRow row = sheet.createRow(i + titles.length + rowPos);
			row.setHeightInPoints(16f);

			String[] contentCols = columns.get(i);

			for (int j = 0; j < contentCols.length; j++) {
				HSSFCell cell = row.createCell(j + 1);
				cell.setCellValue(contentCols[j]);
				cell.setCellStyle(contentStyle);
			}
		}
	}

	/**
	 * 
	 * @param sRealPath
	 *            指定文件的路径
	 * @param isTitle
	 *            是否包含标题
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> readExcel(String sRealPath, boolean isTitle) {
		int startRowNum = 0;
		if (isTitle) {
			startRowNum = 1;
		}
		return readExcel(sRealPath, startRowNum);
	}

	/**
	 * 
	 * 读取excel表格，反射成对应的vo，校验规则
	 * 
	 * @param:@param sRealPath excel文档路径
	 * @param:@param isTitle 是否包含标题
	 * @param:@param fieldNames excel字段属性
	 * @param:@param checkBlankFieldNames 非空校验字段
	 * @param:@param checkDecimalFieldNames 小数校验字段
	 * @param:@param t 页面vo
	 * @param:@return
	 * @return:List<T> 返回vo列表
	 * @author:dragon
	 * @time:2014年8月19日 下午3:27:06
	 */
	public static <T> List<T> readExcel(String sRealPath, boolean isTitle, String fieldNames[], Map<String, String> checkBlankFieldNames, Map<String, String> checkDecimalFieldNames, Class<T> c) {

		String errMsg = "";

		List<T> retList = new ArrayList<T>();

		List<String[]> arrayList = readExcel(sRealPath, isTitle);

		try {
			for (int rowIndex = (isTitle ? 2 : 1); rowIndex < arrayList.size() + (isTitle ? 2 : 1); rowIndex++) {
				String[] array = arrayList.get(rowIndex - (isTitle ? 2 : 1));
				 T o = c.newInstance();
				for (int i = 0; i < array.length && i < fieldNames.length; i++) {
					errMsg += checkBlankRule(array[i], fieldNames[i], checkBlankFieldNames, rowIndex);// 非空校验规则
					errMsg += checkDecimalRule(array[i], fieldNames[i], checkDecimalFieldNames, rowIndex);// 小数校验规则
					String methodName = "set" + fieldNames[i].substring(0, 1).toUpperCase() + fieldNames[i].substring(1);// setName
					Method method = c.getMethod(methodName, c.getDeclaredField(fieldNames[i]).getType());
					if (c.getDeclaredField(fieldNames[i]).getType().equals(BigDecimal.class)) {
						if (StringUtils.hasText(array[i]) && array[i].matches(IConstant.DECIMAL_REGULAR)) {
							method.invoke(o, new BigDecimal(array[i]));
						}
					} else {
						method.invoke(o, array[i]);
					}
				}
				retList.add(o);
			}
			if (StringUtils.hasText(errMsg)) {
				errMsg = "导入文档时发生了如下的问题：<br/><hr/>" + errMsg;
				errMsg += "请检查，导入文档<font color='red'>【" + sRealPath.split("/")[1] + "】</font>!";
				throw new RuntimeException(errMsg);
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}

	/**
	 * 校验excel表格非空规则
	 * 
	 * @param value
	 *            值
	 * @param fieldName
	 *            属性名称
	 * @param checkFieldNames
	 *            校验列
	 */
	private static String checkBlankRule(String value, String fieldName, Map<String, String> checkBlankFieldNames, int rowIndex) {
		String msg = "";
		Iterator it = checkBlankFieldNames.keySet().iterator();
		while (it.hasNext()) {
			String checkFieldName = it.next().toString();
			if (checkFieldName.equals(fieldName)) {// 校验列
				if (!StringUtils.hasText(value)) {
					msg = "【<font color='red'>" + checkBlankFieldNames.get(checkFieldName) + "</font>列，第<font color='red'>" + rowIndex + "</font>记录为空值】<br/><hr/>";
				}
			}
		}
		return msg;
	}

	/**
	 * 校验excel表格小数规则
	 * 
	 * @param value
	 *            值
	 * @param fieldName
	 *            属性名称
	 * @param checkFieldNames
	 *            校验列
	 */
	private static String checkDecimalRule(String value, String fieldName, Map<String, String> checkDecimalFieldNames, int rowIndex) {
		String msg = "";
		Iterator it = checkDecimalFieldNames.keySet().iterator();
		while (it.hasNext()) {
			String checkFieldName = it.next().toString();
			if (checkFieldName.equals(fieldName)) {// 校验列
				if (StringUtils.hasText(value) && !value.matches(IConstant.DECIMAL_REGULAR)) {
					msg = "【<font color='red'>" + checkDecimalFieldNames.get(checkFieldName) + "</font>列，第<font color='red'>" + rowIndex + "</font>记录数据格式无误,该字段为小数】<br/><hr/>";
				}
			}
		}
		return msg;
	}

	/**
	 * 
	 * @param sRealPath
	 *            指定文件的路径
	 * @param rowNum
	 *            从第几行开始读
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> readExcel(String sRealPath, int startRowNum) {

		ArrayList<String[]> arrayList = new ArrayList<String[]>();

		File fTmp = new File(sRealPath);
		if (!fTmp.exists()) {
			throw new RuntimeException("指定文件路径不存在!");
		}

		int iRowNum = 0;// 工作表中的行数量
		int iCellNum = 0;// 每行中的单元格数量

		// 获取文件流,并且创建一个EXCEL文件对象
		try {
			FileInputStream fis = new FileInputStream(fTmp);
			POIFSFileSystem fs = new POIFSFileSystem(fis);
			HSSFWorkbook wb = new HSSFWorkbook(fs);// 获取工作簿对象
			HSSFSheet sheet = wb.getSheetAt(0);// 获取工作簿中第一个sheet表

			iRowNum = sheet.getLastRowNum() + 1;// 获取sheet表中总行数

			/** 开始读取行数据 */

			for (int j = startRowNum; j < iRowNum; j++) {
				HSSFRow rowTmp = sheet.getRow(j);
				if (rowTmp == null) {// 判断是否为空行
					continue;
				}
				if (j == startRowNum) {
					iCellNum = rowTmp.getLastCellNum();
					iCellNum = iCellNum + 1;// 获取第一行中总单元格总数
				}
				String aValues[] = new String[iCellNum];

				/** 开始读取每行中每个单元格数据 */

				for (int k = 0; k < iCellNum; k++) {
					HSSFCell cellTmp = rowTmp.getCell(k);
					if (cellTmp == null) {// 判断是否为空单元格
						aValues[k] = new String("");
						continue;
					}
					aValues[k] = parseCell(cellTmp);// 解析单元格中的数据
				}
				/** 结束读取每行中每个单元格数据 */

				arrayList.add(aValues);// 添加到集合中
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/** 结束读取行数据 */
		return arrayList;
	}

	/**
	 * 
	 * @param sRealPath
	 *            excel文件路径
	 * @param startRowNum
	 *            开始行
	 * @param startColsNum
	 *            开始列
	 * @param endColsNum
	 *            结束列
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> readExcel(String sRealPath, int startRowNum, int startColsNum, int endColsNum) {

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		File fTmp = new File(sRealPath);
		if (!fTmp.exists()) {
			throw new RuntimeException("指定文件路径不存在!");
		}
		// 获取文件流,并且创建一个EXCEL文件对象
		try {
			FileInputStream fis = new FileInputStream(fTmp);
			POIFSFileSystem fs = new POIFSFileSystem(fis);
			HSSFWorkbook wb = new HSSFWorkbook(fs);// 获取工作簿对象
			HSSFSheet sheet = wb.getSheetAt(0);// 获取工作簿中第一个sheet表
			int endRowNum = sheet.getLastRowNum();// 获取sheet表中总行数

			/** 开始读取行数据 */

			for (int j = startRowNum; j < endRowNum + 1; j++) {
				HSSFRow rowTmp = sheet.getRow(j);
				if (rowTmp == null) {// 判断是否为空行
					continue;
				}
				String aValues[] = new String[endColsNum - startColsNum + 1];

				/** 开始读取每行中每个单元格数据 */

				for (int k = startColsNum; k < endColsNum + 1; k++) {
					HSSFCell cellTmp = rowTmp.getCell(k);
					if (cellTmp == null) {// 判断是否为空单元格
						aValues[k] = new String("");
						continue;
					}
					aValues[k] = parseCell(cellTmp);// 解析单元格中的数据
				}
				/** 结束读取每行中每个单元格数据 */

				arrayList.add(aValues);// 添加到集合中
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/** 结束读取行数据 */
		return arrayList;
	}

	/**
	 * 对单元格进行解析操作
	 * 
	 * yyyy-MM-dd-------14 yyyy年m月d日-----31 yyyy年m月---------57 m月d日
	 * ------------58 HH:mm-------------20 h时mm分 ---------32
	 */
	private static String parseCell(HSSFCell cell) {
		String result = new String();
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
			if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
					sdf = new SimpleDateFormat("HH:mm");
				} else {// 日期
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date date = cell.getDateCellValue();
				result = sdf.format(date);
			} else if (cell.getCellStyle().getDataFormat() == 58) {
				// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				double value = cell.getNumericCellValue();
				Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
				result = sdf.format(date);
			} else {
				double value = cell.getNumericCellValue();
				CellStyle style = cell.getCellStyle();
				DecimalFormat format = new DecimalFormat();
				String temp = style.getDataFormatString();
				// 单元格设置成常规
				if (temp.equals("General")) {
					format.applyPattern("#");
				}
				result = format.format(value);
			}
			break;
		case HSSFCell.CELL_TYPE_STRING:// String类型
			result = cell.getRichStringCellValue().toString().trim();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			result = "";
		default:
			result = "";
			break;
		}
		return result;
	}

	/**
	 * 由于Excel当中的单元格Cell存在类型,若获取类型错误就会产生异常, 所以通过此方法将Cell内容全部转换为String类型
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		String str = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			str = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			str = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			str = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			str = String.valueOf((long) cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			str = String.valueOf(cell.getStringCellValue());
			break;
		default:
			str = null;
			break;
		}
		return str;
	}

}

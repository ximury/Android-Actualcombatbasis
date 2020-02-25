package com.back.base.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Font;


public class ExcelReader {    
    private HSSFWorkbook wb;    
    private HSSFSheet sheet;    
    private HSSFRow row;    
    
    //读取所有excel文件的表头内容
    public String[] readAllExcelTitle(File fd,String extName){
       try {
    	   	return readExcelTitle(new FileInputStream(fd));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	//fd.get
    }
    
    public Map<Integer,String> readExcelContentByDate1(InputStream is) {    
        Map<Integer,String> content = new HashMap<Integer,String>();    
        String str = "";    
        try {    
            wb = new HSSFWorkbook(is);    
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
        sheet = wb.getSheetAt(0);    
        //得到总行数    
        int rowNum = sheet.getLastRowNum();    
        row = sheet.getRow(0);    
        int colNum = row.getPhysicalNumberOfCells();    
        //正文内容应该从第二行开始,第一行为表头的标题    
        for (int i = 1; i <= rowNum; i++) {    
            row = sheet.getRow(i); 
            if(row!=null)
            {
	            int j = 0;    
	            while (j<colNum) 
	            {    
			        //每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据    
			        //也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean   
	            	if(j==3 || j==4){
	            		 str += getAllDateCellValue(row.getCell((short) j)).trim() + ";";
	            	}else{
	            		
	            		if(getStringCellValue(row.getCell((short) j))==null||getStringCellValue(row.getCell((short) j)).trim().equals(""))
		            	{            		
		            		 str += "N" + ";";
		            	}else{            		
		            		 str += getStringCellValue(row.getCell((short) j)).trim() + ";";
		            	}
	            	}
	            	
	                j ++;
	            }    
	            content.put(i, str);    
	            str = "";
            }
        }    
        return content;    
    }
    
    /**   
     * 获取单元格数据内容为日期类型的数据   
     * @param cell Excel单元格   
     * @return String 单元格数据内容   
     */   
    private String getAllDateCellValue(HSSFCell cell) {    
       
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	  Date d=cell.getDateCellValue();
        return formatter.format(d);  
    }
    //读取所有excel文件内容
    public  Map<Integer,String> readAllExcelContentByDate(File fd,String extName){
       try {
    	   	return readExcelContentByDate(new FileInputStream(fd),2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	//fd.get
    }
    //读取文件为xls格式的表头内容
    public String[] readExcelTitle(HSSFWorkbook wb,int index) {    
        sheet = wb.getSheetAt(index);    
        row = sheet.getRow(0);    
        //标题总列数    
        int colNum = row.getPhysicalNumberOfCells();    
        String[] title = new String[colNum];    
        for (int i=0; i<colNum; i++) {    
            title[i] = getStringCellValue(row.getCell((short) i));    
        }    
        return title;    
    }
  //读取文件为xls格式的表头内容
    public String[] readExcelTitle(InputStream is) {    
        try {    
            wb = new HSSFWorkbook(is);    
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
        sheet = wb.getSheetAt(0);
        sheet.getSheetName();
        row = sheet.getRow(0);    
        //标题总列数    
        int colNum = row.getPhysicalNumberOfCells();    
        String[] title = new String[colNum];    
        for (int i=0; i<colNum; i++) {    
            title[i] = getStringCellValue(row.getCell((short) i));    
        }    
        return title;    
    }
    //读取文件为xls格式的表头内容
    public String readSheetName(InputStream is) {    
        try{    
            wb = new HSSFWorkbook(is);    
        }catch(IOException e)
        {    
            e.printStackTrace();    
        }    
        sheet = wb.getSheetAt(0);
        return  sheet.getSheetName();
    }
   
    /**   
     * 读取Excel数据内容   
     * @param InputStream   
     * @return Map 包含单元格数据内容的Map对象   
     */ 
    public String[] readExcelTitle(InputStream is,String firstStr) {    
        try {    
            wb = new HSSFWorkbook(is);    
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
        sheet = wb.getSheetAt(0);
        //正文内容应该从第二行开始,第一行为表头的标题    
        for (int i = 0; i <= sheet.getLastRowNum();i++)
        {    
            row = sheet.getRow(i);
            String firstTitle =getStringCellValue(row.getCell(0));
            if(firstTitle==null)firstTitle="";
            else firstTitle=firstTitle.trim().replaceAll(" ","");
            if(firstTitle.equals(firstStr)){
            	break;
            }
        }
        //标题总列数    
        int colNum = row.getPhysicalNumberOfCells();    
        String[] title = new String[colNum];    
        for (int i=0; i<colNum; i++) {    
            title[i] = getStringCellValue(row.getCell((short) i));    
        }    
        return title;    
    }   
    /**   
     * 读取Excel数据内容   
     * @param InputStream   
     * @return Map 包含单元格数据内容的Map对象   
     */   
    public Map<Integer,String> readExcelContent(HSSFWorkbook wb,int index) {    
        Map<Integer,String> content = new HashMap<Integer,String>();    
        String str = "";    
        
        sheet = wb.getSheetAt(index);    
        //得到总行数    
        int rowNum = sheet.getLastRowNum();    
        row = sheet.getRow(0);    
        int colNum = row.getPhysicalNumberOfCells();    
        //正文内容应该从第二行开始,第一行为表头的标题    
        for (int i = 1; i <= rowNum; i++) {    
            row = sheet.getRow(i); 
            if(row!=null)
            {
	            int j = 0;    
	            while (j<colNum) 
	            {    
			        //每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据    
			        //也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean    
	            	if(getStringCellValue(row.getCell((short) j))==null||getStringCellValue(row.getCell((short) j)).trim().equals(""))
	            	{            		
	            		 str += "N" + ";";
	            	}else{            		
	            		 str += getStringCellValue(row.getCell((short) j)).trim() + ";";
	            	}
	                j ++;
	            }    
	            content.put(i, str);    
	            str = "";
            }
        }    
        return content;    
    }  
    
    public int getExcelRowNum(File fd,int index) {      
    	
    	 try {    
    		
    		 
             wb = new HSSFWorkbook( new FileInputStream(fd));    
         } catch (IOException e) {    
             e.printStackTrace();    
         } 
    	 
    	 sheet = wb.getSheetAt(index);    
         //得到总行数    
         return sheet.getLastRowNum();    
    }
    
    public String getExcelContent(File fd,int index,int rowNum,int colNum) {      
    	
   	 try {    
            wb = new HSSFWorkbook( new FileInputStream(fd));    
        } catch (IOException e) {    
            e.printStackTrace();    
        } 
   	 
   	   sheet = wb.getSheetAt(index);    
   	   
   	   row = sheet.getRow(rowNum); 
        //得到总行数    
        return getStringCellValue(row.getCell((short)colNum)).trim();    
   }
    
    public String getExcelContent1(HSSFRow  row ,int colNum) {      
      	   
           //得到总行数    
           return getStringCellValue(row.getCell((short)colNum)).trim();    
      }
    
    public String getExcelContentDate(File fd,int index,int rowNum,int colNum) {      
    	
      	 try {    
               wb = new HSSFWorkbook( new FileInputStream(fd));    
           } catch (IOException e) {    
               e.printStackTrace();    
           } 
      	 
      	   sheet = wb.getSheetAt(index);    
      	   
      	   row = sheet.getRow(rowNum); 
           //得到总行数    
           return getStringCellValueDate(row.getCell((short)colNum)).trim();    
      }
    public String getExcelContentDate(HSSFRow  row ,int colNum)  {      
    	
          //得到总行数    
          return getStringCellValueDate(row.getCell((short)colNum)).trim();    
     }
    /**   
     * 读取Excel数据内容   
     * @param InputStream   
     * @return Map 包含单元格数据内容的Map对象   
     */   
    public Map<Integer,String> readExcelContent(InputStream is) {    
        Map<Integer,String> content = new HashMap<Integer,String>();    
        String str = "";    
        try {    
            wb = new HSSFWorkbook(is);    
        } catch (IOException e) {    
            e.printStackTrace();    
        }   
        
        sheet = wb.getSheetAt(0);    
        //得到总行数    
        int rowNum = sheet.getLastRowNum();    
        row = sheet.getRow(0);    
        int colNum = row.getPhysicalNumberOfCells();    
        //正文内容应该从第二行开始,第一行为表头的标题    
        for (int i = 1; i <= rowNum; i++) {    
            row = sheet.getRow(i); 
            if(row!=null)
            {
	            int j = 0;    
	            while (j<colNum) 
	            {    
			        //每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据    
			        //也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean    
	            	if(getStringCellValue(row.getCell((short) j))==null||getStringCellValue(row.getCell((short) j)).trim().equals(""))
	            	{            		
	            		 str += "N" + ";";
	            	}else{            		
	            		 str += getStringCellValue(row.getCell((short) j)).trim() + ";";
	            	}
	                j ++;
	            }    
	            content.put(i, str);    
	            str = "";
            }
        }    
        return content;    
    } 
    public Map<Integer,String> readExcelContentByDate(InputStream is,int num) {    
        Map<Integer,String> content = new HashMap<Integer,String>();    
        String str = "";    
        try {    
            wb = new HSSFWorkbook(is);    
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
        sheet = wb.getSheetAt(0);    
        //得到总行数    
        int rowNum = sheet.getLastRowNum();    
        row = sheet.getRow(0);    
        int colNum = row.getPhysicalNumberOfCells();    
        //正文内容应该从第二行开始,第一行为表头的标题    
        for (int i = 1; i <= rowNum; i++) {    
            row = sheet.getRow(i); 
            if(row!=null)
            {
	            int j = 0;    
	            while (j<colNum) 
	            {    
			        //每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据    
			        //也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean   
	            	if(j==num){
	            		
	            		 str += getDateCellValue(row.getCell((short) j)).trim() + ";";
	            	}else{
	            		
	            		if(getStringCellValue(row.getCell((short) j))==null||getStringCellValue(row.getCell((short) j)).trim().equals(""))
		            	{            		
		            		 str += "N" + ";";
		            	}else{            		
		            		 str += getStringCellValue(row.getCell((short) j)).trim() + ";";
		            	}
	            	}
	            	
	                j ++;
	            }    
	            content.put(i, str);    
	            str = "";
            }
        }    
        return content;    
    } 
       
    /**   
     * 获取单元格数据内容为字符串类型的数据   
     * @param cell Excel单元格   
     * @return String 单元格数据内容   
     */   
    public String getStringCellValue(HSSFCell cell) {    
        String strCell = ""; 
        while(cell==null){
        	return strCell;
        }
        switch (cell.getCellType()) {    
        case HSSFCell.CELL_TYPE_STRING:    
            strCell = cell.getStringCellValue();    
            break;    
        case HSSFCell.CELL_TYPE_NUMERIC:    
            strCell = String.valueOf((long)cell.getNumericCellValue());    
            break;    
        case HSSFCell.CELL_TYPE_BOOLEAN:    
            strCell = String.valueOf(cell.getBooleanCellValue());    
            break;    
        case HSSFCell.CELL_TYPE_BLANK:    
            strCell = "";    
            break;    
        default:    
            strCell = "";    
            break;    
        }    
        if (strCell.equals("") || strCell == null) {    
            return "";    
        }    
        if (cell == null) {    
            return "";    
        }    
        return strCell;    
    }  
    
    public String getStringCellValueDate(HSSFCell cell) {    
        String curDateTime = ""; 
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        switch (cell.getCellType()) {    
        case HSSFCell.CELL_TYPE_STRING:    
        	curDateTime = DateTime.formatStrDateTime(cell.getStringCellValue(),"yyyy/M/d","yyyy-MM-dd");    
            break;    
        default:    
        	curDateTime = formatter.format( cell.getDateCellValue());
            break;    
        }
        
       
        return curDateTime;    
    } 
        
    /**   
     * 获取单元格数据内容为日期类型的数据   
     * @param cell Excel单元格   
     * @return String 单元格数据内容   
     */   
    private String getDateCellValue(HSSFCell cell) {    
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    	  Date d=cell.getDateCellValue();
    	  
        return formatter.format(d);  
    }
    /**   
     * 获取单元格数据内容为日期类型的数据   
     * @param cell Excel单元格   
     * @return String 单元格数据内容   
     */   
    private String getTimeCellValue(HSSFCell cell)
    {    
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
    	Date d=cell.getDateCellValue();
        return formatter.format(d);  
    }
    //导出Excel时设定标题格式
    public static void setResponseHeader(HttpServletResponse response, String fileName)
	throws Exception {

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		response.setDateHeader("Expires", 0);

	}
    //导出Excel时设定单元格样式
    public static HSSFCellStyle getAlignLeftStyle(HSSFWorkbook wb,int size,String flag) {

		HSSFCellStyle style = wb.createCellStyle();
		if(flag.equals("left"))
		{
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		}else if(flag.equals("right"))
		{
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		}
		else
		{
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		}
		Font font = wb.createFont();   
        font.setFontHeightInPoints((short)size);
        font.setFontName("宋体"); 
        style.setFont(font);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		return style;
	}
    
    public static void setRegionStyle(HSSFSheet sheet, Region region , HSSFCellStyle cs) {
	    
        for (int i = region.getRowFrom(); i <= region.getRowTo(); i ++) {
            HSSFRow row = sheet.getRow(i);
            if(region.getColumnFrom()!=region.getColumnTo()){
            for (int j = region.getColumnFrom(); j <= region.getColumnTo(); j++) {
                HSSFCell cell = row.getCell((short)j);
                if(cell==null)cell = row.createCell((short)j);
                cell.setCellStyle(cs);
            }
            }
        }
 }
}
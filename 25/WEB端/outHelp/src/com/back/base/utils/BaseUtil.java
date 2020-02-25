/**
 * 
 */
package com.back.base.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * @author dl
 * 
 */
public class BaseUtil {

	// -- header 常量定义 --//
	private static final String HEADER_ENCODING = "encoding";
	private static final String HEADER_NOCACHE = "no-cache";
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final boolean DEFAULT_NOCACHE = true;
	// -- content-type 常量定义 --//
	private static final String TEXT_TYPE = "text/plain";
	private static final String JSON_TYPE = "application/json";
	private static final String XML_TYPE = "text/xml";
	private static final String HTML_TYPE = "text/html";
	private static final String JS_TYPE = "text/javascript";
	
	private static final String[] SPECIALPROVINCE = new String[]{"110000","120000","500000","810000","820000","310000"};

	/**
	 * 直接输出JSON.
	 * 
	 * @param:@param response
	 * @param:@param jsonString json字符串.
	 * @param:@param headers
	 * @return:void
	 * @author:dailu
	 * @time:2013-12-2 上午9:34:37
	 * 
	 * 
	 */
	public static void renderJson(HttpServletResponse response,
			final String jsonString, final String... headers) {
		renderResponse(response, JSON_TYPE, jsonString, headers);
	}

	/**
	 * 直接输出文本.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderText(HttpServletResponse response,
			final String text, final String... headers) {
		renderResponse(response, TEXT_TYPE, text, headers);
	}

	/**
	 * 直接输出HTML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(HttpServletResponse response,
			final String html, final String... headers) {
		renderResponse(response, HTML_TYPE, html, headers);
	}

	/**
	 * 直接输出XML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(HttpServletResponse response,
			final String xml, final String... headers) {
		renderResponse(response, XML_TYPE, xml, headers);
	}

	/**
	 * 直接输出内容的简便函数. eg. render("text/plain", "hello", "encoding:GBK");
	 * render("text/plain", "hello", "no-cache:false"); render("text/plain",
	 * "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void renderResponse(HttpServletResponse response,
			final String contentType, final String content,
			final String... headers) {
		response = initResponse(response, contentType, headers);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(content);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			out.close();
		}
	}

	/**
	 * 分析并设置contentType与headers.
	 */
	public static HttpServletResponse initResponse(
			HttpServletResponse response, final String contentType,
			final String... headers) {
		// 分析headers参数
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		for (String header : headers) {
			String headerName = StringUtils.substringBefore(header, ":");
			String headerValue = StringUtils.substringAfter(header, ":");

			if (StringUtils.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
				encoding = headerValue;
			} else if (StringUtils.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
				noCache = Boolean.parseBoolean(headerValue);
			} else {
				throw new IllegalArgumentException(headerName
						+ "不是一个合法的header类型");
			}
		}

		// 设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			setNoCacheHeader(response);
		}
		return response;
	}

	/**
	 * 设置客户端无缓存Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader("Expires", 0);
		// Http 1.1 header
		response.setHeader("Cache-Control", HEADER_NOCACHE);
	}
	
	/**
	 * 转换map中的数字
	 * @param:@param map
	 * @param:@param mapKey
	 * @param:@return
	 * @param:@throws ParseException
	 * @return:Number
	 * @author:dailu
	 * @time:2013年12月27日 上午11:52:29
	 */
	public static Number mapValue2Number(Map<String, String> map, String mapKey) throws ParseException {
		return NumberFormat.getInstance().parse(map.get(mapKey)==null?"0":String.valueOf(map.get(mapKey)));
	}
	
	
	/**
     * 公共数据列表
     * 
     * @param formBean
     * @param request
     * @param response
     * @return
     * @throws Exception
     
    public void findCommonTable(DefaultForm formBean, IRequest request, IResponse response) throws Exception {
    	Map<String, String>  m = getCommonTableMap();//读取properties配置文件
    	String sqlProperties = request.getParameter("sqlProperties");
    	String fieldProperties = request.getParameter("fieldProperties");
    	String titleProperties = request.getParameter("titleProperties");
    	String sql = "";
    	String[] field = new String[]{};
    	String[] title = new String[]{};
    	if(m != null && m.size() > 0){
    		if(!"".equals(m.get(sqlProperties)) && m.get(sqlProperties) != null){
    			sql = m.get(sqlProperties);//自定义SQL,注意中文乱码问题
    		}else{
    			return;
    		}
    		if(!"".equals(m.get(fieldProperties)) && m.get(fieldProperties) != null){
    			field = m.get(fieldProperties).split(",");//自定义easyui中datagrid的field属性,注意中文乱码问题
    		}
    		if(!"".equals(m.get(titleProperties)) && m.get(titleProperties) != null){
    			title = m.get(titleProperties).split(",");//自定义easyui中datagrid的title属性,注意中文乱码问题
    		}
    	}
    	//分页模板
    	JSONArray jsonArray = new JSONArray();
    	JSONArray jsonColumns = new JSONArray();
    	String page = request.getParameter("page");
    	String rows = request.getParameter("rows");
        int start = Integer.parseInt((page == null || page == "0") ? "1":page);//起始条数
        int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows); //查询条数
    	List<Map<String, String>> list  = getIUtilBs().queryObjectListBySql(sql,start,number);
		for (Map<String, String> map : list) {
			JSONObject jsonObj = new JSONObject();
			if(field != null && field.length > 0){
				for (int i = 0; i < field.length; i++) {
					jsonObj.put(field[i], map.get(field[i]));
				}
			}
			jsonArray.add(jsonObj);
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
        jsonMap.put("total", getIUtilBs().getRecordCountByCustomSql(sql));//total键 存放总记录数，必须的  
        jsonMap.put("rows", jsonArray);//rows键 存放每页记录 list 
        if(field != null && field.length > 0){
        	for (int i = 0; i < field.length; i++) {
        		JSONObject jsonColumnsObj = new JSONObject();
        		jsonColumnsObj.put("field", field[i]);
        		jsonColumnsObj.put("title", title[i]);
        		jsonColumns.add(jsonColumnsObj);
        	}
        }
        jsonMap.put("columns", jsonColumns);
        renderJson((HttpServletResponse)response.getServletResponse(), JSONObject.fromObject(jsonMap).toString());
    }
    */
	
    /**
     * 读取properties配置文件
     * @param:@return
     * @return:Map<String,String>
     * @author:dailu
     * @time:2014-2-14 上午10:27:09
     */
    private Map<String, String> getCommonTableMap() {
        Properties prop = loadProperties(); 
        Map result=new HashMap(prop);
        return result!=null?result:new HashMap();
    }
    
    /**
     * 读取properties配置文件
     * @param:@return
     * @return:Properties
     * @author:dailu
     * @time:2014-2-14 上午10:27:04
     */
    private Properties loadProperties() {
        Properties prop = new Properties(); 
        try { 
        	InputStream in = this.getClass().getResourceAsStream("common.properties"); 
            prop.load(in); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
        return prop;
    }
}

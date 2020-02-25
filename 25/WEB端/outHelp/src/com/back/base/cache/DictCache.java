package com.back.base.cache;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.back.base.AbstractEntity;
import com.back.base.model.DataDic;
import com.back.base.model.DicType;
import com.back.base.service.DataDicService;
import com.back.base.service.DicTypeService;
import com.back.base.utils.ReflectUtil;


public class DictCache {
	private String state;
	private static Map<String,Map> dictCache = new ConcurrentHashMap<String,Map>(); 
	private static final Logger logger = Logger.getLogger(DictCache.class);
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/*	 
	 *加载字典初始化
	 */
	public void init(){
		logger.info("----------初始化数据库字典---------");
		
		DicTypeService dicTypeService = (DicTypeService)SpringBeanFactoryUtils.getBean("dicTypeService");
		
		DataDicService  dataDicService = (DataDicService)SpringBeanFactoryUtils.getBean("dataDicService");
		List<DicType>  dtRs  = dicTypeService.selectAll(new DicType());
		for(DicType dt :dtRs){
			DataDic dataDic = new DataDic ();
			dataDic.setTypeId(dt.getPkId());
			List<DataDic>    dataDicRs =  dataDicService.selectAll(dataDic);
			Map<String,String> dicMap = new HashMap<String,String>();
			System.out.println("++++++++++++++++++++++++++++++");
			for(DataDic dd : dataDicRs){
				dicMap.put(String.valueOf(dd.getBusCode()),String.valueOf(dd.getDicValue()));
			}
			dictCache.put(dt.getBusCode(), dicMap);
		}
		
		logger.info("----------初始化字典完毕---------");
	 }
	/*	 
	 *通过字典的CODE和值来获得字典编译后的值
	 */
	public static String getDictValueByDictCode(String dictCode,String code){
		Map dictMap = dictCache.get(dictCode);
		if(dictMap==null){
			return code;
		}else{
			Object newCode = dictMap.get(code);
			if(newCode==null){
				
				return code;
			}else{
				
				return (String)newCode;
			}
		}
		
	}
	
	/*	 
	 *通过字典的CODE来获得字典
	 */
	public static Map getDictByDictCode(String dictCode){
		Map dictMap = dictCache.get(dictCode);
		return dictMap;
	}
	
	public static AbstractEntity dictObj(AbstractEntity ae){
		try {
			if(ae.dicMap()!=null){
				Iterator iter = ae.dicMap().entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String key = entry.getKey().toString();
					String val = entry.getValue().toString();
					String paramValue = getDictValueByDictCode(val,(String) getFieldValueByName(key.toUpperCase().charAt(0)+key.substring(1, key.length()),ae));
					Class c  = ae.getClass();
					if(ReflectUtil.getField(ae,key+"Mc") != null ){
						key = key.toUpperCase().charAt(0)+key.substring(1, key.length())+"Mc";
					}else if(ReflectUtil.getField(ae,key) != null ){
						
						key = key.toUpperCase().charAt(0)+key.substring(1, key.length());
						
					}else{
						key=null;
					}
					if(key!=null){
						Method m = c.getMethod("set" + key,Class.forName("java.lang.String"));
						m.invoke(ae,paramValue);
						
					}
				}
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ae;
	}
	
	public static AbstractEntity dictHtml(AbstractEntity ae){
		try {
			if(ae.dicMap()!=null){
				Iterator iter = ae.dicMap().entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String key = entry.getKey().toString();
					String val = entry.getValue().toString();//"status","ITEMZT");//需要翻译的字段和对应的字典
					Map dictMap = dictCache.get(val);
					StringBuffer selHtml = new StringBuffer();
					if(dictMap!=null){
						String newKey =  key.toUpperCase().charAt(0)+key.substring(1, key.length());
						String nowVal = (String)getFieldValueByName(newKey,ae);  //数据库存放的值
						Object mcObj = getFieldValueByName(newKey+"Mc",ae);
						String mcVal = (String)(mcObj==null?"":mcObj);
						Iterator iter1 = dictMap.entrySet().iterator();
						selHtml.append("<select name=\""+key+"\"  id=\""+key+"\" ");
						if(!mcVal.equals("")){
							selHtml.append("class=\"validate[required]\"");
						}
						selHtml.append(">");
						selHtml.append("<option value=\"\">--请选择--</option>");
						
						List arrayList = new ArrayList(dictMap.entrySet());
						
						Collections.sort(arrayList, new Comparator() {
							   public int compare(Object o1, Object o2) {
							    Map.Entry obj1 = (Map.Entry) o1;
							    Map.Entry obj2 = (Map.Entry) o2;
							    return (obj1.getKey()).toString().compareTo((String) obj2.getKey());
							   }
						});
						
						for (Iterator iter2 = arrayList.iterator(); iter2.hasNext();) {
							Map.Entry opentry = (Map.Entry) iter2.next();
							String opkey = opentry.getKey().toString();
							String opval = opentry.getValue().toString();
							if(opkey.equals(nowVal)){
								selHtml.append("<option value=\""+opkey+"\"  selected>"+opval+"</option>");
							}else{
								selHtml.append("<option value=\""+opkey+"\">"+opval+"</option>");
							}
							
						}
						selHtml.append("</select>");
					}
					
					Class c  = ae.getClass();
					if(ReflectUtil.getField(ae,key+"Mc") != null ){
						key = key.toUpperCase().charAt(0)+key.substring(1, key.length())+"Mc";
					}else if(ReflectUtil.getField(ae,key) != null ){
						
						key = key.toUpperCase().charAt(0)+key.substring(1, key.length());
						
					}else{
						key=null;
					}
					if(key!=null){
						Method m = c.getMethod("set" + key,Class.forName("java.lang.String"));
						m.invoke(ae,selHtml.toString());
						
					}
				}
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ae;
	}
	
	/** 
	 * 根据属性名获取属性值 
	 * */  
	public static Object getFieldValueByName(String fieldName, Object o) {  
	    try {    
	          String getter = "get" + fieldName;    
	          Method method = o.getClass().getMethod(getter, new Class[] {});    
	          Object value = method.invoke(o, new Object[] {});    
	          return value;    
	     } catch (Exception e) {    
	          return null;    
	     }    
	}
	/*	 
	 *通过字典的CODE来获得字典列表
	 */
	public static Map getDictListByDictType(String dictCode){
		return dictCache.get(dictCode);
	}
	/*	 
	 *修改字典
	 */
	public static void updateDict(String dictCode,String hql){
		/*Map oldDicMap = dictCache.get(dictCode);
		if(oldDicMap != null){
			dictCache.remove(dictCode);
		}
		IDBSupportService dbService = (IDBSupportService) ServiceLocator.getBean("IDBSupportService3");
		
		try {
			ArrayList dictList = (ArrayList) dbService.getJdbcTemplate().query(hql, new CodeNameBeanRowMapper());
			Map<String,String> dicMap = new HashMap<String,String>();
			for(int i=0;i<dictList.size();i++){
				CodeNameBean codeName = (CodeNameBean) dictList.get(i);
				dicMap.put(String.valueOf(codeName.getCode()),String.valueOf( codeName.getName()));
			}
			dictCache.put(dictCode, dicMap);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	/*	 
	 *删除字典的显示值
	 */
	public static void updateChildDict(String dictCode,String removeCode){
		Map oldDicMap = dictCache.get(dictCode);
		oldDicMap.remove(removeCode);
	}
	
	public static void updateDict(String dictCode , Map updateDic){
		dictCache.remove(dictCode);
		dictCache.put(dictCode, updateDic);
	}
	/*	 
	 *删除字典
	 */
	public static void removeDict(String dictCode){
		dictCache.remove(dictCode);
	}
	
	
}

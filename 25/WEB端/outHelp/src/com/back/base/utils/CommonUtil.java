package com.back.base.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.util.StringUtils;

public class CommonUtil {

	/**
	 * 获取查询条件对象
	 * 
	 * @param fieldName
	 *            查询条件属性名称
	 * @param fieldValue
	 *            查询条件属性值
	 * @param t
	 * @return
	 * 
	 */
	public static <T> T getConditionObject(String fieldName, String fieldValue, T t)  {
		if (StringUtils.hasText(fieldName) && StringUtils.hasText(fieldValue)) {
			String[] fieldNames = fieldName.split(",");
			String[] fieldValues = fieldValue.split(",");
			try {
				if (fieldNames.length == fieldValues.length) {
					for (int i = 0; i < fieldNames.length; i++) {
						Field[] fields = t.getClass().getDeclaredFields();
						for (Field field : fields) {
							if (fieldNames[i].equals(field.getName())) {
								String methodName = "set" + fieldNames[i].substring(0, 1).toUpperCase() + fieldNames[i].substring(1);// setName
								Method method = t.getClass().getMethod(methodName, fieldValue.getClass());
								method.invoke(t, fieldValues[i]);
							}
						}
					}

				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return t;
	}
}

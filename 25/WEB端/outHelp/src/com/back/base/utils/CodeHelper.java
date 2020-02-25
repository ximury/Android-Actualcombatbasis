package com.back.base.utils;

import java.util.HashMap;
import java.util.Map;

import com.back.base.model.CodeSetupVo;

/**
 * 算号器帮助类
 */
public class CodeHelper {
	//编号缓存
	private static Map<String,CodeSetupVo> codeMap = new HashMap();
	
	/**
	 * 获得编号
	 * @param key 编号类型关键字
	 * @return 编号
	 * @throws Exception
	 */
	public static String getCode(String key) throws Exception{
		//编号配置
		CodeSetupVo codeSetupVo;
		//通过key去寻找缓存中是否已加载该种类编号配置
		if(codeMap.get(key)==null){
			//如果没有找到加载配置则进行初始化
			codeSetupVo=new CodeSetupVo(key);
			//将组织好的配置格式放入缓存
			codeMap.put(key, codeSetupVo);
		}
		else{
			//如果缓存中已加载，直接从缓存中读取
			codeSetupVo=codeMap.get(key);
		}
		
		//调用配置的获得编号方法获得编号
		return codeSetupVo.getCode();
	}
	
	   /**
     * 通过基础编号获得编号
     * @param key 基础编号
     * @param digit 流水位数
     * @return 编号
     * @throws Exception
     */
    public static String getCodeByBaseCode(String key,int digit) throws Exception{
        //编号配置
        CodeSetupVo codeSetupVo;
        //通过key去寻找缓存中是否已加载该种类编号配置
        if(codeMap.get(key)==null){
            //如果没有找到加载配置则进行初始化
            codeSetupVo=new CodeSetupVo(key,digit);
            //将组织好的配置格式放入缓存
            codeMap.put(key, codeSetupVo);
        }
        else{
            //如果缓存中已加载，直接从缓存中读取
            codeSetupVo=codeMap.get(key);
        }
        
        //调用配置的获得编号方法获得编号
        return codeSetupVo.getCode();
    }
    
	/**
	 * 使用编号，在编号的业务单据保存时调用，彻底占用该编号
	 * @param key 编号类型关键字
	 * @param code 占用的编号
	 */
	public static void useCode(String key,String code){
		//编号配置
		CodeSetupVo codeSetupVo=codeMap.get(key);
		
		//调用配置的占用编号方法占用编号
		if(codeSetupVo!=null){
			codeSetupVo.useCode(code);
		}
	}
	
	/**
     * 回收编号，释放
     * @param key 编号类型关键字
     * @param code 占用的编号
     */
    public static int recoveryCode(String key,String code){
        //编号配置
        CodeSetupVo codeSetupVo=codeMap.get(key);
        
        //调用配置的占用编号方法占用编号
        if(codeSetupVo!=null){
            codeSetupVo.recoveryCode(code);
        }
        return 1;
    }
}

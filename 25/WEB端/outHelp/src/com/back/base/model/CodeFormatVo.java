package com.back.base.model;

import java.util.Date;
import com.back.base.cache.SpringBeanFactoryUtils;
import com.back.base.service.DataDicService;
import com.back.base.utils.DateUtil;

/**
 * 算号格式Vo
 */
public class CodeFormatVo {
	
	/**
	 * 格式类型枚举
	 * CONSTANTS - 常量
	 * SEQ - 序列
	 * DATE - 日期
	 */
    public enum FormatType{
    	CONSTANTS,SEQ,DATE;
    }
    
    
    /**
     * 格式类型
     */
	private FormatType formatType;
	
	private CodeSetupVo codeSetupVo;
	/**
	 * 常量
	 */
	private String constants_str;
	
	/**
	 * 序列 位数
	 */
	private int param_seq_digit;
	
	/**
	 * 序列 开始值
	 */
	private int param_seq_begin;
	
	/**
	 * 序列 当前最大值
	 */
	private int param_seq_max;
	
	/**
	 * 查询最大值自定义条件
	 */
	private String param_seq_cond; 

	
	/**
	 * 日期 格式串
	 */
	private String param_date_format;
	
	/**
	 * 日期 序列日期前缀 是年流水月流水还是日流水
	 */
	private String param_date_prefix;
	
	/**
	 * 日期 编号生成日期
	 */
	private String param_date_recode;
	
	/**
	 * 构造函数
	 * @param formatType 格式类型
	 * @param param 参数
	 * @param codeSetupVo 所属算号设置
	 */
	public CodeFormatVo(String param,CodeSetupVo codeSetupVo){
		//设置所属算号设置
		this.codeSetupVo=codeSetupVo;
		//开始分析
		if(param.substring(0,1).equals("{")){
			//如果第一个字符是{，说明是参数
			param=param.substring(1);
			//分割参数
			String paramArray[]=param.split(",");
			
			//第一个参数统一为类型
			//seq 序列
			if("seq".equals(paramArray[0])){
				//定义类型
				formatType=FormatType.SEQ;
				//位数
				param_seq_digit=Integer.parseInt(paramArray[1]);
				//开始值
				param_seq_begin=Integer.parseInt(paramArray[2]);
				//查询最大值自定义条件
				param_seq_cond=paramArray[3];
				
				//获得当前序列前缀串
				String prefixKeySetup=codeSetupVo.getCodeSeqPrefix(true);
				String formatKeySetup=codeSetupVo.getCodeSeqPrefix(false);
				 //根据前缀去数据库中查询当年数据库中最大号
				DataDicService  dataDicService = (DataDicService)SpringBeanFactoryUtils.getBean("dataDicService");
				String maxCode=dataDicService.findMaxCode(codeSetupVo.getKey(),codeSetupVo.getTable(), codeSetupVo.getColumn(), prefixKeySetup,param_seq_cond);
				//解析出当前开始序号
				if(maxCode!=null){
					int to=formatKeySetup.length()+param_seq_digit;
					if(to>maxCode.length()){
						to=maxCode.length();
					}
					String seq=maxCode.substring(formatKeySetup.length(),to);
					if(seq==null || seq.equals("")){
					    seq="0";
					}
					if(Integer.parseInt(seq)>param_seq_begin){
						param_seq_begin=Integer.parseInt(seq);
					}
				}
			}
			//date 日期
			else if("date".equals(paramArray[0])){
				//定义类型
				formatType=FormatType.DATE;
				param_date_format=paramArray[1];
				//序列前缀
				param_date_prefix=paramArray[2];
			}
			//其他类型报错
			else{
				throw new RuntimeException("{"+param+"}参数格式不对");
			}
		}
		else{
			//否则为常量
			//定义类型
			formatType=FormatType.CONSTANTS;
			//保存参数
			constants_str=param;
		}
	}

	/**
	 * 获得编号
	 * @return 编号
	 */
	public String getCode(){
		//根据格式类型不同进行不同处理
		//常量
		if(formatType==FormatType.CONSTANTS){
			//直接返回常量串
			return constants_str;
		}
		//序列
		else if(formatType==FormatType.SEQ){
			//如果当前最大值小于开始值
			if(param_seq_max<param_seq_begin){
				//将当前最大值调整为最小值
				param_seq_max=param_seq_begin;
			}
			//格式化序列号，前面补0
			return String.format("%0"+param_seq_digit+"d", ++param_seq_max);
		}
		//日期
		else if(formatType==FormatType.DATE){
			//记录当前日期
			codeSetupVo.setRecode(DateUtil.formatDate(new Date(), param_date_prefix));
			//直接返回日期值
			return DateUtil.formatDate(new Date(), param_date_format);
		}
		else{
			throw new RuntimeException("编号类型不存在");
		}
	}

	/**
	 * 获得编号流水前缀，只在日期类型上用
	 * @param isPrefix 是否是Prefix
	 * @return 编号流水前缀
	 */
	public String getCodeSeqPrefix(boolean isPrefix){
		//日期
		if(formatType==FormatType.DATE){
			//直接返回日期值
			if(isPrefix){
				return DateUtil.formatDate(new Date(), param_date_prefix);
			}
			else{
				return DateUtil.formatDate(new Date(), param_date_format);
			}
		}
		return "";
	}
	
	/**
	 * 获得格式类型
	 * @return 格式类型
	 */
	public FormatType getFormatType() {
		return formatType;
	}

	/**
	 * 获得日期 格式串
	 * @return 日期 格式串
	 */
	public String getParam_date_format() {
		return param_date_format;
	}
	
	/**
	 * 获得日期 序列日期前缀
	 * @return 日期 序列日期前缀
	 */
	public String getParam_date_prefix() {
		return param_date_prefix;
	}

	/**
	 * 获得日期 编号生成日期
	 * @return 日期 编号生成日期
	 */
	public String getParam_date_recode() {
		return param_date_recode;
	}

	/**
	 * 设置日期 编号生成日期
	 * @param param_date_recode 日期 编号生成日期
	 */
	public void setParam_date_recode(String param_date_recode) {
		this.param_date_recode = param_date_recode;
	}

	/**
	 * @return the param_seq_begin
	 */
	public int getParam_seq_begin() {
		return param_seq_begin;
	}

	/**
	 * @param param_seq_begin the param_seq_begin to set
	 */
	public void setParam_seq_begin(int param_seq_begin) {
		this.param_seq_begin = param_seq_begin;
	}

	/**
	 * @return the param_seq_max
	 */
	public int getParam_seq_max() {
		return param_seq_max;
	}

	/**
	 * @param param_seq_max the param_seq_max to set
	 */
	public void setParam_seq_max(int param_seq_max) {
		this.param_seq_max = param_seq_max;
	}
	
	
}

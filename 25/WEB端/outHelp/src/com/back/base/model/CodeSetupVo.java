package com.back.base.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.back.base.cache.SpringBeanFactoryUtils;
import com.back.base.service.DataDicService;
import com.back.base.utils.DateUtil;

/**
 * 算号设置Vo
 */
public class CodeSetupVo {
	private DataDicService  dataDicService = (DataDicService)SpringBeanFactoryUtils.getBean("dataDicService");
	
    /**
     * 关键字
     */
    private String key=null;
    
    /**
     * 编号对应表名
     */
    private String table=null;
    
    /**
     * 编号对应字段名
     */
    private String column=null;
    
    /**
     * 编号格式
     */
    private List format = new ArrayList();
    
    /**
     * 编号池
     */
    private Map pool = new TreeMap();
    
    /**
     * 编码格式列表
     */
    private Map codeFormatList = new HashMap();
    
    /**
     * 构造函数
     * @param key 编码关键字 
     */
    public CodeSetupVo(String key){
        codeSetupVo(key,-1);
    }

    /**
     * 构造函数
     * @param key 编码关键字 
     */
    public CodeSetupVo(String key,int digit){
        codeSetupVo(key,digit);
    }
        
    /**
     * 构造方法
     * @param key 编码关键字
     * @param digit 流水位数
     * @param baseCode 基础编号
     */
    public void codeSetupVo(String key,int digit){
        //初始化
        this.key=key;
        init();
        if(digit>-1){
            String[] keyInfo = key.split("\\.");
            codeFormatList.put(key, keyInfo[2]+"{seq,"+digit+",0, }!"+keyInfo[0]+","+keyInfo[1]);
        }
        
        //取格式设置
        String formatSetup = (String)codeFormatList.get(key);
        if(formatSetup==null){
            throw new RuntimeException("关键字"+key+"不存在");
        }
        
        //分析格式设置
        //首先通过!分割，分割出格式和数据库表
        String[] formatSetupArray=formatSetup.split("!");
        formatSetup=formatSetupArray[0];
        //再分割数据库表设置
        String[] tableSetupArray=formatSetupArray[1].split(",");
        table=tableSetupArray[0];
        column=tableSetupArray[1];
        
        //分析格式设置
        //将{}替换，替换成!{!，用!作为段落的分割，用{开头判断是参数的开始
        formatSetup=formatSetup.replace("{", "!{");
        formatSetup=formatSetup.replace("}", "!");
        //根据!进行分割
        String[] formatArray=formatSetup.split("!");
        //遍历分割后的数组，单独设置配置项
        for(int i=0;i<formatArray.length;i++){
            //如果只有开头或者结尾是关键符号，不进行分割，不知道是否是BUG，为了防止这种情况替换掉多余的关键符号
            formatArray[i]=formatArray[i].replace("!", "");
            //判断分割后的数组项是什么，初始化成不同类型的配置对象
            if(formatArray[i].equals("")){
                //如果是空的，直接跳过
                continue;
            }
            else{
                format.add(new CodeFormatVo(formatArray[i],this));
            }
        }
        
        //加载数据库中的老号
        List codePool=dataDicService.findCodePool(key);
        for(int i=0;i<codePool.size();i++){
            CodePoolVo codePoolVo = (CodePoolVo)codePool.get(i);
            pool.put(codePoolVo.getCodeValue(), codePoolVo);
        }
    }

    /**
     * 初始化函数
     */
    private void init(){
        //1.com.back.base.model.CodeSetupVo中 init方法添加规则,注意BS_BUY_BILL.BILL_NO和BS_BUY_BILL,BILL_NO的符号的差别
        //如果是新增，付款申请单号自动生成，规则为年月日+四位流水号
    	codeFormatList.put("BS_PAY_APPLY.APPLY_NO", "{date,yyMMdd,yyMMdd}{seq,4,0, }!BS_PAY_APPLY,APPLY_NO");
    	
    	codeFormatList.put("BS_BUY_BILL.BILL_NO", "{date,yyMMdd,yyMMdd}{seq,4,0, }!BS_BUY_BILL,BILL_NO");
    	codeFormatList.put("BS_TRANSFER.TRANSFER_NO", "{date,yyMMdd,yyMMdd}{seq,4,0, }!BS_TRANSFER,TRANSFER_NO");
    }
    
    /**
     * 获得编号
     * @return 编号
     */
    public String getCode(){
        //定义编号
        String code=null;
        
        //遍历号池，读取编号
        Iterator it = pool.values().iterator();
        while(it.hasNext()){
            //取出号池中编号
            CodePoolVo codePool = (CodePoolVo)it.next();
            //如果该编号有有效期
            if(codePool.getValidity()!=null && !codePool.getValidity().equals("")){
                //判断该编号是否过期，如果过期了跳过
                //寻找日期配置项
                CodeFormatVo codeFormatVo=null;
                for(int i=0;i<format.size();i++){
                    codeFormatVo=(CodeFormatVo)format.get(i);
                    if(codeFormatVo.getFormatType()==CodeFormatVo.FormatType.DATE){
                        break;
                    }
                    else{
                        codeFormatVo=null;
                    }
                }
                //判断是否找到日期类型的设置，没有日期不过期
                if(codeFormatVo!=null){
                    //计算时间差
                    if(DateUtil.formatDate(new Date(), codeFormatVo.getParam_date_format()).compareTo(codePool.getValidity())>0){
                        //过期并已超时的移除
                        if(codePool.getReleaseValue()==null || DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss").compareTo(codePool.getReleaseValue())>0){
                            it.remove();
                            dataDicService.deleteCodePool(codePool.getCodeValue());
                        }
                        continue;
                    }
                }
            }
            //如果该编号已经被占用过
            if(codePool.getReleaseValue()!=null && !codePool.getReleaseValue().equals("")){
                //判断该编号是否在占用期内，如果在占用跳过
                if(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss").compareTo(codePool.getReleaseValue())<0){
                    continue;
                }
                else{
                    //清空
                    codePool.setReleaseValue(null);
                    dataDicService.updateRelease(codePool.getCodeValue(),"");
                }
            }
            
            //如果之前的判断没有跳过，则认为该编号可用
            //占用编号，占用1小时
            codePool.setReleaseValue(DateUtil.formatDate(DateUtil.getDateByHour(new Date(), 1), "yyyy-MM-dd HH:mm:ss"));
            dataDicService.updateRelease(codePool.getCodeValue(),codePool.getReleaseValue());
            //将编号付值为当年号池内编号
            code=codePool.getCodeValue();
            //已经获得到编号，所以跳出
            break;
        }
        
        //如果编号为空，即说明号池中没有可用编号，则进行领号
        if(code==null){
            //1次领号领1个
            for(int i=0;i<1;i++){
                //定义号池项Vo
                CodePoolVo codePoolVo = new CodePoolVo();
                //定义临时编号
                StringBuffer tempCode=new StringBuffer();
                //遍历编号设置的格式，单独生成每段编号，并拼接
                for(int j=0;j<format.size();j++){
                    //获得每个设置项
                    CodeFormatVo codeFormatVo =(CodeFormatVo)format.get(j);
                    //调用设置项的获取编号方法，获得该段落的编号
                    tempCode.append(codeFormatVo.getCode());
                    //如果该项是日期型
                    if(codeFormatVo.getFormatType()==CodeFormatVo.FormatType.DATE){
                        //设置有效期
                        codePoolVo.setValidity(DateUtil.formatDate(new Date(), codeFormatVo.getParam_date_format()));
                        //设置重编号日期
                        codePoolVo.setRecode(DateUtil.formatDate(new Date(), codeFormatVo.getParam_date_prefix()));
                    }
                }
                
                //将该编号滚入号池
                codePoolVo.setCodeValue(tempCode.toString());
                codePoolVo.setKeyValue(this.key);
                codePoolVo.setCodeSetupVo(this);
                pool.put(tempCode.toString(), codePoolVo);
                dataDicService.insertCodePool(codePoolVo);
            }
            //递归调用获得编号方法，获取刚才生成的编号
            return getCode();
        }
        else{
            //返回号池中获取的编号
            return code;
        }
    }
    
    /**
     * 获得编号流水前缀
     * @param isPrefix 是否是Prefix
     * @return 编号流水前缀
     */
    public String getCodeSeqPrefix(boolean isPrefix){
        //定义临时编号
        StringBuffer tempCode=new StringBuffer();
        //遍历编号设置的格式，单独生成每段编号，并拼接
        for(int j=0;j<format.size();j++){
            //获得每个设置项
            CodeFormatVo codeFormatVo =(CodeFormatVo)format.get(j);
            //如果遍历到序列则跳出
            if(codeFormatVo.getFormatType()==CodeFormatVo.FormatType.SEQ){
                break;
            }
            //如果遍历到日期
            if(codeFormatVo.getFormatType()==CodeFormatVo.FormatType.DATE){
                //根据前缀设置获得日期数据，获取完后就跳出
                tempCode.append(codeFormatVo.getCodeSeqPrefix(isPrefix));
                break;
            }
            //调用设置项的获取编号方法，获得该段落的编号
            tempCode.append(codeFormatVo.getCode());
        }
        //返回前缀
        return tempCode.toString();
    }
    
    /**
     * 获得编号流水前缀（只获得日期部分)
     * @return 编号流水前缀
     */
    public String getCodeSeqPrefixOnlyDate(){
        //定义临时编号
        for(int j=0;j<format.size();j++){
            //获得每个设置项
            CodeFormatVo codeFormatVo =(CodeFormatVo)format.get(j);
            //如果遍历到日期
            if(codeFormatVo.getFormatType()==CodeFormatVo.FormatType.DATE){
                //根据前缀设置获得日期数据，获取完后就跳出
                return codeFormatVo.getCodeSeqPrefix(true);
            }
        }
        return "";
    }
    
    /**
     * 设置重新编号
     * @param recode 重新编号时间
     */
    public void setRecode(String recode){
        //遍历格式元素
        for(int i=0;i<format.size();i++){
            //找到序列设置
            CodeFormatVo codeFormatVo = (CodeFormatVo)format.get(i);
            if(codeFormatVo.getFormatType()==CodeFormatVo.FormatType.SEQ){
                //判断两个时间，如果新传进来的时间大于旧的时间，证明时间点已过
                String oldRecode=codeFormatVo.getParam_date_recode();
                if(oldRecode==null){
                    oldRecode="";
                }
                if(recode.compareTo(oldRecode)>0){
                    //重置时间
                    codeFormatVo.setParam_date_recode(recode);
                    //序列值初始化
                    codeFormatVo.setParam_seq_max(codeFormatVo.getParam_seq_begin());
                }
            }
        }
    }
    
    /**
     * 使用编号
     * @param code 编号
     */
    public void useCode(String code){
        //移除号池中该编号
        pool.remove(code);
        dataDicService.deleteCodePool(code);
    }
    
    /**
     * 释放编号
     * @param code 编号
     */
    public void recoveryCode(String code){
        //移除号池中该编号
        CodePoolVo codePoolVo = (CodePoolVo)pool.get(code);
        codePoolVo.setReleaseValue("");
        dataDicService.updateRelease(code,"");
    }
    
    /**
     * 获得编号对应表名
     * @return table 编号对应表名
     */
    public String getTable() {
        return table;
    }
    
    /**
     * 获得编号对应字段名
     * @return column 编号对应字段名
     */
    public String getColumn() {
        return column;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }
    
    
}

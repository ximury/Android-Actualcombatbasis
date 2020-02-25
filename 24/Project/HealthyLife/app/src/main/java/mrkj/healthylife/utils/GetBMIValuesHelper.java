package mrkj.healthylife.utils;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/26.
 */
public class GetBMIValuesHelper {
    public static final int VALUES_NULL = 0;
    public static final int EXTENUATION = 1;
    public static final int NORMAL_WEIGHT = 2;
    public static final int WEIGHT_OVER_WEIGHT = 3;
    public static final int SEVERLY_OBESE_ONE = 4;
    public static final int SEVERLY_OBESE_TWO = 5;
    public static final int SEVERLY_OBESE_THREE = 6;
    /**
     * 获取BMI值 进行 判断 这个人的身体肥胖程度
     *
     * @param height
     *            身高
     * @param weight
     *            体重
     * @return
     */
    // 身体质量指数(BMI)
    // BMI（Body Mass Index）
    // 指数也叫做身体质量指数，是目前国际上常用的衡量人体胖瘦程度以及是否健康的标准，比单纯的以体重认定更具准确性。
    // BMI适用于18岁至65岁的人士，不适用儿童、青少年、孕妇、乳母、老人及运动员等。
    private double getBMI_Value(int height, int weight) {
        // BMI的计算公式：体重除以身高的二次幂（xxkg/xxm6^）
        double height2 = height * 0.01;
        double value = weight / (Math.pow(height2, 2));
        DecimalFormat format = new DecimalFormat("#.0");
        // Log.i(TAG, "格式化之前的值:" + value);
        String str = format.format(value);
        double bmi = Double.parseDouble(str);
        return bmi;
    }

    /**
     * 获取健康信息
     * @param height
     * @param weight
     * @return
     */
    public String getHealthyMessage(int height, int weight){
        double bmi = getBMI_Value(height, weight);
        String bodyStr = null;
        if (bmi < 18.5) {// 体重过低
            bodyStr = "体重过低";
        } else if (bmi < 24) {// 体重正常
            bodyStr = "体重正常";
        } else if (bmi < 27) {// 体重偏胖
            bodyStr = "体重偏胖";
        } else if (bmi < 30) {// Ⅰ度肥胖
            bodyStr = "Ⅰ度肥胖";
        } else if (bmi < 35) {// Ⅱ度肥胖
            bodyStr = "Ⅱ度肥胖";
        } else {// Ⅲ度肥胖
            bodyStr = "Ⅲ度肥胖";
        }
        return bodyStr;
    }

    /**
     * 获取身体的类型
     *
     * @param BMI
     * @return
     */
    public int getHealthyType(double BMI) {
        int type = VALUES_NULL;
        if (BMI < 18.5) {// 体重过低
            type = EXTENUATION;
        } else if (BMI < 24) {// 体重正常
            type = NORMAL_WEIGHT;
        } else if (BMI < 27) {// 体重偏胖
            type = WEIGHT_OVER_WEIGHT;
        } else if (BMI < 30) {// Ⅰ度肥胖
            type = SEVERLY_OBESE_ONE;
        } else if (BMI < 35) {// Ⅱ度肥胖
            type = SEVERLY_OBESE_TWO;
        } else {// Ⅲ度肥胖
            type = SEVERLY_OBESE_THREE;
        }
        return type;
    }

    /**
     * 获取正常体重的范围
     * @param height
     * @return
     */
    //体指的标准范围18.5~24
    public Map<String,Double> getNormalWeightRange(int height){
        Map<String,Double> map = new HashMap<>();
        //获取正常范围的最大体重
        Double maxNormalWeight = Math.sqrt(24 * height);
        DecimalFormat maxFormat = new DecimalFormat("#.0");
        String maxStr = maxFormat.format(maxNormalWeight);
        Double maxNormalWeightValues = Double.parseDouble(maxStr);
        //获取正常范围的最小体重
        Double minNormalWeight = Math.sqrt(18.5 * height);
        DecimalFormat minFormat = new DecimalFormat("#.0");
        String minStr = minFormat.format(minNormalWeight);
        Double minNormalWeightValues = Double.parseDouble(minStr);
        //添加所需参数
        map.put("max",maxNormalWeightValues);
        map.put("min",minNormalWeightValues);
        return map;
    }
}

package workutil;

import java.util.regex.Pattern;

import A_lambda.A_1_expressionDemo;
import A_lambda.cn.com.ykse.cinemaprod.api.Test2;

/**
 * @author wangxiaohu
 * @version Id: Test.java, v0.1 2022年02月11日 17:55:50 wangxiaohu Exp $
 */
public class Test {
    private static Pattern encryptedPattern = Pattern.compile("Encrypted:\\{(.*)\\}");
    public static String SEC_KEY  = "cloudConfigKey0123";
    public static void main(String[] args)  throws Exception{
        String packageName = A_1_expressionDemo.class.getPackage().getName();
        System.out.println(packageName);
        packageName = Test2.class.getPackage().getName();
        System.out.println(packageName);

        String channelCode = "DEMOGWWH_12";
        int index = channelCode.indexOf("_");
        if(index>0) {
            String substring = channelCode.substring(0, index);
            System.out.println(substring);

        }
    }
}

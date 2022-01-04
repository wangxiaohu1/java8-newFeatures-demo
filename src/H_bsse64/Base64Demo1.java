package H_bsse64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author wangxiaohu
 * @version Id: H_Java8Base64Demo1.java, v0.1 2020年11月16日 11:27:30 wangxiaohu Exp $xxx
 */
public class Base64Demo1 {
    public static void main(String[] args) {
        try{
            // 编码
            String encoderString = Base64.getEncoder().encodeToString("java?".getBytes("utf-8"));
            System.out.println(encoderString);      //输出  amF2YT8=

            // 解码
            byte[] b = Base64.getDecoder().decode(encoderString);
            String decoderString = new String(b,"utf-8");
            System.out.println(decoderString);      //输出  java?

            // UrlEncoder
            encoderString = Base64.getUrlEncoder().encodeToString("java?".getBytes("utf-8"));
            System.out.println(encoderString);

        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }
}

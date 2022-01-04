package F_nullOptionalDemo1.demo;

import model.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author wangxiaohu
 * @version Id: OptionalDemo1.demo.F_Java8OptionalDemo2.java, v0.1 2020年11月13日 15:51:08 wangxiaohu Exp $
 * @Description java8 Optional类：一个可以方便检查空指针的工具/容器类
 */
public class OptionalDemo1 {
    private static final ThreadLocal<Map<String,String>> rpcLogData = new ThreadLocal<>();

    public static void main(String[] args) {
        String v0 = getFromRpcLogData1("key0","null-v0");
        System.out.println(v0);


        putToRpcLogData1("key1","v1");
        putToRpcLogData2("key2","v2");

        String v1 = getFromRpcLogData2("key1","null-v1");
        String v2 = getFromRpcLogData2("key2","null-v2");
        String v3 = getFromRpcLogData2("key3","null-v3");
        System.out.println("v1:" + v1);
        System.out.println("v2:" + v2);
        System.out.println("v3:" + v3);

        System.out.println("----");
        v1 = getFromRpcLogData1("key1","null-v1");
        v2 = getFromRpcLogData1("key2","null-v2");
        v3 = getFromRpcLogData1("key3","null-v3");
        System.out.println("v1:" + v1);
        System.out.println("v2:" + v2);
        System.out.println("v3:" + v3);

    }

    private static void putToRpcLogData1(String key, String value){
        Optional.ofNullable(rpcLogData.get()).
                orElseGet(()->{
                    rpcLogData.set(new HashMap());
                    System.out.println("rpcLogData.get返回为空,set new HashMap");
                    return rpcLogData.get();
                }
            ).put(key,value);
    }

    private static void putToRpcLogData2(String key, String value){
        Map<String,String> dataMap = rpcLogData.get();
        if(dataMap==null){
            dataMap = new HashMap<>();
            rpcLogData.set(dataMap);
        }
        dataMap.put(key, value);
    }

    private static String getFromRpcLogData1(String key, String defaultValue){
        try{
            //1. rpcLogData.get()==null,则不会执行map(m->m.get(key)) 这个代码，直接执行orElse
            //2. rpcLogData.get()!=null,则执行map(m->m.get(key)) 这个代码
            //3. m->m.get(key)==null后,会执行orElse
            //4. m->m.get(key)!=null后,会返回m.get(key)的值
            return Optional.ofNullable(rpcLogData.get()).
                    map(
                            m->m.get(key)
                    ).orElse(defaultValue);
        }catch (Throwable t){
            t.printStackTrace();
        }
        return defaultValue;
    }

    private static String getFromRpcLogData2(String key, String defaultValue){
        Map<String,String> map = rpcLogData.get();
        String value = null;
        if(map!=null){
            value = map.get(key);
        }
        if(value==null){
            value = defaultValue;
        }
        return value;
    }
}

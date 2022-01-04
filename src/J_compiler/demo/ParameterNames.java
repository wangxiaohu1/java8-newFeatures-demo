package J_compiler.demo;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author wangxiaohu
 * @version Id: J_Java8CompilerDemo1_ParameterNames.java, v0.1 2020年11月17日 15:45:23 wangxiaohu Exp $
 * @Description java8编译器新特性，参数名称的获取
 */
public class ParameterNames {
    public static void main(String[] values)  throws Exception{
        Method m = ParameterNames.class.getMethod("main" ,String[].class);
        for(Parameter p : m.getParameters()){
            System.out.println("Parameter: " + p.getName());
        }
    }
    //如果不使用–parameters参数来编译这个类，然后运行这个类，会得到下面的输出 : Parameter: arg0
    //如果使用–parameters参数来编译这个类，程序的结构会有所不同（参数的真实名字将会显示出来）: Parameter: values
}

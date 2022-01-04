package E_defaultMethod;

/**
 * @author wangxiaohu
 * @version Id: A_defaultMethodDemo3.java, v0.1 2020年11月13日 15:10:01 wangxiaohu Exp $
 * @Description java8接口的默认方法。默认方法的使用
 */
public class A_defaultMethodDemo3 {
    public static void main(String[] args) {
        AppleInterface1 inf = new MyInterface1();
        inf.say();
        // 输出：
        // E_defaultMethod.AppleInterface1
        // E_defaultMethod.CatInterface1
        // E_defaultMethod.AppleInterface1 hello
        // E_defaultMethod.MyInterface1
    }

}
class MyInterface1 implements AppleInterface1,CatInterface1{
    public void say(){
        AppleInterface1.super.say();
        CatInterface1.super.say();
        AppleInterface1.hello();

        System.out.println("E_defaultMethod.MyInterface1");
    }
}

interface AppleInterface1{
    default void say(){
        System.out.println("E_defaultMethod.AppleInterface1");
    }
    static void hello(){
        System.out.println("E_defaultMethod.AppleInterface1 hello");
    }
}
interface CatInterface1{
    default void say(){
        System.out.println("E_defaultMethod.CatInterface1");
    }
}


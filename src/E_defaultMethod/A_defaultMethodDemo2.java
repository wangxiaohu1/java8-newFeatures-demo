package E_defaultMethod;

/**
 * @author wangxiaohu
 * @version Id: A_defaultMethodDemo2.java, v0.1 2020年11月13日 13:59:47 wangxiaohu Exp $
 * @Description java8接口的默认方法，一个类实现了多个接口，这些接口有相同的默认方法如何处理？
 */
public class A_defaultMethodDemo2 implements AppleInterface, CatInterface{
    public void say(){
        AppleInterface.super.say();
    }

    public static void main(String[] args) {
        A_defaultMethodDemo2 demo = new A_defaultMethodDemo2();
        demo.say();
    }

}
interface AppleInterface{
    default void say(){
        System.out.println("我是 Apple");
    }
}
interface CatInterface{
    default void say(){
        System.out.println("我是 Cat");
    }
}

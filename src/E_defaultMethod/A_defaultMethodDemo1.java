package E_defaultMethod;

/**
 * @author wangxiaohu
 * @version Id: A_defaultMethodDemo1.java, v0.1 2020年11月13日 13:59:47 wangxiaohu Exp $
 * @Description java8接口的默认方法，使用defautl关键则来定义默认方法，可以有多个默认方法
 */
public interface A_defaultMethodDemo1 {
    //这个是默认方法
    default void say1(){
        System.out.println("我是默认方法1");
    }
    //这个是默认方法
    default void say2(){
        System.out.println("我是默认方法2");
    }
    //没有使用default就报错了
    //void say3(){
    //    System.out.println();
    //}

    //这个是抽象方法
    void say4();
}

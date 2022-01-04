package E_defaultMethod;

import java.util.function.Supplier;

/**
 * @author wangxiaohu
 * @version Id: B_staticMethodDemo1.java, v0.1 2020年11月13日 13:59:47 wangxiaohu Exp $
 * @Description java8接口的默认方法，静态默认方法：ava 8 的另一个特性是接口可以声明（并且可以提供实现）静态方法
 */
public class B_staticMethodDemo1 {
    public static void main(String[] args) {
        CarFactory car = CarFactory.create(Car::new);
        car.say();              //输出 我是CarFactory的默认方法

        CarFactory car2 = CarFactory.create(Car2::new);
        car2.say();             //输出 我是Car2的默认方法
    }
}
//定义个接口
interface CarFactory {
    //定义个静态方法
    static CarFactory create(Supplier<CarFactory> s){
        System.out.println("我是CarFactory的静态默认方法");
        return s.get();
    }
    default void say(){
        System.out.println("我是CarFactory的默认方法");
    }
}
class Car implements CarFactory{

}

class Car2 implements CarFactory{
    //可以复写默认方法
    @Override
    public void say(){
        System.out.println("我是Car2的默认方法");
    }
}

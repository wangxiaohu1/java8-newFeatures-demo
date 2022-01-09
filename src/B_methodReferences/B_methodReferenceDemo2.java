package B_methodReferences;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author wangxiaohu
 * @version Id: B_methodReferenceDemo2.java, v0.1 2020年11月10日 15:34:38 wangxiaohu Exp $
 * @Description java8的方法引用例子
 */
public class B_methodReferenceDemo2 {
    public static void main(String[] args) {

        //构造器引用：它的语法是Class::new，或者更一般的Class< T >::new
        Car car = Car.create(Car:: new);
        car.setName("car");
        List<Car> cars = Arrays.asList(car);

        //输出列表中的元素, 输出 car
        cars.forEach(System.out :: println);

        //静态方法引用：它的语法是Class::static_method, 输出 coll car
        cars.forEach(Car:: coll);

        //特定类的任意对象的方法引用：它的语法是Class::method。repair这个方法没有入参 , 输出 repair car
        cars.forEach(Car:: repair);

        //特定对象的方法引用：它的语法是instance::method。follow这个方法接收一个参数,  输出 police follow car
        Car police = Car.create(Car:: new);
        police.setName("police");
        cars.forEach(police :: follow);
    }

    /**
     * 定义一个内部类
     */

    static class Car{
        private String name;
        public void setName(String name){
            this.name=name;
        }

        @Override
        public String toString() {
            return name;
        }

        //Supplier是jdk1.8的接口
        public static Car create(Supplier<Car> s){
            return s.get();
        }
        public void repair(){
            System.out.println("repair " + this);
        }

        public void follow(Car antoherCar){
            System.out.println(this + " follow " + antoherCar);
        }
        public static void coll(Car car){
            System.out.println("coll " + car);
        }
    }
}



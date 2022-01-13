package A_lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author wangxiaohu
 * @version Id: A_4_AccessExternalVariables.java, v0.1 2020年11月10日 15:00:53 wangxiaohu Exp $
 * @Description Lambda 本质上是匿名内部类的改装，所以它使用到的变量都会隐式的转成 final
 */
public class A_4_AccessExternalVariables {
    private static String demo="1";

    public static void main(String[] args) {
        // Lambda 本质上是匿名内部类的改装，所以它使用到的变量都会隐式的转成 final
        int x1= 2;
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.forEach(e -> System.out.println(e + x1 + "::'" +demo));

        // 上面整个等价于
        final int x2= 2;
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.forEach(e -> System.out.println(e + x2));

        // 但是不能修改lambda表达式域外的变量，因为域外变量被lambda引用后，变量会被编译器隐式编译成final
        int x3 = 3;
        Stream.of(1).forEach(e->{
            System.out.println(x3-e);
            //尝试修改x3值，编译报错
            //x3 = 1;
        });
        System.out.println("end");
    }
}

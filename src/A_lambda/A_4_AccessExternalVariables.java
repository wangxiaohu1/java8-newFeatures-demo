package A_lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxiaohu
 * @version Id: A_4_AccessExternalVariables.java, v0.1 2020年11月10日 15:00:53 wangxiaohu Exp $
 * @Description Lambda 本质上是匿名内部类的改装，所以它使用到的变量都会隐式的转成 final
 */
public class A_4_AccessExternalVariables {

    public static void main(String[] args) {
        // Lambda 本质上是匿名内部类的改装，所以它使用到的变量都会隐式的转成 final
        int x1= 2;
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.forEach(e -> System.out.println(e + x1));

        // 上面整个等价于
        final int x2= 2;
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.forEach(e -> System.out.println(e + x2));

    }

}

package A_lambda;

import java.util.function.Function;
import java.util.function.IntToLongFunction;
import java.util.function.Supplier;

/**
 * Lambda语法
 * @author wangxiaohu
 * @version Id: A_expressionDemo.java, v0.1 2022年01月03日 19:03:08 wangxiaohu Exp $
 */
public class A_1_expressionDemo {
    public static void main(String[] args) {
        /**
         *
         * lambda表达式语法：(形参列表)->{函数体;}
         *   - 当只有一个形参,不用写()，例如： param -> { System,out.println(param);}
         *   - 当函数体只有一条语句,不用写{}，例如： param -> System,out.println(param);
         *   - 不用写return(如果是return语句)，例如： param-> param+"px";
         *
         */

        //------下面是例子---------//

        //1. 这是一个最简单的表达式，没有参数,返回值a, 由于函数体只有1个语句，所以可以失衡略{}和return
        Supplier<String> supplier = () -> {return "supplier";};
        supplier = () -> "a";
        System.out.println("supplier:" + supplier.get());

        //2. 1个参数，可以省略()
        Function function = (param) -> {return param + "+x";};
        function = param -> param + "+x";
        System.out.println(function.apply("a"));

        //3. 可以指定入参的参数类型

        IntToLongFunction function1 = (int param) -> Long.valueOf(param);

    }
}

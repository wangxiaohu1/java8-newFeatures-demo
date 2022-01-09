package A_lambda;

/**
 * @author wangxiaohu
 * @version Id: A_2_returnVoid.java, v0.1 2020年11月10日 13:58:35 wangxiaohu Exp $
 * @Description java8 Lamdba表达式例子，没有返回值的案例
 */
public class A_2_returnVoid {
    public static void main(String[] args) {
        A_2_returnVoid demo = new A_2_returnVoid();

        demo.operateVoid("Java", x -> System.out.println("Hello, " + x));

        demo.operateVoid("Java", (x) -> System.out.println("Hello, " + x));

        demo.operateVoid("Java", (String x) -> System.out.println("Hello, " + x));

        demo.operateVoid("Java", x -> {System.out.println("Hello, " + x);});

        //输出：Hello, Java

    }

    /**
     * 定义个接收1个参数，调用doVoid接口的私有方法
     * @param x
     * @param doVoid
     */
    private void operateVoid(String x,DoVoid doVoid){
        doVoid.action(x);
    }

    //声明一个没有返回值的接口
    interface DoVoid{
        void action(String x);
    }
}

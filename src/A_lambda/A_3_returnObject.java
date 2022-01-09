package A_lambda;

/**
 * @author wangxiaohu
 * @version Id: A_3_returnObject.java, v0.1 2020年11月10日 13:58:35 wangxiaohu Exp $
 * @Description java8 Lamdba表达式例子，有返回值的案例
 */
public class A_3_returnObject {
    public static void main(String[] args) {
        A_3_returnObject demo = new A_3_returnObject();

        int result1 = demo.operateMath(1,2, (x, y) -> x + y);

        int result2 = demo.operateMath(1,2, (int x, int y) -> x + y);

        int result3 = demo.operateMath(1,2, (x,y ) -> {return x + y;});

        System.out.println("结果: " + result1 + ";" + result2 + ";" + result3);

        //输出 结果: 3;3;3

    }

    /**
     * 定义个接收2个参数，调用doMath接口的私有方法
     * @param x 入参x
     * @param y 入参y
     * @param doMath 接口实现
     * @return 返回
     */
    private int operateMath(int x, int y, DoMath doMath){
        return doMath.action(x, y);
    }


    //声明一个接口，用于运算
    interface  DoMath{
        //声明一个接口
        int action(int x, int y);
    }
}

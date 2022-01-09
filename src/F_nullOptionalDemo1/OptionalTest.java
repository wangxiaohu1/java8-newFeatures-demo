package F_nullOptionalDemo1;

import java.util.Optional;

/**
 * @author wangxiaohu
 * @version Id: OptionalTest.java, v0.1 2020年06月22日 19:05:00 wangxiaohu Exp $
 */
public class OptionalTest {
    private String a;

    public void setA(String a) {
        this.a = a;
    }
    public static void main(String[] args) {

        testOptionalNullPointerException();


    }
    /**
     *
     * 在使用Optional时发现一个场景如下代码所示，当 OptionalTest 对象为null，Optional.ofNullable(str).ifPresent(c::setA)为报错NullPointerException
     * https://www.atatech.org/articles/174604
     *
     * 报错原因分析
     *    行执行Optional.ofNullable()方法后，ifPresent之前，对变量c执行了一个c.getClass()的方法调用。由于在此处c为null，所以抛出了空指针异常。程序报错的原因就在这里
     *
     */

    private static void testOptionalNullPointerException(){
        OptionalTest c = null;
        String str = null;
        Optional.ofNullable(str).ifPresent(c::setA);
    }
}

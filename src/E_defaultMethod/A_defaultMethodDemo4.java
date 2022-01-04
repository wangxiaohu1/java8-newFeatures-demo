package E_defaultMethod;

/**
 * @author wangxiaohu
 * @version Id: A_defaultMethodDemo4.java, v0.1 2020年11月13日 15:37:18 wangxiaohu Exp $
 */
public interface A_defaultMethodDemo4 extends AppleInterface2{
    @Override
    default String sayAndReturn() {

        return "";
    }
}
interface AppleInterface2{
    default String sayAndReturn(){
        System.out.println("say");
        return "return say";
    }
}

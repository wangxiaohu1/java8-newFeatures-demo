package A_lambda;
/**
 * @author wangxiaohu
 * @version Id: A_4_AccessInterfaceDefaultMethod.java, v0.1 2020年11月10日 15:00:53 wangxiaohu Exp $
 * @Description Lambda 内部不能访问接口默认方法，但是匿名内部类类可以访问
 */
public class A_4_AccessInterfaceDefaultMethod {
    public static void main(String[] args) {
        // 内部不能访问接口默认方法，编译报错
        //Integer2LongFunction fuc = a -> {return defaultToLongNumString(a);};

        // 但是匿名内部类类可以访问
        Integer2LongFunction func2 = new Integer2LongFunction(){
            @Override
            public String toLongNumString(Integer i) {
                return defaultToLongNumString(i);
            }
        };
    }
    interface Integer2LongFunction{
        String toLongNumString(Integer i);

        default String defaultToLongNumString(Integer i){
            return i.longValue() + "L";
        }
    }
}

package I_repeatingAnnotations;

import java.lang.annotation.*;

/**
 * @author wangxiaohu
 * @version Id: I_Java8RepeatingAnnotationsDemo1.java, v0.1 2020年11月17日 15:02:19 wangxiaohu Exp $
 * @Description java8新特性-重复注解
 */
public class RepeatingAnnotationsDemo1 {
    public static void main(String[] args) {
        for(Appender a : AppenderBulder.class.getAnnotationsByType(Appender.class)){
            System.out.println(a.value());
        }
    }

    @Target(ElementType.TYPE)
    @Retention( RetentionPolicy.RUNTIME )
    public @interface Appenders{
        Appender[] value();
    }
    @Target(ElementType.TYPE)
    @Retention( RetentionPolicy.RUNTIME )
    @Repeatable(Appenders.class)
    public @interface Appender{
        String value();
    }
    @Appender("appender1")
    @Appender("appender2")
    public interface AppenderBulder{

    }
}

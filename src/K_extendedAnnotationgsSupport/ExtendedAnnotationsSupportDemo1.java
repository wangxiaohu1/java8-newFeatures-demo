package K_extendedAnnotationgsSupport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author wangxiaohu
 * @version Id: I_Java8ExtendedAnnotationsSupportDemo1.java, v0.1 2020年11月17日 15:23:42 wangxiaohu Exp $
 * @Description java8新特性：拓宽注解的应用场景
 */
public class ExtendedAnnotationsSupportDemo1 {
    public static void main(String[] args) {
        Car<String> car = new @NotBlank Car();
        @NotBlank Collection<@NotBlank String> str = new ArrayList();
    }

    @Target({ElementType.TYPE_USE, ElementType.PARAMETER})
    public @interface NotBlank{

    }

    public static class Car< @NotBlank  T>  extends @NotBlank Object{
        public void repair()  throws @NotBlank Exception{

        }
    }
}

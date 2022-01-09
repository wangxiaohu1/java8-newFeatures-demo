package B_methodReferences;

import java.util.stream.Stream;

/**
 * Lambda方法引用
 * @author wangxiaohu
 * @version Id: B_methodReferenceDemo1.java, v0.1 2022年01月03日 22:11:31 wangxiaohu Exp $
 */
public class B_methodReferenceDemo1 {
    public static void main(String[] args) {
        /**
         * 使用 :: 来引用方法
         * list.forEach(String :: length);
         * list.forEach(s -> s.length()); 和上面效果一样
         */

        //静态方法引用: ClassName::staticMethodName
        Stream.of("a","b").forEach(System.out::println);

        //实例对象上的方法引用: instanceObject::methodName
        B_methodReferenceDemo1 d = new B_methodReferenceDemo1();
        d.addtest();

        //类上的方法引用: ClassName::methodName
        Stream.of("a","b").map(String::toUpperCase);

        //数组构造方法引用
        Stream.of("a","b").toArray(String[]::new);

        //构造方法引用
        Stream.of("a","b").map(String::new);
    }

    public Stream addtest(){
        return Stream.of(1,2).map(this::addSuffix);
    }
    public String addSuffix(int i){
        return i + "px";
    }
}

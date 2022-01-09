package F_nullOptionalDemo1;

import model.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author wangxiaohu
 * @version Id: OptionalDemo2.java, v0.1 2020年11月13日 15:51:08 wangxiaohu Exp $
 * @Description java8 Optional类：一个可以方便检查空指针的工具/容器类
 */
public class OptionalDemo2 {
    public static void main(String[] args) {
        /**
         * 简单的API说明
         *  - Optional.ofNullable - 允许传递为 null 参数
         *  - Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
         *  - Optional.isPresent - 判断值是否存在，不为null则返回true
         *  - Optional.orElse - 如果值不为null，返回它，否则返回默认值
         *  - Optional.get - 获取值，值需要存在
         *  - Optional.ifPresent - 不为空则消费这个数据
         *  - Optional.map - 如果有值，则对其执行调用mapping函数得到返回值
         *  - Optional.orElseGet - 接受Supplier接口的实现用来生成默认值，和orElse相似
         *
         */
        OptionalDemo2 demo = new OptionalDemo2();

        Integer v1 = null;
        Integer v2 = new Integer(2);

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> o1 = Optional.ofNullable(v1);

        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> o2 = Optional.of(v2);


        // Optional.isPresent - 判断值是否存在，不为null则返回true
        System.out.println(o1.isPresent());         //输出 false
        System.out.println(o2.isPresent());         //输出 true


        // Optional.orElse - 如果值不为null，返回它，否则返回默认值
        Integer v3 = o1.orElse(0);

        //Optional.get - 获取值，值需要存在
        Integer v4 = o2.get();

        System.out.println(v3);                     //输出 0
        System.out.println(v4);                     //输出 2

        //Optional.ifPresent - 不为空则消费这个数据
        o1.ifPresent(System.out::println);          // o1是空，所有没有执行打印命令

        //Optional.map  - 获取值
        Student student = new Student("1",12,33d);
        Map<String,Student> studentMap = new HashMap<>();
        studentMap.put("zs", student);
        Double score1 = Optional.ofNullable(studentMap.get("zs")).map(Student::getScore).orElse(null);
        Double score2 = Optional.ofNullable(studentMap.get("lisi")).map(Student::getScore).orElse(null);

        System.out.println("score1 : "+ score1);  //输出33.0
        System.out.println("score2 : "+ score2);  //输出null



    }
}

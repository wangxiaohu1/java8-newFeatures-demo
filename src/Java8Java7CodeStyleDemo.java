import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author wangxiaohu
 * @version Id: Java8Java7CodeStyleDemo.java, v0.1 2020年11月09日 19:36:22 wangxiaohu Exp $
 * @Description ：java8去比于java7的编码风格: java引入了Lambda表达式、函数式编程简化代码
 */
public class Java8Java7CodeStyleDemo {
    public static void main(String[] args) {
        //一个案例说明java8和java7编码的格式，java8的lamba
        List<String> mylist=new ArrayList<>();
        mylist.add("java");
        mylist.add("c++");
        mylist.add("go");

        List<String> yourlist=new ArrayList<>();
        yourlist.add("java");
        yourlist.add("c++");
        yourlist.add("go");

        //java7排序
        Collections.sort(mylist, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2){
                return s1.compareTo(s2) ;
           }
        });
        System.out.println("java7 sort结果: " + mylist);

        //java8排序
        Collections.sort(yourlist, (s1, s2) -> s1.compareTo(s2));
        System.out.println("java8 sort结果: " + yourlist);

        //最终输出
        //   java7 sort结果: [c++, go, java]
        //   java8 sort结果: [c++, go, java]
    }
}

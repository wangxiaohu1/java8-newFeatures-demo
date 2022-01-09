package L_parallel;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author wangxiaohu
 * @version Id: K_Java8ParallelArraysDemo1.java, v0.1 2020年11月17日 17:43:08 wangxiaohu Exp $
 * @Description java8的并行数组
 */
public class A_parallelArraysDemo1 {
    public static void main(String[] args) {
        long[] array = new long[30000];

        //使用parallelSetAll()方法生成30000个随机数
        Arrays.parallelSetAll(array,index -> ThreadLocalRandom.current().nextInt(1000000));
        //输出乱序数组前10个元素
        Arrays.stream(array).limit(10).forEach(i  -> System.out.print(i +  " "));

        System.out.println("----");
        //使用parallelSort()方法进行排序
        Arrays.parallelSort(array);
        Arrays.stream(array).limit(10).forEach(i  -> System.out.print(i +  " "));
    }
}

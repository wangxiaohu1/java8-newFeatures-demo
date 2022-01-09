package F1_stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author wangxiaohu
 * @version Id: TestApp.java, v0.1 2022年01月09日 22:40:11 wangxiaohu Exp $
 */
public class A_createStream {
    public static void main(String[] args) {
        //使用Stream的静态方法创建Stream
        Stream stream1 = Stream.of("2");
        Stream stream2 = Stream.of("2","b");
        Stream strea3 = Stream.concat(stream1, stream2);
        Stream strea4 = Stream.empty();
        Stream<Double> strea5 = Stream.generate(Math::random);
        Stream strea6 = Stream.iterate(1, item->item+1);
        Stream strea7 = Arrays.stream(new String[]{"a","b"});

        //使用集合Collection 的stream()方法创建stream
        List<String> list = new ArrayList();
        list.add("a");
        list.add("b");
        Stream<String> stream8 = list.stream();

        //其他
        IntStream ints = new Random().ints(5);
        IntStream stream = BitSet.valueOf(new byte[] {1, 2}).stream();

    }
}

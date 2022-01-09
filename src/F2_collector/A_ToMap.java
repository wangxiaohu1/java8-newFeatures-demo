//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package F2_collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import java.util.stream.Collectors;

import model.Student;

public class A_ToMap {
    public A_ToMap() {
    }

    public static void main(String[] args) {
        testToMap1();
        testToMap2();
        testToMap3();
        testToConcurrentHashMap();
    }

    private static void testToMap1() {
        System.out.println("-------testToMap1--------");
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("B", 16, 90.0D));
        Map<String, Student> map1 = (Map)studentList.stream().collect(Collectors.toMap((x) -> {
            return x.getSex();
        }, (v) -> {
            return v;
        }));
        System.out.println("toMap1：" + map1);
        Map<String, Double> map2 = (Map)studentList.stream().collect(Collectors.toMap(Student::getSex, Student::getScore));
        System.out.println("toMap2：" + map2);
        Map<String, Student> map3 = (Map)studentList.stream().collect(Collectors.toMap((x) -> {
            return x.getSex();
        }, Function.identity()));
        System.out.println("toMap3：" + map3);
        Map<String, Student> map4 = (Map)studentList.stream().collect(Collectors.toMap((x) -> {
            return x.getSex() + x.getAge();
        }, (v) -> {
            return v;
        }));
        System.out.println("toMap4：" + map4);
        Map<String, Boolean> map5 = (Map)studentList.stream().collect(Collectors.toMap((x) -> {
            return x.getSex();
        }, (v) -> {
            return true;
        }));
        System.out.println("toMap5：" + map5);
    }

    private static void testToMap2() {
        System.out.println("-------testToMap2--------");
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));

        Map map1;
        try {
            map1 = (Map)studentList.stream().collect(Collectors.toMap((x) -> {
                return x.getSex();
            }, (v) -> {
                return v;
            }));
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        map1 = (Map)studentList.stream().collect(Collectors.toMap((x) -> {
            return x.getSex();
        }, (v) -> {
            return v;
        }, (v1, v2) -> {
            return v2;
        }));
        System.out.println("toMap1：" + map1);
        Map<String, Integer> map2 = (Map)studentList.stream().collect(Collectors.toMap((x) -> {
            return x.getSex();
        }, (v) -> {
            return v.getAge();
        }, (v1, v2) -> {
            return v1 + v2;
        }));
        System.out.println("toMap：" + map2);
        List<String> list = Arrays.asList("123", "124", "124");
        list.stream().collect(Collectors.toMap((x) -> {
            return x;
        }, (p) -> {
            return "Y";
        }, (v1, v2) -> {
            return v2;
        }));
    }

    private static void testToMap3() {
        System.out.println("-------testToMap3--------");
        List<Student> studentList2 = new ArrayList();
        studentList2.add(new Student((String)null, 14, 70.0D));
        studentList2.add(new Student((String)null, 15, 70.0D));
        Map var10000 = (Map)studentList2.stream().collect(HashMap::new, (m, value) -> {
            String v1 = (String)m.put(value.getAge(), value.getSex());
        }, HashMap::putAll);
        Map<Integer, String> map = (Map)studentList2.stream().collect(Collector.of(HashMap::new, (m, stu) -> {
            String v2 = (String)m.put(stu.getAge(), stu.getSex());
        }, (k, v) -> {
            return v;
        }, Characteristics.IDENTITY_FINISH));
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));
        HashMap<String, Integer> map4 = (HashMap)studentList.stream().collect(Collectors.toMap(Student::getSex, Student::getAge, (v1, v2) -> {
            return v1 + v2;
        }, HashMap::new));
        System.out.println("toMap：" + map4);
    }

    private static void testToConcurrentHashMap() {
        System.out.println("---------testToConcurrentHashMap-----------");
        List<Student> studentList2 = new ArrayList();
        studentList2.add(new Student("G", 14, 60.0D));
        studentList2.add(new Student("B", 15, 70.0D));
        Map<String, Double> cMap1 = (Map)studentList2.stream().collect(Collectors.toConcurrentMap(Student::getSex, Student::getScore));
        Map<String, Double> cMap2 = (Map)studentList2.stream().collect(Collectors.toConcurrentMap(Student::getSex, Student::getScore, (v1, v2) -> {
            return v1 + v2;
        }));
        Map<String, Double> cMap3 = (Map)studentList2.stream().collect(Collectors.toConcurrentMap(Student::getSex, Student::getScore, (v1, v2) -> {
            return v1 + v2;
        }, ConcurrentHashMap::new));
        System.out.println("toConcurrentMap:" + cMap1);
        System.out.println("toConcurrentMap:" + cMap2);
        System.out.println("toConcurrentMap:" + cMap3);
    }
}

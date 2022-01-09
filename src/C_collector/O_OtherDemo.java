//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package C_collector;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import model.Student;

public class O_OtherDemo {
    public O_OtherDemo() {
    }

    public static void main(String[] args) {
        testCollectingAndThen();
        testMapping();
        testToSet_toList();
        testToMap();
        testToCollection();
        testJoining();
        testReducing();
    }

    public static void testCollectingAndThen() {
        System.out.println("-------testCollectingAndThen--------");
        List<String> strList = new ArrayList();
        strList.add("hello");
        strList.add("world");
        strList.add("!");
        strList.add("world");
        int size = (Integer)strList.stream().collect(Collectors.collectingAndThen(Collectors.toSet(), (it) -> {
            return it.size();
        }));
        System.out.println("collectingAndThen: " + size);
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));
        Student student = (Student)studentList.stream().collect(Collectors.collectingAndThen(Collectors.maxBy((s1, st2) -> {
            return Double.compare(s1.getScore(), st2.getScore());
        }), Optional::get));
        System.out.println("collectingAndThen：" + student);
    }

    private static void testMapping() {
        System.out.println("-------testMapping--------");
        List<String> list = new ArrayList();
        list.add("hello");
        list.add("world");
        list.add("!");
        List<Integer> strLengthList = (List)list.stream().collect(Collectors.mapping(String::length, Collectors.toList()));
        System.out.println("mapping: " + strLengthList);
    }

    private static void testToSet_toList() {
        System.out.println("-------testToSet_toList--------");
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));
        List<Integer> ageList = (List)studentList.stream().map(Student::getAge).collect(Collectors.toList());
        System.out.println(ageList);
        Set<Integer> ageSet = (Set)studentList.stream().map(Student::getAge).collect(Collectors.toSet());
        System.out.println(ageSet);
    }

    private static void testToMap() {
        System.out.println("-------testToMap--------");
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));
        Map<Double, Student> map = (Map)studentList.stream().collect(Collectors.toMap((x) -> {
            return x.getScore();
        }, (v) -> {
            return v;
        }));
        Map<String, Student> map1 = (Map)studentList.stream().collect(Collectors.toMap((x) -> {
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
        Map<String, Student> map3 = (Map)studentList.stream().collect(Collectors.toMap((x) -> {
            return x.getSex();
        }, Function.identity(), (v1, v2) -> {
            return v2;
        }));
        System.out.println("toMap3：" + map3);
        HashMap<String, Integer> map4 = (HashMap)studentList.stream().collect(Collectors.toMap(Student::getSex, Student::getAge, (v1, v2) -> {
            return v1 + v2;
        }, HashMap::new));
        System.out.println("toMap：" + map4);
        List<Student> studentList2 = new ArrayList();
        studentList2.add(new Student((String)null, 14, 70.0D));
        studentList2.add(new Student((String)null, 15, 70.0D));
        Map va1 = (Map)studentList2.stream().collect(HashMap::new, (m, value) -> {
            String va2 = (String)m.put(value.getAge(), value.getSex());
        }, HashMap::putAll);
        studentList2 = new ArrayList();
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

    private static void testToCollection() {
        System.out.println("-------testToCollection--------");
        List<String> list = new ArrayList();
        list.add("hello");
        list.add("world");
        list.add(",");
        list.add("java");
        ArrayList<Integer> lengthList = (ArrayList)list.stream().map(String::length).collect(Collectors.toCollection(ArrayList::new));
        System.out.println("Collectors.toCollection : " + lengthList);
        TreeSet<Integer> lengthSet = (TreeSet)list.stream().map(String::length).collect(Collectors.toCollection(TreeSet::new));
        System.out.println("Collectors.toCollection : " + lengthSet);
    }

    private static void testJoining() {
        System.out.println("-------testJoining--------");
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));
        String str = (String)studentList.stream().map(Student::getSex).collect(Collectors.joining());
        System.out.println("joining:" + str);
        str = (String)studentList.stream().map(Student::getSex).collect(Collectors.joining(","));
        System.out.println("joining:" + str);
        str = (String)studentList.stream().map(Student::getSex).collect(Collectors.joining(",", "[", "]"));
        System.out.println("joining:" + str);
    }

    private static void testReducing() {
        System.out.println("-------testReducing-------");
        List<Integer> intList = new ArrayList();
        intList.add(13);
        intList.add(14);
        int maxValue = (Integer)((Optional)intList.stream().collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingInt(Integer::intValue))))).get();
        System.out.println("reducing求最大值：" + maxValue);
        List<String> strList = new ArrayList();
        strList.add("Hello");
        strList.add("Java");
        strList.add("JDK");
        String str = (String)strList.stream().collect(Collectors.reducing("", (left, right) -> {
            return left + right;
        }));
        System.out.println("reducing拼接：" + str);
        intList = new ArrayList();
        intList.add(4);
        intList.add(5);
        Integer sum = (Integer)intList.stream().collect(Collectors.reducing(0, (left, right) -> {
            return left + right;
        }));
        System.out.println("reducing求和：" + sum);
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));
        sum = (Integer)studentList.stream().collect(Collectors.reducing(0, Student::getAge, Integer::sum));
        System.out.println("reducing求年龄和：" + sum);
    }
}

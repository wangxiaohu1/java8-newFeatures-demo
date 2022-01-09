//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package F2_collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import model.Student;
import model.Task;
import model.TaskTag;

public class C_Group {
    public C_Group() {
    }

    public static void main(String[] args) {
        testGroupBy0();
        testGroupBy1();
        testGroupBy2();
        testGroupBy3();
        testGroupBy4();
        testGroupingByConcurrent();
        testPartitioningBy();
    }

    private static void testGroupBy0() {
        System.out.println("-------testGroupBy0-------");
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));
        Map<String, Double> map = new HashMap();

        Student student;
        Double score;
        for(Iterator var2 = studentList.iterator(); var2.hasNext(); map.put(student.getSex(), score)) {
            student = (Student)var2.next();
            score = (Double)map.get(student.getSex());
            if (null != score) {
                score = score + student.getScore();
            } else {
                score = student.getScore();
            }
        }

        System.out.println("普通方式：" + map);
        Map<String, Double> map2 = (Map)studentList.stream().collect(Collectors.groupingBy(Student::getSex, Collectors.summingDouble(Student::getScore)));
        System.out.println("groupBy方式：" + map2);
        Map<String, Double> map3 = (Map)studentList.stream().collect(Collectors.groupingBy(Student::getSex, Collectors.reducing(0.0D, Student::getScore, Double::sum)));
        System.out.println("reducing方式：" + map3);
    }

    private static void testGroupBy1() {
        System.out.println("-------testGroupBy1-------");
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));
        Map<String, List<Student>> sexGroup = (Map)studentList.stream().collect(Collectors.groupingBy((x) -> {
            return x.getSex();
        }));
        sexGroup = (Map)studentList.stream().collect(Collectors.groupingBy(Student::getSex));
        System.out.println("groupingBy:" + sexGroup);
        Map<String, Double> sexGroupTotalScore = (Map)studentList.stream().collect(Collectors.groupingBy(Student::getSex, TreeMap::new, Collectors.summingDouble(Student::getScore)));
        System.out.println("分组求和自定义收集的集合:" + sexGroupTotalScore);
    }

    private static void testGroupBy2() {
        System.out.println("-------testGroupBy2-------");
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));
        Map<String, Double> groupAveScore = (Map)studentList.stream().collect(Collectors.groupingBy(Student::getSex, Collectors.averagingDouble((x) -> {
            return x.getScore();
        })));
        System.out.println("groupingBy:" + groupAveScore);
        Map<String, Double> sexGroupTotalScore = (Map)studentList.stream().collect(Collectors.groupingBy(Student::getSex, Collectors.summingDouble(Student::getScore)));
        System.out.println("分组并求和:" + sexGroupTotalScore);
        Map<String, Long> countMap = (Map)studentList.stream().collect(Collectors.groupingBy(Student::getSex, Collectors.counting()));
        System.out.println("分组求个数：" + countMap);
        Map<String, Student> maxMap = (Map)studentList.stream().collect(Collectors.groupingBy(Student::getSex, Collectors.collectingAndThen(Collectors.reducing((c1, c2) -> {
            return Double.compare(c1.getScore(), c2.getScore()) > 0 ? c1 : c2;
        }), Optional::get)));
        System.out.println("分组求最大值：" + maxMap);
        System.out.println("多级分组： 先根据名性别分组，在根据年龄分组");
        Map<String, Map<Integer, List<Student>>> group2 = (Map)studentList.stream().collect(Collectors.groupingBy((x) -> {
            return x.getSex();
        }, Collectors.groupingBy((x) -> {
            return x.getAge();
        })));
        Iterator var6 = group2.keySet().iterator();

        while(var6.hasNext()) {
            String sex = (String)var6.next();
            System.out.println(sex);
            Map<Integer, List<Student>> group3 = (Map)group2.get(sex);
            Iterator var9 = group3.keySet().iterator();

            while(var9.hasNext()) {
                Integer age = (Integer)var9.next();
                System.out.println("|--" + age);
                System.out.println("   " + group3.get(age));
            }
        }

    }

    private static void testGroupBy3() {
        System.out.println("-------testGroupBy3-------");
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));
        Map<String, Double> sexGroupTotalScore = (Map)studentList.stream().collect(Collectors.groupingBy(Student::getSex, TreeMap::new, Collectors.summingDouble(Student::getScore)));
        System.out.println("分组求和自定义收集的集合:" + sexGroupTotalScore);
    }

    private static void testGroupBy4() {
        System.out.println("-------testGroupBy4-------");
        List<Task> taskList = new ArrayList();
        taskList.add(new Task("java", Arrays.asList("编程", "语言", "面向对象")));
        taskList.add(new Task("linux", Arrays.asList("操作系统", "计算机系统")));
        Map<String, List<Task>> map = (Map)taskList.stream().flatMap((t) -> {
            return t.getTags().stream().map((tag) -> {
                return new TaskTag(tag, t);
            });
        }).collect(Collectors.groupingBy(TaskTag::getTag, Collectors.mapping(TaskTag::getTask, Collectors.toList())));
        System.out.println("groupingBy分组：" + map);
        taskList = new ArrayList();
        taskList.add(new Task("java", Arrays.asList("编程", "语言", "面向对象")));
        taskList.add(new Task("linux", Arrays.asList("操作系统", "计算机系统", "编程")));
        Map<String, Long> map2 = (Map)taskList.stream().flatMap((t) -> {
            return t.getTags().stream().map((tag) -> {
                return new TaskTag(tag, t);
            });
        }).collect(Collectors.groupingBy(TaskTag::getTag, Collectors.counting()));
        System.out.println("groupingBy分组数量：" + map2);
    }

    public static void testGroupingByConcurrent() {
        System.out.println("-------testGroupingByConcurrent-------");
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));
        Map<String, List<Student>> map = (Map)studentList.stream().collect(Collectors.groupingByConcurrent(Student::getSex));
        System.out.println(map);
    }

    public static void testPartitioningBy() {
        System.out.println("-------testPartitioningBy-------");
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));
        Map<Boolean, List<Student>> map = (Map)studentList.stream().collect(Collectors.partitioningBy((x) -> {
            return x.getScore() > 70.0D;
        }));
        Iterator var2 = map.keySet().iterator();

        while(var2.hasNext()) {
            boolean k = (Boolean)var2.next();
            System.out.println("分区 " + k);
            System.out.println("   " + map.get(k));
            System.out.println("----------");
        }

        Map<Boolean, Double> map2 = (Map)studentList.stream().collect(Collectors.partitioningBy((x) -> {
            return x.getScore() > 70.0D;
        }, Collectors.averagingDouble(Student::getScore)));
        System.out.println("分区求平均分：" + map2);
    }
}

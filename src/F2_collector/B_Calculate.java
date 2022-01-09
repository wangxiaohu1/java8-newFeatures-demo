//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package F2_collector;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.Student;

public class B_Calculate {
    public B_Calculate() {
    }

    public static void main(String[] args) {
        testAveraging();
        testCounting();
        testMaxBy_MinBy();
        testSumming();
        testSummarizing();
    }

    private static void testAveraging() {
        System.out.println("-------testAveraging-------");
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));
        Double averageAge = (Double)studentList.stream().collect(Collectors.averagingInt(Student::getAge));
        System.out.println("averagingInt:" + averageAge);
        Double averageScore = (Double)studentList.stream().collect(Collectors.averagingDouble(Student::getScore));
        System.out.println("averagingDouble:" + averageScore);
    }

    public static void testCounting() {
        System.out.println("-------testSumming-------");
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));
        long count = (Long)studentList.stream().filter((x) -> {
            return x.getSex().equals("G");
        }).collect(Collectors.counting());
        System.out.println("counting: " + count);
    }

    public static void testMaxBy_MinBy() {
        System.out.println("-------testMaxBy_MinBy-------");
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));
        Student student = (Student)((Optional)studentList.stream().collect(Collectors.maxBy(Comparator.comparingInt((x) -> {
            return x.getAge();
        })))).get();
        System.out.println("maxBy：" + student);
        List<Integer> list = new ArrayList();
        list.add(4);
        list.add(9);
        list.add(5);
        list.add(3);
        int minElement = (Integer)((Optional)list.stream().collect(Collectors.minBy(Comparator.comparingInt(Integer::intValue)))).get();
        minElement = (Integer)((Optional)list.stream().collect(Collectors.minBy(Comparator.comparingInt((x) -> {
            return x;
        })))).get();
        System.out.println("minBy: " + minElement);
        int maxElement = (Integer)((Optional)list.stream().collect(Collectors.minBy(Comparator.comparingInt(Integer::intValue).reversed()))).get();
        System.out.println("minBy: " + maxElement);
        List<String> strList = new ArrayList();
        strList.add("hello");
        strList.add("java");
        strList.add("my");
        strList.add("~");
        String element = (String)((Optional)strList.stream().collect(Collectors.minBy(Comparator.comparingInt((x) -> {
            return x.length();
        })))).get();
        System.out.println("comparingInt: " + element);
    }

    public static void testSumming() {
        System.out.println("-------testSumming-------");
        List<Student> studentList = new ArrayList();
        studentList.add(new Student("G", 14, 70.0D));
        studentList.add(new Student("G", 16, 90.0D));
        studentList.add(new Student("B", 14, 60.0D));
        studentList.add(new Student("B", 15, 80.0D));
        int totalAge = (Integer)studentList.stream().collect(Collectors.summingInt((x) -> {
            return x.getAge();
        }));
        System.out.println("summingInt:" + totalAge);
        Double totalScore = (Double)studentList.stream().collect(Collectors.summingDouble((x) -> {
            return x.getScore();
        }));
        System.out.println("summingDouble:" + totalScore);
    }

    public static void testSummarizing() {
        System.out.println("-------testSummarizing-------");
        List<String> strList = new ArrayList();
        strList.add("Hello");
        strList.add("Java");
        strList.add("OK!");
        IntSummaryStatistics collect = (IntSummaryStatistics)strList.stream().collect(Collectors.summarizingInt(String::length));
        System.out.println("summarizingInt：" + collect.getAverage());
        System.out.println("summarizingInt：" + collect.getCount());
        System.out.println("summarizingInt：" + collect.getMax());
        System.out.println("summarizingInt：" + collect.getMin());
        System.out.println("summarizingInt：" + collect.getSum());
    }
}

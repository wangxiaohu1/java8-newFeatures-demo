//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package F2_collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import model.Student;

public class A_ToList_ToSet {
    public A_ToList_ToSet() {
    }

    public static void main(String[] args) {
        testToSet_toList();
        testToCollection();
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
}

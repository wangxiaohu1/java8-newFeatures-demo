package G_nullOptionalDemo1;

import java.util.Optional;

import G_nullOptionalDemo1.model.Department;
import G_nullOptionalDemo1.model.Employee;

/**
 * @author wangxiaohu
 * @version Id: mapTest.java, v0.1 2022年01月22日 13:17:07 wangxiaohu Exp $
 */
public class MapTest {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static void test1(){
        Employee emp = new Employee();
        emp.empNo="123";

        Department dept = new Department();
        dept.employee=emp;

        //if else 判断获取值
        String empNo = null;
        if(dept!=null && dept.employee!=null){
            empNo = dept.employee.empNo;
        }else{
            empNo = "test";
        }
        System.out.println(empNo);

        //java8获取值
        String empNo2 =
            Optional.ofNullable(dept).map(x -> x.employee)
            .map(e->e.empNo).orElse("test");
        System.out.println(empNo2);
    }

    private static void test2(){

        Department dept = new Department();

        //if else 判断获取值
        String empNo = null;
        if(dept!=null && dept.employee!=null){
            empNo = dept.employee.empNo;
        }else{
            empNo = "test";
        }
        System.out.println(empNo);

        //java8获取值
        String empNo2 =
            Optional.ofNullable(dept).map(x -> x.employee)
                .map(e->e.empNo).orElse("test");
        System.out.println(empNo2);
    }

    private static void test3(){

        Department dept = null;

        //if else 判断获取值
        String empNo = null;
        if(dept!=null && dept.employee!=null){
            empNo = dept.employee.empNo;
        }else{
            empNo = "test";
        }
        System.out.println(empNo);

        //java8获取值
        String empNo2 =
            Optional.ofNullable(dept).map(x -> x.employee)
                .map(e->e.empNo).orElse("test");
        System.out.println(empNo2);
    }

}

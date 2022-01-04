package model;

public class Student {
    private String sex;
    private int age;
    private double score;
    public Student(String sex, int age, double score){
        this.sex = sex;
        this.age = age;
        this.score = score;
    }
    public String toString(){
        return "sex:"+sex+" ;age:"+age+" ;score:"+score;
    }
    public String getSex() {
        return sex;
    }
    public int getAge() {
        return age;
    }
    public double getScore() {
        return score;
    }
}

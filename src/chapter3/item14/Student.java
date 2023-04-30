package chapter3.item14;

import java.util.Comparator;
import static java.util.Comparator.*;

public class Student implements Comparable<Student>{
    int age;
    int classNumber;

    // 나이가 많을 수록 우선순위가 높고 앞 반일 수록 순서가 높음
    private static final Comparator<Student> StudentComparator =
            comparingInt((Student st) -> st.age)
            .thenComparingInt(st -> -st.classNumber);

    Student(int age, int classNumber){
        this.age = age;
        this.classNumber = classNumber;
    }

    @Override
    public int compareTo(Student o) {
        /*
        * 만약 자신의 age가 o의 age보다 크면 양수 반환
        * 같다면 0, 작다면 음수를 반환
        * */

        // Comparable 인터페이스
//        return Integer.compare(this.age, o.age);

        // Comparator 인터페이스
        return StudentComparator.compare(this, o);
    }


    public static void main(String[] args) {
        Student student1 = new Student(18, 3); // 고2학년 3반
        Student student2 = new Student(17, 1); // 고1학년 1반
        Student student3 = new Student(17, 2); // 고1학년 2반

        // Comparable
//        System.out.println(student1.compareTo(student2));

        // Comparator
        System.out.println(student1.compareTo(student2)); // 1
        System.out.println(student2.compareTo(student3)); // 1
        System.out.println(student1.compareTo(student3)); // 1
    }
}

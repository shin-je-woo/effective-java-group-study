package chapter7.item43;
import java.util.Arrays;
import java.util.List;

public class LambdaVsMethodReferenceExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Jane", "Alex", "Sarah");

        // 람다 표현식을 사용하여 문자열 길이 출력
        names.forEach(name -> System.out.println(name.length()));

        // 메서드 참조를 사용하여 문자열 길이 출력
        names.forEach(System.out::println);
    }
}
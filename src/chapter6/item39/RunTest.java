package chapter6.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTest {
    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;

        Sample sampleClass = new Sample();
        Class<?> testClass = Class.forName("chapter6.item39.Sample");

        for (Method m : testClass.getDeclaredMethods()){
            if (m.isAnnotationPresent(Test.class)){
                tests++;
                try {
                    // 메서드 객체를 호출하여 실행한다.
                    // 정적 메서드 호출
                    m.invoke(null);

                    // 인스턴스 메서드 호출
//                    m.invoke(sampleClass);

                    passed++;
                } catch (InvocationTargetException wrappedExc){

                    // 리플렉션 API로 호출된 메서드가 예외를 던지면 InvocationTargetException으로 감싼다.
                    Throwable exc = wrappedExc.getCause();

                    System.out.println(m + " 실패 - " + exc);
                    System.out.println();
                } catch (Exception exc){
                    System.out.println("잘못 사용한 @Test: " + m);
                    System.out.println(exc);
                    System.out.println();
                }
            }
        }
        System.out.printf("성공: %d, 실패: %d%n", passed, tests - passed);
    }
}

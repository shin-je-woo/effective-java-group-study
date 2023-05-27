package chapter6.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTest3 {
    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;

        Class<?> testClass = Class.forName("chapter6.item39.Sample3");

        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(ExceptionTest2.class)
               || m.isAnnotationPresent(ExceptionTest2Container.class)) {
                tests++;

                try {
                    m.invoke(null);
                    System.out.printf("테스트 %s 실패: 예외를 던지지 않음%n", m);
                } catch (Throwable wrappedExc) {
                    // 발생한 예외
                    Throwable exc = wrappedExc.getCause();

                    int oldPassed = passed;

                    // getAnnotationsByType는 컨테이너 애너테이션 구분하지 않음
                    ExceptionTest2[] excTests = m.getAnnotationsByType(ExceptionTest2.class);
                    for (ExceptionTest2 excTest : excTests) {
                        if (excTest.value().isInstance(exc)) {
                            passed++;
                            break;
                        }
                    }
                    if (passed == oldPassed) System.out.printf("테스트 %s 실패 - 발생한 예외: %s%n", m, exc);
                }
            }
        }
    }
}

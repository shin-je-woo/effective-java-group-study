package chapter5.item33.asSubclass;
import java.lang.annotation.*;
import java.lang.reflect.*;

// 코드 33-5 asSubclass를 사용해 한정적 타입 토큰을 안전하게 형변환한다. (204쪽)
public class PrintAnnotation {
    static Annotation getAnnotation(AnnotatedElement element,String annotationTypeName) {
        Class<?> annotationType = null; // 비한정적 타입 토큰
        try {
            annotationType = Class.forName(annotationTypeName);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
        return element.getAnnotation(
                annotationType.asSubclass(Annotation.class));
    }

    // 명시한 클래스의 명시한 애너테이션을 출력하는 테스트 프로그램
    public static void main(String[] args) throws Exception {
        String name = TestClass.class.getName();
        Class<?> klass = Class.forName(name);
        System.out.println(getAnnotation(klass, TestAnnotation.class.getName()));
    }
}
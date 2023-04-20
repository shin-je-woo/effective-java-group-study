package chapter1.item01.flyweight.after;

/**
 * Flyweight 클래스
 * 불변클래스로 구현
 */
public final class Font {
    // Flyweight 클래스의 필드는 공유가 필요한 값들(공통 관심사)
    private final String fontFamily;
    private final int fontSize;

    public Font(String fontFamily, int fontSize) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
    }
}

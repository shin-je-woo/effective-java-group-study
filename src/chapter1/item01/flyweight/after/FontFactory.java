package chapter1.item01.flyweight.after;

import java.util.HashMap;
import java.util.Map;

public class FontFactory {

    private final static Map<String, Font> fontStore = new HashMap<>();

    public static Font of(String font) {
        if (fontStore.containsKey(font)) {
            return fontStore.get(font);
        } else {
            String[] split = font.split(":");
            Font newFont = new Font(split[0], Integer.parseInt(split[1]));
            fontStore.put(font, newFont);
            System.out.println("새로운 Font가 생성되었습니다.");
            return newFont;
        }
    }
}

package chapter5.item33;
import java.util.*;

// 타입 안전 이종 컨테이너 패턴 (199-202쪽)
public class Favorites {
    // 코드 33-3 타입 안전 이종 컨테이너 패턴 - 구현 (200쪽)
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), type.cast(instance));
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type)); //형변환 연산자의 동적 버전
    }

    // 코드 33-2 타입 안전 이종 컨테이너 패턴 - 클라이언트 (199쪽)
    public static void main(String[] args) {
        Favorites favorites = new Favorites();

        favorites.putFavorite(String.class, "Java");
        favorites.putFavorite(Integer.class, 1);

        String favoriteString = favorites.getFavorite(String.class);
        int favoriteInteger = favorites.getFavorite(Integer.class);

    }
}
package chapter5.item33;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 타입 안전 이종 컨테이너 패턴 (199-202쪽)
public class Favorites_Bounded {
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T extends Number> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), instance);
    }

    public <T extends Number> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }

    public static void main(String[] args) {
        Favorites_Bounded favorites = new Favorites_Bounded();

        //favorites.putFavorite(String.class, "A");
        favorites.putFavorite(Integer.class, 1);
        favorites.putFavorite(Double.class, 2.1);

        int favoriteInteger = favorites.getFavorite(Integer.class);
        Double favoriteDouble = favorites.getFavorite(Double.class);

        System.out.println("Favorite Integer: " + favoriteInteger);
        System.out.println("favorit eDouble = " + favoriteDouble);
    }
}
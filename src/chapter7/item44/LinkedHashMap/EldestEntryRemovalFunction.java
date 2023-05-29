package chapter7.item44.LinkedHashMap;

import java.util.Map;

// 불필요한 함수형 인터페이스 - 대신 표준 함수형 인터페이스를 사용하라.
@FunctionalInterface interface EldestEntryRemovalFunction<K,V> {
    boolean remove(Map<K,V> map, Map.Entry<K,V> eldest );
}
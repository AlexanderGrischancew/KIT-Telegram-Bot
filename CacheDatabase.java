package Commands;

import java.util.Map;
import java.util.TreeMap;

public final class CacheDatabase {
    private static Map<String, Cache> caches = new TreeMap<>();

    public static void addCache(String string, Cache cache) {
        caches.put(string, cache);
    }

    public static Cache getCache(String string) {
        if (caches.containsKey(string)) {
            return caches.get(string);
        } else {
            return null;
        }
    }
}

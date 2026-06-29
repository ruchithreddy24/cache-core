import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCache<K,V> implements Cache<K,V>{

    private final ConcurrentHashMap<K, CacheEntry<V>> storage = new ConcurrentHashMap<>();

    public static void main(String[] args) {

    }

    @Override
    public void put(K key, V value) {
        CacheEntry<V> entry = new CacheEntry<>(value);
        storage.put(key, entry);
    }

    @Override
    public void remove(K key) {
        storage.remove(key);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public V get(K key) {
        CacheEntry<V> entry = storage.get(key);
        return entry.getValue();
    }
}

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCache<K,V> implements Cache<K,V>{

    private final ConcurrentHashMap<K, CacheEntry<V>> storage = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        InMemoryCache<Integer, String> cache = new InMemoryCache<>();
        cache.put(10, "Ruchith", 5000);
        while (true) {
            String value = cache.get(10);

            if (value == null) {
                System.out.println("Cache Expired!");
                break;
            }
            System.out.println(value);
            Thread.sleep(1000);   // Wait for 1 second

        }
    }

    // ************ PUT Statements **************//

    @Override
    public void put(K key, V value) {
        CacheEntry<V> entry = new CacheEntry<>(value);
        storage.put(key, entry);
    }
    @Override
    public void put(K key, V value, long expiry) {
        CacheEntry<V> entry = new CacheEntry<>(value, expiry);
        storage.put(key, entry);
    }

    // ************** REMOVE Statements **************//

    @Override
    public void remove(K key) {
        storage.remove(key);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    // ************** INSIGHTS Statements ***************//

    @Override
    public int size() {
        return storage.size();
    }

    // ************** RETRIEVAL Statements **************//

    @Override
    public V get(K key) {
        CacheEntry<V> entry = storage.get(key);
        if (System.currentTimeMillis() > entry.getExpiresAt()) {
            storage.remove(key);
            return null;
        }
        entry.IncreaseHitCount();
        System.out.print(entry.getHitCount());
        return entry.getValue();
    }
}

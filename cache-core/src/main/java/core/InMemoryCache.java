package core;
import Eviction.EvictionPolicy;
import Eviction.FIFOEvictionPolicy;
import Eviction.LRUEvictionPolicy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InMemoryCache<K,V> implements Cache<K,V> {

    private final ConcurrentHashMap<K, CacheEntry<V>> storage = new ConcurrentHashMap<>();
    private final EvictionPolicy<K, V> evictionPolicy;
    private final int maxCacheSize;

    public InMemoryCache() {
        this.maxCacheSize = 2;
        this.evictionPolicy = new FIFOEvictionPolicy();
    }

    public InMemoryCache(int maxCacheSize, EvictionPolicy evictionPolicy) {
        this.maxCacheSize = maxCacheSize;
        this.evictionPolicy = evictionPolicy;
    }

    public static void main(String[] args) throws InterruptedException {
        Cache<Integer, String> cache = new InMemoryCache<>(3,new LRUEvictionPolicy());
        InMemoryCache<Integer, String> cache2 = new InMemoryCache<>(3,new FIFOEvictionPolicy());


    }

    // ************ PUT Statements **************//

    @Override
    public synchronized void put(K key, V value) {
        if(storage.size() >= maxCacheSize && !storage.containsKey(key)) {
            K keyToEvict = evictionPolicy.getKeyToEvict();
            storage.remove(keyToEvict);
        }

        evictionPolicy.keyAdded(key);
        CacheEntry<V> entry = new CacheEntry<>(value);

        storage.put(key, entry);
    }

    @Override
    public synchronized void put(K key, V value, long expiry) {
        if(storage.size() >= maxCacheSize && !storage.containsKey(key)) {
            K keyToEvict = evictionPolicy.getKeyToEvict();
            storage.remove(keyToEvict);
        }
        evictionPolicy.keyAdded(key);
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
    public synchronized V get(K key) {
        CacheEntry<V> entry = storage.get(key);
        if (entry == null) {
            return null;
        }
        evictionPolicy.keyAccessed(key);
        if (System.currentTimeMillis() > entry.getExpiresAt()) {
            System.out.println("Total Hit Count: "+entry.getHitCount());
            storage.remove(key);
            return null;
        }
        entry.setLastAccessedAt();
        entry.IncreaseHitCount();
        return entry.getValue();
    }

}

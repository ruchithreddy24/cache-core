public class CacheEntry<V> {
    private V value;
    private int expired;

    public CacheEntry(V value) {
        this.value = value;
    }
    public V getValue() {
        return value;
    }
}

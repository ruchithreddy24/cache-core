public class CacheEntry<V> {
    private V value;
    private long expiresAt;
    private long createdAt;
    private int hitcount;

    public CacheEntry(V value) {
        this.value = value;
        this.createdAt = System.currentTimeMillis();
        this.expiresAt = System.currentTimeMillis() + 10000;
        this.hitcount = 0;
    }

    public CacheEntry(V value, long expiry) {
        this.value = value;
        this.createdAt = System.currentTimeMillis();
        this.expiresAt = System.currentTimeMillis() + expiry;
        this.hitcount = 0;
    }

    public void IncreaseHitCount() {
        this.hitcount++;
    }

    public long getExpiresAt(){
        return expiresAt;
    }

    public V getValue() {
        return value;
    }

    public int  getHitCount() {
        return hitcount;
    }
    public long getCreatedAt() {
        return hitcount;
    }
}
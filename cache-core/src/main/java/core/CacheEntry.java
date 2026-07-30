package core;

public class CacheEntry<V> {
    private V value;
    private long expiresAt;
    private long createdAt;
    private long lastAccessedAt;
    private int hitcount;

    //***** Setters *****//

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

    public void setLastAccessedAt() {
        this.lastAccessedAt = System.currentTimeMillis();
    }

    public void IncreaseHitCount() {
        this.hitcount++;
    }


    // ***** Getters *****//
    public long getExpiresAt(){
        return expiresAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public V getValue() {
        return value;
    }

    public long getLastAccessedAt() {
        return lastAccessedAt;
    }

    public int getHitCount() {
        return hitcount;
    }

}
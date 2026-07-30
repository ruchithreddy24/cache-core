package Eviction;

public interface EvictionPolicy<K, V> {
    public void keyAdded(K key);
    K getKeyToEvict();
    void keyAccessed(K key);
    void printLRUOrder();
}
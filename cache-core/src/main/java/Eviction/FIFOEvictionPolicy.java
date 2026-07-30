package Eviction;

import java.util.concurrent.ConcurrentLinkedQueue;

public class FIFOEvictionPolicy<K,V> implements EvictionPolicy<K,V>{

    private final ConcurrentLinkedQueue<K> keys = new ConcurrentLinkedQueue<>();

    @Override
    public void keyAdded(K key) {
            keys.add(key);
    }

    @Override
    public K getKeyToEvict() {
        return keys.poll();
    }

    @Override
    public void keyAccessed(K key) {

    }

    @Override
    public void printLRUOrder() {

    }

}
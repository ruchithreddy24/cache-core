package core;


public interface Cache<K, V>
    {
        void put(K key, V value);
        void put(K key, V value, long expiry);

        void remove(K key);
        void clear();
        int size();

        V get(K key);
    }
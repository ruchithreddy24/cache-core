package Eviction;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

class Node<K>{
    K key;
    Node<K> prev, next;
}

public class LRUEvictionPolicy<K,V> implements EvictionPolicy<K,V> {

    Node<K> head, tail;
    HashMap<K,Node<K>> map = new HashMap<K,Node<K>>();

    @Override
    public void keyAdded(K key) {
        Node<K> newNode = new Node<>();
        newNode.key = key;
        if(map.isEmpty()){
            head = newNode;
            tail = newNode;
        }else{
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        map.put(key, newNode);
    }

    @Override
    public K getKeyToEvict() {

        if(head == null){
            return null;
        }

        Node<K> node = head.next;
        K key = head.key;
        map.remove(key);

        head = node;

        if(head != null){
            head.prev = null;
        }else{
            tail = null;
        }

        return key;
    }

    public void keyAccessed(K key) {
        Node<K> node = map.get(key);

        if(tail == node){
            return;
        }

        if(node == head){
            head = node.next;
            head.prev = null;
        }else{
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        tail.next = node;
        node.prev = tail;
        node.next = null;
        tail = node;
    }

    public void printLRUOrder() {

    }

    // ****** Doubly Linked List methods ******


}

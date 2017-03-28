package com.epam.java.se.unit99;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Yegor on 28.03.2017.
 */
public class CustomTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Node<K, V> root;
    private V previousValue; //TODO get rid of it

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean containsKey(Object key) {
        Objects.requireNonNull(key);

        return root != null && find(root, (K) key) != null;

    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean containsValue(Object value) {

        return root != null && findValue(root, (V) value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        Objects.requireNonNull(key);

        Node<K,V> nodeByKey = find(root, (K) key);

        if (nodeByKey == null){
            return null;
        }
        return nodeByKey.value;
    }

    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(key);

        previousValue = null;
        root = put(root, key, value);
        return previousValue;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private Node<K, V> find(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        if (node.key.equals(key)) {
            return node;
        } else if (node.key.compareTo(key) > 0) {
            return find(node.left, key);
        } else {
            return find(node.right, key);
        }
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) return new Node<>(key, value);
        if (node.key.equals(key)) {
            previousValue = node.value;
            node.value = value;
        } else if (node.key.compareTo(key) > 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }
        return node;
    }

    private boolean findValue(Node node, V value) {
        if (node == null) return false;

        if (node.value.equals(value)) {
            return true;
        } else if (findValue(node.left, value)){
            return true;
        } else if (findValue(node.right, value)){
            return true;
        }

        return false;
    }

    private class Node<K extends Comparable<K>, V> {
        private final K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
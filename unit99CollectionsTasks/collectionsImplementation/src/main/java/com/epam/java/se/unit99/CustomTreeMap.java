package com.epam.java.se.unit99;

import java.util.*;

/**
 * Created by Yegor on 28.03.2017.
 */

/**
 * Custom implementation of TreeMap.
 * Stores key-value pairs in form of {@code Node}.
 * This map permits {@code null} values, but does not permit {@code null} keys.
 *
 * @param <K> type of keys.
 * @param <V> type of mapped values.
 */
public class CustomTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    /**
     * Root of this map.
     */
    private Node<K, V> root;

    /**
     * Used for returning mapped for some keys previous values.
     */
    private V previousValue; //TODO get rid of it

    /**
     * The size of the CustomTreeMap (the number of key-value pairs it contains).
     */
    private int size = 0;

    /**
     * @return the number of key-value pairs in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return <tt>true</tt> if this map contains no elements.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param key key whose presence in this map need to be checked.
     * @return <tt>true</tt> if this map contains a mapping for the specified key.
     * @throws NullPointerException if the specified key is null.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean containsKey(Object key) {
        Objects.requireNonNull(key);

        return root != null && find(root, (K) key) != null;

    }

    /**
     * @param value value whose presence in this map need to be checked.
     * @return <tt>true</tt> if this map maps one or more keys to the specified value.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean containsValue(Object value) {

        return root != null && findValue(root, (V) value);
    }

    /**
     * Returns the value to which the specified key is mapped, or {@code null}
     * if this map contains no mapping for the key.
     * A returned value of {@code null} does not necessarily indicate that the map contains no mapping for the key.
     *
     * @param key key to return mapped value to this key.
     * @return the value to which the specified key is mapped
     * @throws NullPointerException if the specified key is null.
     */
    @Override
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        Objects.requireNonNull(key);

        Node<K, V> nodeByKey = find(root, (K) key);

        if (nodeByKey == null) {
            return null;
        }
        return nodeByKey.value;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with key, or {@code null} if there was no mapping for key.
     * (A {@code null} return can also indicate that the map previously associated {@code null} with key.)
     * @throws NullPointerException if the specified key is null.
     */
    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(key);

        previousValue = null;
        root = put(root, key, value);
        return previousValue;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or {@code null} if there was no mapping for key.
     * (A {@code null} return can also indicate that the map previously associated {@code null} with key.)
     * @throws NullPointerException if the specified key is null.
     */
    @Override
    @SuppressWarnings("unchecked")
    public V remove(Object key) {
        Objects.requireNonNull(key);

        previousValue = null;
        root = remove(root, (K) key);
        return previousValue;
    }

    /**
     * Copies all of the mappings from the specified map to this map.
     * These mappings will replace any mappings that this map had for
     * any of the keys currently in the specified map.
     *
     * @param m mappings to be stored in this map.
     * @throws NullPointerException if the specified map is null.
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    /**
     * Removes all of the key-value pairs from this map.
     * The map will be empty after this call returns.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the set of keys stored in this map.
     * This set currently does not support any modifying operation such as add, remove, clear, e.t.c.
     * This set supports {@code Iterator} operations.
     *
     * @return the set of keys stored in this map.
     */
    @Override
    public Set<K> keySet() {
        return new KeySet();
    }

    /**
     * Returns the collection of values stored in this map.
     * This collection currently does not support any modifying operation such as add, remove, clear, e.t.c.
     * This collection supports {@code Iterator} operations.
     *
     * @return the collection of values stored in this map.
     */
    @Override
    public Collection<V> values() {
        return new ValueCollection();
    }

    /**
     * Returns the set of keys-value pairs ({@code Node}) stored in this map.
     * This set currently does not support any modifying operation such as add, remove, clear, e.t.c.
     * This set supports {@code Iterator} operations.
     *
     * @return the set of keys-value pairs ({@code Node}) stored in this map.
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return new EntrySet();
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
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }
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

        if (node.value == null) {
            return value == null;
        } else if (node.value.equals(value)) {
            return true;
        } else if (findValue(node.left, value)) {
            return true;
        } else if (findValue(node.right, value)) {
            return true;
        }

        return false;
    }

    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) return null;

        if (node.key.compareTo(key) > 0) {
            node.left = remove(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            node.right = remove(node.right, key);
        } else {
            size--;
            previousValue = node.value;
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            Node<K, V> buff = node;
            node = min(buff.right);
            node.right = deleteMin(buff.right);
            node.left = buff.left;
        }
        return node;
    }

    private Node<K, V> deleteMin(Node<K, V> node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;

    }

    private Node<K, V> min(Node<K, V> node) {
        if (node.left == null) return node;
        else return min(node);
    }

    /**
     * Class that used for storing info about map nodes and their sequence.
     *
     * @param <K>
     * @param <V>
     */
    private class Node<K extends Comparable<K>, V> implements Entry<K, V> {
        private final K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V previousValue = this.value;
            this.value = value;
            return previousValue;
        }
    }

    private class EntrySet extends AbstractSet<Entry<K, V>> {

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override
        public int size() {
            return size;
        }
    }

    private class KeySet extends AbstractSet<K> {

        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override
        public int size() {
            return size;
        }
    }

    private class ValueCollection extends AbstractCollection<V> {

        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override
        public int size() {
            return size;
        }
    }

    private class EntryIterator<T> extends CommonEntryIterator {
        @Override
        public Node<K, V> next() {
            return entries[entryIndex++];
        }
    }

    private class KeyIterator extends CommonEntryIterator {
        @Override
        public Object next() {
            return entries[entryIndex++].key;
        }
    }

    private class ValueIterator extends CommonEntryIterator {
        @Override
        public Object next() {
            return entries[entryIndex++].value;
        }
    }

    private abstract class CommonEntryIterator implements Iterator {
        Node<K, V>[] entries = new Node[size];
        int entryIndex = 0;

        CommonEntryIterator() {
            collectEntriesStartingFrom(root, entryIndex);
            entryIndex = 0;
        }

        public boolean hasNext() {
            return entryIndex < entries.length;
        }

        private void collectEntriesStartingFrom(Node<K, V> node, int entryIndex) {
            if (node != null) {
                entries[entryIndex] = node;
                entryIndex++;
                collectEntriesStartingFrom(node.left, entryIndex);
                collectEntriesStartingFrom(node.right, entryIndex);
            }
        }
    }
}
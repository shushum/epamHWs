package com.epam.java.se.unit99;

import java.util.*;

/**
 * Created by Yegor on 28.03.2017.
 */
public class CustomTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Node<K, V> root;
    private V previousValue; //TODO get rid of it
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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

        Node<K, V> nodeByKey = find(root, (K) key);

        if (nodeByKey == null) {
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
    @SuppressWarnings("unchecked")
    public V remove(Object key) {
        Objects.requireNonNull(key);

        previousValue = null;
        root = remove(root, (K) key);
        return previousValue;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return new KeySet();
    }

    @Override
    public Collection<V> values() {
        return new ValueCollection();
    }

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

    private class EntrySet extends AbstractSet<Entry<K, V>>{

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override
        public int size() {
            return size;
        }
    }

    private class KeySet extends AbstractSet<K>{

        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override
        public int size() {
            return size;
        }
    }

    private class ValueCollection extends AbstractCollection<V>{

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
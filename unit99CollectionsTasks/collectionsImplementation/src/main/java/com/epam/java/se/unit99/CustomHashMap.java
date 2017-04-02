package com.epam.java.se.unit99;

import java.util.*;

/**
 * Created by Yegor on 01.04.2017.
 */
public class CustomHashMap<K, V> implements Map<K, V> {
    private final int INITIAL_CAPACITY = 16;
    private int capacity;
    private CustomEntry<K, V>[] buckets = new CustomEntry[INITIAL_CAPACITY];
    private int size;

    public CustomHashMap() {
        this.capacity = INITIAL_CAPACITY;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        Objects.requireNonNull(key);

        CustomEntry<K, V> possibleMatch = inspectEntriesInBucketOnKeyOverlapping(getBucketNumber((K) key), (K) key);

        return possibleMatch != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(key);

        int entries = getBucketNumber(key);

        if (buckets[entries] == null) {
            buckets[entries] = new CustomEntry<>(key, value);
            size++;
        } else {
            CustomEntry overlappingEntry = inspectEntriesInBucketOnKeyOverlapping(entries, key);

            if (overlappingEntry != null) {
                V previousValue = (V) overlappingEntry.value;
                overlappingEntry.value = value;
                return previousValue;
            } else {
                CustomEntry<K, V> newEntry = new CustomEntry<>(key, value);
                newEntry.next = buckets[entries];
                buckets[entries] = newEntry;
                size++;
            }
        }
        return null;
    }

    private CustomEntry<K, V> inspectEntriesInBucketOnKeyOverlapping(int entries, K key) {
        CustomEntry<K, V> currentEntry = buckets[entries];

        while (currentEntry != null) {
            if (currentEntry.key.equals(key)) {
                return currentEntry;
            }
            currentEntry = currentEntry.next;
        }

        return null;
    }

    private int getBucketNumber(K key) {
        return Math.abs(key.hashCode()) % capacity;
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

    private class CustomEntry<K, V> implements Map.Entry<K, V> {

        private final K key;
        private V value;
        private CustomEntry<K, V> next;

        CustomEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        void setNext(CustomEntry<K, V> next) {
            this.next = next;
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public CustomEntry<K, V> next() {
            return next;
        }

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }

        @Override
        public V setValue(V value) {
            return null;
        }
    }
}

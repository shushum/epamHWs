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

        CustomEntry<K, V> possibleMatch = getEntryFromBucketByKey(getBucketNumber((K) key), (K) key);

        return possibleMatch != null;
    }

    @Override
    public boolean containsValue(Object value) {

        for (int i = 0; i < capacity; i++) {
            CustomEntry<K, V> currentEntry = buckets[i];
            while (currentEntry != null) {
                if (currentEntry.value == null) {
                    if (value == null) {
                        return true;
                    }
                } else {
                    return currentEntry.value.equals(value);
                }
                currentEntry = currentEntry.next;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {

        CustomEntry<K, V> requiredEntry = getEntryFromBucketByKey(getBucketNumber((K) key), (K) key);

        return requiredEntry == null ? null : requiredEntry.value;
    }

    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(key);

        int bucket = getBucketNumber(key);

        if (buckets[bucket] == null) {
            buckets[bucket] = new CustomEntry<>(key, value);
            size++;
        } else {
            CustomEntry<K, V> overlappingEntry = getEntryFromBucketByKey(bucket, key);

            if (overlappingEntry != null) {
                V previousValue = overlappingEntry.value;
                overlappingEntry.value = value;
                return previousValue;
            } else {
                CustomEntry<K, V> newEntry = new CustomEntry<>(key, value);
                newEntry.next = buckets[bucket];
                buckets[bucket] = newEntry;
                size++;
            }
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        Objects.requireNonNull(key);

        int bucket = getBucketNumber((K) key);
        CustomEntry<K, V> previousEntry = buckets[bucket];
        CustomEntry<K, V> currentEntry = previousEntry;

        while (currentEntry != null) {
            if (currentEntry.key.equals(key)) {
                break;
            }
            previousEntry = currentEntry;
            currentEntry = currentEntry.next;
        }

        if (currentEntry == null) {
            return null;
        } else if (previousEntry == currentEntry) {
            buckets[bucket] = currentEntry.next;
        } else {
            previousEntry.next = currentEntry.next;
        }
        return currentEntry.value;


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

    private CustomEntry<K, V> getEntryFromBucketByKey(int bucket, K key) {
        CustomEntry<K, V> currentEntry = buckets[bucket];

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
}

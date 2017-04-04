package com.epam.java.se.unit99;

import java.util.*;

/**
 * Created by Yegor on 01.04.2017.
 */

/**
 * Custom implementation of HashMap.
 * Stores key-value pairs in form of {@code CustomEntry}.
 * This map permits {@code null} values, but does not permit {@code null} keys.
 * There is no resizing methods implementing yet. Technically all entries are stored in fixed-length array,
 * backed up with linked list.
 *
 * @param <K> type of keys.
 * @param <V> type of mapped values.
 */
public class CustomHashMap<K, V> implements Map<K, V> {
    /**
     * Initial size of array storage.
     */
    private final int INITIAL_CAPACITY = 16;

    /**
     * Size of array storage.
     */
    private int capacity;

    /**
     * Array storage of {@code CustomEntry} for key-value pairs.
     */
    private CustomEntry<K, V>[] buckets = new CustomEntry[INITIAL_CAPACITY];

    /**
     * The size of the CustomHashMap (the number of key-value pairs it contains).
     */
    private int size;

    /**
     * Constructs an empty CustomHashMap with default capacity.
     */
    public CustomHashMap() {
        this.capacity = INITIAL_CAPACITY;
    }

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
    public boolean containsKey(Object key) {
        Objects.requireNonNull(key);

        CustomEntry<K, V> possibleMatch = getEntryFromBucketByKey(getBucketNumber((K) key), (K) key);

        return possibleMatch != null;
    }

    /**
     * @param value value whose presence in this map need to be checked.
     * @return <tt>true</tt> if this map maps one or more keys to the specified value.
     */
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
    public V get(Object key) {

        CustomEntry<K, V> requiredEntry = getEntryFromBucketByKey(getBucketNumber((K) key), (K) key);

        return requiredEntry == null ? null : requiredEntry.value;
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

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or {@code null} if there was no mapping for key.
     * (A {@code null} return can also indicate that the map previously associated {@code null} with key.)
     * @throws NullPointerException if the specified key is null.
     */
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
        size--;
        return currentEntry.value;
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
        buckets = new CustomEntry[capacity];
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
     * Returns the set of keys-value pairs ({@code CustomEntry}) stored in this map.
     * This set currently does not support any modifying operation such as add, remove, clear, e.t.c.
     * This set supports {@code Iterator} operations.
     *
     * @return the set of keys-value pairs ({@code CustomEntry}) stored in this map.
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return new EntrySet();
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

    /**
     * Class that used for storing info about map entries and their sequence.
     *
     * @param <K>
     * @param <V>
     */
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

    private class KeyIterator extends CommonEntryIterator {
        @Override
        public K next() {
            return entries[entryIndex++].key;
        }
    }

    private class ValueIterator extends CommonEntryIterator {
        @Override
        public V next() {
            return entries[entryIndex++].value;
        }
    }

    private class EntryIterator extends CommonEntryIterator {
        @Override
        public CustomEntry<K, V> next() {
            return entries[entryIndex++];
        }
    }

    private abstract class CommonEntryIterator implements Iterator {
        CustomEntry<K, V>[] entries = new CustomEntry[size];
        int entryIndex = 0;

        CommonEntryIterator() {
            collectEntries();
            entryIndex = 0;
        }

        public boolean hasNext() {
            return entryIndex < entries.length;
        }

        public abstract Object next();

        private void collectEntries() {
            for (int i = 0; i < capacity; i++) {
                CustomEntry<K, V> currentEntry = buckets[i];

                while (currentEntry != null) {
                    entries[entryIndex] = currentEntry;
                    entryIndex++;
                    currentEntry = currentEntry.next;
                }
            }
        }
    }
}

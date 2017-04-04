package com.epam.java.se.unit99;

import java.util.*;

/**
 * Created by Yegor on 03.04.2017.
 */

public class CustomArrayList<T> implements List<T> {

    public static final int CAPACITY = 10;

    private Object[] data = new Object[CAPACITY];
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
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (data[i] == null) {
                if (o == null) {
                    return true;
                }
            } else if (data[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length <= size) {
            return (T1[]) Arrays.copyOf(data, size, a.getClass());
        } else {
            System.arraycopy(data, 0, a, 0, size);
            a[size] = null;
            return a;
        }
    }

    @Override
    public boolean add(T t) {
        ensureCapacity();
        data[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {

        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    shiftElementsToTheLeft(i);
                    size--;
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(data[i])) {
                    shiftElementsToTheLeft(i);
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Objects.requireNonNull(c);

        int sizeFlag = size;

        for (T element : c) {
            add(element);
        }

        return size > sizeFlag;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);

        int sizeFlag = size;

        for (Object element : c) {
            remove(element);
        }

        return size < sizeFlag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        Object[] data = toArray();

        int sizeFlag = size;

        for (Object element : data) {
            if (!c.contains(element)) {
                remove(element);
            }
        }

        return size < sizeFlag;
    }

    @Override
    public void clear() {
        data = new Object[CAPACITY];
        size = 0;
    }

    @Override
    public T get(int index) {
        boundsCheck(index);
        return (T) data[index];
    }

    @Override
    public T set(int index, T element) {
        boundsCheck(index);

        T previousValue = (T) data[index];
        data[index] = element;
        return previousValue;
    }

    @Override
    public void add(int index, T element) {
        if (index == size) {
            add(element);
        } else {
            boundsCheck(index);
            ensureCapacity();
            shiftElementsToTheRight(index);
            data[index] = element;
            size++;
        }
    }

    @Override
    public T remove(int index) {
        boundsCheck(index);

        T value = (T) data[index];
        shiftElementsToTheLeft(index);
        size--;

        return value;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            int newLength = (data.length * 3) / 2 + 1;
            data = Arrays.copyOf(data, newLength);
        }
    }

    private void shiftElementsToTheLeft(int index) {
        int length = data.length - index;
        System.arraycopy(data, index + 1, data, index, length - 1);
    }

    private void shiftElementsToTheRight(int index) {
        int length = data.length - index;
        System.arraycopy(data, index, data, index + 1, length - 1);
    }

    private void boundsCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private class ArrayListIterator implements Iterator<T> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            try {
                int i = cursor;
                T next = get(i);
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }
    }
}

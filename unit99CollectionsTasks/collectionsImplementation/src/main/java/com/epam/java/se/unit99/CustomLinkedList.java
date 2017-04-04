package com.epam.java.se.unit99;

import java.util.*;

/**
 * Created by Yegor on 03.04.2017.
 */
public class CustomLinkedList<T> implements List<T> {

    private Node<T> head = new Node<>(null);
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return !head.hasNext();
    }

    @Override
    public boolean contains(Object o) {
        Node<T> node = head;
        while (node.hasNext()) {
            node = node.next;

            if (equals(o, node.value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];

        Node<T> node = head.next;

        for (int i = 0; i < size; i++) {
            result[i] = node.value;
            node = node.next;
        }
        return result;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        Object[] listArray = toArray();
        if (a.length <= size) {
            return (T1[]) Arrays.copyOf(listArray, size, a.getClass());
        } else {
            System.arraycopy(listArray, 0, a, 0, size);
            a[size] = null;
            return a;
        }
    }

    @Override
    public boolean add(T t) {
        Node<T> iterator = head;

        while (iterator.hasNext()) {
            iterator = iterator.next;
        }

        iterator.next = new Node<>(t);
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<T> current = head.next;
        Node<T> prev = head;

        while (current != null) {
            if (equals(o, current.value)) {
                prev.next = current.next;
                size--;
                return true;
            }
            prev = current;
            current = current.next;
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
        Objects.requireNonNull(c);

        int sizeFlag = size;

        for (T element : c) {
            add(index++, element);
        }

        return size > sizeFlag;
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
        head = new Node<>(null);
        size = 0;
    }

    @Override
    public T get(int index) {
        return getNodeByIndex(index).value;
    }

    @Override
    public T set(int index, T element) {
        Node<T> nodeToSet = getNodeByIndex(index);

        T previousValue = nodeToSet.value;
        nodeToSet.value = element;
        return previousValue;
    }

    @Override
    public void add(int index, T element) {
        if (index == size) {
            add(element);
        } else {
            boundsCheck(index);

            Node<T> current = getNodeByIndex(index - 1);
            Node<T> nodeToAdd = new Node<>(element);

            nodeToAdd.next = current.next;
            current.next = nodeToAdd;
            size++;
        }

    }

    @Override
    public T remove(int index) {
        boundsCheck(index);

        Node<T> current = getNodeByIndex(index - 1);

        T value = current.next.value;
        current.next = current.next.next;
        size--;

        return value;
    }

    @Override
    public int indexOf(Object o) {
        Node<T> current = head;
        int index = -1;

        while (current.hasNext()) {
            current = current.next;
            index++;
            if (equals(o, current.value)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<T> current = head;
        int index = -1;
        int lastOccurrence = -1;

        while (current.hasNext()) {
            current = current.next;
            index++;
            if (equals(o, current.value)) {
                lastOccurrence = index;
            }
        }
        return lastOccurrence;
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

    private Node<T> getNodeByIndex(int index) {
        boundsCheck(index);

        Node<T> current = head.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void boundsCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean equals(Object o, Object value) {
        if (value == null) {
            if (o == null) {
                return true;
            }
        } else if (value.equals(o)) {
            return true;
        }
        return false;
    }

    private class Node<T> {

        private Node<T> next;
        private T value;

        public Node(T value) {
            this.value = value;
        }

        public boolean hasNext() {
            return next != null;
        }

    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode.hasNext();
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            currentNode = currentNode.next;
            return currentNode.value;
        }
    }
}

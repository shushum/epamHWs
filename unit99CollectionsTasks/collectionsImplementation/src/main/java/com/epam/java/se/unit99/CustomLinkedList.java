package com.epam.java.se.unit99;

import java.util.*;

/**
 * Created by Yegor on 03.04.2017.
 */

/**
 * Custom implementation of LinkedList.
 * Stores elements of certain type. Uses {@code Node<T>} to store them.
 * This LinkedList allows to store {@code null} values.
 * @param <T> type of values.
 */
public class CustomLinkedList<T> implements List<T> {

    /**
     * Header of this list. Does not count as its' element.
     */
    private Node<T> head = new Node<>(null);

    /**
     * The size of the CustomArrayList (the number of elements it contains).
     */
    private int size = 0;

    /**
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return <tt>true</tt> if this list contains no elements.
     */
    @Override
    public boolean isEmpty() {
        return !head.hasNext();
    }

    /**
     * @param o element whose presence in this list need to be tested.
     * @return <tt>true</tt> if this list contains at least one copy of specified element.
     */
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

    /**
     * @return an iterator over the elements of this list (in proper sequence), starting at first element of the list.
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    /**
     * @return an array containing all of the elements of this list (in proper sequence).
     */
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

    /**
     * Returns an array containing all of the elements in this list (in proper sequence);
     * the type of the returned array is that of the specified array.
     *
     * If list fits in specified array, it is returned therein. If there is room to spare,
     * the element in the array immediately following the end of the collection is set to <tt>null</tt>.
     *
     * @param a the array into which the elements of list are to be stored, if it is big enough. Otherwise,
     *          a new array of the same type is used for this purpose.
     * @param <T1> the type of returned array.
     * @return an array containing the elements of the list (in proper sequence).
     */
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

    /**
     * Adds the specified element to the end of the list.
     *
     * @param t element to be added in this list.
     * @return <tt>true</tt>
     */
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

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * Otherwise, list is unchanged.
     *
     * @param o element to be removed from this list, if it present.
     * @return <tt>true</tt> if this list contained the specified element.
     */
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

    /**
     * @param c collection to be checked for containment in this list.
     * @return <tt>true</tt> if this list contains all of the elements of the specified collection.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to the end of this list.
     *
     * @param c collection containing elements to be added to this list.
     * @return <tt>true</tt> if this list was changed in result of operation.
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        Objects.requireNonNull(c);

        int sizeFlag = size;

        for (T element : c) {
            add(element);
        }

        return size > sizeFlag;
    }

    /**
     * Adds all of the elements in the specified collection into this
     * list at the specified position.  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right.
     *
     * @param index index at which to add the first element from the specified collection.
     * @param c collection containing elements to be added to this list.
     * @return <tt>true</tt> if this list was changed in result of operation.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Objects.requireNonNull(c);

        int sizeFlag = size;

        for (T element : c) {
            add(index++, element);
        }

        return size > sizeFlag;
    }

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection.
     *
     * @param c collection containing elements to be removed from this list.
     * @return <tt>true</tt> if this list was changed in result of operation.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);

        int sizeFlag = size;

        for (Object element : c) {
            remove(element);
        }

        return size < sizeFlag;
    }

    /**
     * Removes from this list all of its elements that are not contained in the
     * specified collection.
     *
     * @param c collection containing elements to be retained in this list.
     * @return <tt>true</tt> if this list was changed in result of operation.
     */
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

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    @Override
    public void clear() {
        head = new Node<>(null);
        size = 0;
    }

    /**
     * @param index of the element to return.
     * @return the element at the specified position of this list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public T get(int index) {
        return getNodeByIndex(index).value;
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index index of element to be replaced.
     * @param element element to be replaced with in specified position.
     * @return the element that previously was at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public T set(int index, T element) {
        Node<T> nodeToSet = getNodeByIndex(index);

        T previousValue = nodeToSet.value;
        nodeToSet.value = element;
        return previousValue;
    }

    /**
     * Adds the element into this list at the specified position.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right.
     *
     * @param index index at which to add the element.
     * @param element element to be added in list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
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

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left.
     * Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed.
     * @return the element that was removed from the list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public T remove(int index) {
        boundsCheck(index);

        Node<T> current = getNodeByIndex(index - 1);

        T value = current.next.value;
        current.next = current.next.next;
        size--;

        return value;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @param o the element to search for
     * @return index of the element.
     */
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

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @param o the element to search for
     * @return index of the element.
     */
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

    /**
     * Not implemented.
     */
    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    /**
     * Not implemented.
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    /**
     * Not implemented.
     */
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

    /**
     * Class that used for storing info about list elements and their sequence.
     * @param <T>
     */
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

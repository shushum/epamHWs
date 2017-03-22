|   | Basic methods | Examples of using |
|---|---|---|
| Collection | int size()<br>boolean isEmpty()<br>boolean contains(Object element)<br>void clear()<br>&lt;T> T[] toArray(T[] a)  |   |
| Set&lt;E> | boolean add(Object element)<br>boolean remove(Object element)<br>boolean retainAll(Collection&lt;?> c) |   |
| List&lt;E> | boolean add(Object element)<br>void add(int index, E element)<br>boolean remove(Object element)<br>E remove(int index)<br>boolean retainAll(Collection&lt;?> c)<br>E get(int index)<br>E set(int index, E element)<br>int indexOf(Object o)<br>void sort(Comparator&lt;? super E> c)<br>List&lt;E> subList(int fromIndex, int toIndex) |   |
| Queue&lt;E> | boolean add(E e)<br>boolean offer(E e)<br>E remove()<br>E poll()<br>E element()<br>E peek() |   |
| Map&lt;K, V> | boolean containsKey(Object key)<br>boolean containsValue(Object value)<br>V get(Object key)<br>V put(K key, V value)<br>V remove(Object key)<br>void putAll(Map&lt;? extends K, ? extends V> m)<br>Set&lt;K> keySet()<br>Collection&lt;V> values() |   |

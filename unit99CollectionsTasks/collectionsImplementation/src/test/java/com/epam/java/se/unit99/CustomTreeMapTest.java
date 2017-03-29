package com.epam.java.se.unit99;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Yegor on 28.03.2017.
 */
public class CustomTreeMapTest {

    private Map<Integer, String> m;

    @Before
    public void init() {
        m = new CustomTreeMap<>();
    }

    @Test
    public void creationTest() {
        assertThat(m, is(notNullValue()));
    }

    @Test
    public void newMapIsEmptyTest() {
        assertThat(m.isEmpty(), is(true));
    }

    @Test
    public void newMapDoesNotContainAnyKeyObjectTest() {
        assertThat(m.containsKey(1), is(false));
    }

    @Test
    public void newMapDoesNotContainAnyValueObjectTest() {
        assertThat(m.containsValue("1"), is(false));
    }

    @Test
    public void possibleToPutKeyValuePairTest() {
        m.put(1, "abc");
        assertThat(m.containsKey(1), is(true));
        assertThat(m.containsValue("abc"), is(true));
    }

    @Test
    public void filledCustomMapIsNotEmptyTest() {
        m.put(1, "keyToFillIn");

        assertThat(m.isEmpty(), is(false));
    }

    @Test(expected = NullPointerException.class)
    public void impossibleToPutNullKeyTest() {
        m.put(null, "abc");
    }

    @Test
    public void possibleToPutNullValueTest() {
        m.put(1, null);
        assertThat(m.containsKey(1), is(true));
    }

    @Test
    public void possibleToPutKeyWithHashCodeThatAlreadyPresentedTest() {
        m.put(1, "previousValue");
        m.put(1, "newValue");

        assertThat(m.containsValue("previousValue"), is(false));
        assertThat(m.containsValue("newValue"), is(true));
    }

    @Test
    public void afterPutKeyWithHashCodeThatAlreadyPresentedPreviousValueWillBeReturnedTest() {
        m.put(1, "previousValue");

        String previousValue = m.put(1, "newValue");

        assertThat("previousValue", is(equalTo(previousValue)));
    }

    @Test(expected = NullPointerException.class)
    public void containsKeyThrowsExceptionWithNullAsArgumentTest() {
        m.containsKey(null);
    }

    @Test(expected = ClassCastException.class)
    public void containsKeyThrowsExceptionWithWrongKeyTypeAsArgumentTest() {
        m.put(1, "");
        m.containsKey(new String(""));
    }

    @Test
    public void containsValueWorksProperlyWithNullAsArgumentTest() {
        m.put(1, null);
        assertThat(m.containsValue(null), is(true));
    }

    @Test
    public void containsValueWorksProperlyIfValuePresentedInMapTest() {
        m.put(4, "4");
        m.put(2, "2");
        m.put(1, "1");
        m.put(3, "3");
        m.put(6, "6");
        m.put(5, "5");
        m.put(8, "8");

        assertThat(m.containsValue("1"), is(true));
        assertThat(m.containsValue("3"), is(true));
        assertThat(m.containsValue("5"), is(true));
        assertThat(m.containsValue("8"), is(true));
    }

    @Test
    public void containsValueWorksProperlyIfValueDoesNotPresentedInMapTest() {
        m.put(4, "4");

        assertThat(m.containsValue("notExistingValue"), is(false));
    }

    @Test(expected = ClassCastException.class)
    public void containsValueThrowsExceptionWithWrongValueTypeAsArgumentTest() {
        m.put(1, "");
        m.containsValue(new Integer(1));
    }

    @Test
    public void possibleToPut10DifferentKeysInMapTest() {
        IntStream.range(1, 10).forEach(
                i -> m.put(i, String.valueOf(i)));

        IntStream.range(1, 10).forEach(
                i -> assertThat(m.containsKey(i), is(true)));
    }

    @Test
    public void sizeOfCustomTreeMapCalculatesCorrectlyOnPuttingTest() {
        IntStream.range(1, 10).forEach(
                i -> m.put(i, String.valueOf(i)));

        assertThat(m.size(), is(9));
    }

    @Test
    public void getExistingKeyReturnsMappedToKeyValueTest() {
        m.put(1, "mappedText");
        assertThat(m.get(1), is(equalTo("mappedText")));
    }

    @Test
    public void getNotExistingKeyReturnsNullTest() {
        assertThat(m.get(1), is(equalTo(null)));
    }

    @Test
    public void getKeyMappedWithNullReturnsNullTest() {
        m.put(1, null);
        assertThat(m.containsKey(1), is(true));
        assertThat(m.get(1), is(equalTo(null)));
    }

    @Test(expected = NullPointerException.class)
    public void getNullKeyThrowsExceptionTest() {
        m.get(null);
    }

    @Test(expected = ClassCastException.class)
    public void getWrongTypeKeyThrowsExceptionTest() {
        m.put(1, "mappedKey");
        m.get("");
    }

    @Test
    public void removeExistingKeyTest() {
        m.put(1, "mappedKey");
        m.remove(1);
        assertThat(m.containsKey(1), is(false));
    }

    @Test
    public void removeExistingKeyReturnsMappedToKeyValueTest() {
        m.put(1, "mappedKey");
        assertThat(m.remove(1), is(equalTo("mappedKey")));
    }

    @Test
    public void removeNotExistingKeyReturnsNullTest() {
        assertThat(m.remove(1), is(equalTo(null)));
    }

    @Test
    public void removeKeyMappedToNullReturnsNullTest() {
        m.put(1, null);
        assertThat(m.containsKey(1), is(true));
        assertThat(m.remove(1), is(equalTo(null)));
        assertThat(m.containsKey(1), is(false));

    }

    @Test(expected = NullPointerException.class)
    public void removeNullKeyThrowsExceptionTest() {
        m.remove(null);
    }

    @Test
    public void sizeOfCustomTreeMapCalculatesCorrectlyOnRemovingTest() {
        IntStream.range(1, 10).forEach(
                i -> m.put(i, String.valueOf(i)));

        IntStream.range(1, 5).forEach(
                i -> m.remove(i));


        assertThat(m.size(), is(5));
    }

    @Test
    public void clearTest() {
        m.put(1, "mappedKey");
        m.put(2, "mappedKey");
        m.put(3, "mappedKey");

        m.clear();

        assertThat(m.isEmpty(), is(true));
    }

    @Test
    public void entrySetCreatesSetOfAllCustomTreeMapEntriesTest() {
        IntStream.range(1, 10).forEach(
                i -> m.put(i, String.valueOf(i)));
        Set<Map.Entry<Integer, String>> set = m.entrySet();

        Iterator<Map.Entry<Integer, String>> iterator = set.iterator();
        Map<Integer, String> checkingMap = new HashMap<>();

        while (iterator.hasNext()) {
            Map.Entry currEntry = iterator.next();
            checkingMap.put((Integer) currEntry.getKey(), (String) currEntry.getValue());
        }

        IntStream.range(1, 10).forEach(
                i -> assertTrue(checkingMap.containsKey(i)));
        IntStream.range(1, 10).forEach(
                i -> assertTrue(checkingMap.containsValue(String.valueOf(i))));
        assertThat(set.size(), is(9));
    }

    @Test
    public void entrySetCreatesEmptySetOfEmptyCustomTreeMapTest() {
        Set<Map.Entry<Integer, String>> set = m.entrySet();
        assertThat(set.isEmpty(), is(true));
    }

    @Test
    public void keySetCreatesSetOfAllCustomTreeMapKeysTest() {
        IntStream.range(1, 10).forEach(
                i -> m.put(i, String.valueOf(i)));
        Set<Integer> set = m.keySet();

        IntStream.range(1, 10).forEach(
                i -> assertTrue(set.contains(i)));
        assertThat(set.size(), is(9));
    }

    @Test
    public void keySetCreatesEmptySetOfEmptyCustomTreeMapTest() {
        Set<Integer> set = m.keySet();
        assertThat(set.isEmpty(), is(true));
    }

    @Test
    public void valuesCreatesCollectionOfAllCustomTreeMapValuesTest() {
        IntStream.range(1, 10).forEach(
                i -> m.put(i, String.valueOf(i)));
        Collection<String> collection = m.values();

        IntStream.range(1, 10).forEach(
                i -> assertTrue(collection.contains(String.valueOf(i))));
        assertThat(collection.size(), is(9));
    }

    @Test
    public void valuesCreatesEmptyCollectionOfEmptyCustomTreeMapTest() {
        Collection<String> collection = m.values();
        assertThat(collection.isEmpty(), is(true));
    }

    @Test
    public void putAllAddsAllElementsFromRequiredMapToCustomTreeMap(){
        Map<Integer, String> testMap = new TreeMap<>();
        IntStream.range(1, 10).forEach(
                i -> testMap.put(i, String.valueOf(i)));

        m.putAll(testMap);

        IntStream.range(1, 10).forEach(
                i -> assertThat(m.containsKey(i), is(true)));
        IntStream.range(1, 10).forEach(
                i -> assertThat(m.containsValue(String.valueOf(i)), is(true)));
    }

    @Test(expected = NullPointerException.class)
    public void putAllWithNullKeysInRequiredMapThrowsException(){
        Map<Integer, String> testMap = new HashMap<>();
        testMap.put(null, "");
        m.putAll(testMap);
    }

    @Test(expected = NullPointerException.class)
    public void putAllWithNullAsRequiredMapThrowsException(){
        m.putAll(null);
    }

    @Test
    public void putAllWithEmptyRequiredMapChangesNothing(){
        Map<Integer, String> testMap = new TreeMap<>();

        assertThat(testMap.isEmpty(), is(true));

        m.putAll(testMap);

        assertThat(m.isEmpty(), is(true));
    }
}
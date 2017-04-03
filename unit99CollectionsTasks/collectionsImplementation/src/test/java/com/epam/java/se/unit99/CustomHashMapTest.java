package com.epam.java.se.unit99;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.*;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Yegor on 01.04.2017.
 */
public class CustomHashMapTest {

    private Map<Integer, String> m;

    @Before
    public void init() {
        m = new CustomHashMap<>();
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

    @Test(expected = NullPointerException.class)
    public void impossibleToPutNullKeyTest() {
        m.put(null, "abc");
    }

    @Test
    public void possibleToPutNullValueTest() {
        m.put(1, null);
        assertThat(m.containsValue(null), is(true));
    }

    @Test
    public void puttingKeyThatAlreadyPresentedInMapReturnsItsPreviousValueTest() {
        m.put(1, "previous value");
        assertThat(m.put(1, "new value"), equalTo("previous value"));
    }

    @Test
    public void isPossibleToStoreDifferentKeysWithEqualsHashCodesInMap() {
        Object key1 = "";
        Object key2 = 0;

        assertThat(key1.hashCode(), equalTo(key2.hashCode()));

        CustomHashMap<Object, String> map = new CustomHashMap<>();

        map.put(key1, String.valueOf(key1));
        map.put(key2, String.valueOf(key2));

        assertThat(map.containsKey(key1), is(true));
        assertThat(map.containsKey(key2), is(true));
    }

    @Test
    public void properCalculationOfMapSizeTest() {
        IntStream.range(1, 10).forEach(
                i -> m.put(i, String.valueOf(i)));

        assertThat(m.size(), equalTo(9));
    }

    @Test(expected = NullPointerException.class)
    public void containsKeyThrowsExceptionWithNullAsArgumentTest() {
        m.put(1, "value");
        m.containsKey(null);
    }

    @Test
    public void containsValueWorksProperlyWithNullAsArgumentTest() {
        m.put(1, null);
        assertThat(m.containsValue(null), is(true));
    }

    @Test
    public void containsValueWorksProperlyIfValuePresentedInMapTest() {
        m.put(1, "presentedValue");
        assertThat(m.containsValue("presentedValue"), is(true));
    }

    @Test
    public void containsValueWorksProperlyIfValueDoesNotPresentedInMapTest() {
        m.put(1, "presentedValue");
        assertThat(m.containsValue("notPresentedValue"), is(false));
    }

    @Test
    public void isEmptyWorksProperlyWithNotEmptyMapTest() {
        m.put(1, "presentedValue");
        assertThat(m.isEmpty(), is(false));
    }

    @Test
    public void isEmptyWorksProperlyWithEmptyMapTestTest() {
        assertThat(m.isEmpty(), is(true));
    }

    @Test
    public void getReturnsNullIfThereIsNoMappingForKeyTest() {
        assertThat(m.get(1), equalTo(null));
    }

    @Test
    public void getReturnsNullIfMappingForKeyIsNullTest() {
        m.put(1, null);
        assertThat(m.containsKey(1), is(true));
        assertThat(m.get(1), equalTo(null));
    }

    @Test
    public void getReturnsValueOfAKeyIfMappingForThisKeyExistsTest() {
        m.put(1, "value");
        assertThat(m.containsKey(1), is(true));
        assertThat(m.get(1), equalTo("value"));
    }

    @Test(expected = NullPointerException.class)
    public void getThrowsExceptionWithNullAsKeyArgumentTest() {
        m.get(null);
    }

    @Test
    public void removeRemovesKeyValuePairFromMapTest() {
        m.put(1, "value");
        m.remove(1);
        assertThat(m.containsKey(1), is(false));
    }

    @Test
    public void removeReturnsValueMappedToKeyIfSuchKeyWasPresentedInMapTest() {
        m.put(1, "value");
        assertThat(m.remove(1), equalTo("value"));
        assertThat(m.containsKey(1), is(false));
    }

    @Test
    public void removeReturnsNullIfKeyWasNotPresentedInMapTest() {
        assertThat(m.remove(1), equalTo(null));
    }

    @Test
    public void removeReturnsNullIfItWasMappedToPresentedInMapKeyTest() {
        m.put(1, null);
        assertThat(m.remove(1), equalTo(null));
        assertThat(m.containsKey(1), is(false));
    }

    @Test(expected = NullPointerException.class)
    public void removeThrowsExceptionWithNullAsAnArgumentTest() {
        m.put(1, "value");
        m.remove(null);
    }

    @Test
    public void removeFromBigMapTest() {
        IntStream.range(1, 100).forEach(
                i -> m.put(i, String.valueOf(i)));

        assertThat(m.remove(50), equalTo("50"));
        assertThat(m.containsKey(50), is(false));
    }

    @Test
    public void removeNonExistingKeyReturnsNullTest() {
        IntStream.range(1, 100).forEach(
                i -> m.put(i, String.valueOf(i)));

        assertThat(m.remove(101), equalTo(null));
    }

    @Test
    public void sizeCalculatesCorrectlyAfterRemovingExistingKeyValuePairTest() {
        IntStream.range(1, 100).forEach(
                i -> m.put(i, String.valueOf(i)));

        m.remove(20);
        assertThat(m.size(), equalTo(98));
    }

    @Test
    public void sizeCalculatesCorrectlyAfterRemovingNonExistingKeyValuePairTest() {
        IntStream.range(1, 100).forEach(
                i -> m.put(i, String.valueOf(i)));

        m.remove(101);
        assertThat(m.size(), equalTo(99));
    }

    @Test
    public void clearTest() {
        IntStream.range(1, 100).forEach(
                i -> m.put(i, String.valueOf(i)));

        m.clear();
        assertThat(m.size(), equalTo(0));
        assertThat(m.isEmpty(),is(true));
    }

    @Test
    public void putAllPutsAllElementsFromRequiredMapIntoThisMapTest() {
        Map<Integer, String> map = new HashMap<>();

        IntStream.range(1, 100).forEach(
                i -> map.put(i, String.valueOf(i)));

        m.putAll(map);


        IntStream.range(1, 100).forEach(
                i -> assertThat(map.containsKey(i), is(true)));
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
    public void putAllWithEmptyArgumentMapChangesNothing(){
        Map<Integer, String> testMap = new HashMap<>();

        assertThat(testMap.isEmpty(), is(true));

        m.putAll(testMap);

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
}
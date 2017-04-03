package com.epam.java.se.unit99;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Map;
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
}
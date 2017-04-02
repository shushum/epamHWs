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

    

}
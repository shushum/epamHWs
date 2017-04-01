package com.epam.java.se.unit99;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

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
        //TODO assertThat(m.containsValue("abc"), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void impossibleToPutNullKeyTest() {
        m.put(null, "abc");
    }

    @Test
    public void possibleToPutNullValueTest() {

    }

    @Test(expected = OutOfMemoryError.class)
    public void mapHasInfiniteCapacityTest() {

    }

    @Test
    public void possibleToPutKeyWithHashCodeThatAlreadyPresentedTest() {

    }

    @Test
    public void possibleToContainKeysSameWithHashCodeTest() {

    }

    @Test
    public void properCalculationOfMapSizeTest() {

    }

    @Test(expected = NullPointerException.class)
    public void containsKeyThrowsExceptionWithNullAsArgumentTest() {

    }

    @Test(expected = IllegalArgumentException.class)
    public void containsKeyThrowsExceptionWithWrongKeyTypeAsArgumentTest() {

    }

    @Test(expected = IllegalArgumentException.class)
    public void containsValueThrowsExceptionWithWrongValueTypeAsArgumentTest() {

    }

    @Test
    public void containsValueWorksProperlyWithNullAsArgumentTest() {

    }

    @Test
    public void containsValueWorksProperlyIfValuePresentedInMapTest() {

    }

    @Test
    public void containsValueWorksProperlyIfValueDoesNotPresentedInMapTest() {

    }


}
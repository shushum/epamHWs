package com.epam.java.se.unit99;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;
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
    public void possibleToPut10DifferentKeysInMapTest() {
        IntStream.range(1, 10).forEach(
                i -> m.put(i, String.valueOf(i)));

        IntStream.range(1, 10).forEach(
                i -> assertThat(m.containsKey(i), is(true)));
    }

    @Test
    public void getExistingKeyReturnsMappedToKeyValueTest(){
        m.put(1, "mappedText");
        assertThat(m.get(1), is(equalTo("mappedText")));
    }

    @Test
    public void getNotExistingKeyReturnsNullTest(){
        assertThat(m.get(1), is(equalTo(null)));
    }

    @Test
    public void getKeyMappedWithNullReturnsNullTest(){
        m.put(1, null);
        assertThat(m.containsKey(1), is(true));
        assertThat(m.get(1), is(equalTo(null)));
    }

    @Test(expected = NullPointerException.class)
    public void getNullKeyThrowsExceptionTest(){
        m.get(null);
    }

    @Test(expected = ClassCastException.class)
    public void getWrongTypeKeyThrowsExceptionTest(){
        m.put(1, "mappedKey");
        m.get("");
    }

    @Test
    public void removeExistingKeyTest(){
        m.put(1, "mappedKey");
        m.remove(1);
        assertThat(m.containsKey(1), is(false));
    }

    @Test
    public void removeExistingKeyReturnsMappedToKeyValueTest(){
        m.put(1, "mappedKey");
        assertThat(m.remove(1), is(equalTo("mappedKey")));
    }

    @Test
    public void removeNotExistingKeyReturnsNullTest(){
        assertThat(m.remove(1), is(equalTo(null)));
    }

    @Test
    public void removeKeyMappedToNullReturnsNullTest(){
        m.put(1, null);
        assertThat(m.remove(1), is(equalTo(null)));
        assertThat(m.containsKey(1), is(false));

    }

    @Test(expected = NullPointerException.class)
    public void removeNullKeyThrowsExceptionTest(){
        m.remove(null);
    }

    @Test
    public void clearTest(){
        m.put(1, "mappedKey");
        m.put(2, "mappedKey");
        m.put(3, "mappedKey");

        m.clear();

        assertThat(m.isEmpty(), is(true));
    }

    @Test
    public void containsValueWorksProperlyIfValuePresentedInMapTest() {

    }

    @Test
    public void containsValueWorksProperlyIfValueDoesNotPresentedInMapTest() {

    }

    @Test(expected = IllegalArgumentException.class)
    public void containsValueThrowsExceptionWithWrongValueTypeAsArgumentTest() {
        m.put(1, "");
        m.containsValue(new Integer(1));
    }

}
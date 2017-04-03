package com.epam.java.se.unit99;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CustomListsTest {

    private List<String> list;

    public CustomListsTest(List<String> list) {
        this.list = list;
    }

    @Parameterized.Parameters
    public static Collection<Object> data() {
        return Arrays.asList(new Object[]{
                new CustomArrayList(),
                new CustomLinkedList()
        });
    }

    @Before
    public void init() {
        list.clear();
    }

    @Test
    public void newListIsEmptyTest() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void listIsNotEmptyAfterAddingElementTest() {
        list.add("element");
        assertThat(list.isEmpty(), is(false));
    }

    @Test
    public void listContainsElementAddedBeforeTest() {
        String value = "element";

        list.add(value);

        assertTrue(list.contains(value));
    }

    @Test
    public void listDoesNotContainNotAddedElementTest() throws Exception {
        list.add("addedElement");
        assertFalse(list.contains("notAddedElement"));
    }

    @Test
    public void listContainsNullIfItWasAddedTest() {

        list.add(null);

        assertTrue(list.contains(null));
    }

    @Test
    public void listDoesNotContainNotAddedNullTest() {
        list.add("element");
        assertFalse(list.contains(null));
    }

    @Test
    public void listSizeIsDynamic() throws Exception {
        int size = 50;

        for (int i = 0; i < size; i++) {
            list.add(String.valueOf(i));
        }

        assertThat(list.size(), is(size));
    }

    @Test
    public void possibleToAddDuplicateTest() throws Exception {
        list.add("duplicate");
        list.add("duplicate");

        assertThat(list.size(), is(2));
        assertThat(list.contains("duplicate"), is(true));
    }

    @Test
    public void getWorksProperlyWithPresentedInListElementTest() {

        fillList();

        assertThat(list.get(1), is(equalTo("element1")));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getThrowsExceptionWithArgumentGreaterThanItsSizeTest() throws Exception {

        fillList();

        list.get(list.size());
    }

    @Test
    public void removePresentedElementByValueWorksProperlyTest() throws Exception {
        fillList();

        assertThat(list.remove("element4"), is(true));
        assertFalse(list.contains("element4"));
    }

    @Test
    public void removePresentedElementByValueDecreasesSizeTest() throws Exception {
        fillList();

        assertThat(list.size(), is(6));

        list.remove("element4");

        assertThat(list.size(), is(5));
    }

    @Test
    public void removeNotPresentedElementByValueWorksProperlyTest() throws Exception {
        fillList();

        assertThat(list.remove("notPresented"), is(false));
    }

    @Test
    public void removeElementByIndexWorksProperlyTest() throws Exception {
        fillList();

        String removed = list.remove(2);

        assertFalse(list.contains("element2"));
        assertThat(removed, equalTo("element2"));
    }

    @Test
    public void removeElementByIndexDecreasesSizeTest() throws Exception {
        fillList();

        assertThat(list.size(), is(6));

        list.remove(2);

        assertThat(list.size(), is(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeByIndexThrowsExceptionWithArgumentGreaterThanItsSizeTest() throws Exception {
        fillList();

        list.remove(list.size());
    }

    private void fillList() {
        list.add("element0");
        list.add("element1");
        list.add("element2");
        list.add("element3");
        list.add("element4");
        list.add("element5");
    }
}
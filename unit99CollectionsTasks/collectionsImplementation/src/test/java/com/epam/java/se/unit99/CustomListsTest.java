package com.epam.java.se.unit99;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;
import java.util.stream.IntStream;

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

        fillListWithSixStrings();

        assertThat(list.get(1), is(equalTo("element1")));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getThrowsExceptionWithArgumentGreaterThanItsSizeTest() throws Exception {

        fillListWithSixStrings();

        list.get(list.size());
    }

    @Test
    public void removePresentedElementByValueWorksProperlyTest() throws Exception {
        fillListWithSixStrings();

        assertThat(list.remove("element4"), is(true));
        assertFalse(list.contains("element4"));
    }

    @Test
    public void removePresentedElementByValueDecreasesSizeTest() throws Exception {
        fillListWithSixStrings();

        assertThat(list.size(), is(6));

        list.remove("element4");

        assertThat(list.size(), is(5));
    }

    @Test
    public void removeNotPresentedElementByValueWorksProperlyTest() throws Exception {
        fillListWithSixStrings();

        assertThat(list.remove("notPresented"), is(false));
    }

    @Test
    public void removeElementByIndexWorksProperlyTest() throws Exception {
        fillListWithSixStrings();

        String removed = list.remove(2);

        assertFalse(list.contains("element2"));
        assertThat(removed, equalTo("element2"));
    }

    @Test
    public void removeElementByIndexDecreasesSizeTest() throws Exception {
        fillListWithSixStrings();

        assertThat(list.size(), is(6));

        list.remove(2);

        assertThat(list.size(), is(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeByIndexThrowsExceptionWithArgumentGreaterThanItsSizeTest() throws Exception {
        fillListWithSixStrings();

        list.remove(list.size());
    }

    @Test
    public void toArrayOnNotEmptyListReturnsArrayOfAllListElementsTest() throws Exception {
        fillListWithSixStrings();

        Object[] array = list.toArray();

        IntStream.range(0, 6).forEach(
                i -> assertThat(array[i], equalTo("element" + i))
        );
    }

    @Test
    public void toArrayOnEmptyListReturnsEmptyArrayTest() throws Exception {
        Object[] array = list.toArray();
        assertThat(array.length, is(0));
    }

    @Test
    public void toArrayWithTypeOnNotEmptyListReturnsArrayWithAllListElementsWhenArraySizeIsLesserOrEqualThenListSizeTest() throws Exception {
        fillListWithSixStrings();

        String[] array = list.toArray(new String[0]);

        IntStream.range(0, 6).forEach(
                i -> assertThat(array[i], equalTo("element" + i))
        );
    }

    @Test
    public void toArrayWithTypeOnNotEmptyListReturnsArrayWithSameSizeAsListWhenArraySizeIsLesserOrEqualThenListSizeTest() throws Exception {
        fillListWithSixStrings();

        String[] array = list.toArray(new String[0]);

        assertThat(array.length, is(6));
    }

    @Test
    public void toArrayWithTypeOnEmptyListReturnsEmptyArrayTest() throws Exception {
        String[] array = list.toArray(new String[0]);

        assertThat(array.length, is(0));
    }

    @Test
    public void toArrayWithTypeOnNotEmptyListReturnsArrayWithAllListElementsWhenArraySizeIsGreaterThenListSizeTest() throws Exception {
        fillListWithSixStrings();

        String[] baseArray = new String[10];
        IntStream.range(0, 10).forEach(
                i -> baseArray[i] = String.valueOf(i)
        );

        String[] array = list.toArray(baseArray);

        IntStream.range(0, 6).forEach(
                i -> assertThat(array[i], equalTo("element" + i))
        );
    }

    @Test
    public void toArrayWithTypeOnNotEmptyListDoesNotChangeInitialSizeOfArrayWhenArraySizeIsGreaterThenListSizeTest() throws Exception {
        fillListWithSixStrings();

        String[] array = list.toArray(new String[10]);

        assertThat(array.length, is(10));
    }

    @Test
    public void toArrayWithTypeOnNotEmptyListSetsNullInListSizePositionOfArrayWhenArraySizeIsGreaterThenListSizeTest() throws Exception {
        fillListWithSixStrings();

        String[] baseArray = new String[10];
        IntStream.range(0, 10).forEach(
                i -> baseArray[i] = String.valueOf(i)
        );

        String[] array = list.toArray(baseArray);

        assertThat(array[6], equalTo(null));
    }

    @Test
    public void toArrayWithTypeOnNotEmptyListDoesNotChangeArrayElementsWithIndexesGreaterThenListSizeTest() throws Exception {
        fillListWithSixStrings();

        String[] baseArray = new String[10];
        IntStream.range(0, 10).forEach(
                i -> baseArray[i] = String.valueOf(i)
        );

        String[] array = list.toArray(baseArray);

        IntStream.range(7, 10).forEach(
                i -> assertThat(array[i], equalTo(String.valueOf(i)))
        );
    }

    @Test(expected = ArrayStoreException.class)
    public void toArrayWithTypeThrowsExceptionOnInappropriateArrayTypeTest() {
        fillListWithSixStrings();

        Double[] array = list.toArray(new Double[15]);
    }

    @Test
    public void iteratorConsidersAllElementsOfListTest() throws Exception {
        fillListWithSixStrings();

        Iterator iterator = list.iterator();

        int iteratorSize = 0;

        while(iterator.hasNext()){
            iteratorSize++;
            iterator.next();
        }

        assertThat(iteratorSize, equalTo(list.size()));
    }

    @Test
    public void iteratorReturnsElementsOfListInRightOrderTest() throws Exception {
        fillListWithSixStrings();

        Iterator iterator = list.iterator();

        int iteratorSize = 0;

        while(iterator.hasNext()){
            assertThat(iterator.next(), equalTo(list.get(iteratorSize++)));
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorThrowsExceptionWhileTryingToReturnElementOutOfListBoundsTest() throws Exception {
        fillListWithSixStrings();

        Iterator iterator = list.iterator();

        while(iterator.hasNext()){
            iterator.next();
        }

        iterator.next();
    }

    private void fillListWithSixStrings() {
        list.add("element0");
        list.add("element1");
        list.add("element2");
        list.add("element3");
        list.add("element4");
        list.add("element5");
    }
}
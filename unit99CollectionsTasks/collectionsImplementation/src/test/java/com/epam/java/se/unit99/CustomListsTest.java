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

        while (iterator.hasNext()) {
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

        while (iterator.hasNext()) {
            assertThat(iterator.next(), equalTo(list.get(iteratorSize++)));
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorThrowsExceptionWhileTryingToReturnElementOutOfListBoundsTest() throws Exception {
        fillListWithSixStrings();

        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            iterator.next();
        }

        iterator.next();
    }

    @Test
    public void containsAllReturnsTrueIfAllElementsOfCollectionAreStoredInListTest() throws Exception {
        fillListWithSixStrings();

        List<String> storedList = new ArrayList<>();
        storedList.add("element0");
        storedList.add("element3");
        storedList.add("element5");

        assertThat(list.containsAll(storedList), is(true));
    }

    @Test
    public void containsAllReturnsFalseIfOneOrMoreElementsOfCollectionAreNotStoredInListTest() throws Exception {
        fillListWithSixStrings();

        List<String> wrongList = new ArrayList<>();
        wrongList.add("element0");
        wrongList.add("element3");
        wrongList.add("wrong item");

        assertThat(list.containsAll(wrongList), is(false));
    }

    @Test
    public void containsAllWorksProperlyIfCollectionContainsNullTest() throws Exception {
        fillListWithSixStrings();

        List<String> wrongList = new ArrayList<>();
        wrongList.add("element0");
        wrongList.add("element3");
        wrongList.add(null);

        assertThat(list.containsAll(wrongList), is(false));
    }

    @Test
    public void addAllReturnsTrueIfElementsFromCollectionWasAddedInListTest() throws Exception {
        fillListWithSixStrings();

        List<String> listToAdd = new ArrayList<>();
        listToAdd.add("element0");
        listToAdd.add("element3");

        assertThat(list.addAll(listToAdd), is(true));
    }

    @Test
    public void addAllActuallyAddsElementsFromCollectionToListTest() throws Exception {
        fillListWithSixStrings();

        List<String> listToAdd = new ArrayList<>();
        listToAdd.add("element0");
        listToAdd.add("element3");

        list.addAll(listToAdd);

        assertThat(list.size(), equalTo(8));
    }

    @Test
    public void addAllReturnsFalseIfCollectionWasEmptyTest() throws Exception {
        fillListWithSixStrings();

        List<String> listToAdd = new ArrayList<>();

        assertThat(list.addAll(listToAdd), is(false));
    }

    @Test
    public void addAllDoesNotModifyListIfCollectionWasEmptyTest() throws Exception {
        fillListWithSixStrings();

        List<String> listToAdd = new ArrayList<>();

        list.addAll(listToAdd);

        assertThat(list.size(), equalTo(6));
    }

    @Test
    public void addAllWorksProperlyWithCollectionThatContainsNullTest() throws Exception {
        fillListWithSixStrings();

        List<String> listToAdd = new ArrayList<>();
        listToAdd.add("element0");
        listToAdd.add("element3");
        listToAdd.add(null);

        list.addAll(listToAdd);
        assertThat(list.size(), equalTo(9));
    }

    @Test(expected = NullPointerException.class)
    public void addAllThrowsExceptionWithNullAsArgumentTest() throws Exception {
        fillListWithSixStrings();

        list.addAll(null);
    }

    @Test
    public void removeAllReturnsTrueIfElementsFromCollectionWasRemovedFromListTest() throws Exception {
        fillListWithSixStrings();

        List<String> listToRemove = new ArrayList<>();
        listToRemove.add("element0");
        listToRemove.add("element3");

        assertThat(list.removeAll(listToRemove), is(true));
    }

    @Test
    public void removeAllActuallyRemovesElementsOfCollectionFromListTest() throws Exception {
        fillListWithSixStrings();

        List<String> listToRemove = new ArrayList<>();
        listToRemove.add("element0");
        listToRemove.add("element3");

        list.removeAll(listToRemove);

        assertThat(list.size(), equalTo(4));
        assertThat(list.contains("element0"), is(false));
        assertThat(list.contains("element3"), is(false));
    }

    @Test
    public void removeAllReturnsFalseIfElementsFromCollectionWasNotPresentedInListTest() throws Exception {
        fillListWithSixStrings();

        List<String> listToRemove = new ArrayList<>();
        listToRemove.add("notPresented1");
        listToRemove.add("notPresented2");

        assertThat(list.removeAll(listToRemove), is(false));
    }

    @Test
    public void removeAllDoesNotChangeListIfElementsFromCollectionWasNotPresentedInListTest() throws Exception {
        fillListWithSixStrings();

        List<String> listToRemove = new ArrayList<>();
        listToRemove.add("notPresented1");
        listToRemove.add("notPresented2");

        list.removeAll(listToRemove);

        assertThat(list.size(), equalTo(6));
    }

    @Test
    public void removeAllWorksProperlyWithCollectionThatContainsNullTest() throws Exception {
        fillListWithSixStrings();

        List<String> listToRemove = new ArrayList<>();
        listToRemove.add("element0");
        listToRemove.add("element3");
        listToRemove.add(null);

        assertThat(list.removeAll(listToRemove), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void removeAllThrowsExceptionWithNullAsArgumentTest() throws Exception {
        fillListWithSixStrings();

        list.removeAll(null);
    }

    @Test
    public void retainAllReturnsTrueIfElementsOutOfCollectionWasRemovedFromListTest() throws Exception {
        fillListWithSixStrings();

        List<String> listToRetain = new ArrayList<>();
        listToRetain.add("element0");
        listToRetain.add("element3");

        assertThat(list.retainAll(listToRetain), is(true));
    }

    @Test
    public void retainAllActuallyRemovesElementsOutOfCollectionFromListTest() throws Exception {
        fillListWithSixStrings();

        List<String> listToRetain = new ArrayList<>();
        listToRetain.add("element0");
        listToRetain.add("element3");

        list.retainAll(listToRetain);

        assertThat(list.size(), equalTo(2));
        assertThat(list.contains("element0"), is(true));
        assertThat(list.contains("element3"), is(true));
        assertThat(list.contains("element1"), is(false));
    }

    @Test
    public void retainAllReturnsFalseIfElementsFromCollectionTotallyMatchWithListElementsTest() throws Exception {
        fillListWithSixStrings();

        List<String> listToRetain = new ArrayList<>();
        listToRetain.addAll(list);

        assertThat(list.retainAll(listToRetain), is(false));
    }

    @Test
    public void retainAllDoesNotChangeListIfElementsFromCollectionTotallyMatchWithListElementsTest() throws Exception {
        fillListWithSixStrings();

        List<String> listToRetain = new ArrayList<>();
        listToRetain.addAll(list);

        list.retainAll(listToRetain);

        assertThat(list.size(), equalTo(6));
    }

    @Test
    public void retainAllWorksProperlyWithCollectionThatContainsNullTest() throws Exception {
        fillListWithSixStrings();

        list.add(null);

        List<String> listToRemove = new ArrayList<>();
        listToRemove.add("element0");
        listToRemove.add("element3");
        listToRemove.add(null);

        list.retainAll(listToRemove);

        assertThat(list.size(), equalTo(3));
    }

    @Test(expected = NullPointerException.class)
    public void retainAllThrowsExceptionWithNullAsArgumentTest() throws Exception {
        fillListWithSixStrings();

        list.retainAll(null);
    }

    @Test
    public void setChangesListElementInRequiredPositionToTheRequiredElementTest() throws Exception {
        fillListWithSixStrings();

        list.set(4, "newElement");

        assertThat(list.get(4), equalTo("newElement"));
        assertThat(list.contains("element4"), is(false));
    }

    @Test
    public void setReturnsListElementOnRequiredPositionThatWasBeforeSettingTest() throws Exception {
        fillListWithSixStrings();

        assertThat(list.set(4, "newElement"), equalTo("element4"));
    }

    @Test
    public void setWorksProperlyWithNullAsRequiredElementTest() throws Exception {
        fillListWithSixStrings();

        list.set(4, null);

        assertThat(list.get(4), equalTo(null));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setThrowsExceptionOnAttemptToChangeElementOutOfListSizeTest() throws Exception {
        fillListWithSixStrings();

        list.set(list.size(), "newElement");
    }

    @Test
    public void addWithIndexPutsNewRequiredElementInRequiredPositionTest() throws Exception {
        fillListWithSixStrings();

        list.add(4, "newElement");

        assertThat(list.get(4), equalTo("newElement"));
    }

    @Test
    public void addWithIndexIncrementsListSizeTest() throws Exception {
        fillListWithSixStrings();

        list.add(4, "newElement");

        assertThat(list.size(), equalTo(7));
    }

    @Test
    public void addWithIndexShiftsAllListElementWithIndexesEqualOrGreaterToTheRightTest() throws Exception {
        fillListWithSixStrings();

        list.add(4, "newElement");

        IntStream.range(5, 7).forEach(
                i -> assertThat(list.get(i), equalTo("element" + (i - 1)))
        );

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addThrowsExceptionOnAttemptToPutElementOnPositionGreaterThanListSizeTest() throws Exception {
        fillListWithSixStrings();

        list.add(list.size() + 1, "newElement");
    }

    @Test
    public void addWithIndexEqualsToListSizeWorksLikeSimpleAddTest() throws Exception {
        fillListWithSixStrings();

        list.add(6, "newElement");

        assertThat(list.size(), equalTo(7));
        assertThat(list.get(6), equalTo("newElement"));
    }

    @Test
    public void addWithIndexDoesNotBreakListElementsSequenceTest() throws Exception {
        fillListWithSixStrings();
        fillListWithSixStrings();

        list.add(3, "newElement");

        IntStream.range(0, 3).forEach(
                i -> assertThat(list.get(i), equalTo("element" + i))
        );

        assertThat(list.get(3), equalTo("newElement"));

        IntStream.range(4, 7).forEach(
                i -> assertThat(list.get(i), equalTo("element" + (i - 1)))
        );
    }

    @Test
    public void indexOfReturnsIndexOfFirstElementThatMatchesIfItPresentedInListTest() throws Exception {
        fillListWithSixStrings();
        fillListWithSixStrings();

        assertThat(list.indexOf("element4"), equalTo(4));
    }

    @Test
    public void indexOfReturnsMinusOneIfSuchElementIsNotPresentedInListTest() throws Exception {
        fillListWithSixStrings();
        fillListWithSixStrings();

        assertThat(list.indexOf("notPresented"), equalTo(-1));
    }

    @Test
    public void indexOfWorksProperlyWithNullAsArgumentTest() throws Exception {
        fillListWithSixStrings();
        fillListWithSixStrings();

        assertThat(list.indexOf(null), equalTo(-1));
    }

    @Test
    public void lastIndexOfReturnsIndexOfLastElementThatMatchesIfItPresentedInListTest() throws Exception {
        fillListWithSixStrings();
        fillListWithSixStrings();

        assertThat(list.lastIndexOf("element4"), equalTo(10));
    }

    @Test
    public void lastIndexOfReturnsMinusOneIfSuchElementIsNotPresentedInListTest() throws Exception {
        fillListWithSixStrings();
        fillListWithSixStrings();

        assertThat(list.lastIndexOf("notPresented"), equalTo(-1));
    }

    @Test
    public void lastIndexOfWorksProperlyWithNullAsArgumentTest() throws Exception {
        fillListWithSixStrings();
        fillListWithSixStrings();

        assertThat(list.lastIndexOf(null), equalTo(-1));
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
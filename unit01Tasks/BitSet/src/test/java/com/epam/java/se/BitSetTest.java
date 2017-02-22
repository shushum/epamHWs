package com.epam.java.se;

import jdk.nashorn.internal.codegen.types.BitwiseType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 03.02.2017.
 */
public class BitSetTest {
    @Test
    public void addValue() throws Exception {
        final BitSet set = new BitSet();
        set.add(0);
        assertTrue(set.contains(0));
        set.add(100);
        assertTrue(set.contains(100));
        set.add(Integer.MAX_VALUE);
        assertTrue(set.contains(Integer.MAX_VALUE));
    }

    @Test
    public void doubleAddValue() throws Exception {
        final BitSet set = new BitSet();
        set.add(0);
        set.add(0);
        assertTrue(set.contains(0));
    }

    @Test
    public void addNegativeValue() throws Exception {
        final BitSet set = new BitSet();
        set.add(-1);
        assertFalse(set.contains(-1));
    }

    @Test
    public void doesNotContainNotAddedValue() throws Exception {
        final BitSet set = new BitSet();
        assertFalse(set.contains(0));
        assertFalse(set.contains(100));
        assertFalse(set.contains(200));
    }

    @Test
    public void removeAddedValue() throws Exception {
        final BitSet set = new BitSet();

        set.add(0);
        assertTrue(set.contains(0));
        set.remove(0);
        assertFalse(set.contains(0));

        set.add(100);
        assertTrue(set.contains(100));
        set.remove(100);
        assertFalse(set.contains(100));

        set.add(Integer.MAX_VALUE);
        assertTrue(set.contains(Integer.MAX_VALUE));
        set.remove(Integer.MAX_VALUE);
        assertFalse(set.contains(Integer.MAX_VALUE));
    }

    @Test
    public void doubleAddOnesRemoved() throws Exception {
        final BitSet set = new BitSet();

        set.add(100);
        set.add(100);
        assertTrue(set.contains(100));
        set.remove(100);
        assertFalse(set.contains(100));
    }

    @Test
    public void removeNotAddedValue() throws Exception {
        final BitSet set = new BitSet();

        assertFalse(set.contains(100));
        set.remove(100);
        assertFalse(set.contains(100));
    }

    @Test
    public void contains() throws Exception {

        final BitSet set = new BitSet();
        for (int i = -1; i < 130; i++) {
            assertFalse(set.contains(i));
        }

        set.add(-1);
        set.add(0);
        set.add(64);
        set.add(63);
        set.add(7);
        set.add(45);
        set.add(129);

        for (int i = -1; i < 130; i++) {
            if (i == 0 || i == 7 || i == 45 || i == 63 || i == 64 || i == 129) {
                assertTrue(set.contains(i));
            } else {
                assertFalse(set.contains(i));
            }
        }

    }

    @Test
    public void unionWithLegalArguments() throws Exception {
        final BitSet set = new BitSet();
        set.add(7);
        set.add(70);
        final BitSet otherSet = new BitSet();
        otherSet.add(140);

        final BitSet unionSet = set.union(otherSet);

        for (int i = 0; i < 150; i++) {
            if (i == 7 || i == 70 || i == 140) {
                assertTrue(unionSet.contains(i));
            } else {
                assertFalse(unionSet.contains(i));
            }
        }
    }

    @Test
    public void unionWithEmptyArgumentSet() throws Exception {
        final BitSet set = new BitSet();
        set.add(7);
        set.add(70);
        final BitSet otherSet = new BitSet();

        final BitSet unionSet = set.union(otherSet);

        for (int i = 0; i < 80; i++) {
            if (i == 7 || i == 70) {
                assertTrue(unionSet.contains(i));
            } else {
                assertFalse(unionSet.contains(i));
            }
        }
    }

    @Test
    public void unionWithEmptyCurrentSet() throws Exception {
        final BitSet set = new BitSet();

        final BitSet otherSet = new BitSet();
        otherSet.add(7);
        otherSet.add(140);

        final BitSet unionSet = set.union(otherSet);

        for (int i = 0; i < 150; i++) {
            if (i == 7 || i == 140) {
                assertTrue(unionSet.contains(i));
            } else {
                assertFalse(unionSet.contains(i));
            }
        }
    }

    @Test
    public void unionBothEmptySets() throws Exception {
        final BitSet set = new BitSet();
        final BitSet otherSet = new BitSet();

        final BitSet unionSet = set.union(otherSet);

        for (int i = 0; i < 70; i++) {
            assertFalse(unionSet.contains(i));
        }
    }

    @Test
    public void intersectionWithLegalArguments() throws Exception {
        final BitSet set = new BitSet();
        set.add(7);
        set.add(70);
        final BitSet otherSet = new BitSet();
        otherSet.add(7);
        otherSet.add(140);

        final BitSet intersectionSet = set.intersection(otherSet);

        for (int i = 0; i < 150; i++) {
            if (i == 7) {
                assertTrue(intersectionSet.contains(i));
            } else {
                assertFalse(intersectionSet.contains(i));
            }
        }
    }

    @Test
    public void intersectionWithEmptyArgumentSet() throws Exception {
        final BitSet set = new BitSet();
        set.add(7);
        set.add(70);
        final BitSet otherSet = new BitSet();

        final BitSet intersectionSet = set.intersection(otherSet);

        for (int i = 0; i < 150; i++) {
            assertFalse(intersectionSet.contains(i));
        }
    }

    @Test
    public void intersectionWithEmptyCurrentSet() throws Exception {
        final BitSet set = new BitSet();
        final BitSet otherSet = new BitSet();
        otherSet.add(7);
        otherSet.add(140);

        final BitSet intersectionSet = set.intersection(otherSet);

        for (int i = 0; i < 150; i++) {
            assertFalse(intersectionSet.contains(i));
        }
    }

    @Test
    public void intersectionWithBothEmptySets() throws Exception {
        final BitSet set = new BitSet();
        final BitSet otherSet = new BitSet();

        final BitSet intersectionSet = set.intersection(otherSet);

        for (int i = 0; i < 150; i++) {
            assertFalse(intersectionSet.contains(i));
        }
    }


    @Test
    public void differenceWithLegalArguments() throws Exception {
        final BitSet set = new BitSet();
        set.add(7);
        set.add(70);
        final BitSet otherSet = new BitSet();
        otherSet.add(7);
        otherSet.add(140);

        final BitSet differenceSet = set.difference(otherSet);

        for (int i = 0; i < 150; i++) {
            if (i == 70 || i == 140) {
                assertTrue(differenceSet.contains(i));
            } else {
                assertFalse(differenceSet.contains(i));
            }
        }
    }

    @Test
    public void differenceWithEmptyArgumentSet() throws Exception {
        final BitSet set = new BitSet();
        set.add(7);
        set.add(70);
        final BitSet otherSet = new BitSet();

        final BitSet differenceSet = set.difference(otherSet);

        for (int i = 0; i < 150; i++) {
            if (i == 70 || i == 7) {
                assertTrue(differenceSet.contains(i));
            } else {
                assertFalse(differenceSet.contains(i));
            }
        }
    }

    @Test
    public void differenceWithEmptyCurrentSet() throws Exception {
        final BitSet set = new BitSet();
        final BitSet otherSet = new BitSet();
        otherSet.add(7);
        otherSet.add(140);

        final BitSet differenceSet = set.difference(otherSet);

        for (int i = 0; i < 150; i++) {
            if (i == 7 || i == 140) {
                assertTrue(differenceSet.contains(i));
            } else {
                assertFalse(differenceSet.contains(i));
            }
        }
    }

    @Test
    public void differenceWithBothEmptySets() throws Exception {
        final BitSet set = new BitSet();
        final BitSet otherSet = new BitSet();

        final BitSet differenceSet = set.difference(otherSet);

        for (int i = 0; i < 150; i++) {
            assertFalse(differenceSet.contains(i));
        }
    }

    @Test
    public void isSubsetOfWithLegalArguments() throws Exception {
        BitSet set = new BitSet();
        set.add(7);
        set.add(70);
        BitSet anotherSet = new BitSet();
        anotherSet.add(7);
        anotherSet.add(70);
        anotherSet.add(140);

        assertFalse(anotherSet.isSubsetOf(set));
        assertTrue(set.isSubsetOf(anotherSet));
        assertTrue(set.isSubsetOf(set));
    }

    @Test
    public void isSubsetOfEmptySetAndVV() throws Exception {
        BitSet set = new BitSet();
        set.add(7);
        set.add(70);
        BitSet anotherSet = new BitSet();

        assertTrue(anotherSet.isSubsetOf(set));
        assertFalse(set.isSubsetOf(anotherSet));
    }

    @Test
    public void isSubsetOfBothEmptySets() throws Exception {
        BitSet set = new BitSet();
        BitSet anotherSet = new BitSet();

        assertTrue(anotherSet.isSubsetOf(set));
        assertTrue(set.isSubsetOf(anotherSet));
    }

}
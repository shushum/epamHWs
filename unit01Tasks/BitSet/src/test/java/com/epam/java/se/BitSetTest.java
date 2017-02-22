package com.epam.java.se;

import jdk.nashorn.internal.codegen.types.BitwiseType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 03.02.2017.
 */
public class BitSetTest {
    @Test
    public void add() throws Exception {
        final BitSet set = new BitSet();

        assertFalse(set.contains(0));
        set.add(0);
        assertTrue(set.contains(0));

        assertFalse(set.contains(63));
        set.add(63);
        set.add(63);
        assertTrue(set.contains(63));

        set.add(-1);
        assertFalse(set.contains(-1));
        set.add(64);
        assertTrue("64",set.contains(64));
        set.add(130);
        assertTrue("64",set.contains(130));
    }

    @Test
    public void remove() throws Exception {
        final BitSet set = new BitSet();

        assertFalse(set.contains(0));
        set.add(0);
        assertTrue(set.contains(0));
        set.add(0);
        set.remove(0);
        assertFalse(set.contains(0));

        set.remove(0);
        assertFalse(set.contains(0));

        set.add(130);
        set.remove(131);
        assertFalse(set.contains(131));

    }

    @Test
    public void contains() throws Exception {

        final BitSet set = new BitSet();
        for (int i = -1; i < 130; i++) {
            assertFalse(set.contains(i));
        }

        set.add(-1);
        set.add(-1);
        set.add(0);
        set.add(0);
        set.add(64);
        set.add(64);
        set.add(63);
        set.add(7);
        set.add(45);
        set.add(129);

        assertFalse(set.contains(13));

        for (int i = -1; i < 130; i++) {
            if (i == 0 || i == 7 || i == 45 || i == 63 || i == 64 || i == 129) {
                assertTrue(set.contains(i));
            } else {
                assertFalse("At index " + i, set.contains(i));
            }
        }

    }

    @Test
    public void union() throws Exception {
        final BitSet set = new BitSet();
        final BitSet invertSet = new BitSet();

        set.add(7);

        for (int i=0;i<128;i++){
            invertSet.add(i);
        }
        invertSet.remove(7);

        BitSet sameSet = set.union(set);
        BitSet fullSet = set.union(invertSet);
        BitSet viceVersaSet = invertSet.union(set);

        for (int i=0;i<64;i++){
            if (i==7) {
                assertTrue(sameSet.contains(i));
            } else {
            assertFalse("At index "+i, sameSet.contains(i));
            }
        }

        for (int i=0; i<128; i++){
            assertTrue(fullSet.contains(i));
            assertTrue(viceVersaSet.contains(i));
        }
    }

    @Test
    public void intersection() throws Exception {
        final BitSet set = new BitSet();
        final BitSet invertSet = new BitSet();

        set.add(7);

        for (int i=0;i<128;i++){
            invertSet.add(i);
        }
        invertSet.remove(7);

        BitSet sameSet = set.intersection(set);
        BitSet emptySet = set.intersection(invertSet);
        BitSet viceVersaSet = invertSet.intersection(set);

        for (int i=0;i<64;i++){
            if (i == 7) {
                assertTrue(sameSet.contains(i));
            } else {
                assertFalse("At index "+i, sameSet.contains(i));
            }
        }

        for (int i=0; i<128; i++){
            assertFalse("At index " + i, emptySet.contains(i));
            assertFalse("At index " + i, viceVersaSet.contains(i));
        }
    }

    @Test
    public void difference() throws Exception {

        BitSet sameSet = new BitSet();
        sameSet.add(7);
        BitSet zeroSet = sameSet.difference(sameSet);
        for(int i = 0; i<64; i++){
            assertFalse(zeroSet.contains(i));
        }
        sameSet.add(15);
        sameSet.add(63);
        sameSet.add(-1);

        long[] cheese = {-1,-1};
        BitSet fullSet = new BitSet(cheese);
        BitSet set = fullSet.difference(sameSet);
        for(int i = 0; i<128; i++){
            if (i==7 || i==15 || i==63){
                assertFalse(set.contains(i));
            } else {
                assertTrue(set.contains(i));
            }
        }

    }

    @Test
    public void isSubsetOf() throws Exception {
        BitSet refSet = new BitSet();
        refSet.add(1);
        refSet.add(54);
        refSet.add(7);
        assertTrue(refSet.isSubsetOf(refSet));

        BitSet checkSet = new BitSet();
        refSet.add(54);
        refSet.add(7);
        assertFalse(refSet.isSubsetOf(checkSet));
        assertTrue(checkSet.isSubsetOf(refSet));

        BitSet unionCheckSet = new BitSet();
        refSet.add(200);
        refSet.add(18);
        refSet.add(137);

        BitSet unionSet = refSet.union(unionCheckSet);
        assertTrue(unionCheckSet.isSubsetOf(unionSet));
        assertTrue(refSet.isSubsetOf(unionSet));
        assertTrue(checkSet.isSubsetOf(unionSet));

        BitSet finalCheckSet = new BitSet();
        assertTrue(finalCheckSet.isSubsetOf(unionCheckSet));
        finalCheckSet.add(96);
        assertFalse(finalCheckSet.isSubsetOf(unionCheckSet));


    }

}
package org.howard.edu.lsp.assignment6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test cases for IntegerSet.
 * Each method is tested with at least one normal case and one edge case.
 */
public class IntegerSetTest {

    private IntegerSet set1;
    private IntegerSet set2;

    @BeforeEach
    public void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
    }

    // -------------------------------------------------------------------------
    // clear()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("clear() - normal: removes all elements")
    public void testClearNormal() {
        set1.add(1);
        set1.add(2);
        set1.clear();
        assertEquals(0, set1.length());
        assertTrue(set1.isEmpty());
    }

    @Test
    @DisplayName("clear() - edge: clearing an already empty set does not throw")
    public void testClearEmpty() {
        assertDoesNotThrow(() -> set1.clear());
        assertTrue(set1.isEmpty());
    }

    // -------------------------------------------------------------------------
    // length()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("length() - normal: returns correct count after adds")
    public void testLengthNormal() {
        set1.add(10);
        set1.add(20);
        set1.add(30);
        assertEquals(3, set1.length());
    }

    @Test
    @DisplayName("length() - edge: empty set has length 0")
    public void testLengthEmpty() {
        assertEquals(0, set1.length());
    }

    // -------------------------------------------------------------------------
    // equals()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("equals() - normal: same elements same order")
    public void testEqualsNormal() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(1); set2.add(2); set2.add(3);
        assertTrue(set1.equals(set2));
    }

    @Test
    @DisplayName("equals() - edge: same elements different order returns true")
    public void testEqualsDifferentOrder() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(3); set2.add(1); set2.add(2);
        assertTrue(set1.equals(set2));
    }

    @Test
    @DisplayName("equals() - edge: different elements returns false")
    public void testEqualsMismatch() {
        set1.add(1); set1.add(2);
        set2.add(1); set2.add(3);
        assertFalse(set1.equals(set2));
    }

    // -------------------------------------------------------------------------
    // contains()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("contains() - normal: value present in set")
    public void testContainsPresent() {
        set1.add(5);
        assertTrue(set1.contains(5));
    }

    @Test
    @DisplayName("contains() - edge: value not present in set")
    public void testContainsAbsent() {
        set1.add(5);
        assertFalse(set1.contains(99));
    }

    // -------------------------------------------------------------------------
    // largest()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("largest() - normal: returns max value")
    public void testLargestNormal() {
        set1.add(3); set1.add(7); set1.add(1);
        assertEquals(7, set1.largest());
    }

    @Test
    @DisplayName("largest() - edge: single element")
    public void testLargestSingleElement() {
        set1.add(42);
        assertEquals(42, set1.largest());
    }

    @Test
    @DisplayName("largest() - edge: throws exception on empty set")
    public void testLargestEmptyThrows() {
        assertThrows(IntegerSetException.class, () -> set1.largest());
    }

    // -------------------------------------------------------------------------
    // smallest()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("smallest() - normal: returns min value")
    public void testSmallestNormal() {
        set1.add(3); set1.add(7); set1.add(1);
        assertEquals(1, set1.smallest());
    }

    @Test
    @DisplayName("smallest() - edge: single element")
    public void testSmallestSingleElement() {
        set1.add(42);
        assertEquals(42, set1.smallest());
    }

    @Test
    @DisplayName("smallest() - edge: throws exception on empty set")
    public void testSmallestEmptyThrows() {
        assertThrows(IntegerSetException.class, () -> set1.smallest());
    }

    // -------------------------------------------------------------------------
    // add()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("add() - normal: adds new element")
    public void testAddNormal() {
        set1.add(10);
        assertTrue(set1.contains(10));
        assertEquals(1, set1.length());
    }

    @Test
    @DisplayName("add() - edge: duplicate value is not added")
    public void testAddDuplicate() {
        set1.add(5);
        set1.add(5);
        assertEquals(1, set1.length());
    }

    // -------------------------------------------------------------------------
    // remove()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("remove() - normal: removes existing element")
    public void testRemoveNormal() {
        set1.add(1); set1.add(2); set1.add(3);
        set1.remove(2);
        assertFalse(set1.contains(2));
        assertEquals(2, set1.length());
    }

    @Test
    @DisplayName("remove() - edge: removing value not present does not throw or change size")
    public void testRemoveNotPresent() {
        set1.add(1); set1.add(2);
        assertDoesNotThrow(() -> set1.remove(99));
        assertEquals(2, set1.length());
    }

    // -------------------------------------------------------------------------
    // union()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("union() - normal: overlapping sets")
    public void testUnionNormal() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.union(set2);
        assertEquals("[1, 2, 3, 4]", result.toString());
    }

    @Test
    @DisplayName("union() - edge: union with empty set returns copy of original")
    public void testUnionWithEmpty() {
        set1.add(1); set1.add(2);
        IntegerSet result = set1.union(set2);
        assertEquals("[1, 2]", result.toString());
    }

    @Test
    @DisplayName("union() - does not modify original sets")
    public void testUnionDoesNotModifyOriginals() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        set1.union(set2);
        assertEquals(2, set1.length());
        assertEquals(2, set2.length());
    }

    // -------------------------------------------------------------------------
    // intersect()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("intersect() - normal: overlapping sets")
    public void testIntersectNormal() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.intersect(set2);
        assertEquals("[2, 3]", result.toString());
    }

    @Test
    @DisplayName("intersect() - edge: no common elements returns empty set")
    public void testIntersectNoOverlap() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        IntegerSet result = set1.intersect(set2);
        assertTrue(result.isEmpty());
    }

    // -------------------------------------------------------------------------
    // diff()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("diff() - normal: elements in set1 not in set2")
    public void testDiffNormal() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.diff(set2);
        assertEquals("[1]", result.toString());
    }

    @Test
    @DisplayName("diff() - edge: identical sets produce empty result")
    public void testDiffIdenticalSets() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(1); set2.add(2); set2.add(3);
        IntegerSet result = set1.diff(set2);
        assertTrue(result.isEmpty());
    }

    // -------------------------------------------------------------------------
    // complement()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("complement() - normal: elements in set2 not in set1")
    public void testComplementNormal() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.complement(set2);
        assertEquals("[4]", result.toString());
    }

    @Test
    @DisplayName("complement() - edge: disjoint sets returns all of set2")
    public void testComplementDisjoint() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        IntegerSet result = set1.complement(set2);
        assertEquals("[3, 4]", result.toString());
    }

    // -------------------------------------------------------------------------
    // isEmpty()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("isEmpty() - edge: empty set returns true")
    public void testIsEmptyTrue() {
        assertTrue(set1.isEmpty());
    }

    @Test
    @DisplayName("isEmpty() - normal: non-empty set returns false")
    public void testIsEmptyFalse() {
        set1.add(1);
        assertFalse(set1.isEmpty());
    }

    // -------------------------------------------------------------------------
    // toString()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("toString() - normal: elements in ascending order")
    public void testToStringNormal() {
        set1.add(3); set1.add(1); set1.add(2);
        assertEquals("[1, 2, 3]", set1.toString());
    }

    @Test
    @DisplayName("toString() - edge: empty set returns []")
    public void testToStringEmpty() {
        assertEquals("[]", set1.toString());
    }
}

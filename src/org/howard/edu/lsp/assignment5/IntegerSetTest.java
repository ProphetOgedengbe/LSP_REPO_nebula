package org.howard.edu.lsp.assignment5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 tests for IntegerSet.
 * Each method has at least one normal case and one edge case.
 */
public class IntegerSetTest {

    private IntegerSet setA;
    private IntegerSet setB;

    @BeforeEach
    public void setUp() {
        setA = new IntegerSet();
        setB = new IntegerSet();
    }

    // ── clear() ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("clear() removes all elements from a non-empty set")
    public void testClearNonEmpty() {
        setA.add(1);
        setA.add(2);
        setA.clear();
        assertTrue(setA.isEmpty());
    }

    @Test
    @DisplayName("clear() on an already-empty set leaves it empty")
    public void testClearAlreadyEmpty() {
        setA.clear();
        assertTrue(setA.isEmpty());
    }

    // ── length() ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("length() returns 0 for an empty set")
    public void testLengthEmpty() {
        assertEquals(0, setA.length());
    }

    @Test
    @DisplayName("length() returns correct count after adds")
    public void testLengthAfterAdds() {
        setA.add(10);
        setA.add(20);
        setA.add(30);
        assertEquals(3, setA.length());
    }

    @Test
    @DisplayName("length() does not count duplicate adds")
    public void testLengthIgnoresDuplicates() {
        setA.add(5);
        setA.add(5);
        assertEquals(1, setA.length());
    }

    // ── equals() ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("equals() returns true for sets with same elements in same order")
    public void testEqualsSameOrder() {
        setA.add(1); setA.add(2); setA.add(3);
        setB.add(1); setB.add(2); setB.add(3);
        assertTrue(setA.equals(setB));
    }

    @Test
    @DisplayName("equals() returns true for sets with same elements in different order")
    public void testEqualsDifferentOrder() {
        setA.add(1); setA.add(2); setA.add(3);
        setB.add(3); setB.add(2); setB.add(1);
        assertTrue(setA.equals(setB));
    }

    @Test
    @DisplayName("equals() returns false for sets with different elements")
    public void testEqualsDifferentElements() {
        setA.add(1); setA.add(2);
        setB.add(1); setB.add(3);
        assertFalse(setA.equals(setB));
    }

    @Test
    @DisplayName("equals() returns false for sets with different sizes")
    public void testEqualsDifferentSizes() {
        setA.add(1); setA.add(2); setA.add(3);
        setB.add(1); setB.add(2);
        assertFalse(setA.equals(setB));
    }

    @Test
    @DisplayName("equals() returns true for two empty sets")
    public void testEqualsBothEmpty() {
        assertTrue(setA.equals(setB));
    }

    // ── contains() ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("contains() returns true when value is in set")
    public void testContainsPresent() {
        setA.add(7);
        assertTrue(setA.contains(7));
    }

    @Test
    @DisplayName("contains() returns false when value is not in set")
    public void testContainsAbsent() {
        setA.add(7);
        assertFalse(setA.contains(99));
    }

    @Test
    @DisplayName("contains() returns false on empty set")
    public void testContainsEmpty() {
        assertFalse(setA.contains(1));
    }

    // ── largest() ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("largest() returns correct max value")
    public void testLargest() {
        setA.add(3); setA.add(1); setA.add(5); setA.add(2);
        assertEquals(5, setA.largest());
    }

    @Test
    @DisplayName("largest() throws IntegerSetException on empty set")
    public void testLargestEmptyThrows() {
        assertThrows(IntegerSetException.class, () -> setA.largest());
    }

    // ── smallest() ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("smallest() returns correct min value")
    public void testSmallest() {
        setA.add(3); setA.add(1); setA.add(5); setA.add(2);
        assertEquals(1, setA.smallest());
    }

    @Test
    @DisplayName("smallest() throws IntegerSetException on empty set")
    public void testSmallestEmptyThrows() {
        assertThrows(IntegerSetException.class, () -> setA.smallest());
    }

    // ── add() ────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("add() inserts a new element")
    public void testAddNewElement() {
        setA.add(42);
        assertTrue(setA.contains(42));
    }

    @Test
    @DisplayName("add() does not insert a duplicate element")
    public void testAddDuplicate() {
        setA.add(42);
        setA.add(42);
        assertEquals(1, setA.length());
    }

    // ── remove() ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("remove() deletes an existing element")
    public void testRemoveExisting() {
        setA.add(10); setA.add(20); setA.add(30);
        setA.remove(20);
        assertFalse(setA.contains(20));
        assertEquals(2, setA.length());
    }

    @Test
    @DisplayName("remove() on non-existing element does not change the set")
    public void testRemoveNonExisting() {
        setA.add(10);
        setA.remove(99);
        assertEquals(1, setA.length());
    }

    // ── union() ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("union() returns all elements from both overlapping sets")
    public void testUnionOverlapping() {
        setA.add(1); setA.add(2); setA.add(3);
        setB.add(2); setB.add(3); setB.add(4);
        IntegerSet result = setA.union(setB);
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertEquals(4, result.length());
    }

    @Test
    @DisplayName("union() with an empty set returns a copy of this set")
    public void testUnionWithEmpty() {
        setA.add(1); setA.add(2);
        IntegerSet result = setA.union(setB);
        assertTrue(result.equals(setA));
    }

    @Test
    @DisplayName("union() does not modify the original sets")
    public void testUnionOriginalUnchanged() {
        setA.add(1); setA.add(2);
        setB.add(3);
        setA.union(setB);
        assertEquals(2, setA.length());
        assertEquals(1, setB.length());
    }

    // ── intersect() ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("intersect() returns only common elements")
    public void testIntersectOverlapping() {
        setA.add(1); setA.add(2); setA.add(3);
        setB.add(2); setB.add(3); setB.add(4);
        IntegerSet result = setA.intersect(setB);
        assertEquals(2, result.length());
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }

    @Test
    @DisplayName("intersect() of disjoint sets returns empty set")
    public void testIntersectDisjoint() {
        setA.add(1); setA.add(2);
        setB.add(3); setB.add(4);
        IntegerSet result = setA.intersect(setB);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("intersect() does not modify the original sets")
    public void testIntersectOriginalUnchanged() {
        setA.add(1); setA.add(2);
        setB.add(2); setB.add(3);
        setA.intersect(setB);
        assertEquals(2, setA.length());
        assertEquals(2, setB.length());
    }

    // ── diff() ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("diff() returns elements in this set but not in the other")
    public void testDiffOverlapping() {
        setA.add(1); setA.add(2); setA.add(3);
        setB.add(2); setB.add(3); setB.add(4);
        IntegerSet result = setA.diff(setB);
        assertEquals(1, result.length());
        assertTrue(result.contains(1));
    }

    @Test
    @DisplayName("diff() of identical sets returns empty set")
    public void testDiffIdentical() {
        setA.add(1); setA.add(2);
        setB.add(1); setB.add(2);
        IntegerSet result = setA.diff(setB);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("diff() does not modify the original sets")
    public void testDiffOriginalUnchanged() {
        setA.add(1); setA.add(2);
        setB.add(2); setB.add(3);
        setA.diff(setB);
        assertEquals(2, setA.length());
        assertEquals(2, setB.length());
    }

    // ── complement() ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("complement() returns elements in b but not in this set")
    public void testComplementOverlapping() {
        setA.add(1); setA.add(2); setA.add(3);
        setB.add(2); setB.add(3); setB.add(4);
        IntegerSet result = setA.complement(setB);
        assertEquals(1, result.length());
        assertTrue(result.contains(4));
    }

    @Test
    @DisplayName("complement() when this set contains all of b returns empty set")
    public void testComplementSubset() {
        setA.add(1); setA.add(2); setA.add(3);
        setB.add(1); setB.add(2);
        IntegerSet result = setA.complement(setB);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("complement() does not modify the original sets")
    public void testComplementOriginalUnchanged() {
        setA.add(1); setA.add(2);
        setB.add(2); setB.add(3);
        setA.complement(setB);
        assertEquals(2, setA.length());
        assertEquals(2, setB.length());
    }

    // ── isEmpty() ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("isEmpty() returns true for a new set")
    public void testIsEmptyNew() {
        assertTrue(setA.isEmpty());
    }

    @Test
    @DisplayName("isEmpty() returns false after adding an element")
    public void testIsEmptyAfterAdd() {
        setA.add(1);
        assertFalse(setA.isEmpty());
    }

    // ── toString() ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("toString() returns [] for an empty set")
    public void testToStringEmpty() {
        assertEquals("[]", setA.toString());
    }

    @Test
    @DisplayName("toString() returns elements in ascending order")
    public void testToStringSorted() {
        setA.add(3); setA.add(1); setA.add(2);
        assertEquals("[1, 2, 3]", setA.toString());
    }

    @Test
    @DisplayName("toString() works for a single element")
    public void testToStringSingleElement() {
        setA.add(42);
        assertEquals("[42]", setA.toString());
    }
}

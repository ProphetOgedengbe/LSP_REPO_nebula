package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Models a mathematical set of integers using an ArrayList from the Java
 * Collections Framework. A set cannot contain duplicate values. All set
 * operations (union, intersect, diff, complement) return a new IntegerSet
 * and never modify the original sets.
 *
 * <p>Example usage:</p>
 * <pre>
 *   IntegerSet s = new IntegerSet();
 *   s.add(1);
 *   s.add(2);
 *   s.add(3);
 *   System.out.println(s); // [1, 2, 3]
 * </pre>
 */
public class IntegerSet {

    /** Internal storage for the set elements. */
    private ArrayList<Integer> set = new ArrayList<>();

    /** Constructs an empty IntegerSet. */
    public IntegerSet() {}

    /**
     * Removes all elements from the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return the number of elements
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns {@code true} if both sets contain exactly the same elements,
     * regardless of order.
     *
     * @param b the other IntegerSet to compare
     * @return {@code true} if the sets are equal, {@code false} otherwise
     */
    public boolean equals(IntegerSet b) {
        if (this.length() != b.length()) {
            return false;
        }
        ArrayList<Integer> sortedA = new ArrayList<>(set);
        ArrayList<Integer> sortedB = new ArrayList<>(b.set);
        Collections.sort(sortedA);
        Collections.sort(sortedB);
        return sortedA.equals(sortedB);
    }

    /**
     * Returns {@code true} if the set contains the given value.
     *
     * @param value the integer to search for
     * @return {@code true} if the value is in the set, {@code false} otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest element in the set.
     *
     * @return the largest integer in the set
     * @throws IntegerSetException if the set is empty
     */
    public int largest() {
        if (set.isEmpty()) {
            throw new IntegerSetException("Set is empty");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest element in the set.
     *
     * @return the smallest integer in the set
     * @throws IntegerSetException if the set is empty
     */
    public int smallest() {
        if (set.isEmpty()) {
            throw new IntegerSetException("Set is empty");
        }
        return Collections.min(set);
    }

    /**
     * Adds the given item to the set. If the item is already present,
     * the set is not modified (no duplicates allowed).
     *
     * @param item the integer to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes the given item from the set. If the item is not present,
     * the set is not modified.
     *
     * @param item the integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Returns a new IntegerSet containing all elements that appear in either
     * this set or the given set (set union).
     *
     * <p>Example: [1,2,3] union [2,3,4] = [1,2,3,4]</p>
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the union
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(this.set);
        for (int item : intSetb.set) {
            if (!result.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Returns a new IntegerSet containing only elements common to both sets
     * (set intersection).
     *
     * <p>Example: [1,2,3] intersect [2,3,4] = [2,3]</p>
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the intersection
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : this.set) {
            if (intSetb.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Returns a new IntegerSet containing elements in this set but not in
     * the given set (set difference: this - b).
     *
     * <p>Example: [1,2,3] diff [2,3,4] = [1]</p>
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the difference
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : this.set) {
            if (!intSetb.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Returns a new IntegerSet containing elements in the given set but not
     * in this set (complement: b - this).
     *
     * <p>Example: Set1=[1,2,3], Set2=[2,3,4] → complement = [4]</p>
     *
     * @param intSetb the other IntegerSet (treated as the universal set)
     * @return a new IntegerSet representing the complement
     */
    public IntegerSet complement(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : intSetb.set) {
            if (!this.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Returns {@code true} if the set contains no elements.
     *
     * @return {@code true} if the set is empty, {@code false} otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of the set with elements in ascending
     * order, separated by commas and spaces, enclosed in square brackets.
     *
     * <p>Examples: {@code [1, 2, 3]}, {@code []} for an empty set.</p>
     *
     * @return string representation of the set
     */
    @Override
    public String toString() {
        ArrayList<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);
        return sorted.toString();
    }
}

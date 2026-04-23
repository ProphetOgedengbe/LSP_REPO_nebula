package org.howard.edu.lsp.finalexam.question3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GradeCalculatorTest {
    @Test
    public void testAverageTypical() {
        GradeCalculator gc = new GradeCalculator();
        assertEquals(80.0, gc.average(80, 80, 80));
    }

    @Test
    public void testLetterGradeA() {
        GradeCalculator gc = new GradeCalculator();
        assertEquals("A", gc.letterGrade(95.0));
    }

    @Test
    public void testIsPassingTrue() {
        GradeCalculator gc = new GradeCalculator();
        assertTrue(gc.isPassing(75.0));
    }

    // Boundary-value tests
    @Test
    public void testLetterGradeBoundaryB() {
        GradeCalculator gc = new GradeCalculator();
        assertEquals("B", gc.letterGrade(80.0));
    }

    @Test
    public void testIsPassingBoundary() {
        GradeCalculator gc = new GradeCalculator();
        assertTrue(gc.isPassing(60.0));
    }

    // Exception tests
    @Test
    public void testAverageThrowsLow() {
        GradeCalculator gc = new GradeCalculator();
        assertThrows(IllegalArgumentException.class, () -> gc.average(-1, 50, 60));
    }

    @Test
    public void testAverageThrowsHigh() {
        GradeCalculator gc = new GradeCalculator();
        assertThrows(IllegalArgumentException.class, () -> gc.average(101, 90, 80));
    }
}

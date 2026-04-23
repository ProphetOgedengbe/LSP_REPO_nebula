package org.howard.edu.lsp.finalexam.question2;

import java.util.*;

/**
 * Demonstrates polymorphism with the Template Method pattern.
 */
public class Driver {
    public static void main(String[] args) {
        List<Report> reports = new ArrayList<>();
        reports.add(new StudentReport());
        reports.add(new CourseReport());
        for (Report report : reports) {
            report.loadData();
            report.generateReport();
        }
    }
}

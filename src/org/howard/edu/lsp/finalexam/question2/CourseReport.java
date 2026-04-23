package org.howard.edu.lsp.finalexam.question2;

/**
 * Course report implementation.
 */
public class CourseReport extends Report {
    private String courseName;
    private int enrollment;

    @Override
    protected void loadData() {
        this.courseName = "CSCI 363";
        this.enrollment = 45;
    }

    @Override
    protected void formatHeader() {
        System.out.println("Course Report");
    }

    @Override
    protected void formatBody() {
        System.out.println("Course: " + courseName);
        System.out.println("Enrollment: " + enrollment);
    }

    @Override
    protected void formatFooter() {
        System.out.println("End of Course Report");
    }
}

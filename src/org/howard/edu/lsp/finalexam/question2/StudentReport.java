package org.howard.edu.lsp.finalexam.question2;

/**
 * Student report implementation.
 */
public class StudentReport extends Report {
    private String studentName;
    private double gpa;

    @Override
    protected void loadData() {
        this.studentName = "John Doe";
        this.gpa = 3.8;
    }

    @Override
    protected void formatHeader() {
        System.out.println("Student Report");
    }

    @Override
    protected void formatBody() {
        System.out.println("Student Name: " + studentName);
        System.out.println("GPA: " + gpa);
    }

    @Override
    protected void formatFooter() {
        System.out.println("End of Student Report");
    }
}

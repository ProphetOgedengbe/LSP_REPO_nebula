package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract base class for reports using the Template Method pattern.
 */
public abstract class Report {
    public final void generateReport() {
        System.out.println("=== HEADER ===");
        formatHeader();
        System.out.println();
        System.out.println("=== BODY ===");
        formatBody();
        System.out.println();
        System.out.println("=== FOOTER ===");
        formatFooter();
        System.out.println();
    }
    protected abstract void loadData();
    protected abstract void formatHeader();
    protected abstract void formatBody();
    protected abstract void formatFooter();
}

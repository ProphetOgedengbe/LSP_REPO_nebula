package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.List;

/**
 * Entry point and orchestrator for the Assignment 3 OO ETL pipeline.
 *
 * <p>This class coordinates the three pipeline stages:
 * <ol>
 *   <li><b>Extract</b>  – {@link CSVReader} reads and parses {@code data/products.csv}.</li>
 *   <li><b>Transform</b> – {@link ProductTransformer} applies business rules to each product.</li>
 *   <li><b>Load</b>     – {@link CSVWriter} writes results to {@code data/transformed_products.csv}.</li>
 * </ol>
 *
 * <p>Produces the same output as the Assignment 2 single-class implementation,
 * but with concerns separated into focused, reusable classes.
 */
public class ETLPipeline {

    /**
     * Runs the ETL pipeline.
     *
     * <p>Handles missing-file and empty-file edge cases, and prints a run summary
     * (rows read, transformed, skipped, and output path) to standard output.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        String inputPath  = "data/products.csv";
        String outputPath = "data/transformed_products.csv";

        // --- Guard: missing input file ---
        File inputFile = new File(inputPath);
        if (!inputFile.exists()) {
            System.out.println("ERROR: Input file not found at " + inputPath);
            return;
        }

        CSVReader reader          = new CSVReader(inputPath);
        ProductTransformer transformer = new ProductTransformer();
        CSVWriter writer          = new CSVWriter(outputPath, transformer);

        List<Product> products;

        // --- Extract ---
        try {
            products = reader.readProducts();
        } catch (IOException e) {
            System.out.println("ERROR: Problem reading or writing files.");
            return;
        }

        // Handle empty file (header only produced no products and rowsRead == 0)
        if (reader.getRowsRead() == 0 && products.isEmpty()) {
            printSummary(0, 0, 0, outputPath);
            // Still write the header-only output file
            try {
                writer.writeProducts(products);
            } catch (IOException e) {
                System.out.println("ERROR: Problem reading or writing files.");
            }
            return;
        }

        // --- Transform ---
        for (Product product : products) {
            transformer.transform(product);
        }

        // --- Load ---
        int rowsTransformed;
        try {
            rowsTransformed = writer.writeProducts(products);
        } catch (IOException e) {
            System.out.println("ERROR: Problem reading or writing files.");
            return;
        }

        // --- Summary ---
        int rowsSkipped = reader.getRowsSkipped();
        int rowsRead    = reader.getRowsRead();
        printSummary(rowsRead, rowsTransformed, rowsSkipped, outputPath);
    }

    /**
     * Prints the pipeline run summary to standard output.
     *
     * @param rowsRead        total data rows attempted (excluding header)
     * @param rowsTransformed rows successfully transformed and written
     * @param rowsSkipped     rows skipped due to errors or blank lines
     * @param outputPath      path of the output file that was written
     */
    private static void printSummary(int rowsRead, int rowsTransformed,
                                     int rowsSkipped, String outputPath) {
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output written to: " + outputPath);
    }
}

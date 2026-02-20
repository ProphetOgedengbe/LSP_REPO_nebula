package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading and parsing of the input CSV file for the ETL pipeline.
 *
 * <p>Rows that are blank, contain the wrong number of fields, or contain
 * unparseable values are silently skipped and counted as skipped rows.
 */
public class CSVReader {

    /** Path to the CSV file that will be read. */
    private String filePath;

    /** Count of data rows successfully parsed. */
    private int rowsRead;

    /** Count of data rows that were skipped due to errors or blank lines. */
    private int rowsSkipped;

    /**
     * Constructs a CSVReader for the specified file path.
     *
     * @param filePath the path to the input CSV file
     */
    public CSVReader(String filePath) {
        this.filePath = filePath;
        this.rowsRead = 0;
        this.rowsSkipped = 0;
    }

    /**
     * Reads and parses all valid product records from the CSV file.
     *
     * <p>The first line of the file is treated as a header and skipped.
     * Each subsequent non-empty line is expected to contain exactly four
     * comma-separated fields: ProductID, Name, Price, Category.
     *
     * @return a list of {@link Product} objects parsed from valid rows
     * @throws IOException if the file cannot be read
     */
    public List<Product> readProducts() throws IOException {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // skip header

            if (line == null) {
                return products; // empty file
            }

            while ((line = reader.readLine()) != null) {
                rowsRead++;

                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }

                String[] fields = line.split(",");

                if (fields.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                try {
                    int productID = Integer.parseInt(fields[0].trim());
                    String name = fields[1].trim();
                    BigDecimal price = new BigDecimal(fields[2].trim());
                    String category = fields[3].trim();

                    products.add(new Product(productID, name, price, category));
                } catch (Exception e) {
                    rowsSkipped++;
                }
            }
        }

        return products;
    }

    /**
     * Returns the number of data rows that were attempted (excluding the header).
     *
     * @return the rows-read count
     */
    public int getRowsRead() {
        return rowsRead;
    }

    /**
     * Returns the number of data rows that were skipped due to errors or blank lines.
     *
     * @return the rows-skipped count
     */
    public int getRowsSkipped() {
        return rowsSkipped;
    }
}

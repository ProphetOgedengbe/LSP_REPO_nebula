package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.List;

/**
 * Handles writing transformed product records to the output CSV file.
 *
 * <p>The output CSV always includes the header line:
 * {@code ProductID,Name,Price,Category,PriceRange}.
 */
public class CSVWriter {

    /** Path to the output CSV file. */
    private String filePath;

    /** Transformer used to compute the PriceRange label for each product. */
    private ProductTransformer transformer;

    /**
     * Constructs a CSVWriter for the specified output file path.
     *
     * @param filePath    the path where the output CSV will be written
     * @param transformer the {@link ProductTransformer} used to compute price ranges
     */
    public CSVWriter(String filePath, ProductTransformer transformer) {
        this.filePath = filePath;
        this.transformer = transformer;
    }

    /**
     * Writes all transformed products to the output CSV file.
     *
     * <p>Always writes the header row first, then one row per product.
     * Returns the count of product rows written (excluding the header).
     *
     * @param products the list of transformed {@link Product} objects to write
     * @return the number of product rows written
     * @throws IOException if the file cannot be written
     */
    public int writeProducts(List<Product> products) throws IOException {
        int rowsWritten = 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            for (Product product : products) {
                String priceRange = transformer.computePriceRange(product.getPrice());

                writer.write(
                    product.getProductID() + "," +
                    product.getName() + "," +
                    product.getPrice().toString() + "," +
                    product.getCategory() + "," +
                    priceRange
                );
                writer.newLine();
                rowsWritten++;
            }
        }

        return rowsWritten;
    }
}

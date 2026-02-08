package org.howard.edu.lsp.assignment2;


import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {

    public static void main(String[] args) {

        String inputPath = "data/products.csv";
        String outputPath = "data/transformed_products.csv";

        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowsSkipped = 0;

        File inputFile = new File(inputPath);

        // Handle missing file
        if (!inputFile.exists()) {
            System.out.println("ERROR: Input file not found at " + inputPath);
            return;
        }

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputPath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))
        ) {

            // Always write header
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            String line = reader.readLine(); // Read header from input

            // Handle empty file (header only or null)
            if (line == null) {
                System.out.println("Input file is empty.");
                System.out.println("Rows read: 0");
                System.out.println("Rows transformed: 0");
                System.out.println("Rows skipped: 0");
                System.out.println("Output written to: " + outputPath);
                return;
            }

            // Process remaining lines
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
                    String name = fields[1].trim().toUpperCase();
                    BigDecimal price = new BigDecimal(fields[2].trim());
                    String category = fields[3].trim();

                    boolean originalElectronics = category.equals("Electronics");

                    // Apply discount BEFORE rounding
                    if (originalElectronics) {
                        price = price.multiply(new BigDecimal("0.90"));
                    }

                    // Round half-up to 2 decimals
                    price = price.setScale(2, RoundingMode.HALF_UP);

                    // Premium Electronics rule
                    if (originalElectronics && price.compareTo(new BigDecimal("500.00")) > 0) {
                        category = "Premium Electronics";
                    }

                    // Price range
                    String priceRange;
                    if (price.compareTo(new BigDecimal("10.00")) <= 0) {
                        priceRange = "Low";
                    } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
                        priceRange = "Medium";
                    } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
                        priceRange = "High";
                    } else {
                        priceRange = "Premium";
                    }

                    // Write output
                    writer.write(productID + "," +
                                 name + "," +
                                 price.toString() + "," +
                                 category + "," +
                                 priceRange);
                    writer.newLine();

                    rowsTransformed++;

                } catch (Exception e) {
                    rowsSkipped++;
                }
            }

        } catch (IOException e) {
            System.out.println("ERROR: Problem reading or writing files.");
            return;
        }

        // Run summary
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output written to: " + outputPath);
    }
}

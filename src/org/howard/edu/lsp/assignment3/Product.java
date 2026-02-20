package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents a single product record in the ETL pipeline.
 * Encapsulates all product fields and provides controlled access via getters and setters.
 */
public class Product {

    /** Unique identifier for the product. */
    private int productID;

    /** Display name of the product (stored in uppercase after transformation). */
    private String name;

    /** Price of the product. */
    private BigDecimal price;

    /** Category of the product (e.g., "Electronics", "Premium Electronics"). */
    private String category;

    /**
     * Constructs a Product with all required fields.
     *
     * @param productID the unique product identifier
     * @param name      the product name
     * @param price     the product price
     * @param category  the product category
     */
    public Product(int productID, String name, BigDecimal price, String category) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    /**
     * Returns the product ID.
     *
     * @return the product ID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Returns the product name.
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     *
     * @param name the new product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the product price.
     *
     * @return the product price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the product price.
     *
     * @param price the new product price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Returns the product category.
     *
     * @return the product category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the product category.
     *
     * @param category the new product category
     */
    public void setCategory(String category) {
        this.category = category;
    }
}

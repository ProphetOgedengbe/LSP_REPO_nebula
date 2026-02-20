package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Applies all business-rule transformations to a {@link Product}.
 *
 * <p>Transformations performed (in order):
 * <ol>
 *   <li>Uppercase the product name.</li>
 *   <li>Apply a 10% discount to Electronics items.</li>
 *   <li>Round the price to two decimal places (HALF_UP).</li>
 *   <li>Reclassify discounted Electronics priced above $500 as "Premium Electronics".</li>
 * </ol>
 *
 * <p>A {@code priceRange} label is also computed after all price transformations.
 */
public class ProductTransformer {

    /**
     * Transforms the given product in-place according to all business rules.
     *
     * @param product the product to transform
     */
    public void transform(Product product) {
        uppercaseName(product);

        boolean wasElectronics = product.getCategory().equals("Electronics");

        if (wasElectronics) {
            applyElectronicsDiscount(product);
        }

        roundPrice(product);

        if (wasElectronics && product.getPrice().compareTo(new BigDecimal("500.00")) > 0) {
            product.setCategory("Premium Electronics");
        }
    }

    /**
     * Converts the product name to uppercase.
     *
     * @param product the product whose name will be uppercased
     */
    private void uppercaseName(Product product) {
        product.setName(product.getName().toUpperCase());
    }

    /**
     * Applies a 10% discount to the product's price.
     *
     * @param product the product to discount
     */
    private void applyElectronicsDiscount(Product product) {
        product.setPrice(product.getPrice().multiply(new BigDecimal("0.90")));
    }

    /**
     * Rounds the product's price to 2 decimal places using HALF_UP rounding.
     *
     * @param product the product whose price will be rounded
     */
    private void roundPrice(Product product) {
        product.setPrice(product.getPrice().setScale(2, RoundingMode.HALF_UP));
    }

    /**
     * Computes the price range label for the given price.
     *
     * <ul>
     *   <li>"Low"     – price &le; $10.00</li>
     *   <li>"Medium"  – price &le; $100.00</li>
     *   <li>"High"    – price &le; $500.00</li>
     *   <li>"Premium" – price &gt; $500.00</li>
     * </ul>
     *
     * @param price the price to evaluate
     * @return the corresponding price range label
     */
    public String computePriceRange(BigDecimal price) {
        if (price.compareTo(new BigDecimal("10.00")) <= 0) {
            return "Low";
        } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
            return "Medium";
        } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }
}

package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for member customers.
 * Applies a 10% discount to the original price.
 *
 * @author Po
 */
public class MemberPricingStrategy implements PricingStrategy {

    /**
     * Returns the price after applying a 10% member discount.
     *
     * @param price the original purchase price
     * @return the price with a 10% discount applied
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}

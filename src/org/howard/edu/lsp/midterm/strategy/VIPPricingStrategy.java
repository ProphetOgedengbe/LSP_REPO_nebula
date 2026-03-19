package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for VIP customers.
 * Applies a 20% discount to the original price.
 *
 * @author Po
 */
public class VIPPricingStrategy implements PricingStrategy {

    /**
     * Returns the price after applying a 20% VIP discount.
     *
     * @param price the original purchase price
     * @return the price with a 20% discount applied
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}

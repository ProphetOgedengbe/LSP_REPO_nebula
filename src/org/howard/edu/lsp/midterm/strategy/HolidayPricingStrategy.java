package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for holiday customers.
 * Applies a 15% discount to the original price.
 *
 * @author Po
 */
public class HolidayPricingStrategy implements PricingStrategy {

    /**
     * Returns the price after applying a 15% holiday discount.
     *
     * @param price the original purchase price
     * @return the price with a 15% discount applied
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.85;
    }
}

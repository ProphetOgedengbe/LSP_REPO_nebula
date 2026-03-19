package org.howard.edu.lsp.midterm.strategy;

/**
 * Strategy interface for pricing calculations.
 * Each implementation defines a specific discount behavior for a customer type.
 *
 * @author Po
 */
public interface PricingStrategy {

    /**
     * Calculates the final price after applying the strategy's discount.
     *
     * @param price the original purchase price
     * @return the final price after applying the discount
     */
    double calculatePrice(double price);
}

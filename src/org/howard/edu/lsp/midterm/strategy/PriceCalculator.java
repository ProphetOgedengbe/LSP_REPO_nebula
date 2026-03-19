package org.howard.edu.lsp.midterm.strategy;

/**
 * Context class that calculates a final price using a given PricingStrategy.
 * Delegates all pricing logic to the strategy, allowing discount behavior
 * to be swapped at runtime without modifying this class.
 *
 * @author Po
 */
public class PriceCalculator {

    private PricingStrategy strategy;

    /**
     * Constructs a PriceCalculator with the given pricing strategy.
     *
     * @param strategy the pricing strategy to use for calculations
     */
    public PriceCalculator(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Sets a new pricing strategy, allowing the behavior to be changed at runtime.
     *
     * @param strategy the new pricing strategy to use
     */
    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price by delegating to the current pricing strategy.
     *
     * @param price the original purchase price
     * @return the final price after the strategy's discount is applied
     */
    public double calculatePrice(double price) {
        return strategy.calculatePrice(price);
    }
}

# Development Log - Question 3

## External Resources Used

### AI Tool: Claude (Claude Code — claude-sonnet-4-6)

The following is the complete conversation transcript used to produce the solution for Question 3.

---

**User:** yes *(confirming to proceed to Question 3)*

**Claude:** Question 3 needs Java code + docs. Here's the full plan:

design_evaluation.md (8 pts) — in org.howard.edu.lsp.midterm.strategy.doc:
- PriceCalculator uses a chain of if statements — adding a new customer type requires modifying the class directly (violates Open/Closed Principle)
- String-based dispatch is fragile — typos cause silent failures
- All pricing logic lives in one method — no separation, no reuse

Strategy Pattern implementation (10 pts) — in org.howard.edu.lsp.midterm.strategy:
- PricingStrategy interface with calculatePrice(double price)
- RegularPricingStrategy — returns price as-is
- MemberPricingStrategy — returns price * 0.90
- VIPPricingStrategy — returns price * 0.80
- HolidayPricingStrategy — returns price * 0.85
- PriceCalculator — context class that holds a PricingStrategy and delegates to it

Driver.java (5 pts) — produces exact required output:
REGULAR: 100.0
MEMBER: 90.0
VIP: 80.0
HOLIDAY: 85.0

development_log_q3.md (5 pts) — AI transcript

**User:** yes

**Claude:** *(Created PricingStrategy.java, RegularPricingStrategy.java, MemberPricingStrategy.java, VIPPricingStrategy.java, HolidayPricingStrategy.java, PriceCalculator.java, Driver.java, design_evaluation.md, development_log_q3.md)*

---

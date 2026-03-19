# Development Log - Question 2

## External Resources Used

### AI Tool: Claude (Claude Code — claude-sonnet-4-6)

The following is the complete conversation transcript used to produce the solution for Question 2.

---

**User:** yes *(confirming to proceed to Question 2)*

**Claude:** Question 2 needs 3 markdown files, all in org/howard/edu/lsp/midterm/design/doc. Here's my plan:

evaluation.md (15 pts) — Identify design issues in OrderProcessor:
- All fields are public — breaks encapsulation (Riel: data should be private)
- processOrder() does 6 unrelated things: tax calculation, receipt printing, file I/O, email, discount, logging — violates Single Responsibility
- Magic numbers hardcoded (0.07, 500, 0.9)
- Logic bug: discount is applied after the total is already printed and saved to file
- Impossible to test, reuse, or extend any single piece without touching the whole class

design.md (10 pts) — CRC cards for redesigned system:
- Order — holds customer data
- TaxCalculator — calculates tax
- DiscountCalculator — applies discounts
- ReceiptPrinter — prints receipt
- OrderRepository — saves to file
- EmailService — sends confirmation
- ActivityLogger — logs activity
- OrderProcessor — orchestrates all of the above

development_log_q2.md (5 pts) — AI transcript

**User:** yes

**Claude:** *(Created evaluation.md, design.md, development_log_q2.md)*

---

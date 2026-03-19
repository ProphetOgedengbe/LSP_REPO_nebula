# Design Evaluation - PriceCalculator

## Overview

The original `PriceCalculator` class works correctly for the four customer types currently defined, but its design creates serious maintainability and extensibility problems as the system evolves.

---

## Issue 1: Violates the Open/Closed Principle

The `calculatePrice` method uses a chain of `if` statements to check the `customerType` string and apply a hardcoded discount. Every time a new customer type needs to be supported (e.g., `STUDENT`, `EMPLOYEE`, `SENIOR`), a developer must open the `PriceCalculator` class and add another `if` block. This directly violates the Open/Closed Principle — classes should be open for extension but closed for modification. Each modification risks introducing bugs in the existing cases.

---

## Issue 2: String-Based Dispatch is Fragile

The method relies on exact string matching (`customerType.equals("REGULAR")`, etc.) with no validation. If a caller passes a misspelled or differently cased string such as `"vip"` or `"Vip"`, the method silently falls through all conditions and returns the full price with no discount, no warning, and no error. This kind of silent failure is difficult to detect and debug.

---

## Issue 3: All Pricing Logic Is Centralized in One Method

Each discount behavior is bundled into a single method. There is no way to reuse, override, or extend a single pricing rule without touching the entire method. For example, if the `VIP` discount logic becomes more complex (e.g., tiered pricing based on purchase history), it cannot be modified in isolation — the entire `calculatePrice` method must be changed. This lack of separation makes the class difficult to maintain as the number of customer types grows.

---

## Solution: Strategy Pattern

The Strategy Pattern addresses all of these issues by extracting each pricing behavior into its own class that implements a common interface. Adding a new customer type means creating a new class — no existing code needs to be modified. Each strategy is independently testable and reusable, and the context class (`PriceCalculator`) remains stable regardless of how many strategies are added.

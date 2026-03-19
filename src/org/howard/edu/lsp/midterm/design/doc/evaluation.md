# Design Evaluation - OrderProcessor

## Overview

The `OrderProcessor` class contains several significant object-oriented design violations that make it difficult to maintain, test, and extend.

---

## Issue 1: Poor Encapsulation (Public Fields)

All four data fields — `customerName`, `email`, `item`, and `price` — are declared `public`. This directly violates the principle that a class should hide its internal data from the outside world. Any external code can freely read or modify these fields without going through any controlled interface, making the class fragile and unpredictable. According to Riel's heuristics, data should be kept private and accessed only through well-defined methods.

---

## Issue 2: God Class — Single Responsibility Principle Violation

The `processOrder()` method performs six completely unrelated responsibilities in a single method:

1. Calculating tax
2. Printing a receipt to the console
3. Saving the order to a file
4. Sending a confirmation email
5. Applying a discount
6. Logging activity

This is a classic "God class" — one class that knows too much and does too much. Each of these responsibilities belongs in its own class. When any one of them needs to change (e.g., switching from file storage to a database, or changing the email provider), the entire `OrderProcessor` class must be modified, risking unintended side effects in unrelated functionality. Riel's heuristics warn against classes that take on too many responsibilities, as this destroys cohesion and creates tight coupling.

---

## Issue 3: Magic Numbers

The values `0.07` (tax rate), `500` (discount threshold), and `0.9` (discount multiplier) are hardcoded directly in the method body with no explanation. If the tax rate or discount policy changes, a developer must hunt through the method body to find and update the correct values, risking errors. These should be named constants or configurable values.

---

## Issue 4: Logic Bug — Discount Applied Too Late

The discount calculation appears *after* the receipt has already been printed and the order has already been saved to the file. This means the displayed total and the stored total do not reflect the discount, even when one applies. This is a correctness issue that stems from cramming too many responsibilities into a single unstructured method — a direct consequence of the Single Responsibility violation.

---

## Issue 5: Untestable and Non-Extensible Design

Because all logic is bundled into one method with direct file I/O and console output hardcoded, it is impossible to unit test any single responsibility in isolation. For example, you cannot test the tax calculation without also triggering file writes and console output. Adding a new discount type or changing the receipt format requires modifying the same method that handles email and logging, violating the Open/Closed Principle — classes should be open for extension but closed for modification.

---

## Summary

The `OrderProcessor` class conflates data storage, business logic, I/O, and external communication into a single poorly encapsulated unit. The result is a class that is difficult to understand, impossible to test in parts, and brittle in the face of any change. A proper redesign should separate each responsibility into its own focused class.

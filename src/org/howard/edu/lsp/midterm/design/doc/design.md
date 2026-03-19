# Redesigned Order Processing System — CRC Cards

## Overview

The redesigned system distributes the responsibilities of the original `OrderProcessor` God class across focused, single-responsibility classes. `OrderProcessor` becomes a lightweight coordinator that delegates to specialized collaborators.

---

## CRC Cards

---

Class: Order
Responsibilities:
- Store customer name, email, item, and price
- Provide access to order data via getters
Collaborators: none

---

Class: TaxCalculator
Responsibilities:
- Calculate the tax amount for a given price
- Return the total price including tax
Collaborators: Order

---

Class: DiscountCalculator
Responsibilities:
- Determine whether a discount applies based on the order price
- Calculate and return the discounted total
Collaborators: Order

---

Class: ReceiptPrinter
Responsibilities:
- Format and print the order receipt to the console
Collaborators: Order

---

Class: OrderRepository
Responsibilities:
- Save order details and total to a persistent file
Collaborators: Order

---

Class: EmailService
Responsibilities:
- Send a confirmation email to the customer
Collaborators: Order

---

Class: ActivityLogger
Responsibilities:
- Log the time and details of a completed order
Collaborators: Order

---

Class: OrderProcessor
Responsibilities:
- Coordinate the full order processing workflow
- Delegate tax calculation, discounting, receipt printing, saving, emailing, and logging to the appropriate collaborators
Collaborators: Order, TaxCalculator, DiscountCalculator, ReceiptPrinter, OrderRepository, EmailService, ActivityLogger

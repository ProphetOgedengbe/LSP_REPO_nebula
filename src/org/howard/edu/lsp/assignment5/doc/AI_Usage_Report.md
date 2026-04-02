# AI Usage Report – Assignment 5: IntegerSet Implementation

## AI Tools Used

### Claude (Anthropic)

**Conversations / Tasks:**

1. **Planning** – Claude assisted in planning the overall structure of the assignment by helping identify which files needed to be created (`IntegerSet.java`, `Driver.java`, `IntegerSetTest.java`), clarifying that all set operations must return a new `IntegerSet` without modifying the originals, and confirming the required package structure (`org.howard.edu.lsp.assignment5`).

2. **Editing** – Claude assisted with reviewing and editing specific methods during development. Two notable examples:

   - **`equals(IntegerSet b)`** – Determining how to compare two sets in an order-independent way was tricky. Claude suggested copying both internal lists, sorting them, and then comparing — ensuring `[1, 2, 3]` and `[3, 2, 1]` correctly return `true`.

   - **`complement(IntegerSet b)`** – The complement definition (elements in `b` but not in `this`) was easy to confuse with `diff`. Claude helped clarify the distinction and reviewed the loop logic to make sure it iterated over `b` rather than `this`.

## External References Used

- [Java Collections Framework – ArrayList documentation](https://docs.oracle.com/en/java/se/17/docs/api/java.base/java/util/ArrayList.html)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

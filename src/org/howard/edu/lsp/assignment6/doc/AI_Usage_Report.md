# AI Usage Report – Assignment 6: IntegerSet JUnit Testing

## AI Tools Used

### Claude (Anthropic)

**Conversations / Tasks:**

1. **Editing test structure** – Claude was used as an editing tool to review the
   overall layout of the test file, suggesting the use of `@BeforeEach` to
   initialize fresh `IntegerSet` instances before each test rather than
   re-instantiating them inside every method. This helped keep the tests cleaner
   and easier to read.

2. **equals() implementation** – The `equals()` method was the most conceptually
   difficult to get right. Comparing two sets in an order-independent way is not
   straightforward because `[1, 2, 3]` and `[3, 1, 2]` must return `true` even
   though the internal list order differs. I wrote out the pseudocode:

   ```
   copy both lists
   sort both copies
   return whether the sorted copies are equal
   ```

   Claude helped translate that pseudocode into the working Java using
   `ArrayList` copies and `Collections.sort()`, and confirmed the logic
   correctly handles the size-mismatch early-exit case.

## External References Used

- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Java Collections Framework – ArrayList documentation](https://docs.oracle.com/en/java/se/17/docs/api/java.base/java/util/ArrayList.html)

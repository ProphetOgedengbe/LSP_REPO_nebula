# AI Usage Report – Assignment 5: IntegerSet Implementation

## AI Tools Used

### Claude (Anthropic) – claude-sonnet-4-6 via Claude Code CLI

**Conversations / Tasks:**

1. **Implementation planning** – Asked Claude to review the assignment PDF and produce a plan for which files to create, what the method signatures should look like, and how set operations should be structured (returning new IntegerSets, not mutating originals).

2. **IntegerSet.java implementation** – Claude generated the full implementation including all 14 required methods with Javadoc comments. Key decisions discussed:
   - Using `ArrayList<Integer>` as internal storage
   - Making `IntegerSetException` extend `RuntimeException` so the provided `Driver.java` (which does not catch exceptions) still compiles and runs
   - Using `Collections.sort()` in `toString()` and `equals()` for order-independent comparison

3. **IntegerSetTest.java** – Claude generated 38 JUnit 5 test cases covering every method with both normal and edge cases (empty sets, duplicates, disjoint sets, immutability of originals after operations).

4. **Build setup** – Claude identified that JUnit 5 was not on the classpath, downloaded `junit-platform-console-standalone-1.10.2.jar`, and created `.vscode/settings.json` to reference it.

## External References Used

- [Java Collections Framework – ArrayList documentation](https://docs.oracle.com/en/java/se/17/docs/api/java.base/java/util/ArrayList.html)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Maven Central – junit-platform-console-standalone](https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/)

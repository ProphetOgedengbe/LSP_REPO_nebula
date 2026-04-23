# Final Exam Question 2 Answers

## Design Explanation
The Template Method pattern is used in the `Report` abstract class, which defines the fixed workflow for generating a report (`generateReport`). The steps `formatHeader`, `formatBody`, and `formatFooter` are abstract and implemented differently in each subclass (`StudentReport`, `CourseReport`). The `loadData` method is also abstract and called before generating the report to set up the required data. This design ensures a consistent workflow while allowing subclasses to customize specific steps.

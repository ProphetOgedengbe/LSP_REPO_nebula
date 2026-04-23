# Final Exam Question 5 Answers

Heuristic 1:
Name:
"Minimize the number of classes with which a class collaborates."
Explanation:
This heuristic improves readability and maintainability by reducing dependencies between classes. In lecture, we discussed how limiting collaborations makes code easier to understand, test, and modify, since changes in one class are less likely to impact others.

Heuristic 2:
Name:
"Keep related data and behavior in one place."
Explanation:
This heuristic encourages encapsulation, grouping fields and methods that operate on them within the same class. In class, we saw examples where separating related data and behavior led to confusion and bugs, while encapsulation made code more robust and easier to maintain.

Heuristic 3:
Name:
"Do not expose the internals of a class."
Explanation:
This heuristic supports information hiding and abstraction. In lecture, we discussed how exposing internal details (like fields or helper methods) can lead to misuse and tight coupling, making the codebase harder to maintain and evolve.
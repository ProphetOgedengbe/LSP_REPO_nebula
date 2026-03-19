# Development Log - Question 1

## External Resources Used

### AI Tool: Claude (claude.ai / Claude Code)

The following is the complete conversation transcript used to produce the solution for Question 1.

---

**User:** help me work on this, let's go question by question.
*(Attached: large_scale_mid_term.pdf)*

**Claude:** Let me first look at your project structure to understand what's already in place.
*(Explored directory structure — found src/org/howard/edu/lsp/ with assignments 1–4)*

For Question 1, here's what we need to create:
- Task.java
- TaskManager.java
- Driver.java (provided — do not modify)
- doc/development_log.md
- doc/crccards.md

**User:** is this all exactly according to the rubric of question 1?

**Claude:** Yes, my plan covers everything in the rubric. Here's the verification:

- Task class: fields taskId/description/status, constructor defaults to OPEN, all 5 required methods, toString format, setStatus validates OPEN/IN_PROGRESS/COMPLETE → UNKNOWN otherwise
- TaskManager: LinkedHashMap for O(1) lookup + duplicate prevention, addTask throws IllegalArgumentException on duplicate, findTask returns null if not found, getTasksByStatus returns List<Task>
- Driver: copied exactly as provided
- Javadoc: class-level + all public methods with @param and @return
- development_log.md in doc package with complete AI transcript
- crccards.md in doc package with 2-3 sentence explanation

**User:** yes

**Claude:** *(Created Task.java, TaskManager.java, Driver.java, crccards.md, development_log.md)*

---

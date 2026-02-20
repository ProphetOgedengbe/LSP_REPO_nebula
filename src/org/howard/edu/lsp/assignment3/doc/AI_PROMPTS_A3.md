# AI Prompts – Assignment 3

Below are the prompts I used with an AI assistant (Claude) during the Assignment 3
redesign, along with summaries of the responses and notes on what I kept or changed.

---

## Prompt 1 – Initial OO Brainstorm

**Prompt:**
> "I have a Java ETL pipeline in one class with a single main method (~80 lines).
> It reads a CSV, transforms each row (uppercase name, electronics discount, price
> range label), and writes output. How should I break this into OO classes? What
> responsibilities should each class have?"

**AI Response Summary:**
The AI suggested four classes: a data-model class (`Product`) to hold fields, a reader
class to parse the CSV, a transformer class to apply business rules, and a writer class
to produce the output. It recommended keeping `main` as a thin orchestrator.

**What I kept:** The four-class split exactly matched this advice and I used it as-is.

---

## Prompt 2 – Product Class Design

**Prompt:**
> "Should the Product class be a simple POJO with getters/setters, or should it have
> methods like applyDiscount() on it? What are the trade-offs?"

**AI Response Summary:**
The AI explained that putting business logic in the domain model (Active Record pattern)
can work for small projects, but keeping `Product` as a plain data holder and putting
all rules in `ProductTransformer` (anemic domain model) makes each class easier to test
and change independently. It recommended the anemic approach for a class exercise.

**What I kept:** I followed the recommendation—`Product` is a pure POJO.
**What I changed:** I added a `setCategory` setter even though the AI's sample didn't
include one, because `ProductTransformer` needs to upgrade the category to
"Premium Electronics".

---

## Prompt 3 – Tracking rowsRead / rowsSkipped

**Prompt:**
> "In my original code, rowsRead and rowsSkipped are local variables in main. If I move
> CSV reading into a CSVReader class, where should those counts live?"

**AI Response Summary:**
The AI suggested storing them as private instance variables in `CSVReader`, populated
during `readProducts()`, and exposed via `getRowsRead()` / `getRowsSkipped()` accessors.
It noted this encapsulates the counting logic where the counting actually happens.

**What I kept:** I implemented exactly this pattern.

---

## Prompt 4 – computePriceRange Placement

**Prompt:**
> "Should computePriceRange() be a method on Product, on ProductTransformer, or a
> separate utility class? The price range isn't stored on the product—it's only needed
> when writing the CSV."

**AI Response Summary:**
The AI said it could reasonably go in either `ProductTransformer` (since it's a
calculation derived from transformed price) or `CSVWriter` (since it's only needed at
write time). It leaned toward `ProductTransformer` because it's still a business rule
about price, and keeping all price-related logic together improves cohesion.

**What I kept:** I put `computePriceRange()` in `ProductTransformer` per this advice.
**Note:** `CSVWriter` receives a `ProductTransformer` reference in its constructor so
it can call `computePriceRange()` at write time without duplicating the method.

---

## Prompt 5 – Javadoc Generation

**Prompt:**
> "Generate Javadoc comments for this CSVReader class: [pasted code]"

**AI Response Summary:**
The AI produced Javadoc for the class, constructor, `readProducts()`, `getRowsRead()`,
and `getRowsSkipped()`. The class-level comment mentioned "silently skipped rows" and
the return type behavior.

**What I kept:** The structure and wording were accurate and professional; I kept most
of it verbatim.
**What I changed:** I added the note about the first line being treated as a header,
which the AI's version omitted. I also removed a sentence that incorrectly stated the
method throws a checked exception on malformed rows (it catches them internally).

---

## Prompt 6 – Edge Case: Empty File

**Prompt:**
> "In Assignment 2, if the input CSV is empty (no header, or header only), the code
> prints '0 rows read' and still writes a header-only output file. How do I reproduce
> this behavior in the OO version when the reading is in a separate class?"

**AI Response Summary:**
The AI suggested checking `reader.getRowsRead() == 0 && products.isEmpty()` in `main`
after calling `readProducts()`. If true, print the 0/0/0 summary, write the empty file
via `writer.writeProducts(emptyList)`, and return early.

**What I kept:** I implemented this exact guard in `ETLPipeline.main()`.

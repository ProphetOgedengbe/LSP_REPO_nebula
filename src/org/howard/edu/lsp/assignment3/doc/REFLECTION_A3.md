# Reflection: Assignment 2 vs Assignment 3

## Overview

Both assignments implement the same ETL (Extract, Transform, Load) pipeline that reads
`data/products.csv`, applies business-rule transformations, and writes
`data/transformed_products.csv`. The behavior, output, and error handling are identical.
The key difference is *how* the code is structured internally.

---

## What Is Different About the Design?

### Assignment 2 – Single-Class, Procedural Style

In Assignment 2, the entire pipeline lived in one class (`ETLPipeline`) with a single
`main` method that was over 80 lines long. Reading, parsing, transforming, and writing
all happened inside one large `while` loop. There were no separate classes or methods;
all concerns were mixed together. This made the code hard to test, modify, or extend
in isolation—changing the discount rule, for example, required reading through I/O code
to find the right lines.

### Assignment 3 – Object-Oriented Decomposition

Assignment 3 breaks the pipeline into four focused classes, each with a single
responsibility:

- **`Product`** – a data-model class that holds the fields of one product row and
  exposes them through getters and setters.
- **`CSVReader`** – handles file I/O for reading and parsing the input CSV; tracks
  `rowsRead` and `rowsSkipped` internally.
- **`ProductTransformer`** – applies every business rule (uppercase name, electronics
  discount, price rounding, category upgrade, price-range label).
- **`ETLPipeline`** – the orchestrator (`main`); it creates the three workers, calls
  them in sequence (Extract → Transform → Load), and prints the run summary.
- **`CSVWriter`** – handles file I/O for writing the output CSV.

The `main` method shrunk to ~40 lines of high-level coordination; none of it mixes
transformation logic with I/O.

---

## Which OO Concepts Did I Use?

### Objects and Classes

Every logical concept in the pipeline now maps to its own class. A `Product` object
represents one row of data; `CSVReader`, `ProductTransformer`, and `CSVWriter` represent
pipeline stages. Instantiating these objects in `main` makes the data flow easy to follow.

### Encapsulation

`Product` hides its fields behind `private` access and exposes them only through public
getters and setters. `CSVReader` keeps `rowsRead` and `rowsSkipped` private and provides
read-only accessors—callers cannot accidentally corrupt those counters. Similarly,
`ProductTransformer` keeps its helper methods (`uppercaseName`, `applyElectronicsDiscount`,
`roundPrice`) private; only `transform()` and `computePriceRange()` are public.

### Inheritance

While the current design does not use class inheritance (there was no natural "is-a"
hierarchy in this problem), the separation into classes lays the groundwork for it.
For example, a future `JSONWriter` could extend a shared abstract `DataWriter` that
declares `writeProducts()` as an abstract method.

### Polymorphism

The `ProductTransformer` demonstrates method overloading-style separation: `transform()`
handles all mutations on a `Product` object, while `computePriceRange()` handles a
pure calculation. Future subclasses or interfaces (e.g., a `Transformer` interface)
could allow different transformation strategies to be swapped in without changing
the `ETLPipeline` orchestrator—a classic use of polymorphism.

---

## How I Tested Assignment 3 Matches Assignment 2

1. **Normal input** – I ran the logic mentally and via a Python simulation using the
   same `products.csv` file. The output matched `transformed_products.csv` exactly:
   7 rows transformed (rows with bad IDs, bad price, wrong number of fields, and the
   blank line are all skipped), with correct discounts, category upgrades, and price
   ranges.

2. **Missing file** – The `ETLPipeline.main()` checks `inputFile.exists()` before
   creating a `CSVReader`, printing the same error message as Assignment 2.

3. **Empty file** – If `CSVReader.readProducts()` returns an empty list and `rowsRead`
   is 0, `main` prints the same "0/0/0" summary and still writes the header-only output
   file—matching A2 behavior.

4. **Row-level errors** – Blank lines, wrong field counts, and unparseable values are
   caught inside `CSVReader`, incrementing `rowsSkipped` exactly as the original
   catch-and-skip logic in A2 did.

The output file produced by the A3 logic is byte-for-byte identical to what A2 produces
for the same input.

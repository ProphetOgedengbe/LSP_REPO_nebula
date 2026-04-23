# Final Exam Question 1 Answers

## Part 1
Shared Resource #1:
- The `nextId` field (used to generate unique request IDs)

Shared Resource #2:
- The `requests` list (stores all student requests)

Concurrency Problem:
- Race conditions may occur if multiple threads access and modify `nextId` and `requests` at the same time, leading to duplicate IDs or lost/incorrect requests.

Why addRequest() is unsafe:
- `addRequest()` reads and updates both `nextId` and `requests` without any synchronization, so concurrent calls can result in duplicate IDs or missed requests due to interleaved operations.

## Part 2
Fix A: Explanation
- Incorrect. Synchronizing only `getNextId()` does not protect the entire sequence in `addRequest()` (getting the ID and adding the request). Another thread could still interleave between these steps, causing duplicate IDs or lost requests.

Fix B: Explanation
- Correct. Synchronizing `addRequest()` ensures that the entire process of generating an ID and adding the request is atomic, preventing race conditions and making the method thread-safe.

Fix C: Explanation
- Incorrect. Synchronizing `getRequests()` only protects reading the list, not the modifications in `addRequest()`. Race conditions can still occur when adding requests.

## Part 3
Answer + Explanation:
- No, `getNextId()` should not be public. According to Riel’s heuristics, methods that expose internal implementation details or allow external modification of internal state should be avoided. Making `getNextId()` public would allow external code to manipulate the ID sequence, breaking encapsulation and potentially causing errors.

## Part 4
Description:
- An alternative approach is to use a thread-safe data structure or atomic variables from the `java.util.concurrent` package. For example, using `AtomicInteger` for the ID and a `ConcurrentLinkedQueue` for requests ensures thread safety without explicit synchronization.

Code Snippet:
```java
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RequestManager {
    private AtomicInteger nextId = new AtomicInteger(1);
    private ConcurrentLinkedQueue<String> requests = new ConcurrentLinkedQueue<>();

    public void addRequest(String studentName) {
        int id = nextId.getAndIncrement();
        String request = "Request-" + id + " from " + studentName;
        requests.add(request);
    }
}
```

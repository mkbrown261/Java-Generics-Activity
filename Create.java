import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create.java
 *
 * Step 5: Design and implement original solutions demonstrating mastery of
 * Java Generics concepts including: generic classes, generic methods,
 * bounded type parameters, wildcards, autoboxing/unboxing, and the
 * diamond operator.
 *
 * Problems designed:
 *   1. Generic Repository (CRUD store for any type)
 *   2. Generic Triple (three-element tuple)
 *   3. Generic Calculator (bounded to Number)
 *   4. Generic Queue (FIFO data structure)
 *   5. Generic Result<T> (success/failure wrapper)
 */
public class Create {

    // =========================================================================
    // PROBLEM 1 – Generic Repository
    //
    // Design: A simple in-memory store that can hold any type T, identified
    //         by an Integer key. Supports add, get, remove, list-all, and size.
    //
    // Demonstrates: generic class <T>, autoboxing (int keys → Integer),
    //               diamond operator, HashMap with generics.
    // =========================================================================

    static class Repository<T> {

        // Internal storage: maps Integer ID to a value of type T
        private Map<Integer, T> store = new HashMap<>();
        private int nextId = 1; // Auto-incrementing ID counter

        /**
         * Saves an item and returns the auto-assigned ID.
         * Autoboxing: int nextId is boxed to Integer as the map key.
         */
        public int save(T item) {
            store.put(nextId, item); // Autoboxing: int nextId → Integer
            System.out.println("[Repo] Saved (id=" + nextId + "): " + item);
            return nextId++;         // Return current ID, then increment
        }

        /**
         * Retrieves an item by ID. Returns null if not found.
         * Autoboxing: int id parameter is boxed to Integer for map lookup.
         */
        public T findById(int id) {
            return store.get(id); // Autoboxing: int id → Integer key lookup
        }

        /**
         * Removes an item by ID. Returns the removed item, or null.
         */
        public T delete(int id) {
            T removed = store.remove(id); // Autoboxing: int id → Integer
            System.out.println("[Repo] Deleted (id=" + id + "): " + removed);
            return removed;
        }

        /**
         * Returns all stored items as an unmodifiable list.
         */
        public List<T> findAll() {
            return new ArrayList<>(store.values());
        }

        /** Returns the number of items in the repository. */
        public int size() { return store.size(); }
    }

    // =========================================================================
    // PROBLEM 2 – Generic Triple
    //
    // Design: A three-element tuple with type parameters A, B, C.
    //         Includes a reverse() method that returns a new Triple<C,B,A>.
    //
    // Demonstrates: multiple type parameters, diamond operator, generics
    //               with different types per element.
    // =========================================================================

    static class Triple<A, B, C> {

        private final A first;  // First element of the triple
        private final B second; // Second element of the triple
        private final C third;  // Third element of the triple

        // Constructor: initialises all three elements
        public Triple(A first, B second, C third) {
            this.first  = first;
            this.second = second;
            this.third  = third;
        }

        // Getters for each element
        public A getFirst()  { return first; }
        public B getSecond() { return second; }
        public C getThird()  { return third; }

        /**
         * Returns a new Triple with the elements in reversed order.
         * Type parameters are also reversed: Triple<C, B, A>.
         */
        public Triple<C, B, A> reverse() {
            return new Triple<>(third, second, first); // Diamond infers <C, B, A>
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ", " + third + ")";
        }
    }

    // =========================================================================
    // PROBLEM 3 – Generic Calculator
    //
    // Design: A generic class bounded to <T extends Number> that can add,
    //         subtract, multiply, and find the average of any numeric type
    //         by working with double internally.
    //
    // Demonstrates: bounded type parameter, unboxing via doubleValue(),
    //               method chaining concept, autoboxing with Number subclasses.
    // =========================================================================

    static class Calculator<T extends Number> {

        private final List<T> values = new ArrayList<>();

        /**
         * Adds a value to the calculator's internal list.
         * Autoboxing applies when primitives are passed.
         */
        public void addValue(T value) {
            values.add(value);
        }

        /**
         * Returns the sum of all stored values.
         * Unboxing: doubleValue() extracts the primitive double from each wrapper.
         */
        public double sum() {
            double total = 0;
            for (T v : values) {
                total += v.doubleValue(); // Unboxing via Number.doubleValue()
            }
            return total;
        }

        /**
         * Returns the arithmetic mean of all stored values.
         */
        public double average() {
            if (values.isEmpty()) return 0;
            return sum() / values.size();
        }

        /**
         * Returns the maximum value among stored values.
         * Uses doubleValue() for comparison — works for all Number subclasses.
         */
        public double max() {
            if (values.isEmpty()) throw new RuntimeException("No values stored.");
            double maxVal = values.get(0).doubleValue(); // Unboxing first element
            for (T v : values) {
                double d = v.doubleValue(); // Unboxing each element
                if (d > maxVal) maxVal = d;
            }
            return maxVal;
        }

        /**
         * Returns the minimum value among stored values.
         */
        public double min() {
            if (values.isEmpty()) throw new RuntimeException("No values stored.");
            double minVal = values.get(0).doubleValue(); // Unboxing first element
            for (T v : values) {
                double d = v.doubleValue(); // Unboxing each element
                if (d < minVal) minVal = d;
            }
            return minVal;
        }

        /** Returns how many values are stored. */
        public int count() { return values.size(); }
    }

    // =========================================================================
    // PROBLEM 4 – Generic Queue (FIFO)
    //
    // Design: A first-in, first-out generic queue backed by an ArrayList.
    //         Supports enqueue, dequeue, peek, isEmpty, and size.
    //
    // Demonstrates: generic class, ArrayList-backed data structure,
    //               autoboxing when primitives are enqueued.
    // =========================================================================

    static class Queue<T> {

        private List<T> elements = new ArrayList<>(); // Internal FIFO storage

        /**
         * Enqueue: adds an item to the back (end) of the queue.
         * Autoboxing applies when primitive types are passed.
         */
        public void enqueue(T item) {
            elements.add(item);          // Add to end of list
            System.out.println("[Queue] Enqueued: " + item);
        }

        /**
         * Dequeue: removes and returns the item at the front (index 0) of the queue.
         * This is FIFO — first item added is the first removed.
         */
        public T dequeue() {
            if (isEmpty()) throw new RuntimeException("Queue is empty — cannot dequeue.");
            T front = elements.remove(0); // Remove from front of list
            System.out.println("[Queue] Dequeued: " + front);
            return front;
        }

        /**
         * Peek: returns the front item WITHOUT removing it.
         */
        public T peek() {
            if (isEmpty()) throw new RuntimeException("Queue is empty — cannot peek.");
            return elements.get(0); // Look at index 0 without removing
        }

        /** Returns true if the queue has no elements. */
        public boolean isEmpty() { return elements.isEmpty(); }

        /** Returns the number of elements in the queue. */
        public int size() { return elements.size(); }

        @Override
        public String toString() { return "Queue" + elements.toString(); }
    }

    // =========================================================================
    // PROBLEM 5 – Generic Result<T> (Success/Failure Wrapper)
    //
    // Design: A Result wrapper (similar to Optional or Either in functional
    //         programming) that represents either a successful value of type T
    //         or a failure with an error message. Prevents null returns and
    //         makes error handling explicit.
    //
    // Demonstrates: generic class, factory methods, wildcard in printResult,
    //               autoboxing when wrapping numeric success values.
    // =========================================================================

    static class Result<T> {

        private final T value;           // The success value (null if failure)
        private final String errorMsg;   // The error message (null if success)
        private final boolean success;   // true = success, false = failure

        // Private constructor — use factory methods below
        private Result(T value, String errorMsg, boolean success) {
            this.value    = value;
            this.errorMsg = errorMsg;
            this.success  = success;
        }

        /**
         * Factory method: creates a successful Result holding the given value.
         * Diamond operator infers T from the value parameter.
         */
        public static <T> Result<T> success(T value) {
            return new Result<>(value, null, true);
        }

        /**
         * Factory method: creates a failed Result with an error message.
         * The value is null because there is nothing to return on failure.
         */
        public static <T> Result<T> failure(String errorMsg) {
            return new Result<>(null, errorMsg, false);
        }

        /** Returns true if this Result represents success. */
        public boolean isSuccess() { return success; }

        /**
         * Returns the success value, or throws if this is a failure.
         * Unboxing applies if T is a wrapper type and the caller assigns to a primitive.
         */
        public T getValue() {
            if (!success) throw new RuntimeException("Result is a failure: " + errorMsg);
            return value;
        }

        /** Returns the error message, or null if this is a success. */
        public String getError() { return errorMsg; }

        @Override
        public String toString() {
            return success
                ? "Result.SUCCESS(" + value + ")"
                : "Result.FAILURE(\"" + errorMsg + "\")";
        }
    }

    /**
     * Wildcard helper: prints any Result regardless of its type parameter.
     * Uses <?> because we only need to display the result, not use the value.
     */
    public static void printResult(Result<?> result) {
        if (result.isSuccess()) {
            System.out.println("✓ Success: " + result.getValue());
        } else {
            System.out.println("✗ Failure: " + result.getError());
        }
    }

    /**
     * Example method that returns a Result<Integer> from a division operation.
     * Returns a failure Result if the divisor is zero instead of throwing.
     */
    public static Result<Integer> divide(int a, int b) {
        if (b == 0) {
            return Result.failure("Division by zero is not allowed.");
        }
        return Result.success(a / b); // Autoboxing: int result → Integer
    }

    // =========================================================================
    // MAIN METHOD — demonstrates all five created solutions
    // =========================================================================
    public static void main(String[] args) {

        // ----- Problem 1: Generic Repository -----
        System.out.println("=== Problem 1: Generic Repository ===");

        Repository<String> nameRepo = new Repository<>(); // Diamond infers String
        int id1 = nameRepo.save("Alice");   // Stores "Alice" with id=1
        int id2 = nameRepo.save("Bob");     // Stores "Bob"   with id=2
        int id3 = nameRepo.save("Charlie"); // Stores "Charlie" with id=3

        System.out.println("Find id=2: " + nameRepo.findById(id2)); // Bob
        nameRepo.delete(id1);                                         // Removes Alice
        System.out.println("All items: " + nameRepo.findAll());      // [Bob, Charlie]
        System.out.println("Size: " + nameRepo.size());              // 2

        System.out.println();
        Repository<Integer> scoreRepo = new Repository<>(); // Diamond infers Integer
        scoreRepo.save(95);  // Autoboxing: int 95 → Integer
        scoreRepo.save(87);  // Autoboxing: int 87 → Integer
        scoreRepo.save(72);  // Autoboxing: int 72 → Integer
        System.out.println("All scores: " + scoreRepo.findAll());

        // ----- Problem 2: Generic Triple -----
        System.out.println("\n=== Problem 2: Generic Triple ===");

        Triple<String, Integer, Boolean> t1 = new Triple<>("Alice", 30, true);
        // Diamond infers <String, Integer, Boolean>
        // Autoboxing: int 30 → Integer, boolean true → Boolean
        System.out.println("Original: " + t1);          // (Alice, 30, true)
        System.out.println("Reversed: " + t1.reverse()); // (true, 30, Alice)

        Triple<Integer, Double, String> t2 = new Triple<>(1, 3.14, "Pi");
        // Autoboxing: int 1 → Integer, double 3.14 → Double
        System.out.println("Original: " + t2);           // (1, 3.14, Pi)
        System.out.println("Reversed: " + t2.reverse()); // (Pi, 3.14, 1)

        // Access individual elements
        System.out.println("First element: " + t2.getFirst());  // 1
        System.out.println("Third element: " + t2.getThird());  // Pi

        // ----- Problem 3: Generic Calculator -----
        System.out.println("\n=== Problem 3: Generic Calculator ===");

        Calculator<Integer> intCalc = new Calculator<>(); // Diamond infers Integer
        intCalc.addValue(10);  // Autoboxing: int → Integer
        intCalc.addValue(20);
        intCalc.addValue(30);
        intCalc.addValue(40);
        intCalc.addValue(50);
        System.out.println("Count:   " + intCalc.count());   // 5
        System.out.println("Sum:     " + intCalc.sum());     // 150.0
        System.out.println("Average: " + intCalc.average()); // 30.0
        System.out.println("Max:     " + intCalc.max());     // 50.0
        System.out.println("Min:     " + intCalc.min());     // 10.0

        System.out.println();
        Calculator<Double> dblCalc = new Calculator<>(); // Diamond infers Double
        dblCalc.addValue(1.5);  // Autoboxing: double → Double
        dblCalc.addValue(2.5);
        dblCalc.addValue(3.0);
        System.out.println("Sum:     " + dblCalc.sum());     // 7.0
        System.out.println("Average: " + dblCalc.average()); // 2.3333...

        // ----- Problem 4: Generic Queue -----
        System.out.println("\n=== Problem 4: Generic Queue (FIFO) ===");

        Queue<String> taskQueue = new Queue<>(); // Diamond infers String
        taskQueue.enqueue("Task A"); // Added first
        taskQueue.enqueue("Task B");
        taskQueue.enqueue("Task C"); // Added last
        System.out.println("Front of queue: " + taskQueue.peek()); // Task A (not removed)
        System.out.println("Queue: " + taskQueue);                  // Queue[Task A, Task B, Task C]
        taskQueue.dequeue(); // Removes Task A (FIFO — first in, first out)
        taskQueue.dequeue(); // Removes Task B
        System.out.println("Remaining: " + taskQueue); // Queue[Task C]
        System.out.println("Size: " + taskQueue.size()); // 1

        System.out.println();
        Queue<Integer> numQueue = new Queue<>(); // Diamond infers Integer
        numQueue.enqueue(100); // Autoboxing: int 100 → Integer
        numQueue.enqueue(200);
        numQueue.enqueue(300);
        System.out.println("Dequeue: " + numQueue.dequeue()); // 100 (first in)
        System.out.println("Dequeue: " + numQueue.dequeue()); // 200

        // ----- Problem 5: Generic Result<T> -----
        System.out.println("\n=== Problem 5: Generic Result Wrapper ===");

        // Successful division
        Result<Integer> r1 = divide(10, 2);  // Returns Result.success(5), autoboxing int→Integer
        printResult(r1); // ✓ Success: 5

        // Failed division (divide by zero)
        Result<Integer> r2 = divide(7, 0);   // Returns Result.failure(...)
        printResult(r2); // ✗ Failure: Division by zero is not allowed.

        // Success with String type
        Result<String> r3 = Result.success("Hello, Generics!"); // Factory method infers String
        printResult(r3); // ✓ Success: Hello, Generics!

        // Failure with custom message
        Result<Double> r4 = Result.failure("Value not found in database.");
        printResult(r4); // ✗ Failure: Value not found in database.

        // Access value safely
        if (r1.isSuccess()) {
            int value = r1.getValue(); // Unboxing: Integer → int
            System.out.println("Extracted int value: " + value); // 5
        }

        // Demonstrate failure throws exception on getValue()
        try {
            r2.getValue(); // Should throw RuntimeException
        } catch (RuntimeException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
}

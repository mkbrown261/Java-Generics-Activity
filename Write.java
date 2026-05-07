import java.util.ArrayList;
import java.util.List;

/**
 * Write.java
 *
 * Step 3: Implement solutions for each problem below.
 * Each problem builds on Java Generics concepts: type parameters, bounded types,
 * wildcards, autoboxing/unboxing, and generic methods.
 */
public class Write {

    // =========================================================================
    // PROBLEM 1 – Generic Stack
    // Task: Implement a generic Stack class that can hold any type.
    //       Must support: push(), pop(), peek(), isEmpty(), and size().
    //
    // EXPECTED OUTPUT:
    //   Pushed: 10
    //   Pushed: 20
    //   Pushed: 30
    //   Peek: 30
    //   Popped: 30
    //   Popped: 20
    //   Size: 1
    //   Is Empty: false
    // =========================================================================

    // Generic Stack class with type parameter T
    static class Stack<T> {

        // Internal storage — ArrayList of type T
        private List<T> elements = new ArrayList<>();

        // push: adds an item to the top of the stack (end of list)
        public void push(T item) {
            elements.add(item);                  // Add item to end of list (top of stack)
            System.out.println("Pushed: " + item); // Confirm push to user
        }

        // pop: removes and returns the top item (end of list)
        public T pop() {
            if (isEmpty()) {
                throw new RuntimeException("Stack is empty — cannot pop.");
            }
            // Remove and return the last element (top of stack)
            T top = elements.remove(elements.size() - 1);
            System.out.println("Popped: " + top);
            return top;
        }

        // peek: returns the top item WITHOUT removing it
        public T peek() {
            if (isEmpty()) {
                throw new RuntimeException("Stack is empty — cannot peek.");
            }
            T top = elements.get(elements.size() - 1); // Get last element (top of stack)
            System.out.println("Peek: " + top);
            return top;
        }

        // isEmpty: returns true if the stack has no elements
        public boolean isEmpty() {
            return elements.isEmpty();
        }

        // size: returns the number of elements in the stack
        public int size() {
            return elements.size();
        }
    }

    // =========================================================================
    // PROBLEM 2 – Generic Method: Find Maximum
    // Task: Write a generic method that finds and returns the maximum value
    //       in an array. The type T must be Comparable so we can use compareTo().
    //
    // EXPECTED OUTPUT:
    //   Max integer: 95
    //   Max string:  Mango
    //   Max double:  9.9
    // =========================================================================

    // Bounded generic method: T must implement Comparable<T> to support compareTo()
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }
        T max = array[0]; // Assume first element is the maximum
        for (int i = 1; i < array.length; i++) {
            // compareTo returns positive if array[i] > max
            if (array[i].compareTo(max) > 0) {
                max = array[i]; // Update max when a larger element is found
            }
        }
        return max; // Return the largest element found
    }

    // =========================================================================
    // PROBLEM 3 – Generic Method: Swap Array Elements
    // Task: Write a generic method that swaps two elements in an array
    //       at given indices i and j.
    //
    // EXPECTED OUTPUT:
    //   Before swap: [A, B, C, D, E]
    //   After swap:  [A, D, C, B, E]
    //
    //   Before swap: [1, 2, 3, 4, 5]
    //   After swap:  [1, 4, 3, 2, 5]
    // =========================================================================

    // Generic swap method: works on any type T array
    public static <T> void swap(T[] array, int i, int j) {
        if (i < 0 || j < 0 || i >= array.length || j >= array.length) {
            throw new IndexOutOfBoundsException("Index out of bounds for swap.");
        }
        T temp   = array[i]; // Store element at index i in a temporary variable
        array[i] = array[j]; // Overwrite index i with element at index j
        array[j] = temp;     // Overwrite index j with the saved temporary value
    }

    // Helper: prints an array in [e1, e2, ...] format
    public static <T> void printArray(T[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) sb.append(", "); // Comma-separate elements
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    // =========================================================================
    // PROBLEM 4 – Bounded Wildcard: Sum of a Number List
    // Task: Write a method using an upper-bounded wildcard (? extends Number)
    //       that calculates the sum of any list of numbers.
    //
    // EXPECTED OUTPUT:
    //   Sum of integers: 150.0
    //   Sum of doubles:  13.2
    //   Sum of mixed:    46.28
    // =========================================================================

    // Upper-bounded wildcard: accepts List<Integer>, List<Double>, List<Float>, etc.
    public static double sumNumbers(List<? extends Number> list) {
        double sum = 0.0; // Accumulator
        for (Number n : list) {
            sum += n.doubleValue(); // Unboxing: gets double value from any Number subclass
        }
        return sum;
    }

    // =========================================================================
    // PROBLEM 5 – Generic Pair with Swap
    // Task: Create a generic Pair<A, B> class with a swap() method that
    //       returns a new Pair<B, A> with the key and value switched.
    //
    // EXPECTED OUTPUT:
    //   Original pair: (Alice, 25)
    //   Swapped pair:  (25, Alice)
    //
    //   Original pair: (true, Hello)
    //   Swapped pair:  (Hello, true)
    // =========================================================================

    // Generic Pair class with two type parameters A and B
    static class Pair<A, B> {

        private A first;  // First element of the pair
        private B second; // Second element of the pair

        // Constructor: initialise both elements
        public Pair(A first, B second) {
            this.first  = first;
            this.second = second;
        }

        // Getter for first element
        public A getFirst()  { return first; }

        // Getter for second element
        public B getSecond() { return second; }

        // swap: returns a NEW Pair with the types and values reversed
        // Note: the return type Pair<B, A> swaps the type parameters
        public Pair<B, A> swap() {
            return new Pair<>(second, first); // Diamond operator infers <B, A>
        }

        // toString: human-readable representation of the pair
        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    // =========================================================================
    // MAIN METHOD — runs all five problem demonstrations
    // =========================================================================
    public static void main(String[] args) {

        // ----- Problem 1: Generic Stack -----
        System.out.println("=== Problem 1: Generic Stack ===");
        Stack<Integer> stack = new Stack<>(); // Diamond operator infers Integer
        stack.push(10);  // Autoboxing: int 10 → Integer
        stack.push(20);  // Autoboxing: int 20 → Integer
        stack.push(30);  // Autoboxing: int 30 → Integer
        stack.peek();                                     // Look at top without removing
        stack.pop();                                      // Remove 30
        stack.pop();                                      // Remove 20
        System.out.println("Size: "     + stack.size());      // 1 remains
        System.out.println("Is Empty: " + stack.isEmpty());   // false

        // ----- Problem 2: Find Maximum -----
        System.out.println("\n=== Problem 2: Find Maximum ===");
        Integer[] ints    = {34, 7, 95, 12, 56};         // Autoboxing each int literal
        String[]  strings = {"Banana", "Mango", "Apple", "Cherry"};
        Double[]  doubles = {3.3, 9.9, 1.1, 7.7};        // Autoboxing each double literal

        System.out.println("Max integer: " + findMax(ints));    // 95
        System.out.println("Max string:  " + findMax(strings)); // Mango
        System.out.println("Max double:  " + findMax(doubles)); // 9.9

        // ----- Problem 3: Swap -----
        System.out.println("\n=== Problem 3: Swap Array Elements ===");
        String[]  letters = {"A", "B", "C", "D", "E"};
        System.out.print("Before swap: "); printArray(letters);
        swap(letters, 1, 3); // Swap index 1 (B) and index 3 (D)
        System.out.print("After swap:  "); printArray(letters);

        System.out.println();
        Integer[] nums = {1, 2, 3, 4, 5};  // Autoboxing each int
        System.out.print("Before swap: "); printArray(nums);
        swap(nums, 1, 3); // Swap index 1 (2) and index 3 (4)
        System.out.print("After swap:  "); printArray(nums);

        // ----- Problem 4: Sum with Wildcard -----
        System.out.println("\n=== Problem 4: Sum of Number Lists ===");
        List<Integer> intList = new ArrayList<>();
        for (int v : new int[]{10, 20, 30, 40, 50}) intList.add(v); // Autoboxing
        System.out.println("Sum of integers: " + sumNumbers(intList)); // 150.0

        List<Double> dblList = new ArrayList<>();
        for (double v : new double[]{1.1, 2.2, 3.3, 4.4, 2.2}) dblList.add(v); // Autoboxing
        System.out.println("Sum of doubles:  " + sumNumbers(dblList)); // 13.2

        List<Number> mixed = new ArrayList<>();
        mixed.add(10);     // Autoboxing int   → Integer (subclass of Number)
        mixed.add(3.14);   // Autoboxing double → Double  (subclass of Number)
        mixed.add(20L);    // Autoboxing long  → Long    (subclass of Number)
        mixed.add(12.99f); // Autoboxing float → Float   (subclass of Number)
        mixed.add(0.15);   // Autoboxing double → Double
        System.out.println("Sum of mixed:    " + sumNumbers(mixed)); // ~46.28

        // ----- Problem 5: Pair with Swap -----
        System.out.println("\n=== Problem 5: Pair with Swap ===");
        Pair<String, Integer> pair1 = new Pair<>("Alice", 25); // Diamond infers <String, Integer>
        System.out.println("Original pair: " + pair1);
        System.out.println("Swapped pair:  " + pair1.swap()); // Returns Pair<Integer, String>

        System.out.println();
        Pair<Boolean, String> pair2 = new Pair<>(true, "Hello"); // Diamond infers <Boolean, String>
        System.out.println("Original pair: " + pair2);
        System.out.println("Swapped pair:  " + pair2.swap()); // Returns Pair<String, Boolean>
    }
}

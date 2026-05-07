import java.util.ArrayList;
import java.util.List;

/**
 * Fix.java
 *
 * Step 4: Each problem below contains one or more bugs.
 * For each bug found:
 *   1. A comment explains what the bug was and how it was identified.
 *   2. The corrected code follows immediately.
 */
public class Fix {

    // =========================================================================
    // PROBLEM 1 – Generic Box class
    //
    // ORIGINAL BUGGY CODE:
    //   public class Box {                  // Bug: missing type parameter <T>
    //       private Object item;            // Bug: should be T, not Object
    //       public void setItem(Object item) { this.item = item; } // Bug: should be T
    //       public Object getItem() { return item; }               // Bug: should return T
    //   }
    //
    // BUG 1: The class declaration is missing the generic type parameter <T>.
    //        Without <T>, this is just a raw class that uses Object, which loses
    //        type safety — the compiler cannot catch incorrect casts at compile time.
    //        How found: IDE error "cannot find symbol T" if T were used; also
    //        code smell — using Object defeats the purpose of generics.
    //
    // BUG 2: The field type and method signatures use Object instead of T.
    //        This means a Box<String> could accidentally store an Integer,
    //        causing a ClassCastException at runtime.
    //        How found: Design review — a generic Box should be typed, not raw.
    //
    // FIX: Add <T> to the class declaration and replace Object with T.
    // =========================================================================

    // FIXED: Added <T> type parameter to class declaration
    static class Box<T> {

        // FIXED: field type changed from Object to T
        private T item;

        // FIXED: parameter type changed from Object to T
        public void setItem(T item) {
            this.item = item;
        }

        // FIXED: return type changed from Object to T
        public T getItem() {
            return item;
        }

        @Override
        public String toString() {
            return "Box[" + item + "]";
        }
    }

    // =========================================================================
    // PROBLEM 2 – Generic method to print all list elements
    //
    // ORIGINAL BUGGY CODE:
    //   public static void printAll(List<Object> list) {   // Bug: should be List<?>
    //       for (Object o : list) {
    //           System.out.println(o);
    //       }
    //   }
    //
    // BUG: Using List<Object> as the parameter type means this method ONLY
    //      accepts a List<Object>. It will NOT accept List<String>, List<Integer>,
    //      etc. because generics are INVARIANT — List<String> is NOT a subtype
    //      of List<Object> in Java.
    //      How found: Calling printAll(stringList) where stringList is List<String>
    //      gives: "incompatible types: List<String> cannot be converted to List<Object>"
    //
    // FIX: Replace List<Object> with the unbounded wildcard List<?> so the method
    //      accepts a list of ANY type.
    // =========================================================================

    // FIXED: parameter changed from List<Object> to List<?> (unbounded wildcard)
    public static void printAll(List<?> list) {
        for (Object o : list) {      // Object is fine here — we only need to print
            System.out.println(o);
        }
    }

    // =========================================================================
    // PROBLEM 3 – Bounded generic method: sum of a number list
    //
    // ORIGINAL BUGGY CODE:
    //   public static <T> double sum(List<T> list) {  // Bug: T is unconstrained
    //       double total = 0;
    //       for (T item : list) {
    //           total += item.doubleValue();  // Bug: T has no doubleValue() method
    //       }
    //       return total;
    //   }
    //
    // BUG 1: The type parameter <T> is unconstrained, so the compiler does not
    //        know that T has a doubleValue() method.
    //        How found: Compiler error — "cannot find symbol: method doubleValue()"
    //        because doubleValue() is defined on Number, not on arbitrary T.
    //
    // FIX: Add the bound <T extends Number> so the compiler knows T has doubleValue().
    // =========================================================================

    // FIXED: <T> changed to <T extends Number> to allow calling doubleValue()
    public static <T extends Number> double sum(List<T> list) {
        double total = 0;
        for (T item : list) {
            total += item.doubleValue(); // Now valid: Number guarantees doubleValue()
        }
        return total;
    }

    // =========================================================================
    // PROBLEM 4 – Generic Stack using ArrayList (pop bug)
    //
    // ORIGINAL BUGGY CODE:
    //   static class BuggyStack<T> {
    //       private List<T> data = new ArrayList<T>();  // OK
    //       public void push(T item) { data.add(item); } // OK
    //       public T pop() {
    //           return data.remove(0);   // Bug: removes from the FRONT (index 0),
    //                                   // not the top (last element) — wrong LIFO order
    //       }
    //       public T peek() {
    //           return data.get(0);     // Bug: looks at FRONT, not top (last element)
    //       }
    //   }
    //
    // BUG 1: pop() calls data.remove(0) — removes from the front of the list (FIFO),
    //        making this a Queue, not a Stack. A stack must remove from the TOP (last element).
    //        How found: Testing push(1), push(2), pop() returns 1 (wrong — should be 2).
    //
    // BUG 2: peek() calls data.get(0) — looks at the front, not the top of the stack.
    //        How found: Same test — peek() returns 1 instead of the most recently pushed 2.
    //
    // FIX: Change remove(0) → remove(data.size()-1) and get(0) → get(data.size()-1).
    // =========================================================================

    static class FixedStack<T> {
        private List<T> data = new ArrayList<>(); // Diamond operator infers T

        // push: adds item to end of list (top of stack)
        public void push(T item) {
            data.add(item);
        }

        // FIXED: remove last element (top of stack) instead of first
        public T pop() {
            if (data.isEmpty()) throw new RuntimeException("Stack underflow");
            return data.remove(data.size() - 1); // FIXED: was data.remove(0)
        }

        // FIXED: peek at last element (top of stack) instead of first
        public T peek() {
            if (data.isEmpty()) throw new RuntimeException("Stack is empty");
            return data.get(data.size() - 1);    // FIXED: was data.get(0)
        }

        public boolean isEmpty() { return data.isEmpty(); }
        public int size()        { return data.size(); }
    }

    // =========================================================================
    // PROBLEM 5 – Autoboxing / Unboxing NullPointerException
    //
    // ORIGINAL BUGGY CODE:
    //   List<Integer> scores = new ArrayList<>();
    //   scores.add(90);
    //   scores.add(null);    // Bug: null stored where int expected later
    //   scores.add(85);
    //   int total = 0;
    //   for (int s : scores) {   // Bug: unboxing null → NullPointerException at runtime
    //       total += s;
    //   }
    //
    // BUG 1: null is added to a List<Integer>. When the enhanced for-loop tries to
    //        unbox null to int (total += s), Java calls null.intValue() which throws
    //        a NullPointerException at runtime.
    //        How found: Runtime error — "NullPointerException" during unboxing.
    //
    // FIX: Either skip null values with a null check, or never insert null into
    //      a collection used for arithmetic. Both fixes shown below.
    // =========================================================================

    public static int safeSum(List<Integer> scores) {
        int total = 0;
        for (Integer s : scores) {          // Use Integer (not int) to detect null safely
            if (s != null) {                // FIXED: null check prevents NullPointerException
                total += s;                 // Unboxing is safe here — s is guaranteed non-null
            }
        }
        return total;
    }

    // =========================================================================
    // PROBLEM 6 – Wrong diamond operator usage (raw type)
    //
    // ORIGINAL BUGGY CODE:
    //   List<String> names = new ArrayList();  // Bug: raw type — missing <> or <String>
    //
    // BUG: new ArrayList() uses a raw type. While it compiles with a warning,
    //      it bypasses compile-time type checking — items of ANY type could be
    //      added without the compiler catching it.
    //      How found: IDE warning — "Raw use of parameterized class 'ArrayList'"
    //      Compiler flag -Xlint:unchecked reveals the issue.
    //
    // FIX: Use the diamond operator <> so the compiler infers the correct type <String>.
    // =========================================================================

    public static void problem6Demo() {
        // FIXED: raw type replaced with diamond operator — type is inferred as String
        List<String> names = new ArrayList<>(); // FIXED: was new ArrayList() (raw type)
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        System.out.println("Names: " + names);
    }

    // =========================================================================
    // MAIN METHOD — demonstrates all fixed solutions
    // =========================================================================
    public static void main(String[] args) {

        // ----- Problem 1: Fixed Box -----
        System.out.println("=== Problem 1: Fixed Generic Box ===");
        Box<String>  strBox = new Box<>();   // Diamond operator infers String
        strBox.setItem("Hello Generics");
        System.out.println(strBox);           // Box[Hello Generics]

        Box<Integer> intBox = new Box<>();   // Diamond operator infers Integer
        intBox.setItem(42);                   // Autoboxing: int 42 → Integer
        System.out.println(intBox);           // Box[42]

        // ----- Problem 2: Fixed printAll -----
        System.out.println("\n=== Problem 2: Fixed printAll (wildcard) ===");
        List<String>  words = new ArrayList<>();
        words.add("Generics"); words.add("Are"); words.add("Powerful");

        List<Integer> nums  = new ArrayList<>();
        nums.add(1); nums.add(2); nums.add(3); // Autoboxing

        printAll(words); // Now accepts List<String> — no compile error
        printAll(nums);  // Now accepts List<Integer> — no compile error

        // ----- Problem 3: Fixed sum -----
        System.out.println("\n=== Problem 3: Fixed sum (bounded) ===");
        List<Double> prices = new ArrayList<>();
        prices.add(9.99); prices.add(4.50); prices.add(14.99); // Autoboxing
        System.out.println("Sum: " + sum(prices)); // 29.48

        // ----- Problem 4: Fixed Stack -----
        System.out.println("\n=== Problem 4: Fixed Stack (LIFO order) ===");
        FixedStack<Integer> stack = new FixedStack<>();
        stack.push(1); stack.push(2); stack.push(3); // Autoboxing
        System.out.println("Peek (should be 3): " + stack.peek()); // 3
        System.out.println("Pop  (should be 3): " + stack.pop());  // 3
        System.out.println("Pop  (should be 2): " + stack.pop());  // 2

        // ----- Problem 5: Fixed null unboxing -----
        System.out.println("\n=== Problem 5: Fixed null unboxing ===");
        List<Integer> scores = new ArrayList<>();
        scores.add(90);   // Autoboxing
        scores.add(null); // null — would cause NPE without the fix
        scores.add(85);   // Autoboxing
        System.out.println("Safe sum (should be 175): " + safeSum(scores)); // 175

        // ----- Problem 6: Fixed raw type -----
        System.out.println("\n=== Problem 6: Fixed raw type ===");
        problem6Demo(); // Names: [Alice, Bob, Charlie]
    }
}

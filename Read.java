import java.util.ArrayList;  // Import ArrayList class from java.util package
import java.util.List;       // Import List interface from java.util package

/**
 * Read.java
 *
 * This file contains several code examples demonstrating Java Generics concepts.
 * For each problem, comments explain what each line does, the expected output
 * is shown, and a summary describes the purpose of the code.
 *
 * TERMS GLOSSARY (Step 1 Research):
 *
 *  Generics        – A Java feature that allows classes, interfaces, and methods
 *                    to operate on types specified as parameters, enabling type-safe
 *                    code reuse without casting.
 *
 *  Type Parameters – Placeholders (e.g. <T>, <E>, <K,V>) used in generic
 *                    declarations to represent an unknown type that is supplied
 *                    at compile time.
 *
 *  Boxing          – The manual conversion of a primitive type (e.g. int) into
 *                    its corresponding wrapper class object (e.g. Integer).
 *                    Example: Integer i = Integer.valueOf(5);
 *
 *  Unboxing        – The manual (or automatic) conversion of a wrapper object
 *                    back to its primitive type.
 *                    Example: int x = i.intValue();
 *
 *  Autoboxing      – The automatic conversion that Java performs between a
 *                    primitive type and its wrapper class when needed.
 *                    Example: Integer i = 5;  // compiler inserts Integer.valueOf(5)
 *
 *  Wrapper         – A class (Integer, Double, Character, Boolean, etc.) that
 *                    "wraps" a primitive value inside an object so it can be
 *                    used where objects are required (e.g. in generics/collections).
 *
 *  Diamond Operator – The <> syntax used on the right-hand side of a generic
 *                    instantiation, telling the compiler to infer the type argument
 *                    from the left-hand side declaration.
 *                    Example: List<String> list = new ArrayList<>();
 */
public class Read {

    // =========================================================================
    // PROBLEM 1 – Basic Generic Method
    // PURPOSE: Demonstrate a generic method that prints any type of value.
    //          A single method replaces overloaded versions for each type.
    //
    // EXPECTED OUTPUT:
    //   Java Generics
    //   100
    //   99.99
    //   true
    // =========================================================================

    // Generic method: <T> declares the type parameter; T is used as the parameter type
    public static <T> void printValue(T value) {
        System.out.println(value);  // Calls value.toString() implicitly and prints it
    }

    // =========================================================================
    // PROBLEM 2 – Generic Pair Class
    // PURPOSE: Show a generic class with TWO type parameters (K and V),
    //          similar to a Map entry. Useful for grouping two related values
    //          of potentially different types without creating specific classes.
    //
    // EXPECTED OUTPUT:
    //   Key: name, Value: Alice
    //   Key: age, Value: 30
    // =========================================================================

    // Generic class with two type parameters: K (key type) and V (value type)
    static class Pair<K, V> {

        private K key;    // Stores the key of type K
        private V value;  // Stores the value of type V

        // Constructor: initialises both the key and the value
        public Pair(K key, V value) {
            this.key   = key;    // Assign key parameter to field
            this.value = value;  // Assign value parameter to field
        }

        // Getter for key — returns type K
        public K getKey()   { return key; }

        // Getter for value — returns type V
        public V getValue() { return value; }

        // Returns human-readable representation of the Pair
        @Override
        public String toString() {
            return "Key: " + key + ", Value: " + value;
            // Concatenates the string labels with the key and value objects
        }
    }

    // =========================================================================
    // PROBLEM 3 – Generic List / Bounded Type Parameter
    // PURPOSE: Demonstrate an upper-bounded wildcard (? extends Number) and
    //          a bounded type parameter (<T extends Number>) to restrict
    //          the types that can be used with a generic method.
    //          Only Number and its subclasses (Integer, Double, etc.) are accepted.
    //
    // EXPECTED OUTPUT:
    //   Sum = 15.0
    //   Sum = 7.5
    // =========================================================================

    // Bounded generic method: T must be Number or a subclass of Number
    public static <T extends Number> double sumList(List<T> list) {
        double sum = 0;               // Accumulator — starts at 0.0
        for (T item : list) {         // Enhanced for-loop iterates over each element
            sum += item.doubleValue(); // Unboxing: calls doubleValue() on wrapper object
        }
        return sum;                   // Returns the total as a double
    }

    // =========================================================================
    // PROBLEM 4 – Autoboxing and Unboxing with Generics
    // PURPOSE: Show how Java automatically boxes primitives into wrapper objects
    //          when stored in generic collections, and unboxes them when retrieved.
    //
    // EXPECTED OUTPUT:
    //   Stored integers: [10, 20, 30, 40, 50]
    //   Total: 150
    //   Average: 30.0
    // =========================================================================

    // =========================================================================
    // PROBLEM 5 – Wildcard (?)
    // PURPOSE: Demonstrate the unbounded wildcard <?> which accepts a List of
    //          ANY type. Useful when the specific type does not matter, only
    //          that we can iterate and display elements.
    //
    // EXPECTED OUTPUT:
    //   [Hello, World, Java]
    //   [1, 2, 3]
    //   [1.1, 2.2, 3.3]
    // =========================================================================

    // Wildcard parameter: List<?> accepts a list of any type
    public static void printList(List<?> list) {
        System.out.println(list);  // Calls ArrayList.toString() which formats as [e1, e2, ...]
    }

    // =========================================================================
    // MAIN METHOD — runs all five problem demonstrations
    // =========================================================================
    public static void main(String[] args) {

        // ----- Problem 1 -----
        System.out.println("=== Problem 1: Generic Method ===");
        printValue("Java Generics");  // T inferred as String
        printValue(100);              // T inferred as Integer (autoboxing int → Integer)
        printValue(99.99);            // T inferred as Double  (autoboxing double → Double)
        printValue(true);             // T inferred as Boolean (autoboxing boolean → Boolean)

        // ----- Problem 2 -----
        System.out.println("\n=== Problem 2: Generic Pair Class ===");
        Pair<String, String>  p1 = new Pair<>("name", "Alice");
        // Diamond operator <> infers <String, String> from left-hand side
        // Creates a Pair holding key="name" and value="Alice"

        Pair<String, Integer> p2 = new Pair<>("age", 30);
        // Diamond operator <> infers <String, Integer>
        // Autoboxing: int literal 30 → Integer object

        System.out.println(p1);  // prints: Key: name, Value: Alice
        System.out.println(p2);  // prints: Key: age, Value: 30

        // ----- Problem 3 -----
        System.out.println("\n=== Problem 3: Bounded Type Parameter ===");

        List<Integer> intList = new ArrayList<>();  // Diamond operator infers Integer
        intList.add(1);   // Autoboxing: int 1  → Integer
        intList.add(2);   // Autoboxing: int 2  → Integer
        intList.add(3);   // Autoboxing: int 3  → Integer
        intList.add(4);   // Autoboxing: int 4  → Integer
        intList.add(5);   // Autoboxing: int 5  → Integer
        System.out.println("Sum = " + sumList(intList));  // prints: Sum = 15.0

        List<Double> dblList = new ArrayList<>();  // Diamond operator infers Double
        dblList.add(1.5);  // Autoboxing: double 1.5 → Double
        dblList.add(2.5);  // Autoboxing: double 2.5 → Double
        dblList.add(3.5);  // Autoboxing: double 3.5 → Double
        System.out.println("Sum = " + sumList(dblList));  // prints: Sum = 7.5

        // ----- Problem 4 -----
        System.out.println("\n=== Problem 4: Autoboxing & Unboxing ===");

        List<Integer> numbers = new ArrayList<>();  // Generic list of Integer wrapper objects
        int[] rawNums = {10, 20, 30, 40, 50};       // Primitive int array

        for (int n : rawNums) {
            numbers.add(n);  // Autoboxing: each primitive int is boxed into Integer before add
        }

        System.out.println("Stored integers: " + numbers);  // prints: [10, 20, 30, 40, 50]

        int total = 0;
        for (Integer n : numbers) {
            total += n;  // Unboxing: Integer object is automatically converted back to int for +=
        }

        System.out.println("Total: " + total);                       // prints: Total: 150
        System.out.println("Average: " + (double) total / numbers.size()); // prints: Average: 30.0

        // ----- Problem 5 -----
        System.out.println("\n=== Problem 5: Wildcard ===");

        List<String>  strList  = new ArrayList<>();  // List of Strings
        strList.add("Hello");
        strList.add("World");
        strList.add("Java");

        List<Integer> intList2  = new ArrayList<>(); // List of Integers
        intList2.add(1);  // Autoboxing
        intList2.add(2);  // Autoboxing
        intList2.add(3);  // Autoboxing

        List<Double>  dblList2  = new ArrayList<>(); // List of Doubles
        dblList2.add(1.1);  // Autoboxing
        dblList2.add(2.2);  // Autoboxing
        dblList2.add(3.3);  // Autoboxing

        printList(strList);   // <?> accepts List<String>  → prints: [Hello, World, Java]
        printList(intList2);  // <?> accepts List<Integer> → prints: [1, 2, 3]
        printList(dblList2);  // <?> accepts List<Double>  → prints: [1.1, 2.2, 3.3]
    }
}

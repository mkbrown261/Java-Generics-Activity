/**
 * Holder.java
 * 
 * A generic Holder class that can hold any type of object.
 * Demonstrates the use of generics with a type parameter <T>.
 * 
 * PURPOSE: This class acts as a generic container (a "holder") for any type.
 * Instead of writing separate Holder classes for String, Integer, Double, etc.,
 * we use a single generic class with a type parameter T.
 *
 * OUTPUT SUMMARY:
 *   Holding: Hello
 *   Holding: 42
 *   Holding: 3.14
 */
public class Holder<T> {  // <T> is the type parameter — T can be any reference type

    // Instance variable of type T — stores whatever type is provided
    private T value;

    // Constructor: accepts a value of type T and stores it
    public Holder(T value) {
        this.value = value;  // Assign the passed-in value to the instance variable
    }

    // Getter: returns the stored value as type T
    public T getValue() {
        return value;  // Returns the stored value
    }

    // Setter: replaces the stored value with a new one of type T
    public void setValue(T value) {
        this.value = value;  // Updates the stored value
    }

    // toString: returns a readable string representation of the Holder
    @Override
    public String toString() {
        return "Holding: " + value;  // Concatenates "Holding: " with the value's toString()
    }

    // -----------------------------------------------------------------------
    // Main method — demonstrates the Holder class with different types
    // -----------------------------------------------------------------------
    public static void main(String[] args) {

        // --- Problem 1: Holder with a String ---
        // Diamond operator <> infers type String from the left-hand side
        Holder<String> stringHolder = new Holder<>("Hello");
        // Creates a Holder that holds the String "Hello"

        System.out.println(stringHolder);
        // Calls toString() → prints: Holding: Hello

        // --- Problem 2: Holder with an Integer ---
        // Autoboxing: int literal 42 is automatically boxed into Integer object
        Holder<Integer> intHolder = new Holder<>(42);
        // Creates a Holder that holds the Integer 42

        System.out.println(intHolder);
        // Calls toString() → prints: Holding: 42

        // --- Problem 3: Holder with a Double ---
        // Autoboxing: double literal 3.14 is automatically boxed into Double object
        Holder<Double> doubleHolder = new Holder<>(3.14);
        // Creates a Holder that holds the Double 3.14

        System.out.println(doubleHolder);
        // Calls toString() → prints: Holding: 3.14

        // --- Demonstrating getValue() ---
        String s = stringHolder.getValue();  // Unboxing not needed — already a String
        // Returns the String stored inside stringHolder

        System.out.println("Extracted value: " + s);
        // prints: Extracted value: Hello

        // --- Demonstrating setValue() ---
        intHolder.setValue(100);
        // Updates the value inside intHolder from 42 to 100 (autoboxing int → Integer)

        System.out.println("Updated: " + intHolder);
        // Calls toString() → prints: Updated: Holding: 100
    }
}

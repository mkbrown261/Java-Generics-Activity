import java.util.ArrayList;
import java.util.List;

public class Fix {
    public static void main(String[] args) {

        // Fix: Problem 1
        // BUG: List<String> is declared but new ArrayList() is a raw type (missing <>).
        //      Then v.get(0) returns a String, but it's being cast to Integer — ClassCastException.
        // ERROR: "ClassCastException: String cannot be cast to Integer"
        // FIX 1: Use diamond operator on ArrayList
        // FIX 2: Remove the incorrect (Integer) cast — the list holds Strings, not Integers
        List<String> v = new ArrayList<>();  // FIXED: added <>
        v.add("test");
        String i = v.get(0);  // FIXED: changed (Integer) cast to String — matches List<String>
        System.out.println(i);
        // OUTPUT: test

        // Fix: Problem 2
        // BUG: myNumbers is a List<Integer> but 3.14 is a double/Double — type mismatch.
        // ERROR: "incompatible types: double cannot be converted to Integer"
        // FIX: Change the list to List<Double> to match the value being added
        List<Double> myNumbers = new ArrayList<>();  // FIXED: Integer -> Double
        myNumbers.add(3.14);  // Now valid — 3.14 autoboxed into Double
        System.out.println(myNumbers);
        // OUTPUT: [3.14]

        // Fix: Problem 3
        // BUG 1: Holder<Integer> is declared but the String "Indianapolis" is passed —
        //        type mismatch between declared type Integer and actual value String.
        // BUG 2: holder1.getValue() returns a String internally, so casting to (Integer) throws ClassCastException.
        // ERROR: "incompatible types" / "ClassCastException"
        // FIX: Change type parameter to String to match the value passed in
        Holder<String> holder1 = new Holder<>("Indianapolis");  // FIXED: Integer -> String
        System.out.println(holder1);
        System.out.println(holder1.getValue());  // FIXED: removed invalid (Integer) cast
        // OUTPUT: This is a Holder that contains: {Indianapolis}
        //         Indianapolis

        // Fix: Problem 4
        // BUG: Generics only work with reference types (wrapper classes), NOT primitives.
        //      Holder<int> is invalid — int is a primitive, not a reference type.
        // ERROR: "unexpected type — found: int, required: reference"
        // FIX: Replace primitive int with its wrapper class Integer
        Holder<Integer> holder2 = new Holder<>(35);  // FIXED: <int> -> <Integer>, autoboxing int 35 -> Integer
        System.out.println(holder2);
        // OUTPUT: This is a Holder that contains: {35}

        // Fix: Problem 5
        // BUG: myTemps is a List<Double> but int g is assigned from myTemps.get(0).
        //      myTemps.get(0) returns a Double — you cannot unbox Double directly to int
        //      without an explicit cast (Double -> double -> int loses decimal precision).
        // ERROR: "incompatible types: Double cannot be converted to int"
        // FIX: Change variable type to double to properly unbox the Double value
        List<Double> myTemps = new ArrayList<>();
        myTemps.add(72.0);  // Autoboxing: double 72.0 -> Double
        double g = myTemps.get(0);  // FIXED: int -> double — properly unboxes Double to double
        System.out.println(g);
        // OUTPUT: 72.0
    }
}

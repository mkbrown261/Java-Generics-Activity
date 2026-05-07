import java.util.ArrayList;
import java.util.List;

public class Read {
    public static void main(String[] args) {

        // Read: Problem 1
        // Declares a generic List that can only hold Integer objects.
        // ArrayList<> uses the diamond operator — the compiler infers Integer from the left side.
        // OUTPUT: nothing yet — just initializes an empty list
        List<Integer> myAges = new ArrayList<>();

        // Read: Problem 2
        // Autoboxing: the primitive int value 22 is automatically converted
        // into an Integer wrapper object and stored in variable x.
        // System.out.println prints the Integer value.
        // OUTPUT: 22
        Integer x = 22;
        System.out.println(x);

        // Read: Problem 3
        // Three integers are added to the myAges list:
        //   x (already an Integer wrapper) — no boxing needed
        //   Integer.valueOf(25) — manual boxing of int 25 into Integer
        //   27 — autoboxing: int 27 automatically boxed into Integer
        // OUTPUT: nothing printed — list is now [22, 25, 27]
        myAges.add(x);
        myAges.add(Integer.valueOf(25));
        myAges.add(27);

        // Read: Problem 4
        // myAges.get(2) retrieves the Integer at index 2 (value 27).
        // Unboxing: the Integer wrapper object is automatically converted
        // back to a primitive int and stored in variable 'a'.
        // System.out.println prints the int value.
        // OUTPUT: 27
        int a = myAges.get(2);
        System.out.println(a);

        // Read: Problem 5
        // Creates a Holder object with type parameter String.
        // The String "Portland" is passed to the constructor and stored.
        // System.out.println calls toString() on the Holder.
        // OUTPUT: This is a Holder that contains: {Portland}
        Holder<String> myHolder = new Holder<>("Portland");
        System.out.println(myHolder);

        // SUMMARY:
        // This code demonstrates:
        //   - Generic List<Integer> with the diamond operator
        //   - Boxing (Integer.valueOf), autoboxing (add(27)), and unboxing (int a = ...)
        //   - The generic Holder<Type> class used with a String type argument
        // FULL OUTPUT:
        //   22
        //   27
        //   This is a Holder that contains: {Portland}
    }
}

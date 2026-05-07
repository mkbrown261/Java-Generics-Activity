import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Write {

    public static void main(String[] args) {

        // Write: Problem 1
        // Declare a List of Integers and add the first five squares
        // to it: 1, 4, 9, 16, 25.
        List<Integer> squares = new ArrayList<>();
        // Autoboxing: each int literal is automatically boxed into an Integer object
        squares.add(1);   // 1^2
        squares.add(4);   // 2^2
        squares.add(9);   // 3^2
        squares.add(16);  // 4^2
        squares.add(25);  // 5^2
        System.out.println("Squares: " + squares);
        // OUTPUT: Squares: [1, 4, 9, 16, 25]

        // Write: Problem 2
        // Declare a Holder that contains the number 37, and display it.
        // Autoboxing: int 37 is boxed into Integer when passed to Holder<Integer>
        Holder<Integer> numberHolder = new Holder<>(37);
        System.out.println(numberHolder);
        // OUTPUT: This is a Holder that contains: {37}

        // Write: Problem 3
        // Declare a Holder that contains the String "Paris", and display it.
        Holder<String> cityHolder = new Holder<>("Paris");
        System.out.println(cityHolder);
        // OUTPUT: This is a Holder that contains: {Paris}

        // Write: Problem 4
        // Declare a Map that maps cities to their zip codes.
        // Each city is stored as a String, each zip code as an Integer.
        // Diamond operator <> infers <String, Integer> from the left side.
        Map<String, Integer> zipCodes = new HashMap<>();

        // Write: Problem 5
        // Add the following city -> zip code pairs to the Map:
        // Colorado Springs, Colorado (80911)
        // Reynoldsburg, Ohio (43068)
        // Rochester, New York (14617)
        // Autoboxing: int zip code literals are boxed into Integer objects
        zipCodes.put("Colorado Springs, Colorado", 80911);
        zipCodes.put("Reynoldsburg, Ohio", 43068);
        zipCodes.put("Rochester, New York", 14617);
        System.out.println("Zip Codes: " + zipCodes);
        // OUTPUT: Zip Codes: {Colorado Springs, Colorado=80911,
        //                     Reynoldsburg, Ohio=43068,
        //                     Rochester, New York=14617}
    }

}

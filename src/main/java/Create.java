import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Create {
    public static void main(String[] args) {

        // Create: Problem 1
        // Write code to create a List of Maps. The variable name
        // should be world. Each Map should use Strings for the
        // keys and Integers for the values.
        // Diamond operator <> infers the full generic type from the left side
        List<Map<String, Integer>> world = new ArrayList<>();

        // Create: Problem 2
        // Create a Map with the variable name usa. Insert the following
        // cities and population numbers, then add to world.
        // Autoboxing: int population literals are boxed into Integer objects
        Map<String, Integer> usa = new HashMap<>();
        usa.put("New York City", 8600000);
        usa.put("Los Angeles", 4000000);
        usa.put("Chicago", 2700000);
        usa.put("Houston", 2400000);
        world.add(usa);

        // Create: Problem 3
        // Create another Map with the variable name canada. Insert the
        // following cities and population numbers, then add to world.
        Map<String, Integer> canada = new HashMap<>();
        canada.put("Toronto", 5400000);
        canada.put("Montreal", 3500000);
        canada.put("Vancouver", 2300000);
        canada.put("Calgary", 1200000);
        world.add(canada);

        // Create: Problem 4
        // Retrieve the second key-value pair from the first list (usa).
        // world.get(0) retrieves the usa Map.
        // entrySet() returns all key-value pairs; we convert to a List to get by index.
        // Print out the key and the value.
        Map.Entry<String, Integer> usaEntry = new ArrayList<>(world.get(0).entrySet()).get(1);
        System.out.println("USA - Key: " + usaEntry.getKey() + ", Value: " + usaEntry.getValue());
        // Unboxing: getValue() returns Integer, printed as int via println

        // Create: Problem 5
        // Retrieve the first key-value pair from the second list (canada).
        // world.get(1) retrieves the canada Map.
        Map.Entry<String, Integer> canadaEntry = new ArrayList<>(world.get(1).entrySet()).get(0);
        System.out.println("Canada - Key: " + canadaEntry.getKey() + ", Value: " + canadaEntry.getValue());
        // Unboxing: getValue() returns Integer, printed as int via println
    }
}

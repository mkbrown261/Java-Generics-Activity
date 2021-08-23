import java.util.ArrayList;
import java.util.List;

public class Read {
    public static void main(String[] args) {

        //Read: Problem 1
        List<Integer> myAges = new ArrayList<>();

        //Read: Problem 2
        Integer x = 22;
        System.out.println(x);

        //Read: Problem 3
        myAges.add(x);
        myAges.add(Integer.valueOf(25));
        myAges.add(27);

        //Read: Problem 4
        int a = myAges.get(2);
        System.out.println(a);

        //Read: Problem 5
        Holder<String> myHolder = new Holder("Portland");
        System.out.println(myHolder);


    }
}

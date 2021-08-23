import java.util.ArrayList;
import java.util.List;

public class Fix {
    public static void main(String[] args) {

        //Fix: Problem 1
        List<String> v = new ArrayList();
        v.add("test");
        Integer i = (Integer)v.get(0);

        //Fix: Problem 2
        List<Integer> myNumbers = new ArrayList<>();
        myNumbers.add(3.14);

        //Fix: Problem 3
        Holder<Integer> holder1 = new Holder("Indianapolis");
        System.out.println(holder1);
        System.out.println((Integer)holder1.getValue());

        //Fix: Problem 4
        Holder<int> holder2 = new Holder(35);

        //Fix: Problem 5
        List<Double> myTemps = new ArrayList<>();
        myTemps.add(72.0);
        int g = myTemps.get(0);
    }
}

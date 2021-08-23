// Read: Problem 6
public class Holder<Type> {

    // Read: Problem 7
    private Type t;

    // Read: Problem 8
    public Holder(Type t) {
        this.t = t;
    }

    // Read: Problem 9
    public Type getValue() {
        return t;
    }

    // Read: Problem 10
    @Override
    public String toString() {
        return "This is a Holder that contains: {" + t + '}';
    }
}

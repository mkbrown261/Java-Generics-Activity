// Read: Problem 6
// This is a generic class declaration. <Type> is the type parameter —
// it acts as a placeholder for whatever type is passed in when a Holder is created.
public class Holder<Type> {

    // Read: Problem 7
    // This declares a private instance variable 't' of type 'Type'.
    // The actual type is determined when a Holder object is instantiated.
    private Type t;

    // Read: Problem 8
    // This is the constructor. It accepts a parameter of type 'Type'
    // and assigns it to the instance variable 't'.
    public Holder(Type t) {
        this.t = t;
    }

    // Read: Problem 9
    // This is a getter method that returns the stored value.
    // The return type is 'Type', matching whatever was passed in.
    public Type getValue() {
        return t;
    }

    // Read: Problem 10
    // This overrides the default toString() method.
    // It returns a String describing the Holder and its contained value.
    // OUTPUT: "This is a Holder that contains: {<value>}"
    @Override
    public String toString() {
        return "This is a Holder that contains: {" + t + '}';
    }
}

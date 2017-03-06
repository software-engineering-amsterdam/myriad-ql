package visitor;

public class DoubleNumber extends Value {
    private double myValue;

    public DoubleNumber(double myValue) {
        this.myValue = myValue;
    }

    private void add(DoubleNumber b) {
        System.out.println("adding doubles");
        myValue += b.getValue();
    }

    private void add(IntNumber b) {
        System.out.println("adding int to double");
        myValue += (double) b.getValue();
    }


    public double getValue() {
        return myValue;
    }

    @Override
    public int asInteger() {
        return (int) getValue();
    }
}

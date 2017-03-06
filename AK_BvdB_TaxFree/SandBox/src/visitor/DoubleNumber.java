package visitor;

public class DoubleNumber extends Value {
    private double myValue;

    public DoubleNumber(double myValue) {
        this.myValue = myValue;
    }

    private void add(DoubleNumber b) {
        System.out.println("adding doubles");
        myValue += b.asDouble();
    }

    private void add(IntNumber b) {
        System.out.println("adding int to double");
        myValue += (double) b.asDouble();
    }

    @Override
    public double asDouble() {
        return (double)myValue;
    }

    @Override
    public int asIntgeger() {
        return 0;
    }

    public double getValue() {
        return myValue;
    }
}

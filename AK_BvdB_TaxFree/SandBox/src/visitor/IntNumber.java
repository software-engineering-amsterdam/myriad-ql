package visitor;

public class IntNumber extends Value {
    int mValue;

    @Override
    public IntNumber(int initialValue) {
        mValue = initialValue;
    }

    public void accept(VisitorBase v) {
        v.visit(this);
    }

    @Override
    public double asDouble() {
        return new Double(mValue);
    }

    @Override
    public int asIntgeger() {
        return mValue;
    }
}

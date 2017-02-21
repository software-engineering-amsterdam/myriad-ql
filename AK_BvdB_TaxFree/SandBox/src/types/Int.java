package types;

public class Int extends Num {
    private int mValue;

    public Int(int initial) {

        mValue = initial;
    }

    public void setValue(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }
}

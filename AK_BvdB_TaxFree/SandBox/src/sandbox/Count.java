package sandbox;

public class Count {
    private int mCount = 0;

    public void increment() {
        increment(1);
    }

    public void increment(Number amount) {
        mCount += amount.intValue();
    }

    public int getCount() {
        return mCount;
    }
}

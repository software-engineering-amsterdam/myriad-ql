package sandbox;

public class Count {
    private int mCount = 0;

    public void increment() {
        increment(1);
    }

    protected void increment(int amount) {
        mCount += amount;
    }

    public int getCount() {
        return mCount;
    }
}

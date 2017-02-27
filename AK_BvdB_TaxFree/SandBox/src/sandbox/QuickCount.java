package sandbox;

public class QuickCount extends Count {

    public void increment(Integer amount) {
        increment(2 * amount.intValue());
    }
}

package inheritance;

public class QuickCount extends Count {

    public void increment(Integer amount) {
        super.increment(2 * amount.intValue());
    }

}

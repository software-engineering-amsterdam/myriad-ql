import sandbox.Count;
import sandbox.QuickCount;

public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        m.main();
    }

    public static void main() {
        Count count = new QuickCount();
        count.increment();
    }
}

package UvA.Gamma.Validation;

/**
 * Created by Tjarco, 13-03-17.
 */
public class Pair<T> {
    T firstValue;
    T secondValue;

    public Pair(T firstValue, T secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public boolean isComplete() {
        return firstValue != null && secondValue != null;
    }
}

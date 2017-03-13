package qls.ast.widgets;

/**
 * Created by rico on 7-3-17.
 */
public class Slider extends Widget {
    private final int min, max;

    public Slider(int min, int max, int rowNumber) {
        super(rowNumber);
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}

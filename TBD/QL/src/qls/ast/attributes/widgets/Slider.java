package qls.ast.attributes.widgets;

import qls.ast.literals.QLSInt;

/**
 * Created by rico on 7-3-17.
 */
public class Slider extends Widget {
    private final int min, max;

    public Slider(QLSInt min, QLSInt max, int rowNumber) {
        super(rowNumber);
        this.min = min.getValue();
        this.max = max.getValue();
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}

package qls.ast.attributes.widgets;

import qls.ast.literals.QLSString;

/**
 * Created by rico on 7-3-17.
 */
public class Radio extends Widget {
    private final String first;
    private final String second;

    public Radio(QLSString first, QLSString second, int rowNumber) {
        super(rowNumber);
        this.first = first.getValue();
        this.second = second.getValue();
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }
}

package qls.ast.widgets;

/**
 * Created by rico on 7-3-17.
 */
public class Radio extends Widget {
    private final String first;
    private final String second;

    public Radio(String first, String second, int rowNumber) {
        super(rowNumber);
        this.first = first;
        this.second = second;
    }
}

package qls.ast.attributes;

/**
 * Created by rico on 7-3-17.
 */
public class Height extends Attribute {
    private final int height;

    public Height(int height, int rowNumber) {
        super(rowNumber);
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}

package qls.ast.attributes;

import qls.ast.ObjectStatement;

/**
 * Created by rico on 7-3-17.
 */
public abstract class Attribute extends ObjectStatement {
    public Attribute(int rowNumber) {
        super(rowNumber);
    }
}

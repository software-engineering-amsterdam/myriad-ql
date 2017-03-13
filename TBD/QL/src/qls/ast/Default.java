package qls.ast;

import qls.ast.types.Type;

/**
 * Created by rico on 7-3-17.
 */
public class Default extends PageStatement {
    private final Type type;
    private final Attributes attributes;

    public Default(Type type, Attributes attributes, int rowNumber) {
        super(rowNumber);
        this.type = type;
        this.attributes = attributes;
    }

    public Type getType() {
        return type;
    }

    public Attributes getAttributes() {
        return attributes;
    }
}

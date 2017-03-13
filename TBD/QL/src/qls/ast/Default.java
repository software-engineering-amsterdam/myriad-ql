package qls.ast;

import qls.ast.types.Type;
import qls.ast.widgets.Widget;

/**
 * Created by rico on 7-3-17.
 */
public class Default extends PageStatement {
    private final Type type;
    private final DefaultStatements attributes;

    public Default(Type type, DefaultStatements attributes, int rowNumber) {
        super(rowNumber);
        this.type = type;
        this.attributes = attributes;
    }

    public Type getType() {
        return type;
    }

    public DefaultStatements getAttributes() {
        return attributes;
    }
}

package ql.ast.literals;

import ql.ast.values.StringValue;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLString implements QLLiteral {
    private final String qlString;

    public QLString(String qlString) {
        this.qlString = qlString;
    }

    public String getValue() {
        return qlString;
    }


    public StringValue toValue() {
        return new StringValue(qlString);
    }

    @Override
    public String toString() {
        return qlString;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

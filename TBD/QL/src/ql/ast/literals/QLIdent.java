package ql.ast.literals;

import ql.ast.values.UndefinedValue;
import ql.ast.values.Value;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLIdent extends QLLiteral {
    private final String qlIdent;

    public QLIdent(String qlIdent, int rowNumber) {
        super(rowNumber);
        this.qlIdent = qlIdent;
    }

    public String getValue() {
        return qlIdent;
    }

    @Override
    public Value toValue() {
        return new UndefinedValue();
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}

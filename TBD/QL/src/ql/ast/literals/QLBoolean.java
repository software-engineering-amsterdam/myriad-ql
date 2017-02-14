package ql.ast.literals;

import ql.ast.ASTNode;
import ql.ast.QLLiteral;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLBoolean implements QLLiteral {
    private boolean qlBoolean;

    public QLBoolean(boolean qlBoolean) {
        this.qlBoolean = qlBoolean;
    }

    public boolean getQlBoolean() {
        return qlBoolean;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

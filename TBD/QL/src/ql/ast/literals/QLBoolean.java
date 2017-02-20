package ql.ast.literals;

import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLBoolean extends QLLiteral {
    private final boolean qlBoolean;

    public QLBoolean(boolean qlBoolean) {
        this.qlBoolean = qlBoolean;
    }

    public boolean getValue() {
        return qlBoolean;
    }

    @Override
    public String toString() {
        return String.valueOf(qlBoolean);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public QLLiteral and(QLLiteral other) {
        return other.andEval(this);
    }

    @Override
    protected QLLiteral andEval(QLBoolean other) {
        return new QLBoolean(other.getValue() && this.getValue());
    }

    @Override
    public QLLiteral or(QLLiteral other) {
        return other.orEval(this);
    }

    @Override
    protected QLLiteral orEval(QLBoolean other) {
        return new QLBoolean(other.getValue() || this.getValue());
    }

    @Override
    public QLLiteral not() {
        return new QLBoolean(!getValue());
    }
}

package ql.ast.literals;

import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLFloat extends QLLiteral {
    private final float qlFloat;

    public QLFloat(float qlFloat) {
        this.qlFloat = qlFloat;
    }

    public float getValue() {
        return qlFloat;
    }

    @Override
    public String toString() {
        return String.valueOf(qlFloat);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public QLLiteral add(QLLiteral other) {
        return other.addEval(this);
    }

    @Override
    protected QLLiteral addEval(QLFloat other) {
        return new QLFloat(this.getValue() + other.getValue());
    }

    @Override
    protected QLLiteral addEval(QLInt other) {
        return new QLFloat(this.getValue() + other.getValue());
    }

    @Override
    public QLLiteral div(QLLiteral other) {
        return other.divEval(this);
    }

    @Override
    protected QLLiteral divEval(QLFloat other) {
        return new QLFloat(other.getValue() / this.getValue());
    }

    @Override
    protected QLLiteral divEval(QLInt other) {
        return new QLFloat(other.getValue() / this.getValue());
    }

    @Override
    public QLLiteral mul(QLLiteral other) {
        return other.mulEval(this);
    }

    @Override
    protected QLLiteral mulEval(QLFloat other) {
        return new QLFloat(this.getValue() * other.getValue());
    }

    @Override
    protected QLLiteral mulEval(QLInt other) {
        return new QLFloat(this.getValue() * other.getValue());
    }

    @Override
    public QLLiteral gEq(QLLiteral other) {
        return (new QLBoolean(other.equals(this))).or(gT(other));
    }

    @Override
    public QLLiteral gT(QLLiteral other) {
        return lEq(other).not();
    }

    @Override
    protected QLLiteral gTEval(QLFloat other) {
        return new QLBoolean(this.getValue() > other.getValue());
    }

    @Override
    protected QLLiteral gTEval(QLInt other) {
        return new QLBoolean(this.getValue() > other.getValue());
    }

    @Override
    public QLLiteral lEq(QLLiteral other) {
        return (new QLBoolean(other.equals(this))).or(lT(other));
    }

    @Override
    public QLLiteral lT(QLLiteral other) {
        return other.gTEval(this);
    }

    @Override
    public QLLiteral nEq(QLLiteral other) {
        return new QLBoolean(!other.equals(this));
    }

    @Override
    public QLLiteral sub(QLLiteral other) {
        return other.neg().add(this);
    }

    @Override
    public QLLiteral neg() {
        return new QLFloat(-getValue());
    }

    @Override
    public QLLiteral pos() {
        return new QLFloat(-getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QLFloat qlFloat1 = (QLFloat) o;

        return qlFloat == qlFloat1.qlFloat;
    }
}

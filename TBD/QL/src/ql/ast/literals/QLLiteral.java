package ql.ast.literals;

import ql.ast.Expr;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Erik on 7-2-2017.
 */
public abstract class QLLiteral implements Expr {

    public QLLiteral and(QLLiteral other) {
        throw new NotImplementedException();
    }

    protected QLLiteral andEval(QLBoolean other) {
        throw new NotImplementedException();
    }

    public QLLiteral or(QLLiteral other) {
        throw new NotImplementedException();
    }

    protected QLLiteral orEval(QLBoolean other) {
        throw new NotImplementedException();
    }

    public QLLiteral add(QLLiteral other) {
        throw new NotImplementedException();
    }

    protected QLLiteral addEval(QLInt other) {
        throw new NotImplementedException();
    }

    protected QLLiteral addEval(QLFloat other) {
        throw new NotImplementedException();
    }

    public QLLiteral div(QLLiteral other) {
        throw new NotImplementedException();
    }

    protected QLLiteral divEval(QLInt other) {
        throw new NotImplementedException();
    }

    protected QLLiteral divEval(QLFloat other) {
        throw new NotImplementedException();
    }

    public QLLiteral eq(QLLiteral other) {
        throw new NotImplementedException();
    }

    public QLLiteral gEq(QLLiteral other) {
        throw new NotImplementedException();
    }

    public QLLiteral gT(QLLiteral other) {
        throw new NotImplementedException();
    }

    protected QLLiteral gTEval(QLInt other) {
        throw new NotImplementedException();
    }

    protected QLLiteral gTEval(QLFloat other) {
        throw new NotImplementedException();
    }

    public QLLiteral lEq(QLLiteral other) {
        throw new NotImplementedException();
    }

    public QLLiteral lT(QLLiteral other) {
        throw new NotImplementedException();
    }

    public QLLiteral mul(QLLiteral other) {
        throw new NotImplementedException();
    }

    protected QLLiteral mulEval(QLInt other) {
        throw new NotImplementedException();
    }

    protected QLLiteral mulEval(QLFloat other) {
        throw new NotImplementedException();
    }

    public QLLiteral nEq(QLLiteral other) {
        throw new NotImplementedException();
    }

    public QLLiteral sub(QLLiteral other) {
        throw new NotImplementedException();
    }

    public QLLiteral neg() {
        throw new NotImplementedException();
    }

    public QLLiteral not() {
        throw new NotImplementedException();
    }

    public QLLiteral pos() {
        throw new NotImplementedException();
    }

}

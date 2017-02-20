package ql.ast;

import ql.ast.literals.QLBoolean;
import ql.ast.literals.QLFloat;
import ql.ast.literals.QLInt;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Erik on 7-2-2017.
 */
public abstract class QLLiteral implements Expr {

    public QLLiteral and(QLLiteral other) {
        throw new NotImplementedException();
    }

    public QLLiteral andEval(QLBoolean other) {
        throw new NotImplementedException();
    }

    public QLLiteral or(QLLiteral other) {
        throw new NotImplementedException();
    }

    public QLLiteral orEval(QLBoolean other) {
        throw new NotImplementedException();
    }

    public QLLiteral add(QLLiteral other) {
        throw new NotImplementedException();
    }

    public QLLiteral addEval(QLInt other) {
        throw new NotImplementedException();
    }

    public QLLiteral addEval(QLFloat other) {
        throw new NotImplementedException();
    }

    public QLLiteral div(QLLiteral other) {
        throw new NotImplementedException();
    }

    public QLLiteral divEval(QLInt other) {
        throw new NotImplementedException();
    }

    public QLLiteral divEval(QLFloat other) {
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

    public QLLiteral gTEval(QLInt other) {
        throw new NotImplementedException();
    }

    public QLLiteral gTEval(QLFloat other) {
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

    public QLLiteral mulEval(QLInt other) {
        throw new NotImplementedException();
    }

    public QLLiteral mulEval(QLFloat other) {
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

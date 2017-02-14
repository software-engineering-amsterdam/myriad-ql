package ql.ast.visistor;

import ql.ast.Form;
import ql.ast.If;
import ql.ast.Question;
import ql.ast.Statements;
import ql.ast.expressions.*;
import ql.ast.literals.*;

/**
 * Created by Erik on 14-2-2017.
 */
public class TypeASTVisitor implements ASTVisitor {
    @Override
    public Object visit(Form node) {
        return null;
    }

    @Override
    public Object visit(If node) {
        return null;
    }

    @Override
    public Object visit(Question node) {
        return null;
    }

    @Override
    public Object visit(Statements node) {
        return null;
    }

    @Override
    public Object visit(QLBoolean node) {
        return null;
    }

    @Override
    public Object visit(QLInt node) {
        return null;
    }

    @Override
    public Object visit(QLString node) {
        return null;
    }

    @Override
    public Object visit(QLIdent node) {
        return null;
    }

    @Override
    public Object visit(QLFloat node) {
        return null;
    }

    @Override
    public Object visit(Add node) {
        return null;
    }

    @Override
    public Object visit(And node) {
        return null;
    }

    @Override
    public Object visit(Div node) {
        return null;
    }

    @Override
    public Object visit(Eq node) {
        return null;
    }

    @Override
    public Object visit(GEq node) {
        return null;
    }

    @Override
    public Object visit(GT node) {
        return null;
    }

    @Override
    public Object visit(LEq node) {
        return null;
    }

    @Override
    public Object visit(LT node) {
        return null;
    }

    @Override
    public Object visit(Mul node) {
        return null;
    }

    @Override
    public Object visit(Neg node) {
        return null;
    }

    @Override
    public Object visit(NEq node) {
        return null;
    }

    @Override
    public Object visit(Not node) {
        return null;
    }

    @Override
    public Object visit(Or node) {
        return null;
    }

    @Override
    public Object visit(Pos node) {
        return null;
    }

    @Override
    public Object visit(Sub node) {
        return null;
    }
}

package ql.ast.visistor;

import ql.ast.*;
import ql.ast.expressions.binop.*;
import ql.ast.expressions.monop.Neg;
import ql.ast.expressions.monop.Not;
import ql.ast.expressions.monop.Pos;
import ql.ast.literals.*;

import java.util.List;

/**
 * Created by Erik on 14-2-2017.
 */
public abstract class ASTVisitor<T> {

    public T visit(Form node) {
        node.getName().accept(this);
        node.getStatements().accept(this);
        return null;
    }

    
    public T visit(Statements node) {
        List<Statement> statements = node.getStatements();
        for (Statement statement: statements) {
            statement.accept(this);
        }
        return null;
    }

    public T visit(If node) {
        node.getCondition().accept(this);
        node.getIfBlock().accept(this);
        return null;
    }

    public T visit(IfElse node) {
        node.getCondition().accept(this);
        node.getIfBlock().accept(this);
        node.getElseBlock().accept(this);
        return null;
    }


    public T visit(Question node) {
        return null;
    }



    public T visit(QuestionExpr node) {
        node.getExpr().accept(this);
        return null;
    }


    public T visit(QLIdent node) {
        return null;
    }


    public T visit(QLBoolean node) {
        return null;
    }


    public T visit(QLInt node) {
        return null;
    }


    public T visit(QLString node) {
        return null;
    }


    public T visit(QLFloat node) {
        return null;
    }


    public T visit(Add node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        return null;
    }


    public T visit(Div node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        return null;
    }


    public T visit(Eq node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        return null;
    }


    public T visit(GEq node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        return null;
    }


    public T visit(GT node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        return null;
    }


    public T visit(LEq node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        return null;
    }


    public T visit(LT node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        return null;
    }


    public T visit(Mul node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        return null;
    }


    public T visit(NEq node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        return null;
    }


    public T visit(Sub node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        return null;
    }


    public T visit(And node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        return null;
    }


    public T visit(Or node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        return null;
    }


    public T visit(Pos node) {
        node.getExpr().accept(this);
        return null;
    }


    public T visit(Neg node) {
        node.getExpr().accept(this);
        return null;
    }


    public T visit(Not node) {
        node.getExpr().accept(this);
        return null;
    }
}

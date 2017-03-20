package ql.visistor;

import ql.ast.*;
import ql.ast.expressions.binop.*;
import ql.ast.expressions.monop.Neg;
import ql.ast.expressions.monop.Not;
import ql.ast.expressions.monop.Pos;
import ql.ast.literals.*;
import ql.visistor.interfaces.BaseVisitor;
import ql.visistor.interfaces.ExpressionVisitor;

import java.util.List;

/**
 * Created by Erik on 14-2-2017.
 */
public class PrintASTVisitor implements BaseVisitor<Void>, ExpressionVisitor<Void> {
    @Override
    public Void visit(Form node) {
        System.out.print("form ");
        System.out.print(node.getName());
        System.out.print(" {\n");
        node.getStatements().accept(this);
        System.out.print("}\n\n");
        return null;
    }

    @Override
    public Void visit(Statements node) {
        List<Statement> statements = node.getItems();
        for (Statement statement: statements) {
            statement.accept(this);
            System.out.print("\n");
        }
        return null;
    }

    @Override
    public Void visit(If node) {
        System.out.print("if (");
        node.getCondition().accept(this);
        System.out.print("){\n");
        node.getIfBlock().accept(this);
        System.out.print("}");
        return null;
    }

    @Override
    public Void visit(IfElse node) {
        System.out.print("if (");
        node.getCondition().accept(this);
        System.out.print("){\n");
        node.getIfBlock().accept(this);
        System.out.print("}");
        System.out.print("else {\n");
        node.getElseBlock().accept(this);
        System.out.print("}");
        return null;
    }

    @Override
    public Void visit(Question node) {
        System.out.print("\"");
        System.out.print(node.getQuestion());
        System.out.print("\" \n ");
        System.out.print(node.getId());
        System.out.print(" : ");
        System.out.print(node.getType().toString());

        return null;
    }


    @Override
    public Void visit(QuestionExpr node) {
        System.out.print("\"");
        System.out.print(node.getQuestion());
        System.out.print("\" \n ");
        System.out.print(node.getId());
        System.out.print(" : ");
        System.out.print(node.getType().toString());
        System.out.print(" =\n");
        node.getExpr().accept(this);

        return null;
    }

    @Override
    public Void visit(QLIdent node) {
        System.out.print(node.getValue());
        return null;
    }

    @Override
    public Void visit(QLBoolean node) {
        System.out.print(node.getValue());
        return null;
    }

    @Override
    public Void visit(QLInt node) {
        System.out.print(node.getValue());
        return null;
    }

    @Override
    public Void visit(QLString node) {
        System.out.print(node.getValue());
        return null;
    }

    @Override
    public Void visit(QLFloat node) {
        System.out.print(node.getValue());
        return null;
    }

    @Override
    public Void visit(Add node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print(" + ");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(Div node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print(" / ");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(Eq node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print(" == ");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(GEq node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print(" >= ");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(GT node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print(" > ");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(LEq node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print(" <= ");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(LT node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print(" < ");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(Mul node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print(" * ");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(NEq node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print(" != ");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(Sub node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print(" - ");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(And node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print(" && ");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(Or node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print(" || ");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(Pos node) {
        System.out.print("(");
        System.out.print("+");
        node.getExpr().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(Neg node) {
        System.out.print("(");
        System.out.print("-");
        node.getExpr().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(Not node) {
        System.out.print("(");
        System.out.print("!");
        node.getExpr().accept(this);
        System.out.print(")");
        return null;
    }
}

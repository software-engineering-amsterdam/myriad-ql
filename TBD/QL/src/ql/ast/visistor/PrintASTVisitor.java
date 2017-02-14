package ql.ast.visistor;

import ql.ast.*;
import ql.ast.expressions.binop.And;
import ql.ast.expressions.binop.Or;
import ql.ast.expressions.numop.*;
import ql.ast.expressions.unop.Neg;
import ql.ast.expressions.unop.Not;
import ql.ast.expressions.unop.Pos;
import ql.ast.literals.*;

/**
 * Created by Erik on 14-2-2017.
 */
public class PrintASTVisitor implements ASTVisitor<Void> {
    @Override
    public Void visit(Form node) {
        System.out.print("form ");
        node.getName().accept(this);
        System.out.print(" {\n");
        node.getStatements().accept(this);
        System.out.print("}");
        return null;
    }

    @Override
    public Void visit(If node) {
        System.out.print("if (");
        node.getExpression().accept(this);
        System.out.print("){\n");
        node.getIfBlock().accept(this);
        System.out.print("}");
        if (node.hasElseBlock()) {
            System.out.print("else {\n");
            node.getElseBlock().accept(this);
            System.out.print("}");
        }
        return null;
    }

    @Override
    public Void visit(Question node) {
        System.out.print("\"");
        node.getQuestion().accept(this);
        System.out.print("\" \n ");
        node.getId().accept(this);
        System.out.print(" : ");
        System.out.print(typeToString(node.getType()));

        if (node.hasExpr()) {
            System.out.print(" =\n");
            node.getExpr().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Statements node) {
        if (node.hasCurrent()) {
            node.getCurrent().accept(this);
        }
        System.out.print("\n");
        if (node.hasNext()) {
            node.getNext().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(QLBoolean node) {
        System.out.print(node.getQlBoolean());
        return null;
    }

    @Override
    public Void visit(QLInt node) {
        System.out.print(node.getQlInteger());
        return null;
    }

    @Override
    public Void visit(QLString node) {
        System.out.print(node.getQlString());
        return null;
    }

    @Override
    public Void visit(QLIdent node) {
        System.out.print(node.getQlIdent());
        return null;
    }

    @Override
    public Void visit(QLFloat node) {
        System.out.print(node.getQlFloat());
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
    public Void visit(And node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print(" && ");
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
    public Void visit(Neg node) {
        System.out.print("(");
        System.out.print("-");
        node.getExpr().accept(this);
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
    public Void visit(Not node) {
        System.out.print("(");
        System.out.print("!");
        node.getExpr().accept(this);
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
    public Void visit(Sub node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print(" - ");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }


    private String typeToString(Type type) {
        switch (type) {
            case TYPESTRING: return "string";
            case TYPEBOOL: return "boolean";
            case TYPEINT: return "int";
            case TYPEDATE: return "date";
            case TYPEFLOAT: return "float";
            case TYPEMONEY: return "money";
        }
        return "unknown";
    }
}

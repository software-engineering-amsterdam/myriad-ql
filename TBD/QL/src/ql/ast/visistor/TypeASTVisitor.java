package ql.ast.visistor;

import ql.ast.*;
import ql.ast.expressions.BinOp;
import ql.ast.expressions.binop.And;
import ql.ast.expressions.binop.Or;
import ql.ast.expressions.numop.*;
import ql.ast.expressions.unop.Neg;
import ql.ast.expressions.unop.Not;
import ql.ast.expressions.unop.Pos;
import ql.ast.literals.*;

import java.util.HashMap;

/**
 * Created by Erik on 14-2-2017.
 */
public class TypeASTVisitor implements ASTVisitor<Type> {

    private HashMap<String, Type> identTable = new HashMap<>();

    @Override
    public Type visit(Form node) {
        node.getStatements().accept(this);
        return null;
    }

    @Override
    public Type visit(If node) {
        node.getExpression().accept(this);
        node.getIfBlock().accept(this);

        if (node.hasElseBlock()) {
            node.getElseBlock().accept(this);
        }
        return null;
    }

    @Override
    public Type visit(Question node) {
        identTable.put(node.getId().getQlIdent(), node.getType());

        if (node.hasExpr()) {
            node.getExpr().accept(this);
        }
        return null;
    }

    @Override
    public Type visit(Statements node) {
        if (node.hasCurrent()) {
            node.getCurrent().accept(this);
        }

        if (node.hasNext()) {
            node.getNext().accept(this);
        }
        return null;
    }

    @Override
    public Type visit(QLBoolean node) {
        return Type.TYPEBOOL;
    }

    @Override
    public Type visit(QLInt node) {
        return Type.TYPEINT;
    }

    @Override
    public Type visit(QLString node) {
        return Type.TYPESTRING;
    }

    @Override
    public Type visit(QLIdent node) {
        if (identTable.containsKey(node.getQlIdent())) {
            System.out.println(node.getQlIdent());
            System.out.println(typeToString(identTable.get(node.getQlIdent())));
            return identTable.get(node.getQlIdent());
        }
        throw new RuntimeException("Unexpected variable");
    }

    @Override
    public Type visit(QLFloat node) {
        return Type.TYPEFLOAT;
    }

    @Override
    public Type visit(Add node) {
        return null;
    }

    @Override
    public Type visit(And node) {
        return checkBinOp(node);
    }

    @Override
    public Type visit(Div node) {
        return null;
    }

    @Override
    public Type visit(Eq node) {
        return null;
    }

    @Override
    public Type visit(GEq node) {
        return null;
    }

    @Override
    public Type visit(GT node) {
        return null;
    }

    @Override
    public Type visit(LEq node) {
        return null;
    }

    @Override
    public Type visit(LT node) {
        return null;
    }

    @Override
    public Type visit(Mul node) {
        return null;
    }

    @Override
    public Type visit(Neg node) {
        return null;
    }

    @Override
    public Type visit(NEq node) {
        return null;
    }

    @Override
    public Type visit(Not node) {
        return null;
    }

    @Override
    public Type visit(Or node) {
        return checkBinOp(node);
    }

    @Override
    public Type visit(Pos node) {
        return null;
    }

    @Override
    public Type visit(Sub node) {
        Type left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);
        if (left == right){
            return left;
        }
        throw new RuntimeException("Type error");

    }

    private Type checkBinOp(BinOp node) {
        Type left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);
        if (left == Type.TYPEBOOL && left == right){
            return left;
        }
        throw new RuntimeException("Type error");
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

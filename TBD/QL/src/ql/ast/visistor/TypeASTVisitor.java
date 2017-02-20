package ql.ast.visistor;

import ql.ast.*;
import ql.ast.expressions.BinOp;
import ql.ast.expressions.NumOp;
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
        Type expr = node.getExpression().accept(this);

        if (expr != Type.TYPEBOOL) {
            throw new RuntimeException("Type error");
        }

        node.getIfBlock().accept(this);
        if (node.hasElseBlock()) {
            node.getElseBlock().accept(this);
        }
        return null;
    }

    @Override
    public Type visit(Question node) {
        if (identTable.containsKey(node.getId().getValue())) {
            throw new RuntimeException("Id already exist");
        }

        identTable.put(node.getId().getValue(), node.getType());

        if (node.hasExpr()) {
            Type expr = node.getExpr().accept(this);
            if (expr == node.getType()) {
                return null;
            }
            throw new RuntimeException("Type error");
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
        if (identTable.containsKey(node.getValue())) {
            return identTable.get(node.getValue());
        }
        throw new RuntimeException("Unexpected variable");
    }

    @Override
    public Type visit(QLFloat node) {
        return Type.TYPEFLOAT;
    }

    @Override
    public Type visit(Add node) {
        return checkNumOp(node);
    }

    @Override
    public Type visit(And node) {
        return checkBinOp(node);
    }

    @Override
    public Type visit(Div node) {
        return checkNumOp(node);
    }

    @Override
    public Type visit(Eq node) {
        return checkNumOp(node);
    }

    @Override
    public Type visit(GEq node) {
        return checkNumOp(node);
    }

    @Override
    public Type visit(GT node) {
        return checkNumOp(node);
    }

    @Override
    public Type visit(LEq node) {
        return checkNumOp(node);
    }

    @Override
    public Type visit(LT node) {
        return checkNumOp(node);
    }

    @Override
    public Type visit(Mul node) {
        return checkNumOp(node);
    }

    @Override
    public Type visit(Neg node) {
        Type expr = node.getExpr().accept(this);

        if (expr != Type.TYPEBOOL) {
            return expr;
        }
        throw new RuntimeException("Type error");
    }

    @Override
    public Type visit(NEq node) {
        return checkNumOp(node);
    }

    @Override
    public Type visit(Not node) {
        Type expr = node.getExpr().accept(this);

        if (expr == Type.TYPEBOOL) {
            return expr;
        }
        throw new RuntimeException("Type error");
    }

    @Override
    public Type visit(Or node) {
        return checkBinOp(node);
    }

    @Override
    public Type visit(Pos node) {
        Type expr = node.getExpr().accept(this);

        if (expr != Type.TYPEBOOL) {
            return expr;
        }
        throw new RuntimeException("Type error");
    }

    @Override
    public Type visit(Sub node) {
        return checkNumOp(node);
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

    private Type checkNumOp(NumOp node) {
        Type left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);
        if (left == right || (left == Type.TYPEINT && right == Type.TYPEFLOAT) || (left == Type.TYPEFLOAT && right == Type.TYPEINT)){
            return left;
        }
        throw new RuntimeException("Type error");
    }

}

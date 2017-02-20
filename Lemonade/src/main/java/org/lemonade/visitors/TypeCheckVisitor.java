package org.lemonade.visitors;

import org.lemonade.nodes.Block;
import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.Type;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.literal.*;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;

import java.util.HashMap;

/**
 *
 */
public class TypeCheckVisitor implements ASTVisitor<Type> {
    HashMap<String, Type> table = new HashMap<>();

    public Type visit(Block block) {
        System.err.println("block hit");
        return null;
    }

    public Type visit(Form form) {
        for (Block block : form.getBlocks()) {
            block.accept(this);
        }
        return null;
    }

    public Type visit(Question question) {
        String identifier = question.getIdentifier();
        Type type = question.getType();

        assert !table.containsKey(identifier);
        table.put(identifier, type);

        System.err.println("question hit");
        return null;
    }

    public Type visit(Conditional conditional) {
        System.err.println("conditional hit");

        for (Block block : conditional.getBlocks()) {
            block.accept(this);
        }
        Type condition = conditional.getCondition().accept(this);
        return null;
    }

    public Type visit(Expression expression) {
        return null;
    }

    public Type visit(AndBinary andBinary) {
        return null;
    }

    public Type visit(OrBinary orBinary) {
        return null;
    }


    public Type visit(PlusBinary plusBinary) {
        System.err.println(plusBinary.getLeft().accept(this) + "+" + plusBinary.getRight().accept(this));
        return null;
    }

    public Type visit(ProductBinary productBinary) {
        return null;
    }

    public Type visit(MinusBinary minusBinary) {
        return null;
    }

    public Type visit(DivideBinary divideBinary) {
        return null;
    }

    public Type visit(EqBinary eqBinary) {
        return null;
    }

    public Type visit(NEqBinary nEqBinary) {
        return null;
    }

    public Type visit(GTBinary gtBinary) {
        return null;
    }

    public Type visit(GTEBinary gteBinary) {
        return null;
    }

    public Type visit(LTBinary ltBinary) {
        return null;
    }

    public Type visit(LTEBinary lteBinary) {
        return null;
    }

    public Type visit(BangUnary bangUnary) {
        return null;
    }

    public Type visit(NegUnary negUnary) {
        return null;
    }

    public Type visit(BooleanLit booleanLit) {
        return null;
    }

    public Type visit(DecimalLit decimalLit) {
        return null;
    }

    public Type visit(IdentifierLit identifierLit) {
        return null;
    }

    public Type visit(IntegerLit integerLit) {
        return null;
    }

    public Type visit(StringLit stringLit) {
        return null;
    }

    @Override
    public Type visit(Type type) {
        return null;
    }
}

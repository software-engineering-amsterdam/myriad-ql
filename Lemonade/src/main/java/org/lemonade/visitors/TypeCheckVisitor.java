package org.lemonade.visitors;

import org.lemonade.nodes.*;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.types.QLType;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.literal.*;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;

import java.util.HashMap;

/**
 *
 */
public class TypeCheckVisitor implements ASTVisitor<QLType> {
    HashMap<String, QLType> table = new HashMap<>();


    public QLType visit(Block block) {
        System.err.println("block hit");
        return null;
    }

    public QLType visit(Form form) {
        for (Block block : form.getBlocks()) {
            block.accept(this);
        }
        return null;
    }

    public QLType visit(Question question) {
        String identifier = question.getIdentifier();
        QLType type = question.getType();

        assert !table.containsKey(identifier);
        table.put(identifier, type);

        System.err.println("question hit");
        return null;
    }

    public QLType visit(Conditional conditional) {
        System.err.println("conditional hit");

        for (Block block : conditional.getBlocks()) {
            block.accept(this);
        }
        QLType condition = conditional.getCondition().accept(this);
        return null;
    }

    public QLType visit(Expression expression) {
        return null;
    }

    public QLType visit(AndBinary andBinary) {
        return null;
    }

    public QLType visit(OrBinary orBinary) {
        return null;
    }


    public QLType visit(PlusBinary plusBinary) {
        System.err.println(plusBinary.getLeft().accept(this) + "+" + plusBinary.getRight().accept(this));
        return null;
    }

    public QLType visit(ProductBinary productBinary) {
        return null;
    }

    public QLType visit(MinusBinary minusBinary) {
        return null;
    }

    public QLType visit(DivideBinary divideBinary) {
        return null;
    }

    public QLType visit(EqBinary eqBinary) {
        return null;
    }

    public QLType visit(NEqBinary nEqBinary) {
        return null;
    }

    public QLType visit(GTBinary gtBinary) {
        return null;
    }

    public QLType visit(GTEBinary gteBinary) {
        return null;
    }

    public QLType visit(LTBinary ltBinary) {
        return null;
    }

    public QLType visit(LTEBinary lteBinary) {
        return null;
    }

    public QLType visit(BangUnary bangUnary) {
        return null;
    }

    public QLType visit(NegUnary negUnary) {
        return null;
    }

    public QLType visit(BooleanLit booleanLit) {
        return null;
    }

    public QLType visit(DecimalLit decimalLit) {
        return null;
    }

    public QLType visit(IdentifierLit identifierLit) {
        return null;
    }

    public QLType visit(IntegerLit integerLit) {
        return null;
    }

    public QLType visit(StringLit stringLit) {
        return null;
    }

    @Override
    public QLType visit(QLType type) {
        return null;
    }
}

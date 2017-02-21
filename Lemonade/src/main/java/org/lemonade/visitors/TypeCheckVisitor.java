package org.lemonade.visitors;

import org.lemonade.nodes.*;
import org.lemonade.nodes.expressions.BinaryExpression;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.types.*;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.literal.*;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class TypeCheckVisitor implements ASTVisitor<QLType> {
    Map<String, QLType> table = new HashMap<>();

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
        return checkBinaryNumeric(plusBinary);
    }

    public QLType visit(ProductBinary productBinary) {
        return checkBinaryNumeric(productBinary);
    }

    public QLType visit(MinusBinary minusBinary) {
        return checkBinaryNumeric(minusBinary);
    }

    public QLType visit(DivideBinary divideBinary) {
        return checkBinaryNumeric(divideBinary);
    }

    public QLType visit(EqBinary eqBinary) {
        return checkBinaryComparable(eqBinary);
    }

    public QLType visit(NEqBinary nEqBinary) {
        return checkBinaryComparable(nEqBinary);
    }

    public QLType visit(GTBinary gtBinary) {
        return checkBinaryComparable(gtBinary);
    }

    public QLType visit(GTEBinary gteBinary) {
        return checkBinaryComparable(gteBinary);
    }

    public QLType visit(LTBinary ltBinary) {
        return checkBinaryComparable(ltBinary);
    }

    public QLType visit(LTEBinary lteBinary) {
        return checkBinaryComparable(lteBinary);
    }

    public QLType visit(BangUnary bangUnary) {
        return null;
    }

    public QLType visit(NegUnary negUnary) {
        return null;
    }

    public QLType visit(BooleanLit booleanLit) {
        return booleanLit.getType();
    }

    public QLType visit(DecimalLit decimalLit) {
        return decimalLit.getType();
    }

    public QLType visit(IdentifierLit identifierLit) {
        return identifierLit.getType();
    }

    public QLType visit(IntegerLit integerLit) {
        return integerLit.getType();
    }

    public QLType visit(StringLit stringLit) {
        return stringLit.getType();
    }

    @Override
    public QLType visit(QLType type) {
        return null;
    }

    private QLType checkBinaryNumeric(BinaryExpression binaryExpression) {
        QLType leftType = binaryExpression.getLeft().accept(this);
        QLType rightType = binaryExpression.getRight().accept(this);

        if (leftType.isOf(rightType) && leftType instanceof QLNumberType) {
            return leftType;
        }
        throw new RuntimeException("Type mismatch");
    }

    private QLType checkBinaryComparable(BinaryExpression binaryExpression) {
        QLType leftType = binaryExpression.getLeft().accept(this);
        QLType rightType = binaryExpression.getRight().accept(this);

        if (leftType.isOf(rightType) && leftType instanceof QLComparableType) {
            return leftType;
        }
        throw new RuntimeException("Comparable Type mismatch");
    }
}

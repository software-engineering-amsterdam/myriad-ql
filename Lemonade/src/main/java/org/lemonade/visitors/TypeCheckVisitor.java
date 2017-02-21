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
    Map<String, QLType> symbolTable = new HashMap<>();

    public QLType visit(Block block) {
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

        assert !symbolTable.containsKey(identifier);
        symbolTable.put(identifier, type);
        return null;
    }

    public QLType visit(Conditional conditional) {

        for (Block block : conditional.getBlocks()) {
            block.accept(this);
        }
        QLType condition = conditional.getCondition().accept(this);
        if (condition instanceof QLBooleanType) {
            return condition;
        }
        throw new RuntimeException("Condition cannot be resolved because of type mismatch.");
    }

    public QLType visit(Expression expression) {
        return null;
    }

    public QLType visit(AndBinary andBinary) {
        return checkBoolean(andBinary);
    }

    public QLType visit(OrBinary orBinary) {
        return checkBoolean(orBinary);
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
        QLType expressionType = bangUnary.getExpression().accept(this);
        if (expressionType instanceof QLBooleanType) {
            return expressionType;
        }
        throw new RuntimeException("QLBoolean type mismatch in unary expression.");
    }

    public QLType visit(NegUnary negUnary) {
        QLType expressionType = negUnary.getExpression().accept(this);
        if (expressionType instanceof QLNumberType) {
            return expressionType;
        }
        throw new RuntimeException("QLNumeric type mismatch in unary expression.");
    }

    public QLType visit(BooleanLit booleanLit) {
        return booleanLit.getType();
    }

    public QLType visit(DecimalLit decimalLit) {
        return decimalLit.getType();
    }

    public QLType visit(IdentifierLit identifierLit) {
        if (symbolTable.containsKey(identifierLit.getValue())) {
            return symbolTable.get(identifierLit.getValue());
        }
        throw new RuntimeException("Symbol not found!");
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
        throw new RuntimeException("QLNumeric Type mismatch");
    }

    private QLType checkBinaryComparable(BinaryExpression binaryExpression) {
        QLType leftType = binaryExpression.getLeft().accept(this);
        QLType rightType = binaryExpression.getRight().accept(this);

        if (leftType.isOf(rightType) && leftType instanceof QLComparableType) {
            return leftType;
        }
        throw new RuntimeException("QLComparable Type mismatch");
    }

    private QLType checkBoolean(BinaryExpression binaryExpression) {
        QLType leftType = binaryExpression.getLeft().accept(this);
        QLType rightType = binaryExpression.getRight().accept(this);

        if (leftType.isOf(rightType) && leftType instanceof QLBooleanType) {
            return leftType;
        }
        throw new RuntimeException("QLBoolean Type mismatch");
    }
}

package org.lemonade.visitors;

import org.lemonade.nodes.*;
import org.lemonade.nodes.expressions.BinaryExpression;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.literal.*;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;
import org.lemonade.nodes.types.QLBooleanType;
import org.lemonade.nodes.types.QLNumberType;
import org.lemonade.nodes.types.QLType;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class TypeCheckVisitor implements ASTVisitor<QLType> {
    Map<String, QLType> symbolTable;

    public QLType visit(Body body) {
        return null;
    }

    public QLType visit(Form form) {
        symbolTable = new HashMap<>();
        for (Body body : form.getBodies()) {
            body.accept(this);
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

    public QLType visit(Conditional conditional){

        for (Body body : conditional.getBodies()) {
            body.accept(this);
        }

        QLType condition = conditional.getCondition().accept(this);

        if (!condition.isBoolean()) {
            throw new RuntimeException("Condition cannot be resolved because of type mismatch.");
        }
        return condition;
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

        if (!expressionType.isBoolean()) {
            throw new RuntimeException("QLBoolean type mismatch in unary expression.");
        }
        return expressionType;
    }

    public QLType visit(NegUnary negUnary) {
        QLType expressionType = negUnary.getExpression().accept(this);
        if (!expressionType.isNumeric()) {
            throw new RuntimeException("QLNumeric type mismatch in unary expression.");
        }
        return expressionType;
    }

    public QLType visit(BooleanLiteral booleanValue) {
        return booleanValue.getType();
    }

    public QLType visit(DecimalLiteral decimalValue) {
        return decimalValue.getType();
    }

    public QLType visit(MoneyLiteral moneyValue) {
        return moneyValue.getType();
    }

    public QLType visit(IdentifierLiteral identifierValue) {
        if (!symbolTable.containsKey(identifierValue.getValue())) {
            throw new RuntimeException("Symbol not found!");
        }
        return symbolTable.get(identifierValue.getValue());
    }

    public QLType visit(IntegerLiteral integerValue) {
        return integerValue.getType();
    }

    public QLType visit(StringLiteral stringValue) {
        return stringValue.getType();
    }

    @Override
    public QLType visit(QLType type) {
        return type;
    }

    @Override
    public QLType visit(ASTNode astNode) {
        return astNode.accept(this);
    }

    private QLType checkBinaryNumeric(BinaryExpression binaryExpression) {
        QLType leftType = binaryExpression.getLeft().accept(this);
        QLType rightType = binaryExpression.getRight().accept(this);

        if (!(leftType.isNumeric() && rightType.isNumeric())) {
            throw new RuntimeException("QLNumeric Type mismatch");
        }
        return QLNumberType.precedence((QLNumberType) leftType, (QLNumberType) rightType);
    }

    private QLType checkBinaryComparable(BinaryExpression binaryExpression) {
        QLType leftType = binaryExpression.getLeft().accept(this);
        QLType rightType = binaryExpression.getRight().accept(this);

        //Doesn't return it's own type because this can evaluate to a new type.
        // TODO!
        if (!(leftType.isOf(rightType.getClass()) && leftType.isComparable())) {
            throw new RuntimeException("QLComparable Type mismatch");
        }
        return new QLBooleanType();
    }

    private QLType checkBoolean(BinaryExpression binaryExpression) {
        QLType leftType = binaryExpression.getLeft().accept(this);
        QLType rightType = binaryExpression.getRight().accept(this);

        if (!(leftType.isOf(rightType.getClass()) && leftType.isBoolean())) {
            throw new RuntimeException("QLBoolean Type mismatch");
        }
        return leftType;
    }
}

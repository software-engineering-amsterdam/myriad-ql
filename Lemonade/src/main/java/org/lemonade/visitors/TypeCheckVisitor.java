package org.lemonade.visitors;

import org.lemonade.nodes.Body;
import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;
import org.lemonade.nodes.expressions.BinaryExpression;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.value.*;
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

    public QLType visit(Conditional conditional) {

        for (Body body : conditional.getBodies()) {
            body.accept(this);
        }

        QLType condition = conditional.getCondition().accept(this);
        if (condition.isBoolean()) {
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
        if (expressionType.isBoolean()) {
            return expressionType;
        }
        throw new RuntimeException("QLBoolean type mismatch in unary expression.");
    }

    public QLType visit(NegUnary negUnary) {
        QLType expressionType = negUnary.getExpression().accept(this);
        if (expressionType.isNumeric()) {
            return expressionType;
        }
        throw new RuntimeException("QLNumeric type mismatch in unary expression.");
    }

    public QLType visit(BooleanValue booleanValue) {
        return booleanValue.getType();
    }

    public QLType visit(DecimalValue decimalValue) {
        return decimalValue.getType();
    }

    public QLType visit(MoneyValue moneyValue) {
        return moneyValue.getType();
    }

    public QLType visit(IdentifierValue identifierValue) {
        if (symbolTable.containsKey(identifierValue.getValue())) {
            return symbolTable.get(identifierValue.getValue());
        }
        throw new RuntimeException("Symbol not found!");
    }

    public QLType visit(IntegerValue integerValue) {
        return integerValue.getType();
    }

    public QLType visit(StringValue stringValue) {
        return stringValue.getType();
    }

    @Override
    public QLType visit(QLType type) {
        return type;
    }

    private QLType checkBinaryNumeric(BinaryExpression binaryExpression) {
        QLType leftType = binaryExpression.getLeft().accept(this);
        QLType rightType = binaryExpression.getRight().accept(this);

        if (leftType.isOf(rightType) && leftType.isNumeric()) {
            return leftType;
        }
        throw new RuntimeException("QLNumeric Type mismatch");
    }

    private QLType checkBinaryComparable(BinaryExpression binaryExpression) {
        QLType leftType = binaryExpression.getLeft().accept(this);
        QLType rightType = binaryExpression.getRight().accept(this);

        //Doesn't return it's own type because this can evaluate to a new type.
        if (leftType.isOf(rightType) && leftType.isComparable()) {
            return new QLBooleanType();
        }
        throw new RuntimeException("QLComparable Type mismatch");
    }

    private QLType checkBoolean(BinaryExpression binaryExpression) {
        QLType leftType = binaryExpression.getLeft().accept(this);
        QLType rightType = binaryExpression.getRight().accept(this);

        if (leftType.isOf(rightType) && leftType.isBoolean()) {
            return leftType;
        }
        throw new RuntimeException("QLBoolean Type mismatch");
    }
}

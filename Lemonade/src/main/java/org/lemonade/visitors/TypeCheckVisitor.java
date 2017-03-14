package org.lemonade.visitors;

import org.lemonade.nodes.Body;
import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;
import org.lemonade.nodes.expressions.BinaryExpression;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.literal.*;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;
import org.lemonade.nodes.types.*;
import org.lemonade.visitors.interfaces.BaseVisitor;
import org.lemonade.visitors.interfaces.ExpressionVisitor;
import org.lemonade.visitors.interfaces.TypeVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class TypeCheckVisitor implements BaseVisitor<QLType>, ExpressionVisitor<QLType>, TypeVisitor<QLType> {
    Map<String, QLType> symbolTable;
    private List<String> errors;


    public List<String> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public QLType visit(Form form) {
        symbolTable = new HashMap<>();
        errors = new ArrayList<>();
        for (Body body : form.getBodies()) {
            body.accept(this);
        }
        return null;
    }

    public QLType visit(Question question) {
        IdentifierLiteral identifier = question.getIdentifier();
        QLType type = question.getType();

        if (symbolTable.containsKey(identifier.getValue())) {
            errors.add("QLQuestion identifier: " + identifier + " found at " + question.getPosition() + " already declared.");
        }
        symbolTable.put(identifier.getValue(), type);
        return null;
    }

    public QLType visit(Conditional conditional) {

        for (Body body : conditional.getBodies()) {
            body.accept(this);
        }

        QLType condition = conditional.getCondition().accept(this);

        if (!condition.isBoolean()) {
            errors.add("Condition at " + conditional.getPosition() + " cannot be resolved because conditional resolves to: " + condition + ", Boolean expected.");
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
            errors.add("QLBoolean expected at " + bangUnary.getPosition() + ", got " + expressionType + ".");
        }
        return expressionType;
    }

    public QLType visit(NegUnary negUnary) {
        QLType expressionType = negUnary.getExpression().accept(this);
        if (!expressionType.isNumeric()) {
            errors.add("QLNumeric expected at " + negUnary.getPosition() + ", got " + expressionType + ".");
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
            errors.add("Identifier " + identifierValue.getValue() + " at " + identifierValue.getPosition() + " not found!");
        }
        return symbolTable.get(identifierValue.getValue());
    }

    public QLType visit(IntegerLiteral integerValue) {
        return integerValue.getType();
    }

    public QLType visit(StringLiteral stringValue) {
        return stringValue.getType();
    }

    public QLType visit(DateLiteral dateLiteral) {
        return dateLiteral.getType();
    }

    private QLType checkBinaryNumeric(BinaryExpression binaryExpression) {
        QLType leftType = binaryExpression.getLeft().accept(this);
        QLType rightType = binaryExpression.getRight().accept(this);

        if (!(leftType.isNumeric() && rightType.isNumeric())) {
            errors.add("QLNumeric type mismatch at " + binaryExpression.getPosition() + ", " + leftType + " with " + rightType);
        }
        return QLNumberType.precedence((QLNumberType) leftType, (QLNumberType) rightType);
    }

    private QLType checkBinaryComparable(BinaryExpression binaryExpression) {
        QLType leftType = binaryExpression.getLeft().accept(this);
        QLType rightType = binaryExpression.getRight().accept(this);

        //Doesn't return it's own type because this can evaluate to a new type.
        // TODO!
        if (!(leftType.isOf(rightType.getClass()) && leftType.isComparable())) {
            errors.add("QLComparable type mismatch at " + binaryExpression.getPosition() + ", " + leftType + " with " + rightType);
        }
        return new QLBooleanType();
    }

    private QLType checkBoolean(BinaryExpression binaryExpression) {
        QLType leftType = binaryExpression.getLeft().accept(this);
        QLType rightType = binaryExpression.getRight().accept(this);

        if (!(leftType.isOf(rightType.getClass()) && leftType.isBoolean())) {
            errors.add("QLBooleans expected at " + binaryExpression.getPosition() + ", got " + leftType + " and " + rightType);
        }
        return leftType;
    }

    @Override
    public QLType visit(final QLIntegerType qlIntegerType) {
        return qlIntegerType;
    }

    @Override
    public QLType visit(final QLBooleanType qlBooleanType) {
        return qlBooleanType;
    }

    @Override
    public QLType visit(final QLDateType qlDateType) {
        return qlDateType;
    }

    @Override
    public QLType visit(final QLDecimalType qlDecimalType) {
        return qlDecimalType;
    }

    @Override
    public QLType visit(final QLMoneyType qlMoneyType) {
        return qlMoneyType;
    }

    @Override
    public QLType visit(final QLStringType qlStringType) {
        return qlStringType;
    }
}

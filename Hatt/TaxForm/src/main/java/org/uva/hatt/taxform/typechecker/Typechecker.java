package org.uva.hatt.taxform.typechecker;

import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.expressions.BinaryExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.binary.*;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.items.*;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.String;
import org.uva.hatt.taxform.ast.nodes.expressions.ExpressionVisitor;
import org.uva.hatt.taxform.ast.nodes.FormVisitor;
import org.uva.hatt.taxform.typechecker.messages.error.*;
import org.uva.hatt.taxform.typechecker.messages.Messages;
import org.uva.hatt.taxform.typechecker.messages.warning.DuplicateLabel;

import java.util.*;

public class TypeChecker implements FormVisitor<Void>, ItemVisitor<Void>, ExpressionVisitor<ValueType> {

    private final Messages messages;
    private final List<java.lang.String> questions = new ArrayList<>();
    private final Map<java.lang.String, IdentifierInput> declarations = new HashMap<>();

    TypeChecker(Messages messages) {
        this.messages = messages;
    }

    @Override
    public Void visit(Form node) {
        for (Item item : node.getQuestions()) {
            item.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(Question node) {
        if (declarations.containsKey(node.getIdentifier())) {
            messages.addError(new DuplicateDeclaration(node.getLineNumber(), node.getIdentifier()));
        }

        if (questions.contains(node.getLabel().toLowerCase())) {
            messages.addWarning(new DuplicateLabel(node.getLineNumber(), node.getLabel()));
        }

        declarations.put(node.getIdentifier(), new IdentifierInput(node.getType()));
        questions.add(node.getLabel().toLowerCase());

        return null;
    }

    @Override
    public Void visit(ComputedQuestion computedQuestion) {
        if (declarations.containsKey(computedQuestion.getIdentifier())) {
            messages.addError(new DuplicateDeclaration(computedQuestion.getLineNumber(), computedQuestion.getIdentifier()));
        }

        if (questions.contains(computedQuestion.getLabel().toLowerCase())) {
            messages.addWarning(new DuplicateLabel(computedQuestion.getLineNumber(), computedQuestion.getLabel()));
        }

        declarations.put(computedQuestion.getIdentifier(), new IdentifierInput(computedQuestion.getType()));
        questions.add(computedQuestion.getLabel().toLowerCase());

        Expression expression = computedQuestion.getComputedValue();
        expression.accept(this);

        return null;
    }

    @Override
    public Void visit(IfThen node) {
        Expression expression = node.getCondition();
        validateIfCondition(expression, node.getLineNumber());

        node.getCondition().accept(this);

        node.getThenStatements().forEach(item -> item.accept(this));

        return null;
    }

    @Override
    public Void visit(IfThenElse node) {
        Expression expression = node.getCondition();
        validateIfCondition(expression, node.getLineNumber());

        node.getCondition().accept(this);

        node.getThenStatements().forEach(item -> item.accept(this));
        node.getElseStatements().forEach(item -> item.accept(this));

        return null;
    }

    private void validateIfCondition(Expression expression, int line) {
        ValueType type = expression.accept(this);

        if (!type.isBoolean()) {
            messages.addError(new TypeMismatch(line, type.name()));
        }
    }

    @Override
    public ValueType visit(GroupedExpression node) {
        return null;
    }

    @Override
    public ValueType visit(Identifier identifier) {
        if (declarations.keySet().contains(identifier.getValue())) {
            return declarations.get(identifier.getValue()).getType();
        }

        messages.addError(new UndefinedReference(identifier.getLineNumber(), identifier.getValue()));
        return new Unknown();
    }

    @Override
    public ValueType visit(StringerLiteral stringerLiteral) {
        return new String(0);
    }

    @Override
    public ValueType visit(IntegerLiteral integerLiteral) {
        return new Integer(0);
    }

    @Override
    public ValueType visit(BooleanLiteral booleanLiteral) {
        return new Boolean(0);
    }

    @Override
    public ValueType visit(Expression expression) {
        return null;
    }

    @Override
    public ValueType visit(Addition addition) {
        return validateNumericType(addition, "Addition");
    }

    @Override
    public ValueType visit(Division division) {
        return validateNumericType(division, "Division");
    }

    @Override
    public ValueType visit(Equal equal) {
        ValueType expectedType = new String(0);

        if (!validateBinaryExpression(equal, expectedType).name().equals(new Unknown().name())) {
            return new Boolean(0);
        }

        ValueType type = validateNumericType(equal, "Equal");

        if (type.name().equals(new Unknown().name())) {
            return type;
        }
        return new Boolean(0);
    }

    @Override
    public ValueType visit(GreaterThan greaterThan) {
        ValueType type = validateNumericType(greaterThan, "Greater than");

        if (type.name().equals(new Unknown().name())) {
            return type;
        }
        return new Boolean(0);
    }

    @Override
    public ValueType visit(GreaterThanOrEqual greaterThanOrEqual) {
        ValueType type = validateNumericType(greaterThanOrEqual, "Greater than or equal");

        if (type.name().equals(new Unknown().name())) {
            return type;
        }
        return new Boolean(0);
    }

    @Override
    public ValueType visit(LessThan lessThan) {
        ValueType type = validateNumericType(lessThan, "Less than");

        if (type.name().equals(new Unknown().name())) {
            return type;
        }
        return new Boolean(0);
    }

    @Override
    public ValueType visit(LessThanOrEqual lessThanOrEqual) {
        ValueType type = validateNumericType(lessThanOrEqual, "Less than or equal");

        if (type.name().equals(new Unknown().name())) {
            return type;
        }
        return new Boolean(0);
    }

    @Override
    public ValueType visit(LogicalAnd logicalAnd) {
        ValueType expectedType = new Boolean(0);

        if (!validateBinaryExpression(logicalAnd, expectedType).name().equals(new Unknown().name())) {
            return expectedType;
        }

        messages.addError(new InvalidOperandsTypeToOperator(logicalAnd.getLineNumber(), "Logical AND"));
        return new Unknown();
    }

    @Override
    public ValueType visit(LogicalOr logicalOr) {
        ValueType expectedType = new Boolean(0);

        if (!validateBinaryExpression(logicalOr, expectedType).name().equals(new Unknown().name())) {
            return expectedType;
        }

        messages.addError(new InvalidOperandsTypeToOperator(logicalOr.getLineNumber(), "Logical OR"));
        return new Unknown();
    }

    @Override
    public ValueType visit(Multiplication multiplication) {
        return validateNumericType(multiplication, "Multiplication");
    }

    @Override
    public ValueType visit(NotEqual notEqual) {
        ValueType expectedType = new String(0);

        if (!validateBinaryExpression(notEqual, expectedType).name().equals(new Unknown().name())) {
            return new Boolean(0);
        }

        ValueType type = validateNumericType(notEqual, "Not equal");

        if (type.name().equals(new Unknown().name())) {
            return type;
        }
        return new Boolean(0);
    }

    @Override
    public ValueType visit(Subtraction subtraction) {
        return validateNumericType(subtraction, "Subtraction");
    }

    private ValueType validateNumericType(BinaryExpression expression, java.lang.String operationName) {

        if (!validateBinaryExpression(expression, new Integer(0)).name().equals(new Unknown().name())) {
            return new Integer(0);
        }
        if (!validateBinaryExpression(expression, new Money(0)).name().equals(new Unknown().name())) {
            return new Money(0);
        }

        messages.addError(new InvalidOperandsTypeToOperator(expression.getLineNumber(), operationName));

        return new Unknown();
    }

    private ValueType validateBinaryExpression(BinaryExpression expression, ValueType expectedType) {
        ValueType leftType = expression.getLhs().accept(this);
        ValueType rightType = expression.getRhs().accept(this);

        if (leftType.name().equals(rightType.name())) {
            if (leftType.name().equals(expectedType.name())) {
                return leftType;
            }
        }

        return new Unknown();
    }
}

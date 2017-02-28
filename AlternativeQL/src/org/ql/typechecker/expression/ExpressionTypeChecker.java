package org.ql.typechecker.expression;

import org.ql.ast.expression.BinaryExpression;
import org.ql.ast.expression.ExpressionVisitor;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.ast.type.*;
import org.ql.symbol_table.SymbolTable;
import org.ql.typechecker.messages.MessageBag;

public class ExpressionTypeChecker implements ExpressionVisitor<Type, MessageBag> {

    private final SymbolTable symbolTable;

    public ExpressionTypeChecker(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public Type visit(Negation node, MessageBag messages) {
        Type innerExpressionType = node.getExpression().accept(this, messages);

        if (!innerExpressionType.isBoolean()) {
            messages.addError(new TypeMismatchException(new BooleanType(), innerExpressionType));
            return new UndefinedType();
        }

        return new BooleanType();
    }

    @Override
    public Type visit(Product node, MessageBag messages) {
        return checkTypeMismatch(node, messages);
    }

    @Override
    public Type visit(Increment node, MessageBag messages) {
        Type innerExpressionType = node.getExpression().accept(this, messages);

        if (!(innerExpressionType.isNumeric())) {
            messages.addError(new NumberExpectedException(innerExpressionType));
            return new UndefinedType();
        }

        return innerExpressionType;
    }

    @Override
    public Type visit(Subtraction node, MessageBag messages) {
        return checkTypeMismatch(node, messages);
    }

    @Override
    public BooleanType visit(NotEqual node, MessageBag messages) {
        checkTypeMismatch(node, messages);

        return new BooleanType();
    }

    @Override
    public BooleanType visit(LogicalAnd node, MessageBag messages) {
        checkTypeMismatch(node, messages);

        return new BooleanType();
    }

    @Override
    public BooleanType visit(LowerThan node, MessageBag messages) {
        checkTypeMismatch(node, messages);

        return new BooleanType();
    }

    @Override
    public BooleanType visit(GreaterThanOrEqual node, MessageBag messages) {
        checkTypeMismatch(node, messages);

        return new BooleanType();
    }

    @Override
    public Type visit(Division node, MessageBag messages) {
        return checkTypeMismatch(node, messages);
    }

    @Override
    public Type visit(Parameter node, MessageBag messages) {
        if (!symbolTable.has(node.getId())) {
            messages.addError(new UndefinedIdentifierException(node.getId()));
            return new UndefinedType();
        }

        return symbolTable.lookup(node.getId());
    }

    @Override
    public Type visit(Group node, MessageBag messages) {
        return node.getExpression().accept(this, messages);
    }

    @Override
    public Type visit(Addition node, MessageBag messages) {
        return checkTypeMismatch(node, messages);
    }

    @Override
    public BooleanType visit(GreaterThan node, MessageBag messages) {
        checkTypeMismatch(node, messages);

        return new BooleanType();
    }

    @Override
    public Type visit(Decrement node, MessageBag messages) {
        Type innerExpressionType = node.getExpression().accept(this, messages);

        if (!(innerExpressionType.isNumeric())) {
            messages.addError(new NumberExpectedException(innerExpressionType));
            return new UndefinedType();
        }

        return innerExpressionType;
    }

    @Override
    public BooleanType visit(Equals node, MessageBag messages) {
        checkTypeMismatch(node, messages);

        return new BooleanType();
    }

    @Override
    public BooleanType visit(LowerThanOrEqual node, MessageBag messages) {
        checkTypeMismatch(node, messages);

        return new BooleanType();
    }

    @Override
    public BooleanType visit(LogicalOr node, MessageBag messages) {
        checkTypeMismatch(node, messages);

        return new BooleanType();
    }

    @Override
    public BooleanType visit(BooleanLiteral node, MessageBag messages) {
        return new BooleanType();
    }

    @Override
    public FloatType visit(DecimalLiteral node, MessageBag messages) {
        return new FloatType();
    }

    @Override
    public IntegerType visit(IntegerLiteral node, MessageBag messages) {
        return new IntegerType();
    }

    @Override
    public StringType visit(StringLiteral node, MessageBag messages) {
        return new StringType();
    }

    private Type checkTypeMismatch(BinaryExpression node, MessageBag messages) {
        Type leftType = node.getLeft().accept(this, messages);
        Type rightType = node.getRight().accept(this, messages);

        if (!leftType.equals(rightType)) {
            messages.addError(new TypeMismatchException(leftType, rightType));

            return new UndefinedType();
        }

        return leftType;
    }
}

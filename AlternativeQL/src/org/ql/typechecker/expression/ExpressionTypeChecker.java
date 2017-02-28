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

public class ExpressionTypeChecker implements ExpressionVisitor<Type> {

    private final SymbolTable symbolTable;

    public ExpressionTypeChecker(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public Type visit(Negation node) throws Throwable {
        Type innerExpressionType = node.getExpression().accept(this);

        if (!(innerExpressionType instanceof BooleanType)) {
            // TODO Do not use exceptions as error management
            throw new TypeMismatchException(new BooleanType(), innerExpressionType);
        }

        // TODO this is wrong
        return innerExpressionType;
    }

    @Override
    public Type visit(Product node) throws Throwable {
        return checkTypeMismatch(node);
    }

    @Override
    public Type visit(Increment node) throws Throwable {
        Type innerExpressionType = node.getExpression().accept(this);

        if (!(innerExpressionType instanceof NumberType)) {
            throw new NumberExpectedException(innerExpressionType);
        }

        return innerExpressionType;
    }

    @Override
    public Type visit(Subtraction node) throws Throwable {
        return checkTypeMismatch(node);
    }

    @Override
    public Type visit(NotEqual node) throws Throwable {
        checkTypeMismatch(node);

        return (Type) new BooleanType().setSourceLocation(node.getSourceLocation());
    }

    @Override
    public Type visit(LogicalAnd node) throws Throwable {
        checkTypeMismatch(node);

        return (Type) new BooleanType().setSourceLocation(node.getSourceLocation());
    }

    @Override
    public Type visit(LowerThan node) throws Throwable {
        checkTypeMismatch(node);

        return (Type) new BooleanType().setSourceLocation(node.getSourceLocation());
    }

    @Override
    public Type visit(GreaterThanOrEqual node) throws Throwable {
        checkTypeMismatch(node);

        return (Type) new BooleanType().setSourceLocation(node.getSourceLocation());
    }

    @Override
    public Type visit(Division node) throws Throwable {
        return checkTypeMismatch(node);
    }

    @Override
    public Type visit(Parameter node) throws Throwable {
        if (!symbolTable.has(node.getId())) {
            throw new UndefinedIdentifierException(node.getId());
        }

        return symbolTable.lookup(node.getId());
    }

    @Override
    public Type visit(Group node) throws Throwable {
        return node.getExpression().accept(this);
    }

    @Override
    public Type visit(Addition node) throws Throwable {
        return checkTypeMismatch(node);
    }

    @Override
    public Type visit(GreaterThan node) throws Throwable {
        checkTypeMismatch(node);

        return (Type) new BooleanType().setSourceLocation(node.getSourceLocation());
    }

    @Override
    public Type visit(Decrement node) throws Throwable {
        Type innerExpressionType = node.getExpression().accept(this);

        // TODO make this work
        if (!(innerExpressionType.isNumeric())) {
            throw new NumberExpectedException(innerExpressionType);
        }

        return innerExpressionType;
    }

    @Override
    public Type visit(Equals node) throws Throwable {
        checkTypeMismatch(node);

        // TODO remove metadata set here
        return (Type) new BooleanType().setSourceLocation(node.getSourceLocation());
    }

    @Override
    public Type visit(LowerThanOrEqual node) throws Throwable {
        checkTypeMismatch(node);

        return (Type) new BooleanType().setSourceLocation(node.getSourceLocation());
    }

    @Override
    public Type visit(LogicalOr node) throws Throwable {
        checkTypeMismatch(node);

        return (Type) new BooleanType().setSourceLocation(node.getSourceLocation());
    }

    @Override
    public Type visit(BooleanLiteral node) {
        return (Type) new BooleanType().setSourceLocation(node.getSourceLocation());
    }

    @Override
    public Type visit(DecimalLiteral node) {
        return (Type) new FloatType().setSourceLocation(node.getSourceLocation());
    }

    @Override
    public Type visit(IntegerLiteral node) {
        return (Type) new IntegerType().setSourceLocation(node.getSourceLocation());
    }

    @Override
    public Type visit(StringLiteral node) {
        return (Type) new StringType().setSourceLocation(node.getSourceLocation());
    }

    private Type checkTypeMismatch(BinaryExpression node) throws Throwable {
        Type leftType = node.getLeft().accept(this);
        Type rightType = node.getRight().accept(this);

        if (!leftType.equals(rightType)) {
            throw new TypeMismatchException(leftType, rightType);
        }

        return leftType;
    }
}

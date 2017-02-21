package org.ql.typechecker.expression;

import org.ql.ast.Expression;
import org.ql.ast.expression.BinaryExpression;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.ExpressionVisitor;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.ast.type.*;
import org.ql.typechecker.exception.TypeMismatchException;
import org.ql.typechecker.exception.UnexpectedTypeException;
import org.ql.typechecker.exception.UndefinedIdentifierException;

public class ExpressionTypeChecker implements ExpressionVisitor<Type> {

    private final SymbolTable symbolTable;

    public ExpressionTypeChecker(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public Type visit(Negation node) throws Throwable {
        Type innerExpressionType = node.getExpression().accept(this);

        if (!(innerExpressionType instanceof BooleanType)) {

            throw new TypeMismatchException(new BooleanType(), innerExpressionType);
        }

        return innerExpressionType;
    }

    @Override
    public Type visit(Product node) throws Throwable {
        return checkTypeMismatch(node);
    }

    @Override
    public Type visit(Increment node) throws Throwable {
        Type innerExpressionType = node.getExpression().accept(this);

        if(!(innerExpressionType instanceof NumberType)) {
            throw new UnexpectedTypeException(innerExpressionType);
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

        return (Type) new BooleanType().setMetadata(node.getMetadata());
    }

    @Override
    public Type visit(LogicalAnd node) throws Throwable {
        checkTypeMismatch(node);

        return (Type) new BooleanType().setMetadata(node.getMetadata());
    }

    @Override
    public Type visit(LowerThan node) throws Throwable {
        checkTypeMismatch(node);

        return (Type) new BooleanType().setMetadata(node.getMetadata());
    }

    @Override
    public Type visit(GreaterThanOrEqual node) throws Throwable {
        checkTypeMismatch(node);

        return (Type) new BooleanType().setMetadata(node.getMetadata());
    }

    @Override
    public Type visit(Division node) throws Throwable {
        return checkTypeMismatch(node);
    }

    @Override
    public Type visit(Parameter node) throws Throwable {
        if (!symbolTable.containsKey(node.getId())) {
            throw new UndefinedIdentifierException(node.getId());
        }

        return symbolTable.get(node.getId());
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

        return (Type) new BooleanType().setMetadata(node.getMetadata());
    }

    @Override
    public Type visit(Decrement node) throws Throwable {
        Type innerExpressionType = node.getExpression().accept(this);

        if (!(innerExpressionType instanceof NumberType)) {
            throw new UnexpectedTypeException(innerExpressionType);
        }

        return innerExpressionType;
    }

    @Override
    public Type visit(Equals node) throws Throwable {
        checkTypeMismatch(node);

        return (Type) new BooleanType().setMetadata(node.getMetadata());
    }

    @Override
    public Type visit(LowerThanOrEqual node) throws Throwable {
        checkTypeMismatch(node);

        return (Type) new BooleanType().setMetadata(node.getMetadata());
    }

    @Override
    public Type visit(LogicalOr node) throws Throwable {
        checkTypeMismatch(node);

        return (Type) new BooleanType().setMetadata(node.getMetadata());
    }

    @Override
    public Type visit(BooleanLiteral node) {
        return (Type) new BooleanType().setMetadata(node.getMetadata());
    }

    @Override
    public Type visit(DecimalLiteral node) {
        return (Type) new FloatType().setMetadata(node.getMetadata());
    }

    @Override
    public Type visit(IntegerLiteral node) {
        return (Type) new IntegerType().setMetadata(node.getMetadata());
    }

    @Override
    public Type visit(StringLiteral node) {
        return (Type) new StringType().setMetadata(node.getMetadata());
    }

    private Type checkTypeMismatch(BinaryExpression node) throws Throwable {
        Type leftType = node.getLeft().accept(this);
        Type rightType = node.getRight().accept(this);

        if (!leftType.getClass().equals(rightType.getClass())) {
            throw new TypeMismatchException(leftType, rightType);
        }

        return leftType;
    }
}

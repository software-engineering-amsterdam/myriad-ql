package org.ql.typechecker.expression;

import org.ql.ast.Identifier;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.Visitor;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.ast.type.Type;
import org.ql.typechecker.messages.Message;
import org.ql.typechecker.messages.TypeMismatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TypeCheckVisitor implements Visitor<Type> {

    private final Map<Identifier, Type> definitions;
    private final List<Message> errors = new ArrayList<>();

    public TypeCheckVisitor(Map<Identifier, Type> definitions) {
        this.definitions = definitions;
    }

    @Override
    public Type visit(Negation node) {
        return node.getExpression().accept(this);
    }

    @Override
    public Type visit(Product node) {
        return checkBinaryExpression(node);
    }

    private Type checkBinaryExpression(Product node) {
        Type leftMultiplierType = node.getLeft().accept(this);
        Type rightMultiplierType = node.getRight().accept(this);

        if (!leftMultiplierType.getClass().equals(rightMultiplierType.getClass())) {
            errors.add(new TypeMismatch(node.getLeft(), node.getRight()));
        }

        return leftMultiplierType;
    }

    @Override
    public Type visit(Increment node) {
        return node.getExpression().accept(this);
    }

    @Override
    public Type visit(Subtraction node) {
        return null;
    }

    @Override
    public Type visit(NotEqual node) {
        return null;
    }

    @Override
    public Type visit(LogicalAnd node) {
        return null;
    }

    @Override
    public Type visit(LowerThan node) {
        return null;
    }

    @Override
    public Type visit(GreaterThanOrEqual node) {
        return null;
    }

    @Override
    public Type visit(Division node) {
        return null;
    }

    @Override
    public Type visit(Parameter node) {
        return null;
    }

    @Override
    public Type visit(Group node) {
        return null;
    }

    @Override
    public Type visit(Addition node) {
        return null;
    }

    @Override
    public Type visit(GreaterThan node) {
        return null;
    }

    @Override
    public Type visit(Decrement node) {
        return null;
    }

    @Override
    public Type visit(Equals node) {
        return null;
    }

    @Override
    public Type visit(LowerThanOrEqual node) {
        return null;
    }

    @Override
    public Type visit(LogicalOr node) {
        return null;
    }

    @Override
    public Type visit(BooleanLiteral node) {
        return null;
    }

    @Override
    public Type visit(DecimalLiteral node) {
        return null;
    }

    @Override
    public Type visit(IntegerLiteral node) {
        return null;
    }

    @Override
    public Type visit(StringLiteral node) {
        return null;
    }
}

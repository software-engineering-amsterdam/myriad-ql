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

import java.util.Map;

public class TypeCheckVisitor implements Visitor<Type> {

    private final Map<Identifier, Type> definitions;

    public TypeCheckVisitor(Map<Identifier, Type> definitions) {
        this.definitions = definitions;
    }

    @Override
    public Type visit(Negation node) {

        node.accept(this);

        return null;
    }

    @Override
    public Type visit(Product node) {
        return null;
    }

    @Override
    public Type visit(Increment node) {
        return null;
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

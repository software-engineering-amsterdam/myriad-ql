package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.core.QLErrorLogger;
import com.matthewchapman.ql.validation.visitor.AbstractQLVisitor;

import java.util.Map;

/**
 * Created by matt on 16/03/2017.
 */
public class QLConditionChecker extends AbstractQLVisitor<Void> {

    private Map<String, Type> typeTable;
    private QLErrorLogger logger;

    public QLErrorLogger checkConditionals(Form form, Map<String, Type> typeTable) {
        this.typeTable = typeTable;
        this.logger = new QLErrorLogger();

        for(Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }

        return logger;
    }

    @Override
    public Void visit(IfStatement ifStatement, String context) {

        ifStatement.getCondition().accept(this, "If Condition");
        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement, String context) {

        ifElseStatement.getCondition().accept(this, "If Condition");
        return null;
    }

    @Override
    public Void visit(ParameterGroup parameterGroup, String context) {
        parameterGroup.getExpression().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Addition addition, String context) {
        addition.getLeft().accept(this, context);
        addition.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Subtraction subtraction, String context) {
        subtraction.getLeft().accept(this, context);
        subtraction.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Multiplication multiplication, String context) {
        multiplication.getLeft().accept(this, context);
        multiplication.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Division division, String context) {
        division.getLeft().accept(this, context);
        division.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(NotEqual notEqual, String context) {
        notEqual.getLeft().accept(this, context);
        notEqual.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Equal equal, String context) {
        equal.getLeft().accept(this, context);
        equal.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(GreaterThan greaterThan, String context) {
        greaterThan.getLeft().accept(this, context);
        greaterThan.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(GreaterThanEqualTo greaterThanEqualTo, String context) {
        greaterThanEqualTo.getLeft().accept(this, context);
        greaterThanEqualTo.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(LessThan lessThan, String context) {
        lessThan.getLeft().accept(this, context);
        lessThan.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(LessThanEqualTo lessThanEqualTo, String context) {
        lessThanEqualTo.getLeft().accept(this, context);
        lessThanEqualTo.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(LogicalAnd logicalAnd, String context) {
        logicalAnd.getLeft().accept(this, context);
        logicalAnd.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(LogicalOr logicalOr, String context) {
        logicalOr.getLeft().accept(this, context);
        logicalOr.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Negation negation, String context) {
        negation.getExpression().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Parameter parameter, String context) {
        if(!typeTable.containsKey(parameter.getID())) {
            logger.addError(parameter.getLine(), parameter.getColumn(), "If Statement", "Referenced parameter " + parameter.getID() + " does not exist");
        }

            return null;
    }
}

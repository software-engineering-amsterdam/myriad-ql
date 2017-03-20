package com.matthewchapman.ql.environment;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.literal.BooleanLiteral;
import com.matthewchapman.ql.ast.expression.literal.IntegerLiteral;
import com.matthewchapman.ql.ast.expression.literal.StringLiteral;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.environment.datastores.ConditionTable;
import com.matthewchapman.ql.environment.datastores.ExpressionTable;
import com.matthewchapman.ql.environment.datastores.QuestionTable;
import com.matthewchapman.ql.validation.visitors.ExpressionVisitor;
import com.matthewchapman.ql.validation.visitors.StatementVisitor;

/**
 * Created by matt on 20/03/2017.
 */
public class FormEnvironment implements StatementVisitor<Void, String>, ExpressionVisitor<Void, String> {

    private final ExpressionTable expressions;
    private final ConditionTable conditions;
    private final QuestionTable questions;

    public FormEnvironment(Form ast) {
        expressions = new ExpressionTable();
        conditions = new ConditionTable();
        questions = new QuestionTable();

        for(Statement statement : ast.getStatements()) {
            statement.accept(this, ast.getName());
        }
    }

    @Override
    public Void visit(Question question, String context) {
        return null;
    }

    @Override
    public Void visit(CalculatedQuestion calculatedQuestion, String context) {
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement, String context) {
        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement, String context) {
        return null;
    }

    @Override
    public Void visit(Addition addition, String context) {
        return null;
    }

    @Override
    public Void visit(Division division, String context) {
        return null;
    }

    @Override
    public Void visit(Equal equal, String context) {
        return null;
    }

    @Override
    public Void visit(GreaterThan greaterThan, String context) {
        return null;
    }

    @Override
    public Void visit(GreaterThanEqualTo greaterThanEqualTo, String context) {
        return null;
    }

    @Override
    public Void visit(LessThan lessThan, String context) {
        return null;
    }

    @Override
    public Void visit(LessThanEqualTo lessThanEqualTo, String context) {
        return null;
    }

    @Override
    public Void visit(LogicalAnd logicalAnd, String context) {
        return null;
    }

    @Override
    public Void visit(LogicalOr logicalOr, String context) {
        return null;
    }

    @Override
    public Void visit(Multiplication multiplication, String context) {
        return null;
    }

    @Override
    public Void visit(NotEqual notEqual, String context) {
        return null;
    }

    @Override
    public Void visit(Subtraction subtraction, String context) {
        return null;
    }

    @Override
    public Void visit(Negation negation, String context) {
        return null;
    }

    @Override
    public Void visit(Parameter parameter, String context) {
        return null;
    }

    @Override
    public Void visit(ParameterGroup parameterGroup, String context) {
        return null;
    }

    @Override
    public Void visit(StringLiteral stringLiteral, String context) {
        return null;
    }

    @Override
    public Void visit(IntegerLiteral integerLiteral, String context) {
        return null;
    }

    @Override
    public Void visit(BooleanLiteral booleanLiteral, String context) {
        return null;
    }
}

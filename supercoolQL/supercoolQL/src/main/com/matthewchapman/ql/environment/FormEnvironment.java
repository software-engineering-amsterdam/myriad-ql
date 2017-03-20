package com.matthewchapman.ql.environment;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.atomic.type.BooleanType;
import com.matthewchapman.ql.ast.atomic.type.ErrorType;
import com.matthewchapman.ql.ast.atomic.type.IntegerType;
import com.matthewchapman.ql.ast.atomic.type.StringType;
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
import com.matthewchapman.ql.environment.datastores.ValueTable;
import com.matthewchapman.ql.gui.values.BooleanValue;
import com.matthewchapman.ql.gui.values.IntegerValue;
import com.matthewchapman.ql.gui.values.NullValue;
import com.matthewchapman.ql.gui.values.StringValue;
import com.matthewchapman.ql.validation.visitors.ExpressionVisitor;
import com.matthewchapman.ql.validation.visitors.StatementVisitor;
import com.matthewchapman.ql.validation.visitors.TypeVisitor;

/**
 * Created by matt on 20/03/2017.
 */
public class FormEnvironment implements StatementVisitor<Void, String>, ExpressionVisitor<Void, String>, TypeVisitor<Void, String> {

    private final ExpressionTable expressions;
    private final ConditionTable conditions;
    private final QuestionTable questions;
    private final ValueTable values;

    public FormEnvironment(Form ast) {
        expressions = new ExpressionTable();
        conditions = new ConditionTable();
        questions = new QuestionTable();
        values = new ValueTable();

        for(Statement statement : ast.getStatements()) {
            statement.accept(this, ast.getName());
        }
    }

    @Override
    public Void visit(Question question, String context) {
        questions.addQuestion(question.getName(), question);
        question.getType().accept(this, question.getName());
        return null;
    }

    @Override
    public Void visit(CalculatedQuestion calculatedQuestion, String context) {
        questions.addQuestion(calculatedQuestion.getName(), calculatedQuestion);
        expressions.addExpression(calculatedQuestion.getName(), calculatedQuestion.getCalculation());
        calculatedQuestion.getType().accept(this, context);
        calculatedQuestion.getCalculation().accept(this, context);

        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement, String context) {
        for (Statement statement : ifStatement.getIfCaseStatements()) {

        }
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

    @Override
    public Void visit(BooleanType booleanType, String context) {
        values.addValue(context, new BooleanValue(false));
        return null;
    }

    @Override
    public Void visit(IntegerType integerType, String context) {
        values.addValue(context, new IntegerValue(0));
        return null;
    }

    @Override
    public Void visit(StringType stringType, String context) {
        values.addValue(context, new StringValue(""));
        return null;
    }

    @Override
    public Void visit(ErrorType errorType, String context) {
        values.addValue(context, new NullValue());
        return null;
    }
}

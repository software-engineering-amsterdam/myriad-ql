package com.matthewchapman.ql.environment;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.atomic.type.BooleanType;
import com.matthewchapman.ql.ast.atomic.type.ErrorType;
import com.matthewchapman.ql.ast.atomic.type.IntegerType;
import com.matthewchapman.ql.ast.atomic.type.StringType;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.environment.datastores.ConditionTable;
import com.matthewchapman.ql.environment.datastores.ExpressionTable;
import com.matthewchapman.ql.environment.datastores.QuestionTable;
import com.matthewchapman.ql.environment.datastores.ValueTable;
import com.matthewchapman.ql.environment.values.BooleanValue;
import com.matthewchapman.ql.environment.values.IntegerValue;
import com.matthewchapman.ql.environment.values.NullValue;
import com.matthewchapman.ql.environment.values.StringValue;
import com.matthewchapman.ql.visitors.StatementVisitor;
import com.matthewchapman.ql.visitors.TypeVisitor;

/**
 * Created by matt on 22/03/2017.
 */
public class FormEnvironmentFactory implements StatementVisitor<Void, String>, TypeVisitor<Void, String> {

    private ExpressionTable expressions;
    private ConditionTable conditions;
    private QuestionTable questions;
    private ValueTable values;

    public FormEnvironment getFormEnvironment(Form ast) {

        expressions = new ExpressionTable();
        conditions = new ConditionTable();
        questions = new QuestionTable();
        values = new ValueTable();

        for (Statement statement : ast.getStatements()) {
            statement.accept(this, null);
        }

        return new FormEnvironment(expressions, conditions, questions, values, ast.getName());
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
        calculatedQuestion.getType().accept(this, calculatedQuestion.getName());

        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement, String context) {
        for (Statement statement : ifStatement.getIfCaseStatements()) {
            conditions.addCondition(statement.getName(), ifStatement.getCondition());
            statement.accept(this, context);
        }
        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement, String context) {
        for (Statement statement : ifElseStatement.getIfCaseStatements()) {
            conditions.addCondition(statement.getName(), ifElseStatement.getCondition());
            statement.accept(this, context);
        }

        for (Statement statement : ifElseStatement.getElseCaseStatements()) {
            conditions.addCondition(statement.getName(), new Negation(ifElseStatement.getCondition(), 0, 0));
            statement.accept(this, context);
        }
        return null;
    }

    @Override
    public Void visit(BooleanType booleanType, String context) {
        values.addOrUpdateValue(context, new BooleanValue(false));
        return null;
    }

    @Override
    public Void visit(IntegerType integerType, String context) {
        values.addOrUpdateValue(context, new IntegerValue(0));
        return null;
    }

    @Override
    public Void visit(StringType stringType, String context) {
        values.addOrUpdateValue(context, new StringValue(""));
        return null;
    }

    @Override
    public Void visit(ErrorType errorType, String context) {
        values.addOrUpdateValue(context, new NullValue());
        return null;
    }
}

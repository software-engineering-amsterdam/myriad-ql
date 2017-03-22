package com.matthewchapman.ql.environment;

import com.matthewchapman.ql.ast.Expression;
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
import com.matthewchapman.ql.environment.observers.ValueTableObserver;
import com.matthewchapman.ql.environment.values.*;
import com.matthewchapman.ql.visitors.StatementVisitor;
import com.matthewchapman.ql.visitors.TypeVisitor;

import java.util.List;

/**
 * Created by matt on 20/03/2017.
 */
public class FormEnvironment implements StatementVisitor<Void, String>, TypeVisitor<Void, String> {

    private final ExpressionTable expressions;
    private final ConditionTable conditions;
    private final QuestionTable questions;
    private final ValueTable values;

    public FormEnvironment(Form ast, ValueTableObserver observer) {
        expressions = new ExpressionTable();
        conditions = new ConditionTable();
        questions = new QuestionTable();
        values = new ValueTable();

        for (Statement statement : ast.getStatements()) {
            statement.accept(this, null);
        }

        values.addObserver(observer);
    }

    public List<Question> getQuestionsAsList() {
        return this.questions.getQuestionsAsList();
    }

    public List<Expression> getExpressionsAsList() {
        return this.expressions.getExpressionsAsList();
    }

    public void updateValueByName(String name, Value value) {
        values.addOrUpdateValue(name, value);
    }

    public Value getValueByName(String name) {
        return values.getValueByID(name);
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

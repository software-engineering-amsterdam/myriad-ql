package org.ql.evaluator;

import org.ql.ast.Expression;
import org.ql.ast.Form;
import org.ql.ast.Statement;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.*;
import org.ql.evaluator.value.UnknownValue;
import org.ql.evaluator.value.Value;

import java.util.ArrayList;
import java.util.List;

public class ConditionEvaluator implements FormVisitor<List<Question>, ValueTable>,
        StatementVisitor<Void, ValueTable> {

    private final ExpressionEvaluator expressionEvaluator;
    private List<Question> visibleQuestions;

    public ConditionEvaluator() {
        expressionEvaluator = new ExpressionEvaluator();
    }

    @Override
    public List<Question> visitForm(Form form, ValueTable valueTable) {
        visibleQuestions = new ArrayList<>();

        for (Statement statement : form.getStatements())
            statement.accept(this, valueTable);

        return visibleQuestions;
    }

    @Override
    public Void visitIfThen(IfThen ifThen, ValueTable valueTable) {
        if (evaluateIfCondition(ifThen.getCondition(), valueTable)) {
            for (Statement statement : ifThen.getThenStatements())
                statement.accept(this, valueTable);
        }

        return null;
    }

    @Override
    public Void visitIfThenElse(IfThenElse ifThenElse, ValueTable valueTable) {
        if (evaluateIfCondition(ifThenElse.getCondition(), valueTable)) {
            for (Statement statement : ifThenElse.getThenStatements())
                statement.accept(this, valueTable);
        } else {
            for (Statement statement : ifThenElse.getElseStatements())
                statement.accept(this, valueTable);
        }
        return null;
    }

    @Override
    public Void visitQuestion(Question question, ValueTable context) {
        visibleQuestions.add(question);
        return null;
    }

    @Override
    public Void visitComputableQuestion(ComputableQuestion question, ValueTable valueTable) {
        visibleQuestions.add(question);
        return null;
    }

    private boolean evaluateIfCondition(Expression expression, ValueTable valueTable) {
        return expressionEvaluator.evaluate(expression, valueTable).toBoolean();
    }
}

package com.matthewchapman.ql.environment;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.environment.datastores.ConditionTable;
import com.matthewchapman.ql.environment.datastores.ExpressionTable;
import com.matthewchapman.ql.environment.datastores.QuestionTable;
import com.matthewchapman.ql.environment.datastores.ValueTable;
import com.matthewchapman.ql.environment.evaluation.ExpressionEvaluator;
import com.matthewchapman.ql.environment.values.Value;

import java.util.List;

/**
 * Created by matt on 20/03/2017.
 */
public class FormEnvironment {

    private final ExpressionTable expressions;
    private final ConditionTable conditions;
    private final QuestionTable questions;
    private final ValueTable values;

    FormEnvironment(ExpressionTable expressions, ConditionTable conditions, QuestionTable questions, ValueTable values) {
        this.expressions = expressions;
        this.conditions = conditions;
        this.questions = questions;
        this.values = values;
    }

    public List<Question> getQuestionsAsList() { return this.questions.getQuestionsAsList(); }

    public Value getValueByID(String name) { return this.values.getValueByID(name); }

    public Expression getConditionByID(String name) { return this.conditions.getConditionByID(name); }

    public boolean questionHasCondition(String name) { return this.conditions.questionHasCondition(name); }

    public boolean questionIsCalculated(String name) { return this.expressions.questionHasExpression(name); }

    public ValueTable getValues() { return this.values; }

    public void addOrUpdateValue(String id, Value value) {
        this.values.addOrUpdateValue(id, value);
    }

    public void evaluateExpressions(ExpressionEvaluator eval) {
        for (Question question : this.getQuestionsAsList()) {
            if(this.expressions.questionHasExpression(question.getName())) {
                Value value = eval.evaluateExpression(question.getName(), this.expressions.getExpressionByName(question.getName()), this.values);
                this.values.addOrUpdateValue(question.getName(), value);
            }
        }
    }

}

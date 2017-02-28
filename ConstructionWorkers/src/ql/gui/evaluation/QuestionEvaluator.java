package ql.gui.evaluation;

import ql.astnodes.expressions.Expression;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.gui.formenvironment.ValueData;
import ql.gui.formenvironment.values.BooleanValue;
import ql.gui.formenvironment.values.Value;

/**
 * Created by LGGX on 24-Feb-17.
 */
public class QuestionEvaluator {

    private final QuestionValueChecker evaluator;
    private final ValueData valueData;

    public QuestionEvaluator(ValueData valueData) {
        this.valueData = valueData;
        this.evaluator = new QuestionValueChecker(valueData);
    }

    private Value computedQuestionEvaluation(ComputedQuestion question) {
        Expression expression = question.getExpression();
        return expression.accept(this.evaluator);
    }

    public Value expressionEvaluation(ComputedQuestion expression) {
        Value result = this.computedQuestionEvaluation(expression);
        valueData.addValue(expression.getIdentifier().getName(), result);
        return result;
    }

    public boolean IfStatementEvaluation(IfStatement expression) {
        Expression condition = expression.getExpression();
        return this.getConditionEvaluation(condition);
    }

    private boolean getConditionEvaluation(Expression expression) {
        Value expressionValue = expression.accept(this.evaluator);
        BooleanValue result;
        if (!expressionValue.undefined()) {
            result = (BooleanValue) expressionValue;
        } else {
            result = new BooleanValue(false);
        }
        return result.getValue();
    }
}


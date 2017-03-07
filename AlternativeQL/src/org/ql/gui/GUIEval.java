package org.ql.gui;

import org.ql.ast.Expression;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.Question;
import org.ql.evaluator.Evaluator;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;

public class GUIEval {
    private Evaluator evaluator;

    public GUIEval(ValueTable valueTable) {
        this.evaluator = new Evaluator(valueTable);
    }

    public boolean evaluateIfThen(IfThen ifThen) {
        // TODO: Check if it is not a boolean value.

        Expression expression = ifThen.getCondition();
        BooleanValue result = (BooleanValue) evaluator.visitExpression(expression, null);

        return result.getPlainValue();
    }

    public Value evaluateExpression(Question question) {
        Expression expression = question.getValue();

        return evaluator.visitExpression(expression, null);
    }
}

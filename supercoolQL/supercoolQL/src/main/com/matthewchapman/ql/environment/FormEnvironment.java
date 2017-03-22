package com.matthewchapman.ql.environment;

import com.matthewchapman.ql.environment.datastores.ConditionTable;
import com.matthewchapman.ql.environment.datastores.ExpressionTable;
import com.matthewchapman.ql.environment.datastores.QuestionTable;
import com.matthewchapman.ql.environment.datastores.ValueTable;

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

    public ExpressionTable getExpressions() { return this.expressions; }
    public ConditionTable getConditions() { return this.conditions; }
    public QuestionTable getQuestions() { return this.questions; }
    public ValueTable getValues() { return this.values; }

}

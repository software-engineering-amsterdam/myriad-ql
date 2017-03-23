package com.matthewchapman.ql.environment;

import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.environment.datastores.ConditionTable;
import com.matthewchapman.ql.environment.datastores.ExpressionTable;
import com.matthewchapman.ql.environment.datastores.QuestionTable;
import com.matthewchapman.ql.environment.datastores.ValueTable;
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

    public Value getValueByName(String name) { return this.values.getValueByID(name); }

    public ValueTable getValues() { return this.values; }

}

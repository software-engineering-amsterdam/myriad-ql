package com.matthewchapman.ql.environment.datastores;

import com.matthewchapman.ql.ast.Expression;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by matt on 20/03/2017.
 */
public class QuestionTable {

    private final Map<String, Expression> questions;

    public QuestionTable() {
        questions = new HashMap<>();
    }

    public void addQuestion(String name, Expression expression) {
        questions.put(name, expression);
    }

    public Expression getQuestionByID(String name) {
        return questions.get(name);
    }
}

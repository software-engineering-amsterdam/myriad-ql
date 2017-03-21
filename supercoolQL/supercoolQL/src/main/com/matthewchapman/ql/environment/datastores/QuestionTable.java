package com.matthewchapman.ql.environment.datastores;

import com.matthewchapman.ql.ast.statement.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by matt on 20/03/2017.
 */
public class QuestionTable {

    private final Map<String, Question> questions;

    public QuestionTable() {
        questions = new HashMap<>();
    }

    public void addQuestion(String name, Question question) {
        questions.put(name, question);
    }

    public Question getQuestionByID(String name) {
        return questions.get(name);
    }

    public List<Question> getQuestionsAsList() { return new ArrayList<>(questions.values()); }
}

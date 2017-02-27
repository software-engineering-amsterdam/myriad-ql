package org.ql.collection;

import org.ql.ast.statement.Question;

import java.util.ArrayList;

public class Questions extends ArrayList<Question> {
    private boolean isQuestionDuplicate(Question question, Question q) {
        return !q.getType().equals(question.getType()) && q.getId().equals(question.getId());
    }

    public boolean hasDuplicates(Question question) {
        return stream().filter(q -> isQuestionDuplicate(question, q)).count() > 0;
    }
}

package org.ql.collection;

import org.ql.ast.statement.Question;

import java.util.ArrayList;

public class Questions extends ArrayList<Question> {
    public boolean hasDuplicatedDeclarations(Question question) {
        return stream().filter(q -> isQuestionDuplicate(question, q)).count() > 0;
    }

    public boolean hasLabelDuplicates(Question question) {
        return stream().filter(q -> isQuestionLabelDuplicate(question, q)).count() > 0;
    }

    private boolean isQuestionDuplicate(Question question, Question q) {
        return !q.getType().equals(question.getType()) && q.getId().equals(question.getId());
    }

    private boolean isQuestionLabelDuplicate(Question question, Question q) {
        return q.getQuestionText().equals(question.getQuestionText());
    }
}

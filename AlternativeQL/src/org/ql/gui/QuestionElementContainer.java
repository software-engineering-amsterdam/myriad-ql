package org.ql.gui;

import org.ql.ast.Identifier;
import org.ql.ast.statement.Question;
import org.ql.gui.elements.QuestionElement;

import java.util.HashMap;
import java.util.Map;

public class QuestionElementContainer {
    // TODO think about the key
    private final Map<Identifier, QuestionElement> elementsMap = new HashMap<>();

    public QuestionElement getQuestionElement(Question question) {
        if (!elementsMap.containsKey(question.getId())) {
            elementsMap.put(question.getId(), createQuestionElement(question));
        }

        return elementsMap.get(question.getId());
    }

    private QuestionElement createQuestionElement(Question question) {
        return null;
    }
}

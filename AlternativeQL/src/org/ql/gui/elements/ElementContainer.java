package org.ql.gui.elements;

import org.ql.ast.Identifier;
import org.ql.ast.statement.Question;
import org.ql.gui.elements.visitor.QuestionElementFactory;

import java.util.HashMap;
import java.util.Map;

public class ElementContainer {

    private final Map<Identifier, Element> elementsMap = new HashMap<>();
    private final QuestionElementFactory questionElementFactory;

    public ElementContainer(QuestionElementFactory questionElementFactory) {
        this.questionElementFactory = questionElementFactory;
    }

    public Element getQuestionElement(Question question) {
        if (!elementsMap.containsKey(question.getId())) {
            elementsMap.put(question.getId(), createQuestionElement(question));
        }

        return elementsMap.get(question.getId());
    }

    private Element createQuestionElement(Question question) {
        return questionElementFactory.createQuestionElement(question);
    }
}

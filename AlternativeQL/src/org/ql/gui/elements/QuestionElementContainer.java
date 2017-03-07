package org.ql.gui.elements;

import org.ql.ast.Identifier;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.UnknownValue;
import org.ql.gui.widgets.WidgetBuilder;

import java.util.HashMap;
import java.util.Map;

public class QuestionElementContainer {

    private final Map<Identifier, QuestionElement> elementsMap = new HashMap<>();
    private final WidgetBuilder widgetBuilder;

    public QuestionElementContainer(WidgetBuilder widgetBuilder) {
        this.widgetBuilder = widgetBuilder;
    }

    public QuestionElement getQuestionElement(Question question) {
        if (!elementsMap.containsKey(question.getId())) {
            elementsMap.put(question.getId(), createQuestionElement(question));
        }

        return elementsMap.get(question.getId());
    }

    private QuestionElement createQuestionElement(Question question) {
        return new QuestionElement(question, new UnknownValue(), widgetBuilder.getWidget(question));
    }
}

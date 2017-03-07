package org.ql.gui.elements;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.ql.ast.Identifier;
import org.ql.ast.statement.Question;
import org.ql.gui.widgets.Widget;
import org.ql.gui.widgets.WidgetBuilder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class QuestionElementContainerTest {
    @Test
    public void shouldPersistQuestionElementInMap() {

        WidgetBuilder widgetBuilder = mock(WidgetBuilder.class);
        when(widgetBuilder.getWidget(any(Question.class))).thenAnswer(new Answer<Widget>() {
            @Override
            public Widget answer(InvocationOnMock invocationOnMock) throws Throwable {
                return mock(Widget.class);
            }
        });

        Question aQuestion = mock(Question.class);
        when(aQuestion.getId()).thenAnswer(new Answer<Identifier>() {
            @Override
            public Identifier answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new Identifier("example");
            }
        });

        QuestionElementContainer container = new QuestionElementContainer(widgetBuilder);
        QuestionElement actualQuestionElement =  container.getQuestionElement(aQuestion);
        QuestionElement actualRetrievedQuestionElement =  container.getQuestionElement(aQuestion);

        assertSame(actualQuestionElement, actualRetrievedQuestionElement);
    }
}
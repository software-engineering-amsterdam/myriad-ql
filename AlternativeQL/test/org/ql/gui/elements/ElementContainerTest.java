package org.ql.gui.elements;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.ql.ast.Identifier;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionLabel;
import org.ql.ast.type.BooleanType;
import org.ql.ast.type.IntegerType;
import org.ql.ast.type.Type;
import org.ql.gui.widgets.WidgetContainer;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.IntegerInputWidget;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ElementContainerTest {
    @Test
    public void shouldPersistQuestionElementInMap() {

        WidgetContainer aWidgetContainer = mockQuestionElementBuilder();
        Question aQuestion = questionMock();

        ElementContainer container = new ElementContainer(aWidgetContainer);
        Element actualQuestionElement =  container.getQuestionElement(aQuestion);
        Element actualRetrievedQuestionElement =  container.getQuestionElement(aQuestion);

        assertSame(actualQuestionElement, actualRetrievedQuestionElement);
    }

    private WidgetContainer mockQuestionElementBuilder() {
        WidgetContainer elementBuilder = mock(WidgetContainer.class);
        when(elementBuilder.visitIntegerType(any(IntegerType.class), any(Question.class))).thenAnswer(new Answer<IntegerElement>() {
            @Override
            public IntegerElement answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new IntegerElement(mock(GUIMediator.class), mock(Identifier.class), mock(IntegerInputWidget.class));
            }
        });
        return elementBuilder;
    }

    private Question questionMock() {
        Question aQuestion = mock(Question.class);
        when(aQuestion.getType()).thenAnswer(new Answer<Type>() {
            @Override
            public Type answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new BooleanType();
            }
        });
        when(aQuestion.getId()).thenAnswer(new Answer<Identifier>() {
            @Override
            public Identifier answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new Identifier("example");
            }
        });
        when(aQuestion.getQuestionLabel()).thenAnswer(new Answer<QuestionLabel>() {
            @Override
            public QuestionLabel answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new QuestionLabel("a label");
            }
        });
        return aQuestion;
    }
}
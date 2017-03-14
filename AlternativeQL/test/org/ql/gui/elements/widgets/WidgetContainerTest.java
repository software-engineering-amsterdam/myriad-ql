package org.ql.gui.elements.widgets;

import org.junit.Test;
import org.ql.ast.Identifier;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionLabel;
import org.ql.ast.type.BooleanType;
import org.ql.gui.widgets.CheckBoxWidget;
import org.ql.gui.widgets.Widget;
import org.ql.gui.widgets.WidgetContainer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WidgetContainerTest {
    @Test
    public void shouldRetrieveWidgetFromMap() {

        Question question = new Question(new Identifier("hasSoldHouse"), new QuestionLabel("question"), new BooleanType(), null);
        WidgetContainer container = new WidgetContainer(null);
        Widget widget = container.retrieveWidget(question);

        assertTrue(widget != null);
        assertTrue(widget instanceof CheckBoxWidget);

    }
/*
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
    }*/
}
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
import org.ql.gui.elements.visitor.QuestionElementFactory;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.NumericWidget;
import org.ql.gui.widgets.Widget;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class QuestionElementContainerTest {
    @Test
    public void shouldPersistQuestionElementInMap() {

        QuestionElementFactory aQuestionElementFactory = mockQuestionElementBuilder();
        Question aQuestion = questionMock();

        QuestionElementContainer container = new QuestionElementContainer(aQuestionElementFactory);
        QuestionElement actualQuestionElement =  container.getQuestionElement(aQuestion);
        QuestionElement actualRetrievedQuestionElement =  container.getQuestionElement(aQuestion);

        assertSame(actualQuestionElement, actualRetrievedQuestionElement);
    }

    private QuestionElementFactory mockQuestionElementBuilder() {
        QuestionElementFactory elementBuilder = mock(QuestionElementFactory.class);
        when(elementBuilder.visitIntegerType(any(IntegerType.class), any(Question.class))).thenAnswer(new Answer<IntegerQuestionElement>() {
            @Override
            public IntegerQuestionElement answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new IntegerQuestionElement(mock(GUIMediator.class), mock(Question.class), mock(NumericWidget.class));
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
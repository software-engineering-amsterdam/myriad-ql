package org.ql.gui.elements.visitor;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.Addition;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionLabel;
import org.ql.ast.type.IntegerType;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.IntegerValue;
import org.ql.evaluator.value.UnknownValue;
import org.ql.gui.elements.ElementContainer;
import org.ql.gui.elements.IntegerElement;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.IntegerInputWidget;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class QuestionValueVisitorTest {

    @Test
    public void shouldMakeValueTableWithFlatQuestionsAndNoReferences() {
        ValueTable expectedValueTable = new ValueTable();
        expectedValueTable.declare(new Identifier("first"), new IntegerValue(12));
        expectedValueTable.declare(new Identifier("second"), new IntegerValue(15));

        QuestionValueVisitor visitor = new QuestionValueVisitor(new ElementContainer(mockQuestionElementBuilder()));
        ValueTable actualValueTable = new ValueTable();
        visitor.updateValues(new Form(new Identifier("Example"), new ArrayList<Statement>() {{
            add(new Question(new Identifier("first"), new QuestionLabel("Question"), new IntegerType(), new IntegerLiteral(12)));
            add(new Question(new Identifier("second"), new QuestionLabel("Question2"), new IntegerType(), new IntegerLiteral(15)));
        }}), actualValueTable);

        assertTrue(expectedValueTable.equals(actualValueTable));
    }

    @Test
    public void shouldMakeValueTableWithFlatQuestionsAndEmptyValues() {
        ValueTable expectedValueTable = new ValueTable();
        expectedValueTable.declare(new Identifier("first"), new IntegerValue(12));
        expectedValueTable.declare(new Identifier("second"), new UnknownValue());

        QuestionValueVisitor visitor = new QuestionValueVisitor(new ElementContainer(mockQuestionElementBuilder()));
        ValueTable actualValueTable = new ValueTable();
        visitor.updateValues(new Form(new Identifier("Example"), new ArrayList<Statement>() {{
            add(new Question(new Identifier("first"), new QuestionLabel("Question"), new IntegerType(), new IntegerLiteral(12)));
            add(new Question(new Identifier("second"), new QuestionLabel("Question2"), new IntegerType(), null));
        }}), actualValueTable);

        assertTrue(expectedValueTable.equals(actualValueTable));
    }

    @Test
    public void shouldMakeValueTableWithFlatQuestionsAndReferences() {
        ValueTable expectedValueTable = new ValueTable();
        expectedValueTable.declare(new Identifier("first"), new IntegerValue(12));
        expectedValueTable.declare(new Identifier("second"), new IntegerValue(48));
        expectedValueTable.declare(new Identifier("third"), new IntegerValue(48));

        QuestionValueVisitor visitor = new QuestionValueVisitor(new ElementContainer(mockQuestionElementBuilder()));
        ValueTable actualValueTable = new ValueTable();
        visitor.updateValues(new Form(new Identifier("Example"), new ArrayList<Statement>() {{
            add(new Question(new Identifier("first"), new QuestionLabel("Question"), new IntegerType(), new IntegerLiteral(12)));
            add(new Question(new Identifier("second"), new QuestionLabel("Question2"), new IntegerType(), new Parameter(new Identifier("third"))));
            add(new Question(new Identifier("third"), new QuestionLabel("Question2"), new IntegerType(), new Addition(
                    new IntegerLiteral(13), new IntegerLiteral(35)
            )));
        }}), actualValueTable);

        assertTrue(expectedValueTable.equals(actualValueTable));
    }

    @Test
    public void shouldMakeValueTableWithBranchedQuestions() {
        ValueTable expectedValueTable = new ValueTable();
        expectedValueTable.declare(new Identifier("first"), new IntegerValue(12));
        expectedValueTable.declare(new Identifier("second"), new IntegerValue(48));
        expectedValueTable.declare(new Identifier("third"), new IntegerValue(48));

        QuestionValueVisitor visitor = new QuestionValueVisitor(new ElementContainer(mockQuestionElementBuilder()));
        ValueTable actualValueTable = new ValueTable();
        visitor.updateValues(new Form(new Identifier("Example"), new ArrayList<Statement>() {{
            add(new IfThenElse(new BooleanLiteral(true), new ArrayList<Statement>() {{
                add(new Question(new Identifier("first"), new QuestionLabel("Question"), new IntegerType(), new IntegerLiteral(12)));
            }}, new ArrayList<Statement>() {{
                add(new Question(new Identifier("second"), new QuestionLabel("Question2"), new IntegerType(), new Parameter(new Identifier("third"))));
                add(new Question(new Identifier("third"), new QuestionLabel("Question2"), new IntegerType(), new Addition(
                        new IntegerLiteral(13), new IntegerLiteral(35)
                )));
            }}));
        }}), actualValueTable);

        assertTrue(expectedValueTable.equals(actualValueTable));
    }

    private QuestionElementFactory mockQuestionElementBuilder() {
        QuestionElementFactory elementBuilder = mock(QuestionElementFactory.class);
        when(elementBuilder.createQuestionElement(any(Question.class))).thenAnswer(new Answer<IntegerElement>() {
            @Override
            public IntegerElement answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new IntegerElement(mock(GUIMediator.class), mock(Identifier.class), mock(IntegerInputWidget.class));
            }
        });
        return elementBuilder;
    }
}

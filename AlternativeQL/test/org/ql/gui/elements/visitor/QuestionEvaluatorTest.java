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
import org.ql.evaluator.QuestionEvaluator;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.IntegerValue;
import org.ql.evaluator.value.UnknownValue;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.IntegerInputWidget;
import org.ql.gui.widgets.WidgetContainer;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class QuestionEvaluatorTest {

    @Test
    public void shouldMakeValueTableWithFlatQuestionsAndNoReferences() {
        ValueTable expectedValueTable = new ValueTable();
        expectedValueTable.declare(new Identifier("first"), new IntegerValue(12));
        expectedValueTable.declare(new Identifier("second"), new IntegerValue(15));

        QuestionEvaluator visitor = new QuestionEvaluator(modifiedQuestions);
        ValueTable actualValueTable = new ValueTable();
        visitor.updateValueTable(new Form(new Identifier("Example"), new ArrayList<Statement>() {{
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

        QuestionEvaluator visitor = new QuestionEvaluator(modifiedQuestions);
        ValueTable actualValueTable = new ValueTable();
        visitor.updateValueTable(new Form(new Identifier("Example"), new ArrayList<Statement>() {{
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

        QuestionEvaluator visitor = new QuestionEvaluator(modifiedQuestions);
        ValueTable actualValueTable = new ValueTable();
        visitor.updateValueTable(new Form(new Identifier("Example"), new ArrayList<Statement>() {{
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

        QuestionEvaluator visitor = new QuestionEvaluator(modifiedQuestions);
        ValueTable actualValueTable = new ValueTable();
        visitor.updateValueTable(new Form(new Identifier("Example"), new ArrayList<Statement>() {{
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

    private WidgetContainer mockQuestionElementBuilder() {
        WidgetContainer elementBuilder = mock(WidgetContainer.class);
        when(elementBuilder.retrieveWidget(any(Question.class))).thenAnswer(new Answer<IntegerElement>() {
            @Override
            public IntegerElement answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new IntegerElement(mock(GUIMediator.class), mock(Identifier.class), mock(IntegerInputWidget.class));
            }
        });
        return elementBuilder;
    }
}

package org.ql.typechecker;

import org.junit.Test;
import org.ql.ast.Identifier;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionText;
import org.ql.ast.type.BooleanType;
import org.ql.ast.type.Type;
import org.ql.collection.collector.FormQuestionCollector;
import org.ql.collection.collector.QuestionVisitor;
import org.ql.typechecker.messages.MessageBag;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class TypeCheckerTest {

    @Test
    public void shouldAddErrorWhenFormNameEmpty() {
        ITypeChecker typeChecker = new TypeChecker(new FormQuestionCollector(new QuestionVisitor()));

        MessageBag messages = typeChecker.checkForm(new FormBuilder().getDefault().setName("").build());

        assertTrue(messages.getErrors().size() == 1);
        assertEquals("Form name cannot be empty", messages.getErrors().get(0));
    }

    @Test
    public void shouldContainNoErrorsWhenQuestionAdded() {
        ITypeChecker typeChecker = new TypeChecker(new FormQuestionCollector(new QuestionVisitor()));

        MessageBag messages = typeChecker.checkForm(new FormBuilder().getDefault().build());

        assertTrue(messages.getErrors().size() == 0);
    }

    @Test
    public void shouldAddErrorWhenDuplicateLabelsAndTypeForQuestion() {
        ITypeChecker typeChecker = new TypeChecker(new FormQuestionCollector(new QuestionVisitor()));
        String questionLabel = "example";
        String expectedError = "Question '" + questionLabel + "' has duplicate(s)";

        MessageBag messages = typeChecker.checkForm(new FormBuilder()
                .setName("exampleForm")
                .addStatement(new Question(new Identifier(questionLabel), new QuestionText("example question?"), new BooleanType(), null))
                .addStatement(new Question(new Identifier(questionLabel), new QuestionText("example question?"), new BooleanType(), null))
                .build());

        assertTrue(messages.getErrors().size() == 2);
        assertEquals(expectedError, messages.getErrors().get(0));
        assertEquals(expectedError, messages.getErrors().get(1));
    }

    private Question createQuestion() {
        return new Question(mock(Identifier.class), mock(QuestionText.class), mock(Type.class), null);
    }
}

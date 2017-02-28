package org.ql.typechecker;

import org.junit.Test;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionText;
import org.ql.ast.type.BooleanType;
import org.ql.collection.collector.FormQuestionCollector;
import org.ql.collection.QuestionCollector;
import org.ql.typechecker.messages.MessageBag;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TypeCheckerTest {

    @Test
    public void shouldAddErrorWhenFormNameEmpty() {
        ITypeChecker typeChecker = new TypeChecker(new FormQuestionCollector(new QuestionCollector()));

        MessageBag messages = typeChecker.checkForm(new FormBuilder().getDefault().setName("").build());

        assertTrue(messages.getErrors().size() == 1);
        assertEquals("Form name cannot be empty", messages.getErrors().get(0));
    }

    @Test
    public void shouldContainNoErrorsWhenQuestionAdded() {
        ITypeChecker typeChecker = new TypeChecker(new FormQuestionCollector(new QuestionCollector()));

        MessageBag messages = typeChecker.checkForm(new FormBuilder().getDefault().build());

        assertTrue(messages.getErrors().size() == 0);
    }

    @Test
    public void shouldAddErrorWhenDuplicateLabelsAndTypeForQuestion() {
        ITypeChecker typeChecker = new TypeChecker(new FormQuestionCollector(new QuestionCollector()));
        String questionLabel = "example";
        String expectedError = "Question '" + questionLabel + "' isDeclared duplicate(s)";

        MessageBag messages = typeChecker.checkForm(new FormBuilder()
                .setName("exampleForm")
                .addStatement(new Question(new Identifier(questionLabel), new QuestionText("example question?"), new BooleanType(), null))
                .addStatement(new Question(new Identifier(questionLabel), new QuestionText("example question?"), new BooleanType(), null))
                .build());

        assertTrue(messages.getErrors().size() == 2);
        assertEquals(expectedError, messages.getErrors().get(0));
        assertEquals(expectedError, messages.getErrors().get(1));
    }

    @Test
    public void shouldCheckStatementsAndGiveErrors() {
        ITypeChecker typeChecker = new TypeChecker(new FormQuestionCollector(new QuestionCollector()));
        List<Statement> statements = new ArrayList<>();

        statements.add(new Question(new Identifier("test"), new QuestionText("example question?"), new BooleanType(), new IntegerLiteral(12)));
        statements.add(new Question(new Identifier("test"), new QuestionText(""), new BooleanType(), null));

        MessageBag messageBag = typeChecker.checkStatements(statements);

        assertEquals(2, messageBag.getErrors().size());
    }
}

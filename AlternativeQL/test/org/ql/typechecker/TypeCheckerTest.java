package org.ql.typechecker;

import org.junit.Test;
import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionText;
import org.ql.ast.type.*;
import org.ql.typechecker.circular_dependencies.CircularDependenciesResolver;
import org.ql.typechecker.issues.IssuesStorage;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TypeCheckerTest {

    @Test
    public void shouldAddErrorWhenDuplicateLabelsAndTypeForQuestion() {
        String questionLabel = "example";
        String expectedError = "Question '" + questionLabel + "' has duplicate(s)";

        TypeChecker typeChecker = new TypeChecker(new Form(new Identifier("exampleForm"), new ArrayList<Statement>() {{
            add(new Question(new Identifier(questionLabel), new QuestionText("example question?"), new BooleanType(), null));
            add(new Question(new Identifier(questionLabel), new QuestionText("example question?"), new BooleanType(), null));
        }}));

        assertTrue(typeChecker.getWarnings().size() == 2);
        assertEquals(expectedError, typeChecker.getWarnings().get(0));
        assertEquals(expectedError, typeChecker.getWarnings().get(1));
    }
}

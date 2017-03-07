package org.ql.typechecker;

import org.junit.Test;
import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionLabel;
import org.ql.ast.type.*;
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
        String expectedWarning = "Question '" + questionLabel + "' label has duplicate(s)";

        TypeChecker typeChecker = new TypeChecker(new Form(new Identifier("exampleForm"), new ArrayList<Statement>() {{
            add(new Question(new Identifier(questionLabel), new QuestionLabel("example question?"), new BooleanType(), null));
            add(new Question(new Identifier(questionLabel), new QuestionLabel("example question?"), new BooleanType(), null));
        }}));

        IssuesStorage issuesStorage = typeChecker.checkForm();

        assertEquals(1, issuesStorage.getErrors().size());
        assertEquals(1, issuesStorage.getWarnings().size());
        assertEquals(expectedError, issuesStorage.getErrors().get(0).getMessage());
        assertEquals(expectedWarning, issuesStorage.getWarnings().get(0).getMessage());
    }
}

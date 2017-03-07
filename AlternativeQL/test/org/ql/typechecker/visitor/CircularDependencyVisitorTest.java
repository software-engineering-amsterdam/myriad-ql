package org.ql.typechecker.visitor;

import org.junit.Test;
import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.expression.Parameter;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionText;
import org.ql.ast.type.BooleanType;
import org.ql.typechecker.issues.IssuesStorage;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class CircularDependencyVisitorTest {
    @Test
    public void shouldGiveCircularDependencyErrorWhenSuchAreThere() {
        IssuesStorage issuesStorage = new IssuesStorage();
        CircularDependencyVisitor visitor = new CircularDependencyVisitor(issuesStorage);

        visitor.visitForm(new Form(new Identifier("Form"), new ArrayList<Statement>() {{
            add(new Question(new Identifier("first"), new QuestionText("label1"), new BooleanType(), new Parameter(new Identifier("second"))));
            add(new Question(new Identifier("second"), new QuestionText("label2"), new BooleanType(), new Parameter(new Identifier("first"))));
            add(new Question(new Identifier("third"), new QuestionText("label2"), new BooleanType(), null));
        }}), null);

        assertEquals(2, issuesStorage.getErrors().size());
        assertEquals("Circular dependency detected for question 'first'", issuesStorage.getErrors().get(1).getMessage());
        assertEquals("Circular dependency detected for question 'second'", issuesStorage.getErrors().get(0).getMessage());
    }
}
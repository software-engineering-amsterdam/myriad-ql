package org.ql.typechecker.visitor;

import org.junit.Test;
import org.ql.ast.form.Form;
import org.ql.ast.identifier.Identifier;
import org.ql.ast.statement.Statement;
import org.ql.ast.expression.Parameter;
import org.ql.ast.statement.ComputableQuestion;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionLabel;
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
            add(new ComputableQuestion(new Identifier("first"), new QuestionLabel("label1"), new BooleanType(), new Parameter(new Identifier("second"))));
            add(new ComputableQuestion(new Identifier("second"), new QuestionLabel("label2"), new BooleanType(), new Parameter(new Identifier("first"))));
            add(new Question(new Identifier("third"), new QuestionLabel("label2"), new BooleanType()));
        }}), null);

        assertEquals(2, issuesStorage.getErrors().size());
        assertEquals("Circular dependency detected for question 'first'", issuesStorage.getErrors().get(1).getMessage());
        assertEquals("Circular dependency detected for question 'second'", issuesStorage.getErrors().get(0).getMessage());
    }
}
package org.ql.typechecker.visitor;

import org.junit.Test;
import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.expression.Parameter;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionText;
import org.ql.ast.type.BooleanType;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CircularDependencyVisitorTest {
    @Test
    public void shouldGiveCircularDependencyErrorWhenSuchAreThere() {
        CircularDependencyVisitor visitor = new CircularDependencyVisitor();

        visitor.visitForm(new Form(new Identifier("Form"), new ArrayList<Statement>() {{
            add(new Question(new Identifier("first"), new QuestionText("label1"), new BooleanType(), new Parameter(new Identifier("second"))));
            add(new Question(new Identifier("second"), new QuestionText("label2"), new BooleanType(), new Parameter(new Identifier("first"))));
            add(new Question(new Identifier("third"), new QuestionText("label2"), new BooleanType(), null));
        }}), null);

        assertEquals(2, visitor.getErrors().size());
        assertTrue(visitor.getErrors().contains("Circular dependency detected for question 'second'"));
        assertTrue(visitor.getErrors().contains("Circular dependency detected for question 'first'"));
    }
}
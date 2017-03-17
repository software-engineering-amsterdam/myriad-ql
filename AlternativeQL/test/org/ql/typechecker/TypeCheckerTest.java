package org.ql.typechecker;

import org.junit.Test;
import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.Decrement;
import org.ql.ast.expression.arithmetic.Increment;
import org.ql.ast.expression.arithmetic.Product;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.relational.Negation;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionLabel;
import org.ql.ast.type.*;
import org.ql.typechecker.issues.Issue;
import org.ql.typechecker.issues.IssuesStorage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TypeCheckerTest {

    @Test
    public void shouldAddErrorWhenDuplicateLabelsAndTypeForQuestion() {
        String questionLabel = "example";
        String expectedError = "Question '" + questionLabel + "' has duplicate(s)";
        String expectedWarning = "Question '" + questionLabel + "' label has duplicate(s)";

        TypeChecker typeChecker = new TypeChecker();

        IssuesStorage issues = typeChecker.checkForm(new Form(new Identifier("exampleForm"), new ArrayList<Statement>() {{
            add(new Question(new Identifier(questionLabel), new QuestionLabel("example question?"), new BooleanType(), null));
            add(new Question(new Identifier(questionLabel), new QuestionLabel("example question?"), new BooleanType(), null));
        }}), new SymbolTable());

        assertEquals(1, issues.getErrors().size());
        assertEquals(1, issues.getWarnings().size());
        assertEquals(expectedError, issues.getErrors().get(0).getMessage());
        assertEquals(expectedWarning, issues.getWarnings().get(0).getMessage());
    }

    @Test
    public void shouldCollectAllKindsOfErrorsAndWarnings() {
        TypeChecker typeChecker = new TypeChecker();

        IssuesStorage issues = typeChecker.checkForm(new Form(new Identifier("exampleForm"), new ArrayList<Statement>() {{
            add(new Question(new Identifier("example"), new QuestionLabel("example question?"), new BooleanType(), null));
            add(new Question(new Identifier("example"), new QuestionLabel("example question?"), new BooleanType(), null));
            add(new Question(new Identifier("typemismatch"), new QuestionLabel("example question2?"), new BooleanType(),
                    new IntegerLiteral(12)));
            add(new Question(new Identifier("first"), new QuestionLabel("dependency?"), new BooleanType(),
                    new Negation(new Parameter(new Identifier("second")))));
            add(new Question(new Identifier("second"), new QuestionLabel("dependency2?"), new BooleanType(),
                    new Parameter(new Identifier("first"))));
            add(new Question(new Identifier(""), new QuestionLabel("wrong"), new BooleanType(), null));
            add(new Question(new Identifier("number_expected_1"), new QuestionLabel("not a number"), new IntegerType(),
                    new Increment(new BooleanLiteral(true))));
            add(new Question(new Identifier("number_expected_2"), new QuestionLabel("not a number2"), new IntegerType(),
                    new Decrement(new BooleanLiteral(true))));
            add(new Question(new Identifier("typemismatch2"), new QuestionLabel("typemismatch"), new IntegerType(),
                    new Product(new BooleanLiteral(true), new BooleanLiteral(false))));
        }}), new SymbolTable());

        List<Issue> errors = issues.getErrors();

        assertEquals("Question 'example' has duplicate(s)", errors.get(0).getMessage());
        assertEquals("Type mismatch: expected boolean, but got integer", errors.get(1).getMessage());
        assertEquals("Expected number type, got boolean", errors.get(2).getMessage());
        assertEquals("Type mismatch: expected integer, but got UnknownType", errors.get(3).getMessage());
        assertEquals("Expected number type, got boolean", errors.get(4).getMessage());
        assertEquals("Type mismatch: expected integer, but got UnknownType", errors.get(5).getMessage());
        assertEquals("Type mismatch: expected integer, but got boolean", errors.get(6).getMessage());
        assertEquals("Circular dependency detected for question 'second'", errors.get(7).getMessage());
        assertEquals("Circular dependency detected for question 'first'", errors.get(8).getMessage());

        List<Issue> warnings = issues.getWarnings();

        assertEquals("Question 'example' label has duplicate(s)", warnings.get(0).getMessage());
    }
}

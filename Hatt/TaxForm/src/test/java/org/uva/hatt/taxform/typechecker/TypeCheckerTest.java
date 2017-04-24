package org.uva.hatt.taxform.typechecker;

import org.junit.Test;
import org.uva.hatt.taxform.parsing.ASTGenerator;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.expressions.binary.*;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.items.Item;
import org.uva.hatt.taxform.ast.nodes.items.Question;
import org.uva.hatt.taxform.ast.nodes.types.ValueType;
import org.uva.hatt.taxform.typechecker.messages.Messages;
import org.uva.hatt.taxform.typechecker.messages.error.*;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class TypeCheckerTest {

    @Test
    public void testDuplicateLabel() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        String qlForm = "form f1 { \"Same label question\" hasSoldHouse: string \"Same label question\" sellingPrice: money }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        Question q1 = (Question) questions.get(0);
        Question q2 = (Question) questions.get(1);

        typeChecker.visit(q1);
        typeChecker.visit(q2);

        assertEquals(0, messages.getErrors().size());
        assertEquals(1, messages.getWarnings().size());
        assertEquals("Duplicate question label at line 1: Label: Same label question", messages.getWarnings().get(0).getMessage());
    }

    @Test
    public void testDuplicteDeclaration() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        String qlForm = "form f1 { \"question 1\" var: string \"question 2\" var: money }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        Question q1 = (Question) questions.get(0);
        Question q2 = (Question) questions.get(1);

        typeChecker.visit(q1);
        typeChecker.visit(q2);

        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(DuplicateDeclaration.class));
    }

    @Test
    public void testUndefinedReference() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        String qlForm = "form f1 { \"question 1\" var1: string if (unknown) {\"question 2\" var2: money } else {} }";
        Form form = ASTGenerator.getForm(qlForm);

        typeChecker.visit(form);

        assertEquals(3, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(UndefinedReference.class));
    }

    @Test
    public void testOneCyclicDependency() throws IOException {
        Messages messages = new Messages();
        CircularDependencyChecker circularDependencyChecker = new CircularDependencyChecker(messages);

        String qlForm = "form tax {\n" +
                "  \"q1?\" \n" +
                "    a: integer = (b - 1)\n" +
                "  \"q2?\"\n" +
                "    b: integer = (a / 5)\n" +
                "}";

        Form form = ASTGenerator.getForm(qlForm);
        circularDependencyChecker.visit(form);

        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(CyclicDependency.class));
    }

    @Test
    public void testTwoCyclicDependencies() throws IOException {
        Messages messages = new Messages();
        CircularDependencyChecker circularDependencyChecker = new CircularDependencyChecker(messages);

        String qlForm = "form tax {\n" +
                "  \"q1?\"\n" +
                "    b: integer\n" +
                "  \"q2?\"\n" +
                "    a: integer = (b + c)\n" +
                "  \"q3?\"\n" +
                "    c: integer = (a + b)\n" +
                "}";

        Form form = ASTGenerator.getForm(qlForm);
        circularDependencyChecker.visit(form);

        assertEquals(2, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(CyclicDependency.class));
        assertThat(messages.getErrors().get(1), instanceOf(CyclicDependency.class));
    }

    @Test
    public void testStringTypeMismatch() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        String qlForm = "form f1 { \"question 1\" var1: string if (var1) {\"question 2\" var2: money } }";
        Form form = ASTGenerator.getForm(qlForm);

        typeChecker.visit(form);

        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(TypeMismatch.class));
    }

    @Test
    public void testIntegerTypeMismatch() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        String qlForm = "form f1 { \"question 1\" var1: integer if (var1) {\"question 2\" var2: money } }";
        Form form = ASTGenerator.getForm(qlForm);

        typeChecker.visit(form);

        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(TypeMismatch.class));
    }

    @Test
    public void testMoneyTypeMismatch() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        String qlForm = "form f1 { \"question 1\" var1: money if (var1) {\"question 2\" var2: money } }";
        Form form = ASTGenerator.getForm(qlForm);

        typeChecker.visit(form);

        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(TypeMismatch.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorAddition() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        Addition addition = new Addition(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        typeChecker.visit(addition);

        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorDivision() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        Division division = new Division(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        typeChecker.visit(division);

        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorEqual() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        Equal equal = new Equal(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        typeChecker.visit(equal);

        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorGreaterThan() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        GreaterThan greaterThan = new GreaterThan(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        typeChecker.visit(greaterThan);

        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorGreaterThanOrEqual() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        typeChecker.visit(greaterThanOrEqual);

        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorLessThan() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        LessThan lessThan = new LessThan(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        typeChecker.visit(lessThan);

        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorLessThanOrEqual() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        LessThanOrEqual lessThanOrEqual = new LessThanOrEqual(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        typeChecker.visit(lessThanOrEqual);

        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorLogicalAnd() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);
        LogicalAnd logicalAnd = new LogicalAnd(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        ValueType type = typeChecker.visit(logicalAnd);
        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorLogicalOr() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        LogicalOr logicalOr = new LogicalOr(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        typeChecker.visit(logicalOr);

        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorMultiplication() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        Multiplication multiplication = new Multiplication(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        typeChecker.visit(multiplication);

        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorNotEqual() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        NotEqual notEqual = new NotEqual(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        typeChecker.visit(notEqual);

        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorSubtraction() throws IOException {
        Messages messages = new Messages();
        TypeChecker typeChecker = new TypeChecker(messages);

        Subtraction subtraction = new Subtraction(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        typeChecker.visit(subtraction);

        assertEquals(1, messages.getErrors().size());
        assertEquals(0, messages.getWarnings().size());
        assertThat(messages.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }
}

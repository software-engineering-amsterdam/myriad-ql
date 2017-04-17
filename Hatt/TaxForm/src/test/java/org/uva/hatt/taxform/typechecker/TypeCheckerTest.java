package org.uva.hatt.taxform.typechecker;

import org.junit.Test;
import org.uva.hatt.taxform.ast.ASTGenerator;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.expressions.binary.*;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.items.Item;
import org.uva.hatt.taxform.ast.nodes.items.Question;
import org.uva.hatt.taxform.ast.nodes.types.ValueType;
import org.uva.hatt.taxform.typechecker.Typechecker;
import org.uva.hatt.taxform.typechecker.messages.Message;
import org.uva.hatt.taxform.typechecker.messages.error.*;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class TypeCheckerTest {
    @Test
    public void testDuplicateLabel() throws IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        String qlForm = "form f1 { \"Same label question\" hasSoldHouse: string \"Same label question\" sellingPrice: money }";
        Form form = ASTGenerator.getForm(qlForm);
        List<Item> questions = form.getQuestions();
        Question q1 = (Question) questions.get(0);
        Question q2 = (Question) questions.get(1);
        typechecker.visit(q1);
        typechecker.visit(q2);
        assertEquals(0, message.getErrors().size());
        assertEquals(1, message.getWarnings().size());
        assertEquals("Duplicate question label at line 1: Label: Same label question", message.getWarnings().get(0).getMessage());
    }

    @Test
    public void testDuplicteDeclaration() throws IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        String qlForm = "form f1 { \"question 1\" var: string \"question 2\" var: money }";
        Form form = ASTGenerator.getForm(qlForm);
        List<Item> questions = form.getQuestions();
        Question q1 = (Question) questions.get(0);
        Question q2 = (Question) questions.get(1);
        typechecker.visit(q1);
        typechecker.visit(q2);
        assertEquals(1, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(DuplicateDeclaration.class));
    }

    @Test
    public void testUndefinedReference() throws  IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        String qlForm = "form f1 { \"question 1\" var1: string if (unknown) {\"question 2\" var2: money } else {} }";
        Form form = ASTGenerator.getForm(qlForm);
        typechecker.visit(form);
        assertEquals(3, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(UndefinedReference.class));
    }

    @Test
    public void testCyclicDependency() throws  IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        String qlForm = "form taxOfficeExample { \"q1?\" val1: money = 5 - 1  \n \"q2?\" val2: money = 5 + 1 / 2 \n \"q3?\" val3: money = val1 * val2}";;
        Form form = ASTGenerator.getForm(qlForm);
        typechecker.visit(form);
        assertEquals(2, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(CyclicDependency.class));
        assertThat(message.getErrors().get(1), instanceOf(CyclicDependency.class));
    }

    @Test
    public void testStringTypeMismatch() throws  IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        String qlForm = "form f1 { \"question 1\" var1: string if (var1) {\"question 2\" var2: money } }";
        Form form = ASTGenerator.getForm(qlForm);
        typechecker.visit(form);
        assertEquals(1, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(TypeMismatch.class));
    }

    @Test
    public void testIntegerTypeMismatch() throws  IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        String qlForm = "form f1 { \"question 1\" var1: integer if (var1) {\"question 2\" var2: money } }";
        Form form = ASTGenerator.getForm(qlForm);
        typechecker.visit(form);
        assertEquals(1, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(TypeMismatch.class));
    }

    @Test
    public void testMoneyTypeMismatch() throws IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        String qlForm = "form f1 { \"question 1\" var1: money if (var1) {\"question 2\" var2: money } }";
        Form form = ASTGenerator.getForm(qlForm);
        typechecker.visit(form);
        assertEquals(1, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(TypeMismatch.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorAddition() throws  IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        Addition addition = new Addition(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        ValueType type = typechecker.visit(addition);
        assertEquals(1, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorDivision() throws  IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        Division division = new Division(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        ValueType type = typechecker.visit(division);
        assertEquals(1, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorEqual() throws  IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        Equal equal = new Equal(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        ValueType type = typechecker.visit(equal);
        assertEquals(1, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorGreaterThan() throws  IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        GreaterThan greaterThan = new GreaterThan(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        ValueType type = typechecker.visit(greaterThan);
        assertEquals(1, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorGreaterThanOrEqual() throws  IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        ValueType type = typechecker.visit(greaterThanOrEqual);
        assertEquals(1, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorLessThan() throws  IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        LessThan lessThan = new LessThan(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        ValueType type = typechecker.visit(lessThan);
        assertEquals(1, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorLessThanOrEqual() throws  IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        LessThanOrEqual lessThanOrEqual = new LessThanOrEqual(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        ValueType type = typechecker.visit(lessThanOrEqual);
        assertEquals(1, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorLogicalAnd() throws  IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        LogicalAnd logicalAnd = new LogicalAnd(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        ValueType type = typechecker.visit(logicalAnd);
        assertEquals(1, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorLogicalOr() throws  IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        LogicalOr logicalOr = new LogicalOr(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        ValueType type = typechecker.visit(logicalOr);
        assertEquals(1, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorMultiplication() throws  IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        Multiplication multiplication = new Multiplication(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        ValueType type = typechecker.visit(multiplication);
        assertEquals(1, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorNotEqual() throws  IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        NotEqual notEqual = new NotEqual(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        ValueType type = typechecker.visit(notEqual);
        assertEquals(1, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }

    @Test
    public void testInvalidOperandsTypeToOperatorSubtraction() throws  IOException {
        Message message = new Message();
        Typechecker typechecker = new Typechecker(message);
        Subtraction subtraction = new Subtraction(2, new StringerLiteral(2, "test"), new IntegerLiteral(2, 111));
        ValueType type = typechecker.visit(subtraction);
        assertEquals(1, message.getErrors().size());
        assertEquals(0, message.getWarnings().size());
        assertThat(message.getErrors().get(0), instanceOf(InvalidOperandsTypeToOperator.class));
    }
}

package org.uva.hatt.taxform.ast.nodes.expressions.binary;

import org.junit.Test;
import org.uva.hatt.taxform.parsing.ASTGenerator;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.items.IfThen;
import org.uva.hatt.taxform.ast.nodes.items.Item;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class BinaryExpressionTest {

    @Test
    public void testAddition() throws IOException {
        String qlForm = "form taxOfficeExample { if (1 + 1) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertThat(ifThen.getCondition(), instanceOf(Addition.class));
    }

    @Test
    public void testDivision() throws IOException {
        String qlForm = "form taxOfficeExample { if (1 / 1) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertThat(ifThen.getCondition(), instanceOf(Division.class));
    }

    @Test
    public void testEqual() throws IOException {
        String qlForm = "form taxOfficeExample { if (1 == 1) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertThat(ifThen.getCondition(), instanceOf(Equal.class));
    }

    @Test
    public void testGreaterThan() throws IOException {
        String qlForm = "form taxOfficeExample { if (1 > 1) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertThat(ifThen.getCondition(), instanceOf(GreaterThan.class));
    }

    @Test
    public void testGreaterThanOrEqual() throws IOException {
        String qlForm = "form taxOfficeExample { if (1 >= 1) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertThat(ifThen.getCondition(), instanceOf(GreaterThanOrEqual.class));
    }

    @Test
    public void testLessThan() throws IOException {
        String qlForm = "form taxOfficeExample { if (1 < 1) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertThat(ifThen.getCondition(), instanceOf(LessThan.class));
    }

    @Test
    public void testLessThanOrEqual() throws IOException {
        String qlForm = "form taxOfficeExample { if (1 <= 1) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertThat(ifThen.getCondition(), instanceOf(LessThanOrEqual.class));
    }

    @Test
    public void testLogicalAnd() throws IOException {
        String qlForm = "form taxOfficeExample { if (1 && 1) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertThat(ifThen.getCondition(), instanceOf(LogicalAnd.class));
    }

    @Test
    public void testLogicalOr() throws IOException {
        String qlForm = "form taxOfficeExample { if (1 || 1) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertThat(ifThen.getCondition(), instanceOf(LogicalOr.class));
    }

    @Test
    public void testMultiplication() throws IOException {
        String qlForm = "form taxOfficeExample { if (1 * 1) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertThat(ifThen.getCondition(), instanceOf(Multiplication.class));
    }

    @Test
    public void testNotEqual() throws IOException {
        String qlForm = "form taxOfficeExample { if (1 != 1) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertThat(ifThen.getCondition(), instanceOf(NotEqual.class));
    }

    @Test
    public void testSubtraction() throws IOException {
        String qlForm = "form taxOfficeExample { if (1 - 1) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertThat(ifThen.getCondition(), instanceOf(Subtraction.class));
    }

    @Test
    public void testEqualLeftAndRight() throws IOException {
        String qlForm = "form taxOfficeExample { if (1 == 1) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);
        Equal condition = (Equal) ifThen.getCondition();

        assertThat(condition.getLhs(), instanceOf(IntegerLiteral.class));
        assertThat(condition.getRhs(), instanceOf(IntegerLiteral.class));
        assertThat(condition, instanceOf(Equal.class));
    }

    @Test
    public void testAndLeftAndRight() throws IOException {
        String qlForm = "form taxOfficeExample { if (true && false) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);
        LogicalAnd condition = (LogicalAnd) ifThen.getCondition();

        assertThat(condition.getLhs(), instanceOf(BooleanLiteral.class));
        assertThat(condition.getRhs(), instanceOf(BooleanLiteral.class));
        assertThat(condition, instanceOf(LogicalAnd.class));
    }

}

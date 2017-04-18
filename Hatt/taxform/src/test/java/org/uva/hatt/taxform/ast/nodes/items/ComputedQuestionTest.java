package org.uva.hatt.taxform.ast.nodes.items;

import org.junit.Test;
import org.uva.hatt.taxform.parsing.ASTGenerator;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.expressions.BinaryExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.binary.Addition;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.Money;
import org.uva.hatt.taxform.ast.nodes.types.String;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class ComputedQuestionTest {

    @Test
    public void testIsComputedQuestion() throws IOException {
        java.lang.String qlForm = "form taxOfficeExample { \"q1?\" value: money = (5 - 1) }";
        Form form = ASTGenerator.getForm(qlForm);

        assertThat(form.getQuestions().get(0), instanceOf(ComputedQuestion.class));
    }

    @Test
    public void testGroupedExpression() throws IOException {
        java.lang.String qlForm = "form taxOfficeExample { \"q1?\" value: money = (5 - 1) }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        ComputedQuestion question = (ComputedQuestion) questions.get(0);

        assertThat(question.getType(), instanceOf(Money.class));
        assertThat(question.getComputedValue(), instanceOf(GroupedExpression.class));
    }

    @Test
    public void testBooleanExpression() throws IOException {
        java.lang.String qlForm = "form taxOfficeExample { \"q1?\" value: boolean = true == true }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        ComputedQuestion question = (ComputedQuestion) questions.get(0);

        assertThat(question.getType(), instanceOf(Boolean.class));
        assertThat(question.getComputedValue(), instanceOf(BinaryExpression.class));
    }

    @Test
    public void testComputationExpression() throws IOException {
        java.lang.String qlForm = "form taxOfficeExample { \"q1?\" value: integer = 1+1 }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        ComputedQuestion question = (ComputedQuestion) questions.get(0);

        assertThat(question.getType(), instanceOf(Integer.class));
        assertThat(question.getComputedValue(), instanceOf(Addition.class));
    }

    @Test
    public void testBooleanLiteral() throws IOException {
        java.lang.String qlForm = "form taxOfficeExample { \"q1?\" value: boolean = true }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        ComputedQuestion question = (ComputedQuestion) questions.get(0);

        assertThat(question.getType(), instanceOf(Boolean.class));
        assertThat(question.getComputedValue(), instanceOf(BooleanLiteral.class));
    }

    @Test
    public void testStringLiteral() throws IOException {
        java.lang.String qlForm = "form taxOfficeExample { \"q1?\" value: string = \"string\" }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        ComputedQuestion question = (ComputedQuestion) questions.get(0);

        assertThat(question.getType(), instanceOf(String.class));
        assertThat(question.getComputedValue(), instanceOf(StringerLiteral.class));
    }

    @Test
    public void testIntegerLiteral() throws IOException {
        java.lang.String qlForm = "form taxOfficeExample { \"q1?\" value: integer = 1 }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        ComputedQuestion question = (ComputedQuestion) questions.get(0);

        assertThat(question.getType(), instanceOf(Integer.class));
        assertThat(question.getComputedValue(), instanceOf(IntegerLiteral.class));
    }

    @Test
    public void testIdentifier() throws IOException {
        java.lang.String qlForm = "form taxOfficeExample { \"q1?\" value: string = var }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        ComputedQuestion question = (ComputedQuestion) questions.get(0);

        assertThat(question.getType(), instanceOf(String.class));
        assertThat(question.getComputedValue(), instanceOf(Identifier.class));
    }

}
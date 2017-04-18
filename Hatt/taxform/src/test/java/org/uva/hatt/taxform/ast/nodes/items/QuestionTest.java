package org.uva.hatt.taxform.ast.nodes.items;

import org.junit.Test;
import org.uva.hatt.taxform.parsing.ASTGenerator;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.Money;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class QuestionTest {

    @Test
    public void testOneQuestion() throws IOException {
        String qlForm = "form taxOfficeExample { \"q1?\" value: boolean }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        Question question = (Question) questions.get(0);

        assertEquals("taxOfficeExample", form.getFormId());
        assertEquals("q1?", question.getLabel());
        assertEquals("value", question.getIdentifier());
        assertThat(question.getType(), instanceOf(Boolean.class));
    }

    @Test
    public void testIsQuestion() throws IOException {
        String qlForm = "form taxOfficeExample { \"q1?\" value: boolean }";
        Form form = ASTGenerator.getForm(qlForm);

        assertThat(form.getQuestions().get(0), instanceOf(Question.class));
    }

    @Test
    public void testTwoQuestions() throws IOException {
        String qlForm = "form taxOfficeExample { \"q1?\" val1: boolean \"q2?\" val2: string }";

        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        Question q1 = (Question) questions.get(0);
        Question q2 = (Question) questions.get(1);

        assertEquals("taxOfficeExample", form.getFormId());
        assertEquals(2, questions.size());

        assertEquals("q1?", q1.getLabel());
        assertEquals("val1", q1.getIdentifier());
        assertThat(q1.getType(), instanceOf(Boolean.class));

        assertEquals("q2?", q2.getLabel());
        assertEquals("val2", q2.getIdentifier());
        assertThat(q2.getType(), instanceOf(org.uva.hatt.taxform.ast.nodes.types.String.class));
    }

    @Test
    public void testIntegerValueType() throws IOException {
        String qlForm = "form taxOfficeExample { \"q1?\" value: integer }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        Question question = (Question) questions.get(0);

        assertThat(question.getType(), instanceOf(Integer.class));
    }


    @Test
    public void testMoneyValueType() throws IOException {
        String qlForm = "form taxOfficeExample { \"q1?\" value: money }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        Question question = (Question) questions.get(0);

        assertThat(question.getType(), instanceOf(Money.class));
    }

    @Test
    public void testTwoLineNumbers() throws IOException {
        String qlForm = "form taxOfficeExample { \n\"q1?\" val1: boolean \n\"q2?\" val2: string }";

        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        Question q1 = (Question) questions.get(0);
        Question q2 = (Question) questions.get(1);

        assertEquals(1, form.getLineNumber());
        assertEquals(2, q1.getLineNumber());
        assertEquals(3, q2.getLineNumber());
    }

}
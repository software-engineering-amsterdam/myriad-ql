package org.uva.hatt.taxform.ast.nodes.items;

import org.junit.Test;
import org.uva.hatt.taxform.parsing.ASTGenerator;
import org.uva.hatt.taxform.ast.nodes.Form;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class IfThenTest {

    @Test
    public void testIfBlock() throws IOException {
        String qlForm = "form taxOfficeExample { if (hasSoldHouse) { \"q1?\" val1: boolean } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertEquals("taxOfficeExample", form.getFormId());
        assertEquals(1, ifThen.getThenStatements().size());
    }

    @Test
    public void testEmptyThenStatements() throws IOException {
        String qlForm = "form taxOfficeExample { if (hasSoldHouse) {  } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertEquals(0, ifThen.getThenStatements().size());
    }

    @Test
    public void testNestedIfBlock() throws IOException {
        String qlForm = "form taxOfficeExample { if (hasSoldHouse) { if (bool) { \"q2?\" val2: boolean } } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen c1 = (IfThen) questions.get(0);

        assertEquals(1, questions.size());
        assertEquals(1, c1.getThenStatements().size());
        assertThat(c1.getThenStatements().get(0), instanceOf(IfThen.class));
    }

    @Test
    public void testMultipleIfBlocks() throws IOException {
        String qlForm = "form taxOfficeExample { if (hasSoldHouse) { \"q1?\" val1: boolean } if (bool) { \"q2?\" val2: boolean } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen c1 = (IfThen) questions.get(0);
        IfThen c2 = (IfThen) questions.get(0);

        assertEquals(2, questions.size());
        assertEquals(1, c1.getThenStatements().size());
        assertEquals(1, c2.getThenStatements().size());
    }

    @Test
    public void testQuestionAndIfThen() throws IOException {
        String qlForm = "form taxOfficeExample { \"q1?\" val1: boolean if (hasSoldHouse) { \"q2?\" val2: boolean } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();

        assertEquals(2, questions.size());
        assertThat(questions.get(0), instanceOf(Question.class));
        assertThat(questions.get(1), instanceOf(IfThen.class));
    }

}
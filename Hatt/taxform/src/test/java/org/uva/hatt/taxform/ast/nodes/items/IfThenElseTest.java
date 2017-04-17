package org.uva.hatt.taxform.ast.nodes.items;

import org.junit.Test;
import org.uva.hatt.taxform.parsing.ASTGenerator;
import org.uva.hatt.taxform.ast.nodes.Form;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class IfThenElseTest {

    @Test
    public void testIsIfThenElse() throws IOException {
        String qlForm = "form taxOfficeExample { if (hasSoldHouse) { \"q1?\" val1: boolean } else { \"q2?\" val2: boolean } }";
        Form form = ASTGenerator.getForm(qlForm);

        assertThat(form.getQuestions().get(0), instanceOf(IfThenElse.class));
    }

    @Test
    public void testIfElseBlock() throws IOException {
        String qlForm = "form taxOfficeExample { if (hasSoldHouse) { \"q1?\" val1: boolean } else { \"q2?\" val2: boolean } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThenElse ifThenElse = (IfThenElse) questions.get(0);

        assertEquals(1, ifThenElse.getThenStatements().size());
        assertEquals(1, ifThenElse.getElseStatements().size());
    }

    @Test
    public void testEmptyElseStatements() throws IOException {
        String qlForm = "form taxOfficeExample { if (hasSoldHouse) { \"q1?\" val1: boolean } else { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThenElse ifThenElse = (IfThenElse) questions.get(0);

        assertEquals(1, ifThenElse.getThenStatements().size());
        assertEquals(0, ifThenElse.getElseStatements().size());
    }

}
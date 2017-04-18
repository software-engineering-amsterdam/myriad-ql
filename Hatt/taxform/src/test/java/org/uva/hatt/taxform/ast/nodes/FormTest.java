package org.uva.hatt.taxform.ast.nodes;

import org.junit.Test;
import org.uva.hatt.taxform.parsing.ASTGenerator;

import java.io.IOException;

import static org.junit.Assert.*;

public class FormTest {

    @Test
    public void testEmptyForm() throws IOException {
        String qlForm = "form taxOfficeExample { }";
        Form form = ASTGenerator.getForm(qlForm);

        assertEquals("taxOfficeExample", form.getFormId());
        assertEquals(0, form.getQuestions().size());
        assertEquals(1, form.getLineNumber());
    }

    @Test
    public void testOneQuestion() throws IOException {
        String qlForm = "form taxOfficeExample { \"Did you sell a house in 2010?\" hasSoldHouse: boolean }";
        Form form = ASTGenerator.getForm(qlForm);

        assertEquals("taxOfficeExample", form.getFormId());
        assertEquals(1, form.getQuestions().size());
        assertEquals(1, form.getLineNumber());
    }

}
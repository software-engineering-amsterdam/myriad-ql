package org.ql.parser;

import org.junit.Test;
import org.ql.ast.form.Form;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FormTest {
    @Test
    public void shouldCreateFormNode() {
        Parser parser = new Parser();
        String inputCode = "form MyForm {}";
        String expectedName = "MyForm";

        Form form = parser.parseForm(inputCode);

        assertTrue(form != null);
        assertEquals(expectedName, form.getName().toString());
    }
}

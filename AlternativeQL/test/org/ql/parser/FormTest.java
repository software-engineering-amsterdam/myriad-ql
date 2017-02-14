package org.ql.parser;

import org.junit.Test;
import static org.junit.Assert.*;
import org.ql.ast.Form;

public class FormTest {
    @Test
    public void shouldCreateFormNode() {
        Parser parser = new Parser();
        String inputCode = "form MyForm {}";
        String expectedName = "MyForm";

        Form form = parser.parse(inputCode);

        assertTrue(form != null);
        assertEquals(expectedName, form.getName().toString());
    }
}

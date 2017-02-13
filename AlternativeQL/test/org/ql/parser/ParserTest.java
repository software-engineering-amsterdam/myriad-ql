package org.ql.parser;

import org.junit.Assert;
import org.junit.Test;
import org.ql.ast.form.Form;
import org.ql.ast.statement.Question;
import org.ql.ast.type.Type;

public class ParserTest extends Assert {
    @Test
    public void shouldCreateFormNode() {
        Parser parser = new Parser();
        String inputCode = "form MyForm {}";
        String expectedName = "MyForm";

        Form form = parser.parse(inputCode);

        assertTrue(form != null);
        assertEquals(expectedName, form.getName().toString());
    }

    @Test
    public void shouldCreateFormWithQuestionStatements() {
        Parser parser = new Parser();
        String inputCode = "form MyNewForm {" +
                "    boolean hasSoldHouse: \"Did you sell a house in 2010?\";\n" +
                "    boolean hasBoughtHouse: \"Did you buy a house in 2010?\";" +
                "}";

        Form ast = parser.parse(inputCode);

        assertSame(2, ast.getStatements().size());
        assertTrue(ast.getStatement(0) instanceof Question);
        assertEquals("hasSoldHouse", ((Question) ast.getStatement(0)).getId().toString());
        assertEquals("Did you sell a house in 2010?", ((Question) ast.getStatement(0)).getQuestionText().toString());
        assertEquals(Type.BOOLEAN, ((Question) ast.getStatement(0)).getType());
    }
}

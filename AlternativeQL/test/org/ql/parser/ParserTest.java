package org.ql.parser;

import org.junit.Assert;
import org.junit.Test;
import org.ql.ast.declaration.Declaration;
import org.ql.ast.form.Form;
import org.ql.ast.declaration.Question;
import org.ql.ast.literal.BooleanLiteral;
import org.ql.ast.literal.FloatLiteral;
import org.ql.ast.literal.IntegerLiteral;
import org.ql.ast.literal.StringLiteral;
import org.ql.ast.type.Type;

import java.util.ArrayList;

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
    public void shouldCreateFormWithQuestionDeclarations() {
        Parser parser = new Parser();
        String inputCode = "form MyNewForm {" +
                "    boolean hasSoldHouse: \"Did you sell a house in 2010?\";\n" +
                "    boolean hasBoughtHouse: \"Did you buy a house in 2010?\";" +
                "}";

        Form ast = parser.parse(inputCode);

        assertSame(2, ast.getDeclarations().size());
        assertTrue(ast.getDeclaration(0) instanceof Question);
        assertEquals("hasSoldHouse", ((Question) ast.getDeclaration(0)).getId().toString());
        assertEquals("Did you sell a house in 2010?", ((Question) ast.getDeclaration(0)).getQuestion().toString());
        assertEquals(Type.BOOLEAN, ((Question) ast.getDeclaration(0)).getType());
    }
}

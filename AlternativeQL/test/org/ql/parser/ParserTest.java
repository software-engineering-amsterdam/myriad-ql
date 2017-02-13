package org.ql.parser;

import org.junit.Assert;
import org.junit.Test;
import org.ql.ast.form.Form;
import org.ql.ast.declaration.Question;
import org.ql.ast.literal.BooleanLiteral;
import org.ql.ast.literal.FloatLiteral;
import org.ql.ast.literal.IntegerLiteral;
import org.ql.ast.literal.StringLiteral;

public class ParserTest extends Assert {
    @Test
    public void shouldReturnFormNode() {
        // Arrange
        Parser parser = new Parser();
        String inputCode = "form MyForm {" +
                "boolean hasSoldHouse: \"Did you sell a house in 2010?\";" +
                "}";
        String expectedName = "MyForm";

        // Act
        Form form = parser.parse(inputCode);

        // Assert
        assertTrue(form != null);
        assertEquals(expectedName, form.getName().toString());
    }
}

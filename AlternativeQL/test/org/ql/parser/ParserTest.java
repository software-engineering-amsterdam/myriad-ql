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
        String inputCode = "form MyForm {}";
        String expectedName = "MyForm";

        // Act
        Form form = parser.parse(inputCode);

        // Assert
        assertTrue(form != null);
        assertEquals(expectedName, form.getName().toString());
    }

    @Test
    public void shouldReturnQuestion() {
        // Arrange
        Parser parser = new Parser();
        String inputCode = "money valueResidue: \"Value residue:\"";
        String expectedType = "money";
        String expectedQuestion = "\"Value residue:\"";
        String expectedID = "valueResidue";

        // Act
        Question question = parser.parseQuestion(inputCode);

        // Assert
        assertTrue(question != null);
        assertEquals(expectedID, question.getId().toString());
        assertEquals(expectedQuestion, question.getQuestion());
        assertEquals(expectedType, question.getType().getType());
    }

    @Test
    public void shouldReturnBooleanLiteral() {
        // Arrange
        Parser parser = new Parser();
        String inputCode = "true";
        boolean expectedBooleanValue = true;
        String expectedType = "boolean";

        // Act
        BooleanLiteral booleanLiteral = parser.parseBooleanLiteral(inputCode);

        // Assert
        assertEquals(expectedBooleanValue, booleanLiteral.getBooleanLiteral());
        assertEquals(expectedType, booleanLiteral.getType().getType());
    }

    @Test
    public void shouldReturnIntegerLiteral() {
        // Arrange
        Parser parser = new Parser();
        String inputCode = "32";
        int expectedIntegerValue = 32;
        String expectedType = "integer";

        // Act
        IntegerLiteral integerLiteral = parser.parseIntegerLiteral(inputCode);

        // Assert
        assertEquals(expectedIntegerValue, integerLiteral.getIntegerLiteral());
        assertEquals(expectedType, integerLiteral.getType().getType());
    }

    @Test
    public void shouldReturnFloatLiteral() {
        // Arrange
        Parser parser = new Parser();
        String inputCode = "1241.532";
        float expectedFloatValue = 1241.532F;
        String expectedType = "float";

        // Act
        FloatLiteral floatLiteral = parser.parseFloatLiteral(inputCode);

        // Assert
        assert(expectedFloatValue == floatLiteral.getFloatLiteral());
        assertEquals(expectedType, floatLiteral.getType().getType());
    }

    @Test
    public void shouldReturnStringLiteral() {
        // Arrange
        Parser parser = new Parser();
        String inputCode = "\"exampleString\"";
        String expectedStringValue = "\"exampleString\"";
        String expectedType = "string";

        // Act
        StringLiteral stringLiteral = parser.parseStringLiteral(inputCode);

        // Assert
        assertEquals(expectedStringValue, stringLiteral.getStringLiteral());
        assertEquals(expectedType, stringLiteral.getType().getType());
    }
}

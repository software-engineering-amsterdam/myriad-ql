package org.ql.parser;

import org.junit.Assert;
import org.junit.Test;
import org.ql.ast.Form;
import org.ql.ast.Question;

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
}

package org.ql.parser;

import org.junit.Test;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.statement.Question;
import org.ql.ast.type.BooleanType;

import static org.junit.Assert.*;

public class QuestionTest {

    @Test
    public void shouldParseQuestionWithoutDefaultValue() {
        String inputCode = "boolean hasSoldHouse: \"Did you sell a house in 2010?\";";
        String expectedId = "hasSoldHouse";
        String expectedQuestion = "Did you sell a house in 2010?";

        Question actualQuestion = (Question) new Parser().parseStatement(inputCode);

        assertEquals(expectedId, actualQuestion.getId().toString());
        assertEquals(expectedQuestion, actualQuestion.getQuestionText().toString());
        assertNull(actualQuestion.getDefaultValue());
        assertTrue(actualQuestion.getType() instanceof BooleanType);
    }

    @Test
    public void shouldParseQuestionWithBooleanTrueDefaultValue() {
        String inputCode = "boolean hasSoldHouse: \"Did you sell a house in 2010?\" = true;";
        boolean expectedDefaultValue = true;

        Question actualQuestion = (Question) new Parser().parseStatement(inputCode);
        BooleanLiteral actualDefaultValue = (BooleanLiteral) actualQuestion.getDefaultValue();

        assertNotNull(actualDefaultValue);
        assertEquals(expectedDefaultValue, actualDefaultValue.getValue());
    }
}

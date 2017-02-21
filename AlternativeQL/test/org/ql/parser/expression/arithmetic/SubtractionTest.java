package org.ql.parser.expression.arithmetic;

import org.junit.Test;
import org.ql.parser.Parser;

import static org.junit.Assert.assertEquals;

public class SubtractionTest {
    @Test
    public void shouldParseSubtraction() {
        String inputCode = "12-3";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("(12-3)", actual);
    }

    @Test
    public void shouldParseMultipleSubtraction() {
        String inputCode = "12-3-6";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("((12-3)-6)", actual);
    }
}

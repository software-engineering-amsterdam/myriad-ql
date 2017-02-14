package org.ql.parser.expression.relational;

import org.junit.Test;
import org.ql.parser.Parser;

import static org.junit.Assert.assertEquals;

public class EqualsTest {
    @Test
    public void shouldParseEquals() {
        String inputCode = "5==7";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("(5==7)", actual);
    }

    @Test
    public void shouldParseMultipleEquals() {
        String inputCode = "5==7==9";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("((5==7)==9)", actual);
    }
}

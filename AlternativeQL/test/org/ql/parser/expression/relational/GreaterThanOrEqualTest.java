package org.ql.parser.expression.relational;

import org.junit.Test;
import org.ql.parser.Parser;

import static org.junit.Assert.assertEquals;

public class GreaterThanOrEqualTest {
    @Test
    public void shouldParseGreaterThanOrEquals() {
        String inputCode = "5>=7";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("(5>=7)", actual);
    }

    @Test
    public void shouldParseMultipleGreaterThanOrEquals() {
        String inputCode = "5>=7>=1";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("((5>=7)>=1)", actual);
    }
}

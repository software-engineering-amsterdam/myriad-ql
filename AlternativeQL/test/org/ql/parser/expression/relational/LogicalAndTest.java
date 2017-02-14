package org.ql.parser.expression.relational;

import org.junit.Test;
import org.ql.parser.Parser;

import static org.junit.Assert.assertEquals;

public class LogicalAndTest {
    @Test
    public void shouldParseLogicalAnd() {
        String inputCode = "true&&false";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("(true&&false)", actual);
    }
}

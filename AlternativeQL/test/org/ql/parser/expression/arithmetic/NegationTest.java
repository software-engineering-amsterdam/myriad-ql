package org.ql.parser.expression.arithmetic;

import org.junit.Test;
import org.ql.parser.Parser;

import static org.junit.Assert.assertEquals;

public class NegationTest {
    @Test
    public void shouldParseNegation() {
        String inputCode = "!exampleVariable";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("!exampleVariable", actual);
    }
}

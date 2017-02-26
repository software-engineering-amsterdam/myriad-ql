package org.ql.parser.expression.arithmetic;

import org.junit.Test;
import org.ql.parser.Parser;

import static org.junit.Assert.assertEquals;

public class IncrementTest {
    @Test
    public void shouldParseIncrement() {
        String inputCode = "exampleVariable++";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("exampleVariable++", actual);
    }
}

package org.ql.parser.expression.relational;

import org.junit.Test;
import org.ql.parser.Parser;

import static org.junit.Assert.assertEquals;

public class NotEqualTest {
    @Test
    public void shouldParseNotEqual() {
        String inputCode = "true!=false";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("(true!=false)", actual);
    }

    @Test
    public void shouldParseMultipleNotEqual() {
        String inputCode = "true!=false!=exampleVariable";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("((true!=false)!=exampleVariable)", actual);
    }
}

package org.ql.parser.expression.arithmetic;

import org.junit.Test;
import org.ql.parser.Parser;

import static org.junit.Assert.assertEquals;

public class GroupTest {
    @Test
    public void shouldParseGroup() {
        String inputCode = "(10/5)";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("((10/5))", actual);
    }
}

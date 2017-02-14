package org.ql.parser.expression.relational;

import org.junit.Test;
import org.ql.parser.Parser;

import static org.junit.Assert.assertEquals;

public class LowerThanTest {
    @Test
    public void shouldParseLowerThan() {
        String inputCode = "2<4";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("(2<4)", actual);
    }
}

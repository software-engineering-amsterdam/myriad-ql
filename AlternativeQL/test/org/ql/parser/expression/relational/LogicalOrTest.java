package org.ql.parser.expression.relational;

import org.junit.Test;
import org.ql.parser.Parser;

import static org.junit.Assert.assertEquals;

public class LogicalOrTest {
    @Test
    public void shouldParseLogicalOr() {
        String inputCode = "true||false";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("(true||false)", actual);
    }
}

package org.ql.parser.expression.relational;

import org.junit.Test;
import org.ql.ast.expression.literal.Integer;
import org.ql.ast.expression.relational.GreaterThanOrEqual;
import org.ql.parser.Parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class GreaterThanTest {
    @Test
    public void shouldParseGreaterThan() {
        String inputCode = "5>7";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("(5>7)", actual);
    }
}

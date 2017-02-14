package org.ql.parser.expression.arithmetic;

import org.junit.Test;
import org.ql.parser.Parser;

import static org.junit.Assert.assertEquals;

public class ProductTest {
    @Test
    public void shouldParseProduct() {
        String inputCode = "12*3";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("(12*3)", actual);
    }

    @Test
    public void shouldParseMultipleProduct() {
        String inputCode = "12*3*9";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("((12*3)*9)", actual);
    }
}

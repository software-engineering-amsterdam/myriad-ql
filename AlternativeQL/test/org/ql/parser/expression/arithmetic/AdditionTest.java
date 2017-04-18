package org.ql.parser.expression.arithmetic;

import org.junit.Test;
import org.ql.parser.Parser;

import static org.junit.Assert.assertEquals;

public class AdditionTest {
    @Test
    public void shouldParseAddition() {
        String inputCode = "5 + 7";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("(5+7)", actual);
    }

    @Test
    public void shouldParseAdditionWithThreeValuesAndAssocRight() {
        String inputCode = "4 + 27 + 3";

        String actual = new Parser().parseExpression(inputCode).toString();

        assertEquals("((4+27)+3)", actual);
    }
}

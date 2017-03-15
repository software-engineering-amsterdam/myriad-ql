package com.matthewchapman.ql.ast.expression.binary;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.core.CoreParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class SubtractionTest {
    private CoreParser coreParser;

    @Before
    public void setUp() {
        coreParser = new CoreParser();
    }

    @Test
    public void checkSubtractionParse() {
        final String EXPECTED_RESULT = "(1 - 2)";
        QLParser parser = coreParser.getQlParser("1-2");
        Subtraction expression = (Subtraction) coreParser.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.toString());
    }

    @Test
    public void checkSubtractionNestedParse() {
        final String EXPECTED_RESULT = "((1 - 2) - 3)";
        QLParser parser = coreParser.getQlParser("1-2-3");
        Subtraction expression = (Subtraction) coreParser.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.toString());
    }
}
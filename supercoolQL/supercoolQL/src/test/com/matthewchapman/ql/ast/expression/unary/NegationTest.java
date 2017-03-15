package com.matthewchapman.ql.ast.expression.unary;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.core.CoreParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class NegationTest {
    private CoreParser coreParser;

    @Before
    public void setUp() {
        coreParser = new CoreParser();
    }

    @Test
    public void checkNegationParse() {
        final String EXPECTED_RESULT = "!(1)";
        QLParser parser = coreParser.getQlParser("!1");
        Negation expression = (Negation) coreParser.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.toString());
    }

    @Test
    public void checkNegationNestedParse() {
        final String EXPECTED_RESULT = "!(!(2))";
        QLParser parser = coreParser.getQlParser("!!2");
        Negation expression = (Negation) coreParser.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.toString());
    }
}
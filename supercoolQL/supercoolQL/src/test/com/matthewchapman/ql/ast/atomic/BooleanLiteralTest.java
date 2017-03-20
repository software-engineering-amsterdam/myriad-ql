package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.expression.literal.BooleanLiteral;
import com.matthewchapman.ql.core.CoreParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class BooleanLiteralTest {

    private CoreParser coreParser;

    @Before
    public void setUp() {
        coreParser = new CoreParser();
    }

    @Test
    public void checkTrueBooleanLiteralParse() {
        final String EXPECTED_RESULT = "true";
        QLParser parser = coreParser.getQlParser("TRUE");
        BooleanLiteral expression = (BooleanLiteral) coreParser.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.toString());
    }

    @Test
    public void checkFalseBooleanLiteralParse() {
        final String EXPECTED_RESULT = "false";
        QLParser parser = coreParser.getQlParser("FALSE");
        BooleanLiteral expression = (BooleanLiteral) coreParser.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.toString());
    }


}
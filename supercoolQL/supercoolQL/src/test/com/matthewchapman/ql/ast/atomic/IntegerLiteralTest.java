package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.expression.literal.IntegerLiteral;
import com.matthewchapman.ql.core.CoreParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class IntegerLiteralTest {

    private CoreParser coreParser;

    @Before
    public void setUp() {
        coreParser = new CoreParser();
    }

    @Test
    public void checkIntegerLiteralParse() {
        final int EXPECTED_RESULT = 12;
        QLParser parser = coreParser.getQlParser("12");
        IntegerLiteral expression = (IntegerLiteral) coreParser.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.getValue());
    }


}
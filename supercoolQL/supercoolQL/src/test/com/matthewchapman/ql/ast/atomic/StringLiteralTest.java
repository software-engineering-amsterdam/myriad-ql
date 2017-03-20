package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.expression.literal.StringLiteral;
import com.matthewchapman.ql.core.CoreParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class StringLiteralTest {

    private CoreParser coreParser;

    @Before
    public void setUp() {
        coreParser = new CoreParser();
    }

    @Test
    public void checkStringLiteralParse() {
        final String EXPECTED_RESULT = "\"testLiteral\"";
        QLParser parser = coreParser.getQlParser("\"testLiteral\"");
        StringLiteral expression = (StringLiteral) coreParser.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.getValue());
    }

}
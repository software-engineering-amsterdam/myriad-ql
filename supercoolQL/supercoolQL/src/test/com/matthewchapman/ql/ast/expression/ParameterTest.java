package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.core.CoreParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class ParameterTest {

    private CoreParser coreParser;

    @Before
    public void setUp() {
        coreParser = new CoreParser();
    }

    @Test
    public void checkParameterParse() {
        final String EXPECTED_RESULT = "testParameter";
        QLParser parser = coreParser.getQlParser("testParameter");
        Parameter parameter = (Parameter) coreParser.getExpression(parser);

        assertEquals(EXPECTED_RESULT, parameter.getID());
    }

}
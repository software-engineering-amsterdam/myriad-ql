package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.core.CoreParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class ParameterGroupTest {

    private CoreParser coreParser;

    @Before
    public void setUp() {
        coreParser = new CoreParser();
    }

    @Test
    public void checkParameterGroupParse() {
        final String EXPECTED_RESULT = "(test1 && test2)";
        QLParser parser = coreParser.getQlParser("( test1 && test2 )");
        ParameterGroup parameterGroup = (ParameterGroup) coreParser.getExpression(parser);

        assertEquals(EXPECTED_RESULT, parameterGroup.toString());
    }


}
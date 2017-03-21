package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.app.ASTBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class ParameterGroupTest {

    private ASTBuilder ASTBuilder;

    @Before
    public void setUp() {
        ASTBuilder = new ASTBuilder();
    }

    @Test
    public void checkParameterGroupParse() {
        final String EXPECTED_RESULT = "(test1 && test2)";
        QLParser parser = ASTBuilder.getQlParser("( test1 && test2 )");
        ParameterGroup parameterGroup = (ParameterGroup) ASTBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, parameterGroup.toString());
    }


}
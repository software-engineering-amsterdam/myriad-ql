package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.app.ASTBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class ParameterTest {

    private ASTBuilder ASTBuilder;

    @Before
    public void setUp() {
        ASTBuilder = new ASTBuilder();
    }

    @Test
    public void checkParameterParse() {
        final String EXPECTED_RESULT = "testParameter";
        QLParser parser = ASTBuilder.getQlParser("testParameter");
        Parameter parameter = (Parameter) ASTBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, parameter.getID());
    }

}
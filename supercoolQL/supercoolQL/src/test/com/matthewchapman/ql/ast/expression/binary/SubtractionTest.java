package com.matthewchapman.ql.ast.expression.binary;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.app.ASTBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class SubtractionTest {
    private ASTBuilder ASTBuilder;

    @Before
    public void setUp() {
        ASTBuilder = new ASTBuilder();
    }

    @Test
    public void checkSubtractionParse() {
        final String EXPECTED_RESULT = "(1 - 2)";
        QLParser parser = ASTBuilder.getQlParser("1-2");
        Subtraction expression = (Subtraction) ASTBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.toString());
    }

    @Test
    public void checkSubtractionNestedParse() {
        final String EXPECTED_RESULT = "((1 - 2) - 3)";
        QLParser parser = ASTBuilder.getQlParser("1-2-3");
        Subtraction expression = (Subtraction) ASTBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.toString());
    }
}
package com.matthewchapman.ql.ast.expression.binary;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.app.ASTBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class LessThanTest {
    private ASTBuilder ASTBuilder;

    @Before
    public void setUp() {
        ASTBuilder = new ASTBuilder();
    }

    @Test
    public void checkLessThanParse() {
        final String EXPECTED_RESULT = "(1 < 2)";
        QLParser parser = ASTBuilder.getQlParser("1<2");
        LessThan expression = (LessThan) ASTBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.toString());
    }

    @Test
    public void checkLessThanNestedParse() {
        final String EXPECTED_RESULT = "((1 < 2) < 3)";
        QLParser parser = ASTBuilder.getQlParser("1<2<3");
        LessThan expression = (LessThan) ASTBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.toString());
    }
}
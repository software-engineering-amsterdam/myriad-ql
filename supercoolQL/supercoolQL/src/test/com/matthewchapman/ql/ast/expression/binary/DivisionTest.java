package com.matthewchapman.ql.ast.expression.binary;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.parsing.ASTBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class DivisionTest {
    private ASTBuilder ASTBuilder;

    @Before
    public void setUp() {
        ASTBuilder = new ASTBuilder();
    }

    @Test
    public void checkDivisionParse() {
        final String EXPECTED_RESULT = "(1 / 2)";
        QLParser parser = ASTBuilder.getQlParser("1/2");
        Division expression = (Division) ASTBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.toString());
    }

    @Test
    public void checkDivisionNestedParse() {
        final String EXPECTED_RESULT = "((1 / 2) / 3)";
        QLParser parser = ASTBuilder.getQlParser("1/2/3");
        Division expression = (Division) ASTBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.toString());
    }
}
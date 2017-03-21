package com.matthewchapman.ql.ast.expression.unary;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.app.ASTBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class NegationTest {
    private ASTBuilder ASTBuilder;

    @Before
    public void setUp() {
        ASTBuilder = new ASTBuilder();
    }

    @Test
    public void checkNegationParse() {
        final String EXPECTED_RESULT = "!(1)";
        QLParser parser = ASTBuilder.getQlParser("!1");
        Negation expression = (Negation) ASTBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.toString());
    }

    @Test
    public void checkNegationNestedParse() {
        final String EXPECTED_RESULT = "!(!(2))";
        QLParser parser = ASTBuilder.getQlParser("!!2");
        Negation expression = (Negation) ASTBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.toString());
    }
}
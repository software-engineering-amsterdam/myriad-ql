package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.expression.literal.BooleanLiteral;
import com.matthewchapman.ql.app.ASTBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class BooleanLiteralTest {

    private ASTBuilder ASTBuilder;

    @Before
    public void setUp() {
        ASTBuilder = new ASTBuilder();
    }

    @Test
    public void checkTrueBooleanLiteralParse() {
        final String EXPECTED_RESULT = "true";
        QLParser parser = ASTBuilder.getQlParser("TRUE");
        BooleanLiteral expression = (BooleanLiteral) ASTBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.toString());
    }

    @Test
    public void checkFalseBooleanLiteralParse() {
        final String EXPECTED_RESULT = "false";
        QLParser parser = ASTBuilder.getQlParser("FALSE");
        BooleanLiteral expression = (BooleanLiteral) ASTBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.toString());
    }


}
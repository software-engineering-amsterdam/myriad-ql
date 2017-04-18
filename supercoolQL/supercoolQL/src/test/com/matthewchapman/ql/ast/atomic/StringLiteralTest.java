package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.expression.literal.StringLiteral;
import com.matthewchapman.ql.parsing.ASTBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class StringLiteralTest {

    private ASTBuilder ASTBuilder;

    @Before
    public void setUp() {
        ASTBuilder = new ASTBuilder();
    }

    @Test
    public void checkStringLiteralParse() {
        final String EXPECTED_RESULT = "\"testLiteral\"";
        QLParser parser = ASTBuilder.getQlParser("\"testLiteral\"");
        StringLiteral expression = (StringLiteral) ASTBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, expression.getValue());
    }
}
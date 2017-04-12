package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.parsing.ASTBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class StringTypeTest {

    private ASTBuilder ASTBuilder;

    @Before
    public void setUp() {
        ASTBuilder = new ASTBuilder();
    }

    @Test
    public void checkQuestionTypeParse() {
        final String EXPECTED_RESULT = "string";
        QLParser parser = ASTBuilder.getQlParser("\"testQuestion\" testQuestion:string");
        Question question = (Question) ASTBuilder.getStatement(parser);

        assertEquals(EXPECTED_RESULT, question.getType().toString());
    }
}
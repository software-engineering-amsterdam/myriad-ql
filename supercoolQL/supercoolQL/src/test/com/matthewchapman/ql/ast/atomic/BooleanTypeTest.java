package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.app.ASTBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class BooleanTypeTest {

    private ASTBuilder ASTBuilder;

    @Before
    public void setUp() {
        ASTBuilder = new ASTBuilder();
    }

    @Test
    public void checkQuestionTypeParse() {
        final String EXPECTED_RESULT = "boolean";
        QLParser parser = ASTBuilder.getQlParser("\"testQuestion\" testQuestion:boolean");
        Question question = (Question) ASTBuilder.getStatement(parser);

        assertEquals(EXPECTED_RESULT, question.getType().toString());
    }

}
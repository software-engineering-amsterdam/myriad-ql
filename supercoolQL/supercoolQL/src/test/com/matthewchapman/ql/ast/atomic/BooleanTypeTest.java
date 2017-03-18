package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.core.CoreParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 15/03/2017.
 */
public class BooleanTypeTest {

    private CoreParser coreParser;

    @Before
    public void setUp() {
        coreParser = new CoreParser();
    }

    @Test
    public void checkQuestionTypeParse() {
        final String EXPECTED_RESULT = "boolean";
        QLParser parser = coreParser.getQlParser("\"testQuestion\" testQuestion:boolean");
        Question question = (Question) coreParser.getStatement(parser);

        assertEquals(EXPECTED_RESULT, question.getType().toString());
    }

}
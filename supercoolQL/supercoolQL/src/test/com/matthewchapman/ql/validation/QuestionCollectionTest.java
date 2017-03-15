package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.core.CoreParser;
import com.matthewchapman.ql.core.FileReader;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 02/03/2017.
 */
public class QuestionCollectionTest {

    private Form form;
    private QuestionCollection questionCollection;

    @Before
    public void setUp() {
        CoreParser parser = new CoreParser();
        FileReader reader = new FileReader();
        String testInput = reader.readFile(new File("res/test.txt"));
        form = parser.buildQLAST(testInput);
        questionCollection = new QuestionCollection();
    }

    @Test
    public void testForDuplicateIDs() throws Exception {
        //questionCollection.
    }

    @Test
    public void testGatherQuestions() throws Exception {
        final int EXPECTED_QUESTIONS = 12;
        final int EXPECTED_PARAMETERS = 13;
        questionCollection.gatherQuestions(form);

        assertEquals(EXPECTED_PARAMETERS, questionCollection.getQuestionList().size());
        assertEquals(EXPECTED_QUESTIONS, questionCollection.getTypeTable().size());
    }

}
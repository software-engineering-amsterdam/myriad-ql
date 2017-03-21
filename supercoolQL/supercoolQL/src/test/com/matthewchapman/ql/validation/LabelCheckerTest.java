package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.app.ASTBuilder;
import com.matthewchapman.ql.app.FileReader;
import com.matthewchapman.ql.validation.structure.LabelChecker;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 02/03/2017.
 */
public class LabelCheckerTest {

    private Form form;
    private LabelChecker labelChecker;

    @Before
    public void setUp() {
        ASTBuilder parser = new ASTBuilder();
        FileReader reader = new FileReader();
        String testInput = reader.readFile(new File("res/test.txt"));
        form = parser.buildQLAST(testInput);
        labelChecker = new LabelChecker();
    }

    @Test
    public void testForDuplicateIDs() throws Exception {
        assertEquals(false, labelChecker.findDuplicates());
    }

    @Test
    public void testGatherQuestions() throws Exception {
        final int EXPECTED_QUESTIONS = 13;
        final int EXPECTED_PARAMETERS = 14;
        labelChecker.gatherQuestions(form);

        assertEquals(EXPECTED_PARAMETERS, labelChecker.getQuestionList().size());
        assertEquals(EXPECTED_QUESTIONS, labelChecker.getTypeTable().size());
    }

}
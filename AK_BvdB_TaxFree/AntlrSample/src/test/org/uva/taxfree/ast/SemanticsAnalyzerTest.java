package test.org.uva.taxfree.ast;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.ast.Ast;
import org.uva.taxfree.main.SemanticsAnalyzer;

import java.io.File;

/**
 * Created by Alex on 20-2-2017.
 */
public class SemanticsAnalyzerTest {
    @Test
    public void testHasDuplicateLabels() throws Exception {
        Ast ast = Ast.generateAst(new File("src\\test\\org\\uva\\taxfree\\ast\\semanticErrors\\duplicateQuestionForm.txt"));
        SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(ast);
        Assert.assertFalse(semanticsAnalyzer.check(), "Duplicate question, so test should fail");

    }

}
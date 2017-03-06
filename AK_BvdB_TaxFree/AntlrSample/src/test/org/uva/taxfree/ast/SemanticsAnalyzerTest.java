package test.org.uva.taxfree.ast;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.ast.Ast;
import org.uva.taxfree.main.SemanticsAnalyzer;
import org.uva.taxfree.model.environment.Environment;

import java.io.File;

public class SemanticsAnalyzerTest {
    @Test
    public File testFile(String name) {
        return new File("src\\test\\org\\uva\\taxfree\\ast\\semanticErrors\\" + name);
    }

    public void testHasDuplicateQuestionLabels() throws Exception {
        Environment environment = Ast.generateAst(testFile("duplicateQuestionLabelForm.txt"));
        SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(environment);
        Assert.assertFalse(semanticsAnalyzer.validSemantics(), "Duplicate question label, so test should fail");
        Assert.assertEquals(semanticsAnalyzer.getSemanticErrors().size(), 1, "We only have one duplicate here");
    }

    @Test
    public void testHasMultipleDuplicateQuestionLabels() throws Exception {
        Environment environment = Ast.generateAst(testFile("duplicateQuestionLabelsForm.txt"));
        SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(environment);
        Assert.assertFalse(semanticsAnalyzer.validSemantics(), "Duplicate question label, so test should fail");
        Assert.assertEquals(semanticsAnalyzer.getSemanticErrors().size(), 2, "We should have three duplicates here");
    }

    @Test
    public void testHasDuplicateQuestionIds() throws Exception {
        Environment environment = Ast.generateAst(testFile("duplicateQuestionIdForm.txt"));
        SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(environment);
        Assert.assertFalse(semanticsAnalyzer.validSemantics(), "Duplicate question id, so test should fail");
        Assert.assertEquals(semanticsAnalyzer.getSemanticErrors().size(), 1, "We only have one duplicate here");
    }

    @Test
    public void testHasMultipleDuplicateQuestionIds() throws Exception {
        Environment environment = Ast.generateAst(testFile("duplicateQuestionIdsForm.txt"));
        SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(environment);
        Assert.assertFalse(semanticsAnalyzer.validSemantics(), "Duplicate question id, so test should fail");
        Assert.assertEquals(semanticsAnalyzer.getSemanticErrors().size(), 2, "We should have three duplicates here");
    }

    @Test
    public void testHasDuplicateQuestionIdsAndLabels() throws Exception {
        Environment environment = Ast.generateAst(testFile("duplicateQuestionIdsAndLabelsForm.txt"));
        SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(environment);
        Assert.assertFalse(semanticsAnalyzer.validSemantics(), "Duplicate question id, so test should fail");
        Assert.assertEquals(semanticsAnalyzer.getSemanticErrors().size(), 4, "We should have four duplicates here");
    }

}
package test.org.uva.taxfree.ast;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.ast.Ast;
import org.uva.taxfree.main.SemanticsAnalyzer;
import org.uva.taxfree.model.environment.Environment;

import java.io.File;
import java.io.IOException;

public class ConditionTest {

    @Test
    public void testLiteralExpression() throws Exception {
        assertSemantics("conditionLiteralForm.txt", 0, "A valid literal condition");
    }

    @Test
    public void testSimpleBooleanExpression() throws Exception {
        assertSemantics("calculationBooleanExpressionForm.txt", 0, "A valid boolean condition");
    }

    @Test
    public void testSimpleIntegerExpression() throws Exception {
        assertSemantics("calculationIntegerExpressionForm.txt", 0, "A valid integer condition");
    }

    @Test
    public void testSimpleUniformExpression() throws Exception {
        assertSemantics("calculationUniformExpressionForm.txt", 0, "A valid uniform condition");
    }

    @Test
    public void testNestedExpression() throws Exception {
        // TODO
        assertSemantics("calculationNestedExpressionForm.txt", 0, "A valid nested condition");
    }

    @Test
    public void testInvalidConditionExpression() throws Exception {
        // TODO: Next step, check types with in combination with operator
        assertSemantics("invalidConditionForm.txt", 1, "An invalid condition due to it's types");
    }

    private void assertSemantics(String fileName, int expectedErrorAmount, String description) throws IOException {
        boolean expectedValid = 0 == expectedErrorAmount;
        SemanticsAnalyzer semanticsAnalyzer = createAnalyzer(fileName);
        Assert.assertEquals(semanticsAnalyzer.validSemantics(), expectedValid, "Expecting errors: " + description);
        Assert.assertEquals(semanticsAnalyzer.getSemanticErrors().size(), expectedErrorAmount, "Invalid error amount");
    }

    private SemanticsAnalyzer createAnalyzer(String fileName) throws IOException {
        Environment environment = Ast.generateAst(testFile(fileName));
        return new SemanticsAnalyzer(environment);
    }

    private File testFile(String fileName) {
        return new File("src\\test\\org\\uva\\taxfree\\ast\\conditionTestFiles\\" + fileName);
    }

}
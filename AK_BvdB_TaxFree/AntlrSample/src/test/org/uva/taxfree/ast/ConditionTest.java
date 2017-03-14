package test.org.uva.taxfree.ast;

import org.testng.annotations.Test;

import java.io.File;

public class ConditionTest extends SemanticsTester {

    @Test
    public void testLiteralExpression() throws Exception {
        assertSemantics("conditionLiteralForm.txt", 0, "A valid literal condition");
    }

    @Test
    public void testVariableLiteralExpression() throws Exception {
        assertSemantics("conditionVariableLiteralForm.txt", 0, "A valid variable literal condition");
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
        assertSemantics("calculationNestedExpressionForm.txt", 0, "A valid nested condition");
    }

    @Test
    public void testInvalidConditionExpression() throws Exception {
        assertSemantics("invalidConditionForm.txt", 1, "An invalid condition due to it's types");
    }

    protected File testFile(String fileName) {
        return new File("src\\test\\org\\uva\\taxfree\\ast\\conditionTestFiles\\" + fileName);
    }

}
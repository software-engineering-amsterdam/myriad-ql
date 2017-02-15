package org.ql.parser.expression.statement;

import org.junit.Test;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.statement.If;
import org.ql.ast.statement.Question;
import org.ql.ast.type.Type;
import org.ql.parser.Parser;

import static org.junit.Assert.*;

public class IfTest {
    @Test
    public void shouldParseIfWithBooleanTrueExpressionAsConditionAndNoBodyStatement() {
        String inputCode = "if (true) {}";
        boolean expectedCondition = true;
        int expectedBodySize = 0;

        If actualIf = (If) new Parser().parseStatement(inputCode);
        BooleanLiteral actualCondition = (BooleanLiteral) actualIf.getCondition();

        assertEquals(expectedCondition, actualCondition.getValue());
        assertSame(expectedBodySize, actualIf.getThenStatements().size());
    }

    @Test
    public void shouldParseIfWithBooleanFalseExpressionAsConditionAndAQuestionInBody() {
        String inputCode = "if (false) {" +
                "   money price: \"Give a price, please\";" +
                "}";
        boolean expectedCondition = false;
        int expectedBodySize = 1;
        Type expectedQuestionType = Type.MONEY;
        String expectedId = "price";
        String expectedQuestionText = "Give a price, please";

        If actualIf = (If) new Parser().parseStatement(inputCode);
        BooleanLiteral actualCondition = (BooleanLiteral) actualIf.getCondition();
        Question actualQuestion = (Question) actualIf.getThenStatements().get(0);

        assertEquals(expectedCondition, actualCondition.getValue());
        assertSame(expectedBodySize, actualIf.getThenStatements().size());
        assertSame(expectedQuestionType, actualQuestion.getType());
        assertEquals(expectedId, actualQuestion.getId().toString());
        assertEquals(expectedQuestionText, actualQuestion.getQuestionText().toString());
    }

    @Test
    public void shouldParseEmptyIfWithEmptyElse() {
        String inputCode = "if (1) {} else {}";
        int expectedSizeOfThenBody = 0;
        int expectedSizeOfElseBody = 0;

        If actualIf = (If) new Parser().parseStatement(inputCode);

        assertSame(expectedSizeOfThenBody, actualIf.getThenStatements().size());
        assertSame(expectedSizeOfElseBody, actualIf.getElseStatements().size());
    }

    @Test
    public void shouldParseEmptyIfWithAStatementInElse() {
        String inputCode = "if (1) {} else {" +
                "   integer packages: \"Enter number of packages\";" +
                "}";
        int expectedSizeOfThenBody = 0;
        int expectedSizeOfElseBody = 1;

        If actualIf = (If) new Parser().parseStatement(inputCode);

        assertSame(expectedSizeOfThenBody, actualIf.getThenStatements().size());
        assertSame(expectedSizeOfElseBody, actualIf.getElseStatements().size());
    }
}

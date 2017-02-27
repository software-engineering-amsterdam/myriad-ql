package org.ql.parser.expression.statement;

import org.junit.Test;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.Question;
import org.ql.ast.type.MoneyType;
import org.ql.parser.Parser;

import static org.junit.Assert.*;

public class IfThenTest {
    @Test
    public void shouldParseIfWithBooleanTrueExpressionAsConditionAndNoBodyStatement() {
        String inputCode = "if (true) {}";
        boolean expectedCondition = true;
        int expectedBodySize = 0;

        IfThen actualIfThenElse = (IfThen) new Parser().parseStatement(inputCode);
        BooleanLiteral actualCondition = (BooleanLiteral) actualIfThenElse.getCondition();

        assertEquals(expectedCondition, actualCondition.getValue());
        assertSame(expectedBodySize, actualIfThenElse.getThenStatements().size());
    }

    @Test
    public void shouldParseIfWithBooleanFalseExpressionAsConditionAndAQuestionInBody() {
        String inputCode = "if (false) {" +
                "   money price: \"Give a price, please\";" +
                "}";
        boolean expectedCondition = false;
        int expectedBodySize = 1;
        String expectedId = "price";
        String expectedQuestionText = "Give a price, please";

        IfThen actualIfThenElse = (IfThen) new Parser().parseStatement(inputCode);
        BooleanLiteral actualCondition = (BooleanLiteral) actualIfThenElse.getCondition();
        Question actualQuestion = (Question) actualIfThenElse.getThenStatements().get(0);

        assertEquals(expectedCondition, actualCondition.getValue());
        assertSame(expectedBodySize, actualIfThenElse.getThenStatements().size());
        assertTrue(actualQuestion.getType() instanceof MoneyType);
        assertEquals(expectedId, actualQuestion.getId().toString());
        assertEquals(expectedQuestionText, actualQuestion.getQuestionText().toString());
    }
}

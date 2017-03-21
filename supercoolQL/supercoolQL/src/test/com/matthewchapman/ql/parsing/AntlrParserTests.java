package com.matthewchapman.ql.parsing;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.atomic.type.IntegerType;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.binary.Addition;
import com.matthewchapman.ql.ast.expression.binary.Subtraction;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.app.ASTBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by matt on 15/03/2017.
 */
public class AntlrParserTests {

    private ASTBuilder ASTBuilder;

    @Before
    public void setUp() {
        ASTBuilder = new ASTBuilder();
    }

    @Test
    public void testFormDeclarationRule() {
        final String EXPECTED_NAME = "testForm";

        Form form = ASTBuilder.getForm(ASTBuilder.getQlParser("form testForm {}"));

        assertEquals(EXPECTED_NAME, form.getName());
    }

    @Test
    public void testQuestionRule() {

        final String EXPECTED_NAME = "testQuestion";
        final String EXPECTED_TEXT = "Test Question";
        final String EXPECTED_TYPE = "boolean";

        QLParser parser = ASTBuilder.getQlParser("\"Test Question\" testQuestion:boolean;");

        Question question = (Question) ASTBuilder.getStatement(parser);

        assertEquals(EXPECTED_NAME, question.getName());
        assertEquals(EXPECTED_TEXT, question.getText());
        assertEquals(EXPECTED_TYPE, question.getType().toString());
    }

    @Test
    public void testIfStatementRule() {

        final String EXPECTED_CONDITION = "ifCase";

        QLParser parser = ASTBuilder.getQlParser("if ( ifCase ) { \"Test Question\" testQuestion:boolean; }");

        IfStatement ifStatement = (IfStatement) ASTBuilder.getStatement(parser);
        Parameter condition = (Parameter) ifStatement.getCondition();

        assertEquals(EXPECTED_CONDITION, condition.getID());
    }

    @Test
    public void testExpressionRule() {

        final String EXPECTED_LEFT_NAME = "test1";
        final String EXPECTED_RIGHT_NAME = "test2";
        final String EXPECTED_EXPRESSION = "(test1 + test2)";

        QLParser parser = ASTBuilder.getQlParser("test1 + test2");
        Addition expression = (Addition) ASTBuilder.getExpression(parser);

        assertEquals(EXPECTED_LEFT_NAME, expression.getLeft().toString());
        assertEquals(EXPECTED_RIGHT_NAME, expression.getRight().toString());
        assertEquals(EXPECTED_EXPRESSION, expression.toString());
        assertTrue(expression.getLeft() instanceof Parameter);
        assertTrue(expression.getRight() instanceof Parameter);
    }

    @Test
    public void testCalculatedValueRule() {

        QLParser parser = ASTBuilder.getQlParser("\"Test Question\" testQuestion:integer = test1 - test2;");
        CalculatedQuestion question = (CalculatedQuestion) ASTBuilder.getStatement(parser);

        assertTrue(question.getType() instanceof IntegerType);
        assertTrue(question.getCalculation() instanceof Subtraction);
    }
}

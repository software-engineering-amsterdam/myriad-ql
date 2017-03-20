package org.uva.hatt.taxform.ast.nodes.expressions;

import org.junit.Test;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.visitors.EnvironmentsTable;

import javax.script.ScriptException;

public class BooleanExpressionTest {

    @Test
    public void testEqualBooleanExpressionEqual() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        BooleanExpression booleanExpression = new BooleanExpression(1, lhs, rhs);

        BooleanLiteral booleanTrue = new BooleanLiteral(1, "true");
        BooleanLiteral booleanFalse = new BooleanLiteral(1, "true");

        booleanExpression.setLeft(booleanTrue);
        booleanExpression.setRight(booleanFalse);
        booleanExpression.setOperator("==");

        assertEquals("true", booleanExpression.evaluate(environmentsTable));
    }

    @Test
    public void testEqualBooleanExpressionNotEqual() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        BooleanExpression booleanExpression = new BooleanExpression(1, lhs, rhs);

        BooleanLiteral booleanTrue = new BooleanLiteral(1, "true");
        BooleanLiteral booleanFalse = new BooleanLiteral(1, "false");

        booleanExpression.setLeft(booleanTrue);
        booleanExpression.setRight(booleanFalse);
        booleanExpression.setOperator("==");

        assertEquals("false", booleanExpression.evaluate(environmentsTable));
    }

    @Test
    public void testNotEqualBooleanExpressionEqual() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        BooleanExpression booleanExpression = new BooleanExpression(1, lhs, rhs);

        BooleanLiteral booleanTrue = new BooleanLiteral(1, "true");
        BooleanLiteral booleanFalse = new BooleanLiteral(1, "false");

        booleanExpression.setLeft(booleanTrue);
        booleanExpression.setRight(booleanFalse);
        booleanExpression.setOperator("!=");

        assertEquals("true", booleanExpression.evaluate(environmentsTable));
    }

    @Test
    public void testNotEqualBooleanExpressionNotEqual() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        BooleanExpression booleanExpression = new BooleanExpression(1, lhs, rhs);

        BooleanLiteral booleanTrue = new BooleanLiteral(1, "true");
        BooleanLiteral booleanFalse = new BooleanLiteral(1, "true");

        booleanExpression.setLeft(booleanTrue);
        booleanExpression.setRight(booleanFalse);
        booleanExpression.setOperator("!=");

        assertEquals("false", booleanExpression.evaluate(environmentsTable));
    }

    @Test
    public void testAndBooleanExpressionEqual() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        BooleanExpression booleanExpression = new BooleanExpression(1, lhs, rhs);

        BooleanLiteral booleanTrue = new BooleanLiteral(1, "true");
        BooleanLiteral booleanFalse = new BooleanLiteral(1, "true");

        booleanExpression.setLeft(booleanTrue);
        booleanExpression.setRight(booleanFalse);
        booleanExpression.setOperator("&&");

        assertEquals("true", booleanExpression.evaluate(environmentsTable));
    }

    @Test
    public void testAndBooleanExpressionNotEqual() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        BooleanExpression booleanExpression = new BooleanExpression(1, lhs, rhs);

        BooleanLiteral booleanTrue = new BooleanLiteral(1, "true");
        BooleanLiteral booleanFalse = new BooleanLiteral(1, "false");

        booleanExpression.setLeft(booleanTrue);
        booleanExpression.setRight(booleanFalse);
        booleanExpression.setOperator("&&");

        assertEquals("false", booleanExpression.evaluate(environmentsTable));
    }

    @Test
    public void testOrBooleanExpressionEqual() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        BooleanExpression booleanExpression = new BooleanExpression(1, lhs, rhs);

        BooleanLiteral booleanTrue = new BooleanLiteral(1, "true");
        BooleanLiteral booleanFalse = new BooleanLiteral(1, "true");

        booleanExpression.setLeft(booleanTrue);
        booleanExpression.setRight(booleanFalse);
        booleanExpression.setOperator("||");

        assertEquals("true", booleanExpression.evaluate(environmentsTable));
    }

    @Test
    public void testOrBooleanExpressionEqualLeft() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        BooleanExpression booleanExpression = new BooleanExpression(1, lhs, rhs);

        BooleanLiteral booleanTrue = new BooleanLiteral(1, "true");
        BooleanLiteral booleanFalse = new BooleanLiteral(1, "false");

        booleanExpression.setLeft(booleanTrue);
        booleanExpression.setRight(booleanFalse);
        booleanExpression.setOperator("||");

        assertEquals("true", booleanExpression.evaluate(environmentsTable));
    }

    @Test
    public void testOrBooleanExpressionEqualRight() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        BooleanExpression booleanExpression = new BooleanExpression(1, lhs, rhs);

        BooleanLiteral booleanTrue = new BooleanLiteral(1, "false");
        BooleanLiteral booleanFalse = new BooleanLiteral(1, "true");

        booleanExpression.setLeft(booleanTrue);
        booleanExpression.setRight(booleanFalse);
        booleanExpression.setOperator("||");

        assertEquals("true", booleanExpression.evaluate(environmentsTable));
    }

    @Test
    public void testOrBooleanExpressionNotEqual() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        BooleanExpression booleanExpression = new BooleanExpression(1, lhs, rhs);

        BooleanLiteral booleanTrue = new BooleanLiteral(1, "false");
        BooleanLiteral booleanFalse = new BooleanLiteral(1, "false");

        booleanExpression.setLeft(booleanTrue);
        booleanExpression.setRight(booleanFalse);
        booleanExpression.setOperator("||");

        assertEquals("false", booleanExpression.evaluate(environmentsTable));
    }

}
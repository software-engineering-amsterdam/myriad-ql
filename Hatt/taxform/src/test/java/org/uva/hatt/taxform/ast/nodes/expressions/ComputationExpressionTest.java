package org.uva.hatt.taxform.ast.nodes.expressions;

import org.junit.Test;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.visitors.EnvironmentsTable;

import javax.script.ScriptException;

import static org.junit.Assert.*;

public class ComputationExpressionTest {

    @Test
    public void testDivisionComputationExpression() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        ComputationExpression computationExpression = new ComputationExpression(1);

        IntegerLiteral literalSix = new IntegerLiteral(1, "6");
        IntegerLiteral literalTwo = new IntegerLiteral(1, "2");

        computationExpression.setLeft(literalSix);
        computationExpression.setRight(literalTwo);
        computationExpression.setOperator("/");

        assertEquals("3", computationExpression.evaluate(environmentsTable));
    }

    @Test
    public void testProductComputationExpression() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        ComputationExpression computationExpression = new ComputationExpression(1);

        IntegerLiteral literalSix = new IntegerLiteral(1, "6");
        IntegerLiteral literalTwo = new IntegerLiteral(1, "2");

        computationExpression.setLeft(literalSix);
        computationExpression.setRight(literalTwo);
        computationExpression.setOperator("*");

        assertEquals("12", computationExpression.evaluate(environmentsTable));
    }

    @Test
    public void testSubtractionComputationExpression() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        ComputationExpression computationExpression = new ComputationExpression(1);

        IntegerLiteral literalSix = new IntegerLiteral(1, "6");
        IntegerLiteral literalTwo = new IntegerLiteral(1, "2");

        computationExpression.setLeft(literalSix);
        computationExpression.setRight(literalTwo);
        computationExpression.setOperator("-");

        assertEquals("4", computationExpression.evaluate(environmentsTable));
    }

    @Test
    public void testAdditionComputationExpression() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        ComputationExpression computationExpression = new ComputationExpression(1);

        IntegerLiteral literalSix = new IntegerLiteral(1, "6");
        IntegerLiteral literalTwo = new IntegerLiteral(1, "2");

        computationExpression.setLeft(literalSix);
        computationExpression.setRight(literalTwo);
        computationExpression.setOperator("+");

        assertEquals("8", computationExpression.evaluate(environmentsTable));
    }

    @Test
    public void testLowerThanComputationExpression() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        ComputationExpression computationExpression = new ComputationExpression(1);

        IntegerLiteral literalSix = new IntegerLiteral(1, "6");
        IntegerLiteral literalTwo = new IntegerLiteral(1, "2");

        computationExpression.setLeft(literalSix);
        computationExpression.setRight(literalTwo);
        computationExpression.setOperator("<");

        assertEquals("false", computationExpression.evaluate(environmentsTable));
    }

    @Test
    public void testLowerThanOrEqualComputationExpression() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        ComputationExpression computationExpression = new ComputationExpression(1);

        IntegerLiteral literalSix = new IntegerLiteral(1, "4");
        IntegerLiteral literalTwo = new IntegerLiteral(1, "5");

        computationExpression.setLeft(literalSix);
        computationExpression.setRight(literalTwo);
        computationExpression.setOperator("<=");

        assertEquals("true", computationExpression.evaluate(environmentsTable));
    }

    @Test
    public void testGreaterThanComputationExpression() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        ComputationExpression computationExpression = new ComputationExpression(1);

        IntegerLiteral literalSix = new IntegerLiteral(1, "6");
        IntegerLiteral literalTwo = new IntegerLiteral(1, "2");

        computationExpression.setLeft(literalSix);
        computationExpression.setRight(literalTwo);
        computationExpression.setOperator(">");

        assertEquals("true", computationExpression.evaluate(environmentsTable));
    }

    @Test
    public void testGreaterThanOrEqualComputationExpression() throws ScriptException {
        EnvironmentsTable environmentsTable = new EnvironmentsTable();

        ComputationExpression computationExpression = new ComputationExpression(1);

        IntegerLiteral literalSix = new IntegerLiteral(1, "6");
        IntegerLiteral literalTwo = new IntegerLiteral(1, "2");

        computationExpression.setLeft(literalSix);
        computationExpression.setRight(literalTwo);
        computationExpression.setOperator(">=");

        assertEquals("true", computationExpression.evaluate(environmentsTable));
    }


}
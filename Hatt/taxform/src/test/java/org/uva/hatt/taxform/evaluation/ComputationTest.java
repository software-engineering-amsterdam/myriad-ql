package org.uva.hatt.taxform.evaluation;

import org.junit.Before;
import org.junit.Test;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.binary.Addition;
import org.uva.hatt.taxform.ast.nodes.expressions.binary.Division;
import org.uva.hatt.taxform.ast.nodes.expressions.binary.Multiplication;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.values.IntegerValue;

import static org.junit.Assert.assertEquals;

public class ComputationTest {

    private Evaluator evaluator;

    @Before
    public void setUp() throws Exception {
        evaluator = new Evaluator(new Environment());
    }

    @Test
    public void testMultiplicationBeforeAddition() throws Exception {
        // 3 + 6 * 2 = 15
        IntegerLiteral three = new IntegerLiteral(1, 3);
        Multiplication multiplication = new Multiplication(1, new IntegerLiteral(1, 6), new IntegerLiteral(1, 2));

        Addition addition = new Addition(1, three, multiplication);
        IntegerValue value = (IntegerValue) evaluator.visit(addition);

        assertEquals(new Integer(15), value.getValue());
    }

    @Test
    public void testDivisionBeforeSubtract() throws Exception {
        // 2 + 6 / 3 = 4
        IntegerLiteral two = new IntegerLiteral(1, 2);
        Division division = new Division(1, new IntegerLiteral(1, 6), new IntegerLiteral(1, 3));

        Addition addition = new Addition(1, two, division);
        IntegerValue value = (IntegerValue) evaluator.visit(addition);

        assertEquals(new Integer(4), value.getValue());
    }

    @Test
    public void testDivisionAndMultiplication() throws Exception {
        // 30 / 5 * 3 = 18
        IntegerLiteral three = new IntegerLiteral(1, 3);
        Division division = new Division(1, new IntegerLiteral(1, 30), new IntegerLiteral(1, 5));

        Multiplication addition = new Multiplication(1, division, three);
        IntegerValue value = (IntegerValue) evaluator.visit(addition);

        assertEquals(new Integer(18), value.getValue());
    }

    @Test
    public void testParenthesesFirst() throws Exception {
        // (3 + 6) * 2 = 18
        IntegerLiteral three = new IntegerLiteral(1, 3);
        IntegerLiteral six = new IntegerLiteral(1, 6);
        Addition addition = new Addition(1, three, six);

        GroupedExpression groupedExpression = new GroupedExpression(1, addition);
        Multiplication multiplication = new Multiplication(1, groupedExpression, new IntegerLiteral(1, 2));

        IntegerValue value = (IntegerValue) evaluator.visit(multiplication);

        assertEquals(new Integer(18), value.getValue());
    }

    @Test
    public void testDivisionByZero() throws Exception {
        // 6/0 = 0
        Division division = new Division(1, new IntegerLiteral(1, 6), new IntegerLiteral(1, 0));
        IntegerValue value = (IntegerValue) evaluator.visit(division);

        assertEquals(new Integer(0), value.getValue());
    }
}

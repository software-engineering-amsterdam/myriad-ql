package com.matthewchapman.ql.environment.evaluation;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.expression.binary.Addition;
import com.matthewchapman.ql.ast.expression.binary.Equal;
import com.matthewchapman.ql.ast.expression.binary.LogicalAnd;
import com.matthewchapman.ql.ast.expression.binary.Multiplication;
import com.matthewchapman.ql.ast.expression.literal.BooleanLiteral;
import com.matthewchapman.ql.ast.expression.literal.IntegerLiteral;
import com.matthewchapman.ql.ast.expression.literal.StringLiteral;
import com.matthewchapman.ql.environment.datastores.ValueTable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by matt on 21/03/2017.
 * <p>
 * Tests for correct expression evaluation
 */
public class ExpressionEvaluatorTest {

    @Test
    public void testEvaluation() {

        final int RESULT_1 = 24;
        final int RESULT_2 = 120;
        final boolean RESULT_3 = true;
        final boolean RESULT_4 = true;
        final boolean RESULT_5 = true;

        Expression test1 = new Addition(new IntegerLiteral("12", 0, 0), new IntegerLiteral("12", 0, 0), 0, 0);
        Expression test2 = new Multiplication(new IntegerLiteral("12", 0, 0), new IntegerLiteral("10", 0, 0), 0, 0);
        Expression test3 = new LogicalAnd(new BooleanLiteral("true", 0, 0), new BooleanLiteral("true", 0, 0), 0, 0);
        Expression test4 = new Equal(new StringLiteral("test", 0, 0), new StringLiteral("test", 0, 0), 0, 0);
        Expression test5 = new Equal(new IntegerLiteral("12", 0, 0), new IntegerLiteral("12", 0, 0), 0, 0);
        ValueTable values = new ValueTable();

        ExpressionEvaluator eval = new ExpressionEvaluator();

        values.addOrUpdateValue("test1", eval.evaluateExpression("test1", test1, values));
        values.addOrUpdateValue("test2", eval.evaluateExpression("test2", test2, values));
        values.addOrUpdateValue("test3", eval.evaluateExpression("test3", test3, values));
        values.addOrUpdateValue("test4", eval.evaluateExpression("test4", test4, values));
        values.addOrUpdateValue("test5", eval.evaluateExpression("test5", test5, values));

        assertNotNull(values.getValueByID("test1"));
        assertNotNull(values.getValueByID("test2"));
        assertNotNull(values.getValueByID("test3"));
        assertNotNull(values.getValueByID("test4"));
        assertNotNull(values.getValueByID("test5"));

        assertEquals(RESULT_1, values.getValueByID("test1").getValue());
        assertEquals(RESULT_2, values.getValueByID("test2").getValue());
        assertEquals(RESULT_3, values.getValueByID("test3").getValue());
        assertEquals(RESULT_4, values.getValueByID("test4").getValue());
        assertEquals(RESULT_5, values.getValueByID("test5").getValue());
    }
}
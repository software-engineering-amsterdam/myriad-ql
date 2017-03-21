package com.matthewchapman.ql.environment.evaluation;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.expression.binary.Addition;
import com.matthewchapman.ql.ast.expression.literal.IntegerLiteral;
import com.matthewchapman.ql.environment.datastores.ValueTable;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by matt on 21/03/2017.
 */
public class ExpressionEvaluatorTest {

    private Expression test;
    private ValueTable values;

    @Before
    public void setUp() throws Exception {
        test = new Addition(new IntegerLiteral("12", 0, 0), new IntegerLiteral("12", 0, 0), 0, 0);
        values = new ValueTable();
    }

    @Test
    public void testAddition() {
        ExpressionEvaluator eval = new ExpressionEvaluator();
        eval.evaluateExpression("test", test, values);
    }

}
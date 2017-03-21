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
import org.junit.Before;
import org.junit.Test;

/**
 * Created by matt on 21/03/2017.
 */
public class ExpressionEvaluatorTest {

    private Expression test1;
    private Expression test2;
    private Expression test3;
    private Expression test4;
    private ValueTable values;

    @Before
    public void setUp() throws Exception {
        test1 = new Addition(new IntegerLiteral("12", 0, 0), new IntegerLiteral("12", 0, 0), 0, 0);
        test2 = new Multiplication(new IntegerLiteral("12", 0, 0), new IntegerLiteral("10", 0, 0), 0, 0);
        test3 = new LogicalAnd(new BooleanLiteral("true", 0,0), new BooleanLiteral("true", 0, 0), 0, 0);
        test4 = new Equal(new StringLiteral("test", 0, 0), new StringLiteral("test",0,0), 0, 0);
        values = new ValueTable();
    }

    @Test
    public void testAddition() {
        ExpressionEvaluator eval = new ExpressionEvaluator();
        eval.evaluateExpression("test1", test1, values);
        eval.evaluateExpression("test2", test2, values);
        eval.evaluateExpression("test3", test3, values);
        eval.evaluateExpression("test4", test4, values);

        System.out.println();
    }

}
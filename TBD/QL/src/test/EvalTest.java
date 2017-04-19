package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ql.ast.Expr;
import ql.ast.expressions.binop.*;
import ql.ast.literals.QLBoolean;
import ql.ast.literals.QLFloat;
import ql.ast.literals.QLInt;
import ql.gui.evaluator.GUIEvaluator;
import ql.logger.ErrorHandler;
import ql.values.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Erik on 18-4-2017.
 */
public class EvalTest {
    private ErrorHandler errorHandler;


    @BeforeEach
    public void setup() {
        errorHandler = new ErrorHandler(true);
    }

    @AfterEach
    public void cleanup() {
        errorHandler = null;
    }

    @Test
    public void IntAddTest() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new Add(new QLInt(5, 0), new QLInt(10, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof IntValue);
        assertTrue(((IntValue) value).getValue() == 15);
    }

    @Test
    public void IntFloatAddTest() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new Sub(new QLFloat(7.5f, 0), new QLInt(10, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof FloatValue);
        assertTrue(((FloatValue) value).getValue() == -2.5f);
    }



    @Test
    public void IntBoolAddExceptionTest() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new Sub(new QLBoolean(true, 0), new QLInt(10, 0), 0);

        assertThrows(NotImplementedException.class, ()-> expr.accept(guiEvaluator));
    }

    @Test
    public void IntIntAndExceptionTest() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new And(new QLInt(5, 0), new QLInt(10, 0), 0);

        assertThrows(NotImplementedException.class, ()-> expr.accept(guiEvaluator));
    }

    @Test
    public void IntIntDivisionTest() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new Div(new QLInt(5, 0), new QLInt(2, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof IntValue);
        assertTrue(((IntValue) value).getValue() == 2);
    }

    @Test
    public void IntFloatDivisionTest() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new Div(new QLInt(5, 0), new QLFloat(2.0f, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof FloatValue);
        assertTrue(((FloatValue) value).getValue() == 2.5);
    }

    @Test
    public void DivisionByZeroTest() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new Div(new QLInt(5, 0), new QLInt(0, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof UndefinedValue);
    }

    @Test
    public void IntIntGreaterThanTest() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new GT(new QLInt(5, 0), new QLInt(2, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof BooleanValue);
        assertTrue(((BooleanValue) value).getValue());
    }

    @Test
    public void IntIntGreaterThanTest2() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new GT(new QLInt(5, 0), new QLInt(5, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof BooleanValue);
        assertFalse(((BooleanValue) value).getValue());
    }

    @Test
    public void IntIntGreaterThanTest3() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new GT(new QLInt(3, 0), new QLInt(5, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof BooleanValue);
        assertFalse(((BooleanValue) value).getValue());
    }

    @Test
    public void IntIntGreaterThanEqualsTest() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new GEq(new QLInt(5, 0), new QLInt(3, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof BooleanValue);
        assertTrue(((BooleanValue) value).getValue());
    }

    @Test
    public void IntIntGreaterThanEqualsTest2() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new GEq(new QLInt(5, 0), new QLInt(5, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof BooleanValue);
        assertTrue(((BooleanValue) value).getValue());
    }

    @Test
    public void IntIntGreaterThanEqualsTest3() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new GEq(new QLInt(3, 0), new QLInt(4, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof BooleanValue);
        assertFalse(((BooleanValue) value).getValue());
    }

    @Test
    public void BoolAndTest() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new And(new QLBoolean(true, 0), new QLBoolean(true, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof BooleanValue);
        assertTrue(((BooleanValue) value).getValue());
    }

    @Test
    public void BoolAndTest2() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new And(new QLBoolean(true, 0), new QLBoolean(false, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof BooleanValue);
        assertFalse(((BooleanValue) value).getValue());
    }

    @Test
    public void BoolAndTest3() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new And(new QLBoolean(false, 0), new QLBoolean(false, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof BooleanValue);
        assertFalse(((BooleanValue) value).getValue());
    }

    @Test
    public void BoolOrTest() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new Or(new QLBoolean(true, 0), new QLBoolean(true, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof BooleanValue);
        assertTrue(((BooleanValue) value).getValue());
    }

    @Test
    public void BoolOrTest2() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new Or(new QLBoolean(true, 0), new QLBoolean(false, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof BooleanValue);
        assertTrue(((BooleanValue) value).getValue());
    }

    @Test
    public void BoolOrTest3() {

        GUIEvaluator guiEvaluator = new GUIEvaluator(null);
        Expr expr = new Or(new QLBoolean(false, 0), new QLBoolean(false, 0), 0);
        Value value = expr.accept(guiEvaluator);

        assertTrue(value instanceof BooleanValue);
        assertFalse(((BooleanValue) value).getValue());
    }

}

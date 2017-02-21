package scriptengine;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.beans.Expression;

import static org.testng.Assert.*;

public class EvaluatorTest {
    Evaluator mEvalutor;
    @BeforeMethod
    public void setUp(){
        mEvalutor = new Evaluator();
    }
    @Test
    public void testSomeCalculations() throws  Exception{
        Assert.assertEquals(mEvalutor.calculate("5+5"), "10", "5+5 should equal 10");
    }

    @Test
    public void testBooleanExpression() throws Exception{
        Assert.assertEquals(mEvalutor.calculate("true == false"), "false", "true is never false");
        Assert.assertEquals( mEvalutor.calculate("5 > 0"), "true", "this always holds");
    }
}
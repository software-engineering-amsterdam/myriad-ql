package scriptengine;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EvaluatorTest {
    Evaluator mEvalutor;
    @BeforeMethod
    public void setUp(){
        mEvalutor = new Evaluator();
    }
    @Test
    public void testSomeCalculations() throws  Exception{
        Assert.assertEquals(mEvalutor.calcluate("5+5"), "10", "5+5 should equal 10");
    }
}
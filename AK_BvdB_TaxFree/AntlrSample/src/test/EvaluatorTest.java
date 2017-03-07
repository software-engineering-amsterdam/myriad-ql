package test;

import org.testng.annotations.Test;
import org.uva.taxfree.util.Evaluator;

import javax.script.ScriptException;

public class EvaluatorTest {

    @Test
    public void testInvalidExpression() {
        String condition = "true + true";
        String result = new String();
        try {
            result = Evaluator.calculate(condition);
        } catch (ScriptException e) {
            System.out.println("Invalid condition: " + condition);
        }
        System.out.println(result);
    }
}
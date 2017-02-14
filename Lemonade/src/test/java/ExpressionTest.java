/**
 * Created by dimitribelfor on 07/02/2017.
 */
import org.junit.Test;

import org.lemonade.expression.BinaryExpression;

public class ExpressionTest {
    @Test
    public void testPrint(){
        BinaryExpression bin = new BinaryExpression(QLType.INTEGER, OperatorType.BinaryOperator.LT, 5, 6);
        System.out.println(bin);
    }
}

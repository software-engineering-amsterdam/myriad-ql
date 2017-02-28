package org.lemonade;

import org.junit.Test;
import org.lemonade.nodes.expressions.literal.BooleanLit;
import org.lemonade.nodes.expressions.literal.DecimalLit;
import org.lemonade.nodes.expressions.literal.IntegerLit;
import org.lemonade.nodes.expressions.literal.NumericLit;
import org.lemonade.nodes.types.QLBooleanType;
import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.nodes.types.QLIntegerType;

import static org.assertj.core.api.Assertions.assertThat;
/**
 *
 */
public class EvaluateTest {

    @Test
    public void testBooleanLit(){
        BooleanLit boolTrue = new BooleanLit(new QLBooleanType(), true);
        BooleanLit boolFalse = new BooleanLit(new QLBooleanType(), false);
        assert boolTrue.getValue() instanceof Boolean;
        assert (boolTrue.or(boolFalse)).getValue() == true;
        assert (boolTrue.or(boolTrue)).getValue() == true;
        assert (boolTrue.and(boolFalse)).getValue() == false;
        assert (boolTrue.and(boolTrue)).getValue() == true;
    }

    @Test
    public void testIntegerLit(){
        IntegerLit one = new IntegerLit(new QLIntegerType(), 1);
        NumericLit two = new DecimalLit(new QLDecimalType(), 2);

        assert (one.compareTo(one)) == 0;
        System.err.println(one.plus(two).getType());
    }
}

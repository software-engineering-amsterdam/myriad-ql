package org.lemonade;

import org.junit.Test;
import org.lemonade.nodes.expressions.literal.BooleanValue;
import org.lemonade.nodes.expressions.literal.DecimalValue;
import org.lemonade.nodes.expressions.literal.IntegerValue;
import org.lemonade.nodes.expressions.literal.NumericValue;
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
        BooleanValue boolTrue = new BooleanValue(new QLBooleanType(), true);
        BooleanValue boolFalse = new BooleanValue(new QLBooleanType(), false);
        assert boolTrue.getValue() instanceof Boolean;
        assert (boolTrue.or(boolFalse)).getValue() == true;
        assert (boolTrue.or(boolTrue)).getValue() == true;
        assert (boolTrue.and(boolFalse)).getValue() == false;
        assert (boolTrue.and(boolTrue)).getValue() == true;
    }

    @Test
    public void testIntegerLit(){
        IntegerValue one = new IntegerValue(new QLIntegerType(), 1);
        NumericValue two = new DecimalValue(new QLDecimalType(), 2);

        assert (one.compareTo(one)) == 0;
        System.err.println(one.plus(two).getType());
    }
}

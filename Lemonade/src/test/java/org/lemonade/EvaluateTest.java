package org.lemonade;

import org.junit.Test;
import org.lemonade.nodes.expressions.value.*;
import org.lemonade.nodes.types.QLBooleanType;
import org.lemonade.nodes.types.QLDateType;
import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.nodes.types.QLIntegerType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Test
    public void testDateValue() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DateValue date = new DateValue(new QLDateType(), sdf.parse("21/12/2012"));
        DateValue dateTwo = new DateValue(new QLDateType(), sdf.parse("22/07/1991"));
        assertThat(date.compareTo(dateTwo)).isEqualTo(1);
        assertThat(date.compareTo(date)).isEqualTo(0);
        assertThat(date.equals(date)).isEqualTo(true);
        assertThat(date.equals(dateTwo)).isEqualTo(false);
    }

    @Test
    public void testDivision() {
        IntegerValue oneInt = new IntegerValue(new QLIntegerType(), 1);
        IntegerValue zeroInt = new IntegerValue(new QLIntegerType(), 0);
        IntegerValue twoInt = new IntegerValue(new QLIntegerType(), 2);

        DecimalValue twoDec = new DecimalValue(new QLDecimalType(), 2);
        DecimalValue halfDec = new DecimalValue(new QLDecimalType(), 1.0/2);

        assertThat(oneInt.divide(twoInt)).isEqualTo(zeroInt);
        assertThat(oneInt.divide(twoDec).getValue()).isEqualTo(halfDec.getValue());
        assertThat(oneInt.divide(zeroInt)).isInstanceOf(UndefinedValue.class);
    }
}
